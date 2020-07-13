package com.fpt.jira.example.repository;

import org.springframework.stereotype.Component;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.jira.ofbiz.DefaultOfBizConnectionFactory;
import com.atlassian.jira.ofbiz.OfBizConnectionFactory;
import com.fpt.jira.common.repository.BaseRepository;
import com.fpt.jira.example.entity.SearchTaskResult;
import com.fpt.jira.example.entity.TaskEntityDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component

public class TaskRepositoryImpl
implements TaskRepository{
	private final String ID_FIELD = "ID";
	private final String NAME_FIELD = "NAME";
	private final String ASSIGNEE_FIELD = "ASSIGNEE";
	private final String PROJECT_FIELD = "PROJECT";
	private final String COMPLETE_FIELD = "COMPLETE";

	private final String STARTDATE_FIELD = "STARTDATE";
	private final String DUEDATE_FIELD = "TODATE";	
	

	
	private final String TASK_TABLE_NAME = "jiraissue";
//	private final String CUSTOMFIELD_TABLE_NAME = "customfield";
//	private final String CUSTOMFIELDVALUE_TABLE_NAME = "customfieldvalue";



	public SearchTaskResult searchTask(int id, String name, String assignee,String project, String fromDate, String toDate, int recordsPerPage, int page) {
		List<TaskEntityDto> taskEntityDtoList = new ArrayList<>();

		SearchTaskResult searchTaskResult=new SearchTaskResult();
		searchTaskResult.setCurrentPage(page);
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int totalItem=0;
		try {
			// Get Jira Connection
			OfBizConnectionFactory connectionFactory = new DefaultOfBizConnectionFactory();
			conn = connectionFactory.getConnection();
			
			StringBuilder queryString2 = new StringBuilder();
			queryString2.append(" SELECT COUNT(*) AS NUMBERITEM" );
				queryString2.append(" FROM " + TASK_TABLE_NAME + " o");
				queryString2.append(" LEFT JOIN customfieldvalue c" );
				queryString2.append(" ON "+ "o.id=c.issue "+" AND c.customfield= '"+10107+"' " );
				queryString2.append(" LEFT JOIN customfieldvalue d" );
				queryString2.append(" ON "+ "o.id=d.issue "+" AND d.customfield= '"+10108+"' " );
				queryString2.append(" LEFT JOIN project p" );
				queryString2.append(" ON "+ "o.project=p.id ");
				queryString2.append(" WHERE 1 = 1");
				if(id>0) {
					queryString2.append(" AND o.id = '" + id + "' " );
				}
				if(name != null && !name.equals("")) {
					queryString2.append(" AND o.SUMMARY = '" + name + "' " );
				}
				if(assignee != null && !assignee.equals("")) {
					queryString2.append(" AND o.ASSIGNEE = '" + assignee + "' " );
				}
				if(project != null && !project.equals("")) {
					queryString2.append(" AND p.pname = '" + project + "' " );
				}
				if(fromDate != null && !fromDate.equals("")) {
					queryString2.append(" AND c.datevalue = '" + fromDate + "' " );
				}
				if(toDate != null && !toDate.equals("")) {
					queryString2.append(" AND o.duedate = '" + toDate + "' " );
				}
				
				ps = conn.prepareStatement(queryString2.toString());
				// Execute Statement
				rs = ps.executeQuery();

				while (rs.next()) {
				 totalItem= rs.getInt("NUMBERITEM"); 
				}
				
			int totalPage=(int) Math.ceil((double) totalItem/recordsPerPage);
			int startingRecord = (page - 1) * recordsPerPage;
			searchTaskResult.setTotalPage(totalPage);
			searchTaskResult.setTotalItem(totalItem);
			
			
			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT o.id AS "+ ID_FIELD+ ", o.SUMMARY AS " + NAME_FIELD + ", o.ASSIGNEE AS "+ ASSIGNEE_FIELD + ", p.pname AS " + PROJECT_FIELD +", d.numbervalue AS " +COMPLETE_FIELD+", c.datevalue AS "+ STARTDATE_FIELD+", o.duedate AS " + DUEDATE_FIELD );
			queryString.append(" FROM " + TASK_TABLE_NAME + " o");
			queryString.append(" LEFT JOIN customfieldvalue c" );
			queryString.append(" ON "+ "o.id=c.issue "+" AND c.customfield= '"+10107+"' " );
			queryString.append(" LEFT JOIN customfieldvalue d" );
			queryString.append(" ON "+ "o.id=d.issue "+" AND d.customfield= '"+10108+"' " );
			queryString.append(" LEFT JOIN project p" );
			queryString.append(" ON "+ "o.project=p.id ");
			queryString.append(" WHERE 1 = 1");
			if(id>0) {
				queryString.append(" AND o.id = '" + id + "' " );
			}
			if(name != null && !name.equals("")) {
				queryString.append(" AND o.SUMMARY = '" + name + "' " );
			}
			if(assignee != null && !assignee.equals("")) {
				queryString.append(" AND o.ASSIGNEE = '" + assignee + "' " );
			}
			if(project != null && !project.equals("")) {
				queryString.append(" AND p.pname = '" + project + "' " );
			}
			if(fromDate != null && !fromDate.equals("")) {
				queryString.append(" AND c.datevalue = '" + fromDate + "' " );
			}
			if(toDate != null && !toDate.equals("")) {
				queryString.append(" AND o.duedate = '" + toDate + "' " );
			}
			
			queryString.append(" LIMIT " + startingRecord + "," + recordsPerPage );

			ps = conn.prepareStatement(queryString.toString());
			// Execute Statement
			rs = ps.executeQuery();

			while (rs.next()) {
				TaskEntityDto temp2 = new TaskEntityDto();
				temp2.setId(rs.getInt(ID_FIELD));
				temp2.setName(rs.getString(NAME_FIELD));
				temp2.setAssignee(rs.getString(ASSIGNEE_FIELD));
				temp2.setProject(rs.getString(PROJECT_FIELD));
				temp2.setFrom(rs.getDate(STARTDATE_FIELD));
				temp2.setTo(rs.getDate(DUEDATE_FIELD));
				temp2.setComplete(rs.getInt(COMPLETE_FIELD));
				taskEntityDtoList.add(temp2);
			}
			searchTaskResult.setTaskEntityDtoList(taskEntityDtoList);
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
		
		return searchTaskResult;

	}

}
