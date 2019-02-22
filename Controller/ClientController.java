package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Student;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ClientController implements Initializable {
	public Stage clientStage;
	@FXML
	private TextArea textArea;
	@FXML
	private TextField textField;
	@FXML
	private Button btnStartStop;
	@FXML
	private Button btnSend;
	@FXML
	private Label name;
	private Socket socket;
	private Student student;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 0. NAME SET
		Student stu = StudentDAO.searchStudentByStuID(RootController.studentID);
		name.setText(stu.getSName());
		// 1. ����������ư
		btnStartStop.setOnAction(event -> {
			if (btnStartStop.getText().equals("����")) {
				// 1.2[SOCKET]���� ����
				startClient();
			} else {
				// 1.1[SOCKET]���� ����
				stopClient();
			}
		});
		// 2.[SEND] �޼��� ���� (ENTER)
		textField.setOnKeyPressed((KeyEvent event) -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				send(textField.getText());
				textField.clear();
			}
		});
		// 2.[SEND] �޼��� ����
		btnSend.setOnAction(event -> {
			send(textField.getText());
		});

	}
	// 1.1[SOCKET]���� ����
	private void stopClient() {
		if (!socket.isClosed() && socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
		Platform.runLater(() -> {
			textArea.appendText("���� ���� \n");
		});
		btnStartStop.setText("����");
		btnSend.setDisable(true);
		textField.setDisable(true);
	}
	// 1.2[SOCKET]���� ����
	private void startClient() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {

				socket = new Socket();
				try {
					socket.connect(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 5004));
					Platform.runLater(() -> {
						textArea.appendText("[����Ϸ�!]" + socket.getRemoteSocketAddress() + "\n");
						btnStartStop.setText("����");
						btnSend.setDisable(false);
						textField.setDisable(false);
					});
				} catch (IOException e) {
					if (!socket.isClosed()) {
						stopClient();
					}
					return;
				}
				try {
					PrintWriter pw = new PrintWriter(socket.getOutputStream());
					pw.println("INFO" + student.getSName() + ":" + student.getStudentID());
					pw.flush();
					Platform.runLater(() -> {
						textArea.appendText("[���ۿϷ�] \n");
					});
				} catch (IOException e) {
					Platform.runLater(() -> {
						textArea.appendText("[���ۿ���] ������ ���� �ʾҽ��ϴ�. \n");
					});
					stopClient();
				}

				while (true) {
					try {
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String receiveMessage = br.readLine();
						Platform.runLater(() -> {
							textArea.appendText("[���� �޼���] " + receiveMessage + "\n");
						});
					} catch (IOException e) {
						Platform.runLater(() -> {
							textArea.appendText("���ۿ��� ������ ����� �Ұ��մϴ�. \n");
						});
						stopClient();
						break;
					}

				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
	// 2.[SEND] �޼��� ����
	private void send(String sendMessage) {
		Runnable runnable = () -> {
			try {
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println("SEND " + sendMessage);
				pw.flush();
				Platform.runLater(() -> {
					textArea.appendText("[���� ���� �޼���] " + sendMessage + "\n");
				});
			} catch (IOException e) {
				Platform.runLater(() -> {
					textArea.appendText("[���ۿ���] ������ ���� �ʾҽ��ϴ�. \n");
				});
				stopClient();
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
