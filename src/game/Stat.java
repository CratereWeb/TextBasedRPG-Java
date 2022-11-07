package game;

public class Stat {
	
	private String name;
	private int value;
	
	public Stat(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String get() {
		return this.name + " : " + this.value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return this.value;
	}
}
