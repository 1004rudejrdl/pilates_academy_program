package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Student;
import Model.Teacher;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TchLoginController implements Initializable {
	public Stage tchLoginStage;
	@FXML
	ImageView tLogImg;
	@FXML
	TextField tLogId;
	@FXML
	TextField tLogLogin;
	@FXML
	TextField tLogFGID;
	@FXML
	TextField tLogFGPW;
	@FXML
	TextField tLogJoin;
	@FXML
	PasswordField tLogPassword;
	@FXML
	Label tLogBack;
	private File selectFile = null;
	private String fileName = "";
	// private File imageDir = new File("../images");
	private final File DIR = new File("C:/images");
	private Teacher teacher;
	private String localURL;
	static public String teacherID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 0.For Testing
		// [Login]
		// 1. [EXECUTE] Teacher Mode
		tLogLogin.setOnMouseClicked(e -> handleLoginAction());
		tLogImg.setOnMouseClicked(e -> debugLoginImg());
		tLogId.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				handleLoginAction();
			}
		});
		tLogPassword.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				handleLoginAction();
			}
		});
		// 2. [JOIN] ȸ��->����
		tLogJoin.setOnMouseClicked(e -> handleLogJoinAction());
		// [BACK] �ڷΰ��� ����
		tLogBack.setOnMouseClicked(e -> handleBtnBack());
		tLogFGID.setOnMouseClicked(e -> handleLoginFgIdAction());
		tLogFGPW.setOnMouseClicked(e -> handleLoginFgPwAction());
	}

	// 0.For Testing
	private void debugLoginImg() {
		tLogId.setText("kimdj@nate.com");
		tLogPassword.setText("111116");
	}

	// 1.[EXECUTE] TEACHER ��� ����
	private void handleLoginAction() {
		String tchID = tLogId.getText();
		teacherID = tLogId.getText();
		String tPW = tLogPassword.getText();
		int existID = TeacherDAO.checkTeacherId(tchID);
		int existPW = 0;
		if (existID == 0) {
			AdminController.callAlert("[LOGIN ����]: �������� �ʴ� ���̵� �Դϴ�.");
			return;
		} else {
			existPW = TeacherDAO.checkPW(tchID, tPW);
		}
		if (existPW == 0) {
			return;
		} else {
			existPW = TeacherDAO.checkPW(tchID, tPW);
		}

		try {
			Stage tchStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/main_t.fxml"));
			Parent root = loader.load();
			TeacherController tchController = loader.getController();
			tchController.tchStage = tchStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/admin.css").toString());
			tchStage.setScene(scene);
			tchLoginStage.close();
			tchStage.show();

//			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� ");
		} catch (Exception e) {
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}
	}

	// 2.[JOIN] ȸ��->����
	private void handleLogJoinAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/join.fxml"));
			Parent root = loader.load();
			ObservableMap<String, Object> fxmlNameSpace = loader.getNamespace();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			Stage joinStage = new Stage(StageStyle.UTILITY);
			joinStage.setTitle("���� ȸ������");
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
			RootController.inputDecimalFormatFourDigit(joinYear);
			RootController.inputDecimalFormatTwoDigit(joinMonth);
			RootController.inputDecimalFormatTwoDigit(joinDay);
			RootController.inputDecimalFormatElevenDigit(joinPhone);
			RootController.inputDecimalFormatSixDigit(joinPW);
			RootController.inputDecimalFormatSixDigit(joinCheckPW);

			// 1.0.testing
			joinTesting.setOnMouseClicked(e -> {
				joinName.setText("��ü��");
				joinId.setText("00cherry@cherish.com");
				joinPhone.setText("01055885555");
				joinYear.setText("2007");
				joinMonth.setText("09");
				joinDay.setText("21");
				joinFemale.setSelected(true);
				joinImg.setImage(new Image(getClass().getResource("../images/pila.png").toString()));
				joinPW.setText("5555");
				joinCheckPW.setText("5555");
			});

			// 1.0.image chooser
			joinImg.setOnMouseClicked(e -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(tchLoginStage);
				localURL = null;
				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						AdminController.callAlert("[Error] : �̹��� ���ÿ� ������ �߻��߽��ϴ�.");
						e1.printStackTrace();
					}

				}

				joinImg.setImage(new Image(localURL, false)); // ����ȭ������ �̹����� ������� �ʰڴٴ� �ǹ�
