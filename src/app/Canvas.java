package app;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import extras.Vector;
import geometry.*;
import renderer.Light;
import renderer.Scene;

public class Canvas extends JPanel {
	private static final long serialVersionUID = -2567278654860323563L;

	public static final int WIDTH = 1500, HEIGHT = 1000;

	Scene scene;
	Capsule sphere;
	Light light, light2;

	public void init() {
		scene = new Scene(WIDTH, HEIGHT);

		sphere = new Capsule(new Vector(0, 1.4, 3), new Vector(0, 0.6, 3), 0.5);
		//sphere = new Sphere(new Vector(0, 1, 3), 0.5);
		
		//sphere = new Torus(new Vector(0, 4, 10), 5, 0.1);
		
		//sphere = new Cone(new Vector( 0.0, 1, 4), 0.8, 0.5, 2);
		scene.addObject(sphere);
		
		light = new Light(new Vector(-10, 10, 10));

		scene.addLight(light);
		//scene.addLight(light2);

		setBackground(new Color(41, 41, 41));
		setSize(WIDTH, HEIGHT);

		Thread t = new Thread(() -> {
			Light.iTime++;
			repaint();
		});

		t.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		scene.render(g);
	}

	public static float map(float x, float minx, float maxx, float miny, float maxy) {
		return (x - maxx) / (maxx - minx) * (maxy - miny) + miny;
	}

	public static float clamp(float x, float min, float max) {
		if (x < min)
			return min;

		if (x > max)
			return max;

		return x;
	}
}
