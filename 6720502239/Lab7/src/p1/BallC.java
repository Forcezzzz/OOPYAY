package p1;

public class BallC extends BallA{

	public BallC(String mark) {
		super(mark);
	}

	@Override
	public void roll() {
		System.out.println(super.getTreadMark() + " rolls very smoothly.");
	}
}
