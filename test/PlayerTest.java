import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Benson
 *
 */
public class PlayerTest {

	// player objects
	private static Player p;
	private static Player p2;
	private static Player p3;
	private static Player p4;

	// Card objects
	private static Card card_ah = new Card(Suit.H, 1);
	private static Card card_ac = new Card(Suit.C, 1);
	private static Card card_ad = new Card(Suit.D, 1);
	private static Card card_as = new Card(Suit.S, 1);
	private static Card card_2c = new Card(Suit.C, 2);
	private static Card card_2d = new Card(Suit.D, 2);
	private static Card card_2s = new Card(Suit.S, 2);
	private static Card card_3d = new Card(Suit.D, 3);
	private static Card card_3s = new Card(Suit.S, 3);
	private static Card card_4d = new Card(Suit.D, 4);
	private static Card card_5d = new Card(Suit.D, 5);
	private static Card card_6d = new Card(Suit.D, 6);
	private static Card card_7d = new Card(Suit.D, 7);
	private static Card card_9h = new Card(Suit.H, 9);
	private static Card card_10h = new Card(Suit.H, 10);
	private static Card card_jd = new Card(Suit.D, 11);
	private static Card card_qs = new Card(Suit.S, 12);
	private static Card card_ks = new Card(Suit.S, 13);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		p = new Player("P1");
		// p setup
		p.addCardToHand(card_as);
		p.addCardToHand(card_2s);
		p.addCardToHand(card_3s);
		// preset melds
		p.checkMelds();
		p.addCardToHand(card_4d);
		p.addCardToHand(card_5d);
		p.addCardToHand(card_6d);
		p.addCardToHand(card_7d);
		p.addCardToHand(card_10h);

		p2 = new Player("P2");
		// p2 setup
		p2.addCardToHand(card_ah);
		p2.addCardToHand(card_2s);
		p2.addCardToHand(card_qs);
		p2.addCardToHand(card_3d);
		p2.addCardToHand(card_5d);
		p2.addToTotalScore(12);

		p3 = new Player("P3");
		// p3 setup
		p3.addCardToHand(card_ah);
		p3.addCardToHand(card_ac);
		p3.addCardToHand(card_ad);
		p3.addCardToHand(card_as);
		p3.addCardToHand(card_2c);
		p3.addCardToHand(card_2d);
		p3.addCardToHand(card_2s);
		p3.addCardToHand(card_3d);
		p3.addCardToHand(card_qs);
		p3.addCardToHand(card_9h);
		p3.checkMelds();
		p3.recalculateDeadwoodScore();

