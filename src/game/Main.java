package game;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Random;

public class Main {
	
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static final String ANSI_BLACK_BG = "\u001B[40m";
	public static final String ANSI_RED_BG = "\u001B[41m";
	public static final String ANSI_GREEN_BG = "\u001B[42m";
	public static final String ANSI_YELLOW_BG = "\u001B[43m";
	public static final String ANSI_BLUE_BG = "\u001B[44m";
	public static final String ANSI_PURPLE_BG = "\u001B[45m";
	public static final String ANSI_CYAN_BG = "\u001B[46m";
	public static final String ANSI_WHITE_BG = "\u001B[47m";

	
	public static void main(String[] args) {

		// Initialisation du scanner d'entrée utilisateur
		Scanner scanner = new Scanner(System.in);
		
		
		// Initialisation de toutes les castes et de leurs stats de base
		LinkedHashMap<Integer, Caste> castes = initCastes();

		// Menu de création du personnage
		println("Bienvenue!");
		println("Donnez un nom à votre personnage :");
		println("");
		
		// Choix du nom
		String userInput = scanner.nextLine();
		String characterName = userInput;
		
		PlayableCharacter player = new PlayableCharacter(characterName, 1, initDefaultStats(), new Caste(0, null, initDefaultStats()), new int[]{12, 33});
		
		
		println("");
		println("Hello " + ANSI_YELLOW + player.getName() + ANSI_RESET);
		println("");
		
		// Choix de la classe
		println("Choisissez votre classe :");
		castes.forEach((key, caste) -> println(key + " - " + caste.getName()));
		println("");
		println("INFO <id> pour voir les stats de base de la classe");
		println("CHOOSE <id> pour choisir cette classe");
		println("");
		
				
		boolean isCasteChosen = false;
		
		do {
			
			userInput = scanner.nextLine();
			String[] input = userInput.split(" ");
			String command = input[0];
			int param = 0;
			
			
			if(hasParams(input)) {		
				
				param = Integer.parseInt(input[1]);
				
				if(command.contains("info") || command.contains("INFO") ) {
					
					println("");	
					displayCasteInfo(castes, param);
						
					
				} else if (command.contains("choose") || command.contains("CHOOSE") ) {
					
					println("");
					player.setCaste(chooseCaste(castes, param));
					player.setAllStats(chooseCaste(castes, param).getBaseStats());
					println("Vous avez choisi la classe "+ ANSI_CYAN + player.getCaste().getName() + ANSI_RESET);
					println("Pour confirmer ce choix, entrez Y. \nSinon, renseignez vous sur les autres classes avec INFO <id> ou choisissez-en une avec CHOOSE <id>");
					
					userInput = scanner.nextLine();
					if(userInput.equals("y") || userInput.equals("Y")) {
						isCasteChosen = true;
						println(ANSI_YELLOW + player.getName() + ANSI_RESET + " est désormais un " + ANSI_CYAN + player.getCaste().getName() + ANSI_RESET + " !");
					}
						
				}
				
			} else {
				println("Spécifiez une classe par son id dans la liste.");
			}
			
				
		} while (isCasteChosen == false);

		println("");
		println("À tout moment, vous pouvez consultez les stats de votre personnage avec la commande ME");
		
		userInput = scanner.nextLine();
		String[] input = userInput.split(" ");
		String command = input[0];
		
		
		if(command.equals("me")) { player.displayInfo(); }
		
		
		GameMap gameMap = new GameMap(new char[55][36], new char[55][36]);
		gameMap.initMap();
		gameMap.displayBlackMap(player.getLocation());
		gameMap.displayBlackMapLegend();
		gameMap.displayLevelMap(player.getLocation());
		
		// Navigation
		boolean pursueNavigation = true;
		do {

			char direction = navigationToDirection(scanner);
			
			if(gameMap.isLocationAccessible(player.previewNextLocation(direction))) {
				player.navigate(direction);				
			}
			
			
			if(isThereAMob()) {
				String[] mobs = new String[]{"Zombie", "Loup", "Ours", "Chasseur Zombie", "Vampire", "Fantôme", "Serpent"};
				//Mob mob = new Mob(1, "Zombie");
			}
			if(isThereAnObject()){
				
			}

			gameMap.displayBlackMap(player.getLocation());

			System.out.println();
			
		} while (pursueNavigation = true);
		
		
		// Fermeture du scanner d'entrée utilisateur
		scanner.close();
		
	}
	
	
	// METHODES UTILES DANS LA BOUCLE DE JEU
	
