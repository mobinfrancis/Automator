package com.automator.pageComponent.home;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.automator.actions.webActions.WebDriverAction;

public class SearchPageComponent extends WebDriverAction {

	private final WebDriver driver;
	private static final Logger log = Logger.getLogger(SearchPageComponent.class);
	private final By searchTextBox = By.xpath("//input[@type='text'][@name='search']");
	private final By searchButton = By.xpath("//button/i[@class='fa fa-search']");
	private final By searchContentHeading = By.xpath("//div[@id='content']/h1");

	public SearchPageComponent(WebDriver driver) {
		this.driver = driver;
	}

	public SearchPageComponent enterTextInSearchTextBox(String textToEnter) {
		getWebElement(driver, searchTextBox).sendKeys(textToEnter);
		log.info("Entered text in the Search Text Box: " + textToEnter);
		Reporter.log("Entered text in the Search Text Box: " + textToEnter);
		return this;
	}

	public SearchPageComponent clickOnSearchButton() {
		getWebElement(driver, searchButton).click();
		log.info("Clicked on the Search Button");
		Reporter.log("Clicked on the Search Button");
		return this;
	}

	public WebElement getSearchContentHeading() {
		return getWebElement(driver, searchContentHeading);
	}

}
