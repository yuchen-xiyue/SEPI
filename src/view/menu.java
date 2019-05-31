package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class menu extends JComponent{
	private static final long serialVersionUID = 1L;


	
//	static JFrame launch = new JFrame("Zuma");
//	private String name;
//	ArrayList<String> strings= new ArrayList<>();
//	JPanel panel = new JPanel();
//
//	 final JButton btn = new JButton("start");
	
     
	public void paintComponent(Graphics g) {
//		super.paint(g);
//		strings.add("zuma");
		g.setColor(Color.green);
		Font font = new Font("Comic Sans MS", Font.BOLD, 80);
		g.setFont(font);
	
		g.drawString("STG Zuma" + "\n", 530, 390);
		g.setColor(Color.green);
		font = new Font("Comic Sans MS", Font.BOLD, 60);
		g.setFont(font);
		g.drawString("click Enter --> start\n", 380, 480);
		g.drawString("click S --> score board\n", 380, 550);
		g.drawString("click A --> about game", 380, 620);

		font = new Font("Comic Sans MS", Font.BOLD, 25);
		g.setFont(font);
		g.setColor(Color.magenta);
		g.drawString("Welcome to STG Zuma!", 45, 520);
		g.setColor(Color.ORANGE);
		g.drawString(" Operations: ", 15, 570);
		g.setColor(Color.ORANGE);
		g.drawString(" Up: move up", 15, 600);
		g.drawString(" Down: move down", 15, 630);
		g.drawString(" Right: rotate right", 15, 660);
		g.drawString(" Left: rotate left", 15, 690);
		g.drawString(" Space: fire", 15, 720);
//		g.draw������ͼ
	}

	public void threadPart() {
		this.repaint();
	}

}
