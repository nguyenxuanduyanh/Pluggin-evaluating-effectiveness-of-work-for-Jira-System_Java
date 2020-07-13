AJS.toInit(function() {	
	
	// validator for creating project 	
	$.validator.addMethod("string_validator", function(value, element){
		if(value.includes("*")){
			return false;
		}
		else if(value.includes("#")){
			return false;
		}
		else if(value.includes("@")){
			return false;
		}
		else if(value.includes("^")){
			return false;
		}
		else if(value.includes("&")){
			return false;
		}else{
			return true;
		}
	
	});
	$.validator.addMethod("date_validator", function(value, element){
		var d=new Date();
		present_time=d.getTime();
		var txtpicked_date=AJS.$("#dateComboBox").val();
		var picked_date= new Date(moment(txtpicked_date,dateFormatText).toDate().getTime());
		if(picked_date>present_time){
			return false;
		}else{
			return true;
		}
	
	});
	
	// validator for searching task
	$("#searchProjectForm").validate({
        rules: {
        	inputProject1: {
	      		required : false,
	      		string_validator: true
	      	},
        	inputProject2: {
	      		required :false,
	      		string_validator: true
	      	},
	      	numberlimit: {
	      		required : true,
	      		string_validator:true
	      	},
	      	page: {
	      		required : true
	      	}
	
        },
        messages: {
        	inputProject1: {
	      		required : "This is a required field",
	      		string_validator  : "Wrong input"
	      	},
	      	inputProject2: {
	      		required : "This is a required field",
	      		string_validator  : "Wrong input"
	      	},
	      	numberlimit: {
	      		required : "This is a required field",
	      		number_validator : "Wrong input"
	      	},
	      	page: {
	      		required : "This is a required field"
	      	}
        },
        submitHandler : function(form){
        	
        	var currentCreate = {
        			"id":$("#inputProject1").val(),
        			"name":$("#inputProject2").val(),
        			"leader":$('#inputProject3').val(),
        			"fromStr":$("#dateComboBox1").val(),
        			"toStr":$("#dateComboBox2").val(),
        			"recordsPerPage":$("#numberlimit").val(),
        			"page":$("#pageNumber").val()
    		};
        	
        	sendAjaxGetRequestQueryParam(currentCreate, 
        			AJS.contextPath() + '/rest/fisJiraRest/1.0/project/search', 
        			searchSuccessFunction, 
        			null);
        }
    });
	
	dateUtils.setupCalendarForInput("dateComboBox1", "icon-searchDate-create-picker1", false, dateFormat);
	dateUtils.setupCalendarForInput("dateComboBox2", "icon-searchDate-create-picker2", false, dateFormat);
	
})
function searchSuccessFunction(data){
	searchData = data.projectEntityDtoList;
	var a = {
			projectList:data.projectEntityDtoList
		};
	var abcd = exampleTable.viewAllProjectList(a);
	$("#allProject").html(abcd);

	$("#totalPage").html(data.totalPage); 
	$("#totalItem").html(data.totalItem);
	
	AJS.$("#nextbtn").unbind();
	AJS.$("#nextbtn").on('click',function(e){
		
		if(parseInt($("#pageNumber").val())<data.totalPage){
			
			AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())+1);

	        	sendAjaxGetRequestQueryParam({
	        		"id":$("#inputProject1").val(),
        			"name":$("#inputProject2").val(),
        			"fromStr":$("#dateComboBox1").val(),
        			"toStr":$("#dateComboBox2").val(),
        			"recordsPerPage":$("#numberlimit").val(),
        			"page":$("#pageNumber").val()
        			
	        		}, 
	        			AJS.contextPath() + '/rest/fisJiraRest/1.0/project/search', 
	        			searchSuccessFunction, 
	        			null);

		}
		else{
			alert("It is the last page")
		}
	     })
	     
	     AJS.$("#previousbtn").unbind();
	   AJS.$("#previousbtn").on('click',function(){
		if(parseInt($("#pageNumber").val())>1){
	    

	        	sendAjaxGetRequestQueryParam({"page":parseInt($("#pageNumber").val())- 1,
	        		"id":$("#inputProject1").val(),
        			"name":$("#inputProject2").val(),
        			"fromStr":$("#dateComboBox1").val(),
        			"toStr":$("#dateComboBox2").val(),
        			"recordsPerPage":$("#numberlimit").val(),
      
	        		}, 
	        			AJS.contextPath() + '/rest/fisJiraRest/1.0/project/search', 
	        			searchSuccessFunction, 
	        			null);
	        	AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())- 1)
		}
		else{
	    	alert("Dont have previous page")

		}
	     })
	     
		AJS.$("#firstbtn").on('click',function(){
			sendAjaxGetRequestQueryParam({"page":1,
				"id":$("#inputProject1").val(),
    			"name":$("#inputProject2").val(),
    			"fromStr":$("#dateComboBox1").val(),
    			"toStr":$("#dateComboBox2").val(),
    			"recordsPerPage":$("#numberlimit").val(),
	    		}, 
	    			AJS.contextPath() + '/rest/fisJiraRest/1.0/project/search', 
	    			searchSuccessFunction, 
	    			null);
	    	AJS.$("#pageNumber").val("1")
			
		})
		AJS.$("#lastbtn").on('click',function(){
			sendAjaxGetRequestQueryParam({"page":data.totalPage,
				"id":$("#inputProject1").val(),
    			"name":$("#inputProject2").val(),
    			"fromStr":$("#dateComboBox1").val(),
    			"toStr":$("#dateComboBox2").val(),
    			"recordsPerPage":$("#numberlimit").val(),
	    		}, 
	    			AJS.contextPath() + '/rest/fisJiraRest/1.0/project/search', 
	    			searchSuccessFunction, 
	    			null);
	    	AJS.$("#pageNumber").val(data.totalPage)
			
		})
		



}