package com.stiwa.drawshape;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {
	int month;
	int year;
	Vector<Integer> days = new Vector<Integer>();
	String kind;
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	public ExcelDataHandler(String kind, int year, int month) {
		setKind(kind);
		setMonth(month);
		setYear(year);
		getDataFromTable();
	}

	public void getDataFromTable() {
		File parentDir = new File(System.getProperty("user.dir"));
		File file = new File(parentDir.getPath()+"\\excel_files\\"+getKind()+ "\\" + getYear() + ".xlsx");
		try {
			final FileInputStream fis = new FileInputStream(file.getPath());
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

}
