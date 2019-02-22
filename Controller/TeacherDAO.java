package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Student;
import Model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherDAO {
	public static ArrayList<Teacher> dbTchList = new ArrayList<>();

	// 1. [WHERE ID=?] teacherTbl�� �����ϴ� id���� Ȯ��
	public static int checkTeacherId(String teacherID) {
		StringBuffer checkTchID = new StringBuffer("select count(*) from teacherTbl where teacherID = ? ");
		int resultCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			// ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾�
			psmt.setString(1, teacherID);

			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������
			rs = psmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				resultCount = rs.getInt(1);
			
			}
			System.out.println("resultCont" + resultCount);
		} catch (SQLException e) {
			AdminController.callAlert("[Login ���� ]: TeacherDAO");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
			}
		}

		return resultCount;
	}

	// 1. [WHERE ID=?] teacherTbl�� �����ϴ� id���� Ȯ��
	public static int checkPW(String tID, String pw) {
		StringBuffer checkTchID = new StringBuffer(
				"select count(*) from teacherTbl where tPassword = ? and teacherID =?");
		int resultCount = 0;
		Connection con = null;
		PreparedStatement psmt = null;

		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			// ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾�
			psmt.setString(1, pw);
			psmt.setString(2, tID);

			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������
			rs = psmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1));
				resultCount = rs.getInt(1);
			}
			if (resultCount == 0) {
				AdminController.callAlert("LOGIN ���� : ���̵�� �н����尡 ��ġ���� �ʽ��ϴ�.");
				return resultCount;
			}

		} catch (SQLException e) {
			AdminController.callAlert("login ���� : TeacherDAO Check PW");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
			}
		}

		return resultCount;
	}

	//1.3 [WHERE ID=?] teacherTbl�� �����ϴ� id���� Ȯ�� 
	public static String findIDByPhone(String phone, String name, String date) {		
			StringBuffer checkTchID = new StringBuffer("select teacherID from teacherTbl where phone = ? and tName= ? and birthDate=?");
	 		String resultString =null;
	 		System.out.println(date);
			Connection con = null;
			PreparedStatement psmt = null;
			
			ResultSet rs = null;
			try {
				con = DBUtility.getConnection();
				psmt = con.prepareStatement(checkTchID.toString());
				//ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾� 
				psmt.setString(1, phone);
				psmt.setString(2, name);
				psmt.setString(3, date);
		
				// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
				// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������ 
				// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������ 
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
					resultString= rs.getString(1);
				}
				if (resultString==null) {
					//AdminController.callAlert("LOGIN ���� : �������� �ʴ� ���̵� �Դϴ�.");
					return resultString;
				}
				
			} catch (SQLException e) {
				//AdminController.callAlert("login ���� : StudentDAO");
				e.printStackTrace();
			} finally {
				try {
					// 1.6 CLOSE DataBase psmt object
					// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
					// �ݵ�� ������ �ݾƶ�.
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
				}
			}
			
			
			return resultString;
		}
	
	//1.4 [WHERE ID=?] teacherTbl�� �����ϴ� id�� �´� �н����� ���� Ȯ�� 
	public static String findPWByPhone(String name, String phone, String date, String email) {
		StringBuffer checkTchID = new StringBuffer("select tPassword, phone from teacherTbl where teacherId= ? and tName= ? and birthDate= ?");
 		String resultString =null;
		Connection con = null;
		PreparedStatement psmt = null;
		System.out.println("email"+email);
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			//ù��° ����ǥ �ڸ� -> teacherTbl ��ġ �����ִ� �۾� 
			psmt.setString(1, email);
			psmt.setString(2, name);
			psmt.setString(3, date);
			
	                                                           
			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������ 
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������ 
			rs = psmt.executeQuery();
			String phoneFlg=null;
			while(rs.next()) {
				System.out.println(rs.getString(1));
				resultString= rs.getString(1);
				phoneFlg= rs.getString(2);
				System.out.println(rs.getString(2));
				System.out.println(phoneFlg);
				System.out.println(phone);
			}
			/*if(phoneFlg.equals(phone) == false) {
				AdminController.callAlert("PWã�� ���� : ������ �ٸ��� �ʽ��ϴ�.");
				
			}else*/
			
		} catch (SQLException e) {
			//AdminController.callAlert("login ���� : StudentDAO");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
			}
		}
		
		
		return resultString;
	}

	
	// 2. [INSERT] teacherTbl�� Record �߰�
	public static int insertTeacher(Teacher teacher) {
		// insert into teachertbl
		// values('cherry@nate.com','1123','Cherry','01033335555','����','2000-01-01','cherry.png');
		int count = 0;
		// 1.1 ������ �ۼ�
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("insert into teachertbl ");
		insertStudent.append("(teacherID,tPassword,tName,phone,gender,birthDate,image) ");
		insertStudent.append("values ");
		insertStudent.append("(?,?,?,?,?,?,?)");

		// 1.2 BRING DataBase Connection
		Connection con = null;
		// 1.3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertStudent.toString());

			// 1.4�������� ���� ������ ����
			// ��� 1�� ���� *********
			psmt.setString(1, teacher.getTeacherID());
			psmt.setString(2, teacher.getTPassword());
			psmt.setString(3, teacher.getTName());
			psmt.setString(4, teacher.getPhone());
			psmt.setString(5, teacher.getGender());
			psmt.setString(6, teacher.getBirthDate());
			psmt.setString(7, teacher.getImage());

			// 1.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
			// �߸��Ǹ� 0�� �ش�.
			// executeUpdate�� ������ ���� �� ���̺� �������� �� ����ϴ� ������
			count = psmt.executeUpdate();

			if (count == 0) {
				AdminController.callAlert("���� ������ ���� ���� : ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}

		} catch (SQLException e) {
			AdminController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return count;
	}

	// 3. [SELECT]teacherTbl All ��������
	public static ArrayList<Teacher> getTeacherTotalData() {
		ArrayList<Teacher> teacherList = new ArrayList<>();
		// .1 (������)database all record in student table
		String selectTeacher = "select * from teacherTbl ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTeacher);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("select ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while (rs.next()) {
				Teacher teacher = new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));

				teacherList.add(teacher);
			}
			System.out.println(teacherList.size());
		} catch (SQLException e) {
			AdminController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return teacherList;
	}

	// 4. [DELETE] teacherTbl Record
	public static int deleteTeacher(Teacher teacher) {
		int count = 0;
		// .1 ������ �ۼ�
		StringBuffer insertStudent = new StringBuffer();
		// e.g. DELETE from teachertbl where teacherID='cherry@nate.com';
		insertStudent.append("delete from teacherTbl ");
		insertStudent.append("where teacherID=? ");

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertStudent.toString());

			// .4�������� ���� ������ ����
			psmt.setString(1, teacher.getTeacherID());

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
			count = psmt.executeUpdate();

			if (count == 0) {
				AdminController.callAlert("DELETE ������ ���� ���� : ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}

		} catch (SQLException e) {
			AdminController.callAlert("DELETE ���� : ������ DELETE�� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("[DELETE]�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return count;
	}

	// 5. [SEARCH] where sName=? �̸����� �˻�
	public static ObservableList<Teacher> searchSelectedTchData(String tchName) {
		ObservableList<Teacher> dbTchNameList = FXCollections.observableArrayList();
		// .1 (������)database selected records (where sName=?) in student table
		String searchStuName = "select * from teacherTbl where tName= ? ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, tchName);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Student] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}

			while (rs.next()) {
				Teacher selectedTch = new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				dbTchNameList.add(selectedTch);
			}
		} catch (SQLException e) {
			AdminController.callAlert("[Search Selected Teacher] �̸�ã�� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return dbTchNameList;
	}

	// 6. [Select] teacher Name All
	public static ObservableList<String> getTchNameData() {
		ObservableList<String> dbTchNameList = FXCollections.observableArrayList();
		// .1 (������)database all record in student table
		String selectTeacher = "select tName from teacherTbl ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTeacher);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("select ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while (rs.next()) {

				dbTchNameList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			AdminController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return dbTchNameList;
	}

	// 7. [Select] teacher Name By TID
	public static Teacher getTchNameDataByTID(String tID) {
		Teacher tch = null;
		// .1 (������)database all record in student table
		String selectTeacher = "select * from teacherTbl where teacherID = ?";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTeacher);
			psmt.setString(1, tID);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("select ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while (rs.next()) {
				tch = new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			AdminController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return tch;
	}

	// 8. [UPDATE] ���� ����

	public static int updateTeacherInfo(Teacher tch) {
		ResultSet rs = null;
		// call updateTeacher('mjk@nate.com','00022223333','1234','greychart.png');
		int count = 0;
		// .1 ������ �ۼ�
		StringBuffer updateTch = new StringBuffer();
		// delete from teachertbl where teacherID='22@nate.com';
		updateTch.append("call updateTeacher(?,?,?,?) ");

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateTch.toString());

			// .4�������� ���� ������ ����
			psmt.setString(1, tch.getTeacherID());
			psmt.setString(2, tch.getPhone());
			psmt.setString(3, tch.getTPassword());
			psmt.setString(4, tch.getImage());
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
			count = psmt.executeUpdate();

			if (count == 0) {
				AdminController.callAlert("UPDATE ������ ���� ���� : ������ �����ϴ� ���� ������ �߻��߾��.");
				return count;
			}
			AdminController.callAlert("���� UPDATE : ����");
		} catch (SQLException e) {
			AdminController.callAlert("UPDATE ���� : ������ DELETE�� ������ �߻��߾��.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("[DELETE]�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

			}
		}
		return count;
	}
}
