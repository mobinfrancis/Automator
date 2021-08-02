package com.automator.tests;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.automator.businessLayer.opencart.ItemsFunctionality;
import com.automator.controllers.ConfigController;
import com.automator.handlers.dataHandler.ExcelFileHandler;
import com.automator.handlers.dataHandler.TestSuiteMetaDataHandler;
import com.automator.handlers.fileHandler.PropertyFileHandler;
import com.automator.handlers.reportHandler.FrameworkReportHandler;
import com.automator.handlers.reportHandler.TestCaseExecutionStatus;
import com.aventstack.extentreports.ExtentTest;

public class UITest {

	private static final Logger log = Logger.getLogger(UITest.class);

	public FrameworkReportHandler frameworkReportHandler;
	public static InheritableThreadLocal<ExtentTest> extentTest = new InheritableThreadLocal<ExtentTest>();
	public TestSuiteMetaDataHandler testSuiteMetaDataHandler;

	@Test(enabled = true)
	public void validateTheNavigationLinks(Method testMethod, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testMethodName = testMethod.getName();
		log.info("=============== Initiating Test method: " + testMethodName + " ===============");
		ConfigController configController = new ConfigController();
		PropertyFileHandler propertyFileHandler = new PropertyFileHandler();
		String url = null;
		if (configController.doesSystemPropertyConfigExistFor("ProductSearchTestPropertyFile")) {
			url = propertyFileHandler.getDataFromPropertiesFile("url",
					System.getProperty("ProductSearchTestPropertyFile"));
		} else {
			String configFileRootPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
					+ "test" + File.separator + "resources" + File.separator + "configs" + File.separator;
			url = propertyFileHandler.getDataFromPropertiesFile("url",
					configFileRootPath + "ProductSearchTest.properties");
		}
		ExcelFileHandler excelFileHandler = new ExcelFileHandler();
		excelFileHandler.loadExcelForTheTest("./src/test/resources/data/OpenCartData.xlsx", "DataSheet1",
				testMethodName);
		if (!excelFileHandler.getData("url").isEmpty() && excelFileHandler.getData("url") != null) {
			url = excelFileHandler.getData("url");
		}
		extentTest.set(frameworkReportHandler.getExtentReports().createTest(testMethodName));
		ItemsFunctionality itemsFunctionality = new ItemsFunctionality();
		itemsFunctionality.visit(url);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url,
				extentTest.get(), itemsFunctionality.getDriver(), testSuiteName, testMethodName);
		itemsFunctionality.validateNavbarItemIsEnabled("Desktops");
		itemsFunctionality.validateNavbarItemIsEnabled("Laptops & Notebooks");
		itemsFunctionality.validateNavbarItemIsEnabled("Components");
		itemsFunctionality.validateNavbarItemIsEnabled("Tablets");
		itemsFunctionality.validateNavbarItemIsEnabled("Software");
		itemsFunctionality.validateNavbarItemIsEnabled("Phones & PDAs");
		itemsFunctionality.validateNavbarItemIsEnabled("Cameras");
		itemsFunctionality.validateNavbarItemIsEnabled("MP3 Players");
		itemsFunctionality.end();
	}

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

	@AfterMethod
	public void after(Method method, ITestResult iTestResult, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		frameworkReportHandler.appendOverallResultToExtentReportForEachTest(iTestResult, extentTest.get(),
				testSuiteName);
		String testCaseName = method.getName();
		String testCaseDescription = iTestResult.getMethod().getDescription();
		TestCaseExecutionStatus testCaseExecutionStatus = null;
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			testCaseExecutionStatus = TestCaseExecutionStatus.FAIL;
		} else if (iTestResult.getStatus() == ITestResult.SUCCESS) {
			testCaseExecutionStatus = TestCaseExecutionStatus.PASS;
		} else if (iTestResult.getStatus() == ITestResult.SKIP) {
			testCaseExecutionStatus = TestCaseExecutionStatus.SKIP;
		}
		String testCaseTime = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss aa").format(new Date());
		ArrayList<String> testCaseMetaData = new ArrayList<>();
		testCaseMetaData.add(testCaseDescription);
		testCaseMetaData.add(testCaseExecutionStatus.toString());
		testCaseMetaData.add(testCaseTime);
		testSuiteMetaDataHandler.insertDataIntoTestSuiteMetaData(testCaseName, testCaseMetaData);
		log.info("=============== Ending Test: " + method.getName() + " ===============");
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
