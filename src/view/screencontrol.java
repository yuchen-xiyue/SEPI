package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.sun.j3d.utils.applet.MainFrame;

import zuma.Main;

public class screencontrol implements KeyListener{

	menu m=new menu();
	public screencontrol(menu m) {
		this.m=m;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_ENTER) {
			//Into the game
			System.setProperty("sun.awt.noerasebackground", "true");
			Main m = new Main();
			MainFrame mf = new MainFrame(m, 4*240, 4*240);
			
		}
		if(key==KeyEvent.VK_S) {
			//Into the score board
			sbTest sbTest=new sbTest();
		}
		if(key==KeyEvent.VK_A) {
			JFrame window=new JFrame("ABOUT");
			window.getContentPane().setBackground(Color.black);
			window.setVisible(true);
			window.setSize(800, 800);
			window.setLocation(500, 100);
		}
		m.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
