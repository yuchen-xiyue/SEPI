package example;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.sun.j3d.utils.applet.MainFrame;

import com.sun.j3d.utils.universe.*;

import zuma.SpaceShip;

import javax.media.j3d.*;

import javax.vecmath.*;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

import javax.swing.Timer;

public class RollingBall extends Applet implements ActionListener, KeyListener {

	public static final int SCALE = 240; 
	private SpaceShip ss;
	 
	 TransformGroup objTrans;
	
	private ColorSphere colorSphere[] = new ColorSphere[36];
	
	private Vector3f p = new Vector3f(-0.5f, 0f, 0f);
	
	private Transform3D trans = new Transform3D();
	
	private Timer timer;
	
	private BranchGroup objRoot; 
	
	private int t = 0;
	
	public BranchGroup createSceneGraph() {
		// Create the root of the branch graph

				objRoot = new BranchGroup();

				objTrans = new TransformGroup();

				objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

				objRoot.addChild(objTrans);
				for(int i = 0; i < 36; i ++ ) {
					colorSphere[i].setAngle((float)(i*0.0556));
					objRoot.addChild(colorSphere[i].getTrans());
				}
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
	public RollingBall() {

		ss = new SpaceShip(new TransformGroup());
		this.addKeyListener(ss);
		ss.addKeyListener(ss);
		this.setFocusable(true);

		for(int i = 0; i < 36; i ++ )
			colorSphere[i] = new ColorSphere();
		setLayout(new BorderLayout());

		GraphicsConfiguration config =

				SimpleUniverse.getPreferredConfiguration();

		Canvas3D c = new Canvas3D(config);

		add("Center", c);

		c.addKeyListener(this);

		timer = new Timer(17, this);

		 timer.start();



		// Create a simple scene and attach it to the virtual universe

		BranchGroup scene = createSceneGraph();
		
		scene.addChild(ss.objRotate);
		
		trans = new Transform3D();
		trans.setScale(0.02);
		ss.objTrans.setTransform(trans);

		SimpleUniverse u = new SimpleUniverse(c);

		u.getViewingPlatform().setNominalViewingTransform();

		u.addBranchGraph(scene);
		

		trans.setTranslation(p);
		objTrans.setTransform(trans);

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
		for(int i = 0; i < 36; i ++ )
			colorSphere[i].action();
		
		ss.action();
	} 
	

	public static void main(String[] args) {
		System.out.println("Program Started");

		System.setProperty("sun.awt.noerasebackground", "true");

		RollingBall bb = new RollingBall();
		
		//bb.addKeyListener(bb);

		MainFrame mf = new MainFrame(bb, 4*SCALE, 3*SCALE);

	}
}
