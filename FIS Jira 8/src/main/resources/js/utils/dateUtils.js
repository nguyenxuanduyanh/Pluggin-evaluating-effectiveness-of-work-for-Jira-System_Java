var classDateUtils = function(){};

classDateUtils.prototype = {
	setupCalendarForInput: function(inputFieldID, buttonID, isShowTime, format){
		Calendar.setup({
	        firstDay: 0,
	        inputField: inputFieldID,
	        button: buttonID,
	        align: "Br",
	        singleClick: true,
	        useISO8601WeekNumbers: false,
	        showsTime: isShowTime,
	        ifFormat: format
	    });
	},
	formatDateToStringPattern : function(dateInput, pattern){
		var dateStr = "";
		if(dateInput == null){
			return dateStr;
		}
		dateStr = moment(new Date(dateInput)).format(pattern);
		return dateStr;
	}
	
};

var dateUtils = new classDateUtils();