package com.fpt.jira.example.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.atlassian.jira.ofbiz.DefaultOfBizConnectionFactory;
import com.atlassian.jira.ofbiz.OfBizConnectionFactory;
import com.fpt.jira.common.constant.DateConstant;
import com.fpt.jira.example.entity.EmployeeEntityDto;
import com.fpt.jira.example.entity.ProjectEntityDto;
import com.fpt.jira.example.entity.SearchEmployeeResult;
import com.fpt.jira.example.entity.SearchProjectResult;
import com.fpt.jira.example.entity.SearchTaskResult;
import com.fpt.jira.example.entity.TaskEntityDto;
import com.fpt.jira.example.repository.EmployeeRepository;
import com.fpt.jira.example.repository.ProjectRepository;
@Component
public class ProjectServiceImpl implements ProjectService {
	private ProjectRepository projectRepository;
	@Inject
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository=projectRepository;
	}
	@Override
	public SearchProjectResult search(int id, String name,String leader,String fromStr, String toStr,int recordsPerPage, int page) {
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
		SearchProjectResult searchProjectResult = projectRepository.searchProject(id, name,leader, fromDate, toDate,recordsPerPage, page);
		
		StringBuilder projectIdList = new StringBuilder();
		List<ProjectEntityDto> projectEntityDtoList =searchProjectResult.getProjectEntityDtoList();
		for(int i=0;i<projectEntityDtoList.size();i++) {
			projectIdList.append("'" + projectEntityDtoList.get(i).getId() + "',");
		}
		List<TaskEntityDto> taskEntityDtoList = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			// Get Jira Connection
			OfBizConnectionFactory connectionFactory = new DefaultOfBizConnectionFactory();
			conn = connectionFactory.getConnection();
			
			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT o.id AS PROJECT_ID"+ ", o.pname AS PROJECT_NAME"+", o.lead AS LEADER"+", d.datevalue AS STARTDATE"+", c.duedate AS DUEDATE"+", e.numbervalue AS COMPLETE");
			queryString.append(" FROM " + "project o");
			queryString.append(" LEFT JOIN " + "jiraissue c" + " ON o.id= c.project");
			queryString.append(" LEFT JOIN " + "customfieldvalue d " + " ON c.id=d.issue AND d.customfield= '"+10107+"' ");
			queryString.append(" LEFT JOIN " + "customfieldvalue e " + " ON c.id=e.issue AND e.customfield= '"+10108+"' ");

			queryString.append(" WHERE o.id in (" + projectIdList.substring(0, projectIdList.length() - 1) + ")");				
				
			if(fromDate != null && !fromDate.equals("")) {
				queryString.append(" AND d.datevalue >= '" + fromDate + "' " );
			}
			if(toDate != null && !toDate.equals("")) {
				queryString.append(" AND c.duedate <= '" + toDate + "' " );
			}
				ps = conn.prepareStatement(queryString.toString());
				// Execute Statement
				rs = ps.executeQuery();

				while (rs.next()) {
					TaskEntityDto temp = new TaskEntityDto();
					temp.setId(rs.getInt("PROJECT_ID"));
					temp.setFrom(rs.getDate("STARTDATE"));
					temp.setTo(rs.getDate("DUEDATE"));
					temp.setComplete(rs.getInt("COMPLETE"));

					taskEntityDtoList.add(temp);
				}
				Date current=null;
				double totalDay=0;
				double diffDay=0;
				double percentDay=0;
				double task_progression=0;
				double project_progression=0;
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
					
						task_progression=0;
					}
					else {
						diffDay=((current.getTime()-from.getTime())/(24 * 60 * 60 * 1000))+1;
						if(current.getTime()<to.getTime()) {
							percentDay=(diffDay/totalDay)*100;
						}
						else {
							percentDay=100;
						}
						task_progression= taskEntityDto.getComplete()/percentDay*100;
						
					}
					taskEntityDto.setProgression(round(task_progression,0));
				}
		
				for(int i=0;i<projectEntityDtoList.size();i++) {
					int totaltask=0;
					int delayed_task=0;
					int punctual_task=0;
					int early_task=0;
					for(int j=0; j <taskEntityDtoList.size();j++) {
						if(taskEntityDtoList.get(j).getId()==projectEntityDtoList.get(i).getId()) {
							totaltask+=1;
							if(95 <= taskEntityDtoList.get(j).getProgression()&&taskEntityDtoList.get(j).getProgression()<=105) {
								punctual_task+=1;
							
						}
							else if(taskEntityDtoList.get(j).getProgression()>=105) {
								early_task+=1;
							}
							else {
								delayed_task+=1;
							}
							
					}
				}
					project_progression=((double)(early_task+punctual_task))/totaltask*100;
					projectEntityDtoList.get(i).setTotaltask(totaltask);

					projectEntityDtoList.get(i).setPunctualtask(punctual_task);
					projectEntityDtoList.get(i).setEarlytask(early_task);
					projectEntityDtoList.get(i).setDelayedtask(delayed_task);
					projectEntityDtoList.get(i).setProgression(round(project_progression,0));
			}
				
				
				
				searchProjectResult.setProjectEntityDtoList(projectEntityDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
										
				
				return searchProjectResult;
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