//				joinImg.setImage(new Image(getClass().getResource(imageDir.getPath()+"\\"+localURL).toString(), false)); // ����ȭ������ �̹����� ������� �ʰڴٴ� �ǹ�
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
				int count = TeacherDAO.checkTeacherId(joinId.getText().toString());

				if (joinId.getText().contains("@") == false) {
					AdminController.callAlert("[ID��Ŀ���] : �̸��� ������ �����ּ���. ");

				} else if (count != 0) {
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

					teacher = new Teacher(joinId.getText(), joinPW.getText(), joinName.getText(), joinPhone.getText(),
							gender, birthDate, fileName);
					int count2 = TeacherDAO.insertTeacher(teacher);
					if (count2 != 0) {
				AdminController.callAlert("[���� ����] : �Է¼����߾��");
					}
				}
			});

			// 1.2.��ҹ�ư
			joinCancel.setOnMouseClicked(e -> {
				joinStage.close();
			});

			joinStage.initOwner(tchLoginStage);
			joinStage.initModality(Modality.WINDOW_MODAL);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			joinStage.setTitle("ȸ������");
			joinStage.setScene(scene);
			joinStage.show();
		} catch (IOException e) {
			AdminController.callAlert("ȸ������ â ���� : ȸ������â �����߻�!! Ȯ�����ּ���");
		}
	}


	// 5.[FORGOT]
	private void handleLoginFgIdAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/forgot_id.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage forgotIdStage = new Stage(StageStyle.UTILITY);

			// **********************forgot_id.fxml id ���****************************
			TextField name		 	= (TextField) root.lookup("#name");
			TextField phone 		= (TextField) root.lookup("#phone");
			TextField dateOfY 		= (TextField) root.lookup("#dateOfY");
			TextField dateOfM		= (TextField) root.lookup("#dateOfM");
			TextField dateOfD 		= (TextField) root.lookup("#dateOfD");
			TextField btnSearch 	= (TextField) root.lookup("#btnSearch");
			TextField btnExit 		= (TextField) root.lookup("#btnExit");
			ImageView testing 		= (ImageView) root.lookup("#testing");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************

			RootController.inputDecimalFormatFourDigit(dateOfY);
			RootController.inputDecimalFormatTwoDigit(dateOfM);
			RootController.inputDecimalFormatTwoDigit(dateOfD);
			RootController.inputDecimalFormatElevenDigit(phone);
			
			
			testing.setOnMouseClicked(e ->{
				name.setText("õ����");
				phone.setText("01054441000");
				 dateOfY.setText("1978"); 	
				 dateOfM.setText("09");	
				 dateOfD.setText("09"); 	
				
			});
			// 1.1.�˻� ��ư -> ���̵�ã�� 
			btnSearch.setOnMouseClicked(e -> {
				AdminController.spaceCheck(phone.getText());	
				String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfM.getText();
				
				String existID = TeacherDAO.findIDByPhone(phone.getText(), name.getText(), date);
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

		
			forgotIdStage.initOwner(tchLoginStage);
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("���̵�ã��");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("[FORGOT ID] ���� : ���̵�ã�� �����߻�!! Ȯ�����ּ���");
		}

	}

	// 6.[FORGOT]
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
			AdminController.spaceCheck(phone.getText());	
			RootController.inputDecimalFormatFourDigit(dateOfY);
			RootController.inputDecimalFormatTwoDigit(dateOfM);
			RootController.inputDecimalFormatTwoDigit(dateOfD);
			RootController.inputDecimalFormatElevenDigit(phone);

			// 1.1.�˻� ��ư -> ��й�ȣ ã�� �Ϸ�
			btnSearch.setOnMouseClicked(e -> {
				if (emailAddress.getText().contains("@") == false) {
					AdminController.callAlert("[ID��� ����] : �̸��� ������ �����ּ���. ");
				} else {
					System.out.println(emailAddress.getText());
					int count=TeacherDAO.checkTeacherId(emailAddress.getText());
					if(count == 0) {
						AdminController.callAlert("[ID ����] :�������� �ʴ� �̸��Ͼ��̵��Դϴ�.");
						
					}else {
						AdminController.spaceCheck(phone.getText());						
						String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfD.getText();
						System.out.println(date);
						String existPW = TeacherDAO.findPWByPhone(phone.getText(),name.getText(),date,emailAddress.getText());
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

		
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("��й�ȣã��");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("��й�ȣ â ���� : ��й�ȣ �����߻�!! Ȯ�����ּ���");
		}

	}

	
	// �ڷ� ���� ����
	private void handleBtnBack() {

		try {
			Stage stuLoginStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/log.fxml"));
			Parent root = loader.load();
			RootController stuLoginController = loader.getController();
			stuLoginController.primaryStage = stuLoginStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			stuLoginStage.setScene(scene);
			tchLoginStage.close();
			stuLoginStage.show();
		//	AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� ");
		} catch (Exception e) {
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}

	}

	// ��Ÿ : �̹��� c:/images/filename.jpg�� ����
	private void imageSave() {

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		if (!DIR.exists()) {
			DIR.mkdir(); // if there is no directory -> Create Directory
		}
		// ���õ� �̹����� c:/images/"�ش� �̹��� �̸�" ���� ����
		try {
			// c:/images/ddd.jpg
			// selectFile.getName() -> ddd.jpg ���� �����´�.
			// ���ο� ���ϸ��� ���� -> student12345678912_ddd.jpg�� ������ ������� (�ߺ�����)
			// c:/images/ddd.jpg������ �о c:/images/student12345678912_ddd.jpg �� ����
			fis = new FileInputStream(selectFile);
			bis = new BufferedInputStream(fis);

			fileName = "tch" + System.currentTimeMillis() + "_" + selectFile.getName();

			// ����� ���� �Ұ�
			fos = new FileOutputStream(DIR.getAbsolutePath() + "\\" + fileName);

			bos = new BufferedOutputStream(fos);

			int data = -1;
			while ((data = bis.read()) != -1) {
				// ���� �о data���ٰ� ����. -> ���� ����ڰ� �� ������ ���� ���ϴ� ��ġ�� ����
				bos.write(data);
				bos.flush();
			}

		} catch (Exception e) {
			AdminController.callAlert("�̹��� ���� ���� : //images/ �������� ���� ���� �ٶ�");
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
		File imageFile = new File(DIR.getAbsolutePath() + "\\" + teacher.getImage());

		boolean delFlag = false;
		if (imageFile.exists() && imageFile.isFile()) {
			delFlag = imageFile.delete();
			if (delFlag == false) {
				AdminController.callAlert("�̹��� ���� ���� : �̹��� ���� Ȯ�� �ٶ�.");
			}
		}
	}

}