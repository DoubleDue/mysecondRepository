package com.example.sharelp_entity;

public class Entity_Shares {

	private String name;
	private String content;
	private String title;
	private int comments;
	private int zan;
	
	public Entity_Shares() {
		// TODO Auto-generated constructor stub
	}
	public Entity_Shares(String name, String content, String title,
			int comments, int zan) {
		super();
		this.name = name;
		this.content = content;
		this.title = title;
		this.comments = comments;
		this.zan = zan;
	}
	@Override
	public String toString() {
		return "Entity_Shares [name=" + name + ", content=" + content
				+ ", title=" + title + ", comments=" + comments + ", zan="
				+ zan + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getZan() {
		return zan;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	
	
	
}
