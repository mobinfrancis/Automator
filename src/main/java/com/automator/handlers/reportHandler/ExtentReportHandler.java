package com.automator.handlers.reportHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportHandler {

	public ExtentHtmlReporter configureExtentHtmlReporter(ExtentHtmlReporter extentHtmlReporter) {
		extentHtmlReporter.config().setDocumentTitle("AUTOMATOR");
		extentHtmlReporter.config().setReportName("AUTOMATOR Extent Report");
		extentHtmlReporter.config().setTheme(Theme.DARK);
		extentHtmlReporter.config().setTimeStampFormat("dd-MMM-yyyy_hh-mm-ss_aa");
		return extentHtmlReporter;
	}

	public ExtentReports setSystemInfoInExtentReports(ExtentReports extentReports, String testSuiteName) {
		extentReports.setSystemInfo("Name", testSuiteName);
		extentReports.setSystemInfo("Browser", "Chrome");
		extentReports.setSystemInfo("Environment", "Test");
		return extentReports;
	}

}
