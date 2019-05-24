package zuma;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class BackGround extends Applet implements ActionListener, KeyListener {


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public BackGround() {
		setLayout(new BorderLayout());
		Canvas3D c = new  Canvas3D(null);
		add("Center", c);
		BranchGroup scene = createSceneGraoh();
		
		SimpleUniverse u = new SimpleUniverse(c);

		u.getViewingPlatform().setNominalViewingTransform();
		
		u.addBranchGraph(scene);

	}

	private BranchGroup createSceneGraoh() {
		float v[] = {
			-0.1f, 0.1f, 0.0f,
			-0.1f, -0.1f, 0.0f,
			0.1f, 0.1f, 0.0f,
			0.1f, -0.1f, 0.0f};
		
		
		return null;
	}
}
