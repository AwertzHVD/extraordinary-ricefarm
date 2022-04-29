package com.stiwa.excel_file_reader;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ShapeP extends JPanel {
	private File security = new File(
			"C:\\Working\\Workspace_OpenJDK-11\\.template_OpenJDK-11\\ExcelFileReader\\src\\excel_files\\safety\\january.xlsx");
	private ExcelDataHandler excelDataHandler = new ExcelDataHandler(security.getPath());
	private int upperTileAmount;
	private int lowerTileAmount;
	private int frameWidth;
	private int frameHeight;

	private JButton screenshot = new JButton("Screenshot");

	public ShapeP(int frameWidth, int frameHeight) {
		super();
		this.setSize(frameWidth, frameHeight);
		setFrameHeight(frameHeight);
		setFrameWidth(frameWidth);
		setTileAmount();
		setComponents();

	}

	private void setTileAmount() {
		setUpperTileAmount(getExcelDataHandler().getDays().size() / 2);
		setLowerTileAmount(getExcelDataHandler().getDays().size() - getUpperTileAmount());
	}

	private void setComponents() {
		this.getScreenshot().setSize(100, 50);
		this.getScreenshot().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screenshot();
			}
		});
		this.add(getScreenshot());
	}

	public void screenshot() {
		try {
			final Robot robot = new Robot();
			final Rectangle area = new Rectangle(830, 90, 320, 600);
			final BufferedImage bufferedImage = robot.createScreenCapture(area);
			final File outputfile = new File("resources\\security.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (final Exception e) {
			System.out.println("Could not capture screen " + e.getMessage());
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		int extent = 17;
		int circleSize = 200;
		double posX = (getFrameWidth() / 2) - 250 + (circleSize / 2);
		double posY = 100;
		double tileSize = 300;

		drawUpperTileRing(g2d, extent, posX, posY, tileSize);
		drawLowerTileRing(g2d, extent, posX, posY, tileSize);
		drawOverlayCircle(g2d, circleSize);
		drawStringDay(g2d, extent);
	}

	public void drawStringDay(Graphics2D g2d, int extent) {
		g2d.setPaint(Color.black);
		g2d.rotate(Math.toRadians(208), 960, 247);
		for (int tileIndex = getLowerTileAmount() - 1; tileIndex > 0; tileIndex--) {
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
			g2d.drawString(tileIndex + "", (float) (885), (float) (145));
			g2d.rotate(Math.toRadians(extent + 0.2), 935, 255);
		}
		g2d.rotate(Math.toRadians(101), 1128, 296);
		for (int tileIndex = 16; tileIndex < getUpperTileAmount() + getLowerTileAmount() + 1; tileIndex++) {
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
			g2d.drawString(tileIndex + "", (float) (1040), (float) (395));
			g2d.rotate(Math.toRadians(extent + 0.2), 1005, 275);
		}
	}

	public void drawUpperTileRing(Graphics2D g2d, int extent, double posX, double posY, double tileSize) {
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
	}

	public void drawLowerTileRing(Graphics2D g2d, int extent, double posX, double posY, double tileSize) {
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
	}

	public void drawOverlayCircle(Graphics2D g2d, double cirleSize) {
		g2d.setPaint(Color.white);
		g2d.fillOval((int) ((getFrameWidth() / 2) - (200) + (cirleSize / 2)) + 5, (int) (150) + 5, (int) cirleSize - 10,
				(int) cirleSize - 10);
		g2d.fillOval((int) ((getFrameWidth() / 2) - (200) + (cirleSize / 2)) + 5, (int) (396) + 5, (int) cirleSize - 10,
				(int) cirleSize - 10);
	}

	public Color checkTileStatus(int index) {
		switch (getExcelDataHandler().days.get(index)) {
		case 1:
			return Color.green;
		case 2:
			return Color.orange;
		case 3:
			return Color.red;
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

	public JButton getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(JButton screenshot) {
		this.screenshot = screenshot;
	}
}
