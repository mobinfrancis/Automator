package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automator.controllers.WebDriverController;
import com.automator.handlers.PageHandler;

public class BaseFunctionality {

	public WebDriver driver;
	public PageHandler pageHandler;
	public WebDriverController webDriverController;
	private static final Logger log = Logger.getLogger(BaseFunctionality.class);

	public WebDriver getDriver() {
		return this.driver;
	}

	private WebDriver initializeAndGetDriver() {
		this.webDriverController = new WebDriverController();
		this.driver = this.webDriverController.getDriver();
		return driver;
	}

	public void visit(String url) {
		this.driver = initializeAndGetDriver();
		this.pageHandler = new PageHandler(driver);
		this.driver = this.pageHandler.goToURL(url);
	}

	public void end() {
		if (this.driver != null) {
			this.driver.quit();
			log.info("WebDriver session ended and browser(s) exited");
		}
	}

}
