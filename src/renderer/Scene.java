package renderer;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import extras.Vector;
import geometry.Geometry;

public class Scene{

	public static final float SURFACE = 0;
	
	private int WIDTH, HEIGHT;
	private ArrayList<Geometry> objects;
	private ArrayList<Light> lights;
	private Camera cameras[];
	
	public Scene() {
		objects = new ArrayList<Geometry>();
		cameras = new Camera[1];
		
		cameras[0] = new Camera(WIDTH, HEIGHT, new Vector(0, 1, 0));
	}
	
	public Scene(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;

		objects = new ArrayList<Geometry>();
		lights = new ArrayList<Light>();
		cameras = new Camera[1];
		
		cameras[0] = new Camera(WIDTH, HEIGHT, new Vector(0, 1, 0));
	}
	
	public Scene(int WIDTH, int HEIGHT, Geometry[] objects) {		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.objects = new ArrayList<Geometry>(Arrays.asList(objects));
		lights = new ArrayList<Light>();
		cameras = new Camera[1];
		
		cameras[0] = new Camera(WIDTH, HEIGHT, new Vector(0, 1, 0));
	}
	
	public void render(Graphics g) {		
		cameras[0].render(this, g);
	}
	
	public float getDist(Vector p) {
		float min = Float.MAX_VALUE;
		float d = 0;
		
		for(Geometry g: objects) {
			d = g.DE(p);
			
			if(d < min) {
				min = d;
			}
		}
		
		float planeDist = (float) p.y;
	    
	    return Math.min(d, planeDist);
	}
	
	public float getLight(Vector p) {
		double sum = 0;
		
		for(Light l: lights)
			sum += l.getLight(this, p);
		
		return (float)(sum/(float)lights.size());
	}
	
	public Vector getNormal(Vector p) {
		float d = getDist(p);
	    Vector e = new Vector(.01, 0);
	    
	    Vector n = new Vector(
	        getDist(Vector.sub(p, e.xyy())),
	        getDist(Vector.sub(p, e.yxy())),
	        getDist(Vector.sub(p, e.yyx())));
	    
	    n.sub(d);
	        
	    return Vector.normalize(n);
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}
	
	public void addLight(Light l) {
		if (l != null)
			lights.add(l);
	}

	public void addObject(Geometry g) {
		if(objects == null) {
			System.err.println("objects array is null");
			
		}
		
		if(g == null) {
			System.err.println("objects array is null");
		}
		
		objects.add(g);
	}
	
	public boolean deleteObject(Geometry g) {
		if(objects.indexOf(g) == -1)
			return false;
		
		objects.remove(g);
		return true;
	}
}
