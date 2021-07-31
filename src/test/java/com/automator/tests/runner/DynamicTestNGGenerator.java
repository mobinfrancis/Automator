package com.automator.tests.runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class DynamicTestNGGenerator {

	private static final Logger log = Logger.getLogger(DynamicTestNGGenerator.class);

	public static void main(String[] args) {
		DynamicTestNGGenerator dynamicTestNGGenerator = new DynamicTestNGGenerator();
		Map<String, String> testngParams = new HashMap<String, String>();
		testngParams.put("device1", "Desktop");
		testngParams.put("device2", "Mobile");
		testngParams.put("device3", "Tablet");
		dynamicTestNGGenerator.runTestNGTest(testngParams);
	}

	private void runTestNGTest(Map<String, String> testngParams) {
		TestNG testNG = new TestNG();
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("FunctionlTestSuite2");
		xmlSuite.setParallel(XmlSuite.ParallelMode.METHODS);
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName("IntegrationTest2");

		// add groups
		// myTest.addIncludedGroup("group1");
		// myTest.addIncludedGroup("group2");

		// Add any parameters that you want to set to the Test.
		// myTest.setParameters(testngParams);

		List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
		xmlClasses.add(new XmlClass("com.automator.tests.ProductSearchTest"));
		xmlClasses.add(new XmlClass("com.automator.tests.UITest"));
		xmlTest.setXmlClasses(xmlClasses);

		List<XmlTest> xmlTests = new ArrayList<XmlTest>();
		xmlTests.add(xmlTest);

		xmlSuite.setTests(xmlTests);
		List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>();
		xmlSuites.add(xmlSuite);

		testNG.setXmlSuites(xmlSuites);
		xmlSuite.setFileName("functionalTestSuite2.xml");
		xmlSuite.setThreadCount(10);
		testNG.run();

		// Create physical XML file based on the virtual XML content
		for (XmlSuite suite : xmlSuites) {
			createXmlFile(suite);
		}
		log.info("TestNG xml file named \"" + xmlSuite.getFileName() + "\" is generated successfully");

	}

	// This method will create an Xml file based on the XmlSuite data
	public void createXmlFile(XmlSuite mSuite) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(new File("functionalTestSuite2.xml"));
			fileWriter.write(mSuite.toXml());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
