package com.automator.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IntegrationTest {

	@BeforeSuite
	public void suiteSetUp() {
		System.out.println("Inside BeforeSuite");
		System.out.println("*****************************");
	}

	@BeforeTest
	public void testSetUp() {
		System.out.println("Inside BeforeTest");
	}

	@BeforeClass
	public void classSetUp() {
		System.out.println("Inside BeforeClass");
	}

	@BeforeMethod
	public void methodSetUp() {
		System.out.println("Inside BeforeMethod");
	}

	@Test(groups = { "integration1", "integration2" })
	public void test1() {
		System.out.println("Inside test1");
	}

	@Test(groups = { "integration1" })
	public void test2() {
		System.out.println("Inside test2");
	}

	@Test
	public void test3() {
		System.out.println("Inside test3");
	}

	@Test
	public void test4() {
		System.out.println("Inside test4");
	}

	@Test
	public void test5() {
		System.out.println("Inside test5");
	}

	@AfterMethod
	public void methodTeardown() {
		System.out.println("Inside AfterMethod");
	}

	@AfterClass
	public void classTeardown() {
		System.out.println("Inside AfterClass");
	}

	@AfterTest
	public void testTeardown() {
		System.out.println("Inside AfterTest");
	}

	@AfterSuite
	public void suiteTeardown() {
		System.out.println("*****************************");
		System.out.println("Inside AfterSuite");
	}

}
