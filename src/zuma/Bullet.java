package zuma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;

public class Bullet implements ActionListener {
	private BranchGroup objRoot;
	private TransformGroup objTrans;
	private TransformGroup objRotate;
	private Transform3D trans;
	private int color;
	private Vector3f speed;
	private static final Color3f[] COLOR_SET = new Color3f[]{new Color3f(1.0f, 0.0f, 0.0f),new Color3f(1.0f, 0.380f, 0f),new Color3f(1.0f, 1.0f, 0.0f),new Color3f(0.0f, 1.0f, 0.0f),new Color3f(0.0f, 1.0f, 1.0f),new Color3f(0.0f, 0.0f, 1.0f),new Color3f(0.627f, 0.125f, 0.941f)};
	private Vector3f position;
	private Timer timer;
	private Appearance ap;
	private Color3f black;
	private float angle;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		position.add(speed);
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		
		if(position.length()>1) {
			objRoot.detach();
		timer.stop();
		}
		
		//timer.removeActionListener(this);
	}
	public BranchGroup createSceneGraph() {
		objRotate.addChild(new Cone(0.01f, 0.02f, ap));
		objRotate.getTransform(trans);
		trans.rotZ(angle);
		objRotate.setTransform(trans);
		
		objTrans.addChild(objRotate);
		objTrans.getTransform(trans);
		trans.set(position);
		
		objTrans.setTransform(trans);
		objRoot.addChild(objTrans);
		
		return objRoot; 
	}
	public Bullet(Vector3f position, float angle, int color) {
		timer = new Timer(17, this);
		timer.start();
		this.angle = angle;
		this.color = color;
		this.position = position;
		ap = new Appearance(); 
		speed = new Vector3f();
		trans = new Transform3D();
		objRoot = new BranchGroup();
		objTrans = new TransformGroup();
		objRotate = new TransformGroup();
		black = new Color3f(0.0f, 0.0f, 0.0f);
		objRoot.setCapability(BranchGroup.ALLOW_DETACH);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		ap.setMaterial(new Material(COLOR_SET[color], black, COLOR_SET[color], black, 80.f));
	}
	
	public void setSpeed(Vector3f speed) {
		this.speed = speed;
	}

	public TransformGroup getTransformGroup() {
		return objTrans;
	}

}
