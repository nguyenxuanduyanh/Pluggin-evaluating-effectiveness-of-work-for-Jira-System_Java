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
import com.fpt.jira.example.entity.EmployeeEntityDto;
import com.fpt.jira.example.entity.SearchEmployeeResult;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
	private final String ID_FIELD = "ID";
	private final String NAME_FIELD = "EMPLOYEE";
	private final String EMAIL_FIELD = "EMAIL";
	private final String ACCOUNT_FIELD = "ACCOUNT";
	

	private final String USER_TABLE_NAME = "cwd_user";
	public SearchEmployeeResult searchEmployee(int id, String name,String email, String account,String fromDate, String toDate,int recordsPerPage, int page) {

		List<EmployeeEntityDto> employeeEntityDtoList = new ArrayList<>();

		SearchEmployeeResult searchEmployeeResult=new SearchEmployeeResult();
		searchEmployeeResult.setCurrentPage(page);
		
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
				queryString2.append(" FROM " + USER_TABLE_NAME + " o");
				queryString2.append(" WHERE 1 = 1");
				if(id>0) {
					queryString2.append(" AND o.id = '" + id + "' " );
				}
				if(name != null && !name.equals("")) {
					queryString2.append(" AND o.display_name = '" + name + "' " );
				}
				if(email != null && !email.equals("")) {
					queryString2.append(" AND o.email_address = '" + email + "' " );
				}
				if(account != null && !account.equals("")) {
					queryString2.append(" AND o.lower_user_name = '" + account + "' " );
				}
				
				ps = conn.prepareStatement(queryString2.toString());
				// Execute Statement
				rs = ps.executeQuery();

				while (rs.next()) {
				 totalItem= rs.getInt("NUMBERITEM"); 
				}
				
			int totalPage=(int) Math.ceil((double) totalItem/recordsPerPage);
			int startingRecord = (page - 1) * recordsPerPage;
			searchEmployeeResult.setTotalPage(totalPage);
			searchEmployeeResult.setTotalItem(totalItem);
			
			
			StringBuilder queryString = new StringBuilder();
			queryString.append(" SELECT o.id AS "+ ID_FIELD+", o.lower_user_name AS " + ACCOUNT_FIELD+ ", o.display_name AS " + NAME_FIELD + ", o.email_address AS "+ EMAIL_FIELD);
			queryString.append(" FROM " + USER_TABLE_NAME + " o");
			
			queryString.append(" WHERE 1 = 1");				
			if(id>0) {
				queryString.append(" AND o.id = '" + id + "' " );
			}
			if(name != null && !name.equals("")) {
				queryString.append(" AND o.display_name = '" + name + "' " );
			}
			if(email != null && !email.equals("")) {
				queryString.append(" AND o.email_address = '" + email + "' " );
			}
			if(account != null && !account.equals("")) {
				queryString.append(" AND o.lower_user_name = '" + account + "' " );
			}
			
			
			queryString.append(" LIMIT " + startingRecord + "," + recordsPerPage );

			ps = conn.prepareStatement(queryString.toString());
			// Execute Statement
			rs = ps.executeQuery();

			while (rs.next()) {
				EmployeeEntityDto temp2 = new EmployeeEntityDto();
				temp2.setId(rs.getInt(ID_FIELD));
				temp2.setName(rs.getString(NAME_FIELD));
				temp2.setAccount(rs.getString(ACCOUNT_FIELD));
				temp2.setEmail(rs.getString(EMAIL_FIELD));

				employeeEntityDtoList.add(temp2);
			}
			searchEmployeeResult.setEmployeeEntityDtoList(employeeEntityDtoList);
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
		
		return searchEmployeeResult;
	}		
		
		
}
