

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelWriter {

	String file = "";

	public ExcelWriter(String xlsFileName) {
		// TODO Auto-generated constructor stub
		this.file = xlsFileName;
	}

	public ExcelWriter() {
	}

	public void write(String path, String[][] sheetAndCells, int r)
			throws FileNotFoundException {
		// TODO Auto-generated method stub
		String xlsFileName = path + sheetAndCells[r][0] + ".xls";
		File xlsFile = new File(xlsFileName);
		HSSFWorkbook wb = null;
		Sheet sheet;
		String sheetName = sheetAndCells[0][0];  
		if (xlsFile.exists()) {
			
			try {
				POIFSFileSystem fs = 
						new POIFSFileSystem(new FileInputStream(xlsFile));
				wb = new HSSFWorkbook(fs);
				for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					if ((wb.getSheetName(i)).equalsIgnoreCase(sheetName)) {
						sheetName += "-" + getCurrentTimeStamp();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			wb = new HSSFWorkbook();
		}
		sheet = wb.createSheet(sheetName);

		// create cells
		int maxColNum = 0;
		int maxRowNum = 0;
		for (int k = 1; k < sheetAndCells[0].length; k++) {
			String cell = sheetAndCells[0][k];
			ExcelCellIndexReader cr = new ExcelCellIndexReader(cell);
			int rowNum = cr.getRowNum();
			int colNum = cr.getColNum();

			// record max column and row numbers
			if (colNum >= maxColNum)
				maxColNum = colNum;
			if (rowNum >= maxRowNum)
				maxRowNum = rowNum;
		}

		// create cells
		for (int i = 0; i <= maxRowNum; i++) {
			sheet.createRow(i);
			for (int j = 0; j <= maxColNum; j++) {
				sheet.getRow(i).createCell(j);
			}
		}

		// write cells
		for (int col = 1; col < sheetAndCells[r].length; col++) {
			ExcelCellIndexReader cr = new ExcelCellIndexReader(
					sheetAndCells[0][col]);
			sheet.getRow(cr.getRowNum()).getCell(cr.getColNum())
					.setCellValue(sheetAndCells[r][col]);
		}

		// write to file
		FileOutputStream out = new FileOutputStream(xlsFileName);
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getCurrentTimeStamp() {
		// TODO Auto-generated method stub
		String timeStamp = "";
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");
		Date date = new Date();
		timeStamp = df.format(date);
		
		return timeStamp;
	}

}
