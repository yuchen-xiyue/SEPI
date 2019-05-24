package example;

import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;

import zuma.AbstractBullets;


public class ColorSphere extends AbstractBullets{
	private Sphere sphere;
	private TransformGroup objTrans;
	private float angle;
	private float sign;
	private float radius;
	private float maxRadius;
	private float speed;

	public ColorSphere() {
		// TODO Auto-generated constructor stub
		super();
		sphere = new Sphere(0.01f);
		objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_AUTO_COMPUTE_BOUNDS_READ);
		objTrans.addChild(sphere);
		sign = -1;
		radius = 0.0f; 
		maxRadius = 0.6f;
		speed = 0.0005f;
	}
	
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	
	public void setMaxRadius(float maxRadius) {
		this.maxRadius = maxRadius;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public void setColor(Color3f color) {
		Material m = new Material();
		m.setAmbientColor(color);
	}
	
	public void action() {
		Transform3D trans = new Transform3D();
		objTrans.getTransform(trans);
		Vector3f p = new Vector3f();
		
		angle = (float) (angle + 0.005f);
		
		p.setX((float) (radius*Math.cos(angle*Math.PI)));
		p.setY((float) (radius*Math.sin(angle*Math.PI)));
		trans.setTranslation(p);
		objTrans.setTransform(trans);
		if(radius >= maxRadius) {
			sign = 0;
		}
		
		else if(radius <= 0) {
			sign = 1;
		}
		
		radius += speed*sign;
	}

	public TransformGroup getTrans() {
		return objTrans;
	}
	
	public static void main(String[] args) {
		
	}
}
