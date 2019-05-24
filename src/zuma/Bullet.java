package zuma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cone;

public class Bullet implements ActionListener {
	private BranchGroup objRoot;
	private TransformGroup objTrans;
	private TransformGroup objRotate;
	private Transform3D trans;
	private Vector3f speed;
	private Vector3f position;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		position.add(speed);
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		
	}
	
	public Bullet(Vector3f position, float angle) {
		Timer timer = new Timer(17, this);
		timer.start();
		this.position = position;
		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		objRotate = new TransformGroup();
		trans = new Transform3D();
		speed = new Vector3f();
		
		objRotate.addChild(new Cone(0.01f, 0.02f));
		objRotate.getTransform(trans);
		trans.rotZ(angle);
		objRotate.setTransform(trans);
		objTrans.addChild(objRotate);
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
	}
	
	public void setSpeed(Vector3f speed) {
		this.speed = speed;
	}

	public TransformGroup getTransformGroup() {
		return objTrans;
	}
	
	public BranchGroup getGroup() {
		return objRoot;
	}
}
