package com.stiwa.start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataHandler {

	private ArrayList<Transaction> al = new ArrayList<Transaction>();

	public ExcelDataHandler() {
		getDataFromTable();
	}

	private void getDataFromTable() {
		File parentDir = new File(System.getProperty("user.dir"));
		File file = new File(parentDir.getPath() + "\\src\\excel_files\\KestBerechnung.xlsx");
		try {
			final FileInputStream fis = new FileInputStream(file.getPath());
			final XSSFWorkbook wb = new XSSFWorkbook(fis);
			final XSSFSheet sheet = wb.getSheetAt(0);
			final Iterator<Row> itr = sheet.rowIterator();
			while (itr.hasNext()) {
				final Row row = itr.next();
				final Cell date = CellUtil.getCell(row, 0);
				final Cell value = CellUtil.getCell(row, 1);
				final Cell kind = CellUtil.getCell(row, 2);
				final Cell firm = CellUtil.getCell(row, 3);
				final Cell amount = CellUtil.getCell(row, 4);

			if (!date.toString().equalsIgnoreCase("Datum")) {
				Transaction transaction = new Transaction(date.toString(), value.toString(), kind.toString(),
						firm.toString(), amount.toString());
				al.add(transaction);
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Transaction> getAl() {
		return al;
	}

	public void setAl(ArrayList<Transaction> al) {
		this.al = al;
	}

}
