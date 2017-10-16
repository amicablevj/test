package utility;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtility {
	public static Object[][] getDataArray(String FilePath,int sheetIndex) throws Exception

	{
			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Gets to the WorkBook of Excel
			HSSFWorkbook wb = new HSSFWorkbook(ExcelFile);

			// Gets to the Sheet of the Workbook
			HSSFSheet sheet = wb.getSheetAt(sheetIndex);

			Object [][] data = new Object[sheet.getPhysicalNumberOfRows()][1];

			// Retrieve data from each cell
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				data[i][0] = sheet.getRow(i).getCell(0).getStringCellValue();
			}
			return data;			
	}
	
}
