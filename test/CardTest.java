import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {
	
	private static Card card_Ad = new Card(Suit.D, 1); 

	private static Card card_Jd = new Card(Suit.D, 11); 

	private static Card card_2c = new Card(Suit.C, 2); 

	private static Card card_Jh = new Card(Suit.H, 11); 

	private static Card card_Js = new Card(Suit.S, 11); 
	private static Card card_14s;

	/**
	 * Tests FR-C-1 getSuit method  
	 */
	@Test
	public void testGetSuit() {
		assertEquals(Suit.D, card_Ad.getSuit());
	}
	
	/**
	 * Tests FR-C-2 checks if exception is called  
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetRankException() {
		card_14s = new Card (Suit.D, -1);
	}
	
	/**
	 * Tests FR-C-3 points method with a card with rank greater than 10
	 */
	@Test
	public void testPoints1() {
		assertEquals(10, card_Jd.points());

	}
	
	/**
	 * Tests FR-C-4 points method with card less than rank 10
	 */
	@Test
	public void testPoints2() {
		assertEquals(2, card_2c.points());

	}
	
	/**
	 * Tests FR-C-5 toString method with a face card
	 */
	@Test
	public void testToString() {
		assertEquals("Jh", card_Jh.toString());

	}
	
	/**
	 * Tests FR-C-6 toString with a number card
	 */
	@Test
	public void testToString2() {
		assertEquals("2c", card_2c.toString());

	}
	
	/**
	 * Tests FR-C-7 toSymbol with a face card
	 */
	@Test
	public void testToSymbol() {
		assertEquals("J\u2660", card_Js.toSymbol());

	}
	
	/**
	 * Tests FR-C-8 getRank method 
	 */
	@Test
	public void testGetRank() {
		assertEquals(1, card_Ad.getRank());
	}

}
