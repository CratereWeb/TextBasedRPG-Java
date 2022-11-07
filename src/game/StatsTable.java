package game;
import java.util.LinkedHashMap;


public class StatsTable {
	
	LinkedHashMap<String, Integer> statsTable = new LinkedHashMap<String, Integer>();
	
	public StatsTable(LinkedHashMap<String, Integer> statsTable) {
		this.statsTable = statsTable;
	}
	
	
	public LinkedHashMap<String, Integer> getTable() {
		return this.statsTable;
	}
	
	public void displayTable() {
		System.out.println(this.statsTable);
	}
	
	
	public Integer get(String key) {
		return this.statsTable.get(key);
	}
	
	public void set(String key, Integer value) {
		
		if(this.statsTable.containsKey(key)) {
			this.statsTable.replace(key, value);
		} else {			
			this.statsTable.put(key, value);
		}
	}
	
	public void increase(String key, Integer increment) {
		int initialValue = this.statsTable.get(key);
		this.statsTable.replace(key, initialValue + increment);
	}
	
	public void decrease(String key, Integer decrement) {
		int initialValue = this.statsTable.get(key);
		this.statsTable.replace(key, initialValue - decrement);
	}
	public void maxHeal() {
		this.statsTable.replace("PV", 100);
	}

}
