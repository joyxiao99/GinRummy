import java.util.Collections;

/**
 *
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
		} else {
			cpu.addToTotalScore(difference);
		}
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
	 * Display and add the drawn card to hand
	 * 
	 * @param p1 - user player
	 * @param c  - card being added
	 */
	private static void displayAddDraw(Player p1, Card c) {
		System.out.println("You drew: " + c.toString());
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
	public static void drawFromStockPile(Player p1, StockPile stockPile) {
		Card c = stockPile.pop();
		displayAddDraw(p1, c);
	}

	/**
	 * Draw from discard pile
	 * 
	 * @param p1          - user player
	 * @param discardPile - discard pile
	 */
	public static void drawFromDiscardPile(Player p1, DiscardPile discardPile) {
		Card c = discardPile.pop();
		displayAddDraw(p1, c);
	}

	/**
	 * Reset all elements of the former deal
	 * 
	 * @param p1  - user player
	 * @param cpu - cpu player
	 */
	public static void resetEverything(Player p1, Player cpu) {
		p1.resetDeadwoodScore();
		p1.resetHand();
		p1.resetMelds();
		cpu.resetDeadwoodScore();
		cpu.resetHand();
		cpu.resetMelds();
	}
}
