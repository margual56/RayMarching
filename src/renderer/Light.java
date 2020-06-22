package renderer;

import app.Canvas;
import extras.Vector;

public class Light {
	@SuppressWarnings("unused")
	private Vector position, rotation, initialPosition;
	public static long iTime = 0;
	
	public Light(Vector position, Vector rotation) {
		this.position = position.copy();
		this.initialPosition = position.copy();
		this.rotation = rotation.copy();
	}

	public Light(Vector position) {
		this.position = position.copy();
		this.initialPosition = position.copy();
		this.rotation = new Vector(0);
	}

	float getLight(Scene s, Vector p) {
		position.x = initialPosition.x + Math.sin(iTime)*10;
		position.y = initialPosition.y - Math.cos(iTime)*20.;
		
		Vector l = Vector.normalize(Vector.sub(position, p));
		Vector n = s.getNormal(p);

		float dif = Canvas.clamp((float) Vector.dot(n, l), 0f, 1f);
		float d = Camera.rayMarch(s, Vector.add(p, Vector.mult(n, Camera.SURF_DIST * 2f)), l);

		if (d < Vector.length(Vector.sub(position, p)))
			dif *= .5f;
		
		return dif;
	}
}
