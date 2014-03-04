/**
 * SMARTFileReader.java
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
 *   This class is used to create a file reader to read external plain text file 
 * line by line. The plain text file contains information related to the SID 
 * project. The possible information includes:
 * 	 - 6 digital station number of Australian Bureau of Meteorology Weather 
 * station
 *   - 7 digital SA1 code of Australian Statistical Geography Standard (ASGS)
 *   - 6 digital CCD code of Australian Standard Geographical Classification 
 *  (ASGC)
 * 
 * Explanatory Notes:
 *   1. SA1 for "Statistical Area Level 1"
 *   2. CCD for "Census Collection District" 
 * 
 * Usage and Distribution:
 *   - No guarantee for other purpose.
 *   - Modification and distribution is not prohibited.
 * 
 * Feedback: 
 * 
 *   Feedback please sent to the author (jma@uow.edu.au).
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class <code>SMARTFileReader</code> reads a text file line by line.
 * 
 * @author Jun Ma <jma@uow.edu.au>
 * @version 1.0 18/05/2012
 */
public class SMARTFileReader {
	private int lineCount;
	private ArrayList<String> lines;
	
	/**
	 * reads a file line by line.
	 *  
	 * @param file An external file contains SID project related information.
	 */

	public SMARTFileReader(String file) {
		lines = new ArrayList<String>();
		lineCount = 0;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new DataInputStream(
									new FileInputStream(file))));
			String f;
			while ((f = br.readLine()) != null) {
				lines.add(f);
			}
			lineCount = lines.size();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * gets the count of lines in the external file
	 * 
	 * @return count of lines
	 */
	public int getLineCount() {
		return lineCount;
	}

	/**
	 * gets the content of a specific line by line number
	 * 
	 * Line number starts from 0.
	 *  
	 * @param lineIndex given line number
	 * @return content of line <code>lineIndex</code>
	 */
	public String getContentAtLine(int lineIndex) {
		if (!lines.isEmpty() && lineIndex < lines.size()) {
			return lines.get(lineIndex);
		} else {
			return "";
		}
	}
}
