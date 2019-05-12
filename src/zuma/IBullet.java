package zuma;

import java.util.Vector;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public interface IBullet {//the boss bullet
	
	public int Colision(); //Delete this bullet if hit player
	
	public Vector getLoc(); //To get the location (x, y). 
	
	public boolean isPushed(); //Assert if the ball is pushed to move. 

	public boolean isColision(IPlayer player); //Assert if bullet hit player. 
	
	public void color();
}
