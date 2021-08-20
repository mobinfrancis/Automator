package com.automator.handlers.reportHandler;

import java.io.File;
import java.io.IOException;
import com.automator.handlers.dataHandler.JSONReportingData;
import com.automator.handlers.exceptionHandler.FrameworkException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class handles the creation of JSONReport
 * 
 * @author Sumon Dey, August 20, 2021
 *
 */
public class JSONReportHandler {

	public void createTestSuiteJSONReport() {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONReportingData jsonReportingData = new JSONReportingData();
		jsonReportingData.setTestSuite("TestSuite1");
		jsonReportingData.setTestName("TestName1");
		jsonReportingData.setTestDescription("TestDescription1");
		jsonReportingData.setTestExecutionStatus("PASS");
		jsonReportingData.setTestExecutionStartTime("22.00");
		jsonReportingData.setTestExecutionEndTime("23.00");
		jsonReportingData.setTestExecutedBy("User");
		try {
			String jsonReportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator;
			objectMapper.writeValue(new File(jsonReportPath + "report.json"), jsonReportingData);
		} catch (IOException e) {
			throw new FrameworkException("Not able to create Test Suite JSON Report", e);
		}
	}

}