		p4 = new Player("P4");
		// p4 setup
		p4.addCardToHand(card_ah);
		p4.addCardToHand(card_ac);
		p4.addCardToHand(card_ad);
		p4.addCardToHand(card_2s);
		p4.addCardToHand(card_9h);
		p4.addCardToHand(card_10h);
		p4.addCardToHand(card_jd);
		p4.checkMelds();
	}

	/**
	 * This method is deliberately left empty.
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests FR-P-1: Get the name of the player object
	 */
	@Test
	public void testGetName() {
		assertEquals(p.getName(), "P1");
		assertEquals(p2.getName(), "P2");
		assertEquals(p3.getName(), "P3");
		assertEquals(p4.getName(), "P4");
	}

	/**
	 * Test FR-P-2: Add a card to player's hand
	 */
	@Test
	public void testAddCardToHand() {
		p2.addCardToHand(card_ks);
		assertEquals(p2.getHand().size(), 6);
		assertTrue(p2.getHand().contains(card_ks.toString()));
	}

	/**
	 * Test FR-P-3: Get the player's hand
	 */
	@Test
	public void testGetHand() {
		Hand h = p2.getHand();
		assertTrue(h.contains(card_ah.toString()));
		assertTrue(h.contains(card_2s.toString()));
		assertTrue(h.contains(card_qs.toString()));
		assertTrue(h.contains(card_3d.toString()));
		assertTrue(h.contains(card_5d.toString()));
	}

	/**
	 * Test FR-P-4: Get player's total score
	 */
	@Test
	public void testGetTotalScore() {
		assertEquals(p2.getTotalScore(), 12);
	}

	/**
	 * Test FR-P-5: Get player's deadwood score
	 */
	@Test
	public void testGetDeadwoodScore() {
		assertEquals(p3.getDeadwoodScore(), 22);
	}

	/**
	 * Test FR-P-6: Get player's melds
	 */
	@Test
	public void testGetMelds() {
		ArrayList<ArrayList<Card>> melds = p3.getMelds();
		assertEquals(melds.size(), 2);
		ArrayList<Card> m1 = melds.get(0);
		assertTrue(m1.contains(card_ah));
		assertTrue(m1.contains(card_ac));
		assertTrue(m1.contains(card_ad));
		assertTrue(m1.contains(card_as));

		ArrayList<Card> m2 = melds.get(1);
		assertTrue(m2.contains(card_2s));
		assertTrue(m2.contains(card_2d));
		assertTrue(m2.contains(card_2c));
	}

	/**
	 * Test FR-P-7: Discard an existing card from the hand
	 */
	@Test
	public void testDiscardOne() {
		Card c = p2.discardFromHand("5d");
		// assert the hand
		assertEquals(p2.getHand().size(), 4);
		assertFalse(p2.getHand().contains("5d"));
		// assert the returned card
		assertEquals(c.toString(), "5d");
	}

	/**
	 * Test FR-P-8: Discard a non-existent card from the hand
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDiscardTwo() {
		p2.discardFromHand("jh");
	}
	
	/**
	 * Test FR-P-9: Add to the total score
	 */
	@Test
	public void testAddToTotalScore() {
		p2.addToTotalScore(15);
		assertEquals(p2.getTotalScore(), 27);
	}
	
	/**
	 * Test FR-P-10: Extract deadwood cards, given melds
	 */
	@Test
	public void testGetDeadwoodCards() {
		ArrayList<Card> deadwood = p4.extractDeadwood();
		assertEquals(deadwood.size(), 4);
		assertTrue(deadwood.contains(card_2s));
		assertTrue(deadwood.contains(card_9h));
		assertTrue(deadwood.contains(card_10h));
		assertTrue(deadwood.contains(card_jd));
		assertFalse(deadwood.contains(card_ah));
		assertFalse(deadwood.contains(card_ac));
		assertFalse(deadwood.contains(card_ad));
	}
	
	/**
	 * Test FR-P-11: Recalculate deadwood score
	 */
	@Test
	public void testRecalculateDeadwoodScore() {
		int oldScore = p4.getDeadwoodScore();
		p4.recalculateDeadwoodScore();
		assertEquals(p4.getDeadwoodScore(), 31);
		assertNotEquals(p4.getDeadwoodScore(), oldScore);
	}
	
	/**
	 * Test FR-P-12: Recheck melds
	 */
	@Test
	public void testCheckMelds() {
		ArrayList<ArrayList<Card>> oldMelds = p.getMelds();
		p.checkMelds();
		ArrayList<ArrayList<Card>> melds = p.getMelds();
		assertNotEquals(oldMelds.size(), melds.size());
		
		ArrayList<Card> m1 = melds.get(0);
		assertTrue(m1.contains(card_as));
		assertTrue(m1.contains(card_2s));
		assertTrue(m1.contains(card_3s));

		ArrayList<Card> m2 = melds.get(1);
		assertTrue(m2.contains(card_4d));
		assertTrue(m2.contains(card_5d));
		assertTrue(m2.contains(card_6d));
		assertTrue(m2.contains(card_7d));
	}
	
	/**
	 * Test FR-P-13: Reset hand
	 */
	@Test
	public void testResetHand() {
		p.resetHand();
		assertTrue(p.getHand().isEmpty());
	}
	
	/**
	 * Test FR-P-14: Reset deadwood score
	 */
	@Test
	public void testResetDeadwoodScore() {
		p.resetDeadwoodScore();
		assertEquals(p.getDeadwoodScore(), 0);
	}
	
	/**
	 * Test FR-P-15: Reset melds
	 */
	@Test
	public void testResetMelds() {
		p.resetMelds();
		assertTrue(p.getMelds().isEmpty());
	}
	
	/**
	 * Test FR-P-16: Reset total score
	 */
	@Test
	public void testResetTotalScore() {
		p.resetTotalScore();
		assertEquals(p.getTotalScore(), 0);
	}
}
