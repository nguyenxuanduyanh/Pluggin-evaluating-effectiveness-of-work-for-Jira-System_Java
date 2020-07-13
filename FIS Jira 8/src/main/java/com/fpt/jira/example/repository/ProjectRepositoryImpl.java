package com.fpt.jira.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atlassian.jira.ofbiz.DefaultOfBizConnectionFactory;
import com.atlassian.jira.ofbiz.OfBizConnectionFactory;
import com.fpt.jira.example.entity.ProjectEntityDto;
import com.fpt.jira.example.entity.SearchProjectResult;

@Component
public class ProjectRepositoryImpl implements ProjectRepository {
	private final String ID_FIELD = "ID";
	private final String NAME_FIELD = "PROJECT";
	private final String LEADER_FIELD="LEADER";
	

	private final String PROJECT_TABLE_NAME = "project";
	public SearchProjectResult searchProject(int id, String name,String leader,String fromDate, String toDate,int recordsPerPage, int page) {

		List<ProjectEntityDto> projectEntityDtoList = new ArrayList<>();

		SearchProjectResult searchProjectResult=new SearchProjectResult();
		searchProjectResult.setCurrentPage(page);
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int totalItem=0;
		try {
			// Get Jira Connection
			OfBizConnectionFactory connectionFactory = new DefaultOfBizConnectionFactory();
			conn = connectionFactory.getConnection();
			
			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT COUNT(*) AS NUMBERITEM" );
				queryString.append(" FROM " + PROJECT_TABLE_NAME + " o");
				queryString.append(" WHERE 1 = 1");
				if(id>0) {
					queryString.append(" AND o.id = '" + id + "' " );
				}
				if(name != null && !name.equals("")) {
					queryString.append(" AND o.pname = '" + name + "' " );
				}
				if( leader!= null && !leader.equals("")) {
					queryString.append(" AND o.lead = '" + leader + "' " );
				}
				
				
				ps = conn.prepareStatement(queryString.toString());
				// Execute Statement
				rs = ps.executeQuery();

				while (rs.next()) {
				 totalItem= rs.getInt("NUMBERITEM"); 
				}
				
			int totalPage=(int) Math.ceil((double) totalItem/recordsPerPage);
			int startingRecord = (page - 1) * recordsPerPage;
			searchProjectResult.setTotalPage(totalPage);
			searchProjectResult.setTotalItem(totalItem);
			
			
			StringBuilder queryString2 = new StringBuilder();
			queryString2.append(" SELECT o.id AS "+ ID_FIELD+ ", o.pname AS " + NAME_FIELD + ", o.lead AS "+ LEADER_FIELD);
			queryString2.append(" FROM " + PROJECT_TABLE_NAME + " o");
			
			queryString2.append(" WHERE 1 = 1");				
			if(id>0) {
				queryString2.append(" AND o.id = '" + id + "' " );
			}
			if(name != null && !name.equals("")) {
				queryString2.append(" AND o.pname = '" + name + "' " );
			}
			if( leader!= null && !leader.equals("")) {
				queryString2.append(" AND o.lead = '" + leader + "' " );
			}
			
			queryString2.append(" LIMIT " + startingRecord + "," + recordsPerPage );

			ps = conn.prepareStatement(queryString2.toString());
			// Execute Statement
			rs = ps.executeQuery();

			while (rs.next()) {
				ProjectEntityDto temp2 = new ProjectEntityDto();
				temp2.setId(rs.getInt(ID_FIELD));
				temp2.setName(rs.getString(NAME_FIELD));
				temp2.setLeader(rs.getString(LEADER_FIELD));
//				temp2.setEarlytask(0);
//				temp2.setPunctualtask(0);
				projectEntityDtoList.add(temp2);
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
		
		
}
