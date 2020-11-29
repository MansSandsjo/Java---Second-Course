package mountain;

public class Side {
	private Point p1;
	private Point p2;
	
	public Side(Point p1, Point p2) {
		this.setP1(p1);
		this.setP2(p2);
		
	}
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Side){
			Side s = (Side) obj;
			return (s.p1 == p1 && s.p2 == p2 || s.p1 == p2 && s.p2 == p1);
		}
		return false;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}
	

}
