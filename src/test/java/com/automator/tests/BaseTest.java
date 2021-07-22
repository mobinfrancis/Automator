package com.automator.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.automator.handlers.dataHandler.TestSuiteMetaDataHandler;
import com.automator.handlers.reportHandler.FrameworkReportHandler;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {

	public FrameworkReportHandler frameworkReportHandler;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static final Logger log = Logger.getLogger(BaseTest.class);
	public TestSuiteMetaDataHandler testSuiteMetaDataHandler;

	@BeforeSuite
	public void setup(ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		log.info("=============== Initiating Test Suite: " + testSuiteName + " ===============");
		testSuiteMetaDataHandler = new TestSuiteMetaDataHandler(testSuiteName);
		testSuiteMetaDataHandler.insertTestSuiteStartTime(System.currentTimeMillis());
		frameworkReportHandler = new FrameworkReportHandler();
		frameworkReportHandler.initiateExtentReportFormatter(testSuiteName);
		frameworkReportHandler.initiateExcelReportFormatter(testSuiteName);
	}

	@AfterSuite
	public void teardown(ITestContext testSuiteName) {
		testSuiteMetaDataHandler.insertTestSuiteEndTime(System.currentTimeMillis());
		testSuiteMetaDataHandler.calculateAndSetTestSuiteExecutionTime();
		frameworkReportHandler.flushExtentReport();
		frameworkReportHandler.flushExcelReport(testSuiteMetaDataHandler.getTestSuiteMetaData(),
				testSuiteMetaDataHandler.getTestSuiteExecutionTime());
		log.info("=============== Ending Test Suite: " + testSuiteName.getSuite().getName() + " ===============");
	}

}
