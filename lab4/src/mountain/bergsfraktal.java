package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class bergsfraktal extends Fractal {
	int x;
	int y;
	private Point p1;
	private Point p2;
	private Point p3;
	private HashMap<Side, Point> mappen;
	private double dev;

	public bergsfraktal(Point p1, Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
		mappen = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		return "mountain";
	}

	public Point midPoint(Point p1, Point p2, double dev) {
		dev = RandomUtilities.randFunc(20);
		int X1 = (p1.getX() + p2.getX()) / 2;
		int Y1 = (p1.getY() + p2.getY() + (int) dev) / 2;
		Point midP = new Point(X1, Y1);
		return midP;
	}

	public Point getMidPoint(Point d, Point e, double dev) {
		Side s1 = new Side(d, e);
		Point m = midPoint(d, e, dev);
		if(mappen.containsKey(s1)){
			Point m1 = mappen.get(s1);
			mappen.remove(s1);
			return m1;
		}else{
			mappen.put(s1, m);
		return mappen.get(s1);}
//		if(mappen.putIfAbsent(s1, m)==null){
//			return m;
//		}else{
//			return mappen.putIfAbsent(s1, m);
//		}
//		
		
//		if (m != null) {
//			mappen.remove(s1);
//		}
//
//		else {
//			m = midPoint(d, e, dev);
//			mappen.put(s1, m);
//		}
//		return m;
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, p1, p2, p3);
	}

	private void fractalLine(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());

		} else {
			Point a = getMidPoint(p1, p2, dev / 2);
			Point b = getMidPoint(p2, p3, dev / 2);
			Point c = getMidPoint(p3, p1, dev / 2);

			fractalLine(turtle, order - 1, a, b, c);
			fractalLine(turtle, order - 1, p1, c, a);
			fractalLine(turtle, order - 1, a, b, p2);
			fractalLine(turtle, order - 1, c, b, p3);

		}
	}
}
