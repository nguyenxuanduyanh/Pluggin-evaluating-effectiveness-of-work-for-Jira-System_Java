package com.fpt.jira.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	
	public static final int SEC_PER_HOUR = 3600;
	
	/**
	 * anhpt98
	 * Get All (Working) Day between a duration
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getWorkingDatesBetween(Date startDate, Date endDate) {
		
		// enable to remove certain day of the weeks
		//List<Integer> list = Arrays.asList(Const.NON_WORKING_DAYS);

	    List<Date> datesInRange = new ArrayList<>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startDate);
	     
	    Calendar endCalendar = new GregorianCalendar();
	    endCalendar.setTime(endDate);
	 
	    while (calendar.before(endCalendar)) {
	    	// enable to remove certain day of the weeks
	    	//if(!list.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
		        Date result = calendar.getTime();
		        datesInRange.add(result);
	    	//}
	        calendar.add(Calendar.DATE, 1);
	    }
	    
	    return datesInRange;
	}
}
