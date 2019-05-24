package zuma;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;

public class SpaceShip extends Applet implements ActionListener, KeyListener {
	public TransformGroup objRotate;
	public TransformGroup objRotate1;
	public TransformGroup objTrans;
	public BranchGroup bullets;
	private Vector3f direction;
	private float radius;
	private int sign;
	public TransformGroup[] tg;
	public static  final float SPEED = 0.01f;
	private float angleZ;
	private float angleY; 
	private float length;
	private boolean isShot;
	private boolean isLeft;
	private boolean isRight;
	private boolean isUp;
	private boolean isDown;

	public SpaceShip(TransformGroup objTrans) {
		sign = 1;
		angleZ = 0;
		angleY = 0;
		length = 0.5f;
		tg = new TransformGroup[6];
		objRotate = new TransformGroup();
		objRotate1 = new TransformGroup();
		bullets = new BranchGroup();
		bullets.setCapability(BranchGroup.ALLOW_DETACH);
		this.objTrans = objTrans;
		direction = new Vector3f(0.0f, 2.0f, 0.0f);
		radius = 0.0f;
		isShot = false;
		isLeft = false;
		isRight = false;
		isUp = false;
		isDown = false;
		createSceneGraph();
		
	}
	
	public void shot() {
		Transform3D trans = new Transform3D(); 
		Vector3f vf = new Vector3f(); 
		TransformGroup tg = new TransformGroup();
		objRotate.getTransform(trans);
		trans.get(vf);
		Bullet bullet = new Bullet(new Vector3f(0.0f, 0.0f, 0.0f), angleZ);
		BranchGroup bg = new BranchGroup();
		bg.addChild(bullet.getTransformGroup());
		bullets.addChild(bg);
		//System.out.println(bullets.getAllChildren());
	}

	public void action() {
		if (sign == 1) {
			radius += 0.01;
			if (radius >= 1.0)
				sign = -1;

			Transform3D toward = new Transform3D();
			tg[1].getTransform(toward);
			toward.setTranslation(new Vector3f(0.0f, 1.0f + radius, 0.0f));
			tg[1].setTransform(toward);
			
			Transform3D upward = new Transform3D();
			tg[2].getTransform(upward);
			upward.setTranslation(new Vector3f(0.0f, 0.0f, 1.0f + radius));
			tg[2].setTransform(upward);

			Transform3D leftward = new Transform3D();
			tg[3].getTransform(leftward);
			leftward.setTranslation(new Vector3f(-1.0f - radius, 0.0f, 0.0f));
			tg[3].setTransform(leftward);

			Transform3D downward = new Transform3D();
			tg[4].getTransform(downward);
			downward.setTranslation(new Vector3f(0.0f, 0.0f,  -1.0f - radius));
			tg[4].setTransform(downward);

			Transform3D rightward = new Transform3D();
			tg[5].getTransform(rightward);
			rightward.setTranslation(new Vector3f(1.0f + radius, 0.0f, 0.0f));
			tg[5].setTransform(rightward);

		}

		else {
			radius -= 0.01;
			if (radius <= 0.5)
				sign = 1;

			Transform3D toward = new Transform3D();
			tg[1].getTransform(toward);
			toward.setTranslation(new Vector3f(0.0f, 1.0f + radius, 0.0f));
			tg[1].setTransform(toward);
			
			Transform3D upward = new Transform3D();
			tg[2].getTransform(upward);
			upward.setTranslation(new Vector3f(0.0f, 0.0f, 1.0f + radius));
			tg[2].setTransform(upward);

			Transform3D leftward = new Transform3D();
			tg[3].getTransform(leftward);
			leftward.setTranslation(new Vector3f(-1.0f - radius, 0.0f, 0.0f));
			tg[3].setTransform(leftward);

			Transform3D downward = new Transform3D();
			tg[4].getTransform(downward);
			downward.setTranslation(new Vector3f(0.0f, 0.0f,  -1.0f - radius));
			tg[4].setTransform(downward);

			Transform3D rightward = new Transform3D();
			tg[5].getTransform(rightward);
			rightward.setTranslation(new Vector3f(1.0f + radius, 0.0f, 0.0f));
			tg[5].setTransform(rightward);
		}
		Transform3D trans = new Transform3D();  
		objTrans.getTransform(trans);
		trans.setTranslation( new  Vector3f(0.0f, -length, 0.0f));
		objTrans.setTransform(trans);
		objRotate.getTransform(trans);
		trans.rotZ(angleZ);
		objRotate.setTransform(trans);

		objRotate1.getTransform(trans);
		trans.rotY(angleY);
		objRotate1.setTransform(trans);
		angleY = angleY + SPEED;

		if(isLeft) {
			angleZ  = angleZ - (float)(SPEED/length);
		}
		
		if(isRight) {
			angleZ  = angleZ + (float)(SPEED/length);
		}
		
		if(isUp) {
			length  = length - 0.01f;
		}
		
		if(isDown) {
			length  = length + 0.01f;
		}
		
		if(length <= 0.1) {
			length = 0.1f;
		}
		
		if(length >= 0.8f) {
			length = 0.8f;
		}
	}

