package example;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Player extends Applet implements ActionListener, KeyListener {

	private TransformGroup objTrans;
	private Transform3D trans = new Transform3D();
	private float angle;
	private float length;
	private Timer timer;
	private boolean isIn = false;
	private boolean isOut = false; 
	private boolean isLeft = false; 
	private boolean isRight = false;

	public BranchGroup createSceneGraph() {
		BranchGroup objRoot = new BranchGroup();
		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRoot.addChild(objTrans);

		Sphere sphere = new Sphere(0.25f);
		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D pos1 = new Transform3D();
		pos1.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		objTrans.setTransform(pos1);
		objTrans.addChild(sphere);
		objRoot.addChild(objTrans);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);

		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

		DirectionalLight light1

				= new DirectionalLight(light1Color, light1Direction);

		light1.setInfluencingBounds(bounds);

		objRoot.addChild(light1);

		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);

		AmbientLight ambientLightNode = new AmbientLight(ambientColor);

		ambientLightNode.setInfluencingBounds(bounds);

		objRoot.addChild(ambientLightNode);

		return objRoot;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(isRight)
			angle += 0.01*Math.PI;
		else if(isLeft)
			angle -= 0.01*Math.PI; 
	}
	public Player() {		
		angle = 0;
		length = 0; 
		setLayout(new BorderLayout());

	GraphicsConfiguration config =

			SimpleUniverse.getPreferredConfiguration();

	Canvas3D c = new Canvas3D(config);

	add("Center", c);

	c.addKeyListener(this);

	timer = new Timer(17, this);

	timer.start();
		
	BranchGroup scene = createSceneGraph();

	SimpleUniverse u = new SimpleUniverse(c);

	u.getViewingPlatform().setNominalViewingTransform();

	u.addBranchGraph(scene);
	}

	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");

		Player bb = new Player();

		bb.addKeyListener(bb);

		MainFrame mf = new MainFrame(bb, 256, 256);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			isRight = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			isLeft = true; 
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			isRight = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			isLeft = false; 
		}
		
	}

}
