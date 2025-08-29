package p1;

public class BallA extends Ball implements RollAble{
	public BallA(String mark) {
		super(mark);
	}

	public void inflate(double volume) {
		this.volume = volume;
	}
	
	@Override
	public void roll() {
		System.out.println(super.getTreadMark() + " rolls rather smoothly.\n");
	}
}
