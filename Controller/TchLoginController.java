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
		// 2. [JOIN] 회원->가입
		tLogJoin.setOnMouseClicked(e -> handleLogJoinAction());
		// [BACK] 뒤로가기 실행
		tLogBack.setOnMouseClicked(e -> handleBtnBack());
		tLogFGID.setOnMouseClicked(e -> handleLoginFgIdAction());
		tLogFGPW.setOnMouseClicked(e -> handleLoginFgPwAction());
	}

	// 0.For Testing
	private void debugLoginImg() {
		tLogId.setText("kimdj@nate.com");
		tLogPassword.setText("111116");
	}

	// 1.[EXECUTE] TEACHER 모드 실행
	private void handleLoginAction() {
		String tchID = tLogId.getText();
		teacherID = tLogId.getText();
		String tPW = tLogPassword.getText();
		int existID = TeacherDAO.checkTeacherId(tchID);
		int existPW = 0;
		if (existID == 0) {
			AdminController.callAlert("[LOGIN 실패]: 존재하지 않는 아이디 입니다.");
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

//			AdminController.callAlert("화면 전환 성공 : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("화면 전환 오류 : 화면 전환에 오류 발생");
		}
	}

	// 2.[JOIN] 회원->가입
	private void handleLogJoinAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/join.fxml"));
			Parent root = loader.load();
			ObservableMap<String, Object> fxmlNameSpace = loader.getNamespace();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			Stage joinStage = new Stage(StageStyle.UTILITY);
			joinStage.setTitle("강사 회원가입");
			// **********************join.fxml id 등록****************************
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

			// **********************이벤트 등록 및 초기화와 핸들러정의****************************
			RootController.inputDecimalFormatFourDigit(joinYear);
			RootController.inputDecimalFormatTwoDigit(joinMonth);
			RootController.inputDecimalFormatTwoDigit(joinDay);
			RootController.inputDecimalFormatElevenDigit(joinPhone);
			RootController.inputDecimalFormatSixDigit(joinPW);
			RootController.inputDecimalFormatSixDigit(joinCheckPW);

			// 1.0.testing
			joinTesting.setOnMouseClicked(e -> {
				joinName.setText("김체리");
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
						AdminController.callAlert("[Error] : 이미지 선택에 오류가 발생했습니다.");
						e1.printStackTrace();
					}

				}

				joinImg.setImage(new Image(localURL, false)); // 바탕화면으로 이미지를 사용하지 않겠다는 의미