	// Initialisation des castes
	private static LinkedHashMap<Integer, Caste> initCastes() {
		
		LinkedHashMap<Integer, Caste> castes = new LinkedHashMap<Integer, Caste>();
		
		Caste guerrier = new Caste(1, "Guerrier", new StatsTable(new LinkedHashMap<String, Integer>()));
		Caste mage = new Caste(2, "Voleur", new StatsTable(new LinkedHashMap<String, Integer>()));
		Caste voleur = new Caste(3, "Mage", new StatsTable(new LinkedHashMap<String, Integer>()));
		
		guerrier.setBaseStat("PV", 50);
		guerrier.setBaseStat("PA", 100);
		guerrier.setBaseStat("FOR", 4);
		guerrier.setBaseStat("DEX", 2);
		guerrier.setBaseStat("CON", 4);
		guerrier.setBaseStat("INT", 0);
		guerrier.setBaseStat("SAG", 0);
		guerrier.setBaseStat("CHA", 1);

		mage.setBaseStat("PV", 50);
		mage.setBaseStat("PA", 100);
		mage.setBaseStat("FOR", 1);
		mage.setBaseStat("DEX", 1);
		mage.setBaseStat("CON", 1);
		mage.setBaseStat("INT", 4);
		mage.setBaseStat("SAG", 3);
		mage.setBaseStat("CHA", 2);

		voleur.setBaseStat("PV", 50);
		voleur.setBaseStat("PA", 100);
		voleur.setBaseStat("FOR", 2);
		voleur.setBaseStat("DEX", 5);
		voleur.setBaseStat("CON", 2);
		voleur.setBaseStat("INT", 0);
		voleur.setBaseStat("SAG", 0);
		voleur.setBaseStat("CHA", 3);
		
		castes.put(1, guerrier);
		castes.put(2, mage);
		castes.put(3, voleur);


		return castes;
		
	}
	
	
	// Affichage des stats de base d'une caste
	private static void displayCasteInfo(LinkedHashMap<Integer, Caste> castes, int param) {
				
		if(param <= castes.size() && param > 0) {
			
			castes.forEach( (key, caste) -> {

				if(caste.getId() == param) {
										
					println("Statistiques de la classe "+ ANSI_CYAN + caste.getName() + ANSI_RESET);
					caste.displayBaseStats();
					println("Pour choisir la classe " + caste.getName() + ", tapez `choose " + caste.getId() + "`");
					
				}

			});
			
		} else {
			println("Aucune classe ne correspond à l'id " + param);
		}
		
	}
	
	// Assigner au personnage la caste choisie par l'utilisateur
	private static Caste chooseCaste(LinkedHashMap<Integer, Caste> castes, int casteID) {
		return castes.get(casteID);
	}
	
	
	// Initialiser les stats par défaut du personnage
	private static StatsTable initDefaultStats() {
		
		StatsTable defaultStats = new StatsTable(new LinkedHashMap<String, Integer>());	
		
		defaultStats.set("PV", 100);
		defaultStats.set("PA", 100);
		defaultStats.set("FOR", 0);
		defaultStats.set("DEX", 0);
		defaultStats.set("CON", 0);
		defaultStats.set("INT", 0);
		defaultStats.set("SAG", 0);
		defaultStats.set("CHA", 0);

		return defaultStats;
	}
	
	
	// La commande saisie par l'utilisateur contient-t-elle des paramètres ?
	private static boolean hasParams(String[] input) {
		if(input.length > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	// Imprimer une ligne dans la console
	public static void println(String string) {
		System.out.println(string);
	}
	
	
	
	// Activer les commandes utilisateur
	public void allowCommands(Scanner scanner) {
		
		String userInput = scanner.nextLine();
		String[] input = userInput.split(" ");
		String command = input[0];
		String param = "";
		if(input.length > 1) {
			param = input[1];
		}

		
		switch(command) {
			case "fight": {
				switch(param) {
					
				}
			}
		}
	}
	
	
	// Navigation du personnage dans une direction
	public static char navigationToDirection(Scanner scanner) {
		
		char direction = ' ';
		
		String userInput = scanner.nextLine();
		String[] input = userInput.split(" ");
		String command = input[0];
		
		switch(command) {
			case "E":
			case "EAST":
			case "e":
			case "east": System.out.println("Vous allez vers l'EST..."); direction = 'E';
			break;
			case "W":
			case "WEST":
			case "w":
			case "west": System.out.println("Vous allez vers l'OUEST..."); direction = 'W';
			break;
			case "N":
			case "NORTH":
			case "n":
			case "north": System.out.println("Vous allez vers le NORD..."); direction = 'N';
			break;
			case "S":
			case "SOUTH":
			case "s":
			case "south": System.out.println("Vous allez vers le SUD..."); direction = 'S';
			break;
			default: System.out.println();	
		}
		
		return direction;
		
	}
	
	// Déterminer si un mob est là
	public static boolean isThereAMob() {
		
		double random;
		random = Math.ceil(Math.random() * 5);
		System.out.println(random);
		
		if(random > 5) {
			System.out.println("Il y a un MONSTRE ici !");
			return true;
		} else {
			System.out.println("Il n'y a aucun monstre ici...");
			return false;
		}
		
	}
	
	public static boolean isThereAnObject() {
		
		double random;
		random = Math.ceil(Math.random() * 8);
		System.out.println(random);
		
		if(random > 7) {
			System.out.println("Il y a un OBJET ici !");
			return true;
		} else {
			System.out.println("Il n'y a aucun objet ici...");
			return false;
		}
		
	}
	
	

}
