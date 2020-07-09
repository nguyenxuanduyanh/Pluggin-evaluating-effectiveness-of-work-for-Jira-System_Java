package com.fpt.jira.example.entity;

import java.util.List;

public class SearchTaskResult {
	List<TaskEntityDto> taskEntityDtoList;
	Integer currentPage;
	Integer totalPage;
	Integer totalItem;
	
	public Integer getTotalItem() {
		return totalItem;
	}
	
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public SearchTaskResult() {
		
	}
	
	public void setTaskEntityDtoList(List<TaskEntityDto> taskEntityDtoList) {
		this.taskEntityDtoList = taskEntityDtoList;
	}
	
	public List<TaskEntityDto> getTaskEntityDtoList() {
		return this.taskEntityDtoList;
	}
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public Integer getCurrentPage() {
		return this.currentPage;
	}
}
