package com.example.sharelp_entity;


public class Entity_GP {
	private String name;
	private String grade;
	private String photo;
	private String content;
	private String tel;
	
	
	public Entity_GP() {
	}

	

	public Entity_GP(String name, String grade, String photo, String content,
			String tel) {
		super();
		this.name = name;
		this.grade = grade;
		this.photo = photo;
		this.content = content;
		this.tel = tel;
	}





	@Override
	public String toString() {
		return "Entity_GP [name=" + name + ", grade=" + grade + ", photo="
				+ photo + ", content=" + content + ", tel=" + tel + "]";
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}


	

}
