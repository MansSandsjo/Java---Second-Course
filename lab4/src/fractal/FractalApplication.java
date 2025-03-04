package fractal;

import koch.Koch;
import mountain.Point;
import mountain.bergsfraktal;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		
		Point p1 = new Point(100,500);
		Point p2 = new Point(500,600);
		Point p3 = new Point (300, 10);
		bergsfraktal b = new bergsfraktal(p1, p2, p3, 20.0);
		fractals[0]=b;
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
