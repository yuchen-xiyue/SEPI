package zuma;

import java.applet.Applet;

import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedLineArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;

public class GeometryDrawing extends Shape3D {
	GeometryDrawing() {
		this.setGeometry(createGeometry());
	}
	private Geometry createGeometry() {
		// TODO Auto-generated method stub
		IndexedLineArray ila = new IndexedLineArray(18, GeometryArray.COORDINATES, 30);
		ila.setCoordinate(0, new Point3f(-0.5f, 0.0f, 0.0f)); 
		return null;
	}
}
