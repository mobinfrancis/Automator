package com.automator.controllers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automator.handlers.exceptionHandler.FrameworkException;

public class WebDriverController {

	private WebDriver driver;
	private BrowserController browserController;
	private String browserName;
	private static final Logger log = Logger.getLogger(WebDriverController.class);

	public WebDriverController(String browser) {
		this.browserName = browser;
	}

	public WebDriver getDriver() {
		if (this.driver == null) {
			this.driver = initializeWebDriverAndLaunchBrowser(driver, browserName);
		}
		return this.driver;
	}

	private WebDriver initializeWebDriverAndLaunchBrowser(WebDriver driver, String browserName) {
		this.browserController = new BrowserController();
		log.info("Running tests in browser: " + browserName);
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = this.browserController.chromeSetUp(driver);
			break;
		case "firefox":
			driver = this.browserController.firefoxSetUp(driver);
			break;
		case "edge":
			driver = this.browserController.edgeSetUp(driver);
			break;
		case "safari":
			driver = this.browserController.safariSetUp(driver);
			break;
		default:
			log.error("The framework does not support running tests on the browser: " + browserName);
			throw new FrameworkException("The framework does not support running tests on the browser: " + browserName);
		}
		return driver;
	}

}
