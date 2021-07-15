package com.automator.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderSource {

	@DataProvider(name = "dataProvider1")
	public static Object[][] getDataFromDataProvider1() {
		return new Object[][] { { 1, "Pune" }, { 2, "Kolkata" }, { 3, "Bangalore" } };
	}

	@DataProvider(name = "dataProvider2")
	public static Object[][] getDataFromDataProvider2(Method method) {
		String testCaseName = method.getName();
		if ("test1".equals(testCaseName)) {
			return new Object[][] { { "Mumbai", "Too much crowd" }, { "Chennai", "Too hot" } };
		} else if ("test2".equals(testCaseName)) {
			return new Object[][] { { "Hyderabad", "Less Expensive" }, { "Nagpur", "Famous for oranges" } };
		} else {
			return new Object[][] { { "N/A", "N/A" }, { "N/A", "N/A" } };
		}
	}

}
