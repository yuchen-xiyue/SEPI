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

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

import game.SpaceShip;

public class RollingBall implements ActionListener {
	private Transform3D trans;
	public BranchGroup objRoot;
	private TransformGroup objRotate;
	private TransformGroup objTrans;
	private float rotZ;
	private int color;
	private Vector3f position;
	private float length;
	private float angle;
	private float l_a;
	private float a_a;
	private Timer timer;
	private Appearance ap;
	private Color3f black;
	private SpaceShip player;
	private static final Color3f[] COLOR_SET = new Color3f[] { new Color3f(1.0f, 0.0f, 0.0f),
			new Color3f(1.0f, 0.380f, 0f), new Color3f(1.0f, 1.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f),
			new Color3f(0.0f, 1.0f, 1.0f), new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.627f, 0.125f, 0.941f) };

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		length += l_a;
		angle += a_a;
		position.x = (float) (length * Math.cos(angle));
		position.y = (float) (length * Math.sin(angle));
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		rotZ += 0.01f;
		objRotate.getTransform(trans);
		trans.rotZ(rotZ);
		objRotate.setTransform(trans);
		if (player != null) {
			Vector3f d = player.getPosition();
			d.scale(-1.00f);
			d.sub(this.position);
			if (d.length() < 0.05) {
				if (color == player.getColor())
					player.scoreUpdate((int) (20 - 10 * length));
				else
					player.scoreUpdate(-10);
				objRoot.detach();
				timer.stop();
			}
		}
		if (position.length() > 1.5) {
			objRoot.detach();
			timer.stop();
		}
	}

	public BranchGroup createSceneGraph() {
		objRotate.addChild(new Sphere(0.03f, Primitive.GENERATE_NORMALS +

				Primitive.GENERATE_TEXTURE_COORDS, ap));
		objRotate.getTransform(trans);
		trans.rotZ(rotZ);
		objRotate.setTransform(trans);
		objTrans.addChild(objRotate);
		objTrans.getTransform(trans);
		trans.set(position);
		objTrans.setTransform(trans);
		objRoot.addChild(objTrans);
		timer = new Timer(17, this);
		timer.start();
		return objRoot;
	}

	public RollingBall(float length, float angle, int color, Appearance ap) {
		this.length = length;
		this.angle = angle;
		this.color = color;
		black = new Color3f(0.0f, 0.0f, 0.0f);
		this.ap = ap;
		this.ap.setMaterial(new Material(COLOR_SET[color], black, COLOR_SET[color], black, 80.0f));
		objRotate = new TransformGroup();
		objTrans = new TransformGroup();
		objRoot = new BranchGroup();
		trans = new Transform3D();
		this.color = color;
		this.position = new Vector3f();
		objRoot.setCapability(BranchGroup.ALLOW_DETACH);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	}

	public void setSpeed(float l_a, float a_a) {
		this.l_a = l_a;
		this.a_a = a_a;
	}

	public void setPlayer(SpaceShip player) {
		this.player = player;
	}

}
