package com.automator.pageComponent.home;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.automator.actions.webActions.WebDriverAction;

public class SearchPageComponent {

	private final WebDriver driver;
	private WebDriverAction action;
	private static final Logger log = Logger.getLogger(SearchPageComponent.class);
	private final By searchTextBox = By.xpath("//input[@type='text'][@name='search']");
	private final By searchIconButton = By.xpath("//button/i[@class='fa fa-search']");
	private final By searchContentHeading = By.xpath("//div[@id='content']/h1");
	private final By searchContentSubheading = By.xpath("//h2");
	private final By searchCriteriaTextBox = By.id("input-search");
	private final By searchButton = By.id("button-search");
	private final By iPhoneImage = By.xpath("//div[contains(@class,'product-grid')]/div/div/a/img");
	private final By iPhoneImageCaption = By.xpath("//div[@class='caption']/h4/a");
	private final By iPhoneImageText = By.xpath("//div[@class='caption']/p[1]");
	private final By iPhoneImagePrice = By.xpath("//div[@class='caption']/p[2]");

	public SearchPageComponent(WebDriver driver) {
		this.driver = driver;
		action = new WebDriverAction();
	}

	public SearchPageComponent enterTextInSearchTextBox(String textToEnter) {
		action.getWebElement(driver, searchTextBox).sendKeys(textToEnter);
		log.info("Entered text in the Search Text Box: " + textToEnter);
		Reporter.log("Entered text in the Search Text Box: " + textToEnter);
		return this;
	}

	public SearchPageComponent clearTextInSearchTextBox() {
		action.getWebElement(driver, searchTextBox).clear();
		log.info("Cleared text in the Search Text Box");
		Reporter.log("Cleared text in the Search Text Box");
		return this;
	}

	public SearchPageComponent clickOnSearchIconButton() {
		action.getWebElement(driver, searchIconButton).click();
		log.info("Clicked on the Search Icon Button");
		Reporter.log("Clicked on the Search Icon Button");
		return this;
	}

	public WebElement getSearchContentHeading() {
		return action.getWebElement(driver, searchContentHeading);
	}

	public WebElement getSearchContentSubheading() {
		return action.getWebElement(driver, searchContentSubheading);
	}

	public SearchPageComponent enterTextInSearchCriteriaTextBox(String textToEnter) {
		action.getWebElement(driver, searchCriteriaTextBox).sendKeys(textToEnter);
		log.info("Entered text in the Search Criteria Text Box: " + textToEnter);
		Reporter.log("Entered text in the Search Criteria Text Box: " + textToEnter);
		return this;
	}

	public WebElement getSearchCriteriaTextBox() {
		return action.getWebElement(driver, searchCriteriaTextBox);
	}

	public SearchPageComponent clickOnSearchButton() {
		action.getWebElement(driver, searchButton).click();
		log.info("Clicked on the Search Button");
		Reporter.log("Clicked on the Search Button");
		return this;
	}

	public WebElement getSearchButton() {
		return action.getWebElement(driver, searchButton);
	}

	public WebElement getIPhoneImage() {
		return action.getWebElement(driver, iPhoneImage);
	}

	public WebElement getIPhoneImageCaption() {
		return action.getWebElement(driver, iPhoneImageCaption);
	}

	public WebElement getIPhoneImageText() {
		return action.getWebElement(driver, iPhoneImageText);
	}

	public WebElement getIPhoneImagePrice() {
		return action.getWebElement(driver, iPhoneImagePrice);
	}

}
