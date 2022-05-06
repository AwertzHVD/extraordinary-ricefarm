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

public class ShapeQ extends JPanel {
	private File quality = new File(
			"C:\\Users\\AguF\\Documents\\GitHub\\extraordinary-ricefarm\\java_workspace\\ExcelFileReader\\src\\excel_files\\quality\\2022.xlsx");
	private ExcelDataHandler excelDataHandler = new ExcelDataHandler(getQuality().getPath(), 1);
	private int upperTileAmount;
	private int lowerTileAmount;
	private int frameWidth;
	private int frameHeight;

	private JButton screenshot = new JButton("Screenshot");

	public ShapeQ(final int frameWidth, final int frameHeight) {
		super();
		this.setSize(frameWidth, frameHeight);
		setFrameHeight(frameHeight);
		setFrameWidth(frameWidth);
		setTileAmount();
		setComponents();
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
			final Rectangle area = new Rectangle(725, 160, 535, 560);
			final BufferedImage bufferedImage = robot.createScreenCapture(area);
			final File outputfile = new File("resources\\quality.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (final Exception e) {
			System.out.println("Could not capture screen " + e.getMessage());
		}
	}

	private void setTileAmount() {
		setUpperTileAmount(getExcelDataHandler().getDays().size() / 2);
		setLowerTileAmount(getExcelDataHandler().getDays().size() - getUpperTileAmount());
	}

	@Override
	public void paint(final Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g2d.scale(1.3, 1.3);
		final float extent = (float) 12.8571428571428571428571428571;

		final double size = 1.7;
		final double cirleSize = size * 200;
		final double posX = (getFrameWidth() / 2) - (size * 250) + (cirleSize / 2);
		final double posY = (size * 100);
		final double tileSize = size * 300;

		outerLineQ(g2d, extent, posX, posY, tileSize);

		circleQ(g2d, extent, size, cirleSize, posX, posY, tileSize);

		innerLineQ(g2d, extent, size, cirleSize, posX, posY, tileSize);

		drawStringDay(g2d);
	}

	private void circleQ(final Graphics2D g2d, final float extent, final double size, final double cirleSize,
			final double posX, final double posY, final double tileSize) {
		int count = 0;
		for (int tileIndex = 27; tileIndex >= 0; tileIndex--) {
			final Arc2D outerTile = new Arc2D.Double(posX, posY, tileSize, tileSize, (count * extent) + 80, extent,
					Arc2D.PIE);
			g2d.setPaint(checkTileStatus(tileIndex));
			g2d.fill(outerTile);

			final Arc2D innerTile = new Arc2D.Double(posX + (tileSize / 6), posY + (tileSize / 6), tileSize / 1.5,
					tileSize / 1.5, 80 + (count * extent), extent, Arc2D.PIE);
			g2d.setPaint(Color.black);
			g2d.fill(innerTile);

			g2d.setPaint(Color.black);
			g2d.draw(outerTile);
			count++;
		}

		g2d.setPaint(Color.white);
		g2d.fillOval((int) ((getFrameWidth() / 2) - (size * 200) + (cirleSize / 2)) + 5, (int) (size * 150) + 5,
				(int) cirleSize - 10, (int) cirleSize - 10);
	}

	private void outerLineQ(final Graphics2D g2d, final float extent, final double posX, final double posY,
			final double tileSize) {
		int count2 = 30;
		for (int tileIndex = 30; tileIndex < 32; tileIndex++) {
			final Arc2D outerTile = new Arc2D.Double(posX - (tileSize / 3), posY - (tileSize / 3), tileSize * 1.5,
					tileSize * 1.5, (tileIndex * (extent - 2.75)) - 1.2, extent - 2.75, Arc2D.PIE);
			g2d.setPaint(checkTileStatus(count2));
			g2d.fill(outerTile);
			g2d.setPaint(Color.black);
			g2d.drawRect(840, 248, 53, 300);
			g2d.draw(outerTile);
			count2--;
		}
	}

	private void innerLineQ(final Graphics2D g2d, final float extent, final double size, final double cirleSize,
			final double posX, final double posY, final double tileSize) {
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
		g2d.fillOval((int) ((getFrameWidth() / 2) - (size * 200) + cirleSize * 0.75), (int) (size * 150 * 1.25) + 20,
				(int) cirleSize / 2+4, (int) cirleSize / 2+4);
	}

	public void drawStringDay(Graphics2D g2d) {
		g2d.setPaint(Color.black);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
		g2d.drawString("29", (float) (1070), (float) (530));
		g2d.drawString("30", (float) (1195), (float) (625));
		g2d.drawString("31", (float) (1150), (float) (665));
		g2d.rotate(Math.toRadians(30), 803, 495);
		for (int tileIndex = 1; tileIndex < 29; tileIndex++) {
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
			g2d.drawString(tileIndex + "", (float) (878), (float) (148));
			g2d.rotate(Math.toRadians(12.8571428571428571428571428571), 934, 345);
		}
	}

	public Color checkTileStatus(final int index) {
		try {
			switch (getExcelDataHandler().getDays().get(index)) {
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

		return Color.white;
	}

	public static void drawRotate(final Graphics2D g2d, final double x, final double y, final int angle,
			final String text) {
		final Font font = new Font(null, Font.PLAIN, 10);
		final AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(45), 0, 0);
		final Font rotatedFont = font.deriveFont(affineTransform);
		g2d.setFont(rotatedFont);
		g2d.drawString("A String", 0, 0);
	}

	public ExcelDataHandler getExcelDataHandler() {
		return excelDataHandler;
	}

	public void setExcelDataHandler(final ExcelDataHandler excelDataHandler) {
		this.excelDataHandler = excelDataHandler;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(final int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public void setFrameHeight(final int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public int getUpperTileAmount() {
		return upperTileAmount;
	}

	public void setUpperTileAmount(final int upperTileAmount) {
		this.upperTileAmount = upperTileAmount;
	}

	public int getLowerTileAmount() {
		return lowerTileAmount;
	}

	public void setLowerTileAmount(final int lowerTileAmount) {
		this.lowerTileAmount = lowerTileAmount;
	}

	public File getQuality() {
		return quality;
	}

	public void setQuality(File quality) {
		this.quality = quality;
	}

	public JButton getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(JButton screenshot) {
		this.screenshot = screenshot;
	}

}