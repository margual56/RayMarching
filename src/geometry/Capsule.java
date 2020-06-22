package geometry;

import extras.Vector;

public class Capsule implements Geometry {
	private Vector a, b;
	private double radius;
	
	public Capsule(Vector a, Vector b, double radius) {
		super();
		this.a = a;
		this.b = b;
		this.radius = radius;
	}

	@Override
	public float DE(Vector p) {
		Vector pa = Vector.sub(p, a);
		Vector ba = Vector.sub(b, a);
		
		float h = app.Canvas.clamp((float)(Vector.dot(pa,ba)/Vector.dot(ba,ba)), 0.0f, 1.0f);
		return (float) (Vector.length(Vector.sub(pa, Vector.mult(ba, h))) - radius);
	}
	
	
}
