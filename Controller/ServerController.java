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
		// 1.��ư�� ������ start <--> stop Toggle �ϰ� �ش�� �Լ��� �θ�.
		eventButtonStartStopHandle();
		
		// 2. ���̺�� ����
		initTableView();
		
		// 3. ���̺�信 ���õ� ȸ������ �޼��� ���� 
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
			if (btnStartStop.getText().equals("����")) {
				startServer();
			} else if (btnStartStop.getText().equals("����")) {
				stopServer();
			}
		});
	}

	// 2. ���̺�� ����
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
			} // ���� for
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
			textArea.appendText("������ �����Ǿ����ϴ�."+"\n");
			btnStartStop.setText("����");
		});

	}
	
	// make ServerSocket -> bind ->[Thread�� ����(3�� ���) -> ȭ������۾�
	// ����(Platform.runlater())-> wait
	// -> connect -> make client Socket -> Ŭ���̾�ƮŬ���� ��ü ���� ->to
	// collectionFrameWork(Vector) :
	// ������Ϸ�]
	// �������� Thread�� �۵�
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
			// �Ϲ� �����忡�� �ڹ� FX application Thread���� �ؾ��� ���� (UI) �����Ϸ��� Platform.runLater()��
			// �ҷ��ش�.
			// JavaFX application Thread�� �ϵ��� ������ش�.
			Platform.runLater(() -> {
				textArea.appendText("[���� ����] " + Thread.currentThread().getName() + "\n");
				btnStartStop.setText("����");
			});
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					
					Platform.runLater(() -> {
						textArea.appendText("[���� ���� : " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName() + "]" + "\n");// ������ IP ���
						
					});
					
//					count++;
//					String name ="user" + count;
					Client client = new Client(socket);
					obclntList.add(client);
					Platform.runLater(()->{
						
						tableView.setItems(obclntList);					
					});
					Platform.runLater(() -> {
						// ������ IP ���
						textArea.appendText("[���� ����]" + obclntList.size()+"\n");
					});
				} catch (IOException e) {
					if (!serverSocket.isClosed()) {
						stopServer();
						break;
					}
				}
			} // end of while
		};// end of runnable
		mainThread = new Thread(runnable);// Thread-4��
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
			// ���� ���� ���� �����͸� �� �ѷ��ش�. �������� ���� inputStream �޾Ƽ�, read��ٸ���
			// -> ��� client���� �޼����� ���� outputStream
			receive();
		}
	
		
		// �޼��� �� ���� ��� ��ٷ����Ѵ�.
		public void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					// error�߻��� while�� �������Ѵ�.
					try {
						while (true) {
							BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
							String receiveMessage = br.readLine(); // ��� ����
							if (receiveMessage == null) {
								throw new IOException();
							} // null�� ���´� ->��� Client�� ����
							
							String rMessage = receiveMessage.substring(0, 4);
							switch(rMessage) {
							case "INFO":
								//Ŭ���̾�Ʈ�� NAME �� ID ������
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
									textArea.appendText("['" +name+ "'���� ��]"+string2 + "\n");
								});
								break;
							default:
								textArea.appendText("Warning!!!!! ������ �ֽ��ϴ�.");
								break;
							}
							
							/*Platform.runLater(() -> {
								textArea.appendText("[�޼��� ����]" +name+ receiveMessage + "\n");
							});		*/				
						}
					} catch (IOException e) {
						// ������ ���ְ� null�߻� ��(Ŭ���̾�Ʈ ������ �״� ���) -> �÷��������ӿ�ũ���� �ش� ��ü�� �����.
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
							textArea.appendText("[��� ����]" + socket.getRemoteSocketAddress() + "\n");
						});
						obclntList.remove(Client.this);// �׳� this�� �ӽð�ü�� ����Ű�� ������ Client.this ���
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