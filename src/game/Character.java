package game;

public abstract class Character implements Creature {
	
	protected String name;
	protected int level;
	
	public Character (String name, int level) {
		this.name = name;
		this.level = level;
	}
	public String getName() {
		return this.name;
	};
	public int getLevel() {
		return this.level;
	}
	
}
