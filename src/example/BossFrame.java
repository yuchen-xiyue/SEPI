package example;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
public class BossFrame extends Frame {
 
	Image image = BossUtil.getImage("images/Boss.png");
 
	public void launchFrame() {
		setSize(500, 500);
		setLocation(100,100);
		setVisible(true);
 
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 200, 200, null);
	}
 
	public static void main(String[] args) {
		BossFrame gf = new BossFrame();
		gf.launchFrame();
	}
 
}