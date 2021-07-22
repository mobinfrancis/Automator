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
import org.testng.annotations.Test;

import com.automator.businessLayer.opencart.Opencart;
import com.automator.controllers.ConfigController;
import com.automator.handlers.dataHandler.ExcelFileHandler;
import com.automator.handlers.fileHandler.PropertyFileHandler;

public class ProductSearchTest extends BaseTest {

	private static final Logger log = Logger.getLogger(ProductSearchTest.class);

	@Test
	public void validateProductSearchTest(Method testMethod, ITestContext iTestContext) {
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
		Opencart opencart = new Opencart();
		opencart.visit(url);
		captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url, extentTest.get(),
				opencart.getDriver(), testSuiteName, testMethodName);
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

}
