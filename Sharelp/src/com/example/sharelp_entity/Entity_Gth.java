package com.example.sharelp_entity;

import java.io.Serializable;


public class Entity_Gth implements Serializable{

	private String path;
	private String name;
	private String prize;
	private String author;
	private String introduction;






	public Entity_Gth() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Entity_Gth [path=" + path + ", name=" + name + ", prize="
				+ prize + ", author=" + author + ", introduction="
				+ introduction + "]";
	}



	public String getIntroduction() {
		return introduction;
	}



	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}




	public String getAuthor() {
		return author;
	}





	public void setAuthor(String author) {
		this.author = author;
	}





	public Entity_Gth(String path, String name, String prize, String author,
			String introduction) {
		super();
		this.path = path;
		this.name = name;
		this.prize = prize;
		this.author = author;
		this.introduction = introduction;
	}





	public String getPrize() {
		return prize;
	}



	public void setPrize(String prize) {
		this.prize = prize;
	}



	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
