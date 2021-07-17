package com.automator.actions.webActions;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.automator.handlers.exceptionHandler.FrameworkException;


public class WebDriverAction extends JavascriptAction {

	private int timeOutInSeconds = 3;
	private static final Logger log = Logger.getLogger(WebDriverAction.class);

	/**
	 * Method to switch to the default content in the page
	 * 
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * Method to find whether the page has one or more elements or not
	 * 
	 * @param driver
	 * @param by
	 * @return boolean
	 */
	public boolean hasElement(WebDriver driver, By by) {
		boolean isElementPresent = false;
		Date start = new Date();
		Date end = new Date();
		try {
			isElementPresent = driver.findElements(by).size() > 0;
			while (!(isElementPresent) && ((end.getTime() - start.getTime()) < (timeOutInSeconds * 1000))) {
				end = new Date();
				isElementPresent = driver.findElements(by).size() > 0;
			}
		} catch (NoSuchElementException e) {
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		} catch (Exception e) {
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		}
		return isElementPresent;
	}

	/**
	 * Method to get the first matched element after checking whether it is present
	 * in the page or not
	 * 
	 * @param driver
	 * @param by
	 * @return WebElement
	 */
	public WebElement getElement(WebDriver driver, By by) {
		try {
			switchToDefaultContent(driver);
			if (hasElement(driver, by)) {
				WebElement webElement = driver.findElement(by);
				log.info("Found element with identifier: " + by);
				return webElement;
			}
		} catch (NoSuchElementException e) {
			log.error("Could not find element with identifier: " + by, e);
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		} catch (Exception e) {
			log.error("Could not find element with identifier: " + by, e);
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		}
		return null;
	}

	/**
	 * Method to get the first matched element if its existence in the page is
	 * already confirmed
	 * 
	 * @param driver
	 * @param by
	 * @return WebElement
	 */
	public WebElement getWebElement(WebDriver driver, By by) {
		try {
			WebElement webElement = driver.findElement(by);
			log.info("Found element with identifier: " + by);
			return webElement;
		} catch (NoSuchElementException e) {
			log.error("Could not find element with identifier: " + by, e);
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		} catch (Exception e) {
			log.error("Could not find element with identifier: " + by, e);
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		}
	}

	/**
	 * Method to get all the matched elements after checking whether they are
	 * present in the page or not
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public List<WebElement> getElements(WebDriver driver, By by) {
		switchToDefaultContent(driver);
		try {
			if (hasElement(driver, by)) {
				List<WebElement> webElements = driver.findElements(by);
				log.info("Found element(s) with identifier: " + by);
				return webElements;
			}
		} catch (NoSuchElementException e) {
			log.error("Could not find element with identifier: " + by, e);
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		} catch (Exception e) {
			log.error("Could not find element with identifier: " + by, e);
			throw new FrameworkException("Could not find element with identifier: " + by, e);
		}
		return null;
	}

	/**
	 * Method to select a value from a dropdown (using index, value and visible
	 * text)
	 * 
	 * @param webElement
	 * @param visibleText
	 */
	//
	public void selectOptionFromDropDown(WebElement webElement, String selectionMethod, Object optionToSelect) {
		try {
			if (webElement != null) {
				Select select = new Select(webElement);
				switch (selectionMethod) {
				case "index":
					select.selectByIndex((int) optionToSelect);
					log.info("Selected option: " + (int) optionToSelect + ", from the dropdown with identifier: "
							+ webElement);
					break;
				case "value":
					select.selectByValue((String) optionToSelect);
					log.info("Selected option: " + (String) optionToSelect + ", from the dropdown with identifier: "
							+ webElement);
					break;
				case "visibleText":
					select.selectByVisibleText((String) optionToSelect);
					log.info("Selected option: " + (String) optionToSelect + ", from the dropdown with identifier: "
							+ webElement);
					break;
				}
			} else {
				log.error("Could not find element with identifier: " + webElement);
			}
		} catch (Exception e) {
			log.error("Could not select from dropdown");
			throw new FrameworkException("Not able to select option from Dropdown: ", e);
		}

	}

}
