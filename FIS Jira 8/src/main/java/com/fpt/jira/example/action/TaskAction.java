package com.fpt.jira.example.action;

import javax.inject.Inject;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.fpt.jira.common.constant.DateConstant;

public class TaskAction extends JiraWebActionSupport {

	private static final long serialVersionUID = 1L;
	
	
	private ApplicationProperties applicationProperties = ComponentAccessor.getApplicationProperties();
	  
	@Inject 
	public TaskAction() {
	}
	

	@Override
	protected String doExecute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}


	
	public String getDateFormat() {
		return applicationProperties.getDefaultBackedString(APKeys.JIRA_DATE_PICKER_JAVASCRIPT_FORMAT);
	}
	
	public String getDateFormatText() {
		return DateConstant.DATE_PATTERN_D_MMM_YY;
	}


}
