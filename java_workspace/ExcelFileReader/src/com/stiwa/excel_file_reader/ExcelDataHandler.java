package com.stiwa.excel_file_reader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {
	String month;
	Vector<Integer> days = new Vector<Integer>();
	String path;

	public ExcelDataHandler(String path) {
		setPath(path);
		getDataFromTable();

		System.out.println(this.month);
		for (int i = 0; i < this.days.size(); i++) {
			System.out.println(this.days.get(i));
		}
	}

	public void getDataFromTable() {
		try {
			final File file = new File(getPath());
			// creating a new file instance
			final FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			final XSSFWorkbook wb = new XSSFWorkbook(fis);
			final XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object

			final Iterator<Row> itr = sheet.rowIterator();
			// iterating over excel file
			while (itr.hasNext()) {
				final Row row = itr.next();
				final Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				final Cell cell = CellUtil.getCell(row, 1);
				try {
					setMonth(cell.getStringCellValue());
				} catch (final Exception e) {
					getDays().add((int) cell.getNumericCellValue());

				}
			}
		} catch (final Exception e) {
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
