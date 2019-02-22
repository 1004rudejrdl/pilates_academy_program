 package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Model.ClassMember;
import Model.Classes;
import Model.Student;
import Model.Teacher;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherController implements Initializable {
	public Stage tchStage;
	@FXML
	private GridPane gridPane;
	@FXML
	private TextField tchClsAddBtn;
	@FXML
	private TextField tchDate60;
	@FXML
	private TextField tchDate61;
	@FXML
	private TextField tchDate62;
	@FXML
	private TextField tchDate63;
	@FXML
	private TextField tchDate64;
	@FXML
	private TextField tchDate65;
	@FXML
	private TextField tchDate66;
	@FXML
	private TextField tchDate10;
	@FXML
	private TextField tchDate11;
	@FXML
	private TextField tchDate12;
	@FXML
	private TextField tchDate13;
	@FXML
	private TextField tchDate14;
	@FXML
	private TextField tchDate15;
	@FXML
	private TextField tchDate16;
	@FXML
	private TextField tchDate20;
	@FXML
	private TextField tchDate21;
	@FXML
	private TextField tchDate22;
	@FXML
	private TextField tchDate23;
	@FXML
	private TextField tchDate24;
	@FXML
	private TextField tchDate25;
	@FXML
	private TextField tchDate26;
	@FXML
	private TextField tchDate30;
	@FXML
	private TextField tchDate31;
	@FXML
	private TextField tchDate32;
	@FXML
	private TextField tchDate33;
	@FXML
	private TextField tchDate34;
	@FXML
	private TextField tchDate35;
	@FXML
	private TextField tchDate36;
	@FXML
	private TextField tchDate40;
	@FXML
	private TextField tchDate41;
	@FXML
	private TextField tchDate42;
	@FXML
	private TextField tchDate43;
	@FXML
	private TextField tchDate44;
	@FXML
	private TextField tchDate45;
	@FXML
	private TextField tchDate46;
	@FXML
	private TextField tchDate50;
	@FXML
	private TextField tchDate51;
	@FXML
	private TextField tchDate52;
	@FXML
	private TextField tchDate53;
	@FXML
	private TextField tchDate54;
	@FXML
	private TextField tchDate55;
	@FXML
	private TextField tchDate56;
	TextField[] tchDateArray = new TextField[42];

	private LocalDateTime ldt = LocalDateTime.now();
	private Calendar cal = Calendar.getInstance();

	private String localURL = null;
	private final File DIR = new File("C:/images");
	private Teacher teacher;
	private Classes selectedCls;
	private ClassMember selectedCMember;
	private File selectFile = null;
	private String fileName = "";
	private ArrayList<Classes> dbClsViewList;
	private ObservableList<Classes> obClsViewList = FXCollections.observableArrayList();
	private ObservableList<Student> obStuList = FXCollections.observableArrayList();

	@FXML
	private Label tchLogout;
	@FXML
	private Label tNameLbl;
	@FXML
	private Label lblCurrentMenu;
	@FXML
	private Label tchMyInfoTName;
	@FXML
	private ImageView tchExit;
	@FXML
	private Pane paneClsView;
	@FXML
	private Pane paneClsAdd;
	@FXML
	private Pane paneMy1;
	@FXML
	private Pane paneCenterInfo;
	
	@FXML
	private MenuItem menuClsView;
	@FXML
	private MenuItem menuClsAdd;
	@FXML
	private Label menuMy;
	@FXML
	private Label menuCenterInfo;
	

	// **************[Class ADD MENU]***********
	@FXML
	private TableView<Classes> tchClsAddTV;

	// **************[MY MENU]***********
	@FXML
	private TableView<Classes> tchSrchClsTV;
	@FXML
	private TableView<ClassMember> tchSrchCMmbTV;
	@FXML
	private ImageView tchSrBtn;
	@FXML
	private DatePicker tchClsDate;
//	@FXML
//	private TableView<Student> tchMyStuTV;
	@FXML private TableView<Classes> tchClassTV;
//	@FXML
//	private ImageView tchMyStuImg;
	// ************Tab3******************
	@FXML
	private ImageView tchMyImgView;
	@FXML
	private TextField tchMyInfoID;
	@FXML
	private TextField tchMyInfoPhn;
	@FXML
	private TextField tchMyInfoChngBtn;
	private ArrayList<Teacher> dbTchList = new ArrayList<>();
	private ObservableList<Teacher> obTchAllList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsTimeList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsTypeList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsCouseList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsRoomList = FXCollections.observableArrayList();
	@FXML private ImageView tchClsAddImg;
	private Integer jj=0;
	@FXML private ImageView mapBtn;
	@FXML private Button mapTxtBtn;
	@FXML private TableView<ClassMember> tchClassMemberTV;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initClassTableView2();
		// 5.강의 클릭 -> CMember (STUDENTS)
		tchClassTV.setOnMouseClicked(e -> handleAdminClassTVClickAction(e));
		//MAP
		mapBtn.setOnMouseClicked(e-> map());
		mapTxtBtn.setOnMouseClicked(e-> map());				
		
		// 0. [초기화] init TV
		teacher = TeacherDAO.getTchNameDataByTID(TchLoginController.teacherID);
		initClassTableView();
		initClassMemberTableView();
	//	initStudentTableView();
		initClassMemberTableView2();
	
		initValues();
// [ClassADD Menu]
		initCalender();
		tchClsAddBtn.setOnMouseClicked(e -> handleClsAddAction(LocalDate.now().toString()));
		tchClsAddTV.setOnMouseClicked(e-> handleClsAddTVClickedAction());
		
		// [ClassView Menu]
		menuClsView.setOnAction(e -> menuClsViewAction());
		menuClsAdd.setOnAction(e -> menuClsAddAction());
		//menuMy.setOnMouseClicked(e -> menuMyAction());
		menuCenterInfo.setOnMouseClicked(e -> menuCenterInfoAction());

		// [Exit]
		tchExit.setOnMouseClicked(e -> Platform.exit());
		// [Logout]
		tchLogout.setOnMouseClicked(e -> handleLogoutAction());

		// 메뉴 이동

		// [MY MENU]
		// 1.1 classTV -> cmemebers
		tchSrchClsTV.setOnMouseClicked(e -> handleSrchClsTVClickAction());
		// 1.2 수업날짜로 수업을 검색
		tchSrBtn.setOnMouseClicked(e -> handleClsSrchBtnAction());
		// 1.3 학생 이미지 출력
	//	tchMyStuTV.setOnMouseClicked(e -> handleMyStuTVAction());
		// 1.4 정보 수정(tab3)
		tchMyInfoChngBtn.setOnMouseClicked(e -> handleMyInfoChngBtnAction());

	}
	private void initClassMemberTableView2() {

		TableColumn tcSName = tchClassMemberTV.getColumns().get(0);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcStudentID = tchClassMemberTV.getColumns().get(1);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");
		
	}
	private void handleAdminClassTVClickAction(MouseEvent e) {
		selectedCls = tchClassTV.getSelectionModel().getSelectedItem();

		ArrayList<ClassMember> cMemberList = new ArrayList<>();
		ObservableList<ClassMember> obCMemberList = FXCollections.observableArrayList();
		// CMEMBERTBL dataBase와 연동하는 것
		cMemberList = ClassDAO.getCMemberTotalData(selectedCls.getClassInfo());
		for (ClassMember cMmb : cMemberList) {
			obCMemberList.add(cMmb);
		}
		tchClassMemberTV.setItems(obCMemberList);
		
//		ClassDAO.deleteClass(selectedCls);
		if(e.getClickCount() ==2) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/double_check_del_cls.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
				Stage newClassStage = new Stage(StageStyle.UTILITY);

				// **********************new_class.fxml id등록****************************
				TextField delDblTxtName = (TextField) root.lookup("#delDblTxtName");
				TextField delDblTxtID = (TextField) root.lookup("#delDblTxtID");
				TextField delDblBtnBack = (TextField) root.lookup("#delDblBtnBack");
				TextField delDblBtnDelete = (TextField) root.lookup("#delDblBtnDelete");
				// **********************이벤트 등록 및 초기화와 핸들러정의****************************
			//[INITIALIZE] ComboBoxes & TableView
				
				delDblTxtName.setText(selectedCls.getClassInfo());
				delDblTxtID.setText(selectedCls.getTName());
				
				// 1.1.1 삭제 버튼
				delDblBtnDelete.setOnMouseClicked(e1 -> {
					int count = ClassDAO.deleteClass(selectedCls);
					if (count != 0) {
						//ClassDAO.deleteClass(selectedCls);
						newClassStage.close();
						AdminController.callAlert("[CLASS DELETE SUCCEED] : ClassInfo  "
								+ selectedCls.getClassInfo() + "\t"  + "수업 삭제완료");
						initClassTableView();
						
					}
				});

				// 1.1.2.뒤로가기 버튼
				delDblBtnBack.setOnMouseClicked(e1 -> {
					newClassStage.close();
				});
				
				
				newClassStage.initOwner(tchStage);
				newClassStage.initModality(Modality.WINDOW_MODAL);
				newClassStage.setTitle("DELETE CLASS");
				newClassStage.setScene(scene);
				newClassStage.show();
			} catch (IOException e2) {
				AdminController.callAlert("삭제 창 오류 : 삭제 오류발생!! 확인해주세요");
			}
			
			
		}
		
	}

	private void initClassTableView2() {

		TableColumn tcClassDate = tchClassTV.getColumns().get(0);
		tcClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClassTime = tchClassTV.getColumns().get(1);
		tcClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

		TableColumn tcCourse = tchClassTV.getColumns().get(2);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcCType = tchClassTV.getColumns().get(3);
		tcCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcCType.setStyle("-fx-alignment: CENTER;");
		tcClassTime.setStyle("-fx-alignment: CENTER;");
		TableColumn tcClassRoom = tchClassTV.getColumns().get(4);
		tcClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcClassRoom.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTName = tchClassTV.getColumns().get(5);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLimitNum = tchClassTV.getColumns().get(6);
		tcLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcNowNum = tchClassTV.getColumns().get(7);
		tcNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcNowNum.setStyle("-fx-alignment: CENTER;");

///////RE: 과거 이력까지 모두 가져오기// CLASSES dataBase와 연동하는 것
		ArrayList<Classes> dbClsViewList1 = new ArrayList<>();
		dbClsViewList1 = ClassDAO.getClassesTotalData();
		ObservableList<Classes> list = FXCollections.observableArrayList();
		
		for (Classes cls : dbClsViewList1) {
			list.add(cls);
		}
		tchClassTV.setItems(list);

		
	}
	private void map() {
		 // create web engine and view
	        final WebView webView = new WebView();
	        final WebEngine webEngine = webView.getEngine();
	        Stage stage = new Stage();
	        webEngine.load(getClass().getResource("../View/googlemap.html").toString());
	   
	        // create scene
	        stage.setTitle("체리쉬 필라테스로 오시는 길");
	        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
	        stage.setScene(scene);
	        // show stage
	        stage.show();
		
	}


	private void handleMyInfoChngBtnAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit_tch.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			Stage editStage = new Stage(StageStyle.UTILITY);

			// **********************edit_st id 등록****************************
			PasswordField eTchInfoNEW = (PasswordField) root.lookup("#eTchInfoNEW");
			PasswordField eTchInfoNowPW = (PasswordField) root.lookup("#eTchInfoNowPW");
			PasswordField eTchInfoNEWCheck = (PasswordField) root.lookup("#eTchInfoNEWCheck");
			TextField eTchInfoID = (TextField) root.lookup("#eTchInfoID");
			TextField eTchInfoPhone = (TextField) root.lookup("#eTchInfoPhone");
			TextField eTchInfoBtnSave = (TextField) root.lookup("#eTchInfoBtnSave");
			TextField eTchInfoBtnExit = (TextField) root.lookup("#eTchInfoBtnExit");
			ImageView eTchInfoImg = (ImageView) root.lookup("#eTchInfoImg");
			CheckBox changePW = (CheckBox) root.lookup("#changePW");

			// **********************이벤트 등록 및 초기화와 핸들러정의****************************
			// 비밀번호 변경 체크박스
			changePW.setOnAction(e -> {
				changePW.setDisable(true);
				eTchInfoNowPW.setDisable(false);
				eTchInfoNEW.setDisable(false);
				eTchInfoNEWCheck.setDisable(false);
			});

			eTchInfoID.setText(teacher.getTeacherID());
			eTchInfoPhone.setText(teacher.getPhone());

			// 1.1 이미지 버튼 -> 이미지 변경 fileChooser
			eTchInfoImg.setOnMouseClicked(e -> {
				fileName = teacher.getImage();
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(tchStage);

				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						AdminController.callAlert("[Error] : 이미지 선택에 오류가 발생했습니다.");
					}
				}
				eTchInfoImg.setImage(new Image(localURL, false)); // 바탕화면으로 이미지를 사용하지 않겠다는 의미
				// 선택된 파일명

				fileName = selectFile.getName();

				// Image edit
				if (!fileName.equals("")) {
					imageDelete();
					imageSave();
					if (selectFile == null) {
						AdminController.callAlert("이미지 선택 에러 : 이미지선택해주세요");
						return;
					}

				}
			}

			);
			// 1.2 변경된 정보를 저장
			eTchInfoBtnSave.setOnMouseClicked(e -> {
				String pw = "";
				AdminController.spaceCheck(eTchInfoNEW.getText());
				AdminController.spaceCheck(eTchInfoNEWCheck.getText());

				if (!changePW.isSelected()) {
					pw = teacher.getTPassword().trim();
				} else if (!teacher.getTPassword().equals(eTchInfoNowPW.getText())) {
					AdminController.callAlert("현재 패스워드 오류 : 변경 전 패스워드 오류 입니다.");
					eTchInfoNowPW.clear();
					eTchInfoNEW.clear();
					eTchInfoNEWCheck.clear();
				} else if (!eTchInfoNEW.getText().trim().equals(eTchInfoNEWCheck.getText())) {
					eTchInfoNowPW.clear();
					eTchInfoNEW.clear();
					eTchInfoNEWCheck.clear();
					AdminController.callAlert("새로운 비밀번호 오류 : NEW 비밀번호 확인 불일치");
				} else {
					pw = eTchInfoNEW.getText();
				}
				
				Teacher tch = new Teacher(teacher.getTeacherID(), pw, teacher.getTName(), teacher.getPhone(),
						teacher.getGender(), teacher.getBirthDate(), fileName);
				int count = TeacherDAO.updateTeacherInfo(tch);
				tchMyImgView.setImage(new Image(localURL, false));
				editStage.close();
			//	AdminController.callAlert("성공 : 강사정보 변경 성공 ");
			});
			// 1.3.취소버튼
			eTchInfoBtnExit.setOnMouseClicked(e -> {
				editStage.close();
			});

			editStage.initOwner(tchStage);
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.setTitle("정보변경");
			editStage.setScene(scene);
			editStage.show();
		} catch (IOException e) {
			AdminController.callAlert("정보변경 창 오류 : 정보변경 오류발생!! 확인해주세요");
		}

	}

	

	private void initValues() {
		tNameLbl.setText(teacher.getTName());
		tchMyInfoTName.setText(teacher.getTName());
		tchMyInfoID.setText(teacher.getTeacherID());
		tchMyInfoPhn.setText(teacher.getPhone());
		String url = "file:///" + DIR.getAbsolutePath() + "/" + teacher.getImage();
		tchMyImgView.setImage(new Image(url));

		// dataBase와 연동하는 것
		dbTchList = TeacherDAO.getTeacherTotalData();
		for (Teacher tch : dbTchList) {
			obTchAllList.add(tch);
		}
		obNClsTimeList.addAll("07:00-07:50", "08:00-08:50", "09:00-09:50", "10:00-10:50", "11:00-11:50", "18:00-18:50",
				"19:00-19:50", "20:00-20:50", "21:00-21:50", "22:00-22:50");
		obNClsCouseList.addAll("개인", "그룹");
		obNClsTypeList.addAll("리포머", "매트");
		obNClsRoomList.addAll("A", "B", "C", "D");
	}

	private void initClassTableView() {

		TableColumn tcClassDate = tchSrchClsTV.getColumns().get(0);
		tcClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClassTime = tchSrchClsTV.getColumns().get(1);
		tcClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

		TableColumn tcCourse = tchSrchClsTV.getColumns().get(2);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcCType = tchSrchClsTV.getColumns().get(3);
		tcCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcCType.setStyle("-fx-alignment: CENTER;");
		tcClassTime.setStyle("-fx-alignment: CENTER;");
		TableColumn tcClassRoom = tchSrchClsTV.getColumns().get(4);
		tcClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcClassRoom.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTName = tchSrchClsTV.getColumns().get(5);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLimitNum = tchSrchClsTV.getColumns().get(6);
		tcLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcNowNum = tchSrchClsTV.getColumns().get(7);
		tcNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcNowNum.setStyle("-fx-alignment: CENTER;");

		// CLASSES dataBase와 연동하는 것
		obClsViewList = ClassDAO.searchSelectedClsData(TchLoginController.teacherID);
		tchSrchClsTV.setItems(obClsViewList);
		
		TableColumn tcAClassDate = tchClsAddTV.getColumns().get(0);
		tcAClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcAClassDate.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcAClassTime = tchClsAddTV.getColumns().get(1);
		tcAClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));
		tcAClassTime.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcACourse = tchClsAddTV.getColumns().get(2);
		tcACourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcACourse.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcACType = tchClsAddTV.getColumns().get(3);
		tcACType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcACType.setStyle("-fx-alignment: CENTER;");
		TableColumn tcAClassRoom = tchClsAddTV.getColumns().get(4);
		tcAClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcAClassRoom.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcATName = tchClsAddTV.getColumns().get(7);
		tcATName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcATName.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcALimitNum = tchClsAddTV.getColumns().get(5);
		tcALimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcALimitNum.setStyle("-fx-alignment: CENTER;");
		
		TableColumn tcANowNum = tchClsAddTV.getColumns().get(6);
		tcANowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcANowNum.setStyle("-fx-alignment: CENTER;");

	}

	private void initClassMemberTableView() {

		TableColumn tcSName = tchSrchCMmbTV.getColumns().get(0);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcStudentID = tchSrchCMmbTV.getColumns().get(1);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");

	}

	/*private void initStudentTableView() {

		TableColumn tcSName = tchMyStuTV.getColumns().get(0);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcStudentID = tchMyStuTV.getColumns().get(1);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPhone = tchMyStuTV.getColumns().get(2);
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcPhone.setStyle("-fx-alignment: CENTER;");

		TableColumn tcGender = tchMyStuTV.getColumns().get(3);
		tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		tcGender.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBirthDate = tchMyStuTV.getColumns().get(4);
		tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		tcBirthDate.setStyle("-fx-alignment: CENTER;");

		// dataBase와 연동하는 것
		obStuList = StudentDAO.searchSelectedStuData(TchLoginController.teacherID);
		tchMyStuTV.setItems(obStuList);

	}
	*/
	// 강의 정보
	private void handleSrchClsTVClickAction() {
		selectedCls = tchSrchClsTV.getSelectionModel().getSelectedItem();

		ArrayList<ClassMember> cMemberList = new ArrayList<>();
		ObservableList<ClassMember> obCMemberList = FXCollections.observableArrayList();
		// CMEMBERTBL dataBase와 연동하는 것
		cMemberList = ClassDAO.getCMemberTotalData(selectedCls.getClassInfo());
		for (ClassMember cMmb : cMemberList) {
			obCMemberList.add(cMmb);
		}
		tchSrchCMmbTV.setItems(obCMemberList);

	}

	// 1.2 수업날짜로 수업을 검색
	private void handleClsSrchBtnAction() {
		tchSrchCMmbTV.refresh();
		String selectedDate = tchClsDate.getValue().toString();
		ObservableList<Classes> dbClsByDateList = ClassDAO.selectClassDataByDate(selectedDate);
		tchSrchClsTV.setItems(dbClsByDateList);
	}

	/*// 1.3 학생 이미지 출력
	private void handleMyStuTVAction() {
		Student std = tchMyStuTV.getSelectionModel().getSelectedItem();
		if(std!= null) {
			String url = "file:///" + DIR.getAbsolutePath() + "/" + std.getImage();
			tchMyStuImg.setImage(new Image(url));
			
		}
	}*/

