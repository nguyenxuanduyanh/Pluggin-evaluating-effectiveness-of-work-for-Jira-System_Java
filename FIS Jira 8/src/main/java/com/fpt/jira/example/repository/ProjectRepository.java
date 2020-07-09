package com.fpt.jira.example.repository;

import com.fpt.jira.example.entity.SearchProjectResult;

public interface ProjectRepository {
	public SearchProjectResult  searchProject(int id, String name,String leader, String fromDate, String toDate,int recordsPerPage, int page);

}
