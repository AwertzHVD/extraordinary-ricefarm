package com.stiwa.drawshape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShapeView extends JPanel {
	private int month;
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	private String letter = "S";
	private JPanel currentShape;
	private ExcelDataHandler excelDataHandler;
	private ViewPanel viewPanel;

	private int upperTileAmount = 15;
	private int lowerTileAmount = 16;

	public ShapeView(ViewPanel viewPanel) {
		setViewPanel(viewPanel);
		getCurrentMonth();
		this.setVisible(true);
		this.setBackground(Color.white);
		this.setSize(new Dimension(1920 / 2, 1200));
	}

	public void changeShape() {
		this.paint(getGraphics());
		screenshot();
	}

	private void getCurrentMonth() {
		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();
		for (int month = 0; month < getMonths().length; month++) {
			if (getMonths()[month].equalsIgnoreCase(currentMonth.toString())) {
				setMonth(month);
			}
		}
	}

	public void screenshot() {
		try {
			final Robot robot = new Robot();
			final Rectangle area = new Rectangle(50, 50, 850, 1100);
			final BufferedImage bufferedImage = robot.createScreenCapture(area);
			File outputfile = null;
			switch (getLetter()) {
			case "S":
				outputfile = new File("resources\\safety.png");
				break;
			case "P":
				outputfile = new File("resources\\production.png");
				break;
			case "Q":
				outputfile = new File("resources\\quality.png");
				break;
			case "L":
				outputfile = new File("resources\\delivery.png");
				break;
			default:
				break;
			}
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (final Exception e) {
			System.out.println("Could not capture screen " + e.getMessage());
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 16));
		g2d.setStroke(new BasicStroke(5));
		g2d.setPaint(Color.white);
		g2d.fillRect(50, 50, 850, 1100);
		switch (getLetter()) {
		case "S":
			setExcelDataHandler(new ExcelDataHandler("safety", 2022, getMonth()));
			drawShapeS(g2d);
			break;
		case "P":
			setExcelDataHandler(new ExcelDataHandler("production", 2022, getMonth()));
			drawShapeP(g2d);
			break;
		case "Q":
			setExcelDataHandler(new ExcelDataHandler("quality", 2022, getMonth()));
			drawShapeQ(g2d);
			break;
		case "L":
			setExcelDataHandler(new ExcelDataHandler("delivery", 2022, getMonth()));
			drawShapeL(g2d);
			break;
		default:
			break;
		}
	}

	private void drawShapeQ(Graphics2D g2d) {
		g2d.scale(2.1, 2.1);
		final double extent = 12.8571428571428571428571428571;
		final double cirleSize = 200;
		final double posX = 100;
		final double posY = 100;
		final double tileSize = 300;

		int counter = 30;
		for (int tileIndex = 30; tileIndex < 32; tileIndex++) {
			final Arc2D outerTile = new Arc2D.Double(0, 0, tileSize * 1.5, tileSize * 1.5,
					(tileIndex * (extent - 2.75)) - 1.2, extent - 2.75, Arc2D.PIE);
			g2d.setPaint(checkTileStatus(counter));
			g2d.fill(outerTile);
			g2d.setPaint(Color.black);
			g2d.draw(outerTile);
			counter--;
		}

		counter = 0;
		for (int tileIndex = 27; tileIndex >= 0; tileIndex--) {
			final Arc2D outerTile = new Arc2D.Double(posX, posY, tileSize, tileSize, (counter * extent) + 80, extent,
					Arc2D.PIE);
			g2d.setPaint(checkTileStatus(tileIndex));
			g2d.fill(outerTile);

			final Arc2D innerTile = new Arc2D.Double(posX + (tileSize / 6), posY + (tileSize / 6), tileSize / 1.5,
					tileSize / 1.5, 80 + (counter * extent), extent, Arc2D.PIE);
			g2d.setPaint(Color.black);
			g2d.fill(innerTile);

			g2d.setPaint(Color.black);
			g2d.draw(outerTile);
			counter++;
		}

		g2d.setPaint(Color.white);
		g2d.fillOval((int) posX + 55, (int) (posY + 55), (int) cirleSize - 10, (int) cirleSize - 10);

		final Arc2D innerTile = new Arc2D.Double(posX + (tileSize / 6), posY + (tileSize / 6), tileSize / 1.51,
				tileSize / 1.51, (22 * extent) + 16, extent + 12, Arc2D.PIE);
		g2d.setPaint(checkTileStatus(28));
		g2d.fill(innerTile);
		g2d.setPaint(Color.black);
		g2d.draw(innerTile);

		final Arc2D smallInnerTile = new Arc2D.Double(posX + (tileSize / 6), posY + (tileSize / 6), tileSize / 1.80,
				tileSize / 1.87, 18 + (22 * extent), extent + 5, Arc2D.PIE);
		g2d.setPaint(Color.black);
		g2d.fill(smallInnerTile);
		g2d.setPaint(Color.white);
		g2d.fillOval((int) (posX + 100), (int) (posY + 95), (int) cirleSize / 2 + 4, (int) cirleSize / 2 + 4);

		g2d.setPaint(Color.black);
		g2d.drawString("29", 292, 313);
		g2d.drawString("30", 367, 371);
		g2d.drawString("31", 340, 395);
		g2d.rotate(Math.toRadians(30), 803, 495);
		for (int tileIndex = 1; tileIndex < 29; tileIndex++) {
			g2d.drawString(tileIndex + "", 168, 445);
			g2d.rotate(Math.toRadians(extent - 0.04), 203, 560);
		}
	}

	private void drawShapeP(Graphics2D g2d) {
		g2d.scale(1.8, 1.8);
		g2d.setStroke(new BasicStroke(5));
		final double extent = 15;
		final double cirleSize = 200;
		final double posX = 100;
		final double posY = 100;
		final double tileSize = 300;

		final Arc2D lastTile = new Arc2D.Double(posX, posY, tileSize, tileSize, 180, 60, Arc2D.PIE);
		g2d.setPaint(checkTileStatus(30));
		g2d.fill(lastTile);
		g2d.setPaint(Color.black);
		g2d.draw(lastTile);

		int count = 0;
		for (int tileIndex = 29; tileIndex >= 10; tileIndex--) {
			final Arc2D outerTile = new Arc2D.Double(posX, posY, tileSize, tileSize, (count * extent) + 240, extent,
					Arc2D.PIE);
			g2d.setPaint(checkTileStatus(tileIndex));
			g2d.fill(outerTile);

			final Arc2D innerTile = new Arc2D.Double(posX + (tileSize / 6), posY + (tileSize / 6), tileSize / 1.5,
					tileSize / 1.5, 240 + (count * extent), extent, Arc2D.PIE);
			g2d.setPaint(Color.black);
			g2d.fill(innerTile);
			g2d.draw(outerTile);
			count++;
		}

		g2d.setPaint(Color.white);
		g2d.fillOval(155, 155, (int) cirleSize - 10, (int) cirleSize - 10);
		g2d.setPaint(Color.black);
		g2d.drawOval(153, 153, (int) cirleSize - 5, (int) cirleSize - 5);

		int polePosX = 101;
		int polePosY = 250;
		int tileHeight = 35;
		int tileWidth = 52;

		for (int index = 0; index < 10; index++) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(polePosX, polePosY + (index * tileHeight), tileWidth, tileHeight);
			g2d.setPaint(Color.black);
			g2d.drawRect(polePosX, polePosY + (index * tileHeight), tileWidth, tileHeight);
		}

		for (int i = 9; i > 0; i--) {
			g2d.drawString(i + "", 123, 623 - (i * tileHeight));
		}
		g2d.drawString("10", 117, 623 - (10 * tileHeight));

		g2d.setTransform(g2d.getTransform());
		for (int tileIndex = 11; tileIndex <= 31; tileIndex++) {
			g2d.drawString(tileIndex + "", (float) (117), (float) (240));
			g2d.rotate(Math.toRadians(extent), 250, 250);
		}
		g2d.rotate(Math.toRadians(45), 250, 250);
	}

	private void drawShapeL(Graphics2D g2d) {
		g2d.scale(1.2, 1.2);
		for (int index = 0; index < 25; index += 2) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(100, 100 + (index / 2 * 60), 87, 60);
			g2d.setPaint(Color.black);
			g2d.drawRect(100, 100 + (index / 2 * 60), 87, 60);
			g2d.drawString(index + 1 + "", 140, 135 + (index / 2 * 60));
		}
		for (int index = 1; index < 26; index += 2) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(187, 100 + (index / 2 * 60), 87, 60);
			g2d.setPaint(Color.black);
			g2d.drawRect(187, 100 + (index / 2 * 60), 87, 60);
			g2d.drawString(index + 1 + "", 227, 135 + (index / 2 * 60));
		}
		for (int index = 26; index < 31; index++) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(274 + ((index - 26) * 87), 760, 87, 120);
			g2d.setPaint(Color.black);
			g2d.drawRect(274 + ((index - 26) * 87), 760, 87, 120);
			g2d.drawString(index + 1 + "", 310 + ((index - 26) * 87), 825);
		}
	}

	private void drawShapeS(Graphics2D g2d) {
		g2d.scale(1.7, 1.7);
		int extent = 17;
		int circleSize = 200;
		double posX = 100;
		double posY = 100;
		double tileSize = 300;

		for (int tileIndex = 0; tileIndex < getUpperTileAmount(); tileIndex++) {
			Arc2D outerTile = new Arc2D.Double(posX, posY, tileSize, tileSize, 15 + (tileIndex * extent), extent,
					Arc2D.PIE);
			g2d.setPaint(checkTileStatus(tileIndex));
			g2d.fill(outerTile);

			Arc2D innerTile = new Arc2D.Double(posX + (tileSize / 6), posY + (tileSize / 6), tileSize / 1.5,
					tileSize / 1.5, 15 + (tileIndex * extent), extent, Arc2D.PIE);
			g2d.setPaint(Color.black);
			g2d.fill(innerTile);
			g2d.draw(outerTile);
		}

		int counter = 0;
		for (int tileIndex = getLowerTileAmount() - 1; tileIndex >= 0; tileIndex--) {
			Arc2D tileToDraw = new Arc2D.Double(posX, posY * 3.45, tileSize, tileSize, 178 + (tileIndex * extent),
					extent, Arc2D.PIE);
			g2d.setPaint(checkTileStatus(counter + getUpperTileAmount()));
			g2d.fill(tileToDraw);
			Arc2D innerTile = new Arc2D.Double(posX + (tileSize / 6), posY * 3.45 + (tileSize / 6), tileSize / 1.5,
					tileSize / 1.5, 178 + (tileIndex * extent), extent, Arc2D.PIE);
			g2d.setPaint(Color.black);
			g2d.fill(innerTile);
			g2d.draw(tileToDraw);
			counter++;
		}

		g2d.setPaint(Color.white);
		g2d.fillOval((int) (100 + (tileSize / 6) + 5), (int) (150) + 5, (int) circleSize - 10, (int) circleSize - 10);
		g2d.fillOval((int) (100 + (tileSize / 6) + 5), (int) (396) + 5, (int) circleSize - 10, (int) circleSize - 10);

		g2d.setPaint(Color.black);

		for (int tileIndex = 15; tileIndex > 0; tileIndex--) {
			g2d.drawString(tileIndex + "", (float) (222), (float) (377));
			g2d.rotate(Math.toRadians(extent), 247, 249);
		}

		g2d.rotate(Math.toRadians(105), 247, 249);
		for (int tileIndex = 16; tileIndex <= 31; tileIndex++) {
			g2d.drawString(tileIndex + "", (float) (260), (float) (377));
			g2d.rotate(Math.toRadians(extent), 249, 495);
		}
		g2d.rotate(Math.toRadians(88), 249, 495);

	}

	public Color checkTileStatus(int index) {
		try {
			switch (getExcelDataHandler().getDays().get(index)) {
			case 0:
				return Color.white;
			case 1:
				return Color.green;
			case 2:
				return Color.orange;
			case 3:
				return Color.red;
			default:
				break;
			}
		} catch (Exception e) {
			return Color.white;
		}
		return null;
	}

	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {
		Font font = new Font(null, Font.PLAIN, 10);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(45), 0, 0);
		Font rotatedFont = font.deriveFont(affineTransform);
		g2d.setFont(rotatedFont);
		g2d.drawString("A String", 0, 0);
	}

	public JPanel getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(JPanel currentShape) {
		this.currentShape = currentShape;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public ExcelDataHandler getExcelDataHandler() {
		return excelDataHandler;
	}

	public void setExcelDataHandler(ExcelDataHandler excelDataHandler) {
		this.excelDataHandler = excelDataHandler;
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

	public ViewPanel getViewPanel() {
		return viewPanel;
	}

	public void setViewPanel(ViewPanel viewPanel) {
		this.viewPanel = viewPanel;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

}
