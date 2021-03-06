import java.util.ArrayList;

/**
 * @author Joy Xiao
 * @brief Hand of cards class
 */
public class Hand extends ArrayList<Card> {
	
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
		for(Card card: this) {
			System.out.print("_________");
			System.out.print(" ");
		}
		System.out.println();
		for(Card card: this) {
			System.out.print("|       | ");
		}
		System.out.println();
		for(Card card: this) {
			if (card.getRank()!= 10) {
			System.out.print("|"+card.rankString()+"      | ");
			}else {
			System.out.print("|"+card.rankString()+"     | ");	
		}
		}
		System.out.println();
		for(Card card: this) {
			System.out.print("|       | ");
		}
		System.out.println();
		for(Card card: this) {
			System.out.print("|   "+card.suitSymbol()+"   | ");
		}
		System.out.println();
		for(Card card: this) {
			System.out.print("|       | ");
		}
		System.out.println();
		for(Card card: this) {
			if (card.getRank()!= 10)
			System.out.print("|      "+card.rankString()+"| ");
			else
				System.out.print("|     "+card.rankString()+"| ");
		}

		System.out.println();
		for(Card card: this) {
			System.out.print("|_______|");
			System.out.print(" ");
		}
	}
	 
	 /**
	  * Checks if hand contains the card
	  * 
	  * @param playerInput String input of the card to check
	  * @return true when the card is in the hand or else return false
	  */
	 public boolean contains(String playerInput) {
		 for (Card card: this) {
			 if (card.toString().equalsIgnoreCase(playerInput)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 /**
	  * Discards the specific card from the player's hand
	  * 
	  * @param playerInput String input of card to discard
	  * @return the card that has been removed
	  */
	 public Card remove(String playerInput) {		 
		 //Create new hand of cards with the card removed
		 ArrayList<Card> newHand = new ArrayList<Card>();
		 
		 Card cardToDiscard = null;
		 for (Card cardInHand: this) {
			 if (!cardInHand.toString().equalsIgnoreCase(playerInput)) {
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
