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
		// 1.[JOIN] 회원->가입
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
	
	// 1.[JOIN] 회원->가입
	private void handleLogJoinAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/join.fxml"));
			Parent root = loader.load();
			ObservableMap<String, Object> fxmlNameSpace = loader.getNamespace();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			Stage joinStage = new Stage(StageStyle.UTILITY);

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
				// 바탕화면으로 이미지를 사용하지 않겠다는 의미
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
				
				int count = StudentDAO.checkStudentId(joinId.getText().toString());
					
				if (joinId.getText().contains("@") == false) {
					AdminController.callAlert("[ID양식오류] : 이메일 형식을 지켜주세요. ");
				
				} else if(count != 0) {
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
					System.out.println(gender);
					student = new Student(joinId.getText(), joinPW.getText(), joinName.getText(), joinPhone.getText(),
							gender, birthDate, fileName);
					int count2 = StudentDAO.insertStudent(student);
					System.out.println(count2+"count2");
					if (count2 != 0) {
						AdminController.callAlert("[회원가입 성공] : 회원가입이 완료되었습니다. \n 로그인 화면으로 이동합니다. ");
						joinStage.close();
					}
				}
			});
			
			

			// 1.2.취소버튼
			joinCancel.setOnMouseClicked(e -> {
				joinStage.close();
			});

			joinStage.initOwner(primaryStage);
			joinStage.initModality(Modality.WINDOW_MODAL);
			joinStage.setTitle("회원가입");
			joinStage.setScene(scene);
			joinStage.show();
		} catch (IOException e) {
			AdminController.callAlert("회원가입 창 오류 : 회원가입창 오류발생!! 확인해주세요");
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
			//AdminController.callAlert(" 성공 : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("화면 전환 오류 : 화면 전환에 오류 발생");
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
			//AdminController.callAlert("화면 전환 성공 : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("화면 전환 오류 : 화면 전환에 오류 발생");
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
			AdminController.callAlert("[LOGIN 실패]: 존재하지 않는 아이디 입니다.");
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
			//AdminController.callAlert("[성공] : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("[화면 전환 오류] : 화면 전환에 오류 발생");
		}
	}

	// 5.[FORGOT] ID
	private void handleLoginFgIdAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/forgot_id.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage forgotIdStage = new Stage(StageStyle.UTILITY);

			// **********************forgot_id.fxml id 등록****************************
			TextField name = (TextField) root.lookup("#name");
			TextField phone = (TextField) root.lookup("#phone");
			TextField dateOfY = (TextField) root.lookup("#dateOfY");
			TextField dateOfM = (TextField) root.lookup("#dateOfM");
			TextField dateOfD = (TextField) root.lookup("#dateOfD");
			TextField btnSearch = (TextField) root.lookup("#btnSearch");
			TextField btnExit = (TextField) root.lookup("#btnExit");

			// **********************이벤트 등록 및 초기화와 핸들러정의****************************

			// 1.1.검색 버튼 -> 아이디찾기 
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

		
			forgotIdStage.initOwner(primaryStage);
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("아이디찾기");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("[FORGOT ID] 오류 : 아이디찾기 오류발생!! 확인해주세요");
		}

	}

	// 6.[FORGOT] PW
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
			inputDecimalFormatFourDigit(dateOfY);
			inputDecimalFormatTwoDigit(dateOfM);
			inputDecimalFormatTwoDigit(dateOfD);
			inputDecimalFormatElevenDigit(phone);

			// 1.1.검색 버튼 -> 비밀번호 찾기 완료
			btnSearch.setOnMouseClicked(e -> {
				if (emailAddress.getText().contains("@") == false) {
					AdminController.callAlert("[ID양식 오류] : 이메일 형식을 지켜주세요. ");
				} else {
					int count=StudentDAO.checkStudentId(emailAddress.getText());
					if(count == 0) {
						AdminController.callAlert("[ID 오류] :존재하지 않는 이메일아이디입니다.");
					}else {
						AdminController.spaceCheck(phone.getText());	
					
						String date = dateOfY.getText() + "-" + dateOfM.getText() + "-" + dateOfD.getText();
						System.out.println(date);
						
						String existPW = StudentDAO.findPWByPhone(name.getText(),phone.getText(),date,emailAddress.getText());
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

			inputDecimalFormatFourDigit(dateOfY);
			inputDecimalFormatTwoDigit(dateOfM);
			inputDecimalFormatTwoDigit(dateOfD);
			forgotIdStage.initOwner(primaryStage);
			forgotIdStage.initModality(Modality.WINDOW_MODAL);
			forgotIdStage.setTitle("비밀번호찾기");
			forgotIdStage.setScene(scene);
			forgotIdStage.show();
		} catch (IOException e) {
			AdminController.callAlert("비밀번호 창 오류 : 비밀번호 오류발생!! 확인해주세요");
		}

	}

	// 기타 :텍스트필드 입력값 포맷설정기능(2자리 숫자만 받음)
	public static void inputDecimalFormatTwoDigit(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("##");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 3) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}

	// 기타 :텍스트필드 입력값 포맷설정기능(4자리 숫자만 받음)
	public static void inputDecimalFormatFourDigit(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("####");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 5) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}

	// 기타 :텍스트필드 입력값 포맷설정기능(6자리 숫자만 받음)
	public static void inputDecimalFormatSixDigit(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("######");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 7) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}

	// 기타 :텍스트필드 입력값 포맷설정기능(11자리 숫자만 받음)                                            
	public static void inputDecimalFormatElevenDigit(TextField textField) {
		// 숫자만 입력(정수만 입력받음), 실수입력받고싶으면new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###########");
		// 점수 입력시 길이 제한 이벤트 처리
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// 입력받은 내용이 없으면 이벤트를 리턴함.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// 구문을 분석할 시작 위치를 지정함. 세자리까지 계속해서 점검하겠다.
			ParsePosition parsePosition = new ParsePosition(0);
			// 입력받은 내용과 분석위치를 지정한지점부터 format 내용과 일치한지 분석함.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// 리턴값이 null 이거나,입력한길이와 구문분석위치값이 적어버리면(다 분석하지못했음을 뜻함)거나,입력한길이가 4이면(3자리를 넘었음을
			// 뜻함.) 이면 null 리턴함.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 12) {
				return null;
			} else {
				return event; // 값을 돌려주겠다.
			}
		}));
	}

	// 기타 : 이미지 c:/images/filename.jpg로 저장
	private void imageSave() {

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		if (!imageDir.exists()) {
			imageDir.mkdir(); // if there is no directory -> Create Directory
		}
		// 선택된 이미지를 c:/images/"해당 이미지 이름" 으로 저장
		try {
			// c:/images/ddd.jpg
			// selectFile.getName() -> ddd.jpg 만을 가져온다.
			// 새로운 파일명을 규정 -> student12345678912_ddd.jpg가 새로이 만들어짐 (중복방지)
			// c:/images/ddd.jpg파일을 읽어서 c:/images/student12345678912_ddd.jpg 로 복사
			fis = new FileInputStream(selectFile);
			bis = new BufferedInputStream(fis);

			fileName = "stu" + System.currentTimeMillis() + "_" + selectFile.getName();

			fos = new FileOutputStream(imageDir.getAbsolutePath() + "\\" + fileName);
			bos = new BufferedOutputStream(fos);

			int data = -1;
			while ((data = bis.read()) != -1) {
				// 값을 읽어서 data에다가 쓴다. -> 복사 사용자가 준 파일을 내가 원하는 위치에 복사
				bos.write(data);
				bos.flush();
			}

		} catch (Exception e) {
			AdminController.callAlert("이미지 저장 오류 : c\\/images/ 저장파일 에러 점검 바람");
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
		File imageFile = new File(imageDir.getAbsolutePath() + "\\" + student.getImage());

		boolean delFlag = false;
		if (imageFile.exists() && imageFile.isFile()) {
			delFlag = imageFile.delete();
			if (delFlag == false) {
				AdminController.callAlert("이미지 제거 실패 : 이미지 제거 확인 바람.");
			}
		}
	}

}
