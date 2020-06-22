package renderer;

import geometry.Geometry;

public class Collision {
	public float distance;
	public Geometry other;
	
	public Collision(float distance, Geometry other) {
		this.distance = distance;
		this.other = other;
	}
}
