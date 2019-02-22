package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Health;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class HealthDAO {
	public static ArrayList<Health> dbHealthList = new ArrayList<>();
	
	//1. [INSERT] healthTbl에 Record 추가 
	public static int insertHealth(String sID ,
			String measureDate , Double weight, Double bodyfat,
			Double muscleMass, Double bmi) {
		int count =0;
		StringBuffer insertHealth = new StringBuffer();
		insertHealth.append("call ");
		insertHealth.append("insertHealthData(?,?,?,?,?,?)");
		
				Connection con = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				try {
					con = DBUtility.getConnection();
					psmt = con.prepareStatement(insertHealth.toString());
					psmt.setString(1, sID);
					psmt.setString(2, measureDate);
					psmt.setDouble(3, weight);
					psmt.setDouble(4, bodyfat);
					psmt.setDouble(5, muscleMass);
					psmt.setDouble(6, bmi);		
					
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
	//2. [SELECT] healthTbl 가져오기
	public static ArrayList<Health> getHealthTotalData() {
		StringBuffer selectClass = new StringBuffer();
		selectClass.append("call selectHealthData() ");
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
			while(rs.next()) {
				Health health = new Health(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getDouble(4), rs.getDouble(5), 
						rs.getDouble(6), rs.getDouble(7));
				dbHealthList.add(health);
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
		return dbHealthList;
	}
	//3. [SELECT] healthTbl 가져오기
	public static ObservableList<Health> getHealthSelectedData(String studentID) {
			ObservableList<Health> stuSrchOtherHealthList = FXCollections.observableArrayList();
			StringBuffer stuSrchOhterHealthBySID = new StringBuffer();
			stuSrchOhterHealthBySID.append("call selectHealthDataByID(?) ");
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnection();
				psmt = con.prepareStatement(stuSrchOhterHealthBySID.toString());
				psmt.setString(1, studentID);
				rs = psmt.executeQuery();
				if (rs == null) {
					AdminController.callAlert("[SELECT]Search HealthTbl BY StudentID 쿼리문 실행 실패 : Search HealthTbl BY StudentID 쿼리문 실행하는 데에 문제가 발생했어요.");
					return null;
				}
				int resultCount=0;
				while(rs.next()) {
					Health health = new Health(rs.getString(1),rs.getString(2), 
							rs.getString(3), rs.getDouble(4), rs.getDouble(5), 
							rs.getDouble(6), rs.getDouble(7));
					resultCount+= rs.getInt(8);
					stuSrchOtherHealthList.add(health);
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
			return stuSrchOtherHealthList;
		}
	//4. [SELECT] 월 평균 값들 list로 가져오기
	public static ObservableList<PieChart.Data> getHealthAvgData(String studentID,String yyyydd) {
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		StringBuffer stuHlthPieChrt = new StringBuffer();
		stuHlthPieChrt.append("select round(avg(weight),1) ,  round(avg(bodyfat),1) ,round(avg(muscleMass),1) ,round(avg(bmi),1), count(*) ");
		stuHlthPieChrt.append("from healthtbl where studentID=? and measureDate like ? ");
		
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(stuHlthPieChrt.toString());
			psmt.setString(1, studentID);
			psmt.setString(2, yyyydd+"%");
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID 쿼리문 실행 실패 : Make Chart HealthTbl BY StudentID 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {
				list.add(new PieChart.Data("몸무게 \n"+ rs.getDouble(1)+"kg",rs.getDouble(1)));
				list.add(new PieChart.Data("체지방률 \n"+rs.getDouble(2)+"%" ,rs.getDouble(2)));
				list.add(new PieChart.Data("골격근량 \n"+ rs.getDouble(3)+"kg" ,rs.getDouble(3)));
				list.add(new PieChart.Data("BMI 수치 \n"+rs.getDouble(4)+"kg/m²" ,rs.getDouble(4)));
				resultCount = rs.getInt(5);
			}
			if(resultCount==0) {AdminController.callAlert("[CHECK]ID : 찾으시는 회원의 아이디가 회원별 건강기록에 존재하지 않습니다.");}
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
	//5. [SELECT] 년 평균 값들 list로 가져오기
	public static ObservableList<PieChart.Data> getHealthYearlyAvgData(String studentID,String yyyy) {
		ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
		StringBuffer stuHlthPieChrt = new StringBuffer();
		stuHlthPieChrt.append("select round(avg(weight),1) ,  round(avg(bodyfat),1) ,round(avg(muscleMass),1) ,round(avg(bmi),1), count(*) ");
		stuHlthPieChrt.append("from healthtbl where studentID=? and measureDate like ? ");
		
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(stuHlthPieChrt.toString());
			psmt.setString(1, studentID);
			psmt.setString(2, yyyy+"%");
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID 쿼리문 실행 실패 : Make Chart HealthTbl BY StudentID 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {
				
				list.add(new PieChart.Data("몸무게 \n"+ rs.getDouble(1)+"kg",rs.getDouble(1)));
				list.add(new PieChart.Data("체지방률 \n"+rs.getDouble(2)+"%" ,rs.getDouble(2)));
				list.add(new PieChart.Data("골격근량 \n"+ rs.getDouble(3)+"kg" ,rs.getDouble(3)));
				list.add(new PieChart.Data("BMI 수치 \n"+rs.getDouble(4)+"kg/m²" ,rs.getDouble(4)));
				resultCount = rs.getInt(5);
			}
			if(resultCount==0) {AdminController.callAlert("[CHECK] ID : 찾으시는 회원의 아이디가 회원별 건강기록에 존재하지 않습니다. \n 건강기록을 추가해주세요.");}
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
	//6. [SELECT] 월 데이터 값들 list로 가져오기
	public static XYChart.Series<String, Double>  getHealthDataMonthly(String studentID,String yyyydd, String name, String seriesName) {
	
		ObservableList<Data<String, Double>> list = FXCollections.observableArrayList();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
		series.setName(seriesName);
		StringBuffer stuHlthBarChrt = new StringBuffer();
		stuHlthBarChrt.append("select measureDate, ");
		stuHlthBarChrt.append(name);
		stuHlthBarChrt.append(" from healthtbl ");
		stuHlthBarChrt.append(" where substring(measureDate,1,7) = ? and studentID= ? order by measureDate ");
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(stuHlthBarChrt.toString());
			
			psmt.setString(1, yyyydd);
			psmt.setString(2, studentID);
			
			// .5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 !가져올때! 사용하는 번개문 
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID 쿼리문 실행 실패 : Make Chart HealthTbl BY StudentID 쿼리문 실행하는 데에 문제가 발생했어요.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {				
				System.out.println(rs.getString(1));
				System.out.println(rs.getDouble(2));
				list.add(new Data<String, Double>(rs.getString(1), rs.getDouble(2)));			
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
	//7. [SELECT] 년도별 데이터 값들 list로 가져오기
	public static XYChart.Series<String, Double>  getHealthDataYearly(String studentID,String yyyy, String name, String seriesName) {
		
		ObservableList<Data<String, Double>> list = FXCollections.observableArrayList();
		XYChart.Series<String, Double> series = new XYChart.Series<>();
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
				System.out.println(rs.getString(1));
				System.out.println(rs.getDouble(2));
				list.add(new Data<String, Double>(rs.getString(1), rs.getDouble(2)));			
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
