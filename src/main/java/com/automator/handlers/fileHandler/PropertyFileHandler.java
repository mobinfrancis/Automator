package com.automator.handlers.fileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileHandler {

	private Properties prop = new Properties();

	public void saveDataInPropertiesFile(String key, String value, String configFileName) {
		String configFileRootPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator;
		try {
			prop.setProperty(key, value);
			prop.store(new FileOutputStream(configFileRootPath + configFileName), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDataFromPropertiesFile(String key, String configFilePath) {
		String value = "";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(configFilePath));
			value = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
