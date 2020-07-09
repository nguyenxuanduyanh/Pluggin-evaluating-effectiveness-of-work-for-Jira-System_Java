package com.fpt.jira.example.repository;

import com.fpt.jira.example.entity.SearchTaskResult;

public interface TaskRepository {
	public SearchTaskResult  searchTask(int id, String name, String assignee,String project, String fromDate, String toDate,int recordsPerPage, int page);
}
