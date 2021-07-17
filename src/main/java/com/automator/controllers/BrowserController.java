package com.automator.controllers;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserController {

	private ConfigController configController;
	private static final Logger log = Logger.getLogger(BrowserController.class);

	public WebDriver chromeSetUp(WebDriver driver) {
		String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;
		String chromeDriverName = "chromedriver.exe";
		configController = new ConfigController();
		if (configController.doesSystemPropertyConfigExistFor("DRIVER_LOCATION")) {
			driverPath = System.getProperty("DRIVER_LOCATION");
		}
		if (configController.doesSystemPropertyConfigExistFor("macOS")) {
			if (System.getProperty("macOS").equals("true")) {
				chromeDriverName = "chromedriver";
			}
		}
		log.info("Chromedriver path: " + driverPath + chromeDriverName);
		System.setProperty("webdriver.chrome.driver", driverPath + chromeDriverName);
		driver = new ChromeDriver(getChromeOptions());
		return driver;
	}

	public WebDriver firefoxSetUp(WebDriver driver) {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "geckodriver.exe");
		driver = new FirefoxDriver();
		return driver;
	}

	public WebDriver edgeSetUp(WebDriver driver) {
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + File.separator + "drivers"
				+ File.separator + "MicrosoftWebDriver.exe");
		driver = new InternetExplorerDriver();
		return driver;
	}

	public WebDriver safariSetUp(WebDriver driver) {
		driver = new SafariDriver();
		return driver;
	}

	public ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = null;
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		return chromeOptions;
	}

}