//Calendar [ACTION] Add new Class (Register Class)
	private void initCalender() {
		tchDateArray = new TextField[] { tchDate10, tchDate11, tchDate12, tchDate13, tchDate14, tchDate15, tchDate16,
				tchDate20, tchDate21, tchDate22, tchDate23, tchDate24, tchDate25, tchDate26, tchDate30, tchDate31,
				tchDate32, tchDate33, tchDate34, tchDate35, tchDate36, tchDate40, tchDate41, tchDate42, tchDate43,
				tchDate44, tchDate45, tchDate46, tchDate50, tchDate51, tchDate52, tchDate53, tchDate54, tchDate55,
				tchDate56, tchDate60, tchDate61, tchDate62, tchDate63, tchDate64, tchDate65, tchDate66 };

		final int year = ldt.getYear();
		final int month = ldt.getMonthValue();
		int day = ldt.getDayOfMonth();
		cal.set(year, month - 1, 1);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int firstday = 0;
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		switch (dayOfWeek) {
		case 1:
			firstday = 0;
			break;
		case 2:
			firstday = 1;
			break;
		case 3:
			firstday = 2;
			break;
		case 4:
			firstday = 3;
			break;
		case 5:
			firstday = 4;
			break;
		case 6:
			firstday = 5;
			break;
		case 7:
			firstday = 6;
			break;
		}
		int j = 0;

		for (int i = firstday; i < lastDay + firstday; i++) {
			j++;
			tchDateArray[i].setText("" + j);
			tchDateArray[i].setStyle("-fx-cursor: hand;");

			final int ii = i;
			tchDateArray[i].setOnMouseClicked(e -> {
				String month1 = "";
				if (month < 10) {
					month1 = "0" + month;
				}
				String day1 = "";
				if (Integer.parseInt(tchDateArray[ii].getText()) < 10) {
					day1 = "0" + tchDateArray[ii].getText();
				} else {
					day1 = tchDateArray[ii].getText();

				}
				String date = year + "-" + month1 + "-" + day1;

				if (e.getClickCount() == 2) {
					handleClsAddAction(date);

				} else if (e.getClickCount() == 1) {
					ObservableList<Classes> dbClsByDateList = ClassDAO.selectClassDataByDate(date);
					tchClsAddTV.setItems(dbClsByDateList);
					if(jj!=ii){						
						tchDateArray[ii].setStyle("-fx-background-color: orange");
						tchDateArray[jj].setStyle("-fx-background-color: white");						
					}		
					jj=ii;	
				}

			});

		}

	}

	// 1.1.강의정보 -New
	private void handleClsAddAction(String date) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/new_class.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			Stage newClassStage = new Stage(StageStyle.UTILITY);

			// **********************new_class.fxml id등록****************************
			ImageView joinTesting = (ImageView) root.lookup("#nClsImg");
			DatePicker nClsDate = (DatePicker) root.lookup("#nClsDate");
			ComboBox<String> nClsTime = (ComboBox<String>) root.lookup("#nClsTime");
			ComboBox<String> nClsType = (ComboBox<String>) root.lookup("#nClsType");
			ComboBox<String> nClsCourse = (ComboBox<String>) root.lookup("#nClsCourse");
			ComboBox<String> nClsRoom = (ComboBox<String>) root.lookup("#nClsRoom");
			TableView<Teacher> nClsTeacherTV = (TableView<Teacher>) root.lookup("#nClsTeacherTV");
			TextField nClsRegister = (TextField) root.lookup("#nClsRegister");
			TextField nClsExit = (TextField) root.lookup("#nClsExit");
			// **********************이벤트 등록 및 초기화와 핸들러정의****************************
			// [INITIALIZE] ComboBoxes & TableView
			TableColumn tcTeacherID = nClsTeacherTV.getColumns().get(0);
			tcTeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
			tcTeacherID.setStyle("-fx-alignment: CENTER;");

			TableColumn tcTName = nClsTeacherTV.getColumns().get(1);
			tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
			tcTName.setStyle("-fx-alignment: CENTER;");
			nClsDate.setValue(LocalDate.parse(date));
			nClsTeacherTV.setItems(obTchAllList);
			nClsTime.setItems(obNClsTimeList);
			nClsType.setItems(obNClsTypeList);
			nClsCourse.setItems(obNClsCouseList);
			nClsRoom.setItems(obNClsRoomList);
			// 6.1.1[ACTION] Exit
			nClsExit.setOnMouseClicked(e -> {
				newClassStage.close();
				tchStage.show();
			});

			// 6.1.1[ACTION] Add new Class (Register Class)
			nClsRegister.setOnMouseClicked(e -> {
				ClassDAO.insertClass(nClsTeacherTV.getSelectionModel().getSelectedItem().getTeacherID(),
						nClsCourse.getSelectionModel().getSelectedItem(),
						nClsType.getSelectionModel().getSelectedItem(), nClsDate.getValue().toString(),
						nClsTime.getSelectionModel().getSelectedItem(), nClsRoom.getSelectionModel().getSelectedItem());
				newClassStage.close();

			});
			newClassStage.initOwner(tchStage);
			newClassStage.initModality(Modality.WINDOW_MODAL);
			newClassStage.setTitle("ADD NEW CLASS");
			newClassStage.setScene(scene);
			newClassStage.show();
		} catch (IOException e2) {
			AdminController.callAlert("회원계정 삭제 창 오류 : 회원계정 삭제 오류발생!! 확인해주세요");
		}

	}
	// TV -> Teacher IMG
	private void handleClsAddTVClickedAction() {
		Classes cls = tchClsAddTV.getSelectionModel().getSelectedItem();
		String url = "file:///" + DIR.getAbsolutePath() + "/" + cls.getTImage();
		tchClsAddImg.setImage(new Image(url));
	}
	

	private void menuCenterInfoAction() {
		defaultDisplay();
		paneCenterInfo.setVisible(true);
		lblCurrentMenu.setText("Cherish Pilates Information");
	}

	private void menuMyAction() {
		defaultDisplay();
		paneMy1.setVisible(true);
		lblCurrentMenu.setText("My Information");
	}

	private void menuClsAddAction() {
		defaultDisplay();
		paneClsAdd.setVisible(true);
		lblCurrentMenu.setText("Class Information");
	}

	private void menuClsViewAction() {
		defaultDisplay();
		paneClsView.setVisible(true);
		lblCurrentMenu.setText("Class Information");
	}

	// 기타 : 창 초기화
	private void defaultDisplay() {
		paneClsAdd.setVisible(false);
		paneClsView.setVisible(false);
		paneMy1.setVisible(false);
		paneCenterInfo.setVisible(false);
	}

	// [LOGOUT]
	private void handleLogoutAction() {
		try {
			Stage tchLoginStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/login_tch.fxml"));
			Parent root = loader.load();
			TchLoginController tchLoginController = loader.getController();
			tchLoginController.tchLoginStage = tchLoginStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			tchLoginStage.setScene(scene);
			tchLoginStage.setTitle("LOGIN");
			tchStage.close();
			tchLoginStage.show();
			//AdminController.callAlert("화면 전환 성공 : 화면 전환에 성공 ");
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
			// 새로운 파일명을 규정 -> tch12345678912_ddd.jpg가 새로이 만들어짐 (중복방지)
			// c:/images/ddd.jpg파일을 읽어서 c:/images/tch12345678912_ddd.jpg 로 복사
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
