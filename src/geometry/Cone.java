package geometry;

import extras.Vector;

public class Cone implements Geometry{
	private Vector position, size;
	
	public Cone(Vector position, double base, double wtf, double height) {
		this.position = position;
		this.size = new Vector(base, wtf, height);
	}

	@Override
	public float DE(Vector p) {
		Vector diff = Vector.sub(p, position);
		Vector q = new Vector(Vector.length(diff.xz()), diff.y);
	    float d1 = (float) (-q.y-size.z);
	    float d2 = (float) Math.max(Vector.dot(q,size.xy()), q.y);
	    return (float) (Math.max(new Vector(d1,d2).length(), 0.0) + Math.min(Math.max(d1,d2), 0.));
	}

}
