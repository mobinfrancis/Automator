package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.automator.controllers.WebDriverController;
import com.automator.handlers.PageHandler;
import com.automator.pageComponent.home.SearchPageComponent;

public class Opencart {

	private WebDriver driver;
	private PageHandler pageHandler;
	private WebDriverController webDriverController;
	private SearchPageComponent searchPageComponent;
	private static final Logger log = Logger.getLogger(Opencart.class);

	public Opencart() {

	}

	public WebDriver getDriver() {
		return driver;
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

	public void searchProduct(String productName) {
		searchPageComponent = new SearchPageComponent(driver);
		searchPageComponent = searchPageComponent.enterTextInSearchTextBox(productName);
		searchPageComponent = searchPageComponent.clickOnSearchButton();
		Assert.assertEquals(searchPageComponent.getSearchContentHeading().getText(), "Search - " + productName);
	}

	public void end() {
		webDriverController.quitDriver();
	}

}
