package com.stiwa.drawshape;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private ViewPanel viewPanel;
	private JComboBox<String> monthList;
	private Vector<Integer> year = new Vector<Integer>();
	private JComboBox<Integer> yearList;
	private JComboBox<String> categoryList;
	private JLabel title = new JLabel("Select Parameters for Diagram:");

	public ButtonPanel(ViewPanel viewPanel) {
		this.setBackground(Color.LIGHT_GRAY);
		this.add(getTitle());
		setViewPanel(viewPanel);
		setCategories();
		setYears();
		setMonths();
		setTimer();
	}

	private void setTimer() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			String[] letters = { "S", "Q", "L", "P" };
			int counter = 0;

			@Override
			public void run() {
				try {
					getViewPanel().getShapeView().setLetter(letters[counter]);
					getViewPanel().getShapeView().changeShape();
					if (counter == 3) {
						this.cancel();
						System.exit(0);
					}
					counter++;
				} catch (Exception e) {
				}
			};
		};
		timer.schedule(timerTask, new Date(), 1000);
	}

	private void setCategories() {
		String[] categories = { "Safety", "Quality", "Delivery", "Production" };
		setCategoryList(new JComboBox<String>(categories));
		getCategoryList().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (getCategoryList().getSelectedItem().toString()) {
				case "Safety":
					getViewPanel().getShapeView().setLetter("S");
					break;
				case "Quality":
					getViewPanel().getShapeView().setLetter("Q");
					break;
				case "Delivery":
					getViewPanel().getShapeView().setLetter("L");
					break;
				case "Production":
					getViewPanel().getShapeView().setLetter("P");
					break;
				default:
					break;
				}
				System.out.println(getCategoryList().getSelectedItem().toString());
				getViewPanel().getShapeView().changeShape();
			}
		});
		this.add(getCategoryList());
	}

	private void setMonths() {
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		setMonthList(new JComboBox<String>(months));
		getMonthList().setSize(200, 100);
		this.add(getMonthList());
	}

	private void setYears() {
		for (int i = 2000; i <= 2050; i++) {
			this.getYear().add(i);
		}
		setYearList(new JComboBox<Integer>(getYear()));
		this.getYearList().setSelectedIndex(22);
		this.add(getYearList());
	}

	public JComboBox<String> getMonthList() {
		return monthList;
	}

	public void setMonthList(JComboBox<String> monthList) {
		this.monthList = monthList;
	}

	public Vector<Integer> getYear() {
		return year;
	}

	public void setYear(Vector<Integer> year) {
		this.year = year;
	}

	public JComboBox<Integer> getYearList() {
		return yearList;
	}

	public void setYearList(JComboBox<Integer> yearList) {
		this.yearList = yearList;
	}

	public JComboBox<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(JComboBox<String> categoryList) {
		this.categoryList = categoryList;
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public ViewPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(ViewPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

}
