package zuma;

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
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main extends Applet implements ActionListener, KeyListener {
	
	public static final int SCALE = 240; 
	private TransformGroup objTrans;
	private Transform3D trans;
	private BranchGroup objRoot; 
	private SpaceShip ss;
	private int color;
	private Color3f[] colorSet;
	private SpellCard1 sc1; 
	
	public BranchGroup createSceneGraph() {
		BoundingSphere bounds =
				new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1
				= new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		objRoot.addChild(light1);
	
		
		// Set up the ambient light
		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);

		objRoot.addChild(ambientLightNode);
		return objRoot;
	}
	public Main() {
		objTrans = new TransformGroup();
		trans = new Transform3D();
		objRoot = new BranchGroup();
		objTrans.getTransform(trans);
		trans.setScale(0.02);
		objTrans.setTransform(trans);
		ss = new SpaceShip(objTrans);
		
		objTrans = new TransformGroup();
		trans = new Transform3D();
		objRoot = new BranchGroup();
		objTrans.getTransform(trans);
		trans.setScale(0.02);
		objTrans.setTransform(trans);
		sc1 = new SpellCard1(objTrans);
		
		this.addKeyListener(ss);
		this.setFocusable(true);
		setLayout(new BorderLayout());
		GraphicsConfiguration config =
				SimpleUniverse.getPreferredConfiguration();
		Canvas3D c = new Canvas3D(config);
		add("Center", c);
		c.addKeyListener(this);
		Timer timer = new Timer(17, this);
		timer.start();
		
		BranchGroup scene = createSceneGraph();
		SimpleUniverse u = new SimpleUniverse(c);
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(scene);
		
		scene = new BranchGroup();
		scene = ss.createSceneGraph();
		u.addBranchGraph(scene);
		
		scene = new BranchGroup();
		scene = ss.getBullet();
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		scene.setCapability(BranchGroup.ALLOW_DETACH);
		u.addBranchGraph(scene);
		
		scene = new BranchGroup();
		scene = sc1.createSceneGraph();
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		scene.setCapability(BranchGroup.ALLOW_DETACH);
		u.addBranchGraph(scene);
		
		scene = new BranchGroup();
		scene = sc1.getBullet();
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		scene.setCapability(BranchGroup.ALLOW_DETACH);
		u.addBranchGraph(scene);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ss.actionPerformed(e);
		//ss.shot();
		
	}

	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");
		Main m = new Main();
		MainFrame mf = new MainFrame(m, 4*SCALE, 3*SCALE);
	}
}
