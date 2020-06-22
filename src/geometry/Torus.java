package geometry;

import extras.Vector;

public class Torus implements Geometry{
	private Vector center, radius;
	
	public Torus(Vector center, double innerRadius, double fillRadius) {
		this.center = center.copy();
		this.radius = new Vector(innerRadius, fillRadius);
	}
	
	@Override
	public float DE(Vector p) {
		Vector pp = Vector.sub(p, center);
		return (float) (Vector.length( new Vector(Vector.length(Vector.sub(pp.xz(), radius.x)),pp.y))-radius.y);
	}
}
