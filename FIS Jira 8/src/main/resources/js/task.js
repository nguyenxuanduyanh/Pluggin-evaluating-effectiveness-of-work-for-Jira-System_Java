var searchData = {};
AJS.toInit(function() {	
	


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
	$("#searchTaskForm").validate({
        rules: {
        	inputTask1: {
	      		required : false	     
	      		},
        	inputTask2: {
	      		required :false,
	      		string_validator: true
	      	},
	      	inputTask3: {
	      		required : false,
	      		string_validator: true
	      	},
	      	inputTask4: {
	      		required : false,
	      		string_validator: true
	      			},
	      	dateComboBox2: {
	      		required : false,
	      		date_validator: true
	      },
	      	dateComboBox3: {
	    	    required : false,
	    	    date_validator: true
	    },
	      	numberlimit: {
	      		required : true,
	      		number_validator:false
	      	},
	      	page: {
	      		required : true
	      	}
	
        },
        messages: {
        	inputTask1: {
	      		required : "This is a required field"
	      	},
        	inputTask2: {
	      		required : "This is a required field",
	      		string_validator  : "Wrong input"
	      	},
	      	inputTask3: {
	      		required : "This is a required field",
	      		string_validator : "Wrong input"
	      	},
	      	inputTask4: {
	      		required : "This is a required field",
	      		string_validator : "Wrong input"
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
        			"id":$("#inputTask1").val(),
        			"name":$("#inputTask2").val(),
        			"assignee":$("#inputTask3").val(),
        			"project":$("#inputTask4").val(),
        			"fromStr":$("#dateComboBox2").val(),
        			"toStr":$("#dateComboBox3").val(),
        			"recordsPerPage":$("#numberlimit").val(),
        			"page":$("#pageNumber").val()
    		};
        	
        	sendAjaxGetRequestQueryParam(currentCreate, 
        			AJS.contextPath() + '/rest/fisJiraRest/1.0/task/search', 
        			searchSuccessFunction, 
        			null);
        }
    });
	
	
	dateUtils.setupCalendarForInput("dateComboBox3", "icon-searchDate-create-picker3", false, dateFormat);
	dateUtils.setupCalendarForInput("dateComboBox2", "icon-searchDate-create-picker2", false, dateFormat);
	
})

function searchSuccessFunction(data){
	searchData = data.taskEntityDtoList;
	var a = {
			taskList:data.taskEntityDtoList
		};
	var abcd = exampleTable.viewAllTaskList(a);
	$("#allTask").html(abcd);

	$("#totalPage").html(data.totalPage); 
	$("#totalItem").html(data.totalItem);
	AJS.$("#nextbtn").unbind();
	
	AJS.$("#nextbtn").on('click',function(e){
		
		if(parseInt($("#pageNumber").val())<data.totalPage){
			
			AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())+1);

	        	sendAjaxGetRequestQueryParam({"page":$("#pageNumber").val(),
	        		"id":$("#inputTask1").val(),
        			"name":$("#inputTask2").val(),
        			"assignee":$("#inputTask3").val(),
        			"project":$("#inputTask4").val(),
        			"fromStr":$("#dateComboBox2").val(),
        			"toStr":$("#dateComboBox3").val(),
        			"recordsPerPage":$("#numberlimit").val(),
	        		}, 
	        			AJS.contextPath() + '/rest/fisJiraRest/1.0/task/search', 
	        			exampleSuccessFunction, 
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
	        		"id":$("#inputTask1").val(),
        			"name":$("#inputTask2").val(),
        			"assignee":$("#inputTask3").val(),
        			"project":$("#inputTask4").val(),
        			"fromStr":$("#dateComboBox2").val(),
        			"toStr":$("#dateComboBox3").val(),
        			"recordsPerPage":$("#numberlimit").val(),
	        		}, 
	        			AJS.contextPath() + '/rest/fisJiraRest/1.0/task/search', 
	        			exampleSuccessFunction, 
	        			null);
	        	AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())- 1)
		}
		else{
	    	alert("Dont have previous page")

		}
	     })
	     
		AJS.$("#firstbtn").on('click',function(){
			sendAjaxGetRequestQueryParam({"page":1,
				"id":$("#inputTask1").val(),
    			"name":$("#inputTask2").val(),
    			"assignee":$("#inputTask3").val(),
    			"project":$("#inputTask4").val(),
    			"fromStr":$("#dateComboBox2").val(),
    			"toStr":$("#dateComboBox3").val(),
    			"recordsPerPage":$("#numberlimit").val(),
	    		}, 
	    			AJS.contextPath() + '/rest/fisJiraRest/1.0/task/search', 
	    			exampleSuccessFunction, 
	    			null);
	    	AJS.$("#pageNumber").val("1")
			
		})
		AJS.$("#lastbtn").on('click',function(){
			sendAjaxGetRequestQueryParam({"page":data.totalPage,
				"id":$("#inputTask1").val(),
    			"name":$("#inputTask2").val(),
    			"assignee":$("#inputTask3").val(),
    			"project":$("#inputTask4").val(),
    			"fromStr":$("#dateComboBox2").val(),
    			"toStr":$("#dateComboBox3").val(),
    			"recordsPerPage":$("#numberlimit").val(),
	    		}, 
	    			AJS.contextPath() + '/rest/fisJiraRest/1.0/task/search', 
	    			exampleSuccessFunction, 
	    			null);
	    	AJS.$("#pageNumber").val(data.totalPage)
			
		})

}