package zuma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public abstract class Sprite implements ActionListener {
	protected Transform3D trans;
	protected BranchGroup objRoot;
	private TransformGroup objAngle;
	private TransformGroup objEternal;
	private TransformGroup objScale;
	protected TransformGroup objTrans;
	private Vector3f scale;
	protected Vector3f speed;
	protected Vector3f position;
	private AxisAngle4f angles;
	protected Timer timer;
	
	private Sprite(Vector3f scale, Vector3f position, Vector3f speed, AxisAngle4f angles) {
		this.scale = scale;
		this.angles = angles;
		this.position = position;
		this.speed = speed;
		if(scale == null) {
			scale = new Vector3f(1.0f, 1.0f, 1.0f);
		}
		
		if(speed == null) {
			speed = new Vector3f();
		}
		
		if(angles == null) {
			angles = new AxisAngle4f();
		}
		
		if(position == null) {
			position = new Vector3f();
		}
		initialize();	}
	
	private void initialize() {
		trans = new Transform3D();
		objRoot = new BranchGroup();
		objAngle = new TransformGroup();
		objEternal = new TransformGroup();
		objScale = new TransformGroup();
		objTrans = new TransformGroup();

		objRoot.setCapability(BranchGroup.ALLOW_DETACH);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		objAngle.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objEternal.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objScale.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		
		trans = new Transform3D();
		trans.set(scale);
		objScale.setTransform(trans);
		
		trans = new Transform3D();
		trans.set(angles);
		objAngle.setTransform(trans);
		
		trans = new Transform3D();
		trans.set(position);
		objTrans.setTransform(trans);
		
		
		objAngle.addChild(objScale);
		objTrans.addChild(objAngle);
		objEternal.addChild(objTrans);
		objRoot.addChild(objEternal);
	}
	
	public void interval() {
		position.add(speed);
		trans = new Transform3D();
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
	}
	
	public void run() {
		timer = new Timer(17, this);
		timer.start();
	}

	@Override
	public abstract void actionPerformed(ActionEvent arg0);
}
