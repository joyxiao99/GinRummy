/**
 * @brief  A comparator class that defines cards rank by suit and rank
 * @author Smita Singh
 * 
*/
import java.util.Comparator;

public class sortBySR implements Comparator<Card> {

	@Override
	public int compare(Card c1, Card c2) {
		int c;
	    c = c1.getSuit().ordinal() - c2.getSuit().ordinal();
	    if (c == 0)
	       c = c1.getRank()-c2.getRank();
	    return c; 
	}

}
