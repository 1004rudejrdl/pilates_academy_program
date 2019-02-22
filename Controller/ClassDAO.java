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

	// 1. [INSERT] classTbl�� Record �߰�
	public static String insertClass(String tID, String course, String cType, String classDate, String classTime,
			String classRoom) {
		String result = "����";
		
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
			case "����":
				break;
			case "�ߺ�":
				AdminController.callAlert("[INSERT]Failed! ���� ������ ���� ���� : ���� ��¥, �ð��� �����ϴ� �������� �ߺ��Ǿ����ϴ�.");
				break;
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
		return result;
	}
	// 2. [SELECT] ���� �ð� ������ ������ ��������
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
				AdminController.callAlert("[SELECT]classTbl ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				list.add(classes);
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
		return list;
	}
	// 3. [SELECT] ��ü ������ ��������
	public static ArrayList<Classes> getClassesTotalData() {
		ArrayList<Classes> list = new ArrayList<>();
		// .1 (������)database all record in classTbl
		
		StringBuffer selectClass = new StringBuffer();
		
		selectClass.append("call selectTotalClassData() ");
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectClass.toString());
			
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();
			
			if (rs == null) {
				AdminController.callAlert("[SELECT]classTbl ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}
			while (rs.next()) {
				Classes classes = new Classes(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10), rs.getInt(11));
				list.add(classes);
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
		return list;
	}
	// 4. [DELETE] ���� ����
	public static int deleteClass(Classes classes) {
		int count = 0;
		// .1 ������ �ۼ�
		StringBuffer delClasses = new StringBuffer();
		// delete from classtbl where classInfo='2019013012A';
		delClasses.append("delete from classTbl ");
		delClasses.append("where classInfo=? ");

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(delClasses.toString());

			// .4�������� ���� ������ ����
			psmt.setString(1, classes.getClassInfo());

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
			count = psmt.executeUpdate();

			if (count == 0) {
				System.out.println(count);
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
	// 5. [SEARCH] where teacherID=? id�� �˻�
	public static ObservableList<Classes> searchSelectedClsData(String tID) {
		ObservableList<Classes> dbClsByTIDList = FXCollections.observableArrayList();

		// .1 (������)database selected records (where sName=?) in student table
		// call selectClassDataBySID('cherry@nate.com');
		String searchClsDataByTID = "call selectClassDataByTID(?) ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchClsDataByTID);
			psmt.setString(1, tID);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

			/*if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Teacher] ������ ���� ���� : [Search Selected Teacher] ������ �����ϴ� ���� ������ �߻��߾��.");
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
						.callAlert("[Search Class By TeacherID] : '" + tID + "'���� ������ �������� �ʽ��ϴ�. ������ ������ּ���.");
			}*/
		} catch (SQLException e) {
//			AdminController.callAlert("[Search Selected Class] IDã�� ���� : ������ ���࿡ ������ �߻��߾��.");
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
		return dbClsByTIDList;
	}
	// 6. [SEARCH] where studentID=? id�� �˻�
	public static ObservableList<Classes> searchSelectedClsBySID(String sID) {
		ObservableList<Classes> dbClsByTIDList = FXCollections.observableArrayList();
		
		// .1 (������)database selected records (where sName=?) in student table
		// call selectClassDataBySID('cherry@nate.com');
		String searchClsDataByTID = "call selectClassDataBySID(?) ";
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchClsDataByTID);
			psmt.setString(1, sID);
			
			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();
			
			/*if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Student] ������ ���� ���� : [Search Selected Student] ������ �����ϴ� ���� ������ �߻��߾��.");
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
				.callAlert("[Search StudentID on StudentTbl] : '" + sID + "'�� ������ �������� �ʽ��ϴ�. ȸ�� ID�� �� �� �� Ȯ�����ּ���.");
			}*/
		} catch (SQLException e) {
//			AdminController.callAlert("[Search Selected Class] IDã�� ���� : ������ ���࿡ ������ �߻��߾��.");
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
				//AdminController.callAlert("�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");
				
			}
		}
		return dbClsByTIDList;
	}
	// 7. [SELECT] classInfo�� ���� cMemberTbl ��������
	public static ArrayList<ClassMember> getCMemberTotalData(String classInfo) {
		// e.g. call selectCMemberByClassData('2019013015C');
		StringBuffer selectClass = new StringBuffer();
		selectClass.append("call selectCMemberByClassData(?) ");

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectClass.toString());
			psmt.setString(1, classInfo);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

		/*	if (rs == null) {
				AdminController.callAlert("[SELECT]cMemberTbl ������ ���� ���� : Select ������ �����ϴ� ���� ������ �߻��߾��.");
				return null;
			}*/
			dbCMemberList.clear();
			while (rs.next()) {
				ClassMember cMmb = new ClassMember(rs.getString(1), rs.getString(2), rs.getString(3));
				dbCMemberList.add(cMmb);
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

		return dbCMemberList;
	}
	// 8. [SEARCH] where classDate=? ��¥�� �˻�
	public static ObservableList<Classes> selectClassDataByDate(String selectedDate) {
		ObservableList<Classes> dbClsByDateList = FXCollections.observableArrayList();
		// call selectClassDataByDate('2019-01-30');

		// .1 (������)database selected records (where sName=?) in classTbl
		String searchClsDataByDate = "call selectClassDataByDate(?) ";

		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		// .4 ������ ���� �� �����;� �� ���ڵ带 ����ִ� Set ��ü
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(searchClsDataByDate);
			psmt.setString(1, selectedDate);

			// .5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� !�����ö�! ����ϴ� ������
			rs = psmt.executeQuery();

		/*	if (rs == null) {
				AdminController.callAlert(
						"[Search Selected Classes] ������ ���� ���� : [Search Selected Classes] ������ �����ϴ� ���� ������ �߻��߾��.");
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
				//AdminController.callAlert("[Search Classes By Date] : ���õ� ��¥ '" + selectedDate + "'���� ��������� �������� �ʽ��ϴ�. \r\n ��¥�� �� �� �� Ȯ�����ּ���.");
			}
		} catch (SQLException e) {
			//AdminController.callAlert("[Search Selected Class] IDã�� ���� : ������ ���࿡ ������ �߻��߾��.");
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
		return dbClsByDateList;
	}
	// 9. [UPDATE] ���� ����
	public static String updateClassInfo(String cInfo,String nCourse,String nType,String nTID ) {
		String result = "����";
		
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
					
				if(result.equals("�ߺ�")){
						AdminController.callAlert("[INSERT]Failed! : ���� ��¥, �ð��� ����� ������ �ֽ��ϴ�.");
						return result;
					}else if(result.equals("����")) {
						return result;
					}
				} catch (SQLException e) {
					AdminController.callAlert("UPDATE ���� : ������ UPDATE�� ������ �߻��߾��.");
					e.printStackTrace();
				} finally {
					try {
						if (psmt != null)
							psmt.close();
						if (con != null)
							con.close();
					} catch (SQLException e) {
						AdminController.callAlert("[UPDATE]�ڿ� �ݱ� ���� : psmt & con (������ �ڿ�) �ݴ� ���� ������ �߻��߾��.");

					}
				}
		return result;
	}
	// 10. [INSERT] cMemberTbl�� Record �߰� + update nowNum from classTbl
	public static String insertClsMemberData(String cInfo, String sID) {
		String numFlag = "����";//�������� �˻� <�����ʰ�>
		String crsFlag = "����";//�������� �˻� <�ڽ�����ġ>
		String dateFlag = "����";//�������� �˻� <������¥>
		String existFlag = "����";//�ߺ��˻� <�ߺ�>
		String sccdOrFl = "����";//�������� �˻� <����>

		// 2.1 ������ �ۼ�
		String insertClsMember = "call insertNewClsMember2(?,?)";

		// 2.2 BRING DataBase Connection
		Connection con = null;
		// 2.3 (������)MAKE �����ؾ��� Ŭ���� Statement
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertClsMember);

			// 1.4�������� ���� ������ ����
			// ��� 1�� ���� *********
			psmt.setString(1, cInfo);
			psmt.setString(2, sID);

			// 1.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���
			// �߸��Ǹ� 0�� �ش�.
			// executeUpdate�� ������ ���� �� ���̺� �������� �� ����ϴ� ������

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
			
			if(numFlag.equals("�����ʰ�")) {
				AdminController.callAlert("[INSERT]Failed! : ���� �ʰ��� �����Դϴ�.");
				return numFlag;
			}else if(crsFlag.equals("�ڽ�����ġ")) {
				AdminController.callAlert("[INSERT]Failed! : �����Ͻ� �ڽ��� ����ġ�մϴ�. \n �����Ͻ� ������ �׷��ڽ��������ڽ��� Ȯ�����ּ���.");
				return numFlag;
			}else if(existFlag.equals("�ߺ�")){
				AdminController.callAlert("[INSERT]Failed! : ���� ��¥, �ð��� ����� ������ �ֽ��ϴ�.");
				return existFlag;
			}else if(dateFlag.equals("������¥")) {
				AdminController.callAlert("[INSERT]Failed! : �̹� ������¥�� ��������, ������ �� �����ϴ�.");
				return existFlag;
			}else if(sccdOrFl.equals("����")) {
				AdminController.callAlert("[INSERT]Failed! : ȸ������ ���� ������ �����ϴ�.");
				return existFlag;
			}
			AdminController.callAlert("[INSERT]Succceed!! : ����Ϸ�Ǿ����ϴ�.");

		} catch (SQLException e) {
			//AdminController.callAlert("���� ���� : ������ ���Կ� ������ �߻��߾��.");
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
		return existFlag;
	}
	// 11. [DELETE] cMemberTbl�� Record ���� + update nowNum from classTbl
	public static String delClsMemberData(String cInfo, String sID) {
		//���� ��¥���� �˻�
		String result = "����";	
		//���� ���� ���̺� ���� ������ ��� -> "����"
		String sccdOrFl = "����";
		// .1 ������ �ۼ�

		String newDel= "call new_delClsMemberData(?, ?)";
		
		// .2 BRING DataBase Connection
		Connection con = null;
		// .3 (������)MAKE �����ؾ��� Ŭ���� Statement
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
			
			System.out.println("���� " + rs );
			
			while (rs.next()) {
				
				result = rs.getString(1);
				sccdOrFl = rs.getString(2);
				System.out.println("��� :::::: result111"+result);
				System.out.println("��� ::::::sccrdOrFl111"+sccdOrFl);
			}
			
			System.out.println("��");
			
			if(result.equals("������¥"))
			{
				AdminController.callAlert("[DELETE]Failed! : �����Ұ� - ������¥�� ����� �����Դϴ�.");
				return result;
				
			}else if(sccdOrFl.equals("����")) {
				AdminController.callAlert("[DELETE]Failed! : �����Ұ� - ������ ������ �����ϴ�.");
				return result;
			}else if(result.equals("����") && sccdOrFl.equals("����")){
				//AdminController.callAlert("[DELETE]Succeed! ���� ���ν��� ���� ����: cMemberTbl �������ν��� �����ϴ� ���� �����߾��.");
			}

		} catch (SQLException e) {
			AdminController.callAlert("�ߺ� : �ߺ��Ǵ� �������ֽ��ϴ�.");
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
		return result;
	}


}
