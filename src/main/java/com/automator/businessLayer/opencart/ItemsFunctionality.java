package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.automator.handlers.reportHandler.FrameworkReportHandler;
import com.automator.pageComponent.home.ItemPageComponent;
import com.aventstack.extentreports.ExtentTest;

public class ItemsFunctionality extends BaseFunctionality {

	private FrameworkReportHandler frameworkReportHandler;
	private ExtentTest extentTest;
	private String testSuiteName;
	private String testMethodName;
	private String browserName;
	private ItemPageComponent itemPageComponent;
	private static final Logger log = Logger.getLogger(ItemsFunctionality.class);
	private String navbarItemName;

	public ItemsFunctionality(FrameworkReportHandler frameworkReportHandler, ExtentTest extentTest,
			String testSuiteName, String testMethodName) {
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

	public void validateNavbarItemIsEnabled(String navbarItem) {
		this.navbarItemName = navbarItem;
		this.itemPageComponent = new ItemPageComponent(driver);
		switch (this.navbarItemName) {
		case "Desktops":
			Assert.assertTrue(this.itemPageComponent.getDesktopsNavbar().isEnabled());
			break;
		case "Laptops & Notebooks":
			Assert.assertTrue(this.itemPageComponent.getLaptopsAndNotebooksNavbar().isEnabled());
			break;
		case "Components":
			Assert.assertTrue(this.itemPageComponent.getComponentsNavbar().isEnabled());
			break;
		case "Tablets":
			Assert.assertTrue(this.itemPageComponent.getTabletsNavbar().isEnabled());
			break;
		case "Software":
			Assert.assertTrue(this.itemPageComponent.getSoftwareNavbar().isEnabled());
			break;
		case "Phones & PDAs":
			Assert.assertTrue(this.itemPageComponent.getPhonesAndPDAsNavbar().isEnabled());
			break;
		case "Cameras":
			Assert.assertTrue(this.itemPageComponent.getCamerasNavbar().isEnabled());
			break;
		case "MP3 Players":
			Assert.assertTrue(this.itemPageComponent.getMP3PlayersNavbar().isEnabled());
			break;
		default:
			throw new IllegalArgumentException("Incorrect Navbar item: " + navbarItemName);
		}
		log.info("Navbar item - " + navbarItem + " - is present and enabled");
		frameworkReportHandler.captureAndAttachScreenshotForExtentReport("pass",
				"Navbar item - " + navbarItem + " - is present and enabled", this.extentTest, getDriver(),
				testSuiteName, testMethodName);
	}

}
