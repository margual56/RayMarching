package geometry;

import extras.Vector;

public class Cylinder implements Geometry {
	private Vector a, b;
	private double radius;

	public Cylinder(Vector a, Vector b, double radius) {
		this.a = a;
		this.b = b;
		this.radius = radius;
	}

	@Override
	public float DE(Vector p) {
		Vector ba = Vector.sub(b, a);
		Vector pa = Vector.sub(p, a);
		double baba = Vector.dot(ba, ba);
		double paba = Vector.dot(pa, ba);
		double x = Vector.length(Vector.sub(Vector.mult(pa, baba), Vector.mult(ba, paba))) - radius * baba;
		double y = Math.abs(paba - baba * 0.5) - baba * 0.5;
		double x2 = x * x;
		double y2 = y * y * baba;
		double d = (Math.max(x, y) < 0.0) ? -Math.min(x2, y2) : (((x > 0.0) ? x2 : 0.0) + ((y > 0.0) ? y2 : 0.0));
		return (float) (Math.signum(d) * Math.sqrt(Math.abs(d)) / baba);
	}
}
