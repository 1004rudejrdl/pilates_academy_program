package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Model.ClassMember;
import Model.Classes;
import Model.Health;
import Model.Payment;
import Model.Student;
import Model.Teacher;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {
	public Stage adminStage;
	
	@FXML
	private GridPane gridPane;
	@FXML
	private TextField adminClsAddBtn;
	@FXML
	private TextField stuDate60;
	@FXML
	private TextField stuDate61;
	@FXML
	private TextField stuDate62;
	@FXML
	private TextField stuDate63;
	@FXML
	private TextField stuDate64;
	@FXML
	private TextField stuDate65;
	@FXML
	private TextField stuDate66;
	@FXML
	private TextField stuDate10;
	@FXML
	private TextField stuDate11;
	@FXML
	private TextField stuDate12;
	@FXML
	private TextField stuDate13;
	@FXML
	private TextField stuDate14;
	@FXML
	private TextField stuDate15;
	@FXML
	private TextField stuDate16;
	@FXML
	private TextField stuDate20;
	@FXML
	private TextField stuDate21;
	@FXML
	private TextField stuDate22;
	@FXML
	private TextField stuDate23;
	@FXML
	private TextField stuDate24;
	@FXML
	private TextField stuDate25;
	@FXML
	private TextField stuDate26;
	@FXML
	private TextField stuDate30;
	@FXML
	private TextField stuDate31;
	@FXML
	private TextField stuDate32;
	@FXML
	private TextField stuDate33;
	@FXML
	private TextField stuDate34;
	@FXML
	private TextField stuDate35;
	@FXML
	private TextField stuDate36;
	@FXML
	private TextField stuDate40;
	@FXML
	private TextField stuDate41;
	@FXML
	private TextField stuDate42;
	@FXML
	private TextField stuDate43;
	@FXML
	private TextField stuDate44;
	@FXML
	private TextField stuDate45;
	@FXML
	private TextField stuDate46;
	@FXML
	private TextField stuDate50;
	@FXML
	private TextField stuDate51;
	@FXML
	private TextField stuDate52;
	@FXML
	private TextField stuDate53;
	@FXML
	private TextField stuDate54;
	@FXML
	private TextField stuDate55;
	@FXML
	private TextField stuDate56;
	private TextField[] stuDateArray = new TextField[42];

	private LocalDateTime ldt = LocalDateTime.now();
	private Calendar cal = Calendar.getInstance();

	@FXML
	private MenuItem adminMenuStuAll;
	@FXML
	private MenuItem adminMenuStuSearch;
	@FXML
	private MenuItem adminMenuTchAll;
	@FXML
	private MenuItem adminMenuTchSearch;
	@FXML
	private MenuItem adminMenuClsView;
	@FXML
	private MenuItem adminMenuClsAdd;
	@FXML
	private Label adminMenuChart;
	@FXML
	private Label adminMenuChat;
	@FXML
	private Label adminLogout;
	@FXML
	private ImageView adminExit;
	@FXML
	private Label lblCurrentMenu;
	// ******stuAll메뉴******
	@FXML
	private Pane adminStuAll;
	@FXML
	private TextField stuAllSearch;
	@FXML
	private ImageView stuAllSearchBtn;
	@FXML
	private TableView<Student> stuAllStuTV;
	private Student selectedStu;
	public static ArrayList<Student> dbStuList = new ArrayList<>();
	private ObservableList<Student> obStuAllList = FXCollections.observableArrayList();

	// ******studentSearch메뉴******
	@FXML
	private Pane adminStuSearch;
	@FXML
	private ImageView stuSrchBtn;
	@FXML
	private TextField stuSrchTxt;
	@FXML
	private TableView<Payment> stuSrchPaymentTV;
	private Payment selectedPayment;
	public static ArrayList<Payment> dbPmtList = new ArrayList<>();
	private ObservableList<Payment> obPmtList = FXCollections.observableArrayList();

	// ******studentSearch메뉴 > HEALTH*************
	@FXML
	private TableView<Health> stuSrchHealthTV;
	private Health selectedHealth;
	public static ArrayList<Health> dbHealthList = new ArrayList<>();
	private ObservableList<Health> obStuHealthList = FXCollections.observableArrayList();

	// ******teacherAll메뉴******
	@FXML
	private Pane adminTchAll;
	@FXML
	private TextField tchAllSearch;
	@FXML
	private ImageView tchAllSearchBtn;
	@FXML
	private TableView<Teacher> tchAllTV;
	private Teacher selectedTch;
	public static ArrayList<Teacher> dbTchList = new ArrayList<>();
	ObservableList<Teacher> obTchAllList = FXCollections.observableArrayList();
	@FXML
	private TextField adminClsEDITBtn;

	// ******teacherSearchOthers메뉴******
	@FXML
	private TableView<Classes> adminTchSrchClsTV;
	@FXML
	private TableView<ClassMember> adminTchSrchCMmbTV;
	@FXML
	private TableView<Student> adminTchSrchStuTV;
	@FXML
	private TextField tchSrchTxt;
	@FXML
	private ImageView tchSrchTxtBtn;

	// ******classView메뉴******
	@FXML
	private Pane adminClassView;
	@FXML
	private TableView<Classes> adminClassTV;
	@FXML
	private 	TableView<ClassMember> adminClassMemberTV;
	@FXML
	private DatePicker adminClsSrchDate;
	@FXML
	private ImageView adminClsSrchBtn;
	private Classes selectedCls;
	public static ArrayList<Classes> dbClsViewList = new ArrayList<>();
	private ObservableList<Classes> obClsViewList = FXCollections.observableArrayList();
	private ObservableList<Classes> obCalenderClsViewList = FXCollections.observableArrayList();

	// ******classNew메뉴******
	private ObservableList<String> obNClsTimeList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsCouseList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsTypeList = FXCollections.observableArrayList();
	private ObservableList<String> obNClsRoomList = FXCollections.observableArrayList();
	@FXML
	private TableView<Classes> adminClsAddTV;
	@FXML
	private ImageView adminClsAddImg;
	public static ArrayList<ClassMember> dbClsViewCMemberList = new ArrayList<>();
	private ObservableList<ClassMember> obClsViewCMemberList = FXCollections.observableArrayList();
	@FXML
	private Label sssName;
	@FXML
	private ImageView img;
	@FXML
	private Pane adminTchSearch;
	@FXML
	private Pane adminClassAdd;
	@FXML
	private Pane adminSalesChart;
	private Integer jj = 0;

	// ******Chart 변수 ******
	@FXML private BarChart<String, Integer> lineChrtTch;
	@FXML private ImageView btnQuater;
	@FXML private ImageView btnTchSearch;
	@FXML private ImageView btnTchYear;
	@FXML private LineChart<String, Integer> lineChrtQuater;
	@FXML private ComboBox<String> chrtCmbYear;
	@FXML private ComboBox<String> chrtCmbQuarter;
	@FXML private ComboBox<String> cmbBoxTchName;
	@FXML private 
	ComboBox<String> cmbBoxTchYear;
	private ObservableList<String> obQuarterList = FXCollections.observableArrayList();
	private ObservableList<String> obYearList = FXCollections.observableArrayList();
	private ObservableList<String> obCmbTchIDList = FXCollections.observableArrayList();
	private final File DIR = new File("C:/images");
	private Teacher tch = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 0. [SET]setting
		initStudentTableView();
		initStuHealthTableView();
		initTeacherTableView();
		initPaymentTableView();
		initClassTableView();
		initClassMemberTableView();
		initComboBoxes();

		// 1. [회원 MENU]
		adminMenuStuAll.setOnAction(e -> handleStuAll());
		// 1.1 회원 - 삭제
		stuAllStuTV.setOnMouseClicked(e -> handleDeleteStu(e));
		// 1.2 회원기본정보 - 검색
		stuAllSearchBtn.setOnMouseClicked(e -> handleStuInfoSearch());
		stuAllSearch.setOnKeyPressed(event -> {	if (event.getCode().equals(KeyCode.ENTER)) {handleStuInfoSearch();}});
		// 1.3회원정보 Search Others
		stuSrchBtn.setOnMouseClicked(e -> handleStuSearchOthers());
		stuSrchTxt.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) {handleStuSearchOthers();}});

		// 2. [회원 MENU_OTHERS]
		adminMenuStuSearch.setOnAction(e -> handleStuSearch());
	
		// 3. [강사 MENU] 
		adminMenuTchAll.setOnAction(e -> handleTchAll());
		// 3.1 강사 - 삭제
		tchAllTV.setOnMouseClicked(e -> handleTchDelete(e));
		// 3.2 강사 - 검색
		tchAllSearchBtn.setOnMouseClicked(e -> handleTchInfoSearch());
		tchAllSearch.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) {	handleTchInfoSearch();}});

		// 4. [강사 MENU_OTHERS]
		adminMenuTchSearch.setOnAction(e -> handleTchSearch());
		// 4.1강사정보 - Ohters -클래스 - 클래스 멤버
		adminTchSrchClsTV.setOnMouseClicked(e -> handleTchSrchClsTVClickAction());
		// 4.2강사정보 - Ohters -Student- 검색창
		tchSrchTxtBtn.setOnMouseClicked(e -> handleTchSrchTxtAction());
		tchSrchTxt.setOnKeyPressed(event -> {if (event.getCode().equals(KeyCode.ENTER)) {handleTchSrchTxtAction();}});

		// 5. [강의 MENU_VIEW]
		adminMenuClsView.setOnAction(e -> handleClsView());
		// 5. 강의 클릭 -> CMember (STUDENTS)
		adminClassTV.setOnMouseClicked(e -> handleAdminClassTVClickAction(e));
		// 5. 강의정보 -> 날짜로 검색
		adminClsSrchBtn.setOnMouseClicked(e -> handleAdminClsSrchAction());
		// 6. [강의 MENU_ADD] 
		adminMenuClsAdd.setOnAction(e -> handleClsNew());
		adminClsAddTV.setOnMouseClicked(e -> handleClsAddTvAction());
		// 6.1 강의 추가버튼 -new_class.fxml
		adminClsAddBtn.setOnMouseClicked(e -> handleClsAddAction(LocalDate.now().toString()));
		initCalender();
		adminClsEDITBtn.setOnMouseClicked(e -> handleClsEditAction());
		// 7. [CHART MENU]
		adminMenuChart.setOnMouseClicked(e -> handleMenuChart());
		btnQuater.setOnMouseClicked(e -> handleBtnQuarterChart());
		btnTchSearch.setOnMouseClicked(e -> handleBtnTchSrchChart());
		btnTchYear.setOnMouseClicked(e -> handleBtnTchYearChart());
		// 8. [고객상담 MENU]
		adminMenuChat.setOnMouseClicked(e -> ssssserverAction());
		// [LOGOUT]
		adminLogout.setOnMouseClicked(e -> handleLogoutAction());
		// [EXIT]
		adminExit.setOnMouseClicked(e -> Platform.exit());
	}
	// 0. [SET]
	private void initStudentTableView() {

		TableColumn tcSName = stuAllStuTV.getColumns().get(0);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcStudentID = stuAllStuTV.getColumns().get(1);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");

		TableColumn tcSPassword = stuAllStuTV.getColumns().get(2);
		tcSPassword.setCellValueFactory(new PropertyValueFactory<>("sPassword"));
		tcSPassword.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPhone = stuAllStuTV.getColumns().get(3);
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcPhone.setStyle("-fx-alignment: CENTER;");

		TableColumn tcGender = stuAllStuTV.getColumns().get(4);
		tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		tcGender.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBirthDate = stuAllStuTV.getColumns().get(5);
		tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		tcBirthDate.setStyle("-fx-alignment: CENTER;");

		// dataBase와 연동하는 것
		dbStuList = StudentDAO.getStudentTotalData();
		for (Student stu : dbStuList) {
			obStuAllList.add(stu);
		}
		stuAllStuTV.setItems(obStuAllList);

		TableColumn tcTchSName = adminTchSrchStuTV.getColumns().get(0);
		tcTchSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcTchSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchStudentID = adminTchSrchStuTV.getColumns().get(1);
		tcTchStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcTchStudentID.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchSPassword = adminTchSrchStuTV.getColumns().get(2);
		tcTchSPassword.setCellValueFactory(new PropertyValueFactory<>("sPassword"));
		tcTchSPassword.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchPhone = adminTchSrchStuTV.getColumns().get(3);
		tcTchPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcTchPhone.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchGender = adminTchSrchStuTV.getColumns().get(4);
		tcTchGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		tcTchGender.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchBirthDate = adminTchSrchStuTV.getColumns().get(5);
		tcTchBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		tcTchBirthDate.setStyle("-fx-alignment: CENTER;");

		adminTchSrchStuTV.setItems(obStuAllList);

	}
	private void initTeacherTableView() {

		TableColumn tcTName = tchAllTV.getColumns().get(0);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTeacherID = tchAllTV.getColumns().get(1);
		tcTeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
		tcTeacherID.setStyle("-fx-alignment: CENTER;");

		TableColumn tTPassword = tchAllTV.getColumns().get(2);
		tTPassword.setCellValueFactory(new PropertyValueFactory<>("tPassword"));
		tTPassword.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPhone = tchAllTV.getColumns().get(3);
		tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcPhone.setStyle("-fx-alignment: CENTER;");

		TableColumn tcGender = tchAllTV.getColumns().get(4);
		tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		tcGender.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBirthDate = tchAllTV.getColumns().get(5);
		tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
		tcBirthDate.setStyle("-fx-alignment: CENTER;");

		// dataBase와 연동하는 것
		dbTchList = TeacherDAO.getTeacherTotalData();
		for (Teacher tch : dbTchList) {
			obTchAllList.add(tch);
			obCmbTchIDList.add(tch.getTeacherID());
		}
		tchAllTV.setItems(obTchAllList);

	}
	private void initPaymentTableView() {
		TableColumn tcStudentID = stuSrchPaymentTV.getColumns().get(0);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");

		TableColumn tcSName = stuSrchPaymentTV.getColumns().get(1);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcCourse = stuSrchPaymentTV.getColumns().get(2);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTName = stuSrchPaymentTV.getColumns().get(3);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPeriod = stuSrchPaymentTV.getColumns().get(4);
		tcPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
		tcPeriod.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLessons = stuSrchPaymentTV.getColumns().get(5);
		tcLessons.setCellValueFactory(new PropertyValueFactory<>("lessons"));
		tcLessons.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLeftLes = stuSrchPaymentTV.getColumns().get(6);
		tcLeftLes.setCellValueFactory(new PropertyValueFactory<>("leftLes"));
		tcLeftLes.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPOption = stuSrchPaymentTV.getColumns().get(7);
		tcPOption.setCellValueFactory(new PropertyValueFactory<>("pOption"));
		tcPOption.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTuition = stuSrchPaymentTV.getColumns().get(8);
		tcTuition.setCellValueFactory(new PropertyValueFactory<>("tuition"));
		tcTuition.setStyle("-fx-alignment: CENTER;");

		// PAYMENT dataBase와 연동하는 것
		dbPmtList = PaymentDAO.getPaymentTotalData();
		for (Payment pmt : dbPmtList) {
			obPmtList.add(pmt);
		}
		stuSrchPaymentTV.setItems(obPmtList);

	}
	private void initClassTableView() {

		TableColumn tcClassDate = adminClassTV.getColumns().get(0);
		tcClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClassTime = adminClassTV.getColumns().get(1);
		tcClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

		TableColumn tcCourse = adminClassTV.getColumns().get(2);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcCType = adminClassTV.getColumns().get(3);
		tcCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcCType.setStyle("-fx-alignment: CENTER;");
		tcClassTime.setStyle("-fx-alignment: CENTER;");
		TableColumn tcClassRoom = adminClassTV.getColumns().get(4);
		tcClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcClassRoom.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTName = adminClassTV.getColumns().get(5);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLimitNum = adminClassTV.getColumns().get(6);
		tcLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcNowNum = adminClassTV.getColumns().get(7);
		tcNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcNowNum.setStyle("-fx-alignment: CENTER;");

///////RE: 과거 이력까지 모두 가져오기// CLASSES dataBase와 연동하는 것
		ArrayList<Classes> dbClsViewList1 = new ArrayList<>();
		dbClsViewList1 = ClassDAO.getClassesTotalData();
		ObservableList<Classes> list = FXCollections.observableArrayList();

		for (Classes cls : dbClsViewList1) {
			list.add(cls);
		}
		adminClassTV.setItems(list);

		TableColumn tcTchClassDate = adminTchSrchClsTV.getColumns().get(0);
		tcTchClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcTchClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchClassTime = adminTchSrchClsTV.getColumns().get(1);
		tcTchClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

		TableColumn tcTchCourse = adminTchSrchClsTV.getColumns().get(2);
		tcTchCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcTchCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchCType = adminTchSrchClsTV.getColumns().get(3);
		tcTchCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcTchCType.setStyle("-fx-alignment: CENTER;");
		tcTchClassTime.setStyle("-fx-alignment: CENTER;");
		TableColumn tcTchClassRoom = adminTchSrchClsTV.getColumns().get(4);
		tcTchClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcTchClassRoom.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchTName = adminTchSrchClsTV.getColumns().get(5);
		tcTchTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTchTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchLimitNum = adminTchSrchClsTV.getColumns().get(6);
		tcTchLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcTchLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchNowNum = adminTchSrchClsTV.getColumns().get(7);
		tcTchNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcTchNowNum.setStyle("-fx-alignment: CENTER;");

		// Tch Search Others의 TABLEVIEW
		adminTchSrchClsTV.setItems(list);

		TableColumn tcClsClassDate = adminClsAddTV.getColumns().get(0);
		tcClsClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcClsClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsClassTime = adminClsAddTV.getColumns().get(1);
		tcClsClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));
		tcClsClassTime.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsCourse = adminClsAddTV.getColumns().get(2);
		tcClsCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcClsCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsCType = adminClsAddTV.getColumns().get(3);
		tcClsCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcClsCType.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsClassRoom = adminClsAddTV.getColumns().get(4);
		tcClsClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcClsClassRoom.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsLimitNum = adminClsAddTV.getColumns().get(5);
		tcClsLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcClsLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsNowNum = adminClsAddTV.getColumns().get(6);
		tcClsNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcClsNowNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClsTName = adminClsAddTV.getColumns().get(7);
		tcClsTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcClsTName.setStyle("-fx-alignment: CENTER;");

		obCalenderClsViewList.clear();
		ArrayList<Classes> cList = ClassDAO.getClassesDataFromCurDate();
		for (Classes cls : cList) {
			obCalenderClsViewList.add(cls);
		}
		adminClsAddTV.setItems(obCalenderClsViewList);
	}
	private void initClassMemberTableView() {

		TableColumn tcSName = adminClassMemberTV.getColumns().get(0);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcStudentID = adminClassMemberTV.getColumns().get(1);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchSName = adminTchSrchCMmbTV.getColumns().get(0);
		tcTchSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcTchSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTchStudentID = adminTchSrchCMmbTV.getColumns().get(1);
		tcTchStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcTchStudentID.setStyle("-fx-alignment: CENTER;");

	}
	private void initStuHealthTableView() {
		TableColumn tcStudentID = stuSrchHealthTV.getColumns().get(0);
		tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
		tcStudentID.setStyle("-fx-alignment: CENTER;");

		TableColumn tcSName = stuSrchHealthTV.getColumns().get(1);
		tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		tcSName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcMesureDate = stuSrchHealthTV.getColumns().get(2);
		tcMesureDate.setCellValueFactory(new PropertyValueFactory<>("measureDate"));
		tcMesureDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcWeight = stuSrchHealthTV.getColumns().get(3);
		tcWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		tcWeight.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBodyfat = stuSrchHealthTV.getColumns().get(4);
		tcBodyfat.setCellValueFactory(new PropertyValueFactory<>("bodyfat"));
		tcBodyfat.setStyle("-fx-alignment: CENTER;");

		TableColumn tcMuscleMass = stuSrchHealthTV.getColumns().get(5);
		tcMuscleMass.setCellValueFactory(new PropertyValueFactory<>("muscleMass"));
		tcMuscleMass.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBmi = stuSrchHealthTV.getColumns().get(6);
		tcBmi.setCellValueFactory(new PropertyValueFactory<>("bmi"));
		tcBmi.setStyle("-fx-alignment: CENTER;");

		// HealthTbl dataBase와 연동하는 것
		dbHealthList = HealthDAO.getHealthTotalData();
		for (Health hlth : dbHealthList) {
			obStuHealthList.add(hlth);
		}
		stuSrchHealthTV.setItems(obStuHealthList);

	}
	private void initComboBoxes() {

		obNClsTimeList.addAll("07:00-07:50", "08:00-08:50", "09:00-09:50", "10:00-10:50", "11:00-11:50", "18:00-18:50",
				"19:00-19:50", "20:00-20:50", "21:00-21:50", "22:00-22:50");
		obNClsCouseList.addAll("개인", "그룹");
		obNClsTypeList.addAll("리포머", "매트");
		obNClsRoomList.addAll("A", "B", "C", "D");
		obYearList.addAll("2018년", "2019년");
		obQuarterList.addAll("1분기", "2분기", "3분기", "4분기", "선택안함");
		chrtCmbYear.setItems(obYearList);
		chrtCmbQuarter.setItems(obQuarterList);
		cmbBoxTchName.setItems(obCmbTchIDList);
		cmbBoxTchYear.getItems().addAll("2018", "2019");

	}
	// 1. [회원 MENU]
	private void handleStuAll() {
		initPane();
		adminStuAll.setVisible(true);
		lblCurrentMenu.setText("STUDENT INFROMATION");
	}
	// 1.1 회원 - 삭제 -Dialog
	private void handleDeleteStu(MouseEvent e) {
		if (e.getClickCount() == 2) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/double_check_delete.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage delStuStage = new Stage(StageStyle.UTILITY);

				// **********************double_check_delete.fxml id
				// 등록****************************
				TextField delDblTxtName = (TextField) root.lookup("#delDblTxtName");
				TextField delDblTxtStuID = (TextField) root.lookup("#delDblTxtID");
				TextField delDblBtnBack = (TextField) root.lookup("#delDblBtnBack");
				TextField delDblBtnDelete = (TextField) root.lookup("#delDblBtnDelete");

				// **********************이벤트 등록 및 초기화와 핸들러정의****************************
				selectedStu = stuAllStuTV.getSelectionModel().getSelectedItem();
				delDblTxtName.setText(selectedStu.getSName());
				delDblTxtName.setFocusTraversable(false);
				delDblTxtStuID.setText(selectedStu.getStudentID());
				delDblTxtStuID.setFocusTraversable(false);
				delDblBtnBack.setFocusTraversable(false);
				delDblBtnDelete.setFocusTraversable(false);

				// 1.1.1 삭제 버튼
				delDblBtnDelete.setOnMouseClicked(e1 -> {
					int count = StudentDAO.deleteStudent(selectedStu);
					if (count != 0) {
						obStuAllList.remove(selectedStu);
						delStuStage.close();
						AdminController.callAlert("STUDENT ACCOUNT DELETE SUCCEED : StudentID "
								+ selectedStu.getStudentID() + "\t" + selectedStu.getSName() + "님의 계정삭제완료");
					}
				});

				// 1.1.2.뒤로가기 버튼
				delDblBtnBack.setOnMouseClicked(e1 -> {
					delStuStage.close();
				});

				delStuStage.initOwner(adminStage);
				delStuStage.initModality(Modality.WINDOW_MODAL);
				delStuStage.setTitle("회원계정 삭제");
				delStuStage.setScene(scene);
				delStuStage.show();
			} catch (IOException e2) {
				AdminController.callAlert("회원계정 삭제 창 오류 : 회원계정 삭제 오류발생!! 확인해주세요");
			}
		}
