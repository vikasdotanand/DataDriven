package Utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import Base.TestBase;

public class DataUtil extends TestBase {
	
	
	@DataProvider(name = "dp")
	public static Object[][] getData(Method m) {
	
		String sheetName = m.getName();
		int rowCount = read.getRowCount(sheetName);
		//System.out.println("Rowcount = " + rowCount);
		int colCount = read.getColCount(sheetName);
		//System.out.println("Colcount: " + colCount);
		Object[][] data = new Object[rowCount][colCount];

		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < colCount; c++) {
				data[r][c] = read.getCellData(r + 1, c, sheetName);
			}
		}

		return data;
	}
	

}
