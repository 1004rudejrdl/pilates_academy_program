package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class AdmLoginController implements Initializable{
	public Stage admLoginStage;
	@FXML ImageView adLogImg;
	@FXML TextField adLogId;
	@FXML TextField adLogLogin;
	@FXML Label adLogStu;
	@FXML PasswordField adLogPassword;
	@FXML Label adLogBack;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//0.For Testing
		adLogImg.setOnMouseClicked(e -> debugLoginImg());
		adLogId.setOnKeyPressed(event -> {
	         if (event.getCode().equals(KeyCode.ENTER)) {
	        	handleLoginAction();
	         }
	      });
		adLogPassword.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				handleLoginAction();
			}
		});
		
		// 1.[EXECUTE] ������ ��� ����
		adLogLogin.setOnMouseClicked(e -> handleLoginAction());
		// 2.[BACK] �ڷ� ���� ����
		adLogBack.setOnMouseClicked(e->handleBtnBack());
	}
	
	// 0.[Testing] 
	private void debugLoginImg() {
		adLogId.setText("11@nate.com");
		adLogPassword.setText("1234");
	}

	// 1.[EXECUTE] ADMINISTER ������ ��� ����
	private void handleLoginAction() {
		try {
			Stage adminStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/main_admin2.fxml"));
			Parent root = loader.load();
			AdminController adminController = loader.getController();
			adminController.adminStage = adminStage;
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/admin.css").toString());
			adminStage.setScene(scene);
			admLoginStage.close();
			adminStage.show();
		} catch (Exception e) {
			 
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}
	}
	
	// 2.[BACK] �ڷ� ���� ����
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
			admLoginStage.close();
			stuLoginStage.show();
		} catch (Exception e) {
			AdminController.callAlert("ȭ�� ��ȯ ���� : ȭ�� ��ȯ�� ���� �߻�");
		}
		
	}
}
