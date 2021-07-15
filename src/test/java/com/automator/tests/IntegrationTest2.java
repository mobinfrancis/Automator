package com.automator.tests;

import org.testng.annotations.Test;

import com.automator.utilities.DataProviderSource;

public class IntegrationTest2 {

	@Test(dataProvider = "dataProvider1", dataProviderClass = DataProviderSource.class)
	public void test1(int cityIndex, String cityName) {
		System.out.println(cityIndex + ": " + cityName);
	}

	@Test(dataProvider = "dataProvider2", dataProviderClass = DataProviderSource.class)
	public void test2(String cityName, String description) {
		System.out.println(cityName + " -> " + description);
	}

}
