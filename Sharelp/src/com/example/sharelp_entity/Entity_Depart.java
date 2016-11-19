package com.example.sharelp_entity;

public class Entity_Depart {
	
	private int resid;//Í¼Æ¬id
	private String name;//Ãû×Ö
	
	
	public Entity_Depart() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Entity_Depart(int resid, String name) {
		super();
		this.resid = resid;
		this.name = name;
	}


	

	
	@Override
	public String toString() {
		return "Entity_Depart [resid=" + resid + ", name=" + name + "]";
	}


	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
