package com.example.sharelp_entity;


public class Entity_Share {
	private String name;
	private String content;
	private String title;
	private String photo;
	private int comments;
	private int zan;
	
	public Entity_Share() {
	}

	

	public Entity_Share(String name, String content, String title,
			String photo, int comments, int zan) {
		super();
		this.name = name;
		this.content = content;
		this.title = title;
		this.photo = photo;
		this.comments = comments;
		this.zan = zan;
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



	@Override
	public String toString() {
		return "Entity_Share [name=" + name + ", content=" + content
				+ ", title=" + title + ", photo=" + photo + ", comments="
				+ comments + ", zan=" + zan + "]";
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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
	

}
