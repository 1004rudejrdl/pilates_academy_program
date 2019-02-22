package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.ClassMember;
import Model.Classes;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;

public class ClassDAO {
	public static ArrayList<Classes> dbClsList = new ArrayList<>();
	public static ArrayList<ClassMember> dbCMemberList = new ArrayList<>();

	// 1. [INSERT] classTbl에 Record 추가
	public static String insertClass(String tID, String course, String cType, String classDate, String classTime,
			String classRoom) {
		String result = "성공";
		
		StringBuffer insertClass = new StringBuffer();
		insertClass.append("call ");
		insertClass.append("insertClassData(?,?,?,?,?,?)");
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClass.toString());

			psmt.setString(1, tID);
			psmt.setString(2, course);
			psmt.setString(3, cType);
			psmt.setString(4, classDate);
			psmt.setString(5, classTime);
			psmt.setString(6, classRoom);

			rs = psmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}

			switch (result) {
			case "성공":
				break;
			case "중복":
				AdminController.callAlert("[INSERT]Failed! 삽입 쿼리문 실행 실패 : 동일 날짜, 시간에 강의하는 선생님이 중복되었습니다.");
				break;
			}

		} catch (SQLException e) {
			AdminController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

			}
		}
		return result;
	}
	// 2. [SELECT] 현재 시간 이후의 수업들 가져오기
	public static ArrayList<Classes> getClassesDataFromCurDate() {
		ArrayList<Classes> list = new ArrayList<>();

		StringBuffer selectClass = new StringBuffer();

		selectClass.append("call selectClassDataFromCurDate() ");
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectClass.toString());
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[SELECT]classTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				list.add(classes);
			}
		} catch (SQLException e) {
			AdminController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		return list;
	}
	// 3. [SELECT] 전체 수업들 가져오기
	public static ArrayList<Classes> getClassesTotalData() {
		ArrayList<Classes> list = new ArrayList<>();
		// .1 (쿼리문)database all record in classTbl
		
		StringBuffer selectClass = new StringBuffer();
		
		selectClass.append("call selectTotalClassData() ");
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할 레코드를 담고있는 Set 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectClass.toString());
			
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]classTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				list.add(classes);
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
		return list;
	}
	// 4. [DELETE] 수업 삭제
	public static int deleteClass(Classes classes) {
		int count = 0;
		// .1 쿼리문 작성
		StringBuffer delClasses = new StringBuffer();
		// delete from classtbl where classInfo='2019013012A';
		delClasses.append("delete from classTbl ");
		delClasses.append("where classInfo=? ");

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(delClasses.toString());

			// .4쿼리문의 실제 데이터 연결
			psmt.setString(1, classes.getClassInfo());

			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령
			count = psmt.executeUpdate();

			if (count == 0) {
				System.out.println(count);
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
	// 5. [SEARCH] where teacherID=? id로 검색
	public static ObservableList<Classes> searchSelectedClsData(String tID) {
		ObservableList<Classes> dbClsByTIDList = FXCollections.observableArrayList();

		// .1 (쿼리문)database selected records (where sName=?) in student table
		// call selectClassDataBySID('cherry@nate.com');
		String searchClsDataByTID = "call selectClassDataByTID(?) ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할 레코드를 담고있는 Set 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchClsDataByTID);
			psmt.setString(1, tID);

			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문
			rs = psmt.executeQuery();

			/*if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Teacher] 쿼리문 실행 실패 : [Search Selected Teacher] 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}*/
			int resultCount = 0;
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				resultCount = rs.getInt(12);
				dbClsByTIDList.add(classes);
			}
		/*	if (resultCount == 0) {
				AdminController
						.callAlert("[Search Class By TeacherID] : '" + tID + "'님의 수업이 존재하지 않습니다. 수업을 등록해주세요.");
			}*/
		} catch (SQLException e) {
//			AdminController.callAlert("[Search Selected Class] ID찾기 실패 : 데이터 실행에 문제가 발생했어요.");
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
		return dbClsByTIDList;
	}
	// 6. [SEARCH] where studentID=? id로 검색
	public static ObservableList<Classes> searchSelectedClsBySID(String sID) {
		ObservableList<Classes> dbClsByTIDList = FXCollections.observableArrayList();
		
		// .1 (쿼리문)database selected records (where sName=?) in student table
		// call selectClassDataBySID('cherry@nate.com');
		String searchClsDataByTID = "call selectClassDataBySID(?) ";
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할 레코드를 담고있는 Set 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchClsDataByTID);
			psmt.setString(1, sID);
			
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문
			rs = psmt.executeQuery();
			
			/*if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Student] 쿼리문 실행 실패 : [Search Selected Student] 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}*/
			int resultCount = 0;
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				resultCount = rs.getInt(12);
				dbClsByTIDList.add(classes);
			}
			/*if (resultCount == 0) {
				AdminController
				.callAlert("[Search StudentID on StudentTbl] : '" + sID + "'의 수업이 존재하지 않습니다. 회원 ID를 한 번 더 확인해주세요.");
			}*/
		} catch (SQLException e) {
//			AdminController.callAlert("[Search Selected Class] ID찾기 실패 : 데이터 실행에 문제가 발생했어요.");
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
				//AdminController.callAlert("자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
				
			}
		}
		return dbClsByTIDList;
	}
	// 7. [SELECT] classInfo를 통해 cMemberTbl 가져오기
	public static ArrayList<ClassMember> getCMemberTotalData(String classInfo) {
		// e.g. call selectCMemberByClassData('2019013015C');
		StringBuffer selectClass = new StringBuffer();
		selectClass.append("call selectCMemberByClassData(?) ");

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할 레코드를 담고있는 Set 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectClass.toString());
			psmt.setString(1, classInfo);

			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문
			rs = psmt.executeQuery();

		/*	if (rs == null) {
				AdminController.callAlert("[SELECT]cMemberTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}*/
			dbCMemberList.clear();
			while (rs.next()) {
				ClassMember cMmb = new ClassMember(rs.getString(1), rs.getString(2), rs.getString(3));
				dbCMemberList.add(cMmb);
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

		return dbCMemberList;
	}
	// 8. [SEARCH] where classDate=? 날짜로 검색
	public static ObservableList<Classes> selectClassDataByDate(String selectedDate) {
		ObservableList<Classes> dbClsByDateList = FXCollections.observableArrayList();
		// call selectClassDataByDate('2019-01-30');

		// .1 (쿼리문)database selected records (where sName=?) in classTbl
		String searchClsDataByDate = "call selectClassDataByDate(?) ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		// .4 쿼리문 실행 후 가져와야 할 레코드를 담고있는 Set 객체
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchClsDataByDate);
			psmt.setString(1, selectedDate);

			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문
			rs = psmt.executeQuery();

		/*	if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Classes] 쿼리문 실행 실패 : [Search Selected Classes] 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}*/
			int resultCount = 0;
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				resultCount = rs.getInt(12);
				dbClsByDateList.add(classes);
			}
			if (resultCount == 0) {
				//AdminController.callAlert("[Search Classes By Date] : 선택된 날짜 '" + selectedDate + "'에는 수업목록이 존재하지 않습니다. \r\n 날짜를 한 번 더 확인해주세요.");
			}
		} catch (SQLException e) {
			//AdminController.callAlert("[Search Selected Class] ID찾기 실패 : 데이터 실행에 문제가 발생했어요.");
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
		return dbClsByDateList;
	}
	// 9. [UPDATE] 정보 수정
	public static String updateClassInfo(String cInfo,String nCourse,String nType,String nTID ) {
		String result = "성공";
		
		ResultSet rs = null;
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("call updateClass(?,?,?,?) ");
	
				Connection con = null;
				PreparedStatement psmt = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());
					psmt.setString(1,cInfo);
					psmt.setString(2, nCourse);
					psmt.setString(3, nType);
					psmt.setString(4, nTID);
					rs = psmt.executeQuery();
					while (rs.next()) {
						result = rs.getString(1);
					}
					
				if(result.equals("중복")){
						AdminController.callAlert("[INSERT]Failed! : 동일 날짜, 시간에 예약된 수업이 있습니다.");
						return result;
					}else if(result.equals("성공")) {
						return result;
					}
				} catch (SQLException e) {
					AdminController.callAlert("UPDATE 실패 : 데이터 UPDATE에 문제가 발생했어요.");
					e.printStackTrace();
				} finally {
					try {
						if (psmt != null)
							psmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
						AdminController.callAlert("[UPDATE]자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");

					}
				}
		return result;
	}
	// 10. [INSERT] cMemberTbl에 Record 추가 + update nowNum from classTbl
	public static String insertClsMemberData(String cInfo, String sID) {
		String numFlag = "성공";//결제내역 검사 <정원초과>
		String crsFlag = "성공";//결제내역 검사 <코스불일치>
		String dateFlag = "성공";//결제내역 검사 <지난날짜>
		String existFlag = "성공";//중복검사 <중복>
		String sccdOrFl = "성공";//결제내역 검사 <실패>

		// 2.1 쿼리문 작성
		String insertClsMember = "call insertNewClsMember2(?,?)";

		// 2.2 BRING DataBase Connection
		Connection con = null;
		// 2.3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClsMember);

			// 1.4쿼리문의 실제 데이터 연결
			// 얘는 1번 부터 *********
			psmt.setString(1, cInfo);
			psmt.setString(2, sID);

			// 1.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령
			// 잘못되면 0을 준다.
			// executeUpdate는 쿼리문 실행 시 테이블에 저장해줄 때 사용하는 번개문

			rs = psmt.executeQuery();
			while (rs.next()) {
				numFlag = rs.getString(1);
				crsFlag = rs.getString(2);
				dateFlag = rs.getString(3);
				existFlag = rs.getString(4);
				sccdOrFl = rs.getString(5);
				//System.out.println("*num & crsflag & dateFlag*"+ numFlag + crsFlag + dateFlag);
				//System.out.println("* existFlag& sccdOrFl "+ existFlag + sccdOrFl);				
			}
			
			if(numFlag.equals("정원초과")) {
				AdminController.callAlert("[INSERT]Failed! : 정원 초과된 수업입니다.");
				return numFlag;
			}else if(crsFlag.equals("코스불일치")) {
				AdminController.callAlert("[INSERT]Failed! : 결제하신 코스와 불일치합니다. \n 결제하신 내역의 그룹코스ㆍ개인코스를 확인해주세요.");
				return numFlag;
			}else if(existFlag.equals("중복")){
				AdminController.callAlert("[INSERT]Failed! : 동일 날짜, 시간에 예약된 수업이 있습니다.");
				return existFlag;
			}else if(dateFlag.equals("지난날짜")) {
				AdminController.callAlert("[INSERT]Failed! : 이미 지난날짜의 수업으로, 예약할 수 없습니다.");
				return existFlag;
			}else if(sccdOrFl.equals("실패")) {
				AdminController.callAlert("[INSERT]Failed! : 회원님의 결제 내역이 없습니다.");
				return existFlag;
			}
			AdminController.callAlert("[INSERT]Succceed!! : 예약완료되었습니다.");

		} catch (SQLException e) {
			//AdminController.callAlert("삽입 실패 : 데이터 삽입에 문제가 발생했어요.");
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
		return existFlag;
	}
	// 11. [DELETE] cMemberTbl에 Record 삭제 + update nowNum from classTbl
	public static String delClsMemberData(String cInfo, String sID) {
		//지난 날짜인지 검사
		String result = "성공";	
		//성공 여부 테이블에 없는 내역인 경우 -> "없음"
		String sccdOrFl = "성공";
		// .1 쿼리문 작성

		String newDel= "call new_delClsMemberData(?, ?)";
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (쿼리문)MAKE 실행해야할 클래스 Statement
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(newDel);
			
			
			
			psmt.setString(1, cInfo);
			psmt.setString(2, sID);
			
			System.out.println(cInfo);
			System.out.println(sID);
			
			rs = psmt.executeQuery();
			
			System.out.println("시작 " + rs );
			
			while (rs.next()) {
				
				result = rs.getString(1);
				sccdOrFl = rs.getString(2);
				System.out.println("결과 :::::: result111"+result);
				System.out.println("결과 ::::::sccrdOrFl111"+sccdOrFl);
			}
			
			System.out.println("끝");
			
			if(result.equals("지난날짜"))
			{
				AdminController.callAlert("[DELETE]Failed! : 삭제불가 - 지난날짜에 예약된 수업입니다.");
				return result;
				
			}else if(sccdOrFl.equals("없음")) {
				AdminController.callAlert("[DELETE]Failed! : 삭제불가 - 삭제할 수업이 없습니다.");
				return result;
			}else if(result.equals("성공") && sccdOrFl.equals("성공")){
				//AdminController.callAlert("[DELETE]Succeed! 삭제 프로시저 실행 성공: cMemberTbl 삭제프로시저 실행하는 데에 성공했어요.");
			}

		} catch (SQLException e) {
			AdminController.callAlert("중복 : 중복되는 수업이있습니다.");
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
		return result;
	}


}
