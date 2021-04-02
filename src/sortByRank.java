/**
 * @brief  A comparator class that defines cards rank by it ranks
 * @author Smita Singh
 * 
*/
import java.util.Comparator;

public class sortByRank implements Comparator<Card> {

	/**
	 * Public method that returns integer based on which card is greater in rank
	 * 
	 * @param c1 card one
	 * @param c2 card two
	 * @return integer number, if c1 is greater it returns a positive int else it returns a negative int
	 */
	@Override
	public int compare(Card c1, Card c2) {
		int c;
		c = c1.getRank()-c2.getRank();
	    if (c == 0)
		    c = c1.getSuit().ordinal() - c2.getSuit().ordinal();
	    return c; 
	} 
}
