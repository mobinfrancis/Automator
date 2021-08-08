package com.automator.handlers.reportHandler;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.automator.testRunner.MainTestSuiteRunner;

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

	public void createSummaryExcelReport(String excelReportFilePath) {
		Workbook workbook = null;
		FileOutputStream fileOutputStream = null;
		try {
			workbook = new HSSFWorkbook();
			workbook = createCoverPageSheetForSummaryFile(workbook);
			workbook = createResultSummarySheetForSummaryFile(workbook);
			fileOutputStream = new FileOutputStream(excelReportFilePath);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			throw new FrameworkException("Not able to create Test Suite Excel Report file");
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Workbook createCoverPageSheetForSummaryFile(Workbook workbook) {
		Sheet coverPage_Sheet = workbook.createSheet("Cover_Page");
		coverPage_Sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		// create the header row style for the Cover Page (first sheet)
		Row headerRow_CoverPage_Sheet = coverPage_Sheet.createRow(0);
		CellStyle headerRow_CellStyle_CoverPage_Sheet = workbook.createCellStyle();
		Font headerRow_Font_CoverPage_Sheet = workbook.createFont();
		headerRow_Font_CoverPage_Sheet.setBold(true);
		headerRow_Font_CoverPage_Sheet.setFontHeightInPoints((short) 12);
		headerRow_Font_CoverPage_Sheet.setColor(IndexedColors.BLACK.getIndex());
		headerRow_CellStyle_CoverPage_Sheet.setFont(headerRow_Font_CoverPage_Sheet);
		headerRow_CellStyle_CoverPage_Sheet.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		headerRow_CellStyle_CoverPage_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// create the header row cell for the Cover Page (first sheet)
		Cell headerRow_MergedCell_CoverPage_Sheet = headerRow_CoverPage_Sheet.createCell(0);
		headerRow_MergedCell_CoverPage_Sheet.setCellValue("AUTOMATOR - AUTOMATION EXECUTION RESULTS SUMMARY");
		headerRow_MergedCell_CoverPage_Sheet.setCellStyle(headerRow_CellStyle_CoverPage_Sheet);
		// create the cell style for the other rows
		CellStyle otherRows_CellStyle_CoverPage_Sheet = workbook.createCellStyle();
		Font secondRow_Font_CoverPage_Sheet = workbook.createFont();
		secondRow_Font_CoverPage_Sheet.setBold(true);
		secondRow_Font_CoverPage_Sheet.setFontHeightInPoints((short) 12);
		secondRow_Font_CoverPage_Sheet.setColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
		otherRows_CellStyle_CoverPage_Sheet.setFont(secondRow_Font_CoverPage_Sheet);
		otherRows_CellStyle_CoverPage_Sheet.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		otherRows_CellStyle_CoverPage_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// create the second row cells for the Cover Page (first sheet)
		Row secondRow_CoverPage_Sheet = coverPage_Sheet.createRow(1);
		Cell secondRow_FirstCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(0);
		secondRow_FirstCell_CoverPage_Sheet.setCellValue("Date & Time");
		secondRow_FirstCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell secondRow_SecondCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(1);
		secondRow_SecondCell_CoverPage_Sheet
				.setCellValue(": " + new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss aa").format(new Date()));
		secondRow_SecondCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);

		Cell secondRow_ThirdCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(2);
		secondRow_ThirdCell_CoverPage_Sheet.setCellValue("");
		secondRow_ThirdCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell secondRow_FourthCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(3);
		secondRow_FourthCell_CoverPage_Sheet.setCellValue("OnError");
		secondRow_FourthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell secondRow_FifthCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(4);
		secondRow_FifthCell_CoverPage_Sheet.setCellValue(": NEXT_ITERATION");
		secondRow_FifthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		// create the third row cells for the Cover Page (first sheet)
		Row thirdRow_CoverPage_Sheet = coverPage_Sheet.createRow(2);
		Cell thirdRow_FirstCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(0);
		thirdRow_FirstCell_CoverPage_Sheet.setCellValue("Run Configuration");
		thirdRow_FirstCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_SecondCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(1);
		thirdRow_SecondCell_CoverPage_Sheet.setCellValue(": N/A");
		thirdRow_SecondCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_ThirdCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(2);
		thirdRow_ThirdCell_CoverPage_Sheet.setCellValue("");
		thirdRow_ThirdCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_FourthCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(3);
		thirdRow_FourthCell_CoverPage_Sheet.setCellValue("No. of threads");
		thirdRow_FourthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_FifthCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(4);
		thirdRow_FifthCell_CoverPage_Sheet.setCellValue(": 1");
		thirdRow_FifthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		// resize all columns in the Cover_Page (first sheet) to fit the content size
		// for the first sheet
		for (int i = 0; i < 5; i++) {
			coverPage_Sheet.autoSizeColumn(i);
		}
		return workbook;
	}

	public Workbook createResultSummarySheetForSummaryFile(Workbook workbook) {
		Sheet resultSummary_Sheet = workbook.createSheet("Result_Summary");
		// create the header row for the ResultSummary (second sheet)
		Row headerRow_ResultSummary_Sheet = resultSummary_Sheet.createRow(0);
		CellStyle headerRow_CellStyle_ResultSummary_Sheet = workbook.createCellStyle();
		Font headerRow_Font_ResultSummary_Sheet = workbook.createFont();
		headerRow_Font_ResultSummary_Sheet.setBold(true);
		headerRow_Font_ResultSummary_Sheet.setFontHeightInPoints((short) 12);
		headerRow_Font_ResultSummary_Sheet.setColor(IndexedColors.BLACK.getIndex());
		headerRow_CellStyle_ResultSummary_Sheet.setFont(headerRow_Font_ResultSummary_Sheet);
		headerRow_CellStyle_ResultSummary_Sheet.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		headerRow_CellStyle_ResultSummary_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// create the header row cells for the ResultSummary (second sheet)
		String[] headerColumns_ResultSummary_Sheet = { "Test_Scenario", "Test_Case", "Test_Instance",
				"Test_Description", "Additional_Details", "Execution_Time", "Test_Status" };
		for (int i = 0; i < headerColumns_ResultSummary_Sheet.length; i++) {
			Cell cell = headerRow_ResultSummary_Sheet.createCell(i);
			cell.setCellValue(headerColumns_ResultSummary_Sheet[i]);
			cell.setCellStyle(headerRow_CellStyle_ResultSummary_Sheet);
		}
		// insert data for each TestSuite file
		FileFilter filter = new ExcelFileFilter();
		File directory = new File(FrameworkReportHandler.parentReportsFolderPathForSummaryReport + "Excel Results");
		File[] files = directory.listFiles(filter);
		int rowCount_ResultSummary_Sheet = 0;
		for (File file : files) {
			String excelFileName = file.getName();
			String test_Scenario = excelFileName.split("_TC_")[0];
			String test_Case = "TC_" + excelFileName.split("_TC_")[1].split("_Instance")[0];
			String test_Instance = StringUtils.substringBetween(excelFileName, test_Case + "_", ".xls");
			String test_Description = "", additional_Details = "", execution_Time = "", test_Status = "";
			FileInputStream fileInputStream = null;
			Workbook eachWorkbook = null;
			try {
				fileInputStream = new FileInputStream(file);
				eachWorkbook = new HSSFWorkbook(fileInputStream);
				Sheet eachWorkbookTestLogSheet = eachWorkbook.getSheet("Test_Log");
				Row descriptionRow = eachWorkbookTestLogSheet.getRow(2);
				test_Description = descriptionRow.getCell(0).getStringCellValue();
				Sheet eachWorkbookCoverPageSheet = eachWorkbook.getSheet("Cover_Page");
				Row fourthRow_eachWorkbookCoverPageSheet = eachWorkbookCoverPageSheet.getRow(3);
				additional_Details = fourthRow_eachWorkbookCoverPageSheet.getCell(1).getStringCellValue().replace(": ",
						"");
				int rowCount = eachWorkbookTestLogSheet.getLastRowNum();
				execution_Time = eachWorkbookTestLogSheet.getRow(rowCount - 1).getCell(0).getStringCellValue()
						.replace("Execution Duration: ", "");
				int failCount = Integer.parseInt(
						eachWorkbookTestLogSheet.getRow(rowCount).getCell(4).getStringCellValue().replace(": ", ""));
				if (failCount > 0) {
					test_Status = "Failed";
				} else {
					test_Status = "Passed";
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					eachWorkbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// create the other rows for the ResultSummary (second sheet)
			rowCount_ResultSummary_Sheet = resultSummary_Sheet.getLastRowNum();
			Row otherRow_ResultSummary_Sheet = resultSummary_Sheet.createRow(rowCount_ResultSummary_Sheet + 1);
			otherRow_ResultSummary_Sheet.createCell(0).setCellValue(test_Scenario);
			otherRow_ResultSummary_Sheet.createCell(1).setCellValue(test_Case);
			otherRow_ResultSummary_Sheet.createCell(2).setCellValue(test_Instance);
			otherRow_ResultSummary_Sheet.createCell(3).setCellValue(test_Description);
			otherRow_ResultSummary_Sheet.createCell(4).setCellValue(additional_Details);
			otherRow_ResultSummary_Sheet.createCell(5).setCellValue(execution_Time);
			otherRow_ResultSummary_Sheet.createCell(6).setCellValue(test_Status);
		}
		rowCount_ResultSummary_Sheet = resultSummary_Sheet.getLastRowNum();
		Row totalDurationRow_ResultSummary_Sheet = resultSummary_Sheet.createRow(rowCount_ResultSummary_Sheet + 1);
		resultSummary_Sheet.addMergedRegion(
				new CellRangeAddress(rowCount_ResultSummary_Sheet + 1, rowCount_ResultSummary_Sheet + 1, 0, 6));
		Cell totalDurationCell = totalDurationRow_ResultSummary_Sheet.createCell(0);
		totalDurationCell.setCellValue("Total Duration: " + MainTestSuiteRunner.totalExecutionTime);
		CellStyle totalDurationCellStyle = totalDurationCell.getCellStyle();
		totalDurationCellStyle.setAlignment(HorizontalAlignment.CENTER);

		Row totalTestExecutionStatusRow_ResultSummary_Sheet = resultSummary_Sheet
				.createRow(rowCount_ResultSummary_Sheet + 2);
		Cell totalTestExecutionStatusCell1_ResultSummary_Sheet = totalTestExecutionStatusRow_ResultSummary_Sheet
				.createCell(0);
		Cell totalTestExecutionStatusCell2_ResultSummary_Sheet = totalTestExecutionStatusRow_ResultSummary_Sheet
				.createCell(1);
		Cell totalTestExecutionStatusCell6_ResultSummary_Sheet = totalTestExecutionStatusRow_ResultSummary_Sheet
				.createCell(5);
		Cell totalTestExecutionStatusCell7_ResultSummary_Sheet = totalTestExecutionStatusRow_ResultSummary_Sheet
				.createCell(6);
		totalTestExecutionStatusCell1_ResultSummary_Sheet.setCellValue("Tests passed");
		totalTestExecutionStatusCell6_ResultSummary_Sheet.setCellValue("Tests failed");
		int passCount = 0;
		int failCount = 0;
		for (int i = 1; i <= rowCount_ResultSummary_Sheet; i++) {
			Row eachRow = resultSummary_Sheet.getRow(i);
			if (eachRow.getCell(6).getStringCellValue().equals("Passed")) {
				passCount += 1;
			} else if (eachRow.getCell(6).getStringCellValue().equals("Failed")) {
				failCount += 1;
			}
		}
		totalTestExecutionStatusCell2_ResultSummary_Sheet.setCellValue(": " + passCount);
		totalTestExecutionStatusCell7_ResultSummary_Sheet.setCellValue(": " + failCount);
		return workbook;
	}

	public void createTestSuiteExcelFile(String excelReportFilePath, String testSuiteName) {
		Workbook workbook = null;
		FileOutputStream fileOutputStream = null;
		try {
			workbook = new HSSFWorkbook();
			workbook = createCoverPageSheet(workbook, testSuiteName);
			workbook = createTestLogSheet(workbook, testSuiteName);
			fileOutputStream = new FileOutputStream(excelReportFilePath);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			throw new FrameworkException("Not able to create Test Suite Excel Report file");
		} finally {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Workbook createCoverPageSheet(Workbook workbook, String testSuiteName) {
		Sheet coverPage_Sheet = workbook.createSheet("Cover_Page");
		coverPage_Sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		// create the header row style for the CoverPage (first sheet)
		Row headerRow_CoverPage_Sheet = coverPage_Sheet.createRow(0);
		CellStyle headerRow_CellStyle_CoverPage_Sheet = workbook.createCellStyle();
		Font headerRow_Font_CoverPage_Sheet = workbook.createFont();
		headerRow_Font_CoverPage_Sheet.setBold(true);
		headerRow_Font_CoverPage_Sheet.setFontHeightInPoints((short) 12);
		headerRow_Font_CoverPage_Sheet.setColor(IndexedColors.LIGHT_GREEN.getIndex());
		headerRow_CellStyle_CoverPage_Sheet.setFont(headerRow_Font_CoverPage_Sheet);
		headerRow_CellStyle_CoverPage_Sheet.setFillForegroundColor(IndexedColors.BLACK.getIndex());
		headerRow_CellStyle_CoverPage_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// create the header row cell for the CoverPage (first sheet)
		Cell headerRow_MergedCell_CoverPage_Sheet = headerRow_CoverPage_Sheet.createCell(0);
		headerRow_MergedCell_CoverPage_Sheet
				.setCellValue("AUTOMATOR - " + testSuiteName + " - AUTOMATION EXECUTION RESULTS");
		headerRow_MergedCell_CoverPage_Sheet.setCellStyle(headerRow_CellStyle_CoverPage_Sheet);
		// create the cell style for the other rows
		CellStyle otherRows_CellStyle_CoverPage_Sheet = workbook.createCellStyle();
		Font secondRow_Font_CoverPage_Sheet = workbook.createFont();
		secondRow_Font_CoverPage_Sheet.setBold(true);
		secondRow_Font_CoverPage_Sheet.setFontHeightInPoints((short) 12);
		secondRow_Font_CoverPage_Sheet.setColor(IndexedColors.BLACK.getIndex());
		otherRows_CellStyle_CoverPage_Sheet.setFont(secondRow_Font_CoverPage_Sheet);
		otherRows_CellStyle_CoverPage_Sheet.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		otherRows_CellStyle_CoverPage_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// create the second row cells for the CoverPage (first sheet)
		Row secondRow_CoverPage_Sheet = coverPage_Sheet.createRow(1);
		Cell secondRow_FirstCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(0);
		secondRow_FirstCell_CoverPage_Sheet.setCellValue("Date & Time");
		secondRow_FirstCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell secondRow_SecondCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(1);
		secondRow_SecondCell_CoverPage_Sheet
				.setCellValue(": " + new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss aa").format(new Date()));
		secondRow_SecondCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell secondRow_FourthCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(3);
		secondRow_FourthCell_CoverPage_Sheet.setCellValue("Iteration Mode");
		secondRow_FourthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell secondRow_FifthCell_CoverPage_Sheet = secondRow_CoverPage_Sheet.createCell(4);
		secondRow_FifthCell_CoverPage_Sheet.setCellValue(": RUN_ONE_ITERATION_ONLY");
		secondRow_FifthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		// create the third row cells for the CoverPage (first sheet)
		Row thirdRow_CoverPage_Sheet = coverPage_Sheet.createRow(2);
		Cell thirdRow_FirstCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(0);
		thirdRow_FirstCell_CoverPage_Sheet.setCellValue("Start Iteration");
		thirdRow_FirstCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_SecondCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(1);
		thirdRow_SecondCell_CoverPage_Sheet.setCellValue(": 1");
		thirdRow_SecondCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_FourthCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(3);
		thirdRow_FourthCell_CoverPage_Sheet.setCellValue("End Iteration");
		thirdRow_FourthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell thirdRow_FifthCell_CoverPage_Sheet = thirdRow_CoverPage_Sheet.createCell(4);
		thirdRow_FifthCell_CoverPage_Sheet.setCellValue(": 1");
		thirdRow_FifthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		// create the fourth row cells for the CoverPage (first sheet)
		Row fourthRow_CoverPage_Sheet = coverPage_Sheet.createRow(3);
		Cell fourthRow_FirstCell_CoverPage_Sheet = fourthRow_CoverPage_Sheet.createCell(0);
		fourthRow_FirstCell_CoverPage_Sheet.setCellValue("Browser / Platform");
		fourthRow_FirstCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell fourthRow_SecondCell_CoverPage_Sheet = fourthRow_CoverPage_Sheet.createCell(1);
		fourthRow_SecondCell_CoverPage_Sheet.setCellValue(": CHROME on WINDOWS");
		fourthRow_SecondCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell fourthRow_FourthCell_CoverPage_Sheet = fourthRow_CoverPage_Sheet.createCell(3);
		fourthRow_FourthCell_CoverPage_Sheet.setCellValue("Execution on");
		fourthRow_FourthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		Cell fourthRow_FifthCell_CoverPage_Sheet = fourthRow_CoverPage_Sheet.createCell(4);
		fourthRow_FifthCell_CoverPage_Sheet.setCellValue(": Local Machine");
		fourthRow_FifthCell_CoverPage_Sheet.setCellStyle(otherRows_CellStyle_CoverPage_Sheet);
		// resize all columns in the Cover_Page (first sheet) to fit the content size
		// for the first sheet
		for (int i = 0; i < 5; i++) {
			coverPage_Sheet.autoSizeColumn(i);
		}
		return workbook;
	}

	public Workbook createTestLogSheet(Workbook workbook, String testSuiteName) {
		Sheet testLog_Sheet = workbook.createSheet("Test_Log");
		testLog_Sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
		testLog_Sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));
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
		String[] headerColumns_TestLog_Sheet = { "Step_No", "Step_Name", "Description", "Status", "Step_Time" };
		for (int i = 0; i < headerColumns_TestLog_Sheet.length; i++) {
			Cell cell = headerRow_TestLog_Sheet.createCell(i);
			cell.setCellValue(headerColumns_TestLog_Sheet[i]);
			cell.setCellStyle(headerRow_CellStyle_TestLog_Sheet);
		}
		// create the second row for the TestLog (second sheet)
		Row secondRow_TestLog_Sheet = testLog_Sheet.createRow(1);
		CellStyle secondRow_CellStyle_TestLog_Sheet = workbook.createCellStyle();
		Font secondRow_Font_TestLog_Sheet = workbook.createFont();
		secondRow_Font_TestLog_Sheet.setBold(true);
		secondRow_Font_TestLog_Sheet.setFontHeightInPoints((short) 12);
		secondRow_Font_TestLog_Sheet.setColor(IndexedColors.LIGHT_GREEN.getIndex());
		secondRow_CellStyle_TestLog_Sheet.setFont(secondRow_Font_TestLog_Sheet);
		secondRow_CellStyle_TestLog_Sheet.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		secondRow_CellStyle_TestLog_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Cell secondRow_MergedCell_TestLog_Sheet = secondRow_TestLog_Sheet.createCell(0);
		secondRow_MergedCell_TestLog_Sheet.setCellValue("Iteration: 1");
		secondRow_MergedCell_TestLog_Sheet.setCellStyle(secondRow_CellStyle_TestLog_Sheet);
		// create the third row for the TestLog (second sheet)
		Row thirdRow_TestLog_Sheet = testLog_Sheet.createRow(2);
		CellStyle thirdRow_CellStyle_TestLog_Sheet = workbook.createCellStyle();
		Font thirdRow_Font_TestLog_Sheet = workbook.createFont();
		thirdRow_Font_TestLog_Sheet.setBold(true);
		thirdRow_Font_TestLog_Sheet.setFontHeightInPoints((short) 12);
		thirdRow_Font_TestLog_Sheet.setColor(IndexedColors.BLACK.getIndex());
		thirdRow_CellStyle_TestLog_Sheet.setFont(thirdRow_Font_TestLog_Sheet);
		thirdRow_CellStyle_TestLog_Sheet.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		thirdRow_CellStyle_TestLog_Sheet.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Cell thirdRow_MergedCell_TestLog_Sheet = thirdRow_TestLog_Sheet.createCell(0);
		thirdRow_MergedCell_TestLog_Sheet.setCellValue(testSuiteName.toLowerCase());
		thirdRow_MergedCell_TestLog_Sheet.setCellStyle(thirdRow_CellStyle_TestLog_Sheet);
		// resize all columns in the Test_Log (second sheet) to fit the content size
		// for the first sheet
		for (int i = 0; i < 5; i++) {
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
			for (int i = 0; i < rowCount - 2; i++) {
				Row row = testLog_Sheet.getRow(i + 3);
				Cell cell1 = row.getCell(0);
				cell1.setCellValue(i + 1);
			}
			rowCount = testLog_Sheet.getLastRowNum();
			// test suite execution time row
			Row executionTimeRow = testLog_Sheet.createRow(rowCount + 1);
			testLog_Sheet.addMergedRegion(new CellRangeAddress(rowCount + 1, rowCount + 1, 0, 4));
			Cell executionTimeCell = executionTimeRow.createCell(0);
			executionTimeCell.setCellValue("Execution Duration: " + testSuiteExecutionTime);
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
			for (int i = 3; i <= rowCount; i++) {
				Row eachRow = testLog_Sheet.getRow(i);
				if (eachRow.getCell(3).getStringCellValue().equals("PASS")) {
					passCount += 1;
				} else if (eachRow.getCell(3).getStringCellValue().equals("FAIL")) {
					failCount += 1;
				}
			}
			executionStatusCell1.setCellValue("Steps passed");
			executionStatusCell2.setCellValue(": " + passCount);
			executionStatusCell4.setCellValue("Steps failed");
			executionStatusCell5.setCellValue(": " + failCount);
			fileOutputStream = new FileOutputStream(excelReportFilePath);
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
