package com.example.sharelp_entity;

import java.io.Serializable;

public class Entity_Teams implements Serializable{


	private String category;
	private String teamname;
	private String sno;
	private String teamintro;
	private String teamnum;
	private String teamtutor;
	private String honour;
	private String projectintro;
	
	public Entity_Teams() {
		// TODO Auto-generated constructor stub
	}

	public Entity_Teams(String category, String teamname, String sno,
			String teamintro, String teamnum, String teamtutor, String honour,
			String projectintro) {
		super();
		this.category = category;
		this.teamname = teamname;
		this.sno = sno;
		this.teamintro = teamintro;
		this.teamnum = teamnum;
		this.teamtutor = teamtutor;
		this.honour = honour;
		this.projectintro = projectintro;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getTeamintro() {
		return teamintro;
	}

	public void setTeamintro(String teamintro) {
		this.teamintro = teamintro;
	}

	public String getTeamnum() {
		return teamnum;
	}

	public void setTeamnum(String teamnum) {
		this.teamnum = teamnum;
	}

	public String getTeamtutor() {
		return teamtutor;
	}

	public void setTeamtutor(String teamtutor) {
		this.teamtutor = teamtutor;
	}

	public String getHonour() {
		return honour;
	}

	public void setHonour(String honour) {
		this.honour = honour;
	}

	public String getProjectintro() {
		return projectintro;
	}

	public void setProjectintro(String projectintro) {
		this.projectintro = projectintro;
	}

	@Override
	public String toString() {
		return "Entity_Teams [category=" + category + ", teamname=" + teamname
				+ ", sno=" + sno + ", teamintro=" + teamintro + ", teamnum="
				+ teamnum + ", teamtutor=" + teamtutor + ", honour=" + honour
				+ ", projectintro=" + projectintro + "]";
	}
	
}
