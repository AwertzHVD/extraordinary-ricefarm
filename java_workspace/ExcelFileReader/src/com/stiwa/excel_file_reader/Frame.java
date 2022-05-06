package com.stiwa.excel_file_reader;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private int frameWidth = 1980;
	private int frameHeight = 1200;
	private ShapeS shapeS;
	private ShapeP shapeP;
	private ShapeQ shapeQ;
	private ShapeL shapeL;

	public Frame(String letter) {
		setShapeS(new ShapeS(getFrameWidth(), getFrameHeight()));
		setShapeP(new ShapeP(getFrameWidth(), getFrameHeight()));
		setShapeL(new ShapeL(getFrameWidth(), getFrameHeight()));
		setShapeQ(new ShapeQ(getFrameWidth(), getFrameHeight()));

		this.setTitle("Shape" + letter);
		drawShape(letter);

		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		this.setUndecorated(true);
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		device.setFullScreenWindow(this);
	}

	public void drawShape(String letter) {
		switch (letter) {
		case "S":
			this.add(getShapeS());
			break;
		case "P":
			this.add(getShapeP());
			break;
		case "Q":
			this.add(getShapeQ());
		case "L":
			this.add(getShapeL());
		default:
			break;
		}
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public ShapeS getShapeS() {
		return shapeS;
	}

	public void setShapeS(ShapeS shapeS) {
		this.shapeS = shapeS;
	}

	public ShapeP getShapeP() {
		return shapeP;
	}

	public void setShapeP(ShapeP shapeP) {
		this.shapeP = shapeP;
	}

	public ShapeQ getShapeQ() {
		return shapeQ;
	}

	public void setShapeQ(ShapeQ shapeQ) {
		this.shapeQ = shapeQ;
	}

	public ShapeL getShapeL() {
		return shapeL;
	}

	public void setShapeL(ShapeL shapeL) {
		this.shapeL = shapeL;
	}

}
