package zuma;

public interface IPlayer {
	public float getDir();

	public void generateBullet();

	public void discardBullet();
	
	public void useSkill(int no);
	
	public void shoot();
}
