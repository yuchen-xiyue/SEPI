package zuma;

import java.awt.event.ActionEvent;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;

public class RoundDan extends Sprite {

	protected RoundDan(Vector3f scale, Vector3f position, Vector3f speed, AxisAngle4f angles) {
		super(scale, position, speed, angles);
		objScale.addChild(new Sphere());
		// TODO Auto-generated constructor stub
		run();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		interval();
		if(vanish())
			terminated();
	}

}
