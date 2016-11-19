package com.example.sharelp_entity;

public class Entity_Comments {
	
	private String title;
	private String comment;
	private String name;
	private String photo;
	
	
	public Entity_Comments() {
		// TODO Auto-generated constructor stub
	}


	public Entity_Comments(String title, String comment, String name,
			String photo) {
		super();
		this.title = title;
		this.comment = comment;
		this.name = name;
		this.photo = photo;
	}


	@Override
	public String toString() {
		return "Entity_Comments [title=" + title + ", comment=" + comment
				+ ", name=" + name + ", photo=" + photo + "]";
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	

}
