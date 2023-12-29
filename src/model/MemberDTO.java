package model;

import java.sql.Date;

public class MemberDTO {
	// PK
	private String MID;
	
	// 이름
	private String mName;
	
	// 비밀번호
	private String mPassword;
	
	// 생년월일
	private Date yob;
	
	// 성별
	private String gender;
	
	// 국적
	private String nationality;
	
	// 전화번호
	private String phoneNumber;
	
	// 주소
	private String address;
	
	// 등급 (user or admin)
	private String grade;
	
	// 건강 상태
	private String health;
	
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPassword() {
		return mPassword;
	}
	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}
	public Date getYob() {
		return yob;
	}
	public void setYob(Date yob) {
		this.yob = yob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [MID=" + MID + ", mName=" + mName + ", mPassword=" + mPassword + ", yob=" + yob + ", gender="
				+ gender + ", nationality=" + nationality + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", grade=" + grade + ", health=" + health + "]";
	}
	
}
