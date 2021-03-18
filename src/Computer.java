import java.util.ArrayList;

public class Computer {

	/**
	 * Computer opponent makes move 
	 * 
	 * @param player The computer player information
	 * @param stockPile The stock pile	 
	 * @param discardPile The discard pile
	 * @return true when computer knocks, false when computer draws card
	 */
	public static boolean makeMove(Player player, StockPile stockPile, DiscardPile discardPile) {
		System.out.print("Computer Hand:");
		
		player.displayHand();
		player.checkMelds();
		player.recalculateDeadwoodScore();
		
		//Computer knocks when score in hand is 10 or less
		if (player.getDeadwoodScore() <= 10) {
			return true;
		}
		
		//adds card from discard pile into computer hand and checks for melds WITHOUT popping from discard pile
		player.addCardToHand(discardPile.peek());
		
		//can make meld with discard pile card
		player.checkMelds();
		ArrayList<Card> deadwoodCards = player.extractDeadwood();
		if (!deadwoodCards.contains(discardPile.peek())) {
			drawFromDiscard(player, discardPile);
		
		//no meld can be made with discard pile card
		} else {
			noMeldDecision(player, stockPile, discardPile);
		}
		
		player.displayHand();
		System.out.println("Computer deadwood:" + player.extractDeadwood());
		return false;
	}
	
	/**
	 * Decision making for when the discard pile card does not make a meld with cards in hand
	 * 
	 * @param player
	 * @param stockPile
	 * @param discardPile
	 */
	private static void noMeldDecision(Player player, StockPile stockPile, DiscardPile discardPile) {
		//if deadwood score decreases with discard pile card
		int highestNonMeldPoint = 0;
		Card cardWithHighestDeadwood = null;
		
		for (Card card : player.extractDeadwood()) {
			//Finds the deadwood card with highest score and not the discard pile card
			if (card.points() > highestNonMeldPoint && card != discardPile.peek()) {
				highestNonMeldPoint = card.points();
				cardWithHighestDeadwood = card;
			}
		}
		
		//discard pile card has lower point than the highest point deadwood card in hand
		if (cardWithHighestDeadwood.points() > discardPile.peek().points()) {
			drawFromDiscard(player, discardPile);
		}
		else { 
			drawFromStock(player, stockPile, discardPile);
		}
	}
	
	/**
	 * Draws card from stock pile into hand and discards a card
	 * 
	 * @param player
	 * @param stockPile
	 * @param discardPile
	 */
	private static void drawFromStock(Player player, StockPile stockPile, DiscardPile discardPile) {
		Card card = stockPile.pop();
		player.addCardToHand(card);
		//remove discard pile card that was previously added
		player.discardFromHand(discardPile.peek().toString());
	
		player.checkMelds();
		discardHighestPointDeadwoodCard(player, discardPile);
	}
	
	/**
	 * Draws card from discard pile into hand and discards a card
	 * 
	 * @param player
	 * @param discardPile
	 */
	private static void drawFromDiscard(Player player, DiscardPile discardPile) {
		//take discard pile card from pile 
		discardPile.pop();
		
		discardHighestPointDeadwoodCard(player, discardPile);
	}
	

	/**
	 * Discards the appropriate card from hand into the discard pile 
	 * 
	 * @param player
	 * @param discardPile
	 */
	private static void discardHighestPointDeadwoodCard(Player player, DiscardPile discardPile) {
		int highestNonMeldPoint = 0;
		Card cardWithHighestDeadwood = null;
		
		for (Card card : player.extractDeadwood()) {
			//Finds the deadwood card with highest score and not the discard pile card
			if (card.points() > highestNonMeldPoint && card != discardPile.peek()) {
				highestNonMeldPoint = card.points();
				cardWithHighestDeadwood = card;
			}
		}
		player.discardFromHand(cardWithHighestDeadwood.toString());
		discardPile.add(cardWithHighestDeadwood);
	}
}