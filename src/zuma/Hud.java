package zuma;

import java.awt.Font;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Text2D;

public class Hud {
	private BranchGroup objRoot;
	private TransformGroup objTrans;
	private Transform3D trans;
	private  SpaceShip player;
	private Text2D score;
	public BranchGroup createSceneGraph() {
		//score.setGeometry(new );
		Appearance a = new Appearance();
		Material m = new Material(new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), 80.0f);
		m.setLightingEnable(true);
		a.setMaterial(m);
		score.setAppearance(a);
		objTrans.addChild(score);
		objTrans.getTransform(trans);
		trans.setTranslation(new Vector3f(-0.7f, 0.7f, 0.0f));
		objTrans.setTransform(trans);
		objRoot.addChild(objTrans);
		
		return objRoot;
		
	}
	
	public Hud(SpaceShip player) {
		objRoot = new BranchGroup();
		objTrans = new TransformGroup();
		trans = new Transform3D(); 
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		this.player = player;
		score = new Text2D("CXKCXKCXK", new Color3f(1.0f, 0.0f, 0.0f), "default", Font.BOLD, 10);
	}
}
