package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class EnemyBullet {
	final double shotSpeed=10;
	double angle;
	int lifeLeft;
	private double x,y,xVel,yVel;
	public EnemyBullet(double x,double y,int lifeLeft,double angle) {
		this.angle = angle;
		this.x = x;
		this.y = y;
		this.lifeLeft = lifeLeft;
	}
	public void move(int Board_Width,int Board_Height) {
		xVel=(shotSpeed*Math.cos(angle));
		yVel=(shotSpeed*Math.sin(angle));
		lifeLeft--;
		x=x+xVel;
		y=y+yVel;
	}
	
/*	public CircleBullets(float x, float y, boolean flag) {
		super(x, y);
		speed = 1.0F;
		Num = 24;
		vxCircle = new float[Num];
		vyCircle = new float[Num];
		float rad_step = (float) (6.2831853071795862D / (double) tamaNum);
		float rad;
		if (flag)
			rad = 0.0F;
		else
			rad = (float) ((double) rad_step / 2D);
		for (int i = 0; i < tamaNum;) {
			vxCircle[i] = (float) (Math.cos(rad) * (double) speed);
			vyCircle[i] = (float) (Math.sin(rad) * (double) speed);
			GamePanel.addList(new EnemyShot(x, y, vxCircle[i], vyCircle[i]));
			i++;
			rad += rad_step;
		}

		vx = vxCircle[0];
		vy = vyCircle[0];
	} // bullet 
*/
	
/*	public MoveAimingBullet(float x, float y, Battle zuma) {
		super(x, y);
		speed = 2.0F;
		x_zuma = zuma.x;
		y_zuma = zuma.y;
		x_enemy = x;
		y_enemy = y;
		distance = (float) Math
				.sqrt((((double) x_zuma + (double) zuma.WIDTH / 2D) - (double) x_enemy)
						* (((double) x_zuma + (double) zuma.WIDTH / 2D) - (double) x_enemy)
						+ (double) ((y_zuma - y_enemy) * (y_zuma - y_enemy)));
		if (distance != 0.0F) {
			vx = (float) (((((double) x_zuma + (double) zuma.WIDTH / 2D) - (double) x_enemy) / (double) distance) * (double) speed);
			vy = ((y_zuma - y_enemy) / distance) * speed;
		} else {
			vx = 0.0F;
			vy = speed;
		}
	}
*/
	public  double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int)(x), (int)(y), 6, 6);
	}
	public void changeLifeLeft() {
		lifeLeft--;
	}
	public int getLifeLeft() {
		return lifeLeft;
	}
	public boolean shipCollision(Ship ship) {
		if(Math.pow(ship.getRadius()+3,2) >=Math.pow(ship.getX()-x-3,2)+ Math.pow(ship.getY()-y,2)) {    
			return true;
		}
		return false;
	}
}