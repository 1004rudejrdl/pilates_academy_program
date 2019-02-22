package Model;

public class Teacher {
	private String teacherID; 
	private String tPassword; 
	private String tName; 
	private String phone; 
	private String gender; 
	private String birthDate; 
	private String image;
	public Teacher(String teacherID, String tPassword, String tName, String phone, String gender, String birthDate,
			String image) {
		super();
		this.teacherID = teacherID;
		this.tPassword = tPassword;
		this.tName = tName;
		this.phone = phone;
		this.gender = gender;
		this.birthDate = birthDate;
		this.image = image;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getTPassword() {
		return tPassword;
	}
	public void setTPassword(String tPassword) {
		this.tPassword = tPassword;
	}
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
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
