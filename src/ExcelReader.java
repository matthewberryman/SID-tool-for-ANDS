/**
 * ExcelReader.java 
 * 
 * @author Jun MA <jma@uow.edu.au>
 * @version 1.0 17/05/2012
 */


/**
 * Copyright (a) SMART Infrastructure Facility, University of Wollongong
 * 
 * Background:
 * 
 *   This class is designed for the SMART Infrastructure Dashboard (SID) project 
 * funded by ANDS (Australian National Data Services). It implements only 
 * required functions for this project.
 * 
 *   This class is used to extract ABS census data from basic community profile 
 * file in MS Excel 2003/2007 files (.xls and .xlsx).
 * 
 * Usage and Distribution:
 *   - No guarantee for other purpose.
 *   - Modification and distribution is not prohibited.
 * 
 * Feedback: 
 * 
 *   Feedback please sent to the author (jma@uow.edu.au).
 */

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * Class <code>ExcelReader</code> reads content in an MS Excel file based on 
 * given spreadsheet name and cell names.
 * 
 * Although this class is particularly designed for SID project, it can be 
 * further extended to fit other purpose.  
 * 
 */

public class ExcelReader {
	
	private String file;
	
	/**
	 * creates an ExcelReader object using given SpreadSheet file (.xls/.xlsx)
	 * 
	 * @param f given SpreadSheet file
	 */
	public ExcelReader(String f) {
		file = f;
	}
	
	/**
	 * gets cell content of given sheet name and cell index
	 * 
	 * @param sheet given sheet name
	 * @param cell given cell index
	 * @return cell content
	 */
	public String readCell(String sheet, String cell) {
		return read(file, sheet, cell);
	}
	
	/**
	 * gets cell content of given sheet name, column index and row index
	 * 
	 * @param sheet given sheet name
	 * @param col given column index
	 * @param row given row index
	 * @return cell content
	 */
	public String readCell(String sheet, int col, int row) {
		return read(file, sheet, col, row);
	}

	/**
	 * gets the sheet of given sheet name from a SpreadSheet file
	 * 
	 * @param sheet given sheet name
	 * @return the named sheet
	 */
	public HSSFSheet readSheet(String sheet) {
		try {
			POIFSFileSystem fs = 
					new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				if ((wb.getSheetName(i)).equalsIgnoreCase(sheet)) {
					return wb.getSheetAt(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * reads cell content of given sheet and cell from given SpreadSheet file	
	 */
	private String read(String file, String sheet, String cell) {
		String col = "";
		String row = "";

		// get cell's column index and row index based on cell name
		// ////////////////////////////////////////////////////////////////////
		int c = 0;
		while (c < cell.length()) {
			if ((int) cell.charAt(c) >= (int) ('A')) {
				col += cell.charAt(c);
				row = cell.substring(c + 1, cell.length());
			}
			c++;
		}

		int colNum = 0;
		for (int i = 0; i < col.length(); i++) {
			colNum *= 26;
			colNum += (int) (cell.charAt(i)) - (int) ('A');
		}

		int rowNum = Integer.parseInt(row) - 1;
		// ////////////////////////////////////////////////////////////////////
		
		return read(file, sheet, colNum, rowNum);
	}

	/*
	 * reads cell content based on sheet name, column and row indexes in given
	 * SpreadSheet file
	 */
	private String read(String file, String sheetName, int colNumber,
			int rowNumber) {
		
		String cellValue = "";
		if (file.isEmpty()) {
			System.out.println("Error: file name cannot be empty!");
			return null;
		}

		if (sheetName.isEmpty()) {
			System.out.println("Error: spreadsheet name cannot be empty!");
			return null;
		}

		if (colNumber < 0) {
			System.out.println("Error: column name should not be empty!");
		}

		if (rowNumber < 0) {
			System.out.println("Error: row number should be a positive number");
		}

		try {
			// Create a POI File System object; this is the main class for the
			// POIFS file system and it manages the entire life cycle of the
			// file system
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					file));
			// Create a workbook for this file
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			// Iterate over all of the sheets in the workbook
			HSSFSheet sheet = null;
			// System.out.print(fileName + ":\t");
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				if ((wb.getSheetName(i)).equalsIgnoreCase(sheetName)) {
					sheet = wb.getSheet(wb.getSheetName(i));
					// System.out.print("'" + sheet.getSheetName() + "'\t");
				}
			}

			if (sheet != null) {

				if (rowNumber <= sheet.getLastRowNum()) {
					HSSFRow row = sheet.getRow(rowNumber);
					// System.out.print(row.toString());
					if (row != null) {
						// extract cell value
						if (colNumber <= row.getLastCellNum()) {
							HSSFCell cell = row.getCell(colNumber);
							if (cell != null) {
								int cellType = cell.getCellType();
								if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
									double value = cell.getNumericCellValue();
									cellValue = Double.toString(value);
								} else if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
									cellValue = cell.getCellFormula();
								} else {
									HSSFRichTextString string = cell
											.getRichStringCellValue();
									cellValue = string.getString();
								}
							}
						}
					} else {
						System.out.println("Error: Not a row!");
					}
					//
				} else {
					System.out.println("Error: Row number is greater the last existing row number.");
				}
			} else {
				System.out.println("Error: Not a sheet!");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}
}
