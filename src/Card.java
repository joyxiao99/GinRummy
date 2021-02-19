
public class Card {
	private final Suit suit;
	private final int  rank;
	
	/**
     * Card constructor
     * 
     * @param s - Suit type
     * @param r - rank of card (1 to 13)
    */
	public Card(Suit s, int r) {
		this.rank = r;
		this.suit = s;
	}
	
	/**
     * Accessor for suit
     * 
     * @return This returns the card suit
    */
	public Suit getSuit() {
		return this.suit;
	}
	
    /**
     * Accessor for rank
     * 
     * @return This returns the card rank
    */
	public int getRank() {
		return this.rank;
	}
	
	/**
     * This method checks if the card is lower than 10
     * if it is lower than 10 it returns the card rank.
     * if it is not lower than 10, it returns the value of 10
     * 
     * @return Returns card points
    */
	public int points() {
		if (this.rank < 10) {
			return this.rank;
		}else
			return 10;
	}
	/**
     * This method turns a card into a string value
     * by calling two private methods that turn the 
     * rank and suit into string seperately.
     * Then it combines the two string
     * 
     * @return Returns a string representation of the card
    */
	public String toString() {
		String r = rankString();
		String s = suitString();
		return r+s;
	}
	
	
	/**
     * This method will create the string representation
     * of the card rank
     * 
     * 
     * @return Returns a string representation of the card rank
    */
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
	
	/**
     * This method will create the string representation
     * of the card suit
     * 
     * 
     * @return Returns a string representation of the card suit
    */
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

	}

