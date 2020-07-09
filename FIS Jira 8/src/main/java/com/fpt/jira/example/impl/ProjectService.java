package com.fpt.jira.example.impl;

import com.fpt.jira.example.entity.SearchProjectResult;

public interface ProjectService {
	public SearchProjectResult search(int id, String name,String leader,String fromStr, String toStr,int recordsPerPage, int page);

}
