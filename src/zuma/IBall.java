package zuma;

import java.util.Vector;

import javax.media.j3d.Canvas3D;

public interface IBall {
	
	
	public void proceed(); //Proceed one Node length. 
	
	public int delete(); //Delete this ball and return a score value
	
	public Vector getLoc(); //To get the location (x, y). 
	
	public boolean isPushed(); //Assert if the ball is pushed to move. 

	public boolean isColision(IBall bullet); //Assert if Collision. 
}
