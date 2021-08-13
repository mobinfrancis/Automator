package com.automator.handlers.dataHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestSuiteMetaDataHandler {

	private Map<String, ArrayList<String>> testSuiteMetaDataMap;
	private String testSuiteName;
	private long testSuiteExecutionStartTime;
	private long testSuiteExecutionEndTime;
	private String testSuiteExecutionTime;

	public TestSuiteMetaDataHandler(String testSuiteName) {
		this.testSuiteName = testSuiteName;
		this.testSuiteMetaDataMap = new HashMap<String, ArrayList<String>>();
	}

	public synchronized void insertDataIntoTestSuiteMetaData(String testCaseName, ArrayList<String> testCaseMetaData) {
		this.testSuiteMetaDataMap.put(testCaseName, testCaseMetaData);
	}

	public void setTestSuiteStartTime(long startTime) {
		this.testSuiteExecutionStartTime = startTime;
	}

	public void setTestSuiteEndTime(long endTime) {
		this.testSuiteExecutionEndTime = endTime;
	}

	public synchronized void calculateAndSetTestSuiteExecutionTime() {
		long executionTimeInLong = this.testSuiteExecutionEndTime - this.testSuiteExecutionStartTime;
		long minutes = (executionTimeInLong / 1000) / 60;
		long seconds = (executionTimeInLong / 1000) % 60;
		this.testSuiteExecutionTime = minutes + " minute(s), " + seconds + " second(s)";
	}

	public Map<String, ArrayList<String>> getTestSuiteMetaData() {
		return this.testSuiteMetaDataMap;
	}

	public String getTestSuiteExecutionTime() {
		return this.testSuiteExecutionTime;
	}

}
