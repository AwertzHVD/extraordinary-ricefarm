package com.stiwa.excel;

public class Transaction {

	private String date;
	private double value;
	private String kind;
	private String firm;
	private double amount;

	public Transaction(String date, String value, String kind, String firm, String amount) {
		setDate(date);
		setValue(Double.parseDouble(value));
		setKind(kind);
		setFirm(firm);
		if (amount.equalsIgnoreCase("NULL")) {
			setAmount(0);
		} else {
			setAmount(Double.parseDouble(amount));
		}
	}

	@Override
	public String toString() {
		return /* getDate() + " " + */getValue() + " " + getKind() + " " + /* getFirm() + " " + */ getAmount();

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
