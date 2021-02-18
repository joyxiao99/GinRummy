
public class Card {

	//suits Enum: S =Spades, H = Heart..etc
	public enum Suit{
		 H, S, C, D;
	}

	private final Suit suit;
	private final int  rank;
	
	//constructor
	public Card(Suit s, int r) {
		this.rank = r;
		this.suit = s;
	}
	
	//returns suit of the card
	public Suit getSuit() {
		return this.suit;
	}
	
	//returns rank of the card;=
	public int getRank() {
		return this.rank;
	}
	
	//returns 10 points for all face cards except Ace, and returns the card rank for other cards
	public int Points() {
		if (this.rank < 10) {
			return this.rank;
		}else
			return 10;
	}
	
	// calls two private methods that each turn  the rank and suit into a string and then returns the combination
	@Override
	public String toString() {
		String r = rankString();
		String s = suitString();
		return r+s;
	}
	
	
	// returns string of the rank
	private String rankString() {
	String s;
	switch(this.rank) {
		case 1:
			s = "A";
			break;
		case 11:
			s = "J";
			break;
		case 12 :
			s = "Q";
			break;
		case 13:
			s = "K";
			break;
		default:
			s = Integer.toString(this.rank);
	}
	return s;	
	}
	
	//returns string of the suit
	private String suitString() {
		String s;
		switch(this.suit) {
			case S:
				s = "s";
				break;
			case H:
				s = "h";
				break;
			case C :
				s = "c";
				break;
			default:
				s = "d";	
		}
		return s;	
	}

public static void main(String args[]) {
	Card c = new Card(Suit.C,11);
	System.out.println(c.toString());
	System.out.println(c.Points());
	System.out.println(c.getRank());
	System.out.println(c.getSuit());
}
	}

