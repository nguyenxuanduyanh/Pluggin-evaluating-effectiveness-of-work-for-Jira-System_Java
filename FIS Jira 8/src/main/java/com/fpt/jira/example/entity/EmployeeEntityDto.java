package com.fpt.jira.example.entity;


public class EmployeeEntityDto {
	private int id;

	private String name;
	private String email;
	private String account;
	
	
	private int punctualtask;
	private int earlytask;
	private int delayedtask;
	private String comment;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPunctualtask() {
		return punctualtask;
	}
	public void setPunctualtask(int punctualtask) {
		this.punctualtask = punctualtask;
	}
	public int getEarlytask() {
		return earlytask;
	}
	public void setEarlytask(int earlytask) {
		this.earlytask = earlytask;
	}
	public int getDelayedtask() {
		return delayedtask;
	}
	public void setDelayedtask(int delayedtask) {
		this.delayedtask = delayedtask;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public EmployeeEntityDto(int id, String name, String email,String account, int punctualtask, 
			int earlytask, int delayedtask, String comment) {
		super();
		this.id = id;
		this.name = name;
		this.account=account;
		this.email = email;
		this.punctualtask = punctualtask;
		this.earlytask = earlytask;
		this.delayedtask = delayedtask;
		this.comment = comment;
		
	}
	public EmployeeEntityDto() {
		// TODO Auto-generated constructor stub
	}
	
}
