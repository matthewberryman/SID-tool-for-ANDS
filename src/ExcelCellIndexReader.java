/**
 * ExcelCellIndexReader.java
 * 
 * @author Jun MA <jma@uow.edu.au>
 * @version 1.0 18/05/2012
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
 *   This class is used to extract column and row indexes based on given cell 
 * name in a SpreadSheet file.
 * 
 * Usage and Distribution:
 *   - No guarantee for other purpose.
 *   - Modification and distribution is not prohibited.
 * 
 * Feedback: 
 * 
 *   Feedback please sent to the author (jma@uow.edu.au).
 */
public class ExcelCellIndexReader {

	private int rowNum;
	private int colNum;
	
	/**
	 * creates <code>ExcelCellIndexReader</code> object using given cell name 
	 * 
	 * @param cell given cell name
	 */
	public ExcelCellIndexReader(String cell) {
		String col = "";
		String row = "";

		// separate column-row parts from cell reference
		int c = 0;
		while (c < cell.length()) {
			if ((int) cell.charAt(c) >= (int) ('A')) {
				col += cell.charAt(c);
				row = cell.substring(c + 1, cell.length());
			}
			c++;
		}

		// get cell's row-column numeric reference
		colNum = 0;
		for (int i = 0; i < col.length(); i++) {
			colNum *= 26;
			colNum += (int) (cell.charAt(i)) - (int) ('A');
		}

		rowNum = Integer.parseInt(row) - 1;
	}

	/**
	 * gets row index of a given cell
	 * 
	 * @return row index
	 */
	public int getRowNum() {
		return this.rowNum;
	}

	/**
	 * gets column index of a given cell
	 * 
	 * @return column index
	 */
	public int getColNum() {
		return this.colNum;
	}

}
