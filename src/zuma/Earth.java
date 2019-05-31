package zuma;

import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import com.sun.j3d.utils.image.*;

import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Earth {

public BranchGroup addearth() {

   TransformGroup trans = new TransformGroup();  
   BranchGroup objRoot = new BranchGroup();
   TextureLoader loader = new TextureLoader("Texture/bg.png", TextureLoader.BY_REFERENCE, new Container());
   Texture texture = loader.getTexture();
   TextureLoader loader1 = new TextureLoader("Texture/bg1.png", TextureLoader.BY_REFERENCE, new Container());
   Texture texture1 = loader1.getTexture();
   TextureAttributes texAttr = new TextureAttributes();
   texAttr.setTextureMode(TextureAttributes.MODULATE);

   Appearance ap = new Appearance();
   ap.setTexture(texture);
   ap.setTextureAttributes(texAttr);
   ap.setMaterial(new Material(new Color3f(1.0f,  1.0f, 1.0f), new Color3f(.0f,  .0f, .0f), new Color3f(1.0f,  1.0f, 1.0f), new Color3f(.0f,  .0f, .0f), 80.0f));
    
   Appearance ap1 = new Appearance();
   ap1.setTexture(texture1);
   ap1.setTextureAttributes(texAttr);
   int primflags = Primitive.GENERATE_TEXTURE_COORDS;

   Box cube = new Box(8.00f,8.00f, 0.0f, primflags, ap);
   trans.addChild(cube);
   Transform3D original = new Transform3D();
   original.set(new Vector3f(0.0f, 14.00f, -8.0f));
   trans.setTransform(original);
   objRoot.addChild(trans);
   
   trans = new TransformGroup();  
   Box cube1 = new  Box(8.00f, 0.00f, 8.00f, primflags, ap1);  
   trans.addChild(cube1);
   
   original = new Transform3D();
   original.set(new Vector3f(0.0f, 25.00f, -3.000f));
   trans.setTransform(original);
   objRoot.addChild(trans);
   return objRoot;

}

public static void main(String[] args) {
	Earth t = new Earth();
	t.setUp();
}

public void setUp() {
	JFrame jf = new JFrame("Welcome");
	// kill the window on close
	jf.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent winEvent) {
			System.exit(0);
		}
	});
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(1, 1, 2, 2));

	GraphicsConfiguration config = SimpleUniverse
			.getPreferredConfiguration();
	Canvas3D canvas3D = new Canvas3D(config);
	canvas3D.setSize(3600, 1600);
	SimpleUniverse universe = new SimpleUniverse(canvas3D);
	BranchGroup group = new BranchGroup();
	group = addearth();
//	addLights();
	universe.getViewingPlatform().setNominalViewingTransform();
	universe.addBranchGraph(group);
	panel.add(canvas3D);
	jf.getContentPane().add(panel, BorderLayout.CENTER);
	jf.pack();
	jf.setVisible(true);
}

}


