package com.automator.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automator.handlers.exceptionHandler.FrameworkException;
import com.automator.utilities.DateTimeUtility;

/**
 * This class helps to capture screenshots for the tests at runtime and store
 * them.
 * 
 * @author Sumon Dey
 *
 */
public class ScreenshotController {

	private File destinationFile;
	private String screenShotParentFolderPath;
	private String testSuiteName;
	private String testName;

	public String addScreenshotToReport(WebDriver driver) {
		String screenshotFilePath = null;
		File screenshotFile = getCurrentScreenshotAsFile(driver);
		if (screenshotFile != null) {
			if (screenshotFile.exists()) {
				screenshotFilePath = getScreenShotParentFolderPath() + "Screenshots" + File.separator
						+ getTestSuiteName() + File.separator + getTestSuiteName() + "_"
						+ DateTimeUtility.getFormattedCurrentDateTime("dd-MMM-yyyy_hh-mm-ss_aa") + "_" + getTestName()
						+ ".png";
				destinationFile = new File(screenshotFilePath);
			}
		}
		try {
			FileUtils.copyFile(screenshotFile, destinationFile);
		} catch (IOException e) {
			throw new FrameworkException("Not able to copy screenshot file from source to destination", e);
		}
		return screenshotFilePath;
	}

	public File getCurrentScreenshotAsFile(WebDriver driver) {
		if (driver != null) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			return takesScreenshot.getScreenshotAs(OutputType.FILE);
		}
		return null;
	}

	public void setScreenShotParentFolderPath(String screenShotParentFolderPath) {
		this.screenShotParentFolderPath = screenShotParentFolderPath;
	}

	public String getScreenShotParentFolderPath() {
		return this.screenShotParentFolderPath;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

}
