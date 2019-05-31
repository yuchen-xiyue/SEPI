package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.*;

import game.SpaceShip;
import game.SpellCard3;

public class Titles implements ActionListener {
	private SpaceShip player;
	private Text3D text;
	private TransformGroup objLength;
	private Timer timer;
	private int pri;
	private int d;
	private SpellCard3 es;

	public static void main(String[] args) {
		Titles t = new Titles();
		// t.setUp();
	}

	public void setPlayer(SpaceShip player) {
		this.player = player;
	}

//	public void setUp() {
//		JFrame jf = new JFrame("Welcome");
//		// kill the window on close
//		jf.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent winEvent) {
//				System.exit(0);
//			}
//		});
//		JPanel panel = new JPanel();
//		panel.setLayout(new GridLayout(1, 1, 2, 2));
//
//		GraphicsConfiguration config = SimpleUniverse
//				.getPreferredConfiguration();
//		Canvas3D canvas3D = new Canvas3D(config);
//		canvas3D.setSize(3600, 1600);
//		SimpleUniverse universe = new SimpleUniverse(canvas3D);
//		BranchGroup group = new BranchGroup();
//		group = addObjects();
//		addLights();
//		universe.getViewingPlatform().setNominalViewingTransform();
//		universe.addBranchGraph(group);
//		panel.add(canvas3D);
//		jf.getContentPane().add(panel, BorderLayout.CENTER);
//		jf.pack();
//		jf.setVisible(true);
//	}
//
//	public BranchGroup addLights() {
//		BranchGroup objRoot = new BranchGroup();
//		
//		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
//				100.0);
//
//		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
//		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
//		DirectionalLight light1 = new DirectionalLight(light1Color,
//				light1Direction);
//		light1.setInfluencingBounds(bounds);
//		objRoot.addChild(light1);
//
//		// Set up the ambient light
//		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
//		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
//		ambientLightNode.setInfluencingBounds(bounds);
//		objRoot.addChild(ambientLightNode);
//		return objRoot;
//	}

	public BranchGroup addEnemyHP(SpellCard3 es) {
		BranchGroup objRoot = new BranchGroup();
		TransformGroup objRotate = new TransformGroup();
		TransformGroup objTrans = new TransformGroup();

		objLength = new TransformGroup();
		objLength.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D trans = new Transform3D();
		this.es = es;
		Cylinder c = new Cylinder(0.02f, 0.8f);
		Appearance a = new Appearance();
		Material m = new Material(new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f),
				new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 1.0f, 1.0f), 80.0f);
		m.setLightingEnable(true);
		a.setMaterial(m);
		c.setAppearance(a);
		objRotate.addChild(c);
		objRotate.getTransform(trans);
		trans.rotZ((float) (0.5 * Math.PI));
		objRotate.setTransform(trans);
		objLength.addChild(objRotate);
		objLength.getTransform(trans);
		trans.setScale(new Vector3d(1, 1, 1));
		objLength.setTransform(trans);
		objTrans.addChild(objLength);
		objTrans.getTransform(trans);
		trans.setTranslation(new Vector3f(0.0f, 0.65f, 0.0f));
		objTrans.setTransform(trans);
		objRoot.addChild(objTrans);

		text.setString("Score: " + player.getScoreString() + "    " + "             		   Stage: " + es.getSc());
		return objRoot;
	}

	public BranchGroup addObjects(SpaceShip player) {
		BranchGroup objRoot = new BranchGroup();
		this.setPlayer(player);

		Font3D f3d = new Font3D(new Font("TestFont", Font.PLAIN, 1), new FontExtrusion());
		text = new Text3D(f3d, new String(), new Point3f(-3.5f, -.5f, -4.5f));
		text.setCapability(Text3D.ALLOW_STRING_WRITE);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f); // white
		Color3f blue = new Color3f(.2f, 0.2f, 0.6f); // blue
		Appearance a = new Appearance();
		Material m = new Material(blue, blue, blue, white, 80.0f);
		m.setLightingEnable(true);
		a.setMaterial(m);

		Shape3D sh = new Shape3D();
		sh.setGeometry(text);
		sh.setAppearance(a);
		TransformGroup tg = new TransformGroup();
		Transform3D t3d = new Transform3D();
		Transform3D tDown = new Transform3D();
		Transform3D rot = new Transform3D();
		Vector3f v3f = new Vector3f(-1.6f, -1.35f, -6.5f);
		t3d.setTranslation(v3f);

		rot.rotX(Math.PI / 10); // rot
		t3d.mul(rot);
		v3f = new Vector3f(-1.0f, 3f, 0f);
		tDown.setTranslation(v3f);
		t3d.mul(tDown);
		t3d.setScale(0.25f);
		tg.setTransform(t3d);
		tg.addChild(sh);
		objRoot.addChild(tg);
		timer = new Timer(17, this);
		timer.start();
		return objRoot;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (player.getScore() != pri)
			d = (player.getScore() - pri);
		if (d > 0)
			text.setString(
					"Score: " + player.getScoreString() + " +" + d + "             		   Stage: " + es.getSc());
		else if (d < 0)
			text.setString(
					"Score: " + player.getScoreString() + " " + d + "              		  Stage: " + es.getSc());
		pri = player.getScore();

		Transform3D trans = new Transform3D();
		objLength.getTransform(trans);
		trans.setScale(new Vector3d(es.rate(), 1, 1));
		objLength.setTransform(trans);
	}
}