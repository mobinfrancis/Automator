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
		testSuiteMetaDataMap = new HashMap<String, ArrayList<String>>();
	}

	public synchronized void insertDataIntoTestSuiteMetaData(String testCaseName, ArrayList<String> testMetaData) {
		testSuiteMetaDataMap.put(testCaseName, testMetaData);
	}

	public void insertTestSuiteStartTime(long startTime) {
		this.testSuiteExecutionStartTime = startTime;
	}

	public void insertTestSuiteEndTime(long endTime) {
		this.testSuiteExecutionEndTime = endTime;
	}

	public synchronized void calculateAndSetTestSuiteExecutionTime() {
		long executionTimeInLong = this.testSuiteExecutionEndTime - this.testSuiteExecutionStartTime;
		long minutes = (executionTimeInLong / 1000) / 60;
		long seconds = (executionTimeInLong / 1000) % 60;
		String executionTime = minutes + " minute(s), " + seconds + " second(s)";
		testSuiteExecutionTime = executionTime;
	}

	public Map<String, ArrayList<String>> getTestSuiteMetaData() {
		return testSuiteMetaDataMap;
	}

	public String getTestSuiteExecutionTime() {
		return this.testSuiteExecutionTime;
	}

}
