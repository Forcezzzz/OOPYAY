package p1;

public class BallB extends BallA{

	public BallB(String mark) {
		super(mark);
	}
	
	@Override
	public void roll() {
		System.out.println(super.getTreadMark() + " rolls smoothly.\n");
	}
	
	
}
