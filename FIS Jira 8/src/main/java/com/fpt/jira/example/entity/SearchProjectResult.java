package com.fpt.jira.example.entity;

import java.util.List;

public class SearchProjectResult {
	List<ProjectEntityDto> projectEntityDtoList;
	Integer currentPage;
	Integer totalPage;
	Integer totalItem;
	public List<ProjectEntityDto> getProjectEntityDtoList() {
		return projectEntityDtoList;
	}
	public void setProjectEntityDtoList(List<ProjectEntityDto> projectEntityDtoList) {
		this.projectEntityDtoList = projectEntityDtoList;
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
