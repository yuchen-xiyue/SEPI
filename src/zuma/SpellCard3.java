package zuma;

import java.awt.Container;

import javax.media.j3d.Appearance;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.image.TextureLoader;

public class SpellCard3 extends EnemyShip {
	private static final int MAX_HP = 1000;
	Texture texture;
	TextureAttributes texAttr = new TextureAttributes();
	private int cd = 0;
	private int sign;

	private int sc = 0;

	public SpellCard3(TransformGroup objTrans) {
		super(objTrans);
		sign = 1;
		hitPoint = MAX_HP;
		// TODO Auto-generated constructor stub
		TextureLoader loader = new TextureLoader("./Texture/Arizona.jpg",

				"LUMINANCE", new Container());
		texture = loader.getTexture();

		texture.setBoundaryModeS(Texture.WRAP);

		texture.setBoundaryModeT(Texture.WRAP);

		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));

		texAttr.setTextureMode(TextureAttributes.MODULATE);
	}

	protected void shot() {
		if (cd <= 0) {
			if (getSc() == 0) {
				RollingBall[] balls = new RollingBall[24];
				for (int i = 0; i < 24; i++) {
					Appearance ap = new Appearance();
					ap.setTexture(texture);

					ap.setTextureAttributes(texAttr);
					balls[i] = new RollingBall(-0.1f, (float) (i * Math.PI / 12), (int) (Math.random() * 7), ap);
					balls[i].setSpeed(0.003f, (float) (sign * 0.01f));
					bullets.addChild(balls[i].createSceneGraph());
					balls[i].setPlayer(player);
					cd = 300;
				}
			} else if (getSc() == 1) {
				RollingBall[] balls = new RollingBall[24];
				for (int i = 0; i < 24; i++) {
					Appearance ap = new Appearance();
					ap.setTexture(texture);

					ap.setTextureAttributes(texAttr);
					balls[i] = new RollingBall(0.0f, (float) (i * Math.PI / 12), (int) (Math.random() * 7), ap);
					balls[i].setSpeed(0.001f, (float) (sign * 0.01f));
					bullets.addChild(balls[i].createSceneGraph());
					balls[i].setPlayer(player);
					cd = 240;
				}
				sign = sign * -1;
			}

			else if (getSc() == 2) {
				RollingBall[] balls = new RollingBall[24];
				for (int i = 0; i < 12; i++) {
					int color1 = (int) (Math.random() * 7);
					int color2 = (int) (Math.random() * 7);
					Appearance ap = new Appearance();
					ap.setTexture(texture);

					ap.setTextureAttributes(texAttr);
					balls[i] = new RollingBall(-0.1f, (float) (i * Math.PI / 7), color1, ap);
					balls[i + 12] = new RollingBall(0.1f, (float) (i * Math.PI / 7), color2, ap);
					balls[i].setSpeed(0.0015f, (float) (0.02f));
					balls[i + 12].setSpeed(0.0015f, (float) -1 * (0.02f));
					bullets.addChild(balls[i].createSceneGraph());
					bullets.addChild(balls[i + 12].createSceneGraph());
					balls[i].setPlayer(player);
					balls[i + 12].setPlayer(player);
					cd = 300;
				}
			}

			else if (getSc() == 5) {
				RollingBall[] balls = new RollingBall[24];
				int color1 = (int) (Math.random() * 7);
				int color2 = (int) (Math.random() * 7);
				for (int i = 0; i < 12; i++) {
					Appearance ap = new Appearance();
					ap.setTexture(texture);

					ap.setTextureAttributes(texAttr);
					balls[i] = new RollingBall(-0.1f, (float) (i * Math.PI / 12), color1, ap);
					balls[i + 12] = new RollingBall(-0.1f, (float) (-1 * i * Math.PI / 12), color2, ap);
					balls[i].setSpeed(0.0015f, (float) (0.02f));
					balls[i + 12].setSpeed(0.0015f, (float) -1 * (0.02f));
					bullets.addChild(balls[i].createSceneGraph());
					bullets.addChild(balls[i + 12].createSceneGraph());
					balls[i].setPlayer(player);
					balls[i + 12].setPlayer(player);
					cd = 240;
				}
			}

			else if (getSc() == 3) {
				RollingBall[] balls = new RollingBall[48];
				int color = (int) (Math.random() * 7);
				for (int i = 0; i < 48; i++) {
					Appearance ap = new Appearance();
					ap.setTexture(texture);

					ap.setTextureAttributes(texAttr);
					balls[i] = new RollingBall(0.0f, (float) (i * Math.PI / 24), color, ap);
					balls[i].setSpeed(0.001f, (float) (sign * 0.01f));
					bullets.addChild(balls[i].createSceneGraph());
					balls[i].setPlayer(player);
					cd = 240;
				}
			}

			else if (getSc() == 4) {
				if (sign == 1) {
					RollingBall[] balls = new RollingBall[48];
					int color = (int) (Math.random() * 7);
					for (int i = 0; i < 12; i++) {
						Appearance ap = new Appearance();
						ap.setTexture(texture);

						ap.setTextureAttributes(texAttr);
						balls[i] = new RollingBall(0.1f, (float) (i * Math.PI / 6), color, ap);
						balls[i].setSpeed(0.010f, 0.0f);
						balls[i + 12] = new RollingBall(0.1f, (float) (i * Math.PI / 6), color, ap);
						balls[i + 12].setSpeed(0.007f, 0.01f);
						balls[i + 24] = new RollingBall(0.1f, (float) (i * Math.PI / 6), color, ap);
						balls[i + 24].setSpeed(-0.007f, -0.01f);
						balls[i + 36] = new RollingBall(0.1f, (float) (i * Math.PI / 6), color, ap);
						balls[i + 36].setSpeed(-0.010f, -0.0f);

						bullets.addChild(balls[i].createSceneGraph());
						bullets.addChild(balls[i + 12].createSceneGraph());
						bullets.addChild(balls[i + 24].createSceneGraph());
						bullets.addChild(balls[i + 36].createSceneGraph());
						balls[i].setPlayer(player);
						balls[i + 12].setPlayer(player);
						balls[i + 24].setPlayer(player);
						balls[i + 36].setPlayer(player);
					}
				}

				else {
					RollingBall[] balls = new RollingBall[48];
					int color = (int) (Math.random() * 7);
					for (int i = 0; i < 48; i++) {
						Appearance ap = new Appearance();
						ap.setTexture(texture);

						ap.setTextureAttributes(texAttr);
						balls[i] = new RollingBall(0.0f, (float) (i * Math.PI / 24), color, ap);
						balls[i].setSpeed(0.003f, (float) (sign * 0.01f));
						bullets.addChild(balls[i].createSceneGraph());
						balls[i].setPlayer(player);
					}
				}
				sign *= -1;
				cd = 120;
			}
		} else {
			cd = cd - 1;
		}
	}

	protected float rate() {
		// TODO Auto-generated method stub
		if (hitPoint > 0)
			return (float) hitPoint / MAX_HP;
		else
			return 0;
	}

	@Override
	protected void next() {
		// TODO Auto-generated method stub
		setSc(getSc() + 1);
		hitPoint = MAX_HP;
	}

	public int getSc() {
		return sc;
	}

	public void setSc(int sc) {
		this.sc = sc;
	}
}
