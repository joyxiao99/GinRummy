public class Computer {

	/*
	 * Computer opponent makes move 
	 * 
	 * @param player The computer player information
	 * @param stockPile The stock pile	 
	 * @param discardPile The discard pile
	 */
	public static void makeMove(Player player, StockPile stockPile, DiscardPile discardPile) {
		//adds card from discard pile into computer hand and checks for melds WITHOUT popping from discard pile
		player.addCardToHand(discardPile.peek());
		
		//if there is a meld, keep discard pile card in hand and discard highest point deadwood card
		if (Meld.checkMelds(player.getHand()).size() == 0) {
			Card cardToDiscard = findHighestDeadwoodCard(player, discardPile);
			swapDiscardCardHighestDeadwood(player, discardPile, cardToDiscard);
		} else {
			//finalizes computer hand with new card and discard a card from hand
			completeTurnNoMeld(player, stockPile, discardPile);
		}
	}
	
	/*
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
			player.discardFromHand(discardPile.peek().getSuit(), discardPile.peek().getRank());
		} 
		//when drawing the discard pile card would result in decrease in deadwood score
		else {
			swapDiscardCardHighestDeadwood(player, discardPile, cardToDiscard);
		}
		
	}
	
	private static Card findHighestDeadwoodCard(Player player, DiscardPile discardPile) {
		int highestNonMeldPoint = 0;
		Card cardToDiscard = null;
		
		for (Card card : player.extractDeadwood()) {
			//Finds the deadwood card with highest score and not the discard pile card
			if (card.points() > highestNonMeldPoint && card != discardPile.peek()) {
				highestNonMeldPoint = card.points();
				cardToDiscard = card;
			}
		}
		return cardToDiscard;
	}
	
	private static void swapDiscardCardHighestDeadwood(Player player, DiscardPile discardPile, Card cardToDiscard) {
		//Actually removes card from discard pile
		discardPile.pop();

		//Discard highest point deadwood card in hand
		player.discardFromHand(cardToDiscard.getSuit(), cardToDiscard.getRank());
	}
}
