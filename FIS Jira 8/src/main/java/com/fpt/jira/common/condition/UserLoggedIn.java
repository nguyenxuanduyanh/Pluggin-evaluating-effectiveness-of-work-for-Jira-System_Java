package com.fpt.jira.common.condition;

import javax.inject.Inject;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

/**
 * Class for checking condition whether user is logged in or not
 * Can be used to decide whether to show web items or not
 * 
 * @author AnhPT98
 *
 */
@Scanned
public class UserLoggedIn extends AbstractWebCondition
{
	
	@ComponentImport
	private JiraAuthenticationContext jiraAuthenticationContext;

	@Inject
    public UserLoggedIn(JiraAuthenticationContext jiraAuthenticationContext)
    {
        this.jiraAuthenticationContext = jiraAuthenticationContext;
    }

	/**
	 * The actual condition checking
	 */
    public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper)
    {
        return jiraAuthenticationContext.isLoggedInUser();
    }

}
