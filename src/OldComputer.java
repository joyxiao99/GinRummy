public class OldComputer {

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
		player.getHand().displayHand();
		player.checkMelds();
		player.recalculateDeadwoodScore();
		//Computer knocks when score in hand is 10 or less
		if (player.getDeadwoodScore() <= 10) {
			return true;
		}
		
		//adds card from discard pile into computer hand and checks for melds WITHOUT popping from discard pile
		player.addCardToHand(discardPile.peek());
		
		//if there is a meld, keep discard pile card in hand and discard highest point deadwood card
		if (player.getMelds().size() == 0) {
			Card cardToDiscard = findHighestDeadwoodCard(player, discardPile);
			swapDiscardCardHighestDeadwood(player, discardPile, cardToDiscard);
			discardPile.add(cardToDiscard);
		} else {
			//finalizes computer hand with new card and discard a card from hand
			completeTurnNoMeld(player, stockPile, discardPile);
		}
		
		return false;
	}
	
	/**
	 * Keep the discard pile card in hand
	 * or draw from stock pile (and removing discard pile card previously added)
	 * Discard highest point deadwood card
	 * 
	 * @param player The computer player information
	 * @param stockPile The stock pile	 
	 * @param discardPile The discard pile
	 */
	private static void completeTurnNoMeld(Player player, StockPile stockPile, DiscardPile discardPile) {
		Card cardToDiscard = findHighestDeadwoodCard(player, discardPile);
	
		//when drawing from discard pile will not result in a decrease in deadwood score
		if (cardToDiscard == null) {
			//Draw from the stock pile
			player.addCardToHand(stockPile.pop());
			
			//discard the previously drawn discard pile card from the hand
			player.discardFromHand(discardPile.peek().toString());
			
		} 
		//when drawing the discard pile card would result in decrease in deadwood score
		else {
			swapDiscardCardHighestDeadwood(player, discardPile, cardToDiscard);
		}
		
	}
	
	/**
	 * Finds highest deadwood card point from hand
	 * 
	 * @param player The computer player
	 * @param discardPile The discard pile
	 * @return The card with highest deadwood point
	 */
	private static Card findHighestDeadwoodCard(Player player, DiscardPile discardPile) {
		int highestNonMeldPoint = 0;
		Card cardWithHighestDeadwood = null;
		
		for (Card card : player.extractDeadwood()) {
			//Finds the deadwood card with highest score and not the discard pile card
			if (card.points() > highestNonMeldPoint && card != discardPile.peek()) {
				highestNonMeldPoint = card.points();
				cardWithHighestDeadwood = card;
			}
		}
		return cardWithHighestDeadwood;
	}
	
	/**
	 * Removes card from discard with highest deadwood point card
	 * 
	 * @param player The computer player information
	 * @param stockPile The stock pile
	 * @param card The card to swap
	 */
	private static void swapDiscardCardHighestDeadwood(Player player, DiscardPile discardPile, Card cardToDiscard) {
		//Actually removes card from discard pile
		discardPile.pop();

		//Discard highest point deadwood card in hand
		player.discardFromHand(cardToDiscard.toString());
		
		discardPile.add(cardToDiscard);
	}
}