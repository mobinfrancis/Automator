package com.automator.controllers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class WebDriverController {

	private WebDriver driver;
	private BrowserController browserController;
	private ConfigController configController;
	private static final Logger log = Logger.getLogger(WebDriverController.class);

	public WebDriver getDriver() {
		if (this.driver == null) {
			this.driver = initializeWebDriver(driver);
		}
		return this.driver;
	}

	private WebDriver initializeWebDriver(WebDriver driver) {
		String browserName = null;
		browserController = new BrowserController();
		configController = new ConfigController();
		if (configController.doesSystemPropertyConfigExistFor("Browser")) {
			browserName = System.getProperty("Browser");
		} else {
			browserName = "Chrome";// properties.getProperty("Browser");
		}
		log.info("Running tests in browser: " + browserName);
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = browserController.chromeSetUp(driver);
			break;
		case "firefox":
			driver = browserController.firefoxSetUp(driver);
			break;
		case "edge":
			driver = browserController.edgeSetUp(driver);
			break;
		case "safari":
			driver = browserController.safariSetUp(driver);
			break;
		default:
			log.error("The framework does not support running tests on the browser: " + browserName);
			break;
		}
		return driver;
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			log.info("WebDriver session ended and browser(s) exited");
		}
	}

}
