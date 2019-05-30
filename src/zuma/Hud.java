package zuma;

import java.awt.Font;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Text3D;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Text2D;

public class Hud {
	private BranchGroup objRoot;
	private  SpaceShip player;
	private Text2D score;
	public BranchGroup createSceneGraph() {
		score.setString("Example. ");
		objRoot.addChild(score);
		
		return objRoot;
	}
	
	public Hud(SpaceShip player) {
		objRoot = new BranchGroup();
		objRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		this.player = player;
		score = new Text2D(player.getScore() + "", new Color3f(1.0f, 1.0f, 1.0f), "default", Font.BOLD, 20);
	}
}
