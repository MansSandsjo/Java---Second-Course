package tester;

public class snowBalls {

	// public snowBalls() {

	public static int nbrSnowballs(int level) {
		if (level == 0) {
			return 0;
		} else {
			int counterLev = 1;
			return nbrSnowballs(level, 0, counterLev);
		}
	}

	private static int nbrSnowballs(int level, int counter, int counterLev) {
		counter++;
		int counterLevT = counter;
		counterLev = counterLevT * counterLevT + counterLev;

		if (level == counter) {
			return counterLev;
		} else
			return nbrSnowballs(level, counter, counterLev);
	}
	
	public static int nbrSnowballs1(int n) { 
		if (n == 1) return 1;
      else
return n*n + nbrSnowballs(n-1); }

	public static void main(String[] args) {
		System.out.println(snowBalls.nbrSnowballs(3));
		System.out.println(snowBalls.nbrSnowballs1(3));

	}
}
