package com.automator.handlers.dataHandler;

public class ExcelFileHandler {

	private String excelFilePath;
	private String worksheetName;
	private String testCaseName;

	public void loadExcelForTheTest(String excelFilePath, String worksheetName, String testCaseName) {
		this.excelFilePath = excelFilePath;
		this.worksheetName = worksheetName;
		this.testCaseName = testCaseName;
	}

	public String getData(String columnName) {
		ReadFromExcelFile readFromExcelFile = new ReadFromExcelFile();
		return readFromExcelFile.getExcelData(this.excelFilePath, this.worksheetName, this.testCaseName, columnName);
	}

}
