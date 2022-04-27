package com.stiwa.excel_file_reader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {
	String month;
	Vector<Integer> days=new Vector<Integer>();

	public ExcelDataHandler() {
		getDataFromTable();
	}

	public void getDataFromTable() {
		try {
			File file = new File(
					"C:\\Working\\Workspace_OpenJDK-11\\.template_OpenJDK-11\\ExcelFileReader\\src\\excel_files\\january.xlsx");
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
					try {
				setMonth(cell.getStringCellValue());
					} catch (Exception e) {
						getDays().add((int) cell.getNumericCellValue());
					}
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Vector<Integer> getDays() {
		return days;
	}

	public void setDays(Vector<Integer> days) {
		this.days = days;
	}

	public void printData() {
		System.out.println("Month: "+ getMonth());
		
		for (Integer status : getDays()) {
			System.out.println("Status: "+ status);
		}
	}
	
	

}
