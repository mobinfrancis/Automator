package com.automator.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {

	public static String getFormattedCurrentDateTime(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy_hh-mm-ss_aa");
		Date date = new Date();
		return simpleDateFormat.format(date);
	}

}
