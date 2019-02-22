package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
	public static Connection getConnection() {
		Connection con = null;
		try {
		//1. My SQL database class load
		Class.forName("com.mysql.jdbc.Driver");
		//2.주소, 아이디, 비밀번호를 통해 접속요청
		con = DriverManager.getConnection("jdbc:mysql://localhost/pilatesDB", "root", "123456");
		}catch(Exception e) {
			AdminController.callAlert("데이터 베이스 연결 실패 : Class - DBUtility 연결에 문제있어요....ㅜㅜ 확인 부탁드려요");
			return null;
		}		
		return con;
	}

}
