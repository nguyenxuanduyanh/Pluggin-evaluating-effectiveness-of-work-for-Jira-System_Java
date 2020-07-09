function sendAjaxGetRequestQueryParam(sendingData, urlToSend, successFunction, errorFunction) {
	AJS.$.ajax({
	    url: urlToSend,
	    type: 'get',
	    contentType: "application/json",
	    data: sendingData,
	    success: function(data){
	         successFunction(data);
	    },
	    error: function(jqxhr){
	        errorFunction(jqxhr.responseText);
	    }
	});
}

function sendAjaxGetRequestQueryParamNoAsync(sendingData, urlToSend, successFunction, errorFunction) {
	AJS.$.ajax({
	    url: urlToSend,
	    type: 'get',
	    contentType: "application/json",
	    data: sendingData,
	    async: false,
	    success: function(data){
	         successFunction(data);
	    },
	    error: function(jqxhr){
	        errorFunction(jqxhr.responseText);
	    }
	});
}

function sendAjaxPostRequest(sendingData, urlToSend, successFunction, errorFunction) {
	AJS.$.ajax({
	    url: urlToSend,
	    type: 'post',
	    dataType: 'json',
	    contentType: 'application/json',
	    data: JSON.stringify(sendingData),
	    async: true,
	    success: function(data){
	         successFunction(data);
	    },
	    error: function(jqxhr){
	        errorFunction(jqxhr.responseText);
	    }
	});
}

function sendAjaxPostRequestNoAsync(sendingData, urlToSend, successFunction, errorFunction) {
	AJS.$.ajax({
	    url: urlToSend,
	    type: 'post',
	    dataType: 'json',
	    contentType: 'application/json',
	    data: JSON.stringify(sendingData),
	    async: false,
	    success: function(data){
	         successFunction(data);
	    },
	    error: function(jqxhr){
	        errorFunction(jqxhr.responseText);
	    }
	});
}


