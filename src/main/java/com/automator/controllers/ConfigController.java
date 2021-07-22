package com.automator.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigController {

	public boolean doesSystemPropertyConfigExistFor(String propertyKey) {
		if (System.getProperty(propertyKey) != null && !System.getProperty(propertyKey).isEmpty()) {
			return true;
		}
		return false;
	}

}
