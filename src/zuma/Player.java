package zuma;

import java.awt.event.MouseEvent;

import toolkits.Sprite;
import toolkits.Vector2;

public class Player extends Sprite {

	MouseEvent me = new MouseEvent();
	Player() {
		super();
		transform.setLocation(new Vector2(400, 300));
		transform.setSpeed(new Vector2(1, 0));
		// TODO Auto-generated constructor stub
	}
	
	public void fixedUpdate() {
	}

}
