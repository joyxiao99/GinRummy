
public class Computer {

	/*
	 * Computer opponent makes move 
	 * 
	 * @param player The computer player information
	 * @param stockPile The stock pile	 
	 * @param discardPile The discard pile
	 */
	public static void makeMove(Player player, StockPile stockPile, DiscardPile discardPile) {
		//adds card from discard pile into computer hand and checks for melds
		checkMeldsWithTopDiscardCard(player.getHand(), discardPile);
		//finalizes computer hand with new card and discard a card from hand
		completeTurn(player, stockPile, discardPile);
	}
	
	/*
	 * Checks for melds with the visible card from the discard pile
	 *
	 * @param hand The computer opponent's hand
	 * @param discardPile The discard pile
	 */
	private static void checkMeldsWithTopDiscardCard(Hand hand, DiscardPile discardPile) {
		hand.add(discardPile.peek());
//		Meld.checkMeld(hand);
	}
	
	/*
	 * Checks for lowest deadwood score with discard pile card
	 * Keep the discard pile card in hand
	 * or draw from stock pile (and removing discard pile card previously added)
	 * Discard highest point non meld card
	 * 
	 * @param player The computer player information
	 * @param stockPile The stock pile	 
	 * @param discardPile The discard pile
	 */
	private static void completeTurn(Player player, StockPile stockPile, DiscardPile discardPile) {
		//find card to swap with discard pile card to make lowest deadwood score
		int highestNonMeldPoint = 0;
		Card cardToDiscard = null;
		
		for (Card card : player.getHand()) {
			//Finds the card with highest deadwood score in hand (that is non meld and not the discard pile card)
			if (!player.checkMelds().contains(card) && card.points() > highestNonMeldPoint && card != discardPile.peek()) {
				highestNonMeldPoint = card.points();
				cardToDiscard = card;
			}
		}
	
		//when drawing from discard pile will not result in a decrease in deadwood score
		if (highestNonMeldPoint == 0) {
			//Draw from the stock pile
			player.addCardToHand(stockPile.pop());
			
			//discard the previously drawn discard pile card from the hand
			player.discardFromHand(discardPile.peek().getSuit(), discardPile.peek().getRank());
		} 
		//when drawing the discard pile card would result in decrease in deadwood score
		else {
			//Takes the card from discard pile
			discardPile.pop();

			//discard highest non meld card in hand
			player.discardFromHand(cardToDiscard.getSuit(), cardToDiscard.getRank());
		}
		
	}
	
}
