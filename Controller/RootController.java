package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ResourceBundle;

import Model.Student;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	public Stage primaryStage;
	public static String studentID;
	@FXML
	ImageView logImg;
	@FXML
	TextField logId;
	@FXML
	TextField logLogin;
	@FXML
	TextField logJoin;
	@FXML
	TextField logFgId;
	@FXML
	TextField logFgPw;
	@FXML 
	PasswordField logPw;
	@FXML
	Label logTeacher;
	@FXML
	Label logAdmin;
	private Student student;
	private File selectFile = null;
	private String fileName = "";
	private File imageDir = new File("C:/images");

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 0.[Testing] LOGIN
		logImg.setOnMousePressed(e -> debugLoginImg());
		logId.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) { handleLoginAction();}});
		logPw.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) {handleLoginAction();}});
		// 1.[JOIN] ȸ��->����
		logJoin.setOnMouseClicked(e -> handleLogJoinAction());
		// 2.[CHANGE] TEACHER LOGIN
		logTeacher.setOnMouseClicked(e -> handleTchLoginAction());
		// 3.[CHANGE] ADMINISTER LOGIN
		logAdmin.setOnMouseClicked(e -> handleAdminLoginAction());
		// 4.[CHANGE] Login -> Student mode
		logLogin.setOnMouseClicked(e -> handleLoginAction());
		// 5.[FORGOT] ID
		logFgId.setOnMouseClicked(e -> handleLoginFgIdAction());
		// 6.[FORGOT] PW
		logFgPw.setOnMouseClicked(e -> handleLoginFgPwAction());

	}

	// 0.[Testing]
	private void debugLoginImg() {
		logId.setText("kmj@nate.com");
		logPw.setText("222222");
	}
	
	// 1.[JOIN] ȸ��->����
	private void handleLogJoinAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/join.fxml"));
			Parent root = loader.load();
			ObservableMap<String, Object> fxmlNameSpace = loader.getNamespace();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			Stage joinStage = new Stage(StageStyle.UTILITY);

			// **********************join.fxml id ���****************************
			TextField joinName = (TextField) root.lookup("#joinName");
			TextField joinId = (TextField) root.lookup("#joinId");
			TextField joinPhone = (TextField) root.lookup("#joinPhone");
			TextField joinYear = (TextField) root.lookup("#joinYear");
			TextField joinMonth = (TextField) root.lookup("#joinMonth");
			TextField joinDay = (TextField) root.lookup("#joinDay");
			TextField joinRegister = (TextField) root.lookup("#joinRegister");
			TextField joinCancel = (TextField) root.lookup("#joinCancel");
			RadioButton joinMale = (RadioButton) root.lookup("#joinMale");
			RadioButton joinFemale = (RadioButton) root.lookup("#joinFemale");
			ImageView joinImg = (ImageView) root.lookup("#joinImg");
			ImageView joinTesting = (ImageView) root.lookup("#joinTesting");
			PasswordField joinPW = (PasswordField) root.lookup("#joinPW");
			PasswordField joinCheckPW = (PasswordField) root.lookup("#joinCheckPW");
			ToggleGroup group = (ToggleGroup) fxmlNameSpace.get("group");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
			inputDecimalFormatFourDigit(joinYear);
			inputDecimalFormatTwoDigit(joinMonth);
			inputDecimalFormatTwoDigit(joinDay);
			inputDecimalFormatElevenDigit(joinPhone);
			inputDecimalFormatSixDigit(joinPW);
			inputDecimalFormatSixDigit(joinCheckPW);
			joinRegister.setEditable(false);
			joinCancel.setEditable(false);
		
			// 1.0.image chooser
			joinImg.setOnMouseClicked(e -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(primaryStage);
				String localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
					}
				}
				joinImg.setImage(new Image(localURL, false)); 
				// ����ȭ������ �̹����� ������� �ʰڴٴ� �ǹ�
				// ���õ� ���ϸ�
				fileName = selectFile.getName();

			});

			// 1.1.���� ��ư -> ���� �Ϸ�
			joinRegister.setOnMouseClicked(e -> {
				AdminController.spaceCheck(joinName.getText().toString());
				AdminController.spaceCheck(joinPW.getText().toString());
				AdminController.spaceCheck(joinCheckPW.getText().toString());
				AdminController.spaceCheck(joinId.getText().toString());
				AdminController.spaceCheck(joinPhone.getText().toString());
				
				int count = StudentDAO.checkStudentId(joinId.getText().toString());
					
				if (joinId.getText().contains("@") == false) {
					AdminController.callAlert("[ID��Ŀ���] : �̸��� ������ �����ּ���. ");
				
				} else if(count != 0) {
						AdminController.callAlert("[�ߺ�����] : �����ϴ� �̸��Ͼ��̵��Դϴ�.");
				} else {
					// ��Ÿ : �̹��� c:/images/filename.jpg�� ����
					imageSave();
					if (selectFile == null) {
						AdminController.callAlert("[�̹��� ���� ����]: �̹����������ּ���");
						return;
					}

					String birthDate = joinYear.getText().trim() + "-" + joinMonth.getText().trim() + "-"
							+ joinDay.getText().trim();
					String gender = "";
					if (joinMale.isSelected()) {
						gender = joinMale.getText();
					} else if (joinFemale.isSelected()) {
						gender = joinFemale.getText();
					}
					System.out.println(gender);
					student = new Student(joinId.getText(), joinPW.getText(), joinName.getText(), joinPhone.getText(),
							gender, birthDate, fileName);
					int count2 = StudentDAO.insertStudent(student);
					System.out.println(count2+"count2");
					if (count2 != 0) {
						AdminController.callAlert("[ȸ������ ����] : ȸ�������� �Ϸ�Ǿ����ϴ�. \n �α��� ȭ������ �̵��մϴ�. ");
						joinStage.close();
					}
				}
			});
			
			

			// 1.2.��ҹ�ư
			joinCancel.setOnMouseClicked(e -> {
				joinStage.close();
			});

			joinStage.initOwner(primaryStage);
			joinStage.initModality(Modality.WINDOW_MODAL);
			joinStage.setTitle("ȸ������");
			joinStage.setScene(scene);
			joinStage.show();
		} catch (IOException e) {
			AdminController.callAlert("ȸ������ â ���� : ȸ������â �����߻�!! Ȯ�����ּ���");
		}
	}

	// 2.[CHANGE] TEACHER LOGIN
	private void handleTchLoginAction() {

		try {
			Stage tchLoginStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login_tch.fxml"));
			Parent root = loader.load();
			TchLoginController tchLoginController = loader.getController();
			tchLoginController.tchLoginStage = tchLoginStage;
			tchLoginStage.setTitle("Teacher Login");
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			tchLoginStage.setScene(scene);
			primaryStage.close();
			tchLoginStage.show();
			//AdminController.callAlert(" ���� : ȭ�� ��ȯ�� ���� ");
		} catch (Exception e) {
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}
	}

	// 3.[CHANGE] ADMINISTER LOGIN
	private void handleAdminLoginAction() {
		try {
			Stage admLoginStage = new Stage();
			admLoginStage.setResizable(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login_ad.fxml"));
			Parent root = loader.load();
			admLoginStage.setTitle("Administer Login");
			AdmLoginController admLoginController = loader.getController();
			admLoginController.admLoginStage = admLoginStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			admLoginStage.setScene(scene);
			primaryStage.close();
			admLoginStage.show();
			//AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� ");
		} catch (Exception e) {
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}

	}

	// 4.[CHANGE] Login -> Student mode
	private void handleLoginAction() {
		String id = logId.getText();
		studentID = logId.getText();
		System.out.println(studentID);
		String sPW = logPw.getText();
		int existID = StudentDAO.checkStudentId(id);
		int existPW = 0;
		if (existID == 0) {
			AdminController.callAlert("[LOGIN ����]: �������� �ʴ� ���̵� �Դϴ�.");
			return;
		} else {
			existPW = StudentDAO.checkPW(id, sPW);
		}
		if (existPW == 0) {
			return;
		} 

		try {
			Stage stuStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/main_std2.fxml"));
			Parent root = loader.load();
			StudentController stuController = loader.getController();
			stuController.stuStage = stuStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/stu.css").toString());
			stuStage.setScene(scene);
			primaryStage.close();
			stuStage.show();
			//AdminController.callAlert("[����] : ȭ�� ��ȯ�� ���� ");
		} catch (Exception e) {
			AdminController.callAlert("[ȭ�� ��ȯ ����] : ȭ�� ��ȯ�� ���� �߻�");
		}
	}

	// 5.[FORGOT] ID
	private void handleLoginFgIdAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/forgot_id.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage forgotIdStage = new Stage(StageStyle.UTILITY);

			// **********************forgot_id.fxml id ���****************************
			TextField name = (TextField) root.lookup("#name");
			TextField phone = (TextField) root.lookup("#phone");
			TextField dateOfY = (TextField) root.lookup("#dateOfY");
			TextField dateOfM = (TextField) root.lookup("#dateOfM");
			TextField dateOfD = (TextField) root.lookup("#dateOfD");
			TextField btnSearch = (TextField) root.lookup("#btnSearch");
			TextField btnExit = (TextField) root.lookup("#btnExit");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************

			// 1.1.�˻� ��ư -> ���̵�ã�� 
			btnSearch.setOnMouseClicked(e -> {
				AdminController.spaceCheck(phone.getText());	
				inputDecimalFormatFourDigit(dateOfY);
				inputDecimalFormatTwoDigit(dateOfM);
				inputDecimalFormatTwoDigit(dateOfD);
				inputDecimalFormatElevenDigit(phone);

				String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfD.getText();
				
				String existID = StudentDAO.findIDByPhone(phone.getText(),name.getText(),date);
				System.out.println("&&&&&"+existID);
				int existPW = 0;
				if (existID == null) {
					AdminController.callAlert("[ID ã�� ����]: �������� �ʴ� ȸ������ �Դϴ�.");
					return;
				}else{
					AdminController.callAlert("[ID ã�� ����]:  ȸ������ ���̵�� "+ existID+" �Դϴ�.");
					forgotIdStage.close();
				}
			});

			// 1.2.��ҹ�ư
			btnExit.setOnMouseClicked(e -> {
				forgotIdStage.close();
			});

		
			forgotIdStage.initOwner(primaryStage);
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("���̵�ã��");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("[FORGOT ID] ���� : ���̵�ã�� �����߻�!! Ȯ�����ּ���");
		}

	}

	// 6.[FORGOT] PW
	private void handleLoginFgPwAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/forgot_password.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage forgotIdStage = new Stage(StageStyle.UTILITY);

			// **********************forgot_pw.fxml id ���****************************
			TextField name = (TextField) root.lookup("#name");
			TextField phone = (TextField) root.lookup("#phone");
			TextField dateOfY = (TextField) root.lookup("#dateOfY");
			TextField dateOfM = (TextField) root.lookup("#dateOfM");
			TextField dateOfD = (TextField) root.lookup("#dateOfD");
			TextField emailAddress = (TextField) root.lookup("#emailAddress");
			TextField btnSearch = (TextField) root.lookup("#btnSearch");
			TextField btnExit = (TextField) root.lookup("#btnExit");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
			inputDecimalFormatFourDigit(dateOfY);
			inputDecimalFormatTwoDigit(dateOfM);
			inputDecimalFormatTwoDigit(dateOfD);
			inputDecimalFormatElevenDigit(phone);

			// 1.1.�˻� ��ư -> ��й�ȣ ã�� �Ϸ�
			btnSearch.setOnMouseClicked(e -> {
				if (emailAddress.getText().contains("@") == false) {
					AdminController.callAlert("[ID��� ����] : �̸��� ������ �����ּ���. ");
				} else {
					int count=StudentDAO.checkStudentId(emailAddress.getText());
					if(count == 0) {
						AdminController.callAlert("[ID ����] :�������� �ʴ� �̸��Ͼ��̵��Դϴ�.");
					}else {
						AdminController.spaceCheck(phone.getText());	
					
						String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfD.getText();
						System.out.println(date);
						
						String existPW = StudentDAO.findPWByPhone(name.getText(),phone.getText(),date,emailAddress.getText());
						if (existPW == null) {
							AdminController.callAlert("[PW ã�� ����]: �������� �ʴ� ȸ������ �Դϴ�.");
							return;
						}else{
							AdminController.callAlert("[ID ã�� ����]: ȸ������ �н������ "+ existPW+" �Դϴ�.");
							forgotIdStage.close();
						}
						
						
					}	
					
					
				}
				
			});

			// 1.2.��ҹ�ư
			btnExit.setOnMouseClicked(e -> {
				forgotIdStage.close();
			});

			inputDecimalFormatFourDigit(dateOfY);
			inputDecimalFormatTwoDigit(dateOfM);
			inputDecimalFormatTwoDigit(dateOfD);
			forgotIdStage.initOwner(primaryStage);
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("��й�ȣã��");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("��й�ȣ â ���� : ��й�ȣ �����߻�!! Ȯ�����ּ���");
		}

	}

	// ��Ÿ :�ؽ�Ʈ�ʵ� �Է°� ���˼������(2�ڸ� ���ڸ� ����)
	public static void inputDecimalFormatTwoDigit(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("##");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������. ���ڸ����� ����ؼ� �����ϰڴ�.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�,�Է��ѱ��̿� �����м���ġ���� ���������(�� �м������������� ����)�ų�,�Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ�����
			// ����.) �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event; // ���� �����ְڴ�.
			}
		}));
	}

	// ��Ÿ :�ؽ�Ʈ�ʵ� �Է°� ���˼������(4�ڸ� ���ڸ� ����)
	public static void inputDecimalFormatFourDigit(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("####");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������. ���ڸ����� ����ؼ� �����ϰڴ�.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�,�Է��ѱ��̿� �����м���ġ���� ���������(�� �м������������� ����)�ų�,�Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ�����
			// ����.) �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event; // ���� �����ְڴ�.
			}
		}));
	}

	// ��Ÿ :�ؽ�Ʈ�ʵ� �Է°� ���˼������(6�ڸ� ���ڸ� ����)
	public static void inputDecimalFormatSixDigit(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("######");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������. ���ڸ����� ����ؼ� �����ϰڴ�.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�,�Է��ѱ��̿� �����м���ġ���� ���������(�� �м������������� ����)�ų�,�Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ�����
			// ����.) �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 7) {
				return null;
			} else {
				return event; // ���� �����ְڴ�.
			}
		}));
	}

	// ��Ÿ :�ؽ�Ʈ�ʵ� �Է°� ���˼������(11�ڸ� ���ڸ� ����)                                            
	public static void inputDecimalFormatElevenDigit(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###########");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������. ���ڸ����� ����ؼ� �����ϰڴ�.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�,�Է��ѱ��̿� �����м���ġ���� ���������(�� �м������������� ����)�ų�,�Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ�����
			// ����.) �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 12) {
				return null;
			} else {
				return event; // ���� �����ְڴ�.
			}
		}));
	}

	// ��Ÿ : �̹��� c:/images/filename.jpg�� ����
	private void imageSave() {

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		if (!imageDir.exists()) {
			imageDir.mkdir(); // if there is no directory -> Create Directory
		}
		// ���õ� �̹����� c:/images/"�ش� �̹��� �̸�" ���� ����
		try {
			// c:/images/ddd.jpg
			// selectFile.getName() -> ddd.jpg ���� �����´�.
			// ���ο� ���ϸ��� ���� -> student12345678912_ddd.jpg�� ������ ������� (�ߺ�����)
			// c:/images/ddd.jpg������ �о c:/images/student12345678912_ddd.jpg �� ����
			fis = new FileInputStream(selectFile);
			bis = new BufferedInputStream(fis);

			fileName = "stu" + System.currentTimeMillis() + "_" + selectFile.getName();

			fos = new FileOutputStream(imageDir.getAbsolutePath() + "\\" + fileName);
			bos = new BufferedOutputStream(fos);

			int data = -1;
			while ((data = bis.read()) != -1) {
				// ���� �о data���ٰ� ����. -> ���� ����ڰ� �� ������ ���� ���ϴ� ��ġ�� ����
				bos.write(data);
				bos.flush();
			}

		} catch (Exception e) {
			AdminController.callAlert("�̹��� ���� ���� : c\\/images/ �������� ���� ���� �ٶ�");
		} finally {
			try {

				if (fis != null) {
					fis.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (bos != null) {
					bos.close();
				}

			} catch (IOException e) {

			}
		}
	}

	// ��Ÿ : �̹��� Delete
	private void imageDelete() {
		// delete image
		File imageFile = new File(imageDir.getAbsolutePath() + "\\" + student.getImage());

		boolean delFlag = false;
		if (imageFile.exists() && imageFile.isFile()) {
			delFlag = imageFile.delete();
			if (delFlag == false) {
				AdminController.callAlert("�̹��� ���� ���� : �̹��� ���� Ȯ�� �ٶ�.");
			}
		}
	}

}
