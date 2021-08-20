package com.automator.unitTests;

import java.io.File;
import org.junit.Assert;
import org.junit.Test;

import com.automator.handlers.reportHandler.JSONReportHandler;

public class JSONReportTest {

	@Test
	public void createJSONReport() {
		JSONReportHandler jsonReportHandler = new JSONReportHandler();
		jsonReportHandler.createTestSuiteJSONReport();
		String jsonReportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator;
		File jsonReportFile = new File(jsonReportPath + "report.json");
		Assert.assertTrue(jsonReportFile.exists());
	}

}
