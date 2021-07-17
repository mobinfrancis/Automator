package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automator.controllers.WebDriverController;
import com.automator.handlers.PageHandler;

public class Opencart {

	private WebDriver driver;
	private String testSuiteName;
	private String testName;
	private PageHandler pageHandler;
	private WebDriverController webDriverController;
	private static final Logger log = Logger.getLogger(Opencart.class);

	public Opencart() {

	}

	public Opencart(String testSuiteName, String testName) {
		this.testSuiteName = testSuiteName;
		this.testName = testName;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public String getTestName() {
		return testName;
	}

	private WebDriver initializeAndGetDriver() {
		webDriverController = new WebDriverController();
		driver = webDriverController.getDriver();
		return driver;
	}

	public void visit(String url) {
		driver = initializeAndGetDriver();
		pageHandler = new PageHandler(driver);
		driver = pageHandler.goToURL(url);
	}

	public void end() {
		webDriverController.quitDriver();
	}

}
