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

import Model.Classes;
import Model.Health;
import Model.Payment;
import Model.Student;
import Model.Teacher;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentController implements Initializable {
/////����� 
	@FXML private Button sangDamBtn;
	public Stage stuStage;
	private final File DIR = new File("C:/images");
	private Student student;

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
	TextField[] stuDateArray = new TextField[42];

	private LocalDateTime ldt = LocalDateTime.now();
	private Calendar cal = Calendar.getInstance();

	ObservableList<Teacher> obTchAllList = FXCollections.observableArrayList();
	private Teacher selectedTch;
	public static ArrayList<Teacher> dbTchList = new ArrayList<>();

	@FXML
	private Label stuMenuClass;
	@FXML
	private Label stuMenuHealth;
	@FXML
	private Label stuMenuChat;
	@FXML
	private Label stuMenuMy;
	@FXML
	private Label stuBtnLogout;
	@FXML
	private Label stuLblName;
	@FXML
	private Label sRsrvNewRegiDate;
	@FXML
	private Label sRsrvNewExpiDate;
	@FXML
	private Label sRsrvNewLftLssns;
	@FXML
	private Label sRsrvMyRegiDate;
	@FXML
	private Label sRsrvMyExpiDate;
	@FXML
	private Label sRsrvMyLftLssns;
	@FXML
	private Label sRsrvMyTtlLssns;
	@FXML
	private Label sRsrvMyTName;
	@FXML
	private Label sRsrvNewTName;

	// *******ȭ�� ����
	@FXML
	private Pane stuRsvn;
	@FXML
	private Pane stuAskToAdmin;
	@FXML
	private Pane stuHealth;

	@FXML
	private Pane stuMy;
	@FXML
	private Label mySNamelbl;
	@FXML
	private Label mySIDlbl;
	@FXML
	private Label myPhonelbl;
	@FXML
	private ImageView myImgView;
	private String localURL = null;
	@FXML
	private TextField myInfoChangeBtn;
	@FXML
	private HBox sMyAddClsBtn;
	@FXML
	private TableView<Payment> myPaymentTV;
	@FXML
	private ImageView stuBtnExit;
	@FXML
	private ImageView sRsrvNewImg;
	@FXML
	private ImageView sRsrvTImg;
	@FXML
	private ImageView stuHlthChrt;
	@FXML
	private ImageView stuHlthBtnAdd;
	@FXML
	private TableView<Classes> sRsrvNewClsTV;
	@FXML
	private TableView<Classes> sRsvnMyClsTV;
	@FXML
	private TableView<Health> stuHealthTV;
	@FXML
	private TextField sRsrvMyBtnCncl;
	@FXML
	private TextField sRsrvNewBtnRegi;

	private ArrayList<Classes> dbClsViewList = new ArrayList<>();

	private ArrayList<Classes> dbMyClsViewList = new ArrayList<>();
	private ObservableList<Classes> obMyClsViewList = FXCollections.observableArrayList();
	private ObservableList<String> obPeriodList = FXCollections.observableArrayList();

	private File selectFile = null;
	private String fileName = "";
	private File imageDir = new File("C:/images");
	private Teacher teacher;
	private ArrayList<Payment> dbPmtList = new ArrayList<>();
	private ObservableList<Payment> obPmtList = FXCollections.observableArrayList();
	private ObservableList<Health> obStuHealthList = FXCollections.observableArrayList();
	private Integer jj=0;
	
	//map�� ���� ��� ���� 0211_2*************0213
	@FXML private ImageView sangDamImgBtn;
	@FXML private ImageView mapBtn;
	@FXML private Button mapTxtBtn;
////INITIALIZE
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//MAP
		mapBtn.setOnMouseClicked(e-> map());
		mapTxtBtn.setOnMouseClicked(e-> map());
		
		
		
		
		// [SET] TableView
		initClassTableView();
		initTeacherTableView();
		initPaymentTableView();
		initStuHealthTableView();
		initMyClassTableView();
		// ���� ����
		// �󺧵� �⺻����
		initMenuClasslbls();
		obPeriodList.addAll("1", "3", "6", "9", "12");
		// [SET] �޷�
		initCalendar();

		// [SET] ���̵�� STUDENTã��
		student = StudentDAO.searchStudentByStuID(RootController.studentID);

		stuLblName.setText(student.getSName());

		// 1.[CLASS MENU]
		stuMenuClass.setOnMouseClicked(e -> menuClass());

		// 1. 1 ��¥�� ���� ���
		sRsrvNewBtnRegi.setOnMouseClicked(e -> handleNewRegi());
		// 1. 2 �����ϱ� â -> �̹��� Tab1
		sRsrvNewClsTV.setOnMouseClicked(e -> handleRsrvNewClsTVClickedAction());
		// 1. 3 ���� ���� â -> �̹��� Tab2
		sRsvnMyClsTV.setOnMouseClicked(e -> handleRsvnMyClsTVClickedAction());
		// 1. 4.������� ���� ���� â Tab2
		sRsrvMyBtnCncl.setOnMouseClicked(e -> handleRsrvMyBtnCnclAction());
		// 2. [HEALTH MENU]
		stuMenuHealth.setOnMouseClicked(e -> menuHealth());
		stuHlthBtnAdd.setOnMouseClicked(e -> handleHlthBtnAddAction());
		// 2. 1.chart
		stuHlthChrt.setOnMouseClicked(e -> {

			handleHlthChrtDialog();
		});

		// 3. [CHAT MENU]
		stuMenuChat.setOnMouseClicked(e -> menuAsk2Admin());
		sangDamBtn.setOnAction(e -> clientSangDam());
		sangDamImgBtn.setOnMouseClicked(e -> clientSangDam());
		// 2. [MY MENU]
		stuMenuMy.setOnMouseClicked(e -> menuMy());
		myInfoChangeBtn.setOnMouseClicked(e -> handleMyInfoChange());

		sMyAddClsBtn.setOnMouseClicked(e -> handleMyAddClsBtnAction());

		// [LOGOUT]
		stuBtnLogout.setOnMouseClicked(e -> handleLogoutAction());

		// [EXIT]
		stuBtnExit.setOnMouseClicked(e -> Platform.exit());


	}
	private void map() {
			 // create web engine and view
		        final WebView webView = new WebView();
		        final WebEngine webEngine = webView.getEngine();
		        Stage stage = new Stage();
		        webEngine.load(getClass().getResource("../View/googlemap.html").toString());
		   
		        // create scene
		        stage.setTitle("ü���� �ʶ��׽��� ���ô� ��");
		        Scene scene = new Scene(webView,1000,700, Color.web("#666970"));
		        stage.setScene(scene);
		        // show stage
		        stage.show();
			
	}
	
	
	
	
	
