package com.stiwa.excel_file_reader;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {
	int month;
	Vector<Integer> days = new Vector<Integer>();
	String path;

	public ExcelDataHandler(String path, int month) {
		setPath(path);
		setMonth(month);
		getDataFromTable();
	}

	public void getDataFromTable() {
		try {
			final FileInputStream fis = new FileInputStream(getPath());
			final XSSFWorkbook wb = new XSSFWorkbook(fis);
			final XSSFSheet sheet = wb.getSheetAt(getMonth());
			final Iterator<Row> itr = sheet.rowIterator();
			while (itr.hasNext()) {
				final Row row = itr.next();
				final Cell cell = CellUtil.getCell(row, 1);
				try {
					cell.getStringCellValue();
				} catch (final Exception e) {
					getDays().add((int) cell.getNumericCellValue());
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
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
