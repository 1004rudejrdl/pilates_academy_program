package Model;

public class Health {
	private String studentID;
	private String sName;
	private String measureDate;
	private Double weight;
	private Double bodyfat;
	private Double muscleMass;
	private Double bmi;
	public Health(String studentID, String sName, String measureDate, Double weight, Double bodyfat, Double muscleMass,
			Double bmi) {
		super();
		this.studentID = studentID;
		this.sName = sName;
		this.measureDate = measureDate;
		this.weight = weight;
		this.bodyfat = bodyfat;
		this.muscleMass = muscleMass;
		this.bmi = bmi;
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
	public String getMeasureDate() {
		return measureDate;
	}
	public void setMeasureDate(String measureDate) {
		this.measureDate = measureDate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getBodyfat() {
		return bodyfat;
	}
	public void setBodyfat(Double bodyfat) {
		this.bodyfat = bodyfat;
	}
	public Double getMuscleMass() {
		return muscleMass;
	}
	public void setMuscleMass(Double muscleMass) {
		this.muscleMass = muscleMass;
	}
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	
	
}
