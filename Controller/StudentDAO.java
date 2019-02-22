package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Classes;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDAO {
	public static ArrayList<Student> dbStuList = new ArrayList<>();

	//1.1 [WHERE ID=?] studentTbl�� �����ϴ� id���� Ȯ�� 
	public static int checkStudentId(String studentID) {		
		StringBuffer checkTchID = new StringBuffer("select count(*) from studentTbl where studentId = ? ");
 		int resultCount =0;
		Connection con = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			System.out.println(studentID);
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			//ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾� 
			psmt.setString(1, studentID);
	
			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������ 
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������ 
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				resultCount= rs.getInt(1);
			}
			if (resultCount==0) {
				//AdminController.callAlert("LOGIN ���� : �������� �ʴ� ���̵� �Դϴ�.");
				return resultCount;
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
		
		
		return resultCount;
	}
	//1.2 [WHERE ID=?] studentTbl�� �����ϴ� id���� Ȯ�� 
	public static int checkPW(String studentID,String pw) {		
		StringBuffer checkStuID = new StringBuffer("select count(*) from studentTbl where sPassword = ? and studentID= ? ");
		int resultCount =0;
		Connection con = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkStuID.toString());
			//ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾� 
			psmt.setString(1, pw);
			psmt.setString(2, studentID);
			
			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������ 
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������ 
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				resultCount= rs.getInt(1);
			}
			if (resultCount==0) {
				AdminController.callAlert("LOGIN ���� : ���̵�� �н����尡 ��ġ���� �ʽ��ϴ�.");
				return resultCount;
			}
			
		} catch (SQLException e) {
			AdminController.callAlert("login ���� : StudentDAO Check PW");
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
	
	//1.3 [WHERE ID=?] studentTbl�� �����ϴ� id���� Ȯ�� 
	public static String findIDByPhone(String phone, String name,String date) {		
			StringBuffer checkTchID = new StringBuffer("select studentId from studentTbl where phone = ? and sName= ? and birthDate= ? ");
	 		String resultString =null;
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
				System.out.println("###"+phone);
				System.out.println("###"+name);
				System.out.println("###"+date);
		
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
	
	//1.4 [WHERE ID=?] studentTbl�� �����ϴ� id�� �´� �н����� ���� Ȯ�� 
	public static String findPWByPhone(String name, String phone, String date, String email) {
		StringBuffer checkTchID = new StringBuffer("select sPassword, phone from studentTbl where studentId= ? and sName = ? ");
 		String resultString =null;
		Connection con = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			//ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾� 
			psmt.setString(1, email);
			System.out.println(email);
			System.out.println("nameee"+name);
			psmt.setString(2, name);
			
	                                                           
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
			if (resultString==null) {
				AdminController.callAlert("[�н�����ã�� ���� ]: �������� �ʴ� ���̵� �Դϴ�.");
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

	
	//2. [INSERT] studentTbl�� Record �߰� 
	public static int insertStudent(Student student) {
		int count =0;
		//1.1 ������ �ۼ�
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("insert into studentTbl ");
		insertStudent.append("(studentID,sPassword,sName,phone,gender,birthDate,image) ");
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
					psmt.setString(1, student.getStudentID());
					psmt.setString(2, student.getSPassword());
					psmt.setString(3, student.getSName());
					psmt.setString(4, student.getPhone());
					psmt.setString(5, student.getGender());
					psmt.setString(6, student.getBirthDate());
					psmt.setString(7, student.getImage());
					
					// 1.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
					// �߸��Ǹ� 0�� �ش�.
					//executeUpdate�� ������ ���� �� ���̺� �������� �� ����ϴ� ������ 
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
	
	//3. [SELECT]studentTbl ��������
	public static ArrayList<Student> getStudentTotalData() {
		//	.1  (������)database all record in student table 
		String selectStudent = "select * from studenttbl ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectStudent);

	
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("select ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while(rs.next()) {
				Student student = new Student(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				dbStuList.add(student);
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
		return dbStuList;
	}

	//4. [DELETE] studentTbl Record  
	public static int deleteStudent(Student student) {
		int count =0;
		//.1 ������ �ۼ�
		StringBuffer insertStudent = new StringBuffer();
		//delete from studenttbl where studentID='22@nate.com';
		insertStudent.append("delete from studentTbl ");
		insertStudent.append("where studentID=? ");
		
		// .2 BRING DataBase Connection
				Connection con = null;
				// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
				PreparedStatement psmt = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());

					// .4�������� ���� ������ ����
					psmt.setString(1, student.getStudentID());				
					
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

	//5. [SEARCH] where sName=? �̸����� �˻�
	public static ObservableList<Student> searchByName(String stuName) {
		ObservableList<Student> dbStuNameList = FXCollections.observableArrayList();
		//ex : select * from studentTbl where studentId='11@nate.com';
		//ex: select * from studenttbl where sName=(select replace('�� �� ��',' ',''));
		//	.1  (������)database selected records (where sName=?) in student table 
		String searchStuName = "select * from studenttbl where sName= ? ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, stuName);			
			
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[Search Selected Student] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			
			while(rs.next()) {
				Student selectedStu= new Student(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				dbStuNameList.add(selectedStu);
			}
		} catch (SQLException e) { 
			AdminController.callAlert("[Search Selected Student] �̸�ã�� ���� : ������ ���Կ� ������ �߻��߾��.");
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
		return dbStuNameList;
	}

	//6. [SEARCH] sName (where studentID=?) ���̵�� �̸�ã�� 
	public static String searchByStuID(String sID) {
		String name="";
		String searchStuName = "select sName from studenttbl where studentID= ? ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, sID);			
			
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[Search Name by StudentID] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}

			while(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) { 
			AdminController.callAlert("[Search Name by StudentID] �̸�ã�� ���� : ������ ���Կ� ������ �߻��߾��.");
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
		return name;
	}

	//6. [SEARCH] * (where studentID=?) ���̵�� Recordã�� 
	public static Student searchStudentByStuID(String sID) {
		Student student=null;
		String searchStuName = "select * from studenttbl where studentID= ? ";
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, sID);			
			
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[Search Name by StudentID] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			
			while(rs.next()) {
				student = new Student(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) { 
			AdminController.callAlert("[Search Name by StudentID] �̸�ã�� ���� : ������ ���Կ� ������ �߻��߾��.");
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
		return student;
	}

	//7. [SEARCH] All where studentID=? �ش� ������ �л�ã��
	public static ObservableList<Student> searchSelectedStuData(String tID) {
			ObservableList<Student> dbStuByTIDList = FXCollections.observableArrayList();
		
			//	.1  (������)database selected records (where sName=?) in student table
			//select * from studenttbl where studentID= 
			//(select studentID from paymenttbl where teacherID='cherry@nate.com');

			StringBuffer searchStuDataByTID = new StringBuffer();
			searchStuDataByTID.append("select *, count(*) from studentTbl ");
			searchStuDataByTID.append("where studentID = (select studentID from paymenttbl where teacherID= ? )");
			// .2 BRING DataBase Connection
			Connection con = null;
			// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
			PreparedStatement psmt = null;
			// .4 ������ ���� �� �����;� ��  ���ڵ带 ����ִ�  Set ��ü 
			ResultSet rs = null;
			try {
				con = DBUtility.getConnection();
				psmt = con.prepareStatement(searchStuDataByTID.toString());
				psmt.setString(1, tID);			
				
				// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
				// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
				rs = psmt.executeQuery();

				if (rs == null) {
					AdminController.callAlert("[Search Selected Student] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
					return null;
				}
				int resultCount =0;
				while(rs.next()) {
					Student student = new Student(rs.getString(1),rs.getString(2), 
							rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
					dbStuByTIDList.add(student);
					resultCount += rs.getInt(8);
				}
				if(resultCount==0) {
					//AdminController.callAlert("[Search TeacherID on StudentTbl] : '"+tID+"'�� �������� �ʽ��ϴ�. ���� ID�� �� �� �� Ȯ�����ּ���.");
				}
			} catch (SQLException e) { 
				AdminController.callAlert("[Search Selected Class] IDã�� ���� : ������ ���࿡ ������ �߻��߾��.");
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
			return dbStuByTIDList;
	}
		 
	//8. [UPDATE] ���� ����

	//call updateStudent('11@nate.com', '01022227777','1212','stu1549203922741_camera.png');
	public static int updateStudentInfo(Student student) {
		int count =0;
		//.1 ������ �ۼ�
		StringBuffer insertStudent = new StringBuffer();
		//delete from studenttbl where studentID='22@nate.com';
		insertStudent.append("call updateStudent(?,?,?,?) ");
	
		
		// .2 BRING DataBase Connection
				Connection con = null;
				// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
				PreparedStatement psmt = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());

					// .4�������� ���� ������ ����
					psmt.setString(1, student.getStudentID());				
					psmt.setString(2, student.getPhone());
					psmt.setString(3, student.getSPassword());
					psmt.setString(4, student.getImage());
					// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
					count = psmt.executeUpdate();

					if (count == 0) {
						AdminController.callAlert("UPDATE ������ ���� ���� : ������ �����ϴ� ���� ������ �߻��߾��.");
						return count;
					}
					//AdminController.callAlert("���� UPDATE : ����");
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
