package com.fpt.jira.example.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.fpt.jira.common.constant.DateConstant;
import com.fpt.jira.example.entity.SearchTaskResult;
import com.fpt.jira.example.entity.TaskEntityDto;
import com.fpt.jira.example.repository.TaskRepository;
@Component
public class TaskServiceImpl implements TaskService{
	private TaskRepository taskRepository;
	@Inject
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository=taskRepository;
	}
	@Override
	public SearchTaskResult search(int id, String name, String assignee,String project, String fromStr, String toStr, int recordsPerPage, int page) {
		Date from = null;
		Date to=null;
		try {
			from = new SimpleDateFormat(DateConstant.DATE_PATTERN_D_MMM_YY).parse(fromStr);
			to=new SimpleDateFormat(DateConstant.DATE_PATTERN_D_MMM_YY).parse(toStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String fromDate = "";
	String toDate = "";
	if(from != null) {
		fromDate = sdf.format(from);
		
	}
	if(to !=null) {
		toDate=sdf.format(to);
	}
		SearchTaskResult searchTaskResult = taskRepository.searchTask(id, name, assignee, project, fromDate, toDate, recordsPerPage, page);
		List<TaskEntityDto> taskEntityDtoList = new ArrayList<>();
		taskEntityDtoList=searchTaskResult.getTaskEntityDtoList();	
		
		Date current=null;
		double totalDay=0;
		double diffDay=0;
		double percentDay=0;
		
		double progression=0;
		try {
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yy");
			Date date = new Date();
			current=new SimpleDateFormat(DateConstant.DATE_PATTERN_D_MMM_YY).parse(formatter.format(date));
		
		} catch (ParseException e) {
			 //TODO Auto-generated catch block
				e.printStackTrace();
	}

			for(TaskEntityDto taskEntityDto : taskEntityDtoList) {
				from = taskEntityDto.getFrom();
				to = taskEntityDto.getTo();
				
				
				totalDay= (to.getTime()-from.getTime())/(24 * 60 * 60 * 1000);
				if(current.getTime()<from.getTime()) {
				
					progression=0;
				}
				else {
					diffDay=((current.getTime()-from.getTime())/(24 * 60 * 60 * 1000))+1;
					if(current.getTime()<to.getTime()) {
						percentDay=(diffDay/totalDay)*100;
					}
					else {
						percentDay=100;
					}
					progression= taskEntityDto.getComplete()/percentDay*100;
					
				}
				taskEntityDto.setProgression(round(progression,0));
				searchTaskResult.setTaskEntityDtoList(taskEntityDtoList);
				
			}
				
		
		return searchTaskResult;
	}
	public Double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
