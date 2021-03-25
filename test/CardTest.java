import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CardTest {
	
	private static Card card_Ad = new Card(Suit.D, 1); 

	private static Card card_Jd = new Card(Suit.D, 11); 

	private static Card card_2c = new Card(Suit.C, 2); 

	private static Card card_Jh = new Card(Suit.H, 11); 

	private static Card card_Js = new Card(Suit.S, 11); 


	@Test
	public void testGetSuit() {
		Assert.assertEquals(Suit.D, card_Ad.getSuit());
	}

	@Test
	public void testGetRank() {
		Assert.assertEquals(1, card_Ad.getRank());
	}

	@Test
	public void testPoints() {
		Assert.assertEquals(10, card_Jd.points());
		Assert.assertEquals(2, card_2c.points());

	}
	@Test
	public void testToString() {
		Assert.assertEquals("Jh", card_Jh.toString());
		Assert.assertEquals("2c", card_2c.toString());

	}
	@Test
	public void testToSymbol() {
		Assert.assertEquals("J\u2660", card_Js.toSymbol());

	}
}
