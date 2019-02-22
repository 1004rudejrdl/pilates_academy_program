package Model;

import java.time.LocalDate;

public class Payment {
	private String studentID;
	private String sName;
	private String teacherID;
	private String tName;
	private String course;
	private Integer period;
	private Integer lessons;
	private Integer leftLes;
	private String pOption;
	private String tuition;
	private String regiDate;
	private String expiDate;
	
	public Payment(String studentID, String sName, String teacherID, String tName, String course, Integer period,
			Integer lessons, Integer leftLes, String pOption, String tuition, String regiDate, String expiDate) {
		super();
		this.studentID = studentID;
		this.sName = sName;
		this.teacherID = teacherID;
		this.tName = tName;
		this.course = course;
		this.period = period;
		this.lessons = lessons;
		this.leftLes = leftLes;
		this.pOption = pOption;
		this.tuition = tuition;
		this.regiDate = regiDate;
		this.expiDate = expiDate;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getLessons() {
		return lessons;
	}
	public void setLessons(Integer lessons) {
		this.lessons = lessons;
	}
	public Integer getLeftLes() {
		return leftLes;
	}
	public void setLeftLes(Integer leftLes) {
		this.leftLes = leftLes;
	}
	public String getPOption() {
		return pOption;
	}
	public void setPOption(String pOption) {
		this.pOption = pOption;
	}
	public String getTuition() {
		return tuition;
	}
	public void setTuition(String tuition) {
		this.tuition = tuition;
	}
	
	
	public String getRegiDate() {
		return regiDate;
	}


	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}


	public String getExpiDate() {
		return expiDate;
	}


	public void setExpiDate(String expiDate) {
		this.expiDate = expiDate;
	}


	@Override
	public String toString() {
		return "Payment [studentID=" + studentID + ", sName=" + sName + ", teacherID=" + teacherID + ", tName=" + tName
				+ ", course=" + course + ", period=" + period + ", lessons=" + lessons + ", leftLes=" + leftLes
				+ ", pOption=" + pOption + ", tuition=" + tuition + "]";
	}
	
 
}
