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

	//1.1 [WHERE ID=?] studentTbl에 존재하는 id인지 확인 
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
			//첫번째 물음표 자리 -> studentID 매치 시켜주는 작업 
			psmt.setString(1, studentID);
	
			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문 
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문 
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				resultCount= rs.getInt(1);
			}
			if (resultCount==0) {
				//AdminController.callAlert("LOGIN 실패 : 존재하지 않는 아이디 입니다.");
				return resultCount;
			}
			
		} catch (SQLException e) {
			//AdminController.callAlert("login 실패 : StudentDAO");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		
		
		return resultCount;
	}
	//1.2 [WHERE ID=?] studentTbl에 존재하는 id인지 확인 
	public static int checkPW(String studentID,String pw) {		
		StringBuffer checkStuID = new StringBuffer("select count(*) from studentTbl where sPassword = ? and studentID= ? ");
		int resultCount =0;
		Connection con = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkStuID.toString());
			//첫번째 물음표 자리 -> studentID 매치 시켜주는 작업 
			psmt.setString(1, pw);
			psmt.setString(2, studentID);
			
			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문 
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문 
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				resultCount= rs.getInt(1);
			}
			if (resultCount==0) {
				AdminController.callAlert("LOGIN 실패 : 아이디와 패스워드가 일치하지 않습니다.");
				return resultCount;
			}
			
		} catch (SQLException e) {
			AdminController.callAlert("login 실패 : StudentDAO Check PW");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		
		
		return resultCount;
	}
	
	//1.3 [WHERE ID=?] studentTbl에 존재하는 id인지 확인 
	public static String findIDByPhone(String phone, String name,String date) {		
			StringBuffer checkTchID = new StringBuffer("select studentId from studentTbl where phone = ? and sName= ? and birthDate= ? ");
	 		String resultString =null;
			Connection con = null;
			PreparedStatement psmt = null;
			
			ResultSet rs = null;
			try {
				con = DBUtility.getConnection();
				psmt = con.prepareStatement(checkTchID.toString());
				//첫번째 물음표 자리 -> studentID 매치 시켜주는 작업 
				psmt.setString(1, phone);
				psmt.setString(2, name);
				psmt.setString(3, date);
				System.out.println("###"+phone);
				System.out.println("###"+name);
				System.out.println("###"+date);
		
				// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
				// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문 
				// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문 
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
					resultString= rs.getString(1);
				}
				if (resultString==null) {
					//AdminController.callAlert("LOGIN 실패 : 존재하지 않는 아이디 입니다.");
					return resultString;
				}
				
			} catch (SQLException e) {
				//AdminController.callAlert("login 실패 : StudentDAO");
				e.printStackTrace();
			} finally {
				try {
					// 1.6 CLOSE DataBase psmt object
					// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
					// 반드시 있으면 닫아라.
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
				}
			}
			
			
			return resultString;
		}
	
	//1.4 [WHERE ID=?] studentTbl에 존재하는 id에 맞는 패스워드 인지 확인 
	public static String findPWByPhone(String name, String phone, String date, String email) {
		StringBuffer checkTchID = new StringBuffer("select sPassword, phone from studentTbl where studentId= ? and sName = ? ");
 		String resultString =null;
		Connection con = null;
		PreparedStatement psmt = null;
		
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(checkTchID.toString());
			//첫번째 물음표 자리 -> studentID 매치 시켜주는 작업 
			psmt.setString(1, email);
			System.out.println(email);
			System.out.println("nameee"+name);
			psmt.setString(2, name);
			
	                                                           
			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문 
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문 
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
				AdminController.callAlert("PW찾기 실패 : 정보가 바르지 않습니다.");
				
			}else*/
			if (resultString==null) {
				AdminController.callAlert("[패스워드찾기 실패 ]: 존재하지 않는 아이디 입니다.");
				return resultString;
			}
			
		} catch (SQLException e) {
			//AdminController.callAlert("login 실패 : StudentDAO");
			e.printStackTrace();
		} finally {
			try {
				// 1.6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		
		
		return resultString;
	}

	
	//2. [INSERT] studentTbl에 Record 추가 
	public static int insertStudent(Student student) {
		int count =0;
		//1.1 쿼리문 작성
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("insert into studentTbl ");
		insertStudent.append("(studentID,sPassword,sName,phone,gender,birthDate,image) ");
		insertStudent.append("values ");
		insertStudent.append("(?,?,?,?,?,?,?)");
		
		// 1.2 BRING DataBase Connection
				Connection con = null;
				// 1.3 (쿼리문)MAKE 실행해야할 클래스 Statement
				PreparedStatement psmt = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());

					// 1.4쿼리문의 실제 데이터 연결
					// 얘는 1번 부터 *********
					psmt.setString(1, student.getStudentID());
					psmt.setString(2, student.getSPassword());
					psmt.setString(3, student.getSName());
					psmt.setString(4, student.getPhone());
					psmt.setString(5, student.getGender());
					psmt.setString(6, student.getBirthDate());
					psmt.setString(7, student.getImage());
					
					// 1.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령
					// 잘못되면 0을 준다.
					//executeUpdate는 쿼리문 실행 시 테이블에 저장해줄 때 사용하는 번개문 
					count = psmt.executeUpdate();

					if (count == 0) {
						AdminController.callAlert("삽입 쿼리문 실행 실패 : 쿼리문 실행하는 데에 문제가 발생했어요.");
						return count;
					}

				} catch (SQLException e) {
					AdminController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
					e.printStackTrace();
				} finally {
					try {
						// 1.6 CLOSE DataBase psmt object
						// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
						// 반드시 있으면 닫아라.
						if (psmt != null)
							psmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
						AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

					}
				}
		return count;
	}
	
	//3. [SELECT]studentTbl 가져오기
	public static ArrayList<Student> getStudentTotalData() {
		//	.1  (쿼리문)database all record in student table 
		String selectStudent = "select * from studenttbl ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectStudent);

	
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("select 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while(rs.next()) {
				Student student = new Student(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				dbStuList.add(student);
			}
		} catch (SQLException e) { 
			AdminController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}
		return dbStuList;
	}

	//4. [DELETE] studentTbl Record  
	public static int deleteStudent(Student student) {
		int count =0;
		//.1 쿼리문 작성
		StringBuffer insertStudent = new StringBuffer();
		//delete from studenttbl where studentID='22@nate.com';
		insertStudent.append("delete from studentTbl ");
		insertStudent.append("where studentID=? ");
		
		// .2 BRING DataBase Connection
				Connection con = null;
				// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
				PreparedStatement psmt = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());

					// .4쿼리문의 실제 데이터 연결
					psmt.setString(1, student.getStudentID());				
					
					// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령
					count = psmt.executeUpdate();

					if (count == 0) {
						AdminController.callAlert("DELETE 쿼리문 실행 실패 : 쿼리문 실행하는 데에 문제가 발생했어요.");
						return count;
					}

				} catch (SQLException e) {
					AdminController.callAlert("DELETE 실패 : 데이터 DELETE에 문제가 발생했어요.");
					e.printStackTrace();
				} finally {
					try {
						// .6 CLOSE DataBase psmt object
						if (psmt != null)
							psmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
						AdminController.callAlert("[DELETE]자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

					}
				}
		return count;
	}

	//5. [SEARCH] where sName=? 이름으로 검색
	public static ObservableList<Student> searchByName(String stuName) {
		ObservableList<Student> dbStuNameList = FXCollections.observableArrayList();
		//ex : select * from studentTbl where studentId='11@nate.com';
		//ex: select * from studenttbl where sName=(select replace('김 민 지',' ',''));
		//	.1  (쿼리문)database selected records (where sName=?) in student table 
		String searchStuName = "select * from studenttbl where sName= ? ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, stuName);			
			
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[Search Selected Student] 쿼리문 실행 실패 : [Search Selected Student] 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			
			while(rs.next()) {
				Student selectedStu= new Student(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				dbStuNameList.add(selectedStu);
			}
		} catch (SQLException e) { 
			AdminController.callAlert("[Search Selected Student] 이름찾기 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}
		return dbStuNameList;
	}

	//6. [SEARCH] sName (where studentID=?) 아이디로 이름찾기 
	public static String searchByStuID(String sID) {
		String name="";
		String searchStuName = "select sName from studenttbl where studentID= ? ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, sID);			
			
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[Search Name by StudentID] 쿼리문 실행 실패 : [Search Selected Student] 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}

			while(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) { 
			AdminController.callAlert("[Search Name by StudentID] 이름찾기 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}		
		return name;
	}

	//6. [SEARCH] * (where studentID=?) 아이디로 Record찾기 
	public static Student searchStudentByStuID(String sID) {
		Student student=null;
		String searchStuName = "select * from studenttbl where studentID= ? ";
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchStuName);
			psmt.setString(1, sID);			
			
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[Search Name by StudentID] 쿼리문 실행 실패 : [Search Selected Student] 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			
			while(rs.next()) {
				student = new Student(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) { 
			AdminController.callAlert("[Search Name by StudentID] 이름찾기 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				// .6 CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
				
			}
		}		
		return student;
	}

	//7. [SEARCH] All where studentID=? 해당 선생님 학생찾기
	public static ObservableList<Student> searchSelectedStuData(String tID) {
			ObservableList<Student> dbStuByTIDList = FXCollections.observableArrayList();
		
			//	.1  (쿼리문)database selected records (where sName=?) in student table
			//select * from studenttbl where studentID= 
			//(select studentID from paymenttbl where teacherID='cherry@nate.com');

			StringBuffer searchStuDataByTID = new StringBuffer();
			searchStuDataByTID.append("select *, count(*) from studentTbl ");
			searchStuDataByTID.append("where studentID = (select studentID from paymenttbl where teacherID= ? )");
			// .2 BRING DataBase Connection
			Connection con = null;
			// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
			PreparedStatement psmt = null;
			// .4 쿼리문 실행 후 가져와야 할  레코드를 담고있는  Set 객체 
			ResultSet rs = null;
			try {
				con = DBUtility.getConnection();
				psmt = con.prepareStatement(searchStuDataByTID.toString());
				psmt.setString(1, tID);			
				
				// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
				// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
				rs = psmt.executeQuery();

				if (rs == null) {
					AdminController.callAlert("[Search Selected Student] 쿼리문 실행 실패 : [Search Selected Student] 쿼리문 실행하는 데에 문제가 발생했어요.");
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
					//AdminController.callAlert("[Search TeacherID on StudentTbl] : '"+tID+"'는 존재하지 않습니다. 강사 ID를 한 번 더 확인해주세요.");
				}
			} catch (SQLException e) { 
				AdminController.callAlert("[Search Selected Class] ID찾기 실패 : 데이터 실행에 문제가 발생했어요.");
				e.printStackTrace();
			} finally {
				try {
					// .6 CLOSE DataBase psmt object
					// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
					// 반드시 있으면 닫아라.
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

				}
			}
			return dbStuByTIDList;
	}
		 
	//8. [UPDATE] 정보 수정

	//call updateStudent('11@nate.com', '01022227777','1212','stu1549203922741_camera.png');
	public static int updateStudentInfo(Student student) {
		int count =0;
		//.1 쿼리문 작성
		StringBuffer insertStudent = new StringBuffer();
		//delete from studenttbl where studentID='22@nate.com';
		insertStudent.append("call updateStudent(?,?,?,?) ");
	
		
		// .2 BRING DataBase Connection
				Connection con = null;
				// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
				PreparedStatement psmt = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());

					// .4쿼리문의 실제 데이터 연결
					psmt.setString(1, student.getStudentID());				
					psmt.setString(2, student.getPhone());
					psmt.setString(3, student.getSPassword());
					psmt.setString(4, student.getImage());
					// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령
					count = psmt.executeUpdate();

					if (count == 0) {
						AdminController.callAlert("UPDATE 쿼리문 실행 실패 : 쿼리문 실행하는 데에 문제가 발생했어요.");
						return count;
					}
					//AdminController.callAlert("성공 UPDATE : 성공");
				} catch (SQLException e) {
					AdminController.callAlert("UPDATE 실패 : 데이터 DELETE에 문제가 발생했어요.");
					e.printStackTrace();
				} finally {
					try {
						// .6 CLOSE DataBase psmt object
						if (psmt != null)
							psmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
						AdminController.callAlert("[DELETE]자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

					}
				}
		return count;
	}


	
}
