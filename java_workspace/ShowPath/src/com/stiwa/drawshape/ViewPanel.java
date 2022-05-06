package com.stiwa.drawshape;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ViewPanel extends JPanel {
	ShapeView shapeView;
	ButtonPanel buttonPanel;
	private int month;
	private int year;

	public ViewPanel() {
		this.setLayout(new GridLayout(1, 2));
		setButtonPanel(new ButtonPanel(this));
		setShapeView(new ShapeView(this));
		this.add(getShapeView());
		this.add(getButtonPanel());
		this.setBackground(Color.white);
	}

	public void setDirectory() {

	}

	public ViewPanel getViewPanel() {
		return this;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ShapeView getShapeView() {
		return shapeView;
	}

	public void setShapeView(ShapeView shapeView) {
		this.shapeView = shapeView;
	}

	public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

}
