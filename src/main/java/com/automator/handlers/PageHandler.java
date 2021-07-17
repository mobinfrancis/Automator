package com.automator.handlers;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.automator.handlers.exceptionHandler.FrameworkException;


public class PageHandler {

	private WebDriver driver;
	private static final Logger log = Logger.getLogger(PageHandler.class);

	public PageHandler(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver goToURL(String url) {
		try {
			driver.get(url);
			log.info("Launched URL: " + url);
		} catch (Exception e) {
			log.error("Could not launch URL: " + url, e);
			throw new FrameworkException("Could not launch URL: " + url, e);
		}
		return driver;
	}

	public String getPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			log.error("Could not get the page title", e);
			throw new FrameworkException("Could not get the page title", e);
		}
	}

	public String getCurrentUrl() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			log.error("Could not get the current url", e);
			throw new FrameworkException("Could not get the current url", e);
		}
	}

	public String getCurrentWindowHandle() {
		try {
			return driver.getWindowHandle();
		} catch (Exception e) {
			log.error("Could not get the current window handle", e);
			throw new FrameworkException("Could not get the current window handle", e);
		}
	}

	public Set<String> getCurrentWindowHandles() {
		try {
			return driver.getWindowHandles();
		} catch (Exception e) {
			log.error("Could not get the current window handles", e);
			throw new FrameworkException("Could not get the current window handles", e);
		}

	}
}
