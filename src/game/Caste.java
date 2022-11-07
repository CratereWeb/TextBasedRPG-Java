package game;

public class Caste {
	
	protected int id;
	protected String name;
	protected StatsTable baseStats;
	
	public Caste(int id, String name, StatsTable baseStats) {
		this.id = id;
		this.name = name;
		this.baseStats = baseStats;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getIdToString() {
		return Integer.toString(this.id);
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void setBaseStat(String key, int value) {
		this.baseStats.set(key, value);
	}

	public StatsTable getBaseStats() {
		return this.baseStats;
	}
	
	public void displayBaseStats() {
		this.baseStats.displayTable();
	}
	
}
