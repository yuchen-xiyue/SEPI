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
	TextureLoader loader = new TextureLoader("./Texture/Red.jpg", "LUMINANCE", new Container());
	Texture texture;
	 Appearance ap; 
	 int primflags;
	private SpaceShip ss;
	 
	  private TransformGroup objTrans;
	
	private ColorSphere colorSphere[] = new ColorSphere[36];
	
	private Vector3f p = new Vector3f(-0.5f, 0f, 0f);
	
	private Transform3D trans = new Transform3D();
	
	private Timer timer;
	
	private BranchGroup objRoot; 
	
	private int t = 0;
	
	private class ColorSphere {
		private TransformGroup objTrans1;
		private Color3f c;
		private float angle;
		private float sign;
		private float r; 
		
		ColorSphere(float angle) {
			objTrans1 = new TransformGroup();
			//objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			this.angle = angle;
			sign = -1;
			r = 0.0f; 
			c = new Color3f(1.0f, 1.0f, 1.0f);
		}
		
		ColorSphere(float angle, Color3f c) {
			this.c = c;
			objTrans1 = new TransformGroup();
			//objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			this.angle = angle;
			sign = -1;
			r = 0.0f; 
		}
		
		public void action() {
			//trans = new Transform3D();
			
			angle = (float) (angle + 0.005f);
			if(angle > 2) {
				angle -= 2;
			}
			p.setX((float) (r*Math.cos(angle*Math.PI)));
			p.setY((float) (r*Math.sin(angle*Math.PI)));
			trans.setTranslation(p);
			objTrans1.setTransform(trans);
			if(r >= 0.6) {
				sign = 0;
			}
			
			else if(r <= 0) {
				sign = 1;
			}
			
			r += 0.0005*sign;
		}
	}
	
	public Node createSphere(ColorSphere cs) {
		//Sphere sphere = new Sphere(0.01f);//, primflags, ap);
		//Appearance ap = new Appearance();
		//ap.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
		
		//sphere.setAppearance(ap);

		
		cs.objTrans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		cs.objTrans1.setCapability(TransformGroup.ALLOW_AUTO_COMPUTE_BOUNDS_READ);

		Transform3D pos1 = new Transform3D();

		pos1.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));

		cs.objTrans1.setTransform(pos1);

		cs.objTrans1.addChild(new Sphere(0.01f));
		trans = new Transform3D();
		trans.rotX(-5*Math.PI/12);
		cs.objTrans1.setTransform(trans);
		
		
//		BoundingSphere bounds =
//
//				new BoundingSphere(cs.objTrans1.getBounds());
//
//		Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
//
//		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
//
//		DirectionalLight light1
//
//				= new DirectionalLight(light1Color, light1Direction);
//
//		light1.setInfluencingBounds(bounds);
//
//		cs.objTrans1.addChild(light1);
//
//		// Set up the ambient light
//
//		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
//
//		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
//
//		ambientLightNode.setInfluencingBounds(bounds);
//
//		cs.objTrans1.addChild(ambientLightNode);
		
		return cs.objTrans1;
		
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
				//objTrans = new TransformGroup();
				
				//objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D transform = new Transform3D(); 
				ColorCube cb = new ColorCube(0.1f);
				//transform.rotX(-Math.PI/4);
				objTrans.setTransform(transform);
				//objTrans.addChild(cb);
				//objRoot.addChild(objTrans);
				//transform = new Transform3D(); 
				transform.setScale(0.05);
				ss.objTrans.setTransform(transform);
				objTrans.addChild(ss.objTrans);
				for(int i = 0; i < 36; i ++ )
					objTrans.addChild(createSphere(colorSphere[i]));
				//objRoot.addChild(objTrans);
				
//				Sphere sphere1 = new Sphere(0.05f);
//				
//				objTrans = new TransformGroup();
		//
//				objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				
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
				
				//Transform3D t = new Transform3D();  t.rotX(Math.PI/4);
				//objRoot.set
				return objRoot;
		
	}
	public RollingBall() {

		ss = new SpaceShip();
		
		
		texture = loader.getTexture();

		   texture.setBoundaryModeS(Texture.WRAP);

		   texture.setBoundaryModeT(Texture.WRAP);

		   texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );

		   

		   // Set up the texture attributes

		   //could be REPLACE, BLEND or DECAL instead of MODULATE

		    TextureAttributes texAttr = new TextureAttributes();

		   texAttr.setTextureMode(TextureAttributes.MODULATE);

		    ap = new Appearance();

		    ap.setTexture(texture);

		    ap.setTextureAttributes(texAttr);

		    //set up the material
		    Color3f black = new Color3f(0.0f, 0.0f, 0.0f);

		    Color3f white = new Color3f(1.0f, 1.0f, 1.0f);

		    Color3f red = new Color3f(0.7f, .15f, .15f);

		   ap.setMaterial(new Material(red, black, red, black, 1.0f));

		   

		   // Create a ball to demonstrate textures

		   int primflags = Primitive.GENERATE_NORMALS +

		   Primitive.GENERATE_TEXTURE_COORDS;

		 
		

		for(int i = 0; i < 36; i ++ )
			colorSphere[i] = new ColorSphere((float) (i*0.0556));
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
		for(int i = 0; i < 36; i ++ )
			colorSphere[i].action();
		
		Transform3D trans = new Transform3D();
		ss.action();
		ss.objTrans.getTransform(trans);

		ss.objTrans.setTransform(trans);
//		
//		colorSphere.angle = (float) (colorSphere.angle + 0.01f);
//		if(colorSphere.angle > 2) {
//			colorSphere.angle -= 2;
//		}
//		p.setX((float) (colorSphere.r*Math.cos(colorSphere.angle*Math.PI)));
//		p.setY((float) (colorSphere.r*Math.sin(colorSphere.angle*Math.PI)));
//		trans.setTranslation(p);
//		objTrans.setTransform(trans);
//		if(colorSphere.r >= 0.5) {
//			colorSphere.sign = 0;
//		}
//		
//		else if(colorSphere.r <= 0) {
//			colorSphere.sign = 1;
//		}
//		
//		colorSphere.r += 0.0005*colorSphere.sign;
		//System.out.println(p.toString());
	} 
	

	public static void main(String[] args) {
		System.out.println("Program Started");

		System.setProperty("sun.awt.noerasebackground", "true");

		RollingBall bb = new RollingBall();
		
//		bb.addKeyListener(bb);

		MainFrame mf = new MainFrame(bb, 4*SCALE, 3*SCALE);

	}
}
