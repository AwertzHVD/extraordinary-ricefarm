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
	private File quality = new File(
			"C:\\Users\\AguF\\Documents\\GitHub\\extraordinary-ricefarm\\java_workspace\\ExcelFileReader\\src\\excel_files\\quality\\2022.xlsx");
	private ExcelDataHandler excelDataHandler = new ExcelDataHandler(getQuality().getPath(), 1);
	private int upperTileAmount;
	private int lowerTileAmount;
	private int frameWidth;
	private int frameHeight;

	private JButton screenshot = new JButton("Screenshot");

	public ShapeP(final int frameWidth, final int frameHeight) {
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
		final double extent = 15;

		final double size = 1.7;
		final double cirleSize = size * 200;
		final double posX = (getFrameWidth() / 2) - (size * 250) + (cirleSize / 2);
		final double posY = (size * 100);
		final double tileSize = size * 300;

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
		g2d.fillOval((int) ((getFrameWidth() / 2) - (size * 200) + (cirleSize / 2)) + 5, (int) (size * 150) + 5,
				(int) cirleSize - 10, (int) cirleSize - 10);
		g2d.setPaint(Color.black);
		g2d.drawOval((int) ((getFrameWidth() / 2) - (size * 200) + (cirleSize / 2)) + 3, (int) (size * 150) + 3,
				(int) cirleSize - 5, (int) cirleSize - 5);

		drawPole(g2d);
		drawStringDay(g2d, extent);
	}

	private void drawPole(Graphics2D g2d) {
		for (int index = 0; index < 10; index++) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(735, 425 + (index * 60), 87, 60);
			g2d.setPaint(Color.black);
			g2d.drawRect(735, 425 + (index * 60), 87, 60);
		}
	}

	public void drawStringDay(Graphics2D g2d, double extent) {
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
		g2d.setPaint(Color.black);
		for (int i = 10; i > 0; i--) {
			g2d.drawString(i + "", 775, 1060 - (i * 60));
		}
		g2d.setTransform(g2d.getTransform());
		for (int tileIndex = 11; tileIndex <= 31; tileIndex++) {
			g2d.drawString(tileIndex + "", (float) (775), (float) (400));
			g2d.rotate(Math.toRadians(15), 990, 425);
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