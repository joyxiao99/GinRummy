import java.util.ArrayList;

public class Hand extends ArrayList<Card> {
	
	 public Hand(ArrayList<Card> playerHand) {
		 for (Card card : playerHand) {
			 this.add(card);
		 }
	 }
	
	 /*
	  * Displays the player's hand to the console
	  */
	 private void displayHand() {
		 String playerHand = "";
		 
		 for (Card card : this) {
			 playerHand += card.toString() + " ";
		 }
		 System.out.println(playerHand);
	 }
	 

	 /*
	  * Discards the specific card from the player's hand
	  * 
	  * @param playerInput String input of card to discard
	  * @return true
	  */
	 @Override
	 public boolean remove(Object playerInput) {
		 String discardCard = playerInput.toString().trim();
		 
		 //Create new hand of cards with the card removed
		 ArrayList<Card> newHand = new ArrayList<Card>();
		 
		 for (Card cardInHand: this) {
			 if (!cardInHand.toString().equals(discardCard.toString())) {
				 newHand.add(cardInHand);
			 }
		 }
		 
		 //Set the player's hand to the new hand of cards
		 this.clear();
		 this.addAll(newHand);
		 
		 return true;
	 }
}
