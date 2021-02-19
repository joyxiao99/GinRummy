import java.util.Stack;

public class DiscardPile extends Stack<Card> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Display the top card of the discard pile
	 */
	public void displayTopCard() {
		Card topCard = this.peek();
		System.out.println(topCard.toString());
	}
}
