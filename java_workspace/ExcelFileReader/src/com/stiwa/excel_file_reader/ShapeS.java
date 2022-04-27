package com.stiwa.excel_file_reader;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

import javax.swing.JFrame;

public class ShapeS extends JFrame {
	private ExcelDataHandler excelDataHandler = new ExcelDataHandler();
	private Panel panel = new Panel();
	private int frameWidth = 1900;
	private int frameHeight = 1180;
	private int upperTileAmount;
	private int lowerTileAmount;

	public ShapeS() {
		this.setTitle("Arbeitsunfälle");
		this.setSize(this.frameWidth, this.frameHeight);
		this.setVisible(true);
		this.setBackground(Color.blue);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTileAmount();
	}

	private void setTileAmount() {
		setUpperTileAmount(getExcelDataHandler().getDays().size() / 2);
		setLowerTileAmount(getExcelDataHandler().getDays().size() - getUpperTileAmount());

	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke((float) 10));
		int extent = 17;

		double size = 1;
		double cirleSize = size * 200;
		double posX = (getFrameWidth() / 2) - (size * 250) + (cirleSize / 2);
		double posY = (size * 100);
		double tileSize = size * 300;

		for (int tileIndex = 0; tileIndex < getUpperTileAmount(); tileIndex++) {
			Arc2D tile = new Arc2D.Double(posX, posY, tileSize, tileSize, 15 + (tileIndex * extent), extent, Arc2D.PIE);
			g2d.setPaint(checkTileStatus(tileIndex));
			g2d.fill(tile);

			g2d.setPaint(Color.white);
			g2d.draw(tile);
		}

		g2d.setPaint(Color.white);
		g2d.fillOval((int) ((getFrameWidth() / 2) - (size * 200) + (cirleSize / 2)), (int) (size * 150),
				(int) cirleSize, (int) cirleSize);

		for (int tileIndex = 0; tileIndex < getLowerTileAmount(); tileIndex++) {
			Arc2D tileToDraw = new Arc2D.Double(posX, posY * 3.45, tileSize, tileSize, 178 + (tileIndex * extent),
					extent, Arc2D.PIE);
			g2d.setPaint(checkTileStatus(tileIndex + getUpperTileAmount()));
			g2d.fill(tileToDraw);
			g2d.setPaint(Color.white);
			g2d.draw(tileToDraw);
			g2d.setPaint(Color.black);
			drawRotate(g2d, 0, 0, 45, "test");
		}

		g2d.setPaint(Color.white);
		g2d.fillOval((int) ((getFrameWidth() / 2) - (size * 200) + (cirleSize / 2)), (int) (size * 396),
				(int) cirleSize, (int) cirleSize);
	}

	public Color checkTileStatus(int index) {
		switch (getExcelDataHandler().days.get(index)) {
		case 0:
			return Color.GREEN;
		case 1:
			return Color.ORANGE;
		case 2:
			return Color.RED;
		default:
			break;
		}
		return Color.WHITE;
	}

	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {
		Font font = new Font(null, Font.PLAIN, 10);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(45), 0, 0);
		Font rotatedFont = font.deriveFont(affineTransform);
		g2d.setFont(rotatedFont);
		g2d.drawString("A String", 0, 0);
	}

	public ExcelDataHandler getExcelDataHandler() {
		return excelDataHandler;
	}

	public void setExcelDataHandler(ExcelDataHandler excelDataHandler) {
		this.excelDataHandler = excelDataHandler;
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

	public int getUpperTileAmount() {
		return upperTileAmount;
	}

	public void setUpperTileAmount(int upperTileAmount) {
		this.upperTileAmount = upperTileAmount;
	}

	public int getLowerTileAmount() {
		return lowerTileAmount;
	}

	public void setLowerTileAmount(int lowerTileAmount) {
		this.lowerTileAmount = lowerTileAmount;
	}

}
