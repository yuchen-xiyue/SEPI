package zuma;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class SpellCard1 extends EnemyShip {
	int hitPoint = 10000;
	
	public SpellCard1(TransformGroup objTrans) {
		super(objTrans);
	}
	@Override
	protected void shot() {
		for(int i = 0; i < 5; i ++) {
		float angle = (float)(2*Math.PI*Math.random());
		int color  = (int) (7*Math.random());
		Transform3D trans = new Transform3D(); 
		objRotate.getTransform(trans);
		Ball ball = new Ball(new Vector3f(), color);
		Vector3f speed = new Vector3f((float)Math.cos(angle), (float)Math.sin(angle), 0.0f);
		speed.normalize();
		speed.scale(0.01f);
		ball.setSpeed(speed);
		bullets.addChild(ball.createSceneGraph());
			
		}
	}
}
