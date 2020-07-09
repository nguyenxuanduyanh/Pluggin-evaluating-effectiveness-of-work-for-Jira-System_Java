package com.fpt.jira.example.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fpt.jira.example.impl.TaskService;
import com.google.gson.Gson;

@Named
@Path("task") 
public class TaskRest {
	TaskService taskservice;	
	Gson gson = new Gson();
	@Inject
	public TaskRest(TaskService taskservice) {
		this.taskservice = taskservice;
	}
	@GET //localhost :8080/rest/1.0/fisJiraRest/user/getAll
	@Path("/search")
	@Produces({ "application/json" })
	public Response search( 
			@QueryParam("id") int id,
			@QueryParam("name") String name,
			@QueryParam("assignee") String assignee,
			@QueryParam("project") String project,
			@QueryParam("fromStr") String fromStr,
			@QueryParam("toStr") String toStr, 
			@QueryParam("recordsPerPage")int recordsPerPage, 
			@QueryParam("page")int page
			) 
			{
		return Response.ok(gson.toJson(taskservice.search(id,name,assignee,project,fromStr,toStr,recordsPerPage,page))).build();
	}
}

