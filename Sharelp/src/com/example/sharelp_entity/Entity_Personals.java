package com.example.sharelp_entity;

import java.io.Serializable;

public class Entity_Personals implements Serializable{
	
	private String sno;
	private String sname;
	private String grade;
	private String depart;
	private String major;
	private String phonenum;
	private String email;
	private String speciality;
	private String selfinfo;
	private String experience;
	private String intent;
	private String isjoin;
	private String sex;
	
	
	public Entity_Personals() {
		// TODO Auto-generated constructor stub
	}


	public Entity_Personals(String sno, String sname, String grade,
			String depart, String major, String phonenum, String email,
			String speciality, String selfinfo, String experience,
			String intent, String isjoin,String sex) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.grade = grade;
		this.depart = depart;
		this.major = major;
		this.phonenum = phonenum;
		this.email = email;
		this.speciality = speciality;
		this.selfinfo = selfinfo;
		this.experience = experience;
		this.intent = intent;
		this.isjoin = isjoin;
		this.sex=sex;
	}


	public String getSno() {
		return sno;
	}


	public void setSno(String sno) {
		this.sno = sno;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getDepart() {
		return depart;
	}


	public void setDepart(String depart) {
		this.depart = depart;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getPhonenum() {
		return phonenum;
	}


	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSpeciality() {
		return speciality;
	}


	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}


	public String getSelfinfo() {
		return selfinfo;
	}


	public void setSelfinfo(String selfinfo) {
		this.selfinfo = selfinfo;
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}


	public String getIntent() {
		return intent;
	}


	public void setIntent(String intent) {
		this.intent = intent;
	}


	public String getIsjoin() {
		return isjoin;
	}


	public void setIsjoin(String isjoin) {
		this.isjoin = isjoin;
	}

	

	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	@Override
	public String toString() {
		return "Entity_Personals [sno=" + sno + ", sname=" + sname + ", grade="
				+ grade + ", depart=" + depart + ", major=" + major
				+ ", phonenum=" + phonenum + ", email=" + email
				+ ", speciality=" + speciality + ", selfinfo=" + selfinfo
				+ ", experience=" + experience + ", intent=" + intent
				+ ", isjoin=" + isjoin + ", sex=" + sex + "]";
	}



	

}
