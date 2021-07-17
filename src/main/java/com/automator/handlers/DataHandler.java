package com.automator.handlers;

import org.apache.log4j.Logger;

public class DataHandler {

	private static String requestNumber;
	private static final Logger log = Logger.getLogger(DataHandler.class);

	public static String getRequestNumber() {
		return requestNumber;
	}

	public static void setRequestNumber(String requestNumber1) {
		requestNumber = requestNumber1;
	}

}
