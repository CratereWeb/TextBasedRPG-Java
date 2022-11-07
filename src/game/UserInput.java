package game;
import java.util.Scanner;

public class UserInput {
	
	Scanner scanner;
	String command;
	String [] params;
	
	public UserInput(Scanner scanner, String command, String[] params) {
		this.scanner = scanner;
		this.command = command;
		this.params = params;
	}
	
	
}
