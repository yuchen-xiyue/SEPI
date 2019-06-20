package zuma;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public class Sprite {
	private Transform3D trans;
	private BranchGroup objRoot;
	private TransformGroup objAngle;
	private TransformGroup objEternal;
	private TransformGroup objScale;
	private TransformGroup objTrans;
	private Vector3f scale;
	private Vector3f speed;
	private Vector3f position;
	private AxisAngle4f angles;
	
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
		
		objAngle.addChild(objScale);
		objTrans.addChild(objAngle);
		objEternal.addChild(objTrans);
		objRoot.addChild(objEternal);
	}
}
