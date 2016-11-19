package com.example.sharelp_entity;
public class Entity_Tutor {
	
	private String name;
	private String prize;
	private String tel;
	private String depart;
	private String photo;
	
	
	public Entity_Tutor() {
		// TODO Auto-generated constructor stub
	}


	public Entity_Tutor(String name, String prize, String tel, String depart,
			String photo) {
		super();
		this.name = name;
		this.prize = prize;
		this.tel = tel;
		this.depart = depart;
		this.photo = photo;
	}


	@Override
	public String toString() {
		return "Entity_Tutor [name=" + name + ", prize=" + prize + ", tel="
				+ tel + ", depart=" + depart + ", photo=" + photo + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPrize() {
		return prize;
	}


	public void setPrize(String prize) {
		this.prize = prize;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getDepart() {
		return depart;
	}


	public void setDepart(String depart) {
		this.depart = depart;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}
	

}
