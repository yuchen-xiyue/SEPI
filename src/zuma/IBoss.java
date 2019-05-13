package zuma;

public interface IBoss {

	public void shake();//when player hit boss, boss shake
	
	public int getlife();//return life of boss
	
	public void attack();
	
	public int attackfreque();//return random time between attack
}
