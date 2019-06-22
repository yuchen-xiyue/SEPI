package zuma;

import java.awt.event.ActionEvent;

import javax.media.j3d.BranchGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

public class Enemy extends Sprite {

	protected Enemy(Vector3f scale, Vector3f position, Vector3f speed, AxisAngle4f angles) {
		super(scale, position, speed, angles);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		// TODO Auto-generated constructor stub
		run();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		float angle = (float) (-0.75*Math.PI+0.5*Math.PI*Math.random());
		RoundDan rd = new RoundDan(new Vector3f(0.01f, 0.01f, 0.01f) , new Vector3f(position), new Vector3f( (float) (0.01*Math.cos(angle)), (float) (0.01*Math.sin(angle)), 0.00f), new AxisAngle4f(0.0f, 0.0f, 1.0f, (float) (0.5*Math.PI*Math.random())));
		objRoot.addChild(rd.objRoot);
		//SSystem.out.print("木大");
	}

}