//////////////////////////
		Student stu = stuAllStuTV.getSelectionModel().getSelectedItem();

		String url = "file:///" + DIR.getAbsolutePath() + "/" + stu.getImage();
		img.setImage(new Image(url));
		sssName.setText(stu.getSName());

	}
	// 1.2 회원기본정보 - 검색
	private void handleStuInfoSearch() {
		String searchName = stuAllSearch.getText().trim();
		// 공백이 있는 경우
		spaceCheck(searchName);
		ObservableList<Student> stuNameSearchList = StudentDAO.searchByName(searchName);
		stuAllSearch.clear();
		int size = stuNameSearchList.size();
		switch (size) {
		case 0:
			callAlert(searchName + "님의 검색결과가 없습니다. : 검색 결과, 회원계정에 등록되어있지 않은 이름입니다.");
			break;
		default:
			Parent sameNameRoot = null;
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/same_name_stu.fxml"));
				sameNameRoot = fxmlLoader.load();
				Stage sameNameStage = new Stage();
				TableView<Student> sameNstuTV = (TableView) sameNameRoot.lookup("#sameNstuTV");
				Label sameNstuName = (Label) sameNameRoot.lookup("#sameNstuName");
				ImageView sameNExit = (ImageView) sameNameRoot.lookup("#sameNExit");

				// same_name_stu.fxml TableView
				TableColumn tcSName = sameNstuTV.getColumns().get(0);
				tcSName.setCellValueFactory(new PropertyValueFactory<>("sName"));
				tcSName.setStyle("-fx-alignment: CENTER;");

				TableColumn tcStudentID = sameNstuTV.getColumns().get(1);
				tcStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
				tcStudentID.setStyle("-fx-alignment: CENTER;");

				TableColumn tcSPassword = sameNstuTV.getColumns().get(2);
				tcSPassword.setCellValueFactory(new PropertyValueFactory<>("sPassword"));
				tcSPassword.setStyle("-fx-alignment: CENTER;");

				TableColumn tcPhone = sameNstuTV.getColumns().get(3);
				tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
				tcPhone.setStyle("-fx-alignment: CENTER;");

				TableColumn tcGender = sameNstuTV.getColumns().get(4);
				tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
				tcGender.setStyle("-fx-alignment: CENTER;");

				TableColumn tcBirthDate = sameNstuTV.getColumns().get(5);
				tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
				tcBirthDate.setStyle("-fx-alignment: CENTER;");

				sameNstuTV.setItems(stuNameSearchList);

				// Label에 이름 Setting
				sameNstuName.setText(searchName);

				// EXIT
				sameNExit.setOnMouseClicked(e -> sameNameStage.close());

				Scene scene = new Scene(sameNameRoot);
				scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
				sameNameStage.setScene(scene);
				sameNameStage.show();
			} catch (IOException e) {

			}
			break;
		}

	}
	// 2. [회원 MENU_OTHERS]
	private void handleStuSearchOthers() {
		String searchName = stuSrchTxt.getText().trim();
		// 검색결과에 따른것
		spaceCheck(searchName);

		ObservableList<Payment> obStuSrchPaymentList = PaymentDAO.searchPaymentSelectedData(searchName);
		stuSrchPaymentTV.refresh();
		stuSrchPaymentTV.setItems(obStuSrchPaymentList);

		ObservableList<Health> stuSrchOtherHealthList = HealthDAO.getHealthSelectedData(searchName);
		stuSrchHealthTV.refresh();
		stuSrchHealthTV.setItems(stuSrchOtherHealthList);

		stuSrchTxt.clear();

	}
	// 2.회원정보 -검색
	private void handleStuSearch() {
		initPane();
		adminStuSearch.setVisible(true);
		lblCurrentMenu.setText("STUDENT INFROMATION");
	}
	// 3. [강사 MENU] 
	private void handleTchAll() {
		initPane();
		adminTchAll.setVisible(true);
		lblCurrentMenu.setText("TEACHER INFROMATION");
	}
	// 3.1. 강사정보 - 삭제
	private void handleTchDelete(MouseEvent e) {
		if (e.getClickCount() == 2) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/double_check_delete.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage delTchStage = new Stage(StageStyle.UTILITY);

				// **********************double_check_delete.fxml id
				// 등록****************************
				TextField delDblTxtName = (TextField) root.lookup("#delDblTxtName");
				TextField delDblTxtTchID = (TextField) root.lookup("#delDblTxtID");
				TextField delDblBtnBack = (TextField) root.lookup("#delDblBtnBack");
				TextField delDblBtnDelete = (TextField) root.lookup("#delDblBtnDelete");

				// **********************이벤트 등록 및 초기화와 핸들러정의****************************
				selectedTch = tchAllTV.getSelectionModel().getSelectedItem();
				delDblTxtName.setText(selectedTch.getTName());
				delDblTxtName.setFocusTraversable(false);
				delDblTxtTchID.setText(selectedTch.getTeacherID());
				delDblTxtTchID.setFocusTraversable(false);
				delDblBtnBack.setFocusTraversable(false);
				delDblBtnDelete.setFocusTraversable(false);

				// 3.1.1 삭제 버튼
				delDblBtnDelete.setOnMouseClicked(e1 -> {
					int count = TeacherDAO.deleteTeacher(selectedTch);
					if (count != 0) {
						obTchAllList.remove(selectedTch);

						delTchStage.close();
						AdminController.callAlert("TEACHER ACCOUNT DELETE SUCCEED : TeacherID "
								+ selectedTch.getTeacherID() + "\t" + selectedTch.getTName() + "님의 계정삭제완료");
					}
				});

				// 3.1.2.뒤로가기 버튼
				delDblBtnBack.setOnMouseClicked(e1 -> {
					delTchStage.close();
				});

				delTchStage.initOwner(adminStage);
				delTchStage.initModality(Modality.WINDOW_MODAL);
				delTchStage.setTitle("강사계정 삭제");
				delTchStage.setScene(scene);
				delTchStage.show();
			} catch (IOException e2) {
				AdminController.callAlert("강사계정 삭제 창 오류 : 강사계정 삭제 오류발생!! 확인해주세요");
			}

		}
	}
	// 3.2 강사 - 검색
	private void handleTchInfoSearch() {
		String searchName = tchAllSearch.getText().trim();
		tchAllSearch.clear();
		// 공백이 있는 경우
		spaceCheck(searchName);
		ObservableList<Teacher> tchNameSearchList = TeacherDAO.searchSelectedTchData(searchName);

		int size = tchNameSearchList.size();
		switch (size) {
		case 0:
			callAlert(searchName + "님의 검색결과가 없습니다. : 검색 결과, 선생님 계정에 등록되어있지 않은 이름입니다.");
			break;
		default:
			Parent sameNameRoot = null;
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/same_name_tch.fxml"));
				sameNameRoot = fxmlLoader.load();
				Stage sameNameStage = new Stage();
				TableView<Teacher> sameNtchTV = (TableView) sameNameRoot.lookup("#sameNTchTV");
				Label sameNTchName = (Label) sameNameRoot.lookup("#sameNTchName");
				ImageView sameNTchBtnExit = (ImageView) sameNameRoot.lookup("#sameNTchBtnExit");

				TableColumn tcTName = sameNtchTV.getColumns().get(0);
				tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
				tcTName.setStyle("-fx-alignment: CENTER;");

				TableColumn tcTeacherID = sameNtchTV.getColumns().get(1);
				tcTeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
				tcTeacherID.setStyle("-fx-alignment: CENTER;");

				TableColumn tcTPassword = sameNtchTV.getColumns().get(2);
				tcTPassword.setCellValueFactory(new PropertyValueFactory<>("tPassword"));
				tcTPassword.setStyle("-fx-alignment: CENTER;");

				TableColumn tcPhone = sameNtchTV.getColumns().get(3);
				tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
				tcPhone.setStyle("-fx-alignment: CENTER;");

				TableColumn tcGender = sameNtchTV.getColumns().get(4);
				tcGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
				tcGender.setStyle("-fx-alignment: CENTER;");

				TableColumn tcBirthDate = sameNtchTV.getColumns().get(5);
				tcBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
				tcBirthDate.setStyle("-fx-alignment: CENTER;");

				sameNtchTV.setItems(tchNameSearchList);

				// Label에 이름 Setting
				sameNTchName.setText(searchName);
				// EXIT
				sameNTchBtnExit.setOnMouseClicked(e -> sameNameStage.close());

				Scene scene = new Scene(sameNameRoot);
				scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
				sameNameStage.setScene(scene);
				sameNameStage.setTitle("강사 검색");
				sameNameStage.show();
			} catch (IOException e) {

			}
			break;
		}
	}
	// 4. [강사 MENU_OTHERS]
	private void handleTchSearch() {
		initPane();
		adminTchSearch.setVisible(true);
		lblCurrentMenu.setText("TEACHER INFROMATION");
	}
	// 4.2 강사정보 - Ohters -Student- 검색창
	private void handleTchSrchTxtAction() {
		String searchID = tchSrchTxt.getText().trim();
		// 검색결과에 따른것
		spaceCheck(searchID);

		ObservableList<Classes> obStuSrchClsList = ClassDAO.searchSelectedClsData(searchID);
		adminTchSrchClsTV.refresh();
		adminTchSrchCMmbTV.refresh();
		adminTchSrchClsTV.setItems(obStuSrchClsList);

		ObservableList<Student> obTchSrchStuList = StudentDAO.searchSelectedStuData(searchID);
		adminTchSrchStuTV.refresh();
		adminTchSrchStuTV.setItems(obTchSrchStuList);

		tchSrchTxt.clear();
	}
	// 5. [강의 MENU]
	private void handleTchSrchClsTVClickAction() {
		selectedCls = adminTchSrchClsTV.getSelectionModel().getSelectedItem();

		ArrayList<ClassMember> cMemberList = new ArrayList<>();
		ObservableList<ClassMember> obCMemberList = FXCollections.observableArrayList();
		// CMEMBERTBL dataBase와 연동하는 것
		cMemberList = ClassDAO.getCMemberTotalData(selectedCls.getClassInfo());
		for (ClassMember cMmb : cMemberList) {
			obCMemberList.add(cMmb);
		}
		adminTchSrchCMmbTV.setItems(obCMemberList);

	}
	// 5. 강의정보
	private void handleClsView() {
		initPane();
		adminClassView.setVisible(true);
		lblCurrentMenu.setText("CLASS INFROMATION");
	}
	// 5. [강의 MENU_VIEW] - classTbl -> cMemberTbl
	private void handleAdminClassTVClickAction(MouseEvent e) {
		selectedCls = adminClassTV.getSelectionModel().getSelectedItem();

		ArrayList<ClassMember> cMemberList = new ArrayList<>();
		ObservableList<ClassMember> obCMemberList = FXCollections.observableArrayList();
		// CMEMBERTBL dataBase와 연동하는 것
		cMemberList = ClassDAO.getCMemberTotalData(selectedCls.getClassInfo());
		for (ClassMember cMmb : cMemberList) {
			obCMemberList.add(cMmb);
		}
		adminClassMemberTV.setItems(obCMemberList);

//		ClassDAO.deleteClass(selectedCls);
		if (e.getClickCount() == 2) {
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
				// [INITIALIZE] ComboBoxes & TableView

				delDblTxtName.setText(selectedCls.getClassInfo());
				delDblTxtID.setText(selectedCls.getTName());

				// 1.1.1 삭제 버튼
				delDblBtnDelete.setOnMouseClicked(e1 -> {
					int count = ClassDAO.deleteClass(selectedCls);
					if (count != 0) {
						// ClassDAO.deleteClass(selectedCls);
						newClassStage.close();
						AdminController.callAlert(
								"[CLASS DELETE SUCCEED] : ClassInfo  " + selectedCls.getClassInfo() + "\t" + "수업 삭제완료");
						initClassTableView();

					}
				});

				// 1.1.2.뒤로가기 버튼
				delDblBtnBack.setOnMouseClicked(e1 -> {
					newClassStage.close();
				});

				newClassStage.initOwner(adminStage);
				newClassStage.initModality(Modality.WINDOW_MODAL);
				newClassStage.setTitle("DELETE CLASS");
				newClassStage.setScene(scene);
				newClassStage.show();
			} catch (IOException e2) {
				AdminController.callAlert("삭제 창 오류 : 삭제 오류발생!! 확인해주세요");
			}

		}

	}
	// 5. 강의정보 -> 날짜로 검색
	private void handleAdminClsSrchAction() {
		adminClassMemberTV.refresh();
		String selectedDate = adminClsSrchDate.getValue().toString();
		ObservableList<Classes> dbClsByDateList = ClassDAO.selectClassDataByDate(selectedDate);
		adminClassTV.setItems(dbClsByDateList);

	}
	// 6. [강의 MENU_ADD] 
	private void handleClsNew() {
		initPane();
		adminClassAdd.setVisible(true);
		lblCurrentMenu.setText("CLASS INFROMATION");
	}
	// 6.1 Add new Class (Register Class)
	private void initCalender() {
		stuDateArray = new TextField[] { stuDate10, stuDate11, stuDate12, stuDate13, stuDate14, stuDate15, stuDate16,
				stuDate20, stuDate21, stuDate22, stuDate23, stuDate24, stuDate25, stuDate26, stuDate30, stuDate31,
				stuDate32, stuDate33, stuDate34, stuDate35, stuDate36, stuDate40, stuDate41, stuDate42, stuDate43,
				stuDate44, stuDate45, stuDate46, stuDate50, stuDate51, stuDate52, stuDate53, stuDate54, stuDate55,
				stuDate56, stuDate60, stuDate61, stuDate62, stuDate63, stuDate64, stuDate65, stuDate66 };

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
			stuDateArray[i].setText("" + j);
			stuDateArray[i].setStyle("-fx-cursor: hand;");

			final int ii = i;
			stuDateArray[i].setOnMouseClicked(e -> {
				String month1 = "";
				if (month < 10) {
					month1 = "0" + month;
				}
				String day1 = "";
				if (Integer.parseInt(stuDateArray[ii].getText()) < 10) {
					day1 = "0" + stuDateArray[ii].getText();
				} else {
					day1 = stuDateArray[ii].getText();

				}
				String date = year + "-" + month1 + "-" + day1;

				if (e.getClickCount() == 2) {
					handleClsAddAction(date);

				} else if (e.getClickCount() == 1) {
					ObservableList<Classes> dbClsByDateList = ClassDAO.selectClassDataByDate(date);
					adminClsAddTV.setItems(dbClsByDateList);
					if (jj != ii) {
						stuDateArray[ii].setStyle("-fx-background-color: orange");
						stuDateArray[jj].setStyle("-fx-background-color: white");
					}
					jj = ii;
				}

			});

		}

	}
	// 6.1 Add new Class (Register Class)
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
				adminStage.show();
			});

			// 6.1.1[ACTION] Add new Class (Register Class)
			nClsRegister.setOnMouseClicked(e -> {
				ClassDAO.insertClass(nClsTeacherTV.getSelectionModel().getSelectedItem().getTeacherID(),
						nClsCourse.getSelectionModel().getSelectedItem(),
						nClsType.getSelectionModel().getSelectedItem(), nClsDate.getValue().toString(),
						nClsTime.getSelectionModel().getSelectedItem(), nClsRoom.getSelectionModel().getSelectedItem());
				initClassTableView();
				newClassStage.close();

			});
			newClassStage.initOwner(adminStage);
			newClassStage.initModality(Modality.WINDOW_MODAL);
			newClassStage.setTitle("ADD NEW CLASS");
			newClassStage.setScene(scene);
			newClassStage.show();
		} catch (IOException e2) {
			AdminController.callAlert("회원계정 삭제 창 오류 : 회원계정 삭제 오류발생!! 확인해주세요");
		}

	}
	// 6.1.1 view images
	private void handleClsAddTvAction() {
		Classes cls = adminClsAddTV.getSelectionModel().getSelectedItem();
		String url = "file:///" + DIR.getAbsolutePath() + "/" + cls.getTImage();
		adminClsAddImg.setImage(new Image(url));
	}
	// 6.2 Edit a Class
	private void handleClsEditAction() {
		Classes cls = adminClsAddTV.getSelectionModel().getSelectedItem();
		if (cls == null) {
			AdminController.callAlert("[수업 미선택] : 수정할 수업을 선택해주세요.");
		} else {

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit_class.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
				Stage newClassStage = new Stage(StageStyle.UTILITY);

				// **********************new_class.fxml id등록****************************
				ImageView joinTesting = (ImageView) root.lookup("#joinTesting");
				DatePicker nClsDate = (DatePicker) root.lookup("#edClsDate");
				ComboBox<String> nClsTime = (ComboBox<String>) root.lookup("#edClsTime");
				ComboBox<String> nClsType = (ComboBox<String>) root.lookup("#edClsCType");
				ComboBox<String> nClsCourse = (ComboBox<String>) root.lookup("#edClsCourse");
				ComboBox<String> nClsRoom = (ComboBox<String>) root.lookup("#edClsRoom");
				TableView<Teacher> nClsTeacherTV = (TableView<Teacher>) root.lookup("#edClsTeacherTV");
				TextField nClsRegister = (TextField) root.lookup("#edClsRegister");
				TextField nClsExit = (TextField) root.lookup("#edClsExit");
				// **********************이벤트 등록 및 초기화와 핸들러정의****************************
				// [INITIALIZE] ComboBoxes & TableView
				TableColumn tcTeacherID = nClsTeacherTV.getColumns().get(0);
				tcTeacherID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
				tcTeacherID.setStyle("-fx-alignment: CENTER;");

				TableColumn tcTName = nClsTeacherTV.getColumns().get(1);
				tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
				tcTName.setStyle("-fx-alignment: CENTER;");

				nClsTeacherTV.setItems(obTchAllList);
				nClsDate.setValue(LocalDate.parse(cls.getClassDate().toString()));

				////////// Teacher tch = TeacherDAO.getTchNameDataByTID(cls.getTeacherID());

				nClsTime.setItems(obNClsTimeList);
				nClsTime.getSelectionModel().select(cls.getClassTime());
				nClsType.setItems(obNClsTypeList);
				nClsType.getSelectionModel().select(cls.getCType());
				nClsCourse.setItems(obNClsCouseList);
				nClsCourse.getSelectionModel().select(cls.getCourse());
				nClsRoom.setItems(obNClsRoomList);
				nClsRoom.getSelectionModel().select(cls.getClassRoom());

				// 6.1.1[ACTION] Exit
				nClsExit.setOnMouseClicked(e -> {
					newClassStage.close();
					adminStage.show();
				});

				nClsCourse.setOnAction(e -> {
					if (nClsCourse.getSelectionModel().getSelectedItem().equals("그룹")) {
						nClsTeacherTV.setDisable(true);
					} else {
						nClsTeacherTV.setDisable(false);
					}
				});

				// 6.1.1[ACTION] Edit Class (Edit Class)
				nClsRegister.setOnMouseClicked(e -> {
					String teacher = null;
					if (nClsTeacherTV.getSelectionModel().getSelectedItem() == null) {
						AdminController.callAlert("[강사 미선택]: 강사를 선택해주세요.");
					}
					System.out.println(nClsTime.getSelectionModel().getSelectedItem().toString());
					String type = nClsType.getSelectionModel().getSelectedItem();
					System.out.println("type" + type);
					String room = nClsRoom.getSelectionModel().getSelectedItem();
					System.out.println("room" + room);
					teacher = nClsTeacherTV.getSelectionModel().getSelectedItem().getTeacherID();
					System.out.println("teacher" + teacher);
					System.out.println(nClsDate.getValue().toString());
					ClassDAO.updateClassInfo(cls.getClassInfo(), nClsCourse.getSelectionModel().getSelectedItem(), type,
							teacher);

					initClassTableView();

					newClassStage.close();

				});
				newClassStage.initOwner(adminStage);
				newClassStage.initModality(Modality.WINDOW_MODAL);
				newClassStage.setTitle("ADD NEW CLASS");
				newClassStage.setScene(scene);
				newClassStage.show();
			} catch (IOException e2) {
				AdminController.callAlert("회원계정 삭제 창 오류 : 회원계정 삭제 오류발생!! 확인해주세요");
			}
		}

	}
	// 7. [CHART MENU]
	private void handleMenuChart() {
		lblCurrentMenu.setText("SALES REVENUES");
		initPane();
		adminSalesChart.setVisible(true);
	}
	private void handleBtnTchYearChart() {
		String yyyy = cmbBoxTchYear.getSelectionModel().getSelectedItem();

		lineChrtTch.setTitle(yyyy + "년도 강사별 매출현황");
		lineChrtTch.setAnimated(false);
		lineChrtTch.getData().clear();

		System.out.println("obCmbTchIDList size()()()" + obCmbTchIDList.size());
		System.out.println("yyyy: " + yyyy);
		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		series1.setName("1분기");
		ObservableList<Data<String, Integer>> list1 = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
		series2.setName("2분기");
		ObservableList<Data<String, Integer>> list2 = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
		series3.setName("3분기");
		ObservableList<Data<String, Integer>> list3 = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> series4 = new XYChart.Series<>();
		series4.setName("4분기");
		ObservableList<Data<String, Integer>> list4 = FXCollections.observableArrayList();

		// obCmbTchIDList.size()-1 => teacherID가 '그룹'인 것은 제외 한다.
		for (int i = 0; i < obCmbTchIDList.size() - 1; i++) {
			Data dd1 = PaymentDAO.getPmtDataQuarteryByT(obCmbTchIDList.get(i), yyyy, "1분기");
			list1.add(dd1);
			Data dd2 = PaymentDAO.getPmtDataQuarteryByT(obCmbTchIDList.get(i), yyyy, "2분기");
			list2.add(dd2);
			Data dd3 = PaymentDAO.getPmtDataQuarteryByT(obCmbTchIDList.get(i), yyyy, "3분기");
			list3.add(dd3);
			Data dd4 = PaymentDAO.getPmtDataQuarteryByT(obCmbTchIDList.get(i), yyyy, "4분기");
			list4.add(dd4);
		}

		series1.setData(list1);
		lineChrtTch.getData().add(series1);
		series2.setData(list2);
		lineChrtTch.getData().add(series2);
		series3.setData(list3);
		lineChrtTch.getData().add(series3);
		series4.setData(list4);
		lineChrtTch.getData().add(series4);

	}
	private void handleBtnQuarterChart() {
		String yyyy = chrtCmbYear.getSelectionModel().getSelectedItem();
		String qq = chrtCmbQuarter.getSelectionModel().getSelectedItem();
		int qValue = 0;
		String m1 = null;
		String m2 = null;
		String m3 = null;
		System.out.println("qq" + qq.substring(0, 1));

		switch (qq.substring(0, 1)) {
		case "1":
			m1 = "01";
			m2 = "02";
			m3 = "03";

			break;
		case "2":
			m1 = "04";
			m2 = "05";
			m3 = "06";

			break;
		case "3":
			m1 = "07";
			m2 = "08";
			m3 = "09";
			m2 = "10";
			break;
		case "4":
			m1 = "10";
			m2 = "11";
			m3 = "12";
			break;
		}
		if (qq.equals("선택안함")) {

			lineChrtQuater.setTitle(yyyy + " 매출현황");
			XYChart.Series<String, Integer> series = new XYChart.Series<>();
			lineChrtQuater.getData().clear();
			// populating the series with data
			series.getData().add(new XYChart.Data<String, Integer>("1월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "01")));
			series.getData().add(new XYChart.Data<String, Integer>("2월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "02")));
			series.getData().add(new XYChart.Data<String, Integer>("3월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "03")));
			series.getData().add(new XYChart.Data<String, Integer>("4월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "04")));
			series.getData().add(new XYChart.Data<String, Integer>("5월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "05")));
			series.getData().add(new XYChart.Data<String, Integer>("6월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "06")));
			series.getData().add(new XYChart.Data<String, Integer>("7월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "07")));
			series.getData().add(new XYChart.Data<String, Integer>("8월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "08")));
			series.getData().add(new XYChart.Data<String, Integer>("9월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "09")));
			series.getData().add(new XYChart.Data<String, Integer>("10월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "10")));
			series.getData().add(new XYChart.Data<String, Integer>("11월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "11")));
			series.getData().add(new XYChart.Data<String, Integer>("12월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), "12")));

			lineChrtQuater.getData().add(series);

		} else {
			// 분기를 선택하면 나오는 것
			lineChrtQuater.setTitle(yyyy + " " + qq + " 매출현황");
			XYChart.Series<String, Integer> series = new XYChart.Series<>();
			lineChrtQuater.getData().clear();
			// populating the series with data
			series.getData().add(new XYChart.Data<String, Integer>(m1 + "월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), m1)));
			series.getData().add(new XYChart.Data<String, Integer>(m2 + "월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), m2)));
			series.getData().add(new XYChart.Data<String, Integer>(m3 + "월",
					PaymentDAO.getSalesRevenueByMM(yyyy.substring(0, 4), m3)));
			lineChrtQuater.getData().add(series);

		}

	}
	private void handleBtnTchSrchChart() {

		tch = TeacherDAO.getTchNameDataByTID(cmbBoxTchName.getSelectionModel().getSelectedItem());

		String yyyy = cmbBoxTchYear.getSelectionModel().getSelectedItem();
		if (yyyy == null) {
			callAlert("연도 선택 없음 : 원하시는 연도를 선택해주세요");
		}

		lineChrtTch.setTitle(yyyy + "년도 " + tch.getTName() + "님의 매출현황");
		lineChrtTch.setAnimated(false);
		lineChrtTch.getData().clear();

		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		series1.setName("1분기");
		ObservableList<Data<String, Integer>> list1 = FXCollections.observableArrayList();

		XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
		series2.setName("2분기");
		ObservableList<Data<String, Integer>> list2 = FXCollections.observableArrayList();

		XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
		series3.setName("3분기");
		ObservableList<Data<String, Integer>> list3 = FXCollections.observableArrayList();

		XYChart.Series<String, Integer> series4 = new XYChart.Series<>();
		series4.setName("4분기");
		ObservableList<Data<String, Integer>> list4 = FXCollections.observableArrayList();

		Data dd1 = PaymentDAO.getPmtDataQuarteryByT(tch.getTeacherID(), yyyy, "1분기");
		list1.add(dd1);
		Data dd2 = PaymentDAO.getPmtDataQuarteryByT(tch.getTeacherID(), yyyy, "2분기");
		list2.add(dd2);
		Data dd3 = PaymentDAO.getPmtDataQuarteryByT(tch.getTeacherID(), yyyy, "3분기");
		list3.add(dd3);
		Data dd4 = PaymentDAO.getPmtDataQuarteryByT(tch.getTeacherID(), yyyy, "4분기");
		list4.add(dd4);

		series1.setData(list1);
		lineChrtTch.getData().add(series1);
		series2.setData(list2);
		lineChrtTch.getData().add(series2);
		series3.setData(list3);
		lineChrtTch.getData().add(series3);
		series4.setData(list4);
		lineChrtTch.getData().add(series4);
	}
	// 8. [고객상담 MENU]
	private void ssssserverAction() {
		try {
			Stage serverStage = new Stage();
			serverStage.setTitle("고객 상담 채팅창");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/root.fxml"));
			Parent root = loader.load();
			ServerController serverController = loader.getController();
			serverController.serverStage = serverStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/chat.css").toString());
			serverStage.setScene(scene);
			// adminStage.close();
			serverStage.show();

			// AdminController.callAlert("고객 상담 채팅방입니다. : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("화면 전환 오류 : 화면 전환에 오류 발생");
		}
	}
	// [LOGOUT]
	private void handleLogoutAction() {
		try {
			Stage stuLoginStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/log.fxml"));
			Parent root = loader.load();
			RootController loginController = loader.getController();
			loginController.primaryStage = stuLoginStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/login.css").toString());
			stuLoginStage.setScene(scene);
			stuLoginStage.setTitle("LOGIN");
			adminStage.close();
			stuLoginStage.show();
			// AdminController.callAlert("화면 전환 성공 : 화면 전환에 성공 ");
		} catch (Exception e) {
			AdminController.callAlert("화면 전환 오류 : 화면 전환에 오류 발생");
		}

	}
	// 기타 : 화면 default visible
	private void initPane() {
		adminStuAll.setVisible(false);
		adminStuSearch.setVisible(false);
		adminTchAll.setVisible(false);
		adminTchSearch.setVisible(false);
		adminClassView.setVisible(false);
		adminClassAdd.setVisible(false);
		adminSalesChart.setVisible(false);
	}
	// 기타 : 알림창 (중간에 : 꼭 적어줄 것) 예시 : "오류정보 : 값을 제대로 입력바랍니다."
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("알림");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":") + 1));
		alert.showAndWait();
	}
	// 기타 : 문자열 공백 체크
	public static boolean spaceCheck(String spaceCheck) {
		for (int i = 0; i < spaceCheck.length(); i++) {
			if (spaceCheck.charAt(i) == ' ' || spaceCheck == null) {
				AdminController.callAlert("[" + spaceCheck + "] 항목 공백 확인" + ": 입력란을 확인해주세요.");
				return true;
			}
		}
		return false;
	}

}
