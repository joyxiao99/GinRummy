
public class CardADT {

	
	public enum Suit{
		 H, S, C, D;
	}

	private final Suit suit;
	private final int  rank;
	public CardADT(Suit s, int r) {
		this.rank = r;
		this.suit = s;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public int Points() {
		if (this.rank < 10) {
			return this.rank;
		}else
			return 10;
	}
	@Override
	public String toString() {
		String r = rankString();
		String s = suitString();
		return String.format(r,s);
	}
	
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
		System.out.println(s);
		return s;	
	}

public static void main(String args[]) {
	CardADT c = new CardADT(Suit.C,11);
	System.out.println(c.toString());
	System.out.println(c.Points());
	System.out.println(c.getRank());
	System.out.println(c.getSuit());
}
	}


