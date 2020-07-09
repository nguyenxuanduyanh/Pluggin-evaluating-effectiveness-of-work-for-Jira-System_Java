package com.fpt.jira.example.impl;

import com.fpt.jira.example.entity.SearchTaskResult;

public interface TaskService {
	public SearchTaskResult search(int id, String name, String assignee,String project, String fromStr, String toStr, int recordsPerPage, int page);

}
