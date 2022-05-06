package com.stiwa.drawshape;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public Frame() {
		this.setTitle("Test");

		this.add(new ViewPanel());
		this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
