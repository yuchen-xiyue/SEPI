package view;

import java.awt.Color;

import javax.swing.JFrame;

public class Mytest {
	public static void main(String[] args) {
		Myboard s = new Myboard();
		JFrame window = new JFrame("score");
		window.getContentPane().setBackground(Color.black);
		window.add(s);
		window.setVisible(true);

		window.setLocation(500, 100);
		window.setSize(600, 800);
	}
}
