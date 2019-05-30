package zuma;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Node;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Main extends Applet implements ActionListener, KeyListener {
	
	public static final int SCALE = 240; 
	private TransformGroup objTrans;
	private Transform3D trans;
	private BranchGroup objRoot; 
	private BranchGroup enemyBullets;
	private BranchGroup myBullets; 
	private BranchGroup player;
	private BranchGroup enemy; 
	private SpaceShip ss;
	private int color;
	private Color3f[] colorSet;
	private SpellCard3 sc1; 
	private float view = 0.35f;
	private boolean isAsc;
	private boolean isDesc;
	private Hud hud;

	Appearance ap = new Appearance();
	
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
		Timer timer = new Timer(17, this);
		timer.start();
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
		trans.setScale(0.03);
		objTrans.setTransform(trans);
		sc1 = new SpellCard3(objTrans);
		
		this.addKeyListener(ss);
		this.setFocusable(true);
		setLayout(new BorderLayout());
		GraphicsConfiguration config =
				SimpleUniverse.getPreferredConfiguration();
		Canvas3D c = new Canvas3D(config);
		add("Center", c);
		c.addKeyListener(this);
		
		
		
		BranchGroup scene = createSceneGraph();
		SimpleUniverse u = new SimpleUniverse(c);
		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(scene);
		objTrans = new TransformGroup(); 
		scene = new BranchGroup();
		scene = ss.createSceneGraph();
		objTrans.addChild(scene);
		//u.addBranchGraph(scene);
		
		myBullets = new BranchGroup();
		myBullets = ss.getBullet();
		myBullets.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		myBullets.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		myBullets.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		myBullets.setCapability(BranchGroup.ALLOW_DETACH);
		objTrans.addChild(myBullets);
		//u.addBranchGraph(myBullets);
		
		enemy = new BranchGroup();
		enemy = sc1.createSceneGraph();
		enemy.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		enemy.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		enemy.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		enemy.setCapability(BranchGroup.ALLOW_DETACH);
		objTrans.addChild(enemy);
		//u.addBranchGraph(enemy);
		
		enemyBullets = new BranchGroup();
		enemyBullets = sc1.getBullet();
		enemyBullets.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		enemyBullets.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		enemyBullets.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		enemyBullets.setCapability(BranchGroup.ALLOW_DETACH);
		objTrans.addChild(enemyBullets);
		//u.addBranchGraph(enemyBullets);
		objTrans.getTransform(trans);
		trans.rotX((float)(-1*view*Math.PI));
		objTrans.setTransform(trans);
		objRoot = new BranchGroup();
		objRoot.addChild(objTrans);
		TextureLoader myLoader = new TextureLoader("Texture/bg.png", this);
		ImageComponent2D myImage = myLoader.getImage();
		scene = new BranchGroup();
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		Background bg = new Background(scene);
		bg.setImage(myImage);
		bg.setApplicationBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0));

		objTrans.addChild(scene);
		u.addBranchGraph(objRoot);		ss.setTarget(sc1);		sc1.setPlayer(ss);
		isAsc = false;
		isDesc = false;
		
		  TextureLoader loader = new TextureLoader("./Texture/bg.png",

				      "LUMINANCE", new Container());
		   Texture texture = loader.getTexture();

		   texture.setBoundaryModeS(Texture.WRAP);

		   texture.setBoundaryModeT(Texture.WRAP);

		   texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );
		   TextureAttributes texAttr = new 
				   TextureAttributes();

		   texAttr.setTextureMode(TextureAttributes.MODULATE);
		   ap.setTexture(texture);

		   ap.setTextureAttributes(texAttr);
		   
		   hud = new Hud(ss);
		  
		   objRoot.addChild(hud.createSceneGraph());

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W)
			isAsc = true;
		if(e.getKeyCode() == KeyEvent.VK_S)
			isDesc = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W)
			isAsc = false;
		if(e.getKeyCode() == KeyEvent.VK_S)
			isDesc = false;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//ss.actionPerformed(e);
		//ss.shot();
		if(isAsc)
			view = view + 0.001f;
		if(isDesc)
			view = view - 0.001f;

			objTrans.getTransform(trans);
			trans.rotX((float)(-1*view*Math.PI));
			//objTrans.setTransform(trans);
			
	}

	public static void main(String[] args) {
		System.setProperty("sun.awt.noerasebackground", "true");
		Main m = new Main();
		MainFrame mf = new MainFrame(m, 4*SCALE, 3*SCALE);
	}
}
