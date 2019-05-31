package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class window{
//	static 
	public static void main(String[] args) {
		menu s = new menu();
		
		

		screencontrol s2=new screencontrol(s);
		JFrame window = new JFrame();
		window.addKeyListener(s2);
//		JButton button = new JButton("start");
//		button.setBounds(15, 15, 200, 100);
//		window.add(button);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("zuma");
		window.setSize(1200, 800);
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		//Dimension��ĸ߶ȺͿ��ֵ��һ�������������ж��ٸ����ص�
		Dimension screen=toolkit .getScreenSize();
		int x=(screen.width-window.getWidth())/2;  
		//���ƴ�������Ļ���ϱ߾�  
		int y=(screen.height-window.getHeight())/2-8;  
		window.setLocation(x,y);

		ImageIcon img = new ImageIcon("src/images/1.jpg");
		JLabel imgLabel = new JLabel(img);
		window.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());
		Container cp=window.getContentPane();
		cp.setLayout(new BorderLayout());
		((JPanel)cp).setOpaque(false); 
		window.add(s);
		
	}

}	
