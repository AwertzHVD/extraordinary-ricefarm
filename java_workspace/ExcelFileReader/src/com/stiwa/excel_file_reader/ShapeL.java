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
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ShapeL extends JPanel {
	private File quality = new File(
			"C:\\Users\\AguF\\Documents\\GitHub\\extraordinary-ricefarm\\java_workspace\\ExcelFileReader\\src\\excel_files\\quality\\2022.xlsx");
	private ExcelDataHandler excelDataHandler = new ExcelDataHandler(getQuality().getPath(), 1);
	private int upperTileAmount;
	private int lowerTileAmount;
	private int frameWidth;
	private int frameHeight;

	private JButton screenshot = new JButton("Screenshot");

	public ShapeL(final int frameWidth, final int frameHeight) {
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
			final Rectangle area = new Rectangle(725, 115, 640, 895);
			final BufferedImage bufferedImage = robot.createScreenCapture(area);
			final File outputfile = new File("resources\\delivery.png");
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
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
		g2d.setStroke(new BasicStroke(5));
		drawShape(g2d);
	}

	private void drawShape(Graphics2D g2d) {
		for (int index = 0; index < 25; index += 2) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(735, 125 + (index / 2 * 60), 87, 60);
			g2d.setPaint(Color.black);
			g2d.drawRect(735, 125 + (index / 2 * 60), 87, 60);
			g2d.drawString(index + 1 + "", 775, 160 + (index / 2 * 60));
		}
		for (int index = 1; index < 26; index += 2) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(822, 125 + (index / 2 * 60), 87, 60);
			g2d.setPaint(Color.black);
			g2d.drawRect(822, 125 + (index / 2 * 60), 87, 60);
			g2d.drawString(index + 1 + "", 862, 160 + (index / 2 * 60));
		}
		for (int index = 26; index < 31; index++) {
			g2d.setPaint(checkTileStatus(index));
			g2d.fillRect(910 + ((index - 26) * 87), 785, 87, 120);
			g2d.setPaint(Color.black);
			g2d.drawRect(910 + ((index - 26) * 87), 785, 87, 120);
			g2d.drawString(index + 1 + "", 948 + ((index - 26) * 87), 850);
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