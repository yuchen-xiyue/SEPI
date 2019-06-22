package zuma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public abstract class Sprite implements ActionListener {
	protected Transform3D trans;
	protected BranchGroup objRoot;
	protected TransformGroup objAngle;
	protected TransformGroup objEternal;
	protected TransformGroup objScale;
	protected TransformGroup objTrans;
	protected Vector3f scale;
	protected Vector3f speed;
	protected Vector3f position;
	protected AxisAngle4f angles;
	protected Timer timer;
	
	protected Sprite(Vector3f scale, Vector3f position, Vector3f speed, AxisAngle4f angles) {
		this.scale = scale;
		this.angles = angles;
		this.position = position;
		this.speed = speed;
		if(this.scale == null) {
			this.scale = new Vector3f(1.0f, 1.0f, 1.0f);
		}
		
		if(this.speed == null) {
			this.speed = new Vector3f();
		}
		
		if(this.angles == null) {
			this.angles = new AxisAngle4f();
		}
		
		if(this.position == null) {
			this.position = new Vector3f();
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
		trans.setScale(new Vector3d(scale));
		objScale.setTransform(trans);
		
		trans = new Transform3D();
		objAngle.getTransform(trans);
		trans.setRotation(angles);
		objAngle.setTransform(trans);
		
		trans = new Transform3D();
		objTrans.setTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		
		
		objAngle.addChild(objScale);
		objTrans.addChild(objAngle);
		objEternal.addChild(objTrans);
		objRoot.addChild(objEternal);
	}
	
	protected void interval() {
		position.add(speed);
		trans = new Transform3D();
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
	}
	
	protected void run() {
		timer = new Timer(17, this);
		timer.start();
	}

	protected boolean vanish()  {
		if(position.length()>=3.0f)
			return true;
		return  false;
	}
	protected void terminated() {
		objRoot.detach();
		timer.stop();
	}
	@Override
	public abstract void actionPerformed(ActionEvent arg0);
}
