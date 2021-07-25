package com.automator.pageComponent.home;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.automator.actions.webActions.WebDriverAction;

public class ItemPageComponent extends WebDriverAction {

	private final WebDriver driver;
	private static final Logger log = Logger.getLogger(ItemPageComponent.class);
	private final By desktopsNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Desktops']");
	private final By laptopsAndNotebooksNavbar = By
			.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Laptops & Notebooks']");
	private final By componentsNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Components']");
	private final By tabletsNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Tablets']");
	private final By softwareNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Software']");
	private final By phonesAndPDAsNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Phones & PDAs']");
	private final By camerasNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='Cameras']");
	private final By mp3PlayersNavbar = By.xpath("//ul[@class='nav navbar-nav']/li/a[text()='MP3 Players']");

	public ItemPageComponent(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getDesktopsNavbar() {
		return getWebElement(driver, desktopsNavbar);
	}

	public WebElement getLaptopsAndNotebooksNavbar() {
		return getWebElement(driver, laptopsAndNotebooksNavbar);
	}

	public WebElement getComponentsNavbar() {
		return getWebElement(driver, componentsNavbar);
	}

	public WebElement getTabletsNavbar() {
		return getWebElement(driver, tabletsNavbar);
	}

	public WebElement getSoftwareNavbar() {
		return getWebElement(driver, softwareNavbar);
	}

	public WebElement getPhonesAndPDAsNavbar() {
		return getWebElement(driver, phonesAndPDAsNavbar);
	}

	public WebElement getCamerasNavbar() {
		return getWebElement(driver, camerasNavbar);
	}

	public WebElement getMP3PlayersNavbar() {
		return getWebElement(driver, mp3PlayersNavbar);
	}

}
