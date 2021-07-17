package com.automator.handlers.dataHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.automator.handlers.exceptionHandler.FrameworkException;


public class ReadFromExcelFile {

	// method to extract data from excel
	public String getExcelData(String excelFilePath, String worksheetName, String testCaseName, String columnName) {
		XSSFWorkbook workbook = null;
		try {
			// ArrayList<String> a = new ArrayList<String>();
			Map<String, String> excelDataMap = new HashMap<String, String>();
			FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
			workbook = new XSSFWorkbook(fileInputStream);
			for (int workSheetIndex = 0; workSheetIndex < workbook.getNumberOfSheets(); workSheetIndex++) {
				if (workbook.getSheetName(workSheetIndex).equalsIgnoreCase(worksheetName)) {
					XSSFSheet workSheet = workbook.getSheetAt(workSheetIndex);
					// Identify TestCases column by scanning the entire 1st row
					Iterator<Row> rows = workSheet.iterator();
					Row firstrow = rows.next();
					Iterator<Cell> cellIterator1 = firstrow.cellIterator();
					Iterator<Cell> cellIterator2 = firstrow.cellIterator();
					int k = 0;
					int testCasesColumnIndex = 0;
					while (cellIterator1.hasNext()) {
						Cell value = cellIterator1.next();
						if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
							testCasesColumnIndex = k;
						}
						k++;
					}
					String[] columnNamesArray = new String[k];
					for (int i = 0; i < k; i++) {
						columnNamesArray[i] = cellIterator2.next().getStringCellValue();
					}
					// once TestCases column is identified, scan the entire TestCases column to
					// identify the intended TestCase row
					while (rows.hasNext()) {
						Row row = rows.next();
						if (row.getCell(testCasesColumnIndex).getStringCellValue().equalsIgnoreCase(testCaseName)) {
							// after the intended TestCase row is identified, all of the data of that row is
							// extracted
							Iterator<Cell> cellIterator = row.cellIterator();
							for (int i = 0; i < k; i++) {
								Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
								if (cell == null) {
									excelDataMap.put(columnNamesArray[i], "");
								} else if (cell.getCellType() == CellType.STRING) {
									excelDataMap.put(columnNamesArray[i], cell.getStringCellValue());
								} else if (cell.getCellType() == CellType.NUMERIC) {
									excelDataMap.put(columnNamesArray[i],
											NumberToTextConverter.toText(cell.getNumericCellValue()));
								}
							}
						}
					}
				}
			}
			// return excelDataMap;
			for (Map.Entry<String, String> entry : excelDataMap.entrySet()) {
				if (entry.getKey().equals(columnName)) {
					return entry.getValue();
				}
			}
		} catch (Exception e) {
			throw new FrameworkException("Not able to read data from the Excel file");
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				throw new FrameworkException("Not able to close the Excel workbook");
			}
		}
		return "";
	}

}
