package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import Model.Student;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ServerController implements Initializable {
//	public Stage primaryStage;
	public Stage serverStage;
	@FXML	private TextArea textArea;
	@FXML	private TableView<Client> tableView;
	@FXML	private Button btnStartStop;
	@FXML	private TextField txtField;
	@FXML	private Button btnSend;
	private ServerSocket serverSocket;
	private ObservableList<Client> obclntList = FXCollections.observableArrayList();
	private List<Client> list = new Vector<Client>();
	private Thread mainThread;
	private Integer count=0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 1.버튼을 누르면 start <--> stop Toggle 하고 해당된 함수를 부름.
		eventButtonStartStopHandle();
		
		// 2. 테이블뷰 세팅
		initTableView();
		
		// 3. 테이블뷰에 선택된 회원에게 메세지 전송 
		txtField.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)) {
				tableView.getSelectionModel().getSelectedItem().send(txtField.getText());
				txtField.clear();
			}	
		});
		btnSend.setOnAction(e ->{
			tableView.getSelectionModel().getSelectedItem().send(txtField.getText());
			txtField.clear();
		});
	}

	private void eventButtonStartStopHandle() {
		btnStartStop.setOnAction(e -> {
			if (btnStartStop.getText().equals("시작")) {
				startServer();
			} else if (btnStartStop.getText().equals("정지")) {
				stopServer();
			}
		});
	}

	// 2. 테이블뷰 세팅
	private void initTableView() {
		TableColumn tcName = tableView.getColumns().get(0);
		tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tcName.setStyle("-fx-alignment: CENTER;");		
		
		TableColumn tcStudentID = tableView.getColumns().get(1);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcPhone= tableView.getColumns().get(2);
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcPhone.setStyle("-fx-alignment: CENTER;");
		TableColumn tcSocket = tableView.getColumns().get(3);
		tcSocket.setCellValueFactory(new PropertyValueFactory<>("socket"));
		tcSocket.setStyle("-fx-alignment: CENTER;");
		
	}
	
	public void stopServer() {

		try {
			for (Client client : obclntList) {
				client.socket.close();
				obclntList.remove(client);
			} // 향상된 for
		} catch (Exception e) {
			if (mainThread.isAlive()) {
				mainThread.stop();
			}
		}
		if (!serverSocket.isClosed() && serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e1) {
			}
		}

		Platform.runLater(() -> {
			textArea.appendText("서버가 정지되었습니다."+"\n");
			btnStartStop.setText("시작");
		});

	}
	
	// make ServerSocket -> bind ->[Thread로 시작(3번 방식) -> 화면수정작업
	// 진행(Platform.runlater())-> wait
	// -> connect -> make client Socket -> 클라이언트클래스 객체 생성 ->to
	// collectionFrameWork(Vector) :
	// 스레드완료]
	// 모든과정을 Thread로 작동
	public void startServer() {
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 5004));
			
		} catch (IOException e) {
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		Runnable runnable = () -> {
			// 일반 스레드에서 자바 FX application Thread에서 해야할 일을 (UI) 수정하려면 Platform.runLater()를
			// 불러준다.
			// JavaFX application Thread가 하도록 만들어준다.
			Platform.runLater(() -> {
				textArea.appendText("[서버 시작] " + Thread.currentThread().getName() + "\n");
				btnStartStop.setText("정지");
			});
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					
					Platform.runLater(() -> {
						textArea.appendText("[연결 수락 : " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]" + "\n");// 상대방의 IP 출력
						
					});
					
//					count++;
//					String name ="user" + count;
					Client client = new Client(socket);
					obclntList.add(client);
					Platform.runLater(()->{
						
						tableView.setItems(obclntList);					
					});
					Platform.runLater(() -> {
						// 상대방의 IP 출력
						textArea.appendText("[연결 개수]" + obclntList.size()+"\n");
					});
				} catch (IOException e) {
					if (!serverSocket.isClosed()) {
						stopServer();
						break;
					}
				}
			} // end of while
		};// end of runnable
		mainThread = new Thread(runnable);// Thread-4번
		mainThread.start();
	}// startServer end

	public class Client {
		private Socket socket;
		private String name;
		private String studentID;
		private String phone;
		private Student student;

		public Client(Socket socket) {
			super();
			this.socket= socket;
			// 나로 부터 오는 데이터를 다 뿌려준다. 소켓으로 부터 inputStream 받아서, read기다리고
			// -> 모든 client에게 메세지를 전달 outputStream
			receive();
		}
	
		
		// 메세지 올 것을 계속 기다려야한다.
		public void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					// error발생시 while문 나가게한다.
					try {
						while (true) {
							BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							String receiveMessage = br.readLine(); // 대기 상태
							if (receiveMessage == null) {
								throw new IOException();
							} // null을 보냈다 ->상대 Client가 종료
							
							String rMessage = receiveMessage.substring(0, 4);
							switch(rMessage) {
							case "INFO":
								//클라이언트의 NAME 과 ID 가져옴
//								contentText.substring(0, contentText.lastIndexOf(":")));
								String information = receiveMessage.substring(0,receiveMessage.lastIndexOf(":") );
								String id = receiveMessage.substring(receiveMessage.lastIndexOf(":")+1 );
								name = information.substring(4);	
								student = StudentDAO.searchStudentByStuID(id);
								studentID = student.getStudentID();
								phone = student.getPhone();
								System.out.println(studentID);
								break;
							case "SEND":
								String string2 = receiveMessage.substring(5);
								Platform.runLater(() -> {
									textArea.appendText("['" +name+ "'님의 말]"+string2 + "\n");
								});
								break;
							default:
								textArea.appendText("Warning!!!!! 문제가 있습니다.");
								break;
							}
							
							/*Platform.runLater(() -> {
								textArea.appendText("[메세지 수신]" +name+ receiveMessage + "\n");
							});		*/				
						}
					} catch (IOException e) {
						// 데이터 안주고 null발생 시(클라이언트 서버가 죽는 경우) -> 컬렉션프레임워크에서 해당 객체를 지운다.
						obclntList.remove(Client.this);
					} // end of while
				}// end of run method
			};
			new Thread(runnable).start();
		}
		public void send(String message) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.println(message);
						pw.flush();
					} catch (IOException e) {
						Platform.runLater(() -> {
							textArea.appendText("[통신 오류]" + socket.getRemoteSocketAddress() + "\n");
						});
						obclntList.remove(Client.this);// 그냥 this는 임시객체를 가리키기 때문에 Client.this 사용
						try {
							socket.close();
						} catch (IOException e1) {
						}
					}
				}
			});
			thread.start();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Socket getSocket() {
			return socket;
		}
		public void setSocket(Socket socket) {
			this.socket = socket;
		}


		public String getStudentID() {
			return studentID;
		}


		public void setStudentID(String studentID) {
			this.studentID = studentID;
		}


		public Student getStudent() {
			return student;
		}


		public void setStudent(Student student) {
			this.student = student;
		}
		
	}// end of class Client
//	public void setPrimaryStage(Stage primaryStage) {
//		this.primaryStage = primaryStage;
//	}
}