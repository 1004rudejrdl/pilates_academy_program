package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
	public static Connection getConnection() {
		Connection con = null;
		try {
		//1. My SQL database class load
		Class.forName("com.mysql.jdbc.Driver");
		//2.�ּ�, ���̵�, ��й�ȣ�� ���� ���ӿ�û
		con = DriverManager.getConnection("jdbc:mysql://localhost/pilatesDB", "root", "123456");
		}catch(Exception e) {
			AdminController.callAlert("������ ���̽� ���� ���� : Class - DBUtility ���ῡ �����־��....�̤� Ȯ�� ��Ź�����");
			return null;
		}		
		return con;
	}

}
