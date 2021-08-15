package com.automator.testRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.automator.handlers.exceptionHandler.FrameworkException;
import com.automator.handlers.fileHandler.PropertyFileHandler;

/**
 * This class is the entry point for running tests using the Automator
 * framework. Users can create testng.xml file at runtime from this class and
 * then use the generated xml file to drive the tests. Users can define all the
 * items needed in the testng.xml file from here (e.g. test suite name, test
 * name, parameters, class names, parallel mode, thread count) and they will get
 * dynamically added to the file at runtime. To execute from CLIs, run with the
 * below command: 
 * mvn -Dexec.mainClass="com.automator.testRunner.DynamicTestNGGenerator" -Dexec.classpathScope=test test-compile exec:java
 * 
 * @author Sumon Dey
 *
 */
public class DynamicTestNGGenerator {

	private static final Logger log = Logger.getLogger(DynamicTestNGGenerator.class);
	private PropertyFileHandler propertyFileHandler;
	private final String configFileRootPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources" + File.separator + "configs" + File.separator;

	public static void main(String[] arguments) {
		DynamicTestNGGenerator dynamicTestNGGenerator = new DynamicTestNGGenerator();
		dynamicTestNGGenerator.runTestNGTest();
	}

	private void runTestNGTest() {
		propertyFileHandler = new PropertyFileHandler();
		String testngConfigFilePath = configFileRootPath + "testng.properties";
		TestNG testNG = new TestNG();
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName(propertyFileHandler.getDataFromPropertiesFile("testSuiteName", testngConfigFilePath));
		xmlSuite.setParallel(XmlSuite.ParallelMode.METHODS);
		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(propertyFileHandler.getDataFromPropertiesFile("testName", testngConfigFilePath));
		List<XmlClass> xmlClasses = new ArrayList<XmlClass>() {
			{
				add(new XmlClass(propertyFileHandler.getDataFromPropertiesFile("className1", testngConfigFilePath)));
				add(new XmlClass(propertyFileHandler.getDataFromPropertiesFile("className2", testngConfigFilePath)));
			}
		};
		xmlTest.setXmlClasses(xmlClasses);
		List<XmlTest> xmlTests = new ArrayList<XmlTest>();
		xmlTests.add(xmlTest);
		xmlSuite.setTests(xmlTests);
		List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>();
		xmlSuites.add(xmlSuite);
		testNG.setXmlSuites(xmlSuites);
		xmlSuite.setFileName(propertyFileHandler.getDataFromPropertiesFile("testngFileName", testngConfigFilePath));
		xmlSuite.setThreadCount(10);
		testNG.run();
		for (XmlSuite suite : xmlSuites) {
			createTestNGXmlFile(suite);
		}
		log.info("TestNG xml file named \"" + xmlSuite.getFileName() + "\" is generated successfully");

	}

	// This method will create an Xml file based on the XmlSuite data
	public void createTestNGXmlFile(XmlSuite xmlSuite) {
		FileWriter fileWriter;
		propertyFileHandler = new PropertyFileHandler();
		try {
			fileWriter = new FileWriter(new File(configFileRootPath + propertyFileHandler
					.getDataFromPropertiesFile("testngFileName", configFileRootPath + "testng.properties")));
			fileWriter.write(xmlSuite.toXml());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			throw new FrameworkException("Not able to create testng xml file at runtime. " + e);
		}
	}

}
