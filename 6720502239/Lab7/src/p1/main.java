package p1;

public class main {
	public static void main(String[] args) {
		Ball ballA = new BallA("Zentia");
		ballA.inflate(1.0);
		TestBall(ballA);
		Ball ballB = new BallB("Zentia");
		ballB.inflate(1.1);
		TestBall(ballB);
		Ball ballC = new BallC("Zentia");
		ballC.inflate(1.2);
		TestBall(ballC);
	}
	
	static void TestBall(Ball b) {
		System.out.println(b.getTreadMark() + " is a trademark of " + b.getClass().getSimpleName());
		System.out.println(b.getTreadMark() +"'s ball is inflated " + b.getVolume() + " cu.ft");
		RollAble other = (RollAble)b;
		other.roll();
	}
	
	
}

