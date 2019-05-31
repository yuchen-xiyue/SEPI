package zuma;

import java.awt.Container;

import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

public class SpellCard4 extends EnemyShip {
	Texture texture; 
TextureAttributes texAttr = new TextureAttributes();
private static final int MAX_HP = 1000; 
	private int cd = 0;
	private int sign;
	public SpellCard4(TransformGroup objTrans) {
		super(objTrans);
		sign = 1;
		hitPoint = MAX_HP;
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
			
		RollingBall[] balls = new RollingBall[24];
		for(int i = 0; i < 24; i ++)  {
			Appearance ap = new Appearance();
			ap.setTexture(texture);

			ap.setTextureAttributes(texAttr);
		balls[i] = new RollingBall(0.0f, (float) (i*Math.PI/12), (int)(Math.random()*7), ap);
		balls[i].setSpeed(0.001f, (float)(sign * 0.01f));
		bullets.addChild(balls[i].createSceneGraph());
		balls[i].setPlayer(player);
		cd = 300;
		sign = sign *-1; 
		}
		}
		else {
			cd = cd - 1;
		}
	}

	@Override
	protected float rate() {
		// TODO Auto-generated method stub
		if(hitPoint > 0)
			return (float)hitPoint/MAX_HP;
		else
			return  0;  
	}
}
