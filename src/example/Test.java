package example;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

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
import javax.media.j3d.Group;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f; 

public class Test  extends Applet implements ActionListener, KeyListener, MouseListener  {
	private static Timer timer; 
	private TransformGroup objTrans;
	private SimpleUniverse u;
	private BranchGroup objRoot;
	
	private BranchGroup createBall() {
		
	}
	private void  light() {
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
	}
	public Test() {
		objRoot = new BranchGroup();
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND
				);

		objRoot.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		objRoot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		setLayout(new BorderLayout());

		GraphicsConfiguration config =

				SimpleUniverse.getPreferredConfiguration();

		Canvas3D c = new Canvas3D(config);

		add("Center", c);

		c.addKeyListener(this);

		timer = new Timer(17, this);

		 timer.start();



		// Create a simple scene and attach it to the virtual universe


		u = new SimpleUniverse(c);

		u.getViewingPlatform().setNominalViewingTransform();

		
		u.addBranchGraph(objRoot);
		//TransformGroup next = new TransformGroup();
		objRoot.addChild(createSphere(new TransformGroup()));
		;
	}
	
	public Node createSphere(TransformGroup objTrans) {
		Sphere sphere = new Sphere(0.05f);
		//Appearance ap = new Appearance();
		//ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		
		//sphere.setAppearance(ap);

		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Transform3D pos1 = new Transform3D();

		pos1.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));

		objTrans.setTransform(pos1);

		objTrans.addChild(sphere);
		
		return objTrans;
		
	}
	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");

		//RollingBall bb = new RollingBall();
		Test t = new Test();
		
//		bb.addKeyListener(bb);
		
		MainFrame mf = new MainFrame(t, 512, 512);
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
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		objRoot.addChild(new TransformGroup());
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseWheelMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
