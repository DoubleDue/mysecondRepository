package com.example.sharelp_entity;

public class Entity_AllContent {
	
	
	private String comment;
	private String name_com;
	private String photo_com;
	private String author;
	private String content;
	private String title;
	private String photo_auth;
	private int comments_amout;
	private int zan_amout;
	
	
	
	
	public Entity_AllContent() {
		// TODO Auto-generated constructor stub
	}
	
	


	public Entity_AllContent(String comment, String name_com, String photo_com,
			String author, String content, String title, String photo_auth,
			int comments_amout, int zan_amout) {
		super();
		this.comment = comment;
		this.name_com = name_com;
		this.photo_com = photo_com;
		this.author = author;
		this.content = content;
		this.title = title;
		this.photo_auth = photo_auth;
		this.comments_amout = comments_amout;
		this.zan_amout = zan_amout;
	}




	@Override
	public String toString() {
		return "Entity_AllContent [comment=" + comment + ", name_com="
				+ name_com + ", photo_com=" + photo_com + ", author=" + author
				+ ", content=" + content + ", title=" + title + ", photo_auth="
				+ photo_auth + ", comments_amout=" + comments_amout
				+ ", zan_amout=" + zan_amout + "]";
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getName_com() {
		return name_com;
	}


	public void setName_com(String name_com) {
		this.name_com = name_com;
	}


	public String getPhoto_com() {
		return photo_com;
	}


	public void setPhoto_com(String photo_com) {
		this.photo_com = photo_com;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
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


	public String getPhoto_auth() {
		return photo_auth;
	}


	public void setPhoto_auth(String photo_auth) {
		this.photo_auth = photo_auth;
	}


	public int getComments_amout() {
		return comments_amout;
	}


	public void setComments_amout(int comments_amout) {
		this.comments_amout = comments_amout;
	}


	public int getZan_amout() {
		return zan_amout;
	}


	public void setZan_amout(int zan_amout) {
		this.zan_amout = zan_amout;
	}
	

}
