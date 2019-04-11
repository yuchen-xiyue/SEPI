package toolkits;

public class Vector2 {
//A class of 2-Dimension Vector and calculation methods; 
	float x;
	float y;

	public Vector2() {
		// Conductor
		this.x = 0;
		this.y = 0;
	}

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2 normalize() {
		float norm = (float) Math.sqrt(x * x + y * y);
		x = x / norm;
		y = y / norm;
		return this;
	}

	public Vector2 add(Vector2 v) {
		//Addition; 
		x = x + v.x;
		y = y + v.y;
		return this;
	}

	public Vector2 multiply(float n) {
		//Return the n times of this Vector; 
		return new Vector2(n * x, n * y);
	}

	public float multiply(Vector2 v) {
		//Return the dot product of this Vector and another one Vector v; 
		return x * v.x + y * v.y;
	}

	public Vector2 rotate(float a) {
		//rotate this vector by a degree;  
		a = (float) (a*Math.PI/180);
		return new Vector2((new Vector2((float) Math.cos(a), (float) -Math.sin(a))).multiply(this),
				(new Vector2((float) Math.sin(a), (float) Math.cos(a))).multiply(this));
	}

	public float distance(Vector2 v) {
		//return distance of two location;     
		return (float) Math.sqrt((x - v.x) * (x - v.x) + (y - v.y) * (y - v.y));
	}
}
