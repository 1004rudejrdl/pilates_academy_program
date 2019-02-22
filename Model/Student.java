package Model;

public class Student {
	private String studentID;
	private String sPassword;
	private String sName;
	private String phone;
	private String gender;
	private String birthDate;
	private String image;
	
	
	public Student(String studentID, String sPassword, String sName, String phone, String gender, String birthDate,
			String image) {
		super();
		this.studentID = studentID;
		this.sPassword = sPassword;
		this.sName = sName;
		this.phone = phone;
		this.gender = gender;
		this.birthDate = birthDate;
		this.image = image;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getSPassword() {
		return sPassword;
	}
	public void setSPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public String getSName() {
		return sName;
	}
	public void setSName(String sName) {
		this.sName = sName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
