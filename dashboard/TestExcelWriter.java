package dashboard;
//
import java.io.FileNotFoundException;

public class TestExcelWriter {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String filePath = "";
		if (args.length > 0) {
			filePath = args[0];
		} else {
			filePath = System.getProperty("user.dir");
		}
		filePath += System.getProperty("file.separator");
		
		filePath = "C:\\junm\\Downloads\\tmp\\";
		// cell-values.csv
		// first non-comment line is the header
		// first column in non-comment line is the filename
		// other non-comment lines are the data
		String valueFile = filePath + "cell-values.csv";
		
		SMARTFileReader sfr = new SMARTFileReader(valueFile);
		
		int row = 0;
		String COMMENT_MARK = "#";
		
		for (int i = 0; i < sfr.getLineCount(); i++) {
			if (!sfr.getContentAtLine(i).startsWith(COMMENT_MARK))
				row++;
		};
		String[][] sheetAndCells = new String[row][];
		
		row = 0;
		for (int i = 0; i < sfr.getLineCount(); i++) {
			if (!sfr.getContentAtLine(i).startsWith(COMMENT_MARK)) {
				String[] fields = sfr.getContentAtLine(i).split(",");
				sheetAndCells[row] = fields;
				row++;
			}
		}

		for (int r = 1; r < sheetAndCells.length; r++) {
			ExcelWriter ew = new ExcelWriter();
			ew.write(filePath, sheetAndCells, r);
			System.out.println("Create " + Integer.toString(r) + " files.");
		}
	}

}
