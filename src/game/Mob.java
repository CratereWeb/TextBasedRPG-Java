package game;

public class Mob extends Character {
	
	private StatsTable stats;
	
	public Mob(String name, int id, StatsTable stats) {
		super(name, id);
		this.stats = stats;
	}
	
	public StatsTable getStats() {
		return this.stats;
	}
	
	public void displayStats() {
		System.out.println(this.stats);
	}
	
	
}
