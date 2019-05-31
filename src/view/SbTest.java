package view;

import java.awt.Color;

import javax.swing.JFrame;

public class SbTest {
//	public static void main(String[] args) {
	public void SbTest() {
		Scoreboard s = new Scoreboard();
		JFrame window = new JFrame("score");
		window.getContentPane().setBackground(Color.black);
		window.add(s);
		window.setVisible(true);

		window.setLocation(500, 100);
		window.setSize(600, 800);
	}
//		public static void main(String[] args) {
//			sbTest s=new sbTest();
//		}
//	}
}
