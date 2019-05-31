package zuma;

import java.awt.Container;

import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

public class SpellCard5 extends EnemyShip {
	Texture texture; 
TextureAttributes texAttr = new TextureAttributes();
	
	private int cd = 0;
	private int sign;
	public SpellCard5(TransformGroup objTrans) {
		super(objTrans);
		sign = 1;
		// TODO Auto-generated constructor stub
		TextureLoader loader = new TextureLoader("./Texture/Arizona.jpg",

	      "LUMINANCE", new Container());
texture = loader.getTexture();

texture.setBoundaryModeS(Texture.WRAP);

texture.setBoundaryModeT(Texture.WRAP);

texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );

texAttr.setTextureMode(TextureAttributes.MODULATE);
	}

	protected void shot() {
		if(cd <= 0) {
			
		RollingBall[] balls = new RollingBall[48];
			int color =  (int)(Math.random()*7);
		for(int i = 0; i < 48; i ++)  {
			Appearance ap = new Appearance();
			ap.setTexture(texture);

			ap.setTextureAttributes(texAttr);
		balls[i] = new RollingBall(0.0f, (float) (i*Math.PI/24), color, ap);
		balls[i].setSpeed(0.001f, (float)(sign * 0.01f));
		bullets.addChild(balls[i].createSceneGraph());
		balls[i].setPlayer(player);
		cd = 240; 
		}
		//sign = sign *-1;
		}
		else {
			cd = cd - 1;
		}
	}

	@Override
	protected float rate() {
		// TODO Auto-generated method stub
		return 0;
	}
}
