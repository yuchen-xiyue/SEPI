package view;

import java.awt.Color;

import javax.swing.JFrame;

public class mytest {
public static void main(String[] args) {
	myboard s= new myboard();
	JFrame window=new JFrame("score");
	window.getContentPane().setBackground(Color.black);
	window.add(s);
	window.setVisible(true);

	window.setLocation(500, 100);
	window.setSize(600, 800);
}
}
