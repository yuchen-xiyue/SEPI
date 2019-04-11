package toolkits;

import java.awt.Graphics;
import java.util.LinkedList;

public class Sprite extends Monobehavior{
	protected Transform transform;//Basic physical toolkits 
	private int scale;
	private int id;
	protected Sprite parent;//parent sprite
	protected LinkedList<Sprite> children;//children sprites 
	
	public Sprite() {
		//Conductor
		parent = null;
		transform = new Transform();
		children = new LinkedList<Sprite>();
	}
	
	public Sprite addChild(Sprite s) {
		//add a child sprite to children list
		children.add(s);
		s.setParent(this);
		
		return this;
	}
	
	public Sprite setParent(Sprite s) {
		//set s to be the parent sprite of this sprite; 
		parent = s;
		return this;
	}
	
	public void deleteChild(Sprite s) {
		//delete a child sprite
		children.remove(s);
	}
	
	public void deleteWithChildren() {
		//delete this sprite and all child sprite
		parent.deleteChild(this);
	}
	
	public void deleteWithoutChildren() {
		//delete this sprite and give all its child to the super sprite;  
		for(Sprite s : children)	parent.addChild(s);
		parent.deleteChild(this);
	}
	
	
	protected void draw(Graphics g) {
		//Travel all its children sprite and draw
		for(Sprite s : children)	s.draw(g);
	}
	
	public void colision(Sprite p) {
	}
}
