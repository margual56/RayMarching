package renderer;

import java.awt.Graphics;

import extras.Vector;

public class Camera{

	public static final int MAX_STEPS = 1000;
	public static final float MAX_DIST = 10000.f;
	public static final float SURF_DIST = .001f;

	public static final float FOV = (float) Math.toRadians(60);
	public static final Vector w = new Vector(0, 1, 0); // Global up vector

	private int WIDTH, HEIGHT;
	private Vector position, lookAt;
	private Vector iResolution;

	public Camera() {
		this.WIDTH = 500;
		this.HEIGHT = 500;
		this.position = new Vector(0);
		this.lookAt = new Vector(1, 0, 0);
		this.iResolution = new Vector(WIDTH, HEIGHT);
	}

	public Camera(int WIDTH, int HEIGHT, Vector position, Vector lookAt) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.position = position.copy();
		this.lookAt = lookAt;
		this.iResolution = new Vector(WIDTH, HEIGHT);
	}

	public Camera(int WIDTH, int HEIGHT, Vector position) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.position = position.copy();
		this.lookAt = null;
		this.iResolution = new Vector(WIDTH, HEIGHT);
	}

	public void render(Scene s, Graphics g) {
		float d = 0;
		Vector uv, col, rd;

		for (float Px = 0; Px < WIDTH; Px++) {
			for (float Py = 0; Py < HEIGHT; Py++) {
				//getRay(Px, Py);

				uv = Vector.div(Vector.sub(new Vector(Px, Py), Vector.mult(iResolution, 0.5)), iResolution.y);
			    col = new Vector(0);
			    
			    rd = Vector.normalize(new Vector(uv.x, uv.y, 1));

			    d = rayMarch(s, rd);
			    
			    Vector p = Vector.add(position, Vector.mult(rd, d));
			    
			    float dif = s.getLight(p);
			    col = new Vector(dif);
			    Vector.map(col, 0, WIDTH*HEIGHT, 1, 0);
			    
			    g.setColor(col.toColor());
			    g.fillRect(WIDTH-(int)Px, HEIGHT-(int)Py, 1, 1);
			}
		}
	}

	@SuppressWarnings("unused")
	private Vector getRay(float Px, float Py) {
		///////////////////////////
		// WAY 1
		///////////////////////////
		/*
		 * float zoom = 1; 
		 * Vector f = Vector.normalize(Vector.sub(lookAt, position));
		 * Vector r = Vector.cross(new Vector(0.0, 1.0, 0.0), f); 
		 * Vector u = Vector.cross(f, r); 
		 * Vector c = Vector.add(position, Vector.mult(f, zoom));
		 * Vector i = Vector.add(c, Vector.add(Vector.mult(r, UV.x), Vector.mult(u, UV.y))); 
		 * Vector dir = Vector.sub(i, position); return new Ray(position, Vector.normalize(dir));
		 */

		///////////////////////////
		// WAY 2
		// https://computergraphics.stackexchange.com/questions/8479/how-to-calculate-ray
		///////////////////////////
		/*
		 * eye position 		-> E 	-> position
		 * target position 		-> T 	-> lookAt
		 * field of view (angle, for human eye 90� ) -> FOV
		 * number of square pixels k (horizontal direction) and m (vertical direction) -> i,j
		 * processed pixel indexes where 1<=i<=k and 1<=j<=m 
		 * we also know vertical w vector usually equal to w=[wx, wy, wz]=[0,1,0] which indicate where is up and where is down
		 * 
		 * The orthogonal vectors v and b on picture are determined by w
		 * t = T-E
		 * The d and pixel size is arbitrary and don't change the result because of fixed FOV.
		 */

		/*
		 * The Z coordinate should be negative or positive depending on whether you are using a right handed or left handed coordinate system. 
		 * Px and Py are pixel coordinates offset by 0.5 to get the center of the pixel.
		 */

		// d=1/tan(FOV/2))
		double d = 1 / Math.tan(FOV / 2.0);

		// Px=Px+0.5
		Px += 0.5;

		// Py=Py+0.5
		Py += 0.5;

		// aspect_ratio=width/height
		float aspect_ratio = WIDTH / HEIGHT;

		Vector dir = new Vector();

		// ray.dir.x=aspect_ratio*(2*Px/width)-1
		dir.x = aspect_ratio * (2 * Px / WIDTH) - 1;

		// ray.dir.y=(2*Py/height)-1
		dir.y = (2 * Py / HEIGHT) - 1;

		// ray.dir.z=d
		dir.z = d;

		return dir;
	}

	private float rayMarch(Scene s, Vector direction) {
		float dO = 0, dS = 0;
		Vector p = null;

		for (int i = 0; i < MAX_STEPS; i++) {
			p = Vector.add(position, Vector.mult(direction, dO));
			dS = s.getDist(p);
			dO += dS;
			if (dO > MAX_DIST || dS < SURF_DIST)
				break;
		}

		return dO;
	}
	
	public static float rayMarch(Scene s, Vector pos, Vector direction) {
		float dO = 0, dS = 0;
		Vector p = null;

		for (int i = 0; i < MAX_STEPS; i++) {
			p = Vector.add(pos, Vector.mult(direction, dO));
			dS = s.getDist(p);
			dO += dS;
			if (dO > MAX_DIST || dS < SURF_DIST)
				break;
		}

		return dO;
	}

	public Vector up() {
		return Vector.cross(lookAt, right());
	}

	public Vector right() {
		return Vector.cross(w, lookAt);
	}
}
