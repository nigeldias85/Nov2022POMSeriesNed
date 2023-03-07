package com.qa.opencart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook workbook;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		System.out.println("Reading data from sheet: "+sheetName);
		Object[][] data = null;
		
		try {
			FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			workbook = WorkbookFactory.create(fis);
			sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			
			//Initialize the size of the two-dimensional array data
			data = new Object[rowCount][colCount];
			
			//Iterate through all the rows and colums of the sheet in the testdata xlsx
			for(int i=0; i<rowCount; i++) {
				for(int j=0; j<colCount; j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
