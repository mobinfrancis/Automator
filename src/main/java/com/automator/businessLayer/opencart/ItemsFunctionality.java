package com.automator.businessLayer.opencart;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.automator.controllers.WebDriverController;
import com.automator.handlers.PageHandler;
import com.automator.pageComponent.home.ItemPageComponent;
import com.automator.pageComponent.home.SearchPageComponent;

public class ItemsFunctionality {

	private WebDriver driver;
	private PageHandler pageHandler;
	private WebDriverController webDriverController;
	private ItemPageComponent itemPageComponent;
	private static final Logger log = Logger.getLogger(ItemsFunctionality.class);
	private String navbarItemName;

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

	public void validateNavbarItemIsEnabled(String navbarItem) {
		this.navbarItemName = navbarItem;
		this.itemPageComponent = new ItemPageComponent(driver);
		switch (this.navbarItemName) {
		case "Desktops":
			Assert.assertTrue(this.itemPageComponent.getDesktopsNavbar().isEnabled());
			break;
		case "LaptopsAndNotebooks":
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
		case "PhonesAndPDAs":
			Assert.assertTrue(this.itemPageComponent.getPhonesAndPDAsNavbar().isEnabled());
			break;
		case "Cameras":
			Assert.assertTrue(this.itemPageComponent.getCamerasNavbar().isEnabled());
			break;
		case "MP3Players":
			Assert.assertTrue(this.itemPageComponent.getMP3PlayersNavbar().isEnabled());
			break;
		}

	}

	public void end() {
		if (this.driver != null) {
			this.driver.quit();
			log.info("WebDriver session ended and browser(s) exited");
		}
	}

}