/////[CHAT MODE]��� ���
	private void clientSangDam(){
		try {
			Stage clientStage = new Stage();
			clientStage.setTitle("�� ��� ä��â");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/cli.fxml"));
			Parent root = loader.load();
			ClientController clientController = loader.getController();
			clientController.clientStage = clientStage;
			clientController.setStudent(student);			
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/chat.css").toString());
			clientStage.setScene(scene);
			
			clientStage.show();			
			
		//	AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� ");
		} catch (Exception e) {
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}
		
		
		
	}
	
	
	
	
	// [INIT] TABLE
	private void initClassTableView() {
		ObservableList<Classes> obClsViewList = FXCollections.observableArrayList();

		TableColumn tcClassDate = sRsrvNewClsTV.getColumns().get(0);
		tcClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClassTime = sRsrvNewClsTV.getColumns().get(1);
		tcClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

		TableColumn tcCourse = sRsrvNewClsTV.getColumns().get(2);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcCType = sRsrvNewClsTV.getColumns().get(3);
		tcCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcCType.setStyle("-fx-alignment: CENTER;");
		tcClassTime.setStyle("-fx-alignment: CENTER;");
		TableColumn tcClassRoom = sRsrvNewClsTV.getColumns().get(4);
		tcClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcClassRoom.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLimitNum = sRsrvNewClsTV.getColumns().get(5);
		tcLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcNowNum = sRsrvNewClsTV.getColumns().get(6);
		tcNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcNowNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTName = sRsrvNewClsTV.getColumns().get(7);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		// CLASSES dataBase�� �����ϴ� ��
		dbClsViewList.clear();
		dbClsViewList = ClassDAO.getClassesDataFromCurDate();
		for (Classes cls : dbClsViewList) {
			obClsViewList.add(cls);
		}
		sRsrvNewClsTV.setItems(obClsViewList);
	}

	private void initMyClassTableView() {

		TableColumn tcClassDate = sRsvnMyClsTV.getColumns().get(0);
		tcClassDate.setCellValueFactory(new PropertyValueFactory<>("classDate"));
		tcClassDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcClassTime = sRsvnMyClsTV.getColumns().get(1);
		tcClassTime.setCellValueFactory(new PropertyValueFactory<>("classTime"));

		TableColumn tcCourse = sRsvnMyClsTV.getColumns().get(2);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcCType = sRsvnMyClsTV.getColumns().get(3);
		tcCType.setCellValueFactory(new PropertyValueFactory<>("cType"));
		tcCType.setStyle("-fx-alignment: CENTER;");
		tcClassTime.setStyle("-fx-alignment: CENTER;");
		TableColumn tcClassRoom = sRsvnMyClsTV.getColumns().get(4);
		tcClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
		tcClassRoom.setStyle("-fx-alignment: CENTER;");
		TableColumn tcTName = sRsvnMyClsTV.getColumns().get(5);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLimitNum = sRsvnMyClsTV.getColumns().get(6);
		tcLimitNum.setCellValueFactory(new PropertyValueFactory<>("limitNum"));
		tcLimitNum.setStyle("-fx-alignment: CENTER;");

		TableColumn tcNowNum = sRsvnMyClsTV.getColumns().get(7);
		tcNowNum.setCellValueFactory(new PropertyValueFactory<>("nowNum"));
		tcNowNum.setStyle("-fx-alignment: CENTER;");

		// CLASSES dataBase�� �����ϴ� ��

		obMyClsViewList = ClassDAO.searchSelectedClsBySID(RootController.studentID);

		sRsvnMyClsTV.refresh();
		sRsvnMyClsTV.setItems(obMyClsViewList);
	}

	private void initStuHealthTableView() {
		TableColumn tcMesureDate = stuHealthTV.getColumns().get(0);
		tcMesureDate.setCellValueFactory(new PropertyValueFactory<>("measureDate"));
		tcMesureDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcWeight = stuHealthTV.getColumns().get(1);
		tcWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		tcWeight.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBodyfat = stuHealthTV.getColumns().get(2);
		tcBodyfat.setCellValueFactory(new PropertyValueFactory<>("bodyfat"));
		tcBodyfat.setStyle("-fx-alignment: CENTER;");

		TableColumn tcMuscleMass = stuHealthTV.getColumns().get(3);
		tcMuscleMass.setCellValueFactory(new PropertyValueFactory<>("muscleMass"));
		tcMuscleMass.setStyle("-fx-alignment: CENTER;");

		TableColumn tcBmi = stuHealthTV.getColumns().get(4);
		tcBmi.setCellValueFactory(new PropertyValueFactory<>("bmi"));
		tcBmi.setStyle("-fx-alignment: CENTER;");

		// HealthTbl dataBase�� �����ϴ� ��
		obStuHealthList = HealthDAO.getHealthSelectedData(RootController.studentID);
		stuHealthTV.setItems(obStuHealthList);

	}

	private void initTeacherTableView() {
		dbTchList = TeacherDAO.getTeacherTotalData();
		for (Teacher tch : dbTchList) {
			obTchAllList.add(tch);
		}
	}

	private void initPaymentTableView() {

		TableColumn tcCourse = myPaymentTV.getColumns().get(0);
		tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		tcCourse.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTName = myPaymentTV.getColumns().get(1);
		tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
		tcTName.setStyle("-fx-alignment: CENTER;");
		TableColumn tcRegiDate = myPaymentTV.getColumns().get(2);
		tcRegiDate.setCellValueFactory(new PropertyValueFactory<>("regiDate"));
		tcRegiDate.setStyle("-fx-alignment: CENTER;");
		TableColumn tcExpiDate = myPaymentTV.getColumns().get(3);
		tcExpiDate.setCellValueFactory(new PropertyValueFactory<>("expiDate"));
		tcExpiDate.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPeriod = myPaymentTV.getColumns().get(4);
		tcPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
		tcPeriod.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLessons = myPaymentTV.getColumns().get(5);
		tcLessons.setCellValueFactory(new PropertyValueFactory<>("lessons"));
		tcLessons.setStyle("-fx-alignment: CENTER;");

		TableColumn tcLeftLes = myPaymentTV.getColumns().get(6);
		tcLeftLes.setCellValueFactory(new PropertyValueFactory<>("leftLes"));
		tcLeftLes.setStyle("-fx-alignment: CENTER;");

		TableColumn tcPOption = myPaymentTV.getColumns().get(7);
		tcPOption.setCellValueFactory(new PropertyValueFactory<>("pOption"));
		tcPOption.setStyle("-fx-alignment: CENTER;");

		TableColumn tcTuition = myPaymentTV.getColumns().get(8);
		tcTuition.setCellValueFactory(new PropertyValueFactory<>("tuition"));
		tcTuition.setStyle("-fx-alignment: CENTER;");

		// PAYMENT dataBase�� �����ϴ� ��
		dbPmtList.clear();
		obPmtList.clear();
		System.out.println(RootController.studentID);
		dbPmtList = PaymentDAO.getPaymentTotalDataBySID(RootController.studentID);

		for (Payment pmt : dbPmtList) {
			obPmtList.add(pmt);
		}
		myPaymentTV.setItems(obPmtList);
	}

	// [INIT] calender
	private void initCalendar() {
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
			stuDateArray[i].setStyle("-fx-cursor: hand; ");
			
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
				
				if (e.getClickCount() == 1) {
					ObservableList<Classes> dbClsByDateList = ClassDAO.selectClassDataByDate(date);
					sRsrvNewClsTV.setItems(dbClsByDateList);					
					
					if(jj!=ii){						
						stuDateArray[ii].setStyle("-fx-background-color: orange");		
						stuDateArray[jj].setStyle("-fx-background-color: white");								
					}					
					jj=ii;
				}
			});
		}
	}

	// 1. [Class MENU]
	private void menuClass() {
		defaultMainStuDisplay();
		stuRsvn.setVisible(true);
	}

	// 1. 	0. �⺻ ����  ��
	private void initMenuClasslbls() {

		String closeDate = null;
		closeDate = PaymentDAO.getCloseRegiDate(RootController.studentID);

		if (closeDate == null) {
			sRsrvNewRegiDate.setText("���� ���� ����");
			sRsrvNewExpiDate.setText("���� ���� ����");
			sRsrvMyRegiDate.setText("���� ���� ����");
			sRsrvMyExpiDate.setText("���� ���� ����");

		} else {
			sRsrvMyExpiDate.setText(closeDate.substring(10));
			sRsrvMyRegiDate.setText(closeDate.substring(0, 10));
			sRsrvNewExpiDate.setText(closeDate.substring(10));
			sRsrvNewRegiDate.setText(closeDate.substring(0, 10));
			sRsrvMyLftLssns.setText(PaymentDAO.getLeftLesCloseRegiDate(RootController.studentID));
			sRsrvNewLftLssns.setText(PaymentDAO.getLeftLesCloseRegiDate(RootController.studentID));
			sRsrvMyTtlLssns.setText(PaymentDAO.getTtlLessonsCloseRegiDate(RootController.studentID));

		}

	}

	// 1. 	1 ���� ���
	private void handleNewRegi() {
		sRsrvNewBtnRegi.setEditable(false);
		System.out.println(RootController.studentID);
		ClassDAO.insertClsMemberData(sRsrvNewClsTV.getSelectionModel().getSelectedItem().getClassInfo(),
				RootController.studentID);
		initClassTableView();
		initMyClassTableView();
		initMenuClasslbls();
		
	}

	// 1. 2 �����ϱ� â -> �̹��� Tab1
	private void handleRsrvNewClsTVClickedAction() {
		Classes cls = sRsrvNewClsTV.getSelectionModel().getSelectedItem();
		String url = "file:///" + DIR.getAbsolutePath() + "/" + cls.getTImage();
		sRsrvNewImg.setImage(new Image(url));
		sRsrvNewTName.setText(cls.getTName());

	}

	// 1. 3 ���� ���� â -> �̹��� Tab2
	private void handleRsvnMyClsTVClickedAction() {
		Classes cls = sRsvnMyClsTV.getSelectionModel().getSelectedItem();
		String url = "file:///" + DIR.getAbsolutePath() + "/" + cls.getTImage();
		sRsrvTImg.setImage(new Image(url));
		sRsrvMyTName.setText(cls.getTName());

	}

	// 1. 4.������� ���� ���� â Tab2 
	private void handleRsrvMyBtnCnclAction() {
		Classes cls = sRsvnMyClsTV.getSelectionModel().getSelectedItem();
		String result = ClassDAO.delClsMemberData(cls.getClassInfo(), RootController.studentID);

		
		sRsrvMyLftLssns.setText(PaymentDAO.getLeftLesCloseRegiDate(RootController.studentID));
		sRsrvNewLftLssns.setText(PaymentDAO.getLeftLesCloseRegiDate(RootController.studentID));
		sRsrvMyTtlLssns.setText(PaymentDAO.getTtlLessonsCloseRegiDate(RootController.studentID));

		initClassTableView();
		initMyClassTableView();

	}

	// 2. 	[HEALTH MENU]
	private void menuHealth() {
		defaultMainStuDisplay();
		stuHealth.setVisible(true);
	}

	// 2. 	1 INSERT HealthTbl
	private void handleHlthBtnAddAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/new_health.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			Stage newHlthStage = new Stage(StageStyle.UTILITY);

			// **********************new_health id ���****************************

			ImageView testing = (ImageView) root.lookup("#testing");

			DatePicker nHlthDate = (DatePicker) root.lookup("#nHlthDate");
			TextField nHlthWeight = (TextField) root.lookup("#nHlthWeight");
			TextField nHlthFat = (TextField) root.lookup("#nHlthFat");
			TextField nHlthMuscle = (TextField) root.lookup("#nHlthMuscle");
			TextField nHlthBMI = (TextField) root.lookup("#nHlthBMI");
			TextField nHlthBtnRegi = (TextField) root.lookup("#nHlthBtnRegi");
			TextField nHlthBtnExit = (TextField) root.lookup("#nHlthBtnExit");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
			
			testing.setOnMouseClicked(e -> {
				nHlthDate.setValue(LocalDate.parse("2018-02-15"));
				double d=Math.random()*(50-46)+46;
				double d1=Math.random()*(22-18)+18;
				double d2=Math.random()*(24-19)+19;
				double d3=Math.random()*(50-46)+46;


				nHlthWeight.setText(String.format("%.2f",d));
				nHlthFat.setText(String.format("%.2f",d1));
				nHlthMuscle.setText(String.format("%.2f",d2));
				nHlthBMI.setText(String.format("%.2f",d3));
			});

			// [INSERT] ��� ��ư
			nHlthBtnRegi.setOnMouseClicked(e -> {
				AdminController.spaceCheck(nHlthWeight.getText());
				AdminController.spaceCheck(nHlthFat.getText());
				AdminController.spaceCheck(nHlthMuscle.getText());
				AdminController.spaceCheck(nHlthBMI.getText());

				HealthDAO.insertHealth(student.getStudentID(), nHlthDate.getValue().toString(),
						Double.parseDouble(nHlthWeight.getText().trim()), Double.parseDouble(nHlthFat.getText().trim()),
						Double.parseDouble(nHlthMuscle.getText().trim()),
						Double.parseDouble(nHlthBMI.getText().trim()));

				AdminController	.callAlert("�ǰ� ���� �߰� ���� : �����Ͻ� ��¥ " + nHlthDate.getValue().toString() + "�� �ǰ� ������ ��ϵǾ����ϴ�.");
				initStuHealthTableView();
				newHlthStage.close();
			});

			// [EXIT]��ҹ�ư
			nHlthBtnExit.setOnMouseClicked(e -> {
				newHlthStage.close();
			});

			newHlthStage.initOwner(stuStage);
			newHlthStage.initModality(Modality.WINDOW_MODAL);
			newHlthStage.setTitle("�ǰ� ��� �߰�");
			newHlthStage.setScene(scene);
			newHlthStage.show();
		} catch (IOException e) {
			AdminController.callAlert("�ǰ� ��� �߰� â ���� : �ǰ� ��� �߰� �����߻�!! Ȯ�����ּ���");
		}
	}

	// 2. 	2.��Ʈ
	private void handleHlthChrtDialog() {
		final String ym = "2019-02";
		ObservableList<PieChart.Data> listMonth = FXCollections.observableArrayList();
		ObservableList<PieChart.Data> listYear = FXCollections.observableArrayList();
		// ������Ʈ �ʱⰪ����
		listMonth = HealthDAO.getHealthAvgData(RootController.studentID, ym);
		listYear = HealthDAO.getHealthYearlyAvgData(RootController.studentID, ym.substring(0, 4));

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/chrt_stu.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/chrt.css").toString());
			Stage newHlthStage = new Stage(StageStyle.UTILITY);

			// **********************chrt_stu id ���****************************

			ImageView chrtBtnExit = (ImageView) root.lookup("#chrtBtnExit");
			Label chrtNameLbl = (Label) root.lookup("#chrtNameLbl");
			Label chrtMenuMonth = (Label) root.lookup("#chrtMenuM");
			Label chrtMenuYear = (Label) root.lookup("#chrtMenuYear");
			// ���� �м�
			Pane chrtMonthPane = (Pane) root.lookup("#chrtMonthPane");
			PieChart mPieChrt = (PieChart) root.lookup("#mPieChrt");
			BarChart<String, Double> mBarChrt = (BarChart<String, Double>) root.lookup("#mBarChrt");
			Label mChrtYYYY = (Label) root.lookup("#mChrtYYYY");
			Label mChrtMM = (Label) root.lookup("#mChrtMM");
			Label chrtMAfter = (Label) root.lookup("#chrtMAfter");
			Label chrtMBefore = (Label) root.lookup("#chrtMBefore");

			Pane chrtYearPane = (Pane) root.lookup("#chrtYearPane");
			PieChart yPieChrtYear = (PieChart) root.lookup("#yPieChrtYear");
			BarChart<String, Double> yBarChrtYear = (BarChart<String, Double>) root.lookup("#yBarChrtYear");
			Label chrtYAfter = (Label) root.lookup("#chrtYAfter");
			Label chrtYBefore = (Label) root.lookup("#chrtYBefore");
			Label yChrtYYYY = (Label) root.lookup("#yChrtYYYY");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
			// �ʱ� �� ����
			chrtBtnExit.setStyle("-fx-cursor : hand");
			yPieChrtYear.setAnimated(false);
			chrtMenuMonth.setOnMouseClicked(e -> {
				chrtYearPane.setVisible(false);
				chrtMonthPane.setVisible(true);
			});

			chrtMenuYear.setOnMouseClicked(e -> {
				chrtMonthPane.setVisible(false);
				chrtYearPane.setVisible(true);
			});

			chrtNameLbl.setText(student.getSName());
			mChrtYYYY.setText(ym.substring(0, 4));
			mChrtMM.setText(ym.substring(5));
			yChrtYYYY.setText(ym.substring(0, 4));
			mPieChrt.setData(listMonth);
			yPieChrtYear.setData(listYear);
			// �ش� ���� ����Ʈ
			mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
					mChrtYYYY.getText() + "-" + mChrtMM.getText(), "weight", "������"));
			mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
					mChrtYYYY.getText() + "-" + mChrtMM.getText(), "bodyfat", "ü�����"));
			mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
					mChrtYYYY.getText() + "-" + mChrtMM.getText(), "muscleMass", "��ݱٷ�"));
			mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
					mChrtYYYY.getText() + "-" + mChrtMM.getText(), "bmi", "BMI"));
			// �ش� �⵵�� �� ��Ʈ
			yBarChrtYear.getData()
					.add(HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "weight", "������"));
			yBarChrtYear.getData().add(
					HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "bodyfat", "ü�����"));
			yBarChrtYear.getData().add(
					HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "muscleMass", "��ݱٷ�"));
			yBarChrtYear.getData()
					.add(HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "bmi", "BMI"));
			
			
			
			// ���� �� ��ư Ŭ�� �׼�
			chrtMBefore.setOnMouseClicked(e -> {
				String mm = mChrtMM.getText();
				String yyyy = mChrtYYYY.getText();
				if (mm.equals("01")) {
					yyyy = (Integer.parseInt(yyyy) - 1) + "";
					mm = "12";
				} else if ((Integer.parseInt(mm) - 1) < 10) {
					mm = "0" + (Integer.parseInt(mm) - 1);
				} else {
					mm = "" + (Integer.parseInt(mm) - 1);
				}
				mChrtYYYY.setText(yyyy);
				mChrtMM.setText(mm);

				// ������Ʈ ��ȭ
				ObservableList<PieChart.Data> listNew = FXCollections.observableArrayList();
				listNew = HealthDAO.getHealthAvgData(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText());
				mPieChrt.setData(listNew);

				mBarChrt.getData().clear();
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "weight", "������"));
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "bodyfat", "ü�����"));
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "muscleMass", "��ݱٷ�"));
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "bmi", "BMI"));

			});

			// ���� �� ��ư Ŭ�� �׼�
			chrtMAfter.setOnMouseClicked(e -> {
				String mm = mChrtMM.getText();
				String yyyy = mChrtYYYY.getText();
				if (mm.equals("12")) {
					yyyy = (Integer.parseInt(yyyy) + 1) + "";
					mm = "01";
				} else if ((Integer.parseInt(mm) + 1) < 10) {
					mm = "0" + (Integer.parseInt(mm) + 1);
				} else {
					mm = "" + (Integer.parseInt(mm) + 1);
				}
				mChrtYYYY.setText(yyyy);
				mChrtMM.setText(mm);
				// ������Ʈ ��ȭ
				ObservableList<PieChart.Data> listNew = FXCollections.observableArrayList();
				listNew = HealthDAO.getHealthAvgData(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText());
				mPieChrt.setData(listNew);

				mBarChrt.getData().clear();
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "weight", "������"));
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "bodyfat", "ü�����"));
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "muscleMass", "��ݱٷ�"));
				mBarChrt.getData().add(HealthDAO.getHealthDataMonthly(RootController.studentID,
						mChrtYYYY.getText() + "-" + mChrtMM.getText(), "bmi", "BMI"));

			});

			// ���� �⵵ ��ư Ŭ�� �׼�
			chrtYBefore.setOnMouseClicked(e -> {
				Integer yyyy = Integer.parseInt(yChrtYYYY.getText()) - 1;
				yChrtYYYY.setText(yyyy + "");

				// ������Ʈ ��ȭ
				ObservableList<PieChart.Data> listNew = FXCollections.observableArrayList();
				listNew = HealthDAO.getHealthYearlyAvgData(RootController.studentID, yChrtYYYY.getText());
				yPieChrtYear.setData(listNew);

				// �ش� �⵵�� �� ��Ʈ
				yBarChrtYear.getData().clear();
				yBarChrtYear.getData().add(
						HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "weight", "������"));
				yBarChrtYear.getData().add(HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(),
						"bodyfat", "ü�����"));
				yBarChrtYear.getData().add(HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(),
						"muscleMass", "��ݱٷ�"));
				yBarChrtYear.getData().add(
						HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "bmi", "BMI"));

			});

			// ���� �⵵ ��ư Ŭ�� �׼�
			chrtYAfter.setOnMouseClicked(e -> {
				Integer yyyy = Integer.parseInt(yChrtYYYY.getText()) + 1;
				yChrtYYYY.setText(yyyy + "");

				// ������Ʈ ��ȭ
				ObservableList<PieChart.Data> listNew = FXCollections.observableArrayList();
				listNew = HealthDAO.getHealthYearlyAvgData(RootController.studentID, yChrtYYYY.getText());
				yPieChrtYear.setData(listNew);

				// �ش� �⵵�� �� ��Ʈ
				yBarChrtYear.getData().clear();
				yBarChrtYear.getData().add(
						HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "weight", "������"));
				yBarChrtYear.getData().add(HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(),
						"bodyfat", "ü�����"));
				yBarChrtYear.getData().add(HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(),
						"muscleMass", "��ݱٷ�"));
				yBarChrtYear.getData().add(
						HealthDAO.getHealthDataYearly(RootController.studentID, yChrtYYYY.getText(), "bmi", "BMI"));

			});

			// [EXIT]��ҹ�ư
			chrtBtnExit.setOnMouseClicked(e -> {
				newHlthStage.close();
			});

			newHlthStage.initOwner(stuStage);
			newHlthStage.initModality(Modality.WINDOW_MODAL);
			newHlthStage.setTitle("�ǰ� ��� �м�");
			newHlthStage.setScene(scene);
			newHlthStage.show();
		} catch (IOException e) {
			AdminController.callAlert("�ǰ� ��� �м� �߰� â ���� : �ǰ� ��� �߰� �����߻�!! Ȯ�����ּ���");
		}

	}

	
	// 3. 	[CHAT MENU]
	private void menuAsk2Admin() {
		defaultMainStuDisplay();
		stuAskToAdmin.setVisible(true);
	}

	// 4. 	[MY MENU]
	private void menuMy() {
		defaultMainStuDisplay();
		stuMy.setVisible(true);
		mySNamelbl.setText(student.getSName());
		mySIDlbl.setText(student.getStudentID());
		myPhonelbl.setText(student.getPhone());
		String url = "file:///" + DIR.getAbsolutePath() + "/" + student.getImage();
		System.out.println(url);
		myImgView.setImage(new Image(url));

	}

	private void handleMyInfoChange() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/edit_st.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			Stage editStage = new Stage(StageStyle.UTILITY);

			// **********************edit_st id ���****************************
			PasswordField eStuInfoNEW = (PasswordField) root.lookup("#eStuInfoNEW");
			PasswordField eStuInfoNowPW = (PasswordField) root.lookup("#eStuInfoNowPW");
			PasswordField eStuInfoNEWCheck = (PasswordField) root.lookup("#eStuInfoNEWCheck");
			TextField eStuInfoID = (TextField) root.lookup("#eStuInfoID");
			TextField eStuInfoPhone = (TextField) root.lookup("#eStuInfoPhone");
			TextField eStuInfoBtnSave = (TextField) root.lookup("#eStuInfoBtnSave");
			TextField eStuInfoBtnExit = (TextField) root.lookup("#eStuInfoBtnExit");
			ImageView eStuInfoImg = (ImageView) root.lookup("#eStuInfoImg");
			Label testing = (Label) root.lookup("#testing");
			CheckBox changePW = (CheckBox) root.lookup("#changePW");

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
			AdminController.spaceCheck(eStuInfoNEW.getText().trim());
			// ��й�ȣ ���� üũ�ڽ�
			changePW.setOnAction(e -> {
				changePW.setDisable(true);
				eStuInfoNowPW.setDisable(false);
				eStuInfoNEW.setDisable(false);
				eStuInfoNEWCheck.setDisable(false);
			});

			eStuInfoID.setText(student.getStudentID());
			eStuInfoPhone.setText(student.getPhone());
			// 1.0.testing
			testing.setOnMouseClicked(e -> {
				eStuInfoPhone.setText("01000005555");
				eStuInfoImg.setImage(new Image(getClass().getResource("../images/pila.png").toString()));
				eStuInfoNowPW.setText("1234");
				eStuInfoNEW.setText("0000");
				eStuInfoNEWCheck.setText("0000");
			});
			// 1.1 �̹��� ��ư -> �̹��� ���� fileChooser
			eStuInfoImg.setOnMouseClicked(e -> {
				fileName = student.getImage();
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
				selectFile = fileChooser.showOpenDialog(stuStage);

				if (selectFile != null) {
					try {
						localURL = selectFile.toURI().toURL().toString();
					} catch (MalformedURLException e1) {
						AdminController.callAlert("[Error] : �̹��� ���ÿ� ������ �߻��߽��ϴ�.");
					}
				}
				eStuInfoImg.setImage(new Image(localURL, false)); // ����ȭ������ �̹����� ������� �ʰڴٴ� �ǹ�
				// ���õ� ���ϸ�

				fileName = selectFile.getName();

				// Image edit
				if (!fileName.equals("")) {
					imageDelete();
					imageSave();
					if (selectFile == null) {
						AdminController.callAlert("�̹��� ���� ���� : �̹����������ּ���");
						return;
					}

				}
			}

			);
			// 1.2 ����� ������ ����
			eStuInfoBtnSave.setOnMouseClicked(e -> {
				String pw = "";
				AdminController.spaceCheck(eStuInfoNEW.getText());
				AdminController.spaceCheck(eStuInfoNEWCheck.getText());

				if (changePW.isSelected()==false) {
					pw = student.getSPassword().trim();
				} else if (!student.getSPassword().equals(eStuInfoNowPW.getText())) {
					AdminController.callAlert("���� �н����� ���� : ���� �� �н����� ���� �Դϴ�.");
					eStuInfoNowPW.clear();
					eStuInfoNEW.clear();
					eStuInfoNEWCheck.clear();
				} else if (!eStuInfoNEW.getText().trim().equals(eStuInfoNEWCheck.getText())) {
					AdminController.callAlert("���ο� ��й�ȣ ���� : NEW ��й�ȣ Ȯ�� ����ġ");
					eStuInfoNowPW.clear();
					eStuInfoNEW.clear();
					eStuInfoNEWCheck.clear();
				} else {
					pw = eStuInfoNEW.getText();

				}
				Student stu = new Student(student.getStudentID(), pw, student.getSName(), eStuInfoPhone.getText(),
						student.getGender(), student.getBirthDate(), fileName);
				int count = StudentDAO.updateStudentInfo(stu);
				myImgView.setImage(new Image(localURL, false));
			//	AdminController.callAlert("���� : ȸ���������� ���� ");
				editStage.close();
			});
			// 1.3.��ҹ�ư
			eStuInfoBtnExit.setOnMouseClicked(e -> {
				editStage.close();
			});

			editStage.initOwner(stuStage);
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.setTitle("��������");
			editStage.setScene(scene);
			editStage.show();
		} catch (IOException e) {
			AdminController.callAlert("�������� â ���� : �������� �����߻�!! Ȯ�����ּ���");
		}
	}

	private void handleMyAddClsBtnAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/new_payment.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
			Stage newPmtStage = new Stage(StageStyle.UTILITY);

			// **********************new_class.fxml id���****************************
			ImageView testing = (ImageView) root.lookup("#testing");
			TableView<Teacher> sPmtTchNameTV = (TableView<Teacher>) root.lookup("#pmtTchNameTV");
			ImageView sPmtTchImg = (ImageView) root.lookup("#pmtTchImg");
			CheckBox sPmtGroup = (CheckBox) root.lookup("#sPmtGroup");
			CheckBox sPmtPersonal = (CheckBox) root.lookup("#sPmtPersonal");
			CheckBox sPmtOptCrd = (CheckBox) root.lookup("#sPmtOptCrd");
			CheckBox sPmtOptCsh = (CheckBox) root.lookup("#sPmtOptCsh");
			TextField sPmtName = (TextField) root.lookup("#sPmtName");
			ComboBox<String> sPmtPeriodCmb = (ComboBox<String>) root.lookup("#sPmtPeriodCmb");

			TextField sPmtOK = (TextField) root.lookup("#sPmtOK");
			TextField sPmtExit = (TextField) root.lookup("#sPmtExit");

			student = StudentDAO.searchStudentByStuID(RootController.studentID);

			// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
			// [INITIALIZE] ComboBoxes & TableView
			TableColumn tcTName = sPmtTchNameTV.getColumns().get(0);
			tcTName.setCellValueFactory(new PropertyValueFactory<>("tName"));
			tcTName.setStyle("-fx-alignment: CENTER;");
			TableColumn tcImage = sPmtTchNameTV.getColumns().get(1);
			tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));
			tcImage.setStyle("-fx-alignment: CENTER;");
			sPmtTchNameTV.setItems(obTchAllList);
			sPmtName.setText(student.getSName());
			sPmtName.setEditable(false);

			sPmtTchNameTV.setOnMouseClicked(e -> {
				teacher = sPmtTchNameTV.getSelectionModel().getSelectedItem();
				String url = "file:///" + DIR.getAbsolutePath() + "/" + teacher.getImage();
				sPmtTchImg.setImage(new Image(url));

			});

			sPmtPeriodCmb.setItems(obPeriodList);

			// 6.1.1[ACTION] Exit
			sPmtExit.setOnMouseClicked(e -> {
				newPmtStage.close();
				stuStage.show();
			});

			// 6.1.1[ACTION] Add new Class (Register Class)
			sPmtOK.setOnMouseClicked(e -> {
				if ((sPmtPersonal.isSelected() && sPmtGroup.isSelected())
						|| (!sPmtPersonal.isSelected() && !sPmtGroup.isSelected())) {
					AdminController.callAlert("Course ���� ���� : ���� ������ �ϳ�!�������ּ���");
				} else if (sPmtOptCrd.isSelected() && sPmtOptCsh.isSelected()
						|| (!sPmtOptCrd.isSelected() && !sPmtOptCsh.isSelected())) {
					AdminController.callAlert("������� ���� ���� : ���� ����� �ϳ�!�������ּ���");
				} else {
					String option1 = "";
					if (sPmtOptCsh.isSelected()) {
						option1 = "����";
					} else if (sPmtOptCrd.isSelected()) {
						option1 = "ī��";
					}
					try {
						FXMLLoader loaderDbl = new FXMLLoader(
								getClass().getResource("../View/double_check_payment.fxml"));
						Parent rootDbl = loaderDbl.load();
						Scene sceneDbl = new Scene(rootDbl);
						sceneDbl.getStylesheets().add(getClass().getResource("../application/basic.css").toString());
						Stage dblChckPmtStage = new Stage(StageStyle.UTILITY);

						// **********************double_check_payment.fxml
						// id���****************************
						TextField dbchTxtSName = (TextField) rootDbl.lookup("#dbchTxtSName");
						CheckBox dbchGroup = (CheckBox) rootDbl.lookup("#dbchGroup");
						CheckBox dbchPersonal = (CheckBox) rootDbl.lookup("#dbchPersonal");
						TextField dbchTxtTName = (TextField) rootDbl.lookup("#dbchTxtTName");
						ComboBox<String> dbchCmbPeriod = (ComboBox<String>) rootDbl.lookup("#dbchCmbPeriod");
						TextField dbchTxtTtl = (TextField) rootDbl.lookup("#dbchTxtTtl");
						TextField dbchTxtRegiDate = (TextField) rootDbl.lookup("#dbchTxtRegiDate");
						TextField dbchTuition = (TextField) rootDbl.lookup("#dbchTuition");
						TextField dbchOption = (TextField) rootDbl.lookup("#dbchOption");
						TextField dbchOKBtn = (TextField) rootDbl.lookup("#dbchOKBtn");
						TextField dbchBackBtn = (TextField) rootDbl.lookup("#dbchBackBtn");

						// **********************�̺�Ʈ ��� �� �ʱ�ȭ�� �ڵ鷯����****************************
						// [INITIALIZE] ComboBoxes & TableView
						dbchTxtSName.setText(sPmtName.getText());
						dbchTxtSName.setEditable(false);

						int monthMoney = 0;

						if (sPmtGroup.isSelected()) {
							dbchGroup.setSelected(true);
							dbchGroup.setDisable(true);
							dbchPersonal.setDisable(true);
							monthMoney = 200000;

						} else if (sPmtPersonal.isSelected()) {
							dbchPersonal.setSelected(true);
							monthMoney = 400000;
							dbchGroup.setDisable(true);
							dbchPersonal.setDisable(true);
						} else if (sPmtPersonal.isSelected() && sPmtGroup.isSelected()) {
							AdminController.callAlert("������� ���� ���� : ���� ����� �ϳ�!�������ּ���");
						}

						String teacherName = "";
						teacherName = teacher.getTName();
						dbchTxtTName.setText(teacher.getTName());
						dbchTxtTName.setEditable(false);

						dbchCmbPeriod.getSelectionModel().select(sPmtPeriodCmb.getValue());

						int total = monthMoney * Integer.parseInt(dbchCmbPeriod.getSelectionModel().getSelectedItem());
						dbchTxtTtl.setText(Integer.parseInt(dbchCmbPeriod.getSelectionModel().getSelectedItem())
								* PaymentDAO.LESSON + "");

						dbchTxtRegiDate.setText(LocalDate.now().toString());

						dbchTuition.setText(total + "");

						dbchOption.setText(option1);

						dbchCmbPeriod.setItems(obPeriodList);

						dbchTuition.setEditable(false);
						dbchOption.setEditable(false);
						dbchCmbPeriod.setDisable(true);
						dbchTxtTtl.setEditable(false);
						dbchTxtRegiDate.setEditable(false);

						dbchOKBtn.setOnMouseClicked(e3 -> {
							String course = "";
							String tID = "";
							String tName = "";
							if (dbchGroup.isSelected()) {
								course = "�׷�";
								tID = teacher.getTeacherID();
								tName = teacher.getTName();
							} else if (sPmtPersonal.isSelected()) {
								course = "����";
								tID = teacher.getTeacherID();
								tName = teacher.getTName();
							}

							String maxExpi = null;
							maxExpi = PaymentDAO.getMaxExpiDateBySID(RootController.studentID);

							Integer mExpi = 0;
							Integer newRegi = 1;

							if (maxExpi != null) {
								mExpi = Integer.parseInt(maxExpi.replace("-", ""));
								newRegi = Integer.parseInt(dbchTxtRegiDate.getText().replace("-", ""));
							}
							if (maxExpi != null && mExpi >= newRegi) {
								AdminController
										.callAlert("��� ��¥ Ȯ�� : �ش� ��¥�� ����� ������ �̹� �����մϴ�. \n �ߺ��� ��¥���� ����� �Ұ����մϴ�.");
								System.out.println(newRegi);
								System.out.println(mExpi);

							} else {
								Payment payment = new Payment(student.getStudentID(), student.getSName(), tID, tName,
										course, Integer.parseInt(dbchCmbPeriod.getSelectionModel().getSelectedItem()),
										Integer.parseInt(dbchTxtTtl.getText()), Integer.parseInt(dbchTxtTtl.getText()),
										dbchOption.getText(), dbchTuition.getText(), dbchTxtRegiDate.getText(),
										dbchTxtRegiDate.getText());
								System.out.println(payment.toString());
								PaymentDAO.insertPayment(payment);
								initPaymentTableView();
								AdminController.callAlert("���� ���� : ������ �����߽��ϴ�. ");
								newPmtStage.close();
							}
							dblChckPmtStage.close();
							newPmtStage.close();
						});
						dbchBackBtn.setOnMouseClicked(e4 -> {
							dblChckPmtStage.close();
						});

						dblChckPmtStage.initOwner(stuStage);
						dblChckPmtStage.initModality(Modality.WINDOW_MODAL);
						dblChckPmtStage.setTitle("ADD NEW CLASS");
						dblChckPmtStage.setScene(sceneDbl);
						dblChckPmtStage.show();
					} catch (IOException e2) {
						AdminController.callAlert("ȸ������ ���� â ���� : ȸ������ ���� �����߻�!! Ȯ�����ּ���");
					}
				}

			});

			newPmtStage.initOwner(stuStage);
			newPmtStage.initModality(Modality.WINDOW_MODAL);
			newPmtStage.setTitle("ADD NEW CLASS");
			newPmtStage.setScene(scene);
			newPmtStage.show();
		} catch (IOException e2) {
			AdminController.callAlert("ȸ������ ���� â ���� : ȸ������ ���� �����߻�!! Ȯ�����ּ���");
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
			stuStage.close();
			stuLoginStage.show();
			//AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� ");
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

			fileName = "stu" + System.currentTimeMillis() + "_" + selectFile.getName();

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
		File imageFile = new File(DIR.getAbsolutePath() + "\\" + student.getImage());

		boolean delFlag = false;
		if (imageFile.exists() && imageFile.isFile()) {
			delFlag = imageFile.delete();
			if (delFlag == false) {
				AdminController.callAlert("�̹��� ���� ���� : �̹��� ���� Ȯ�� �ٶ�.");
			}
		}
	}

	// ��Ÿ : ȭ�� �� ������
	private void defaultMainStuDisplay() {
		stuRsvn.setVisible(false);
		stuAskToAdmin.setVisible(false);
		stuMy.setVisible(false);
		stuHealth.setVisible(false);

	}
}
