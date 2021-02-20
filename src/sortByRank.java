import java.util.Comparator;

public class sortByRank implements Comparator<Card> {
	@Override
	public int compare(Card c1, Card c2) {
		return c1.getRank() - c2.getRank();
	} 
}
