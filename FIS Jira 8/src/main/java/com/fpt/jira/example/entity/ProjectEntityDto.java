package com.fpt.jira.example.entity;

public class ProjectEntityDto {
	private int id;
	private String name;
	private String leader;
	private int punctualtask;
	private int earlytask;
	private int delayedtask;
	private int totaltask;
	private double progression;
	public double getProgression() {
		return progression;
	}
	public void setProgression(double progression) {
		this.progression = progression;
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
	public int getTotaltask() {
		return totaltask;
	}
	public void setTotaltask(int totaltask) {
		this.totaltask = totaltask;
	}
	
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public ProjectEntityDto(int id, String name,String leader, int punctualtask, int earlytask, int delayedtask, int totaltask, double progression) {
		super();
		this.id = id;
		this.name = name;
		this.leader=leader;
		this.punctualtask = punctualtask;
		this.earlytask = earlytask;
		this.delayedtask = delayedtask;
		this.totaltask = totaltask;
		this.progression=progression;
	}
	public ProjectEntityDto() {
		// TODO Auto-generated constructor stub
	}
	
	
}
