package com.automator.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigController {

	private Properties prop = new Properties();
	private String configFileRootPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources" + File.separator;
	private static final Logger log = Logger.getLogger(ConfigController.class);

	public void saveDataInPropertiesFile(String key, String value, String configFileName) {
		try {
			prop.setProperty(key, value);
			prop.store(new FileOutputStream(configFileRootPath + configFileName), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDataFromPropertiesFile(String key, String configFileName) {
		String value = "";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(configFileRootPath + configFileName));
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public boolean doesSystemPropertyConfigExistFor(String propertyKey) {
		if (System.getProperty(propertyKey) != null && !System.getProperty(propertyKey).isEmpty()) {
			return true;
		}
		return false;
	}

}
