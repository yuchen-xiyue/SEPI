package zuma;

public abstract class AbstractBullets {
	private int hitPoint;
	private int damage;
	private int type;
	
	public AbstractBullets() {
		hitPoint = 1;
		damage = 1;
		type = 0;
	}
	
	public void setHP(int hitPoint) {
		this.hitPoint = hitPoint;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void setType(int type) {
		this.type = type; 
	}
}
