package com.automator.tests;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.automator.businessLayer.opencart.Opencart;
import com.automator.controllers.ConfigController;
import com.automator.handlers.dataHandler.ExcelFileHandler;
import com.automator.handlers.dataHandler.TestSuiteMetaDataHandler;
import com.automator.handlers.reportHandler.FrameworkReportHandler;
import com.aventstack.extentreports.ExtentTest;

public class IntegrationTest1 {

	private FrameworkReportHandler frameworkReportHandler;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static final Logger log = Logger.getLogger(IntegrationTest1.class);
	private TestSuiteMetaDataHandler testSuiteMetaDataHandler;

	@BeforeSuite
	public void standardBillingTestSuiteSetUp(ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		log.info("=============== Initiating Test Suite: " + testSuiteName + " ===============");
		testSuiteMetaDataHandler = new TestSuiteMetaDataHandler(testSuiteName);
		testSuiteMetaDataHandler.insertTestSuiteStartTime(System.currentTimeMillis());
		frameworkReportHandler = new FrameworkReportHandler();
		frameworkReportHandler.initiateExtentReportFormatter(testSuiteName);
		frameworkReportHandler.initiateExcelReportFormatter(testSuiteName);
	}

	@BeforeMethod
	public void test1(Method method) {
		log.info("=============== Initiating Test: " + method.getName() + " ===============");
	}

	@Test
	public void opencartTest1(Method method, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testName = method.getName();
		String url = "http://opencart.abstracta.us/";
		ConfigController configController = new ConfigController();
		ExcelFileHandler excelFileHandler = new ExcelFileHandler();
		// to get data from the "Properties" file
		url = configController.doesSystemPropertyConfigExistFor("propertyFile")
				? configController.getDataFromPropertiesFile("url1", System.getProperty("propertyFile"))
				: url;
		// to get data from the System Configuration Environment (System Properties)
		url = configController.doesSystemPropertyConfigExistFor("urlForOpenCartTest1")
				? System.getProperty("urlForOpenCartTest1")
				: url;
		// to get data from excel file
		excelFileHandler.loadExcelForTheTest("./src/test/resources/data/OpenCartData.xlsx", "DataSheet1", testName);
		url = excelFileHandler.getData("URL");
		// start test
		extentTest.set(frameworkReportHandler.getExtentReports().createTest("OpenCart Test 1"));
		Opencart opencart = new Opencart(testSuiteName, testName);
		opencart.visit(url);
		captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url, extentTest.get(),
				opencart.getDriver(), testSuiteName, testName);
		opencart.getDriver().quit();
	}

	@Test
	public void opencartTest2(Method method, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testName = method.getName();
		String url = "http://opencart.abstracta.us/";
		ConfigController configController = new ConfigController();
		ExcelFileHandler excelFileHandler = new ExcelFileHandler();
		// to get data from the "Properties" file
		url = configController.doesSystemPropertyConfigExistFor("propertyFile")
				? configController.getDataFromPropertiesFile("url1", System.getProperty("propertyFile"))
				: url;
		// to get data from the System Configuration Environment (System Properties)
		url = configController.doesSystemPropertyConfigExistFor("urlForOpenCartTest1")
				? System.getProperty("urlForOpenCartTest1")
				: url;
		// to get data from excel file
		excelFileHandler.loadExcelForTheTest("./src/test/resources/data/OpenCartData.xlsx", "DataSheet1", testName);
		url = excelFileHandler.getData("URL");
		// start test
		extentTest.set(frameworkReportHandler.getExtentReports().createTest("OpenCart Test 1"));
		Opencart opencart = new Opencart(testSuiteName, testName);
		opencart.visit(url);
		captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url, extentTest.get(),
				opencart.getDriver(), testSuiteName, testName);
		opencart.getDriver().quit();
	}

	@Test
	public void opencartTest3(Method method, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testName = method.getName();
		String url = "http://opencart.abstracta.us/";
		ConfigController configController = new ConfigController();
		ExcelFileHandler excelFileHandler = new ExcelFileHandler();
		// to get data from the "Properties" file
		url = configController.doesSystemPropertyConfigExistFor("propertyFile")
				? configController.getDataFromPropertiesFile("url1", System.getProperty("propertyFile"))
				: url;
		// to get data from the System Configuration Environment (System Properties)
		url = configController.doesSystemPropertyConfigExistFor("urlForOpenCartTest1")
				? System.getProperty("urlForOpenCartTest1")
				: url;
		// to get data from excel file
		excelFileHandler.loadExcelForTheTest("./src/test/resources/data/OpenCartData.xlsx", "DataSheet1", testName);
		url = excelFileHandler.getData("URL");
		// start test
		extentTest.set(frameworkReportHandler.getExtentReports().createTest("OpenCart Test 1"));
		Opencart opencart = new Opencart(testSuiteName, testName);
		opencart.visit(url);
		captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url, extentTest.get(),
				opencart.getDriver(), testSuiteName, testName);
		opencart.getDriver().quit();
	}

	@AfterMethod
	public void opencartTestTearDown(Method method, ITestResult iTestResult, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		frameworkReportHandler.appendOverallResultToExtentReportForEachTest(iTestResult, extentTest.get(),
				testSuiteName);
		String testCaseName = method.getName();
		String testCaseDescription = iTestResult.getMethod().getDescription();
		String testCaseStatus = "";
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			testCaseStatus = "FAIL";
		} else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			testCaseStatus = "PASS";
		} else if (iTestResult.getStatus() == ITestResult.SKIP) {
			testCaseStatus = "SKIP";
		}
		String testCaseTime = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss aa").format(new Date());
		ArrayList<String> testCaseMetaData = new ArrayList<>();
		testCaseMetaData.add(testCaseDescription);
		testCaseMetaData.add(testCaseStatus);
		testCaseMetaData.add(testCaseTime);
		testSuiteMetaDataHandler.insertDataIntoTestSuiteMetaData(testCaseName, testCaseMetaData);
		log.info("=============== Ending Test: " + method.getName() + " ===============");
	}

	@AfterSuite
	public void opencartTestSuiteTearDown(ITestContext testSuiteName) {
		testSuiteMetaDataHandler.insertTestSuiteEndTime(System.currentTimeMillis());
		testSuiteMetaDataHandler.calculateAndSetTestSuiteExecutionTime();
		frameworkReportHandler.flushExtentReport();
		frameworkReportHandler.flushExcelReport(testSuiteMetaDataHandler.getTestSuiteMetaData(),
				testSuiteMetaDataHandler.getTestSuiteExecutionTime());
		log.info("=============== Ending Test Suite: " + testSuiteName.getSuite().getName() + " ===============");
	}

	private void captureAndAttachScreenshotForExtentReport(String extentLog, String testStep, ExtentTest extentTest,
			WebDriver driver, String testSuiteName, String testName) {
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport(extentLog, testStep, extentTest, driver,
				testSuiteName, testName);
	}

}
