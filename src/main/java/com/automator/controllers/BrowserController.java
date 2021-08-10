package com.automator.controllers;

import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
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
	private static String driverPath = System.getProperty("user.dir") + File.separator + "drivers" + File.separator;

	public WebDriver chromeSetUp(WebDriver driver) {
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
		DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDisplayMode();
		int screen_width = (int) displayMode.getWidth();
		int screen_height = (int) displayMode.getHeight();
		log.info("Desktop Screen Resolution: " + screen_width + "/" + screen_height);
		driver = new ChromeDriver(getChromeOptions());
		log.info("Running Chrome Browser Dimension: " + driver.manage().window().getSize());
		return driver;
	}

	public WebDriver firefoxSetUp(WebDriver driver) {
		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver edgeSetUp(WebDriver driver) {
		System.setProperty("webdriver.edge.driver", driverPath + "MicrosoftWebDriver.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver safariSetUp(WebDriver driver) {
		driver = new SafariDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public ChromeOptions getChromeOptions() {
		ChromeOptions chromeOptions = null;
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		if (configController.doesSystemPropertyConfigExistFor("incognito")) {
			if (System.getProperty("incognito").equals("true")) {
				chromeOptions.addArguments("--incognito");
			}
		}
		if (configController.doesSystemPropertyConfigExistFor("headless")) {
			if (System.getProperty("headless").equals("true")) {
				chromeOptions.addArguments("--headless");
			}
		}
		return chromeOptions;
	}

}
