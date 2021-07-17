package com.automator.handlers;

import org.testng.asserts.SoftAssert;

public class AssertHandler {

	/**
	 * SoftAssert - It will collect all errors during @Test but will not throw
	 * exception when an assert fails and will continue with the next step after the
	 * assert statement. If there is any exception and you want to throw it, then
	 * you need to use softAssert.assertAll() method as a last statement in
	 * the @Test and test suite will continue with the next @Test as it is.
	 *
	 * @return new SoftAssert object
	 */

	public SoftAssert getSoftAssert() {
		SoftAssert softAssert = new SoftAssert();
		return softAssert;
	}

}
