package p1;

public abstract class Ball {
	private String treadMark;
	protected double volume;
	
	public abstract void inflate(double volume);
	
	public Ball (String mark) {
		this.treadMark = mark;
	}
	
	public String getTreadMark() {
		return this.treadMark; 
	}
	
	public double getVolume() {
		return volume;
	}
}
