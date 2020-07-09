package com.fpt.jira.example.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fpt.jira.example.impl.ProjectService;
import com.google.gson.Gson;

@Named
@Path("project") 
public class ProjectRest {
	ProjectService projectservice;	
	Gson gson = new Gson();
	@Inject
	public ProjectRest(ProjectService projectservice) {
		this.projectservice = projectservice;
	}
	@GET //localhost :8080/rest/1.0/fisJiraRest/user/getAll
	@Path("/search")
	@Produces({ "application/json" })
	public Response search( 
			@QueryParam("id") int id,
			@QueryParam("name") String name,
			@QueryParam("leader") String leader,
			@QueryParam("fromStr") String fromStr,
			@QueryParam("toStr") String toStr, 
			@QueryParam("recordsPerPage")int recordsPerPage, 
			@QueryParam("page")int page
			) 
			{
		return Response.ok(gson.toJson(projectservice.search(id,name,leader,fromStr,toStr,recordsPerPage,page))).build();
	}
}

