

/**
 * Copyright (a) SMART Infrastructure Facility, University of Wollongong
 * 
 * Background:
 * 
 *   This class is designed for the SMART Infrastructure Dashboard (SID) project 
 * funded by ANDS (Australian National Data Services). It implements only 
 * required functions for this project.
 * 
 *   This class is used to save processed information to an external CSV file.
 * 
 * Usage and Distribution:
 *   - No guarantee for other purpose.
 *   - Modification and distribution is not prohibited.
 * 
 * Feedback: 
 * 
 *   Feedback please sent to the author (jma@uow.edu.au).
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SMARTFileWriter {
	/**
	 * writes information to a given CSV (.csv) file
	 * 
	 * @param path the directory of the CSV file
	 * @param name the file name of the CSV file
	 * @param values the information to be written
	 */
	public static void WriteCSV(String path, String name, 
			ArrayList<String> values) {
		String outFile = path + name + ".csv";
		try {
			FileWriter fw = new FileWriter(outFile);
			BufferedWriter out = new BufferedWriter(fw);
			for (int i = 0; i < values.size(); i++) {
				out.write(values.get(i) + "\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
