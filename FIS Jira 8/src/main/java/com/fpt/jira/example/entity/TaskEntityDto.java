package com.fpt.jira.example.entity;

import java.util.Date;

public class TaskEntityDto {
	private int id;

private String name;
private String assignee;
private String project;
private Date from;
private Date to;
private double complete;
private double progression;

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
public String getAssignee() {
	return assignee;
}
public void setAssignee(String assignee) {
	this.assignee = assignee;
}
public String getProject() {
	return project;
}
public void setProject(String project) {
	this.project = project;
}
public Date getFrom() {
	return from;
}
public void setFrom(Date from) {
	this.from = from;
}
public Date getTo() {
	return to;
}
public void setTo(Date to) {
	this.to = to;
}
public double getComplete() {
	return complete;
}
public void setComplete(double complete) {
	this.complete = complete;
}
public double getProgression() {
	return progression;
}
public void setProgression(double progression) {
	this.progression=progression;
}

public TaskEntityDto(int id, String name, String assignee, String project, Date from, Date to, double complete,double progression) {
	super();
	this.id = id;
	this.progression=progression;
	this.complete=complete;
	this.name = name;
	this.assignee = assignee;
	this.project = project;
	this.from = from;
	this.to = to;
}
public TaskEntityDto() {
	// TODO Auto-generated constructor stub
}



}

