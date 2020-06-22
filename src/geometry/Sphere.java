package geometry;

import extras.Vector;

public class Sphere implements Geometry{
	private double radius;
	private Vector center;
	
	public Sphere(Vector center, double radius) {
		this.center = center.copy();
		this.radius = radius;
	}

	@Override
	public float DE(Vector p) {
		return (float) (Vector.length(Vector.sub(p, center))-radius);
	}
	
	float dir = 1;
	public void bounce(float min, float max) {
		if(center.x <= min || center.x >= max)
			dir *= -1;
		
		center.x += dir;
	}
}
