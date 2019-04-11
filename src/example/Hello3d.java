package example;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Geometry;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Hello3d {

public Hello3d()

{

   SimpleUniverse universe = new SimpleUniverse();

   BranchGroup group = new BranchGroup();
   
   ColorCube cb = new ColorCube(0.3);
   
   //cb.setAppearance(new Appearance());

   group.addChild(cb);

   universe.getViewingPlatform().setNominalViewingTransform();

   universe.addBranchGraph(group);

}

public static void main( String[] args ) {

   System.setProperty("sun.awt.noerasebackground", "true");

   new Hello3d();

}

} // end of class Hello3d