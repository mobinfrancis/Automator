package com.automator.tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automator.controllers.ConfigController;


public class IntegrationTest1 {

	private static final Logger log = Logger.getLogger(IntegrationTest1.class);

	@BeforeMethod
	public void test1(Method method) {
		log.info("=============== Initiating Test: " + method.getName() + " ===============");
	}

	@Test
	public void test1(Method method, ITestContext iTestContext) {
		String testSuiteName = iTestContext.getSuite().getName();
		String testName = method.getName();
		String url = "http://opencart.abstracta.us/";
		ConfigController configController = new ConfigController();
	}

}
