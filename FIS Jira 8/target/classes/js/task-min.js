var searchData={};AJS.toInit(function(){$.validator.addMethod("string_validator",function(b,a){if(b.includes("*")){return false}else{if(b.includes("#")){return false}else{if(b.includes("@")){return false}else{if(b.includes("^")){return false}else{if(b.includes("&")){return false}else{return true}}}}}});$.validator.addMethod("date_validator",function(c,b){var f=new Date();present_time=f.getTime();var e=AJS.$("#dateComboBox").val();var a=new Date(moment(e,dateFormatText).toDate().getTime());if(a>present_time){return false}else{return true}});$("#searchTaskForm").validate({rules:{inputTask1:{required:false},inputTask2:{required:false,string_validator:true},inputTask3:{required:false,string_validator:true},inputTask4:{required:false,string_validator:true},dateComboBox2:{required:false,date_validator:true},dateComboBox3:{required:false,date_validator:true},numberlimit:{required:true,number_validator:false},page:{required:true}},messages:{inputTask1:{required:"This is a required field"},inputTask2:{required:"This is a required field",string_validator:"Wrong input"},inputTask3:{required:"This is a required field",string_validator:"Wrong input"},inputTask4:{required:"This is a required field",string_validator:"Wrong input"},numberlimit:{required:"This is a required field",number_validator:"Wrong input"},page:{required:"This is a required field"}},submitHandler:function(b){var a={id:$("#inputTask1").val(),name:$("#inputTask2").val(),assignee:$("#inputTask3").val(),project:$("#inputTask4").val(),fromStr:$("#dateComboBox2").val(),toStr:$("#dateComboBox3").val(),recordsPerPage:$("#numberlimit").val(),page:$("#pageNumber").val()};sendAjaxGetRequestQueryParam(a,AJS.contextPath()+"/rest/fisJiraRest/1.0/task/search",searchSuccessFunction,null)}});dateUtils.setupCalendarForInput("dateComboBox3","icon-searchDate-create-picker3",false,dateFormat);dateUtils.setupCalendarForInput("dateComboBox2","icon-searchDate-create-picker2",false,dateFormat)});function searchSuccessFunction(c){searchData=c.taskEntityDtoList;var b={taskList:c.taskEntityDtoList};var d=exampleTable.viewAllTaskList(b);$("#allTask").html(d);$("#totalPage").html(c.totalPage);$("#totalItem").html(c.totalItem);AJS.$("#nextbtn").unbind();AJS.$("#nextbtn").on("click",function(a){if(parseInt($("#pageNumber").val())<c.totalPage){AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())+1);sendAjaxGetRequestQueryParam({page:$("#pageNumber").val(),id:$("#inputTask1").val(),name:$("#inputTask2").val(),assignee:$("#inputTask3").val(),project:$("#inputTask4").val(),fromStr:$("#dateComboBox2").val(),toStr:$("#dateComboBox3").val(),recordsPerPage:$("#numberlimit").val(),},AJS.contextPath()+"/rest/fisJiraRest/1.0/task/search",exampleSuccessFunction,null)}else{alert("It is the last page")}});AJS.$("#previousbtn").unbind();AJS.$("#previousbtn").on("click",function(){if(parseInt($("#pageNumber").val())>1){sendAjaxGetRequestQueryParam({page:parseInt($("#pageNumber").val())-1,id:$("#inputTask1").val(),name:$("#inputTask2").val(),assignee:$("#inputTask3").val(),project:$("#inputTask4").val(),fromStr:$("#dateComboBox2").val(),toStr:$("#dateComboBox3").val(),recordsPerPage:$("#numberlimit").val(),},AJS.contextPath()+"/rest/fisJiraRest/1.0/task/search",exampleSuccessFunction,null);AJS.$("#pageNumber").val(parseInt($("#pageNumber").val())-1)}else{alert("Dont have previous page")}});AJS.$("#firstbtn").on("click",function(){sendAjaxGetRequestQueryParam({page:1,id:$("#inputTask1").val(),name:$("#inputTask2").val(),assignee:$("#inputTask3").val(),project:$("#inputTask4").val(),fromStr:$("#dateComboBox2").val(),toStr:$("#dateComboBox3").val(),recordsPerPage:$("#numberlimit").val(),},AJS.contextPath()+"/rest/fisJiraRest/1.0/task/search",exampleSuccessFunction,null);AJS.$("#pageNumber").val("1")});AJS.$("#lastbtn").on("click",function(){sendAjaxGetRequestQueryParam({page:c.totalPage,id:$("#inputTask1").val(),name:$("#inputTask2").val(),assignee:$("#inputTask3").val(),project:$("#inputTask4").val(),fromStr:$("#dateComboBox2").val(),toStr:$("#dateComboBox3").val(),recordsPerPage:$("#numberlimit").val(),},AJS.contextPath()+"/rest/fisJiraRest/1.0/task/search",exampleSuccessFunction,null);AJS.$("#pageNumber").val(c.totalPage)})};