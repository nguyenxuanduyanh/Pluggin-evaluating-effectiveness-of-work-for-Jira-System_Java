package com.fpt.jira.example.repository;

import com.fpt.jira.example.entity.SearchEmployeeResult;

public interface EmployeeRepository {
	public SearchEmployeeResult  searchEmployee(int id, String name,String email,String account, String fromDate, String toDate,int recordsPerPage, int page);

}
