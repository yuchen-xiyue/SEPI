package example;


import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Sphere;

import javax.media.j3d.BranchGroup;

import javax.media.j3d.Canvas3D;

import java.awt.GraphicsConfiguration;

import java.awt.BorderLayout;

import java.awt.Label;

import java.applet.Applet;

import com.sun.j3d.utils.applet.MainFrame;

public class CanvasDemo extends Applet {

public CanvasDemo() {


   setLayout(new BorderLayout());

   GraphicsConfiguration config =

   SimpleUniverse.getPreferredConfiguration();

   Canvas3D canvas = new Canvas3D(config);

   add("North",new Label("This is the top"));

   add("Center", canvas);

   add("South",new Label("This is the bottom"));

   BranchGroup contents = new BranchGroup();

   contents.addChild(new ColorCube(0.3));
   //contents.addChild(new Sphere(0.1f));

   SimpleUniverse universe = new SimpleUniverse(canvas);

   universe.getViewingPlatform().setNominalViewingTransform();

   universe.addBranchGraph(contents);

}

public static void main( String[] args ) {

   System.setProperty("sun.awt.noerasebackground", "true");

   CanvasDemo demo = new CanvasDemo();

   new MainFrame(demo,600,400);

}

}
