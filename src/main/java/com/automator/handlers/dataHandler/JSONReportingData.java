package com.automator.handlers.dataHandler;

public class JSONReportingData {

	private String testSuite;
	private String testName;
	private String testDescription;
	private String testExecutionStatus;
	private String testExecutionStartTime;
	private String testExecutionEndTime;
	private String testExecutedBy;

	public String getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(String testSuite) {
		this.testSuite = testSuite;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getTestExecutionStatus() {
		return testExecutionStatus;
	}

	public void setTestExecutionStatus(String testExecutionStatus) {
		this.testExecutionStatus = testExecutionStatus;
	}

	public String getTestExecutionStartTime() {
		return testExecutionStartTime;
	}

	public void setTestExecutionStartTime(String testExecutionStartTime) {
		this.testExecutionStartTime = testExecutionStartTime;
	}

	public String getTestExecutionEndTime() {
		return testExecutionEndTime;
	}

	public void setTestExecutionEndTime(String testExecutionEndTime) {
		this.testExecutionEndTime = testExecutionEndTime;
	}

	public String getTestExecutedBy() {
		return testExecutedBy;
	}

	public void setTestExecutedBy(String testExecutedBy) {
		this.testExecutedBy = testExecutedBy;
	}

}
