package com.automator.tests;

import org.testng.annotations.Test;

public class IntegrationTest {

	@Test(groups = {"integration1", "integration2"})
	public void test1() {
		System.out.println("Inside test1");
	}

	@Test(groups = {"integration1"})
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

}
