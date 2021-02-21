import java.util.ArrayList;

public class Hand extends ArrayList<Card> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Hand constructor
	 */
	public Hand() {
	}
	
	 /**
	  * Displays the player's hand to the console
	  */
	 public void displayHand() {
		 String playerHand = "";
		 
		 for (Card card : this) {
			 playerHand += card.toString() + " ";
		 }
		 System.out.println(playerHand);
	 }
	 
	 /**
	  * Checks if hand contains the card
	  * 
	  * @param playerInput String input of the card to check
	  */
	 public boolean contains(String playerInput) {
		 for (Card card: this) {
			 if (card.toString().equals(playerInput)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 /**
	  * Discards the specific card from the player's hand
	  * 
	  * @param playerInput String input of card to discard
	  * @return Card that has been removed
	  */
	 public Card remove(String playerInput) {		 
		 //Create new hand of cards with the card removed
		 ArrayList<Card> newHand = new ArrayList<Card>();
		 
		 Card cardToDiscard = null;
		 for (Card cardInHand: this) {
			 if (!cardInHand.toString().equals(playerInput)) {
				 newHand.add(cardInHand);
			 } else {
				 cardToDiscard = cardInHand;
			 }
		 }
		 
		 //Set the player's hand to the new hand of cards
		 this.clear();
		 this.addAll(newHand);
		 
		 return cardToDiscard;
	 }
}
