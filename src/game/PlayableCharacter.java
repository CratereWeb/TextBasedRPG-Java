package game;



public class PlayableCharacter extends Character {
	
	private StatsTable stats;
	private Caste caste;
	private int[] location = new int[2];
	
	public PlayableCharacter(String name, int level, StatsTable stats, Caste caste, int[] location) {
		super(name, level);
		this.stats = stats;
		this.caste = caste;
		this.location = location;
	}
	
	public void setCaste(Caste caste) {
		this.caste = caste;
	}
	public Caste getCaste() {
		return this.caste;
	}
	
	public void setStat(String key, int value) {
		this.stats.set(key, value);
	}
	
	public void setAllStats(StatsTable stats) {
		this.stats = stats;
	}
	
	public void displayInfo() {
		System.out.println(this.getName() + ", " + this.caste.getName() + " de niveau " + this.level);
		this.stats.displayTable();
	}
	
	public int[] getLocation() {
		return this.location;
	}
	
	public void navigate(char direction) {
		
		switch(direction) {
			case 'E': this.location[0]++;
				break;
			case 'W': this.location[0]--;
				break;
			case 'N': this.location[1]--;
				break;
			case 'S': this.location[1]++;
				break;
		}
		
	}
	
	public int[] previewNextLocation(char direction) {
		
		int[] nextLocation = {0, 0};
		
		switch(direction) {
			case 'E': nextLocation[0] = this.location[0]+1; 	nextLocation[1] = this.location[1];
				break;
			case 'W': nextLocation[0] = this.location[0]-1; 	nextLocation[1] = this.location[1];
				break;
			case 'N': nextLocation[0] = this.location[0]; 		nextLocation[1] = this.location[1]-1;
				break;
			case 'S': nextLocation[0] = this.location[0]; 		nextLocation[1] = this.location[1]+1;
				break;
		}
		
		return nextLocation;

	}
}
