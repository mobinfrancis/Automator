package com.automator.tests.runner;

import java.util.ArrayList;
import java.util.List;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class TestNGRunner {

	// To run from command line
	// java -jar Automator.jar main_testng.xml
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		TestListenerAdapter testListenerAdapter = new TestListenerAdapter();
		testng.addListener((ITestNGListener) testListenerAdapter);
		List<String> testSuites = new ArrayList<String>();
		testSuites.add("./src/test/resources/config/main_testng.xml");
		testng.setTestSuites(testSuites);
		// testng.setParallel(XmlSuite.ParallelMode.METHODS);
		// testng.setPreserveOrder(true);
		// testng.setSuiteThreadPoolSize(5);
		// testng.setVerbose(0);
		testng.setOutputDirectory("test-output");
		testng.run();
	}

}
