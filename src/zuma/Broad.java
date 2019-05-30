package zuma;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;

import javax.media.j3d.Canvas3D;
import javax.swing.JPanel;

public class Broad extends Canvas3D{

	public Broad(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1616386874546775416L;
//	private ScoreKeeper scoreKeeper;     //score data
	
	class Config {
		public static final int Board_Width = 1200;
		public static final int Board_Height = 1000;
	}

	private void drawString(Graphics g, String text, Rectangle rect, int size) {//make our own way to draw a String on the screen
		Graphics2D g2d = (Graphics2D) g.create();
		Font font = new Font("Arial", Font.BOLD, size);
		g2d.setFont(font);
		FontMetrics metrics = g2d.getFontMetrics();
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g2d.setColor(Color.CYAN);
		g2d.drawString(text, x, y);
	}
	
	public void paint(Graphics g) {		//draw String on the window; use the methon drawString ;
		g.setColor(Color.black);
		g.fillRect(0, 0, Config.Board_Width, Config.Board_Height);
		drawString(g, "Asteroids Game Hall of Fame", new Rectangle(0, 0, Config.Board_Width, Config.Board_Height / 4),36);
		g.setColor(Color.CYAN);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		drawString(g, " ", new Rectangle(0, 416, Config.Board_Width, 175), 24);
	}
	
	public static void main(String[] args) {
		
	}
}
