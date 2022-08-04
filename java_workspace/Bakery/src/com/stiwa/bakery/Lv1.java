package com.stiwa.bakery;

import java.util.Vector;

public class Lv1 {

	class DailySale {
		public static final String kind = "F";
		public int day;
		public int sale;

		public String toString() {
			return kind + " " + day + " " + sale;
		}
	}

	class BankPayment {
		public static final String kind = "B";
		public int day;
		public int payment;

		public String toString() {
			return kind + " " + day + " " + payment;
		}
	}

	private int days;
	private Vector<Integer> unpaidDay = new Vector<Integer>();

	public Lv1(String[] input) {
		for (int i = 0; i < input.length; i++) {
			new Lv1(input[i]);
		}
	}

	public Lv1(String input) {
		System.out.println(input);
		getData(input);
//		System.out.println(getDays());
		for (int i = 0; i < getUnpaidDay().size(); i++) {
			System.out.print(getUnpaidDay().get(i)+" ");
		}
		System.out.println();
		System.out.println();
	}

	private void getData(String input) {
		String[] data = input.split(" ");
//		getDays(data);
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
		System.out.println(dailySales);
		System.out.println(bankPayments);

		checkUnpaidDay(dailySales, bankPayments);

	}

	private void checkUnpaidDay(Vector<DailySale> dailySales, Vector<BankPayment> bankPayments) {
		for (int i = 0; i < dailySales.size(); i++) {
			if (dailySales.get(i).sale > bankPayments.get(i).payment) {
				getUnpaidDay().add(bankPayments.get(i).day);
			}
		}

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

}
