import java.util.ArrayList;
import java.util.Collections;

/**
 * @brief Class of operations for a game of Gin Rummy.
 * @author Benson Hall
 */
public class GameOps {

	/**
	 * Calculate score: The absolute value of the difference of the deadwood scores
	 * is calculated, and the points are awarded to the player with the lowest
	 * deadwood score. If any of the players have 0 deadwood points and knocks, they
	 * get an additional 20 points.
	 * 
	 * @param p1  - user player
	 * @param cpu - computer player
	 */
	public static void calculateScores(Player p1, Player cpu) {
		int playerScore = p1.getDeadwoodScore();
		int cpuScore = cpu.getDeadwoodScore();
		int difference = Math.abs(playerScore - cpuScore);
		if (playerScore < cpuScore) {
			p1.addToTotalScore(difference);
			// player goes gin
			if (playerScore == 0) {
				p1.addToTotalScore(20);
			}
		} else {
			cpu.addToTotalScore(difference);
			// computer goes gin
			if (cpuScore == 0) {
				cpu.addToTotalScore(20);
			}
		}
	}

	/**
	 * Player must choose a card to discard.
	 * 
	 * @param p1 - user
	 * @return user's card to discard
	 */
	private static String chooseDiscard(Player p1) {
		System.out.println("Choose a card to discard: ");
		// show hand for user to make decision
		p1.displayHand();
		System.out.println("");

		// instructions
		System.out.println("Helpful tip: Type the card rank, followed by the first letter representing the suit.");
		System.out.println("Example: For 9\u2660, type in 9s");
		// ask for user's input
		return UserInputOps.chooseDiscard();
	}

	/**
	 * Create initial stock pile and add cards to it
	 * 
	 * @return new stock pile
	 */
	public static StockPile createStockPile() {
		StockPile stockPile = new StockPile();

		// create all 52 cards and add to stock pile
		for (int i = 1; i <= 13; i++) {
			for (Suit s : Suit.values()) {
				Card c = new Card(s, i);
				stockPile.add(c);
			}
		}

		// shuffle stock pile
		Collections.shuffle(stockPile);
		return stockPile;
	}

	/**
	 * Create discard pile
	 * 
	 * @return new discard pile
	 */
	public static DiscardPile createDiscardPile() {
		DiscardPile discardPile = new DiscardPile();
		return discardPile;
	}

	/**
	 * Discard specified card from player's hand
	 * 
	 * @param p1      - user player
	 * @param dp      - discard pile
	 * @param discard - string input of card to be discarded
	 */
	private static void discardCard(Player p1, DiscardPile dp, String discard) {
		dp.push(p1.discardFromHand(discard));
	}

	/**
	 * Display and add the drawn card to hand
	 * 
	 * @param p1 - user player
	 * @param c  - card being added
	 */
	private static void displayAddDraw(Player p1, Card c) {
		System.out.println("You drew: " + c.toSymbol());
		p1.addCardToHand(c);
	}

	/**
	 * Distribute cards. 10 cards per player, next card on the discard pile
	 * 
	 * @param p1          - user player
	 * @param cpu         - computer player
	 * @param stockPile   - stock pile
	 * @param discardPile - discard pile
	 */
	public static void distributeCards(Player p1, Player cpu, StockPile stockPile, DiscardPile discardPile) {
		for (int i = 0; i < 10; i++) {
			p1.addCardToHand(stockPile.pop());
			cpu.addCardToHand(stockPile.pop());
		}
		discardPile.push(stockPile.pop());
	}

	/**
	 * Draw from the stock pile
	 * 
	 * @param p1        - user player
	 * @param stockPile - stock pile
	 */
	private static void drawFromStockPile(Player p1, StockPile stockPile) {
		Card c = stockPile.pop();
		displayAddDraw(p1, c);
	}

	/**
	 * Draw from discard pile
	 * 
	 * @param p1          - user player
	 * @param discardPile - discard pile
	 */
	private static void drawFromDiscardPile(Player p1, DiscardPile discardPile) {
		Card c = discardPile.pop();
		displayAddDraw(p1, c);
	}

	/**
	 * End the game, close scanners
	 */
	public static void endGame() {
		UserInputOps.closeScanner();
	}

	/**
	 * Play again? Ask the user.
	 * 
	 * @return user's input
	 */
	public static char playAgain() {
		return UserInputOps.playAgain();
	}

	/**
	 * Given the user's choice, process its choice and return true if the user
	 * knocks, false otherwise.
	 * 
	 * @param p1 - user player
	 * @param sp - stock pile
	 * @param dp - discard pile
	 * @return true if user knocks, false otherwise
	 */
	public static boolean processDecision(Player p1, StockPile sp, DiscardPile dp) {
		// for the do-while loop
		int choice;

		// check melds
		p1.checkMelds();
		// recalculate deadwood score
		p1.recalculateDeadwoodScore();

		do {
			System.out.print("Card on the discard pile: ");
			dp.displayTopCard();

			// display hand
			System.out.println("Your hand: ");
			p1.displayHand();

			choice = UserInputOps.playerDecision();

			// choice check
			switch (choice) {
			case 1:
				// draw from stock pile
				drawFromStockPile(p1, sp);
				break;
			case 2:
				// draw from discard pile
				drawFromDiscardPile(p1, dp);
				break;
			case 3:
				// check melds, if any
				ArrayList<ArrayList<Card>> melds = p1.getMelds();
				if (melds.isEmpty()) {
					System.out.println("There are no melds.\n");
				} else {
					for (ArrayList<Card> meld : melds) {
						for (Card c : meld) {
							System.out.print(c.toSymbol() + " ");
						}
						System.out.println("\n");
					}
				}
				break;
			case 4:
				// deadwood score - knock?
				System.out.println("Your current deadwood score: " + p1.getDeadwoodScore() + "\n");
				if (p1.getDeadwoodScore() <= 10) {
					System.out.println("Would you like to knock? (y/n)");
					char knock = UserInputOps.knock();
					if (knock == 'y') {
						return true;
					}
				}
				break;
			}
		} while (choice == 3 || choice == 4);

		// discard a card from the hand
		String discard = chooseDiscard(p1);
		discardCard(p1, dp, discard);
		return false;
	}

	/**
	 * Reset all elements of the former deal
	 * 
	 * @param p - player being reset
	 */
	public static void resetEverything(Player p) {
		p.resetDeadwoodScore();
		p.resetHand();
		p.resetMelds();
	}

	/**
	 * Call user operations to ask for a username
	 * 
	 * @return username from user operations
	 */
	public static String username() {
		return UserInputOps.username();
	}
}
