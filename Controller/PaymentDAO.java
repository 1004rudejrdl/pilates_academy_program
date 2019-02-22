package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Model.Payment;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class PaymentDAO {

	public static ArrayList<Payment> dbPmtList = new ArrayList<>();
	private static Calendar cal;
	// Lesson per Month
	public static final Integer LESSON = 12;

	// 1.  [INSERT] paymentTbl에 Record 추가
	public static int insertPayment(Payment payment) {

		int count = 0;
		ResultSet rs = null;
		String result = "";
		String insertPayment = ("call insertPayment(?,?,?,?,?,?,?,?,?,?) ");

		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertPayment);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try {
				System.out.println(payment.getRegiDate());
				date = format.parse(payment.getRegiDate());
				cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.MONTH, payment.getPeriod()); 
				System.out.println("날짜 확인" + format.format(cal.getTime()));
				payment.setExpiDate(format.format(cal.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (payment.getCourse().equals("그룹")) {
				payment.setTeacherID("그룹");
			}

			psmt.setString(1, payment.getStudentID());
			psmt.setString(2, payment.getTeacherID());
			psmt.setString(3, payment.getCourse());
			psmt.setInt(4, payment.getPeriod());
			psmt.setInt(5, payment.getLessons());
			psmt.setString(6, payment.getTuition());
			psmt.setString(7, payment.getPOption());
			psmt.setInt(8, payment.getLeftLes());
			psmt.setString(9, payment.getRegiDate());
			psmt.setString(10, payment.getExpiDate());

			count = psmt.executeUpdate();

			if (count == 0) {
				AdminController.callAlert("PAYMENT INSERT 실패 : 데이터 삽입에 문제가 발생했어요.");
			}

		} catch (SQLException e) {
			AdminController.callAlert("PAYMENT INSERT 실패 : 데이터 삽입에 문제가 발생했어요.");
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
		return count;
	}
	// 2.  [SELECT] paymentTbl 모두 가져오기
	public static ArrayList<Payment> getPaymentTotalData() {
		StringBuffer selectPayment = new StringBuffer();
		selectPayment.append(
				"select s.studentID, s.sName, t.teacherID, t.tName, p.course, p.period,p.lessons, p.leftLes, p.pOption, p.tuition,p.regiDate, p.expiDate ");
		selectPayment.append("from studenttbl s ");
		selectPayment.append("inner join paymenttbl p ");
		selectPayment.append("on s.studentID = p.studentID ");
		selectPayment.append("inner join teachertbl t ");
		selectPayment.append("on t.teacherID = p.teacherID ");

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPayment.toString());
			rs = psmt.executeQuery();
			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			System.out.println(rs);
			while (rs.next()) {
				Payment payment = new Payment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));

				dbPmtList.add(payment);
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
		return dbPmtList;
	}
	// 2.1 [SELECT] paymentTbl By StudentID
	public static ArrayList<Payment> getPaymentTotalDataBySID(String studentID) {
		ArrayList<Payment> list = new ArrayList<>();
		StringBuffer selectPayment = new StringBuffer();
		selectPayment.append(
				"select s.studentID, s.sName, t.teacherID, t.tName, p.course, p.period,p.lessons, p.leftLes, p.pOption, p.tuition,p.regiDate, p.expiDate ");
		selectPayment.append("from studenttbl s ");
		selectPayment.append("inner join paymenttbl p ");
		selectPayment.append("on s.studentID = p.studentID ");
		selectPayment.append("inner join teachertbl t ");
		selectPayment.append("on t.teacherID = p.teacherID ");
		selectPayment.append(" where p.studentID=? order by p.regiDate ");

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPayment.toString());
			psmt.setString(1, studentID);
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				Payment payment = new Payment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getDate(11).toString(), rs.getDate(12).toString());
				System.out.println(rs.getString(1));
				list.add(payment);
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
	// 3.  [SEARCH] where sID=? 아이디으로 검색
	public static ObservableList<Payment> searchPaymentSelectedData(String sID) {
		ObservableList<Payment> pmtList = FXCollections.observableArrayList();
		StringBuffer stuSrchOhterPaymentBySID = new StringBuffer();
		stuSrchOhterPaymentBySID.append("call selectPaymentDataByID(?) ");

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(stuSrchOhterPaymentBySID.toString());
			psmt.setString(1, sID);

			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert(
						"[SELECT]Search PAYMENTTBL BY StudentID 쿼리문 실행 실패 : Search PAYMENTTBL BY StudentID 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			int resultCount = 0;
			while (rs.next()) {
				Payment payment = new Payment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
				resultCount += rs.getInt(11);
				pmtList.add(payment);
			}
			if (resultCount == 0) {
				AdminController.callAlert("[CHECK]ID  : 찾으시는 회원의 아이디가 수강등록자료에 존재하지 않습니다.");
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
		return pmtList;
	}
	// 4.  [MAX] regiDate 회원아이디로 검색
	public static String getMaxRegiDateBySID(String studentID) {
		String selectPayment = "SELECT max(regiDate) from paymenttbl where studentID=?";
		String regiMax = "";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectPayment);

			psmt.setString(1, studentID);
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				regiMax = rs.getString(1);
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
		return regiMax;
	}
	// 5.  [MAX] expiDate 회원아이디로 검색
	public static String getMaxExpiDateBySID(String studentID) {
		String selectMaxExpi = "SELECT max(expiDate) from paymenttbl where studentID=?";
		String maxExpi = "";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectMaxExpi);
			psmt.setString(1, studentID);
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				maxExpi = rs.getString(1);
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
		return maxExpi;
	}
	// 6.  [MIN] regiDate-curDate 회원아이디로 검색
	public static String getCloseRegiDate(String studentID) {

		StringBuffer selectCloseRegi = new StringBuffer();
		selectCloseRegi.append("SELECT regiDate, expiDate from paymenttbl where studentID=? ");
		selectCloseRegi.append("having regiDate-curdate() = (select min(regiDate - curdate())  from paymenttbl ");
		selectCloseRegi.append("where studentID=?) ");

		String closeRegi = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectCloseRegi.toString());
			psmt.setString(1, studentID);
			psmt.setString(2, studentID);
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				closeRegi = rs.getString(1) + rs.getString(2);
				System.out.println(closeRegi);
			}
			if (closeRegi==null) {
				AdminController.callAlert("결제 내역 없음 : 회원님의 결제 내역이 존재하지 않습니다.");
				return null;
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
		return closeRegi;
	}
	// 7.  [SELECT] 회원별 남은 횟수 
	public static String getLeftLesCloseRegiDate(String studentID) {
		StringBuffer selectLeftLes = new StringBuffer();
		selectLeftLes.append("select sum(leftLes) , expiDate from paymenttbl where studentID=? ");
		selectLeftLes.append("having expiDate-curdate() > 0 ");
		String leftLes = "";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectLeftLes.toString());
			psmt.setString(1, studentID);
			rs = psmt.executeQuery();

			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				leftLes = rs.getString(1);
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
		return leftLes;
	}
	// 8.  [SELECT] 회원별 total Lessons
	public static String getTtlLessonsCloseRegiDate(String studentID) {
		StringBuffer selectTtlLessons = new StringBuffer();
		
		selectTtlLessons.append("select sum(lessons) , expiDate from paymenttbl where studentID=? ");
		selectTtlLessons.append("having expiDate-curdate() > 0 ");
		
		String ttlLessons = "";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectTtlLessons.toString());
			
			psmt.setString(1, studentID);
			rs = psmt.executeQuery();
			if (rs == null) {
				AdminController.callAlert("[SELECT]paymentTbl 쿼리문 실행 실패 : Select 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			while (rs.next()) {
				ttlLessons = rs.getString(1);
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
		return ttlLessons;
	}
	// 9.  [SELECT] 월별 매출
	public static int getSalesRevenueByMM(String yyyy, String mm) {
		int revenue=0;
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("call getSalesRevenueByMM(?,?) ");
	
				Connection con = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertStudent.toString());
					psmt.setString(1, yyyy);				
					psmt.setString(2, mm);
					rs = psmt.executeQuery();
					while (rs.next()) {
						revenue  = rs.getInt(1);
					}
					
				} catch (SQLException e) {
					AdminController.callAlert("CALL 실패 : 데이터 GET에 문제가 발생했어요.");
					e.printStackTrace();
				} finally {
					try {
						if (psmt != null)
							psmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
						AdminController.callAlert("[CALL]자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
					}
				}
		
		return revenue;
	}
	// 10. [SELECT] 강사별 매출
	public static int getSalesRevenueByT(String teacherID) {
		int revenue=0;
		StringBuffer insertStudent = new StringBuffer();
		insertStudent.append("call getSalesRevenueByT(?) ");
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertStudent.toString());
			psmt.setString(1, teacherID);				
			rs = psmt.executeQuery();
			while (rs.next()) {
				revenue  = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			AdminController.callAlert("CALL 실패 : 데이터 GET에 문제가 발생했어요.");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AdminController.callAlert("[CALL]자원 닫기 실패 : psmt & con (데이터 자원) 닫는 데에 문제가 발생했어요.");
			}
		}
		
		return revenue;
	}
	// 11. [SELECT] 강사별 월 데이터 값들 list로 가져오기
	public static 	Data<String, Integer> getPmtDataQuarteryByT(String teacherID,String yyyy, String seriesQuarter) {
		
		Data<String, Integer> ddaa = null;
		String stuHlthBarChrt = new String("call getSalesYearlyByT(?,?,?) ");
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(stuHlthBarChrt);
			psmt.setString(1, teacherID);
			psmt.setString(2, yyyy);
			psmt.setString(3, seriesQuarter.substring(0,1));
			rs = psmt.executeQuery();
			if (rs == null) {
				AdminController.callAlert("[SELECT]Make Chart PaymentTbl BY TeacherID  쿼리문 실행 실패 : Make Chart PaymentTbl BY TeacherID 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {	
				ddaa = new Data<String, Integer>(seriesQuarter, rs.getInt(2));			
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
		return ddaa;
	}
	// 12. [SELECT] 월 데이터 값들 list로 가져오기
	public static XYChart.Series<String, Integer>  getHealthDataYearly(String studentID,String yyyy, String name, String seriesName) {
		
		ObservableList<Data<String, Integer>> list = FXCollections.observableArrayList();
		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		series.setName(seriesName);
		StringBuffer stuHlthBarChrt = new StringBuffer();
		stuHlthBarChrt.append("select measureDate, ");
		stuHlthBarChrt.append(name);
		stuHlthBarChrt.append(" from healthtbl ");
		stuHlthBarChrt.append(" where substring(measureDate,1,4) = ? and studentID= ? order by measureDate");
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(stuHlthBarChrt.toString());
			psmt.setString(1, yyyy);
			psmt.setString(2, studentID);
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID 쿼리문 실행 실패 : Make Chart HealthTbl BY StudentID 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {				
				list.add(new Data<String, Integer>(rs.getString(1), rs.getInt(2)));			
			}
			series.setData(list);
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
		return series;
	}

}









































