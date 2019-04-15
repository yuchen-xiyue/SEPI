package example;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class BallLauncher extends Applet implements ActionListener{
	private TransformGroup objTrans;
	private Timer timer;
	private SimpleUniverse u;
	private int cnt = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(cnt == 60) {
		RollingBall bb = new RollingBall();
		
		BranchGroup scene = bb.createSceneGraph();

		u.addBranchGraph(scene);
			cnt = 0; 
		}
		
		else 
			cnt = cnt +1; 
	}
	BallLauncher() {
		setLayout(new BorderLayout());

		GraphicsConfiguration config =

				SimpleUniverse.getPreferredConfiguration();

		Canvas3D c = new Canvas3D(config);

		add("Center", c);

		timer = new Timer(17, this);

		 timer.start();
			
		u = new SimpleUniverse(c);

		u.getViewingPlatform().setNominalViewingTransform();
	}
	public static void main(String[] args) {
		System.out.println("Program Started");

		System.setProperty("sun.awt.noerasebackground", "true");

		BallLauncher bl = new BallLauncher();
//		bb.addKeyListener(bb);

		MainFrame mf = new MainFrame(bl, 512, 512);

	}
}
