import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	public Main() {
		setTitle("Hello World !");
		setSize(600, 300);
		setVisible(true);
		setBackground(Color.DARK_GRAY);
		Penel panel = new Penel();
		this.add(panel);
	}

	public static void main(String[] args) {
		Main main = new Main();
	}

}
