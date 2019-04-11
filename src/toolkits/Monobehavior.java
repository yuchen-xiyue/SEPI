package toolkits;

import javax.swing.JComponent;

/**
 * Basic Executing events for any object in that game; 
 * @author chens
 * @version 1.0.0
 * */

public abstract class Monobehavior extends JComponent{
	private Transform transform;
	
	public void awake() {}
	
	public void update() {}
	public void fixedUpdate() {
		//execute per page
	}
	
	public void onDestroy() {}
	public void onEnter() {}
	public void onEnable() {}
	public void onDisable() {}
	
	public void onCollisionEnter() {}
	public void onCollisionStay() {}
	public void onCollisionExit() {}
	
	public void onTriggerEenter() {}
	public void onTriggerStay() {}
	public void onTriggerExit() {}
	
	public void onMouseEnter() {}
	public void onMouseExit() {}
	public void onMouseDown() {}
	public void onMouseUp() {}
	public void onMouseDrag() {}
	
}
