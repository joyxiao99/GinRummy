import java.util.ArrayList;
import java.util.Scanner;

public class UserInputOps {
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Close scanner
	 */
	public static void closeScanner() {
		scanner.close();
	}

	/**
	 * Discard from hand and put on top of discard pile
	 * 
	 * @param p1          - Player's information
	 * @param discardPile - discard pile
	 */
	public static void discardCard(Player p1, DiscardPile discardPile) {
		System.out.println("Choose a card to discard: ");
		for (Card c : p1.getHand()) {
			System.out.print(c.toString() + " ");
		}
		System.out.println("");
		String discard = scanner.nextLine();
		discardPile.push(p1.discardFromHand(discard));
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
	 * Does the user want to play a new game?
	 * 
	 * @return y if yes, n if no
	 */
	public static char playAgain() {
		char confirm = scanner.nextLine().toLowerCase().charAt(0);
		while (confirm != 'y' || confirm != 'n') {
			System.out.println("Play again? (y/n)");
			confirm = scanner.nextLine().toLowerCase().charAt(0);
		}
		return confirm;
	}

	/**
	 * Player makes decisions
	 * 
	 * @return true if the player knocks, false otherwise
	 */
	public static boolean playerDecision(Player p1, StockPile sp, DiscardPile dp) {
		// hand
		System.out.println("Your hand: ");
		for (Card c : p1.getHand()) {
			System.out.print(c.toString() + " ");
		}

		// check melds
		p1.checkMelds();
		// recalculate deadwood score
		p1.recalculateDeadwoodScore();

		// declare choice variable for do-while loop
		int choice;
		do {
			// choices
			displayChoices();
			choice = Integer.parseInt(scanner.nextLine());

			// lock user and prevent them from advancing if input is incorrect
			while (choice < 1 || choice > 4) {
				System.out.println("Invalid input. Enter a value between 1 and 4.");
				choice = Integer.parseInt(scanner.nextLine());
			}

			// choice check
			switch (choice) {
			case 1:
				// draw from stock pile and discard another
				GameOps.drawFromStockPile(p1, sp);
				discardCard(p1, dp);
				break;
			case 2:
				// draw from discard pile and discard another
				GameOps.drawFromDiscardPile(p1, dp);
				discardCard(p1, dp);
				break;
			case 3:
				// check melds
				ArrayList<ArrayList<Card>> melds = p1.getMelds();
				for (ArrayList<Card> meld : melds) {
					for (Card c : meld) {
						System.out.print(c.toString() + " ");
					}
					System.out.println();
				}
				break;
			case 4:
				// deadwood score - knock?
				System.out.println("Your current deadwood score: " + p1.getDeadwoodScore());
				if (p1.getDeadwoodScore() <= 10) {
					System.out.println("Would you like to knock? (y/n)");
					char knock = scanner.nextLine().toLowerCase().charAt(0);
					if (knock == 'y') {
						return true;
					}
				}
				break;
			}
		} while (choice == 3 || choice == 4);

		return false;
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
