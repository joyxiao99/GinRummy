import java.util.Stack;

/**
 * @author Joy Xiao
 * @brief The discard pile class 
 */
public class DiscardPile extends Stack<Card> {

	private static final long serialVersionUID = 1L;

	/**
	 * Display the top card of the discard pile
	 */
	public void displayTopCard() {
		Card topCard = this.peek();
		System.out.println();
		System.out.println("_________");
		System.out.println("|       | ");
		if (topCard.getRank()!= 10) 
			System.out.println("|"+topCard.rankString()+"      | ");
		else 
			System.out.println("|"+topCard.rankString()+"     | ");	
		
		System.out.println("|       | ");
		System.out.println("|   "+topCard.suitSymbol()+"   | ");
		System.out.println("|       | ");
	
		if (topCard.getRank()!= 10)
			System.out.println("|      "+topCard.rankString()+"| ");
		else
			System.out.println("|     "+topCard.rankString()+"| ");

		System.out.println("|_______|");

		}
	}

