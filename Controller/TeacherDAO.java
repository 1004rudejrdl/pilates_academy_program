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

			psmt.setString(1, teacherID);

			rs = psmt.executeQuery();

			while (rs.next()) {
				resultCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			AdminController.callAlert("[Login ���� ]: TeacherDAO");
			e.printStackTrace();
		} finally {
			try {
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

	// 1.1 [WHERE ID=?] teacherTbl�� �����ϴ� id���� Ȯ��
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
			psmt.setString(1, pw);
			psmt.setString(2, tID);

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

	// 1.3 teacherTbl�� �����ϴ� id���� Ȯ�� 
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
				psmt.setString(1, phone);
				psmt.setString(2, name);
				psmt.setString(3, date);
		
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					resultString= rs.getString(1);
				}
				if (resultString==null) {
					return resultString;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
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
	
	//1.4 teacherTbl�� �����ϴ� id�� �´� �н����� ���� Ȯ�� 
	public static String findPWByPhone(String name, String phone, String date, String email) {
		StringBuffer checkTchID = new StringBuffer("select tPassword, phone from teacherTbl where teacherId= ? and tName= ? and birthDate= ?");
 		String resultString =null;
		Connection con = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			//ù��° ����ǥ �ڸ� -> teacherTbl ��ġ �����ִ� �۾� 
			psmt.setString(1, email);
			psmt.setString(2, name);
			psmt.setString(3, date);
			
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
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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
		int count = 0;
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("insert into teachertbl ");
		insertStudent.append("(teacherID,tPassword,tName,phone,gender,birthDate,image) ");
		insertStudent.append("values ");
		insertStudent.append("(?,?,?,?,?,?,?)");

		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertStudent.toString());

			psmt.setString(1, teacher.getTeacherID());
			psmt.setString(2, teacher.getTPassword());
			psmt.setString(3, teacher.getTName());
			psmt.setString(4, teacher.getPhone());
			psmt.setString(5, teacher.getGender());
			psmt.setString(6, teacher.getBirthDate());
			psmt.setString(7, teacher.getImage());

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

	// 3. [SELECT] teacherTbl All ��������
	public static ArrayList<Teacher> getTeacherTotalData() {
		ArrayList<Teacher> teacherList = new ArrayList<>();
		String selectTeacher = "select * from teacherTbl ";

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTeacher);

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
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("delete from teacherTbl ");
		insertStudent.append("where teacherID=? ");

		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertStudent.toString());

			psmt.setString(1, teacher.getTeacherID());

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

	// 5. [SEARCH] where tchName=? �̸����� �˻�
	public static ObservableList<Teacher> searchSelectedTchData(String tchName) {
		ObservableList<Teacher> dbTchNameList = FXCollections.observableArrayList();
		String searchStuName = "select * from teacherTbl where tName= ? ";

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, tchName);
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Teacher] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
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
		String selectTeacher = "select tName from teacherTbl ";

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTeacher);
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
		String selectTeacher = "select * from teacherTbl where teacherID = ?";

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTeacher);
			psmt.setString(1, tID);

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
		int count = 0;
		StringBuffer updateTch = new StringBuffer();
		updateTch.append("call updateTeacher(?,?,?,?) ");

		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(updateTch.toString());

			psmt.setString(1, tch.getTeacherID());
			psmt.setString(2, tch.getPhone());
			psmt.setString(3, tch.getTPassword());
			psmt.setString(4, tch.getImage());
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
