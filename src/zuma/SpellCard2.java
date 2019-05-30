package zuma;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public class SpellCard2 extends EnemyShip {
	private float angle = 0;
	private float sp = 0;
	private float ac = 0;
	private int counter = 0;
	public SpellCard2(TransformGroup objTrans) {
		super(objTrans);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void shot() {
		// TODO Auto-generated method stub
		angle += sp;
		sp += ac;
		
		if(counter <= 300) {
			ac = 0.001f;
		}
		else if(counter <= 600) {
			ac = 0;
		}
		else if(counter <= 1200) {
			ac = -0.001f;
		}
		else {
			counter = -300;
		}
		
		counter ++; 
		
		
		
		Transform3D trans = new Transform3D(); 
		objRotate.getTransform(trans);
		Ball ball = new Ball(new Vector3f(), 6);
		Ball ball1 = new Ball(new Vector3f(), 6);
		Ball ball2 = new Ball(new Vector3f(), 6);
		Ball ball3 = new Ball(new Vector3f(), 6);
		Vector3f speed = new Vector3f((float)Math.cos(angle), (float)Math.sin(angle), 0.0f);
		Vector3f speed1 = new Vector3f(-(float)Math.cos(angle), -(float)Math.sin(angle), 0.0f);
		Vector3f speed2 = new Vector3f(-(float)Math.sin(angle), (float)Math.cos(angle), 0.0f);
		Vector3f speed3 = new Vector3f((float)Math.sin(angle), -(float)Math.cos(angle), 0.0f);
		speed.normalize();
		speed.scale(0.01f);
		speed1.scale(0.01f);
		speed2.scale(0.01f);
		speed3.scale(0.01f);
		ball.setSpeed(speed);
		ball1.setSpeed(speed1);
		ball2.setSpeed(speed2);
		ball3.setSpeed(speed3);
		bullets.addChild(ball.createSceneGraph());
		bullets.addChild(ball1.createSceneGraph());
		bullets.addChild(ball2.createSceneGraph());
		bullets.addChild(ball3.createSceneGraph());
		ball.setPlayer(player);
		ball1.setPlayer(player);
		ball2.setPlayer(player);
		ball3.setPlayer(player);
	}

}
