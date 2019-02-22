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
	
	//1. [INSERT] healthTbl�� Record �߰� 
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
	//2. [SELECT] healthTbl ��������
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
				AdminController.callAlert("[SELECT]classTbl ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while(rs.next()) {
				Health health = new Health(rs.getString(1),rs.getString(2), 
						rs.getString(3), rs.getDouble(4), rs.getDouble(5), 
						rs.getDouble(6), rs.getDouble(7));
				dbHealthList.add(health);
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
		return dbHealthList;
	}
	//3. [SELECT] healthTbl ��������
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
					AdminController.callAlert("[SELECT]Search HealthTbl BY StudentID ������ ���� ���� : Search HealthTbl BY StudentID ������ �����ϴ� ���� ������ �߻��߾��.");
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
			return stuSrchOtherHealthList;
		}
	//4. [SELECT] �� ��� ���� list�� ��������
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
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID ������ ���� ���� : Make Chart HealthTbl BY StudentID ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {
				list.add(new PieChart.Data("������ \n"+ rs.getDouble(1)+"kg",rs.getDouble(1)));
				list.add(new PieChart.Data("ü����� \n"+rs.getDouble(2)+"%" ,rs.getDouble(2)));
				list.add(new PieChart.Data("��ݱٷ� \n"+ rs.getDouble(3)+"kg" ,rs.getDouble(3)));
				list.add(new PieChart.Data("BMI ��ġ \n"+rs.getDouble(4)+"kg/m��" ,rs.getDouble(4)));
				resultCount = rs.getInt(5);
			}
			if(resultCount==0) {AdminController.callAlert("[CHECK]ID : ã���ô� ȸ���� ���̵� ȸ���� �ǰ���Ͽ� �������� �ʽ��ϴ�.");}
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
		return list;
	}
	//5. [SELECT] �� ��� ���� list�� ��������
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
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID ������ ���� ���� : Make Chart HealthTbl BY StudentID ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			int resultCount=0;
			while(rs.next()) {
				
				list.add(new PieChart.Data("������ \n"+ rs.getDouble(1)+"kg",rs.getDouble(1)));
				list.add(new PieChart.Data("ü����� \n"+rs.getDouble(2)+"%" ,rs.getDouble(2)));
				list.add(new PieChart.Data("��ݱٷ� \n"+ rs.getDouble(3)+"kg" ,rs.getDouble(3)));
				list.add(new PieChart.Data("BMI ��ġ \n"+rs.getDouble(4)+"kg/m��" ,rs.getDouble(4)));
				resultCount = rs.getInt(5);
			}
			if(resultCount==0) {AdminController.callAlert("[CHECK] ID : ã���ô� ȸ���� ���̵� ȸ���� �ǰ���Ͽ� �������� �ʽ��ϴ�. \n �ǰ������ �߰����ּ���.");}
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
		return list;
	}
	//6. [SELECT] �� ������ ���� list�� ��������
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
			
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������ 
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID ������ ���� ���� : Make Chart HealthTbl BY StudentID ������ �����ϴ� ���� ������ �߻��߾��.");
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
		return series;
	}
	//7. [SELECT] �⵵�� ������ ���� list�� ��������
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
				AdminController.callAlert("[SELECT]Make Chart HealthTbl BY StudentID ������ ���� ���� : Make Chart HealthTbl BY StudentID ������ �����ϴ� ���� ������ �߻��߾��.");
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
		return series;
	}
}
