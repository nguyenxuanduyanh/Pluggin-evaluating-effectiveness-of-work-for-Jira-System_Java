package com.fpt.jira.example.impl;

import com.fpt.jira.example.entity.SearchEmployeeResult;

public interface EmployeeService {
	public SearchEmployeeResult search(int id, String name,String email,String account,String fromStr, String toStr,int recordsPerPage, int page);

}
