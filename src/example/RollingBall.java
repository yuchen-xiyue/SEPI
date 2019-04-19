package example;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

import com.sun.j3d.utils.applet.MainFrame;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;

import javax.vecmath.*;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;

import javax.swing.Timer;

public class RollingBall extends Applet implements ActionListener, KeyListener {

	private TransformGroup objTrans;
	
	private Vector3f p = new Vector3f(-0.5f, 0f, 0f);
	
	private Vector3f o = new Vector3f(0f, 0f, 0f);
	
	private float r = 0f;
	
	private Transform3D trans = new Transform3D();
	
	private Timer timer;
	
	private float sign = -1; 
	
	private BranchGroup objRoot; 
	
	private int t = 0;
	
	float angle = 0.0f;
	
	public Node createSphere() {
		Sphere sphere = new Sphere(0.05f);
		objTrans = new TransformGroup();

		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Transform3D pos1 = new Transform3D();

		pos1.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));

		objTrans.setTransform(pos1);

		objTrans.addChild(sphere);
		return objTrans;
		
	}
	public BranchGroup createSceneGraph() {
		// Create the root of the branch graph

				objRoot = new BranchGroup();

				objTrans = new TransformGroup();

				objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

				objRoot.addChild(objTrans);

				// Create a simple shape leaf node, add it to the scene graph.

//				Sphere sphere = new Sphere(0.05f);
//
//				objTrans = new TransformGroup();
//
//				objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//
//				Transform3D pos1 = new Transform3D();
//
//				pos1.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
//
//				objTrans.setTransform(pos1);
//
//				objTrans.addChild(sphere);
				objTrans = new TransformGroup();
				
				objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D transform = new Transform3D(); 
				ColorCube cb = new ColorCube(0.1f);
				transform.setTranslation((new Vector3f(.0f,.0f,.0f)));
				objTrans.setTransform(transform);
				objTrans.addChild(cb);
				objRoot.addChild(objTrans);

				objRoot.addChild(createSphere());
				
//				Sphere sphere1 = new Sphere(0.05f);
//				
//				objTrans = new TransformGroup();
		//
//				objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//				
//				objTrans.addChild(sphere1);

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
		angle = (float) (angle + 0.01f);
		if(angle > 2) {
			angle -= 2;
		}
		p.setX((float) (r*Math.cos(angle*Math.PI))+o.x);
		p.setY((float) (r*Math.sin(angle*Math.PI))+o.y);
		trans.setTranslation(p);
		objTrans.setTransform(trans);
		if(r >= 0.5) {
			sign = 0;
		}
		
		else if(r <= 0) {
			sign = 1;
		}
		
			r += 0.0005*sign;
		//System.out.println(p.toString());
	} 
	

	public static void main(String[] args) {
		System.out.println("Program Started");

		System.setProperty("sun.awt.noerasebackground", "true");

		RollingBall bb = new RollingBall();
		
//		bb.addKeyListener(bb);

		MainFrame mf = new MainFrame(bb, 512, 512);

	}
}
