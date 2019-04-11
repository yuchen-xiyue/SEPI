package toolkits;
/**
 * Basic Physical Module
 * @author chens
 * @version 1.0.0
 * */


public class Transform {
	private Vector2 location;//x position and y position
	private Vector2 speed;//actually a vector representing direction
	Vector2 accelerate;//x: speed direction;y : perpendicular to speed direction.  

	Transform() {
		//Conductor
		setLocation(new Vector2());
		setSpeed(new Vector2());
		accelerate = new Vector2();
	}

	Transform(Vector2 location) {
		//Conductor
		this.setLocation(location);
		setSpeed(new Vector2());
		accelerate = new Vector2();
	}

	Transform(Vector2 location, Vector2 speed) {
		//Conductor
		this.setLocation(location);
		this.setSpeed(speed);
		accelerate = new Vector2();
	}

	Transform(Vector2 location, Vector2 speed, Vector2 accelerate) {
		//Conductor
		this.setLocation(location);
		this.setSpeed(speed);
		this.accelerate = accelerate;
	}

	Transform(Vector2 location, Vector2 speed, Vector2 accelerate, Vector2 direction) {
		//Conductor
		this.setLocation(location);
		this.setSpeed(speed);
		this.accelerate = accelerate;
	}

	Transform(Vector2 location, Vector2 accelerate, float speed) {
		//Conductor, speed represents a rotating degree from(1, 0) 
		this.setLocation(location);
		this.setSpeed((new Vector2(1, 0)).rotate(speed));
		this.accelerate = accelerate;
	}

	public Transform translate() {
		getLocation().add(getSpeed().multiply(accelerate.x)).add((new Vector2(getSpeed().y, -getSpeed().x)).multiply(accelerate.y));
		return this;
	}
	
	public float distance(Transform t) {
		return getLocation().distance(t.getLocation());
	}

	public Vector2 getSpeed() {
		//get speed(Vector2)
		return speed;
	}

	public void setSpeed(Vector2 speed) {
		//set speed(Vector2)
		this.speed = speed;
	}

	public Vector2 getLocation() {
		//get position(Vector2)
		return location;
	}

	public void setLocation(Vector2 location) {
		//set Location(Vector2)
		this.location = location;
	}
}
