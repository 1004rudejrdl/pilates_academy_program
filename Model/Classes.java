package Model;

public class Classes {
	private String classInfo;
	private String teacherID;
	private String tName;
	private String tImage;
	private String course;
	private String cType;
	private String classDate;
	private String classTime;
	private String classRoom;
	private int limitNum;
	private int nowNum;	
	

	public Classes(String classInfo, String teacherID, String tName, String tImage, String course, String cType, String classDate,
			String classTime, String classRoom, int limitNum, int nowNum) {
		super();
		this.classInfo = classInfo;
		this.teacherID = teacherID;
		this.tName = tName;
		this.tImage = tImage;
		this.course = course;
		this.cType = cType;
		this.classDate = classDate;
		this.classTime = classTime;
		this.classRoom = classRoom;
		this.limitNum = limitNum;
		this.nowNum = nowNum;
	}
	public String getClassInfo() {
		return classInfo;
	}
	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
	public String getTImage() {
		return tImage;
	}
	public void setTImage(String tImage) {
		this.tImage = tImage;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCType() {
		return cType;
	}
	public void setCType(String cType) {
		this.cType = cType;
	}
	public String getClassDate() {
		return classDate;
	}
	public void setClassDate(String classDate) {
		this.classDate = classDate;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	public int getNowNum() {
		return nowNum;
	}
	public void setNowNum(int nowNum) {
		this.nowNum = nowNum;
	}
	

}
