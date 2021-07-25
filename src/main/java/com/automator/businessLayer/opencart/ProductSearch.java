package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.automator.controllers.WebDriverController;
import com.automator.handlers.PageHandler;
import com.automator.pageComponent.home.SearchPageComponent;

public class ProductSearch {

	private WebDriver driver;
	private PageHandler pageHandler;
	private WebDriverController webDriverController;
	private SearchPageComponent searchPageComponent;
	private static final Logger log = Logger.getLogger(ProductSearch.class);
	private String searchedProductName;

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

	public void searchProduct(String productToSearch) {
		this.searchPageComponent = new SearchPageComponent(driver);
		this.searchPageComponent = this.searchPageComponent.enterTextInSearchTextBox(productToSearch);
		this.searchPageComponent = this.searchPageComponent.clickOnSearchIconButton();
		this.searchedProductName = productToSearch;

	}

	public void validateTheSearchedProductHeading() {
		Assert.assertEquals(this.searchPageComponent.getSearchContentHeading().getText(),
				"Search - " + this.searchedProductName);
	}

	public void validateTheSearchedProductSubheading() {
		Assert.assertEquals(this.searchPageComponent.getSearchContentSubheading().getText(), "Search");
	}

	public void validateTheTextPresentInSearchCriteriaTextBox() {
		Assert.assertEquals(this.searchPageComponent.getSearchCriteriaTextBox().getAttribute("value"),
				this.searchedProductName);
	}

	public void validateTheSearchButtonIsEnabled() {
		Assert.assertTrue(this.searchPageComponent.getSearchButton().isEnabled());
	}

	public void end() {
		if (this.driver != null) {
			this.driver.quit();
			log.info("WebDriver session ended and browser(s) exited");
		}
	}

}