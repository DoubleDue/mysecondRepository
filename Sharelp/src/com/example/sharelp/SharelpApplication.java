package com.example.sharelp;

import android.app.Application;

public class SharelpApplication extends Application{
	
	private boolean state;
	private String sno;
	private String sname;
	private String photo;
	private boolean personlized;
	
	public SharelpApplication() {
		// TODO Auto-generated constructor stub
	}

	public SharelpApplication(boolean state, String sno, String sname,
			String photo, boolean personlized) {
		super();
		this.state = state;
		this.sno = sno;
		this.sname = sname;
		this.photo = photo;
		this.personlized = personlized;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isPersonlized() {
		return personlized;
	}

	public void setPersonlized(boolean personlized) {
		this.personlized = personlized;
	}

	@Override
	public String toString() {
		return "SharelpApplication [state=" + state + ", sno=" + sno
				+ ", sname=" + sname + ", photo=" + photo + ", personlized="
				+ personlized + "]";
	}
	
	
	

	

}
