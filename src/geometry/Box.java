package geometry;

import extras.Vector;

public class Box implements Geometry{
	private Vector center;
	private double length, width, height;
	
	public Box(Vector center, double length, double width, double height) {
		this.center = center;
		this.length = length;
		this.width = width;
		this.height = height;
	}

	@Override
	public float DE(Vector p) {
		Vector d = Vector.sub(Vector.abs(p), Vector.add(b(), center));
	    return (float) (Math.max(d.length(), 0) + Math.min(Math.max(d.x, Math.max(d.y, d.z)),0));
	}
	
	private Vector b() {
		return new Vector(length, width, height);
	}
}
