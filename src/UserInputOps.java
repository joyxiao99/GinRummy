import java.util.Scanner;

/**
 * @brief Class designated for user input and operations.
 * @author Benson Hall
 *
 */
public class UserInputOps {
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Discard from hand and put on top of discard pile
	 * 
	 * @param discardPile - discard pile
	 */
	public static String chooseDiscard() {
		String discard = scanner.nextLine();
		return discard;
	}

	/**
	 * Close scanner
	 */
	public static void closeScanner() {
		scanner.close();
	}
	
	/**
	 * Print method for displaying user's options
	 */
	private static void displayChoices() {
		System.out.println("\nWhat would you like to do? Input 1-4 to make your choice.");
		System.out.println("1. Draw a card from the stock pile.");
		System.out.println("2. Draw the card from the discard pile.");
		System.out.println("3. Check melds.");
		System.out.println("4. Check deadwood score/attempt to knock.");
	}
	
	/**
	 * Does the user want to knock?
	 * 
	 * @return y if yes, n if no
	 */
	public static char knock() {
		char knock = scanner.nextLine().toLowerCase().charAt(0);
		while(knock != 'y' && knock != 'n') {
			System.out.println("Invalid input.\nWould you like to knock? (y/n)");
			knock = scanner.nextLine().toLowerCase().charAt(0);
		}
		return knock;
	}

	/**
	 * Does the user want to play a new game?
	 * 
	 * @return y if yes, n if no
	 */
	public static char playAgain() {
		char confirm = scanner.nextLine().toLowerCase().charAt(0);
		while (confirm != 'y' && confirm != 'n') {
			System.out.println("Invalid input.\nPlay again? (y/n)");
			confirm = scanner.nextLine().toLowerCase().charAt(0);
		}
		return confirm;
	}

	/**
	 * Player makes decisions
	 * 
	 * @param p1 - user player
	 * @return player's decision, integer between 1 and 4
	 */
	public static int playerDecision() {
		// choices
		displayChoices();
		int choice = Integer.parseInt(scanner.nextLine());
		// lock user and prevent them from advancing if input is incorrect
		while (choice < 1 || choice > 4) {
			System.out.println("Invalid input. Enter a value between 1 and 4.");
			choice = Integer.parseInt(scanner.nextLine());
		}
		return choice;
	}

	/**
	 * Get user to print name and return result
	 * 
	 * @return name - user's name
	 */
	public static String username() {
		System.out.println("Please enter your name:");
		String name = scanner.nextLine();
		return name;
	}
}
