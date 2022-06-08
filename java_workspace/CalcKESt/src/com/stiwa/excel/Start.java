package com.stiwa.excel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Start {
	static DecimalFormat df = new DecimalFormat("0.00000");
	public static void main(String[] args) {
		ExcelDataHandler edh = new ExcelDataHandler();
		ArrayList<Transaction> excelData = edh.getAl();

		double resultValue = 0;
		double resultAmount = 0;
		double kest = 0;
		for (int i = 0; i < excelData.size(); i++) {
			resultValue += excelData.get(i).getValue();

			if (excelData.get(i).getKind().equalsIgnoreCase("Verkauf")) {
				excelData.get(i).setAmount(excelData.get(i).getAmount() * -1);
			}
			resultAmount += excelData.get(i).getAmount();
		}

		ArrayList<Transaction> filteredData = new ArrayList<Transaction>();

		for (int i = 0; i < excelData.size(); i++) {
			if (!excelData.get(i).getKind().equalsIgnoreCase("Ausschüttung")) {
				filteredData.add(excelData.get(i));
			}
		}

//		for (int i = 0; i < filteredData.size(); i++) {
//			System.out.println(filteredData.get(i));
//		}

		double gewichtete = 0;
		double lfdPreis = 0;
		double lfdStück = 0;
		double lfdKurs = 0;
		double average = 0;
		double sumAmount = 0;
		double rest = 0;
		String previousKind = "";
		for (int i = 0; i < filteredData.size(); i++) {
			lfdPreis += filteredData.get(i).getValue();
			lfdStück += filteredData.get(i).getAmount();
			lfdKurs = lfdPreis / lfdStück;
			double share = filteredData.get(i).getValue() / filteredData.get(i).getAmount();
//			if (!filteredData.get(i).getKind().equalsIgnoreCase("Verkauf")) {
//				gewichtete = 0;
//			}
			if (!filteredData.get(i).getKind().equalsIgnoreCase("Verkauf")) {
				gewichtete += Math.abs(filteredData.get(i).getAmount()) * Math.abs(share);
				previousKind = filteredData.get(i).getKind();
				System.out.println("KAUF " + filteredData.get(i) + " share: " + df.format(share) + " conPrice: "
						+ df.format(lfdPreis) + " conAmount: " + df.format(lfdStück) + " conShare: "
						+ df.format(lfdKurs) + " gewichtete: " + df.format(gewichtete) + " sumAmount: " + sumAmount);
				sumAmount += filteredData.get(i).getAmount();
			} else {
				average = gewichtete / sumAmount;
				rest = 0;
				System.out.println("VERKAUF " + filteredData.get(i) + " share: " + df.format(share) + " conPrice: "
						+ df.format(lfdPreis) + " conAmount: " + df.format(lfdStück) + " conShare: "
						+ df.format(lfdKurs) + " gewichtete: " + df.format(gewichtete) + " average: " + average
						+ " sumAmount: " + sumAmount);
				gewichtete = 0;
			}
		}

//		System.out.println("resultValue:\t€ " + df.format(resultValue));
//		System.out.println("resultAmount:\t   " + df.format(resultAmount));
//		System.out.println("kest:\t\t€  " + df.format(resultValue * 0.25));

	}

}
