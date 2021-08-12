package com.automator.controllers;

public class ConfigController {

	public boolean doesSystemPropertyConfigExistFor(String propertyKey) {
		if (System.getProperty(propertyKey) != null && !System.getProperty(propertyKey).isEmpty()) {
			return true;
		}
		return false;
	}

}