	public static void main(String[] args) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			isLeft = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			isShot = true;
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			isRight = true;
		}

		if(e.getKeyCode() == KeyEvent.VK_UP) {
			isUp = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			isDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			isLeft = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_Z) {
			isShot = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			isRight = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			isUp = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			isDown = false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			if (sign == 1) {
				radius += 0.01;
				if (radius >= 1.0)
					sign = -1;

				Transform3D toward = new Transform3D();
				tg[1].getTransform(toward);
				toward.setTranslation(new Vector3f(0.0f, 1.0f + radius, 0.0f));
				tg[1].setTransform(toward);
				
				Transform3D upward = new Transform3D();
				tg[2].getTransform(upward);
				upward.setTranslation(new Vector3f(0.0f, 0.0f, 1.0f + radius));
				tg[2].setTransform(upward);

				Transform3D leftward = new Transform3D();
				tg[3].getTransform(leftward);
				leftward.setTranslation(new Vector3f(-1.0f - radius, 0.0f, 0.0f));
				tg[3].setTransform(leftward);

				Transform3D downward = new Transform3D();
				tg[4].getTransform(downward);
				downward.setTranslation(new Vector3f(0.0f, 0.0f,  -1.0f - radius));
				tg[4].setTransform(downward);

				Transform3D rightward = new Transform3D();
				tg[5].getTransform(rightward);
				rightward.setTranslation(new Vector3f(1.0f + radius, 0.0f, 0.0f));
				tg[5].setTransform(rightward);

			}

			else {
				radius -= 0.01;
				if (radius <= 0.5)
					sign = 1;

				Transform3D toward = new Transform3D();
				tg[1].getTransform(toward);
				toward.setTranslation(new Vector3f(0.0f, 1.0f + radius, 0.0f));
				tg[1].setTransform(toward);
				
				Transform3D upward = new Transform3D();
				tg[2].getTransform(upward);
				upward.setTranslation(new Vector3f(0.0f, 0.0f, 1.0f + radius));
				tg[2].setTransform(upward);

				Transform3D leftward = new Transform3D();
				tg[3].getTransform(leftward);
				leftward.setTranslation(new Vector3f(-1.0f - radius, 0.0f, 0.0f));
				tg[3].setTransform(leftward);

				Transform3D downward = new Transform3D();
				tg[4].getTransform(downward);
				downward.setTranslation(new Vector3f(0.0f, 0.0f,  -1.0f - radius));
				tg[4].setTransform(downward);

				Transform3D rightward = new Transform3D();
				tg[5].getTransform(rightward);
				rightward.setTranslation(new Vector3f(1.0f + radius, 0.0f, 0.0f));
				tg[5].setTransform(rightward);

			}
			Transform3D trans = new Transform3D();  
			objTrans.getTransform(trans);
			trans.setTranslation( new  Vector3f(0.0f, -length, 0.0f));
			objTrans.setTransform(trans);
			objRotate.getTransform(trans);
			trans.rotZ(angleZ);
			objRotate.setTransform(trans);

			objRotate1.getTransform(trans);
			trans.rotY(angleY);
			objRotate1.setTransform(trans);
			angleY = angleY + SPEED;

			if(isLeft) {
				angleZ  = angleZ - (float)(SPEED/length);
			}
			
			if(isRight) {
				angleZ  = angleZ + (float)(SPEED/length);
			}
			
			if(isUp) {
				length  = length - 0.005f;
			}
			
			if(isDown) {
				length  = length + 0.005f;
			}

			if(length <= 0.1) {
				length = 0.1f;
			}
			
			if(length >= 0.8f) {
				length = 0.8f;
			}
			
			if(isShot) {
				shot();
			}
	}

	public void createSceneGraph() {

		objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRotate.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		objRotate.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		objRotate.addChild(objRotate1);
		
		objRotate1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRotate1.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		objRotate1.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		objRotate1.addChild(objTrans);
		

		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		
		Transform3D original = new Transform3D();
		original.set(new Vector3f(0.0f, 0.0f, 0.0f));

		Transform3D toward = new Transform3D();
		toward.setTranslation(direction);
		toward.setScale(0.5f);
		
		Transform3D upward = new Transform3D();
		upward.setTranslation(new Vector3f(0.0f, 0.0f, 2.0f));
		upward.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) (0.5 * Math.PI)));
		upward.setScale(0.5f);

		Transform3D leftward = new Transform3D();
		leftward.setTranslation(new Vector3f(-2.0f, 0.0f, 0.0f));
		leftward.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, (float) (0.5 * Math.PI)));
		leftward.setScale(0.5f);
		
		Transform3D backward = new Transform3D();
		backward.setTranslation(new Vector3f(0.0f, 0.0f, -2.0f));
		backward.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) (-0.5 * Math.PI)));
		backward.setScale(0.5f);

		Transform3D rightward = new Transform3D();
		rightward.setTranslation(new Vector3f(2.0f, 0.0f, 0.0f));
		rightward.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, (float) (1.5 * Math.PI)));
		rightward.setScale(0.5f);

		tg[0] = new TransformGroup();
		tg[0].addChild(new Sphere());
		tg[0].setTransform(original);

		tg[1] = new TransformGroup();
		tg[1].addChild(new Cone());
		tg[1].setTransform(toward);
		
		tg[2] = new TransformGroup();
		tg[2].addChild(new Cone());
		tg[2].setTransform(upward);

		tg[3] = new TransformGroup();
		tg[3].addChild(new Cone());
		tg[3].setTransform(leftward);

		tg[4] = new TransformGroup();
		tg[4].addChild(new Cone());
		tg[4].setTransform(backward);

		tg[5] = new TransformGroup();
		tg[5].addChild(new Cone());
		tg[5].setTransform(rightward);

		for (int i = 0; i < 6; i++) {

			tg[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			tg[i].setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			tg[i].setCapability(TransformGroup.ALLOW_AUTO_COMPUTE_BOUNDS_READ);

			tg[i].setCapability(TransformGroup.ALLOW_CHILDREN_READ);
			tg[i].setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
			objTrans.addChild(tg[i]);
		}
	}

	
	public void getBullet(BranchGroup bullets) {
		bullets = this.bullets;
	}
}
