package Model;

public class ClassMember {
	private String classInfo;
	private String studentID;
	private String sName;
	
	public ClassMember(String classInfo, String studentID, String sName) {
		super();
		this.classInfo = classInfo;
		this.studentID = studentID;
		this.sName = sName;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getSName() {
		return sName;
	}
	public void setSName(String sName) {
		this.sName = sName;
	}
	
}
