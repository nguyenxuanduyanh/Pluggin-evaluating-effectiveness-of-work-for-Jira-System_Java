var searchData = {};

AJS.toInit(function() {	

	// Show the dialog
	AJS.$("#dialog-show-button").on('click', function(e) {
	    e.preventDefault();
	    AJS.dialog2("#demo-dialog").show();
	});
	
		
	
	
	sendAjaxGetRequestQueryParam({}, 
		AJS.contextPath() + '/rest/fisJiraRest/1.0/user/getAll', 
		getAllUserSuccessFunction, 
			null);

	
// validator for creating user 	
	$.validator.addMethod("example_validator", function(value, element){
		if(value.includes("a")){
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

	
	$("#createUserForm").validate({
        rules: {
        	inputTwo: {
	      		required : true,
	      		example_validator: true
	      	},
	      	inputThree: {
	      		required : true,
	      		example_validator: true
	      	},
	      	inputFour: {
	      		required : true,
	      		example_validator: true
	      			},
	      	inputFive: {
				required : true,
				example_validator: true
	      			},
	      	dateComboBox: {
	      		required : true,
	      		date_validator: true
  	}
	
        },
        messages: {
        	inputTwo: {
	      		required : "This is a required field",
	      		example_validator  : "Can't have a"
	      	},
	      	inputThree: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	inputFour: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	inputFive: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	dateComboBox:{
	      		require:"This is a required field",
	      		date_validator:"Your DOB should equal or smaller than today"
	      	}
        },
        submitHandler : function(form){
        	
        	var currentCreate = {
        			"name":$("#inputTwo").val(),
        			"account":$("#inputThree").val(),
        			"email":$("#inputFour").val(),
        			"phone":$("#inputFive").val(),
        			"birthdayStr":$("#dateComboBox").val()
    		};
        	
        	sendAjaxPostRequest(currentCreate, 
    				AJS.contextPath() + '/rest/fisJiraRest/1.0/user/create', 
    				userCreateSuccessFunction, 
    				null);
        }
    });
	
	// validator for searching user
	$("#searchUserForm").validate({
        rules: {
        	inputOne1: {
	      		required : false,
	      		example_validator: true
	      	},
        	inputTwo1: {
	      		required :false,
	      		example_validator: true
	      	},
	      	inputThree1: {
	      		required : false,
	      		example_validator: true
	      	},
	      	inputFour1: {
	      		required : false,
	      		example_validator: true
	      			},
	      	inputFive1: {
				required : false,
				example_validator: true
	      			},
	      	dateComboBox1: {
	      		required : false,
	      		date_validator: true
	      			},
	      	numberlimit: {
	      		required : true
	      	},
	      	page: {
	      		required : true
	      	}
	
        },
        messages: {
        	inputOne1: {
	      		required : "This is a required field",
	      		example_validator  : "Can't have a"
	      	},
        	inputTwo1: {
	      		required : "This is a required field",
	      		example_validator  : "Can't have a"
	      	},
	      	inputThree1: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	inputFour1: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	inputFive1: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	dateComboBox1:{
	      		require:"This is a required field",
	      		date_validator:"Your DOB should equal or smaller than today"
	      	},
	      	numberlimit: {
	      		required : "This is a required field"
	      	},
	      	page: {
	      		required : "This is a required field"
	      	}
        },
        submitHandler : function(form){
        	
        	var currentCreate = {
        			"id":$("#inputOne1").val(),
        			"name":$("#inputTwo1").val(),
        			"account":$("#inputThree1").val(),
        			"email":$("#inputFour1").val(),
        			"phone":$("#inputFive1").val(),
        			"birthdayStr":$("#dateComboBox1").val(),
        			"recordsPerPage":$("#numberlimit").val(),
        			"page":$("#pageNumber").val()
    		};
        	
        	sendAjaxGetRequestQueryParam(currentCreate, 
        			AJS.contextPath() + '/rest/fisJiraRest/1.0/user/search', 
        			exampleSuccessFunction, 
        			null);
        }
    });
	
// edit user
	$("#editUserForm").validate({
        rules: {
        	inputTwo2: {
	      		required : true,
	      		example_validator: true
	      	},
	      	inputThree2: {
	      		required : true,
	      		example_validator: true
	      	},
	      	inputFour2: {
	      		required : true,
	      		example_validator: true
	      			},
	      	inputFive2: {
				required : true,
				example_validator: true
	      			},
	      	dateComboBox2: {
	      		required : true,
	      		date_validator: true
  	}
	
        },
        messages: {
        	inputTwo2: {
	      		required : "This is a required field",
	      		example_validator  : "Can't have a"
	      	},
	      	inputThree2: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	inputFour2: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	inputFive2: {
	      		required : "This is a required field",
	      		example_validator : "Can't have a"
	      	},
	      	dateComboBox2:{
	      		require:"This is a required field",
	      		date_validator:"Your DOB should equal or smaller than today"
	      	}
        },
        submitHandler : function(form){
        	
        	var currentCreate = {
        			"id":$("#inputOne2").val(),
        			"name":$("#inputTwo2").val(),
        			"account":$("#inputThree2").val(),
        			"email":$("#inputFour2").val(),
        			"phone":$("#inputFive2").val(),
        			"birthdayStr":$("#dateComboBox2").val()
    		};
        	
        	sendAjaxPostRequest(currentCreate, 
    				AJS.contextPath() + '/rest/fisJiraRest/1.0/user/edit', 
    				userEditSuccessFunction, 
    				null);
        }
    });
	
	
	
	
	
// DateComboBox	
	dateUtils.setupCalendarForInput("dateComboBox1", "icon-searchDate-create-picker1", false, dateFormat);
	dateUtils.setupCalendarForInput("dateComboBox", "icon-searchDate-create-picker", false, dateFormat);
	dateUtils.setupCalendarForInput("dateComboBox2", "icon-searchDate-create-picker2", false, dateFormat);

	
})
function shuffle(str) {
	  return str
	    .split('')
	    .sort(function() {
	      return 0.5 - Math.random();
	    })
	    .join('');
	}

	// For demonstration purposes we first make
	// a huge array of demo data (20 000 items)
	// HEADS UP; for the _.map function i use underscore (actually lo-dash) here
	


function userCreateSuccessFunction(data){
		alert("aa");
		
	    AJS.dialog2("#demo-dialog").hide();
	
	
};
function userEditSuccessFunction(data){
	alert("Edit success");
	
	    AJS.dialog2("#demo-dialog3").hide();
};

function exampleSuccessFunction(data) {
	searchData = data.userEntityDtoList;
	

	var a = {
		userList:data.userEntityDtoList
	};
	var abcd = exampleTable.viewAllUserList(a);
	$("#Alluser").html(abcd);

	$("#totalPage").html(data.totalPage); 
	$("#totalItem").html(data.totalItem);
	
	
	
	
	
	AJS.$(".dialog-show-button3").on('click',function(){
		
		var user = findUser($(this).parent().parent().attr("userid"));

		AJS.$("#inputOne2").val(user.id);
		AJS.$("#inputTwo2").val(user.name);
		AJS.$("#inputThree2").val(user.account);
		AJS.$("#inputFour2").val(user.email);
		AJS.$("#inputFive2").val(user.phone);
		AJS.$("#dateComboBox2").val(moment(new Date(user.birthday)).format(dateFormatText));
		//AJS.$("#dateComboBox2").val(user.birthday);
	    AJS.dialog2("#demo-dialog3").show();
	    
	})
	
	
	
	AJS.$(".removebtn").on('click',function(){
		var userid = $(this).parent().parent().attr("userid");
		
		sendAjaxPostRequest(userid, 
				AJS.contextPath() + '/rest/fisJiraRest/1.0/user/removeOne', 
				userDeleteSuccessFunction, 
				null);		
	});
	
	AJS.$("#nextbtn").unbind();
	
AJS.$("#nextbtn").on('click',function(e){
	
	if(parseInt($("#pageNumber").val())<data.totalPage){
		
		AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())+1);

        	sendAjaxGetRequestQueryParam({"page":$("#pageNumber").val(),
        		"id":$("#inputOne1").val(),
    			"name":$("#inputTwo1").val(),
    			"account":$("#inputThree1").val(),
    			"email":$("#inputFour1").val(),
    			"phone":$("#inputFive1").val(),
    			"birthdayStr":$("#dateComboBox1").val(),
    			"recordsPerPage":$("#numberlimit").val()
        		}, 
        			AJS.contextPath() + '/rest/fisJiraRest/1.0/user/search', 
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
        		"id":$("#inputOne1").val(),
    			"name":$("#inputTwo1").val(),
    			"account":$("#inputThree1").val(),
    			"email":$("#inputFour1").val(),
    			"phone":$("#inputFive1").val(),
    			"birthdayStr":$("#dateComboBox1").val(),
    			"recordsPerPage":$("#numberlimit").val()
        		}, 
        			AJS.contextPath() + '/rest/fisJiraRest/1.0/user/search', 
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
    		"id":$("#inputOne1").val(),
			"name":$("#inputTwo1").val(),
			"account":$("#inputThree1").val(),
			"email":$("#inputFour1").val(),
			"phone":$("#inputFive1").val(),
			"birthdayStr":$("#dateComboBox1").val(),
			"recordsPerPage":$("#numberlimit").val()
    		}, 
    			AJS.contextPath() + '/rest/fisJiraRest/1.0/user/search', 
    			exampleSuccessFunction, 
    			null);
    	AJS.$("#pageNumber").val("1")
		
	})
	AJS.$("#lastbtn").on('click',function(){
		sendAjaxGetRequestQueryParam({"page":data.totalPage,
    		"id":$("#inputOne1").val(),
			"name":$("#inputTwo1").val(),
			"account":$("#inputThree1").val(),
			"email":$("#inputFour1").val(),
			"phone":$("#inputFive1").val(),
			"birthdayStr":$("#dateComboBox1").val(),
			"recordsPerPage":$("#numberlimit").val()
    		}, 
    			AJS.contextPath() + '/rest/fisJiraRest/1.0/user/search', 
    			exampleSuccessFunction, 
    			null);
    	AJS.$("#pageNumber").val(data.totalPage)
		
	})
	

}

