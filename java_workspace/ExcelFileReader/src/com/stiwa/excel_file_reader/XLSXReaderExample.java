package com.stiwa.excel_file_reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXReaderExample {
	public static void main(String[] args) {
		try {
			File file = new File(
					"C:\\Users\\AguF\\Documents\\GitHub\\extraordinary-ricefarm\\java_workspace\\ExcelFileReader\\src\\excel_files\\demo.xlsx");
			// creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (isNumeric(cell.getStringCellValue())) {
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
					} else {
						System.out.print(cell.getStringCellValue() + "\t\t\t");
					}
				}
				System.out.println("");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}