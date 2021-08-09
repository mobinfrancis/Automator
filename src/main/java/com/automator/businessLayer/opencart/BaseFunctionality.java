package com.automator.businessLayer.opencart;

import org.openqa.selenium.WebDriver;

import com.automator.controllers.WebDriverController;
import com.automator.handlers.PageHandler;

public class BaseFunctionality {

	public WebDriver driver;
	public PageHandler pageHandler;
	public WebDriverController webDriverController;

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
		}
	}

}