function userDeleteSuccessFunction(data){
	AJS.$("div [userid=" + data + "]").remove();
	alert("delete success");
}

function findUser(id) {
	for (i = 0; i < searchData.length; i++) {
		  if(searchData[i].id == id) {
			  return searchData[i];
		  }
		}
	
	return null;
}

function getAllUserSuccessFunction(data){

	function mockData(data) {
		  return _.map(data,function(i) {
		    return {
		      id: i.id,
		      text:i.name,
		    };
		  });
		}
				
		(function() {
		  // init select 2
		  $('#test').select2({
		    data: mockData(),
		    placeholder: 'search',
		    // query with pagination
		    query: function(q) {
		      var pageSize,
		        results,
		        that = this;
		      pageSize = 20; // or whatever pagesize
		      results = [];
		      if (q.term && q.term !== '') {
		        // HEADS UP; for the _.filter function i use underscore (actually lo-dash) here
		        results = _.filter(that.data, function(e) {
		          return e.text.toUpperCase().indexOf(q.term.toUpperCase()) >= 0;
		        });
		      } else if (q.term === '') {
		        results = that.data;
		      }
		      q.callback({
		        results: results.slice((q.page - 1) * pageSize, q.page * pageSize),
		        more: results.length >= q.page * pageSize,
		      });
		    },
		  });
		})();
		
}
