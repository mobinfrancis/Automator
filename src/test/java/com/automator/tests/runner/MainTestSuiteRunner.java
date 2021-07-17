package com.automator.tests.runner;

import java.util.ArrayList;
import java.util.List;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.automator.handlers.reportHandler.FrameworkReportHandler;


public class MainTestSuiteRunner {

	public static String totalExecutionTime;

	// java -jar FIMAutomation.jar main_testng.xml
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		TestListenerAdapter adapter = new TestListenerAdapter();
		List<String> suites = new ArrayList<String>();
		testng.addListener((ITestNGListener) adapter);
		suites.add("./src/test/resources/com/fimautomation/tests/configs/main_testng.xml");
		testng.setTestSuites(suites);
		testng.setParallel(XmlSuite.ParallelMode.METHODS);
		testng.setPreserveOrder(true);
		testng.setSuiteThreadPoolSize(5);
		testng.setVerbose(0);
		testng.setOutputDirectory("test-output");
		long executionStartTimeInLong = System.currentTimeMillis();
		testng.run();
		long executionEndTimeInLong = System.currentTimeMillis();
		long totalExecutionTimeInLong = executionEndTimeInLong - executionStartTimeInLong;
		long minutes = (totalExecutionTimeInLong / 1000) / 60;
		long seconds = (totalExecutionTimeInLong / 1000) % 60;
		totalExecutionTime = minutes + " minute(s), " + seconds + " second(s)";
		FrameworkReportHandler frameworkReportHandler = new FrameworkReportHandler();
		frameworkReportHandler.initiateSummaryExcelReport();
	}

}
