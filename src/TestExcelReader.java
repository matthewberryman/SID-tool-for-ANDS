/**
 * TestExcelReader.java
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
 *   This is a simple class for testing purpose. It can be extended to a general
 * purpose class.
 * 
 * Usage:
 * 
 *   To use this testing class, two specific files need to be set in advance:
 *    - "filelist.txt" contains SID information such as weather station number 
 * or SA1 code. Ref. Readme.txt file.
 *    - "sheet-cells.txt" contains sheet name and cell names. 
 *   Both files are plain text files. Please sample files in folder *sample*.
 *   
 *   Moreover, the MS Excel files to be processed should be put the same folder
 * of the above two files.
 *   
 *   This is a command-line tool. It needs only one command line argument, i.e.
 * the path to the folder where the aforementioned files are put. By default, 
 * this tool search the user's home directory. 
 * 
 *   Examples:
 *   1. files are in current working directory
 *   
 *   	java TestExcelReader ./
 *   
 *   2. files are in a specific directory
 *   
 *     	java TestExcelReader c:/User/ABC/sample
 * 
 * NOTE: Class file name "TestExcelReader" can be changed to any possible name 
 * when packing it.
 * 
 * Distribution:
 *   - No guarantee for other purpose.
 *   - Modification and distribution is not prohibited.
 * 
 * Feedback: 
 * 
 *   Feedback please sent to the author (jma@uow.edu.au).
 */

import java.util.ArrayList;

public class TestExcelReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Add comments to test the git repository
		String filePath = "";
		if (args.length != 0) {
			filePath = args[0];
		} else {
			filePath = System.getProperty("user.dir");
		}
		filePath += System.getProperty("file.separator");

		String file = filePath + "filelist.txt";
		SMARTFileReader fr = new SMARTFileReader(file);
		ArrayList<String> values = new ArrayList<String>();

		String sheetFile = filePath + "sheet-cells.txt";
		SMARTFileReader sfr = new SMARTFileReader(sheetFile);
		int row = 0;
		String COMMENT_MARK = "#";

		for (int i = 0; i < sfr.getLineCount(); i++) {
			if (!sfr.getContentAtLine(i).startsWith(COMMENT_MARK))
				row++;
		}
		;
		String[][] sheetAndCells = new String[row][];

		row = 0;
		for (int i = 0; i < sfr.getLineCount(); i++) {
			if (!sfr.getContentAtLine(i).startsWith(COMMENT_MARK)) {
				String[] fields = sfr.getContentAtLine(i).split(",");
				sheetAndCells[row] = fields;
				row++;
			}
		}

		for (int k = 0; k < sheetAndCells.length; k++) {
			values.clear();

			for (int i = 0; i < fr.getLineCount(); i++) {
				String delimiter = ",";
				String excelFileName = filePath + fr.getContentAtLine(i);
				ExcelReader er = new ExcelReader(excelFileName);

				// String sheetName = "B 02";

				String record = "";

				String cd_code = fr.getContentAtLine(i).substring(0,
						fr.getContentAtLine(i).indexOf(".xls"));
				record += cd_code;

				String sheetName = sheetAndCells[k][0];
				for (int idx = 1; idx < sheetAndCells[k].length; idx++) {
					String cellName = sheetAndCells[k][idx];
					String cell = er.readCell(sheetName, cellName);
					record += (delimiter + '"' + cell + '"');
				}
				System.out.println("i = " + Integer.toString(i));
				values.add(record);
				SMARTFileWriter.WriteCSV(filePath, "abs-region-"
						+ sheetAndCells[k][0], values);
			}
		}

		// ====================================================================
		// Command line processing
		// Options
		// -f <file> assign the EXCEL filename to be processed
		// -s <sheet> assign the Sheet to be processed
		// -c <cell> assign the Cell to be processed
		// -d <directory> assign the output folder
		// -o <file> assign the output filename
		// ====================================================================
		// String file = null;
		// String sheet = null;
		// String cell = null;
		// String outFolder = null;
		// String outFile = null;
		// System.out.println(Integer.toString(args.length));
		// if (args.length == 0) {
		// // TODO: usage description
		// System.out.println("Usage: ");
		// System.exit(0);
		// } else {
		//
		// for (int i = 1; i <= args.length; i++) {
		// if (args[i] == "-f") {
		// file = args[++i];
		// if (args[i] == "-s") {
		// sheet = args[++i];
		// if (args[i] == "-c") {
		// cell = args[++i];
		// } else {
		// System.out.println("No cell to be processed");
		// System.exit(0);
		// }
		// } else {
		// System.out.println("No sheet to be processed");
		// System.exit(0);
		// }
		// } else {
		// System.out.println("No excel file to be procesed");
		// System.exit(0);
		// }
		// if (args[i] == "-d") {
		// outFolder = args[++i];
		// } else {
		// outFolder = System.getProperty("user.dir");
		// }
		// if (args[i] == "-o") {
		// outFile = args[++i];
		// } else {
		// outFile = "untitled.csv";
		// }
		// }
		// ExcelReader er = new ExcelReader(file);
		// System.out.println(er.readCell(sheet, cell));
		//
		// }// end of if -- command line option processing
	}// end of main();

	/*
	 * DisplayUsage() -- Show basic usage of the tool.
	 */
	// private void displayUsage() {
	// System.out.println("Usage: You must specify at least three options as ");
	// System.out.println("TestExcelReader -f <file> -s <sheet> -c <cell>");
	// System.out.println("\t <file>   the MS Excel (.xls) file to be processed");
	// System.out.println("\t <sheet>  the Spreadsheet where the information (data) in");
	// System.out.println("\t <cell>   the Cell reference");
	// }

}
