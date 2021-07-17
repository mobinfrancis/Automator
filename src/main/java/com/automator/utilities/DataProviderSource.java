package com.automator.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderSource {

	@DataProvider(name = "dataProvider1")
	public static Object[][] getDataFromDataProvider1() {
		return new Object[][] { { "", "" }, { "", "" }, { "", "" } };
	}

	@DataProvider(name = "dataProvider2")
	public static Object[][] getDataFromDataProvider2(Method method) {
		String testCaseName = method.getName();
		if ("test1".equals(testCaseName)) {
			return new Object[][] { { "", "" }, { "", "" } };
		} else if ("test2".equals(testCaseName)) {
			return new Object[][] { { "", "" }, { "", "" } };
		} else {
			return new Object[][] { { "", "" }, { "", "" } };
		}
	}

}
