package com.fpt.jira.example.entity;

import java.util.List;

public class SearchEmployeeResult {
	List<EmployeeEntityDto> employeeEntityDtoList;
	Integer currentPage;
	Integer totalPage;
	Integer totalItem;
	public List<EmployeeEntityDto> getEmployeeEntityDtoList() {
		return employeeEntityDtoList;
	}
	public void setEmployeeEntityDtoList(List<EmployeeEntityDto> employeeEntityDtoList) {
		this.employeeEntityDtoList = employeeEntityDtoList;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	
}