//				joinImg.setImage(new Image(getClass().getResource(imageDir.getPath()+"\\"+localURL).toString(), false)); // 바탕화면으로 이미지를 사용하지 않겠다는 의미
				// 선택된 파일명
				fileName = selectFile.getName();

			});

		

			// 1.1.가입 버튼 -> 가입 완료
			joinRegister.setOnMouseClicked(e -> {
				AdminController.spaceCheck(joinName.getText().toString());
				AdminController.spaceCheck(joinPW.getText().toString());
				AdminController.spaceCheck(joinCheckPW.getText().toString());
				AdminController.spaceCheck(joinId.getText().toString());
				AdminController.spaceCheck(joinPhone.getText().toString());
				int count = TeacherDAO.checkTeacherId(joinId.getText().toString());

				if (joinId.getText().contains("@") == false) {
					AdminController.callAlert("[ID양식오류] : 이메일 형식을 지켜주세요. ");

				} else if (count != 0) {
					AdminController.callAlert("[중복오류] : 존재하는 이메일아이디입니다.");
				} else {
					// 기타 : 이미지 c:/images/filename.jpg로 저장
					imageSave();
					if (selectFile == null) {
						AdminController.callAlert("[이미지 선택 오류]: 이미지선택해주세요");
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
				AdminController.callAlert("[가입 성공] : 입력성공했어요");
					}
				}
			});

			// 1.2.취소버튼
			joinCancel.setOnMouseClicked(e -> {
				joinStage.close();
			});

			joinStage.initOwner(tchLoginStage);
			joinStage.initModality(Modality.WINDOW_MODAL);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			joinStage.setTitle("회원가입");
			joinStage.setScene(scene);
			joinStage.show();
		} catch (IOException e) {
			AdminController.callAlert("회원가입 창 오류 : 회원가입창 오류발생!! 확인해주세요");
		}
	}


	// 5.[FORGOT]
	private void handleLoginFgIdAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/forgot_id.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage forgotIdStage = new Stage(StageStyle.UTILITY);

			// **********************forgot_id.fxml id 등록****************************
			TextField name		 	= (TextField) root.lookup("#name");
			TextField phone 		= (TextField) root.lookup("#phone");
			TextField dateOfY 		= (TextField) root.lookup("#dateOfY");
			TextField dateOfM		= (TextField) root.lookup("#dateOfM");
			TextField dateOfD 		= (TextField) root.lookup("#dateOfD");
			TextField btnSearch 	= (TextField) root.lookup("#btnSearch");
			TextField btnExit 		= (TextField) root.lookup("#btnExit");
			ImageView testing 		= (ImageView) root.lookup("#testing");

			// **********************이벤트 등록 및 초기화와 핸들러정의****************************

			RootController.inputDecimalFormatFourDigit(dateOfY);
			RootController.inputDecimalFormatTwoDigit(dateOfM);
			RootController.inputDecimalFormatTwoDigit(dateOfD);
			RootController.inputDecimalFormatElevenDigit(phone);
			
			
			testing.setOnMouseClicked(e ->{
				name.setText("천송이");
				phone.setText("01054441000");
				 dateOfY.setText("1978"); 	
				 dateOfM.setText("09");	
				 dateOfD.setText("09"); 	
				
			});
			// 1.1.검색 버튼 -> 아이디찾기 
			btnSearch.setOnMouseClicked(e -> {
				AdminController.spaceCheck(phone.getText());	
				String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfM.getText();
				
				String existID = TeacherDAO.findIDByPhone(phone.getText(), name.getText(), date);
				int existPW = 0;
				if (existID == null) {
					AdminController.callAlert("[ID 찾기 실패]: 존재하지 않는 회원정보 입니다.");
					return;
				}else{
					AdminController.callAlert("[ID 찾기 성공]:  회원님의 아이디는 "+ existID+" 입니다.");
					forgotIdStage.close();
				}
			});

			// 1.2.취소버튼
			btnExit.setOnMouseClicked(e -> {
				forgotIdStage.close();
			});

		
			forgotIdStage.initOwner(tchLoginStage);
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("아이디찾기");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("[FORGOT ID] 오류 : 아이디찾기 오류발생!! 확인해주세요");
		}

	}

	// 6.[FORGOT]
	private void handleLoginFgPwAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/forgot_password.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage forgotIdStage = new Stage(StageStyle.UTILITY);

			// **********************forgot_pw.fxml id 등록****************************
			TextField name = (TextField) root.lookup("#name");
			TextField phone = (TextField) root.lookup("#phone");
			TextField dateOfY = (TextField) root.lookup("#dateOfY");
			TextField dateOfM = (TextField) root.lookup("#dateOfM");
			TextField dateOfD = (TextField) root.lookup("#dateOfD");
			TextField emailAddress = (TextField) root.lookup("#emailAddress");
			TextField btnSearch = (TextField) root.lookup("#btnSearch");
			TextField btnExit = (TextField) root.lookup("#btnExit");

			// **********************이벤트 등록 및 초기화와 핸들러정의****************************
			AdminController.spaceCheck(phone.getText());	
			RootController.inputDecimalFormatFourDigit(dateOfY);
			RootController.inputDecimalFormatTwoDigit(dateOfM);
			RootController.inputDecimalFormatTwoDigit(dateOfD);
			RootController.inputDecimalFormatElevenDigit(phone);

			// 1.1.검색 버튼 -> 비밀번호 찾기 완료
			btnSearch.setOnMouseClicked(e -> {
				if (emailAddress.getText().contains("@") == false) {
					AdminController.callAlert("[ID양식 오류] : 이메일 형식을 지켜주세요. ");
				} else {
					System.out.println(emailAddress.getText());
					int count=TeacherDAO.checkTeacherId(emailAddress.getText());
					if(count == 0) {
						AdminController.callAlert("[ID 오류] :존재하지 않는 이메일아이디입니다.");
						
					}else {
						AdminController.spaceCheck(phone.getText());						
						String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfD.getText();
						System.out.println(date);
						String existPW = TeacherDAO.findPWByPhone(phone.getText(),name.getText(),date,emailAddress.getText());
						if (existPW == null) {
							AdminController.callAlert("[PW 찾기 실패]: 존재하지 않는 회원정보 입니다.");
							return;
						}else{
							AdminController.callAlert("[ID 찾기 성공]: 회원님의 패스워드는 "+ existPW+" 입니다.");
							forgotIdStage.close();
						}
						
						
					}	
					
					
				}
				
			});

			// 1.2.취소버튼
			btnExit.setOnMouseClicked(e -> {
				forgotIdStage.close();
			});

		
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("비밀번호찾기");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("비밀번호 창 오류 : 비밀번호 오류발생!! 확인해주세요");
		}

	}

	
	// 뒤로 가기 실행
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
		//	AdminController.callAlert("화면 전환 성공 : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("화면 전환 오류 : 화면 전환에 오류 발생");
		}

	}

	// 기타 : 이미지 c:/images/filename.jpg로 저장
	private void imageSave() {

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		if (!DIR.exists()) {
			DIR.mkdir(); // if there is no directory -> Create Directory
		}
		// 선택된 이미지를 c:/images/"해당 이미지 이름" 으로 저장
		try {
			// c:/images/ddd.jpg
			// selectFile.getName() -> ddd.jpg 만을 가져온다.
			// 새로운 파일명을 규정 -> student12345678912_ddd.jpg가 새로이 만들어짐 (중복방지)
			// c:/images/ddd.jpg파일을 읽어서 c:/images/student12345678912_ddd.jpg 로 복사
			fis = new FileInputStream(selectFile);
			bis = new BufferedInputStream(fis);

			fileName = "tch" + System.currentTimeMillis() + "_" + selectFile.getName();

			// 상대경로 복사 불가
			fos = new FileOutputStream(DIR.getAbsolutePath() + "\\" + fileName);

			bos = new BufferedOutputStream(fos);

			int data = -1;
			while ((data = bis.read()) != -1) {
				// 값을 읽어서 data에다가 쓴다. -> 복사 사용자가 준 파일을 내가 원하는 위치에 복사
				bos.write(data);
				bos.flush();
			}

		} catch (Exception e) {
			AdminController.callAlert("이미지 저장 오류 : //images/ 저장파일 에러 점검 바람");
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

	// 기타 : 이미지 Delete
	private void imageDelete() {
		// delete image
		File imageFile = new File(DIR.getAbsolutePath() + "\\" + teacher.getImage());

		boolean delFlag = false;
		if (imageFile.exists() && imageFile.isFile()) {
			delFlag = imageFile.delete();
			if (delFlag == false) {
				AdminController.callAlert("이미지 제거 실패 : 이미지 제거 확인 바람.");
			}
		}
	}

}