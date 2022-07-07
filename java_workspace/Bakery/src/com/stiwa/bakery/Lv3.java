package com.stiwa.bakery;

import java.util.Vector;

public class Lv3 {

	class DailySale {
		public static final String kind = "F";
		public int day;
		public int sale = 0;

		public String toString() {
			return kind + " " + day + " " + sale;
		}
	}

	class BankPayment {
		public static final String kind = "B";
		public int day;
		public int payment = 0;

		public String toString() {
			return kind + " " + day + " " + payment;
		}
	}

	private int days;
	private Vector<Integer> unpaidDay = new Vector<Integer>();
	private int sumDebt = 0;

	public Lv3(String[] input) {
		for (int i = 0; i < input.length; i++) {
			new Lv3(input[i]);
		}
	}

	public Lv3(String input) {
		System.out.println(input);
		getData(input);
//		System.out.println(getSumDebt());
		printUnpaidDays();
		System.out.println();
		System.out.println();
	}

	private void printUnpaidDays() {
		for (int i = 0; i < getUnpaidDay().size(); i++) {
			System.out.print(getUnpaidDay().get(i) + " ");
		}
	}

	private void getData(String input) {
		String[] data = input.split(" ");
		separateData(data);
	}

	private void separateData(String[] data) {
		Vector<DailySale> dailySales = new Vector<DailySale>();
		Vector<BankPayment> bankPayments = new Vector<BankPayment>();
		for (int i = 0; i < data.length - 2; i++) {
			if (data[i].equalsIgnoreCase("F")) {
				DailySale ds = new DailySale();
				ds.day = Integer.parseInt(data[i + 1]);
				ds.sale = Integer.parseInt(data[i + 2]);
				dailySales.add(ds);

			} else if (data[i].equalsIgnoreCase("B")) {
				BankPayment bp = new BankPayment();
				bp.day = Integer.parseInt(data[i + 1]);
				bp.payment = Integer.parseInt(data[i + 2]);
				bankPayments.add(bp);
			}
		}
		compressDays(dailySales, bankPayments);
	}

	private void compressDays(Vector<DailySale> dailySales, Vector<BankPayment> bankPayments) {
		Vector<DailySale> compressedDailySales = new Vector<DailySale>();
		Vector<BankPayment> compressedBankPayments = new Vector<BankPayment>();
		compressedDailySales.add(dailySales.get(0));
		for (int i = 1; i < dailySales.size(); i++) {
			if (dailySales.get(i).day == dailySales.get(i - 1).day) {
				dailySales.get(i - 1).sale += dailySales.get(i).sale;
			} else {
				compressedDailySales.add(dailySales.get(i));
			}
		}
		compressedBankPayments.add(bankPayments.get(0));
		for (int i = 1; i < bankPayments.size(); i++) {
			if (bankPayments.get(i).day == bankPayments.get(i - 1).day) {
				bankPayments.get(i - 1).payment += bankPayments.get(i).payment;
			} else {
				compressedBankPayments.add(bankPayments.get(i));
			}
		}

		System.out.println(dailySales);
		System.out.println(bankPayments);
		System.out.println();
		System.out.println(compressedDailySales);
		System.out.println(compressedBankPayments);

		calcDebt(compressedDailySales, compressedBankPayments);
	}

	private void calcDebt(Vector<DailySale> compressedDailySales, Vector<BankPayment> compressedBankPayments) {
		// TODO Auto-generated method stub

	}

	private void setEventlessDays(Vector<DailySale> dailySales, Vector<BankPayment> bankPayments) {
		for (int i = 0; i < bankPayments.size(); i++) {

			try {
				if (dailySales.get(i).sale != bankPayments.get(i).payment) {
					getUnpaidDay().add(bankPayments.get(i).day);
				}
			} catch (Exception e) {
				DailySale temp = new DailySale();
				temp.day = bankPayments.get(i).day;
				temp.sale = 0;
				dailySales.add(temp);
				i--;
			}
		}
		System.out.println(dailySales);
		System.out.println(bankPayments);
	}

	private void getDays(String[] data) {
		int counter = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i].equalsIgnoreCase("F")) {
				counter += Integer.parseInt(data[i + 1]);
			}
		}
		setDays(counter);
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Vector<Integer> getUnpaidDay() {
		return unpaidDay;
	}

	public void setUnpaidDay(Vector<Integer> unpaidDay) {
		this.unpaidDay = unpaidDay;
	}

	public int getSumDebt() {
		return sumDebt;
	}

	public void setSumDebt(int sumDebt) {
		this.sumDebt = sumDebt;
	}

}
