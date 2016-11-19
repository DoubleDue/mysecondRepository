package com.example.sharelp_entity;

public class Entity_PersonalTeam {
	
	private String sno;
	private String teamname;
	private String teamstate;
	
	public Entity_PersonalTeam() {
		// TODO Auto-generated constructor stub
	}

	public Entity_PersonalTeam(String sno, String teamname, String teamstate) {
		super();
		this.sno = sno;
		this.teamname = teamname;
		this.teamstate = teamstate;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getTeamstate() {
		return teamstate;
	}

	public void setTeamstate(String teamstate) {
		this.teamstate = teamstate;
	}

	@Override
	public String toString() {
		return "Entity_PersonalTeam [sno=" + sno + ", teamname=" + teamname
				+ ", teamstate=" + teamstate + "]";
	}
	
	

}
