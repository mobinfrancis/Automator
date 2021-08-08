package com.automator.tests;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.automator.businessLayer.opencart.ProductSearch;
import com.automator.handlers.dataHandler.TestSuiteMetaDataHandler;
import com.automator.handlers.exceptionHandler.FrameworkException;
import com.automator.handlers.fileHandler.PropertyFileHandler;
import com.automator.handlers.reportHandler.FrameworkReportHandler;
import com.automator.handlers.reportHandler.TestCaseExecutionStatus;
import com.automator.utilities.DatabaseUtility;
import com.automator.utilities.ProductSearchDataProviderSource;
import com.aventstack.extentreports.ExtentTest;

public class ProductSearchTest {

	private static final Logger log = Logger.getLogger(ProductSearchTest.class);
	private FrameworkReportHandler frameworkReportHandler;
	private static InheritableThreadLocal<ExtentTest> extentTest = new InheritableThreadLocal<ExtentTest>();
	private TestSuiteMetaDataHandler testSuiteMetaDataHandler;
	private final String configFileRootPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources" + File.separator + "configs" + File.separator;

	@Test(dataProvider = "productsToSearch", dataProviderClass = ProductSearchDataProviderSource.class, enabled = true)
	public void shouldValidateProductSearch(Method testMethod, ITestContext iTestContext, String productToSearch) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testMethodName = testMethod.getName();
		PropertyFileHandler propertyFileHandler = new PropertyFileHandler();
		String url = propertyFileHandler.getDataFromPropertiesFile("url",
				configFileRootPath + "ProductSearchTest.properties");
		ProductSearch productSearch = new ProductSearch();
		productSearch.visit(url);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url,
				extentTest.get(), productSearch.getDriver(), testSuiteName, testMethodName);
		log.info("Product being searched is: " + productToSearch);
		productSearch.searchProduct(productToSearch);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info",
				"Searched the product: " + productToSearch, extentTest.get(), productSearch.getDriver(), testSuiteName,
				testMethodName);
		productSearch.validateTheSearchedProductHeading();
		productSearch.validateTheSearchedProductSubheading();
		productSearch.validateTheTextPresentInSearchCriteriaTextBox();
		productSearch.validateTheSearchButtonIsEnabled();
		productSearch.validateCorrectProductItemIsDisplayedIfPresent();
		productSearch.end();
	}

	@Test(enabled = true)
	public void shouldValidateProductSearchForProductsInDB(Method testMethod, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testMethodName = testMethod.getName();
		PropertyFileHandler propertyFileHandler = new PropertyFileHandler();
		String url = propertyFileHandler.getDataFromPropertiesFile("url",
				configFileRootPath + "ProductSearchTest.properties");
		ProductSearch productSearch = new ProductSearch();
		productSearch.visit(url);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info", "Visited the url: " + url,
				extentTest.get(), productSearch.getDriver(), testSuiteName, testMethodName);
		DatabaseUtility databaseUtility = new DatabaseUtility();
		ResultSet resultSet = databaseUtility.getDataFromMySQLDB("select * from products;");
		try {
			while (resultSet.next()) {
				String productToSearch = resultSet.getString("product_name");
				log.info("Product being searched is: " + resultSet.getString("product_name"));
				productSearch.searchProduct(productToSearch);
				frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info",
						"Searched the product: " + productToSearch, extentTest.get(), productSearch.getDriver(),
						testSuiteName, testMethodName);
				productSearch.validateTheSearchedProductHeading();
				productSearch.validateTheSearchedProductSubheading();
				productSearch.validateTheTextPresentInSearchCriteriaTextBox();
				productSearch.validateTheSearchButtonIsEnabled();
				productSearch.validateCorrectProductItemIsDisplayedIfPresent();
			}
		} catch (SQLException e) {
			throw new FrameworkException("Could not retrieve data from the DB");
		} finally {
			databaseUtility.closeSQLDBConnection();
		}
		productSearch.end();
	}

	@BeforeSuite
	public void testSuiteSetup(ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		log.info("=============== Initiating Test Suite: " + testSuiteName + " ===============");
		testSuiteMetaDataHandler = new TestSuiteMetaDataHandler(testSuiteName);
		testSuiteMetaDataHandler.insertTestSuiteStartTime(System.currentTimeMillis());
		frameworkReportHandler = new FrameworkReportHandler();
		frameworkReportHandler.initiateExtentReportFormatter(testSuiteName);
		frameworkReportHandler.initiateExcelReportFormatter(testSuiteName);
	}

	@BeforeMethod
	public void testMethodSetup(Method testMethod) {
		String testMethodName = testMethod.getName();
		log.info("=============== Initiating Test method: " + testMethodName + " ===============");
		extentTest.set(frameworkReportHandler.getExtentReports().createTest(testMethodName));
	}

	@AfterMethod
	public void testMethodTeardown(Method testMethod, ITestResult iTestResult, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		frameworkReportHandler.appendOverallResultToExtentReportForEachTest(iTestResult, extentTest.get(),
				testSuiteName);
		String testCaseName = testMethod.getName();
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
		log.info("=============== Ending Test method: " + testMethod.getName() + " ===============");
	}

	@AfterSuite
	public void testSuiteTeardown(ITestContext testSuiteName) {
		testSuiteMetaDataHandler.insertTestSuiteEndTime(System.currentTimeMillis());
		testSuiteMetaDataHandler.calculateAndSetTestSuiteExecutionTime();
		frameworkReportHandler.flushExtentReport();
		frameworkReportHandler.flushExcelReport(testSuiteMetaDataHandler.getTestSuiteMetaData(),
				testSuiteMetaDataHandler.getTestSuiteExecutionTime());
		log.info("=============== Ending Test Suite: " + testSuiteName.getSuite().getName() + " ===============");
	}

}
