package com.automator.handlers.reportHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.automator.handlers.exceptionHandler.FrameworkException;

/**
 * This class handles the creation of Excel Report
 * 
 * @author Sumon Dey, July 22, 2021
 *
 */
public class ExcelReportHandler {

	public void createTestSuiteExcelReport(String testSuiteExcelReportFilePath, String testSuiteName) {
		createTestSuiteExcelFile(testSuiteExcelReportFilePath, testSuiteName);
	}

	public void createTestSuiteExcelFile(String excelReportFilePath, String testSuiteName) {
		Workbook workbook = null;
		FileOutputStream fileOutputStream = null;
		try {
			workbook = new HSSFWorkbook();
			workbook = createTestLogSheet(workbook, testSuiteName);
			fileOutputStream = new FileOutputStream(excelReportFilePath);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			throw new FrameworkException("Not able to create TestSuite ExcelReport file", e);
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				throw new FrameworkException("Not able to close FileOutputStream", e);
			}
			try {
				workbook.close();
			} catch (IOException e) {
				throw new FrameworkException("Not able to close workbook", e);
			}
		}
	}

	public Workbook createTestLogSheet(Workbook workbook, String testSuiteName) {
		Sheet testLog_Sheet = workbook.createSheet("Test_Log");
		testLog_Sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
		// create the header row for the TestLog (second sheet)
		Row headerRow_TestLog_Sheet = testLog_Sheet.createRow(0);
		CellStyle headerRow_CellStyle_TestLog_Sheet = workbook.createCellStyle();
		Font headerRow_Font_TestLog_Sheet = workbook.createFont();
		headerRow_Font_TestLog_Sheet.setBold(true);
		headerRow_Font_TestLog_Sheet.setFontHeightInPoints((short) 12);
		headerRow_Font_TestLog_Sheet.setColor(IndexedColors.LIGHT_GREEN.getIndex());
		headerRow_CellStyle_TestLog_Sheet.setFont(headerRow_Font_TestLog_Sheet);
		headerRow_CellStyle_TestLog_Sheet.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		headerRow_CellStyle_TestLog_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// create the header row cells for the TestLog (second sheet)
		String[] headerColumns_TestLog_Sheet = { "Test No.", "Test Name", "Test Description", "Test Execution Status",
				"Test Execution Time" };
		for (int i = 0; i < headerColumns_TestLog_Sheet.length; i++) {
			Cell cell = headerRow_TestLog_Sheet.createCell(i);
			cell.setCellValue(headerColumns_TestLog_Sheet[i]);
			cell.setCellStyle(headerRow_CellStyle_TestLog_Sheet);
		}
		// create the second row for the TestLog sheet
		Row secondRow_TestLog_Sheet = testLog_Sheet.createRow(1);
		CellStyle secondRow_CellStyle_TestLog_Sheet = workbook.createCellStyle();
		Font secondRow_Font_TestLog_Sheet = workbook.createFont();
		secondRow_Font_TestLog_Sheet.setBold(true);
		secondRow_Font_TestLog_Sheet.setFontHeightInPoints((short) 12);
		secondRow_Font_TestLog_Sheet.setColor(IndexedColors.BLACK.getIndex());
		secondRow_CellStyle_TestLog_Sheet.setFont(secondRow_Font_TestLog_Sheet);
		secondRow_CellStyle_TestLog_Sheet.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		secondRow_CellStyle_TestLog_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Cell secondRow_MergedCell_TestLog_Sheet = secondRow_TestLog_Sheet.createCell(0);
		secondRow_MergedCell_TestLog_Sheet.setCellValue("Test Suite: " + testSuiteName);
		secondRow_MergedCell_TestLog_Sheet.setCellStyle(secondRow_CellStyle_TestLog_Sheet);
		// resize all columns in the Test_Log (second sheet) to fit the content size
		// for the first sheet
		for (int i = 0; i <= 4; i++) {
			testLog_Sheet.autoSizeColumn(i);
		}
		return workbook;
	}

	public void addTestSuiteMetaDataToExcelReport(String excelReportFilePath,
			Map<String, ArrayList<String>> testSuiteMetaDataMap, String testSuiteExecutionTime) {
		Workbook workbook = null;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(excelReportFilePath));
			workbook = new HSSFWorkbook(fileInputStream);
			Sheet testLog_Sheet = workbook.getSheet("Test_Log");
			int rowCount = testLog_Sheet.getLastRowNum();
			for (Map.Entry<String, ArrayList<String>> entry : testSuiteMetaDataMap.entrySet()) {
				Row row = testLog_Sheet.createRow(rowCount + 1);
				Cell cell1 = row.createCell(0);
				cell1.setCellValue("");
				Cell cell2 = row.createCell(1);
				cell2.setCellValue(entry.getKey());
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(entry.getValue().get(0));
				Cell cell4 = row.createCell(3);
				cell4.setCellValue(entry.getValue().get(1));
				Cell cell5 = row.createCell(4);
				cell5.setCellValue(entry.getValue().get(2));
				rowCount++;
			}
			for (int i = 0; i < rowCount - 1; i++) {
				Row row = testLog_Sheet.getRow(i + 2);
				Cell cell1 = row.getCell(0);
				cell1.setCellValue(i + 1);
			}
			rowCount = testLog_Sheet.getLastRowNum();
			// test suite execution time row
			Row executionTimeRow = testLog_Sheet.createRow(rowCount + 1);
			testLog_Sheet.addMergedRegion(new CellRangeAddress(rowCount + 1, rowCount + 1, 0, 4));
			Cell executionTimeCell = executionTimeRow.createCell(0);
			executionTimeCell.setCellValue("Test Suite Execution Duration: " + testSuiteExecutionTime);
			CellStyle executionTimeCellStyle = executionTimeCell.getCellStyle();
			executionTimeCellStyle.setAlignment(HorizontalAlignment.CENTER);
			executionTimeCell.setCellStyle(executionTimeCellStyle);
			// test suite execution status row
			Row executionStatusRow = testLog_Sheet.createRow(rowCount + 2);
			Cell executionStatusCell1 = executionStatusRow.createCell(0);
			Cell executionStatusCell2 = executionStatusRow.createCell(1);
			Cell executionStatusCell4 = executionStatusRow.createCell(3);
			Cell executionStatusCell5 = executionStatusRow.createCell(4);
			int passCount = 0;
			int failCount = 0;
			for (int i = 2; i <= rowCount; i++) {
				Row eachRow = testLog_Sheet.getRow(i);
				if (eachRow.getCell(3).getStringCellValue().equals("PASS")) {
					passCount += 1;
				} else if (eachRow.getCell(3).getStringCellValue().equals("FAIL")) {
					failCount += 1;
				}
			}
			executionStatusCell1.setCellValue("No. of Tests Passed:");
			executionStatusCell2.setCellValue(passCount);
			executionStatusCell4.setCellValue("No. of Tests Failed:");
			executionStatusCell5.setCellValue(failCount);
			fileOutputStream = new FileOutputStream(excelReportFilePath);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			throw new FrameworkException(e);
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				throw new FrameworkException("Not able to close FileInputStream", e);
			}
			try {
				workbook.close();
			} catch (IOException e) {
				throw new FrameworkException("Not able to close workbook", e);
			}
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				throw new FrameworkException("Not able to close FileOutputStream", e);
			}
		}
	}

}
