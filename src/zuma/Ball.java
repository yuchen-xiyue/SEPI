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

import com.sun.j3d.utils.geometry.Sphere;

import game.SpaceShip;

public class Ball extends Sprite {
	private int color;
	private Appearance ap;
	private Color3f black;
	private SpaceShip player;
	private static final Color3f[] COLOR_SET = new Color3f[] { new Color3f(1.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 0.380f, 0f), new Color3f(1.0f, 1.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f),
			new Color3f(0.0f, 1.0f, 1.0f), new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.627f, 0.125f, 0.941f) };

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		position.add(speed);
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		if (player != null) {
			Vector3f d = player.getPosition();
			d.scale(-1.00f);
			d.sub(this.position);
			if (d.length() < 0.05) {
				objRoot.detach();
				timer.stop();
			}
		}
		if (position.length() > 1) {
			objRoot.detach();
			timer.stop();
		}
	}

	public BranchGroup createSceneGraph() {
		objTrans.addChild(new Sphere(0.01f, ap));
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		objRoot.addChild(objTrans);
		timer = new Timer(17, this);
		timer.start();
		return objRoot;
	}

	public Ball(Vector3f position, int color) {
		this.color = color;
		ap = new Appearance();
		black = new Color3f(0.0f, 0.0f, 0.0f);
		ap.setMaterial(new Material(COLOR_SET[color], black, COLOR_SET[color], black, 80.f));
		objTrans = new TransformGroup();
		objRoot = new BranchGroup();
		trans = new Transform3D();
		this.color = color;
		this.position = position;
		objRoot.setCapability(BranchGroup.ALLOW_DETACH);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	}

	public void setSpeed(Vector3f speed) {
		this.speed = speed;
	}

	public void setPlayer(SpaceShip player) {
		this.player = player;
	}
}
