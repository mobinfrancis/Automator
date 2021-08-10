package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.automator.handlers.reportHandler.FrameworkReportHandler;
import com.automator.pageComponent.home.SearchPageComponent;
import com.aventstack.extentreports.ExtentTest;

public class ProductSearch extends BaseFunctionality {

	private FrameworkReportHandler frameworkReportHandler;
	private ExtentTest extentTest;
	private String testSuiteName;
	private String testMethodName;
	private String browserName;
	private SearchPageComponent searchPageComponent;
	private static final Logger log = Logger.getLogger(ProductSearch.class);
	private String searchedProductName;

	public ProductSearch(FrameworkReportHandler frameworkReportHandler, ExtentTest extentTest, String testSuiteName,
			String testMethodName) {
		this.frameworkReportHandler = frameworkReportHandler;
		this.extentTest = extentTest;
		this.testSuiteName = testSuiteName;
		this.testMethodName = testMethodName;
	}

	@Override
	public void launch(String browser) {
		super.launch(browser);
		this.browserName = browser;

	}

	@Override
	public void visit(String url) {
		super.visit(url);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info",
				"Visited the url: " + url + " , on browser: " + this.browserName, this.extentTest, getDriver(),
				testSuiteName, testMethodName);
	}

	@Override
	public void end() {
		super.end();
		log.info("WebDriver session ended and browser(s) exited");
	}

	public void searchProduct(String productToSearch) {
		this.searchPageComponent = new SearchPageComponent(driver);
		this.searchPageComponent = this.searchPageComponent.enterTextInSearchTextBox(productToSearch);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info",
				"Text entered in Search TextBox: " + productToSearch, this.extentTest, getDriver(), testSuiteName,
				testMethodName);
		this.searchPageComponent = this.searchPageComponent.clickOnSearchIconButton();
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info", "Clicked on the Search button",
				this.extentTest, getDriver(), testSuiteName, testMethodName);
		this.searchPageComponent = this.searchPageComponent.clearTextInSearchTextBox();
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("info", "Cleared text in the Search TextBox",
				this.extentTest, getDriver(), testSuiteName, testMethodName);
		this.searchedProductName = productToSearch;

	}

	public void validateTheSearchedProductHeading() {
		Assert.assertEquals(this.searchPageComponent.getSearchContentHeading().getText(),
				"Search - " + this.searchedProductName);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("pass",
				"Searched product heading is getting correctly displayed", this.extentTest, getDriver(), testSuiteName,
				testMethodName);
	}

	public void validateTheSearchedProductSubheading() {
		Assert.assertEquals(this.searchPageComponent.getSearchContentSubheading().getText(), "Search");
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("pass",
				"Searched product sub-heading is getting correctly displayed", this.extentTest, getDriver(),
				testSuiteName, testMethodName);
	}

	public void validateTheTextPresentInSearchCriteriaTextBox() {
		Assert.assertEquals(this.searchPageComponent.getSearchCriteriaTextBox().getAttribute("value"),
				this.searchedProductName);
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("pass",
				"The text present in the Search Criteria TextBox is correct", this.extentTest, getDriver(),
				testSuiteName, testMethodName);
	}

	public void validateTheSearchButtonIsEnabled() {
		Assert.assertTrue(this.searchPageComponent.getSearchButton().isEnabled());
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("pass",
				"The Search button is in enabled condition", this.extentTest, getDriver(), testSuiteName,
				testMethodName);
	}

	public void validateCorrectProductItemIsDisplayedIfPresent() {
		if (this.searchedProductName.toLowerCase().equals("iphone")) {
			Assert.assertEquals(this.searchPageComponent.getIPhoneImage().getAttribute("alt"), "iPhone");
			Assert.assertEquals(this.searchPageComponent.getIPhoneImage().getAttribute("title"), "iPhone");
			Assert.assertEquals(this.searchPageComponent.getIPhoneImageCaption().getText(), "iPhone");
			Assert.assertEquals(this.searchPageComponent.getIPhoneImageText().getText(),
					"iPhone is a revolutionary new mobile phone that allows you to make a call by simply tapping a name o..");
			Assert.assertTrue(this.searchPageComponent.getIPhoneImagePrice().getText().contains("$123.20")
					&& this.searchPageComponent.getIPhoneImagePrice().getText().contains("Ex Tax: $101.00"));
			frameworkReportHandler.captureAndAttachScreenshotForExtentReport("pass",
					"Correct product items are getting displayed", this.extentTest, getDriver(), testSuiteName,
					testMethodName);
		}
	}

}
