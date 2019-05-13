package zuma;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;

public class SpaceShip extends Applet implements ActionListener, KeyListener {
	public TransformGroup objTrans;
	private Vector3f direction;
	private float radius; 
	private int sign = 1; 
	public TransformGroup[] tg;
	public static final int SCALE = 240; 
	private float angle = 0;  

	public SpaceShip() {
		tg = new TransformGroup[5];
		objTrans = new TransformGroup();
		direction = new Vector3f(0.0f, 2.0f, 0.0f);
		radius = 0.0f;   
		
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		objTrans.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		Transform3D original = new Transform3D();
		original.set(new Vector3f(0.0f, 0.0f, 0.0f));

		Transform3D toward = new Transform3D();
		toward.setTranslation(direction);
		toward.setScale(0.5f);

		Transform3D leftward = new Transform3D();
		leftward.setTranslation(new Vector3f(-2.0f, 0.0f, 0.0f));
		leftward.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, (float) (0.5 * Math.PI)));
		leftward.setScale(0.5f);
		
		Transform3D backward = new Transform3D();
		backward.setTranslation(new Vector3f(0.0f, -2.0f, 0.0f));
		backward.setRotation(new AxisAngle4f(0.0f, 0.0f, 1.0f, (float) (1.0 * Math.PI)));
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
		tg[2].setTransform(leftward);
		
		tg[3] = new TransformGroup(); 
		tg[3].addChild(new Cone());
		tg[3].setTransform(backward); 
		
		tg[4] = new TransformGroup(); 
		tg[4].addChild(new Cone());
		tg[4].setTransform(rightward);
		
		for(int i = 0; i < 5; i ++) {

			tg[i].setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			tg[i].setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			tg[i].setCapability(TransformGroup.ALLOW_AUTO_COMPUTE_BOUNDS_READ);

			tg[i].setCapability(TransformGroup.ALLOW_CHILDREN_READ);
			tg[i].setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
			objTrans.addChild(tg[i]);
			
			
		}
		
//		objTrans.addChild(shipBody);
//		objTrans.addChild(headArrow);
//		objTrans.addChild(leftArrow);
//		objTrans.addChild(backArrow);
//		objTrans.addChild(rightArrow); 
	}
	
	public void action() {
		if(sign == 1) {
			radius += 0.01
					;
			if(radius >= 1.0)
				sign = -1;
			
			Transform3D toward = new Transform3D();
			tg[1].getTransform(toward);
			toward.setTranslation(new Vector3f(0.0f, 1.0f + radius, 0.0f));
			tg[1].setTransform(toward);
			
			Transform3D leftward = new Transform3D();
			tg[2].getTransform(leftward);
			leftward.setTranslation(new Vector3f(-1.0f - radius, 0.0f, 0.0f));
			tg[2].setTransform(leftward);
			
			Transform3D backward = new Transform3D();
			tg[3].getTransform(backward);
			backward.setTranslation(new Vector3f(0.0f, -1.0f - radius, 0.0f));
			tg[3].setTransform(backward);
			
			Transform3D rightward = new Transform3D();
			tg[4].getTransform(rightward);
			rightward.setTranslation(new Vector3f(1.0f + radius, 0.0f, 0.0f));
			tg[4].setTransform(rightward);
			
		}
		
		else {
			radius -= 0.01;
			if(radius <= 0.5)
				sign = 1;
			
			Transform3D toward = new Transform3D();
			tg[1].getTransform(toward);
			toward.setTranslation(new Vector3f(0.0f, 1.0f + radius, 0.0f));
			tg[1].setTransform(toward);
			
			Transform3D leftward = new Transform3D();
			tg[2].getTransform(leftward);
			leftward.setTranslation(new Vector3f(-1.0f - radius, 0.0f, 0.0f));
			tg[2].setTransform(leftward);
			
			Transform3D backward = new Transform3D();
			tg[3].getTransform(backward);
			backward.setTranslation(new Vector3f(0.0f, -1.0f - radius, 0.0f));
			tg[3].setTransform(backward);
			
			Transform3D rightward = new Transform3D();
			tg[4].getTransform(rightward);
			rightward.setTranslation(new Vector3f(1.0f + radius, 0.0f, 0.0f));
			tg[4].setTransform(rightward);
			
		}
		
		//Transform3D t = new Transform3D();
		//objTrans.getTransform(t);
		//t.setTranslation(new Vector3f(radius, 0.0f, 0.0f) );
		//objTrans.setTransform(t);
		
	}

	public static void main(String[] args) {
		SpaceShip ss = new SpaceShip();
		MainFrame mf = new MainFrame(ss, 4*SCALE, 3*SCALE);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
