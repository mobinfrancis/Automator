package com.automator.actions.webActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automator.handlers.exceptionHandler.FrameworkException;


public class JavascriptAction {

	private static final Logger log = Logger.getLogger(JavascriptAction.class);

	/**
	 * Method to execute JavaScript code
	 * 
	 * @param driver
	 * @param javascriptString
	 * @return
	 */
	public Object runJavascript(WebDriver driver, String javascriptString) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javascriptString);
	}

	/**
	 * Method to execute asynchronous JavaScript code
	 * 
	 * @param driver
	 * @param asyncJavascriptString
	 * @param webElement
	 * @return
	 */
	public Object runAsyncJavascript(WebDriver driver, String asyncJavascriptString, WebElement webElement) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		return js.executeAsyncScript(asyncJavascriptString, webElement);
	}

	/**
	 * Method to execute JQuery code
	 * 
	 * @param driver
	 * @param jQueryString
	 * @return
	 */
	public Object runJquery(WebDriver driver, String jQueryString) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(jQueryString);
	}

	/**
	 * Method to execute asynchronous JQuery code
	 * 
	 * @param driver
	 * @param asyncJQueryString
	 * @param webElement
	 * @return
	 */
	public Object runJquery(WebDriver driver, String asyncJQueryString, WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(asyncJQueryString, webElement);
	}

	/**
	 * Method to find and return web element using JavaScript
	 * 
	 * @param driver
	 * @param queryString
	 * @param timeout
	 * @return
	 */
	public Object getElementUsingJavascript(WebDriver driver, String queryString, int timeout) {
		Object element = null;
		try {
			if (doesElementExist(driver, queryString, timeout)) {
				log.info("Element with identifier: " + queryString + " -> exists");
				if (isElementVisible(driver, queryString, timeout)) {
					log.info("Element with identifier: " + queryString + " -> is visible");
					element = runJavascript(driver, queryString);
				} else {
					log.error("Element with identifier: " + queryString + " -> exists, but is not visible");
				}
			} else {
				log.error("Element with identifier: " + queryString + " -> does not exist");
			}
		} catch (Exception e) {
			log.error("Could not find element with identifier: " + queryString);
			throw new FrameworkException("Could not find element with identifier: " + queryString, e);
		}
		return element;
	}

	/**
	 * Method to check whether an element exists in web page or not
	 * 
	 * @param driver
	 * @param queryString
	 * @param waitTime
	 * @return
	 */
	public boolean doesElementExist(WebDriver driver, String queryString, int waitTime) {
		try {
			return new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(final WebDriver driver) {
					return (Boolean) ((JavascriptExecutor) driver)
							.executeScript("return " + queryString + ".length > 0");
				}
			});
		} catch (TimeoutException e) {
			log.error("Element with identifier: " + queryString + " -> does not exist");
			return false;
		}
	}

	/**
	 * Method to check whether an element is visible in web page or not
	 * 
	 * @param driver
	 * @param queryString
	 * @param waitTime
	 * @return
	 */
	public boolean isElementVisible(WebDriver driver, String queryString, int waitTime) {
		try {
			return new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(final WebDriver driver) {
					return (Boolean) ((JavascriptExecutor) driver)
							.executeScript("return " + queryString + ".filter(':visible').length > 0");
				}
			});
		} catch (TimeoutException e) {
			log.error("Element with identifier: " + queryString + " -> is not visible");
			return false;
		}
	}

	/**
	 * Method to check whether an element is in enabled condition in web page or not
	 * 
	 * @param driver
	 * @param queryString
	 * @param waitTime
	 * @return
	 */
	public boolean isElementEnabled(WebDriver driver, String queryString, int waitTime) {
		try {
			return new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(final WebDriver driver) {
					return !(Boolean) ((JavascriptExecutor) driver)
							.executeScript("return " + queryString + ".is(':disabled')");
				}
			});
		} catch (TimeoutException e) {
			log.error("Element with identifier: " + queryString + " -> is not in enabled condition");
			return false;
		}
	}

	/**
	 * Method to wait for the complete page to load (using JavaScript)
	 * 
	 * @param driver
	 * @param waitTime
	 */
	public void waitForPageLoad(WebDriver driver, int waitTime) {
		try {
			new WebDriverWait(driver, waitTime).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			});
		} catch (TimeoutException e) {
			log.error("The page has not loaded");
		}
	}

}
