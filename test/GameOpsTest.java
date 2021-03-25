import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EmptyStackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameOpsTest {

	private static Player p1;
	private static Player p2;
	private static StockPile sp;
	private static StockPile sp2;
	private static DiscardPile dp;

	@Before
	public void setUp() throws Exception {
		p1 = new Player("P1");
		p2 = new Player("P2");
		sp = GameOps.createStockPile();
		dp = GameOps.createDiscardPile();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests FR-GO-1: Score is calculated correctly when p1 is winning.
	 */
	@Test
	public void testCalculateScoreOne() {
		// customized setup
		p1.addCardToHand(new Card(Suit.C, 10));
		p1.checkMelds();
		p1.recalculateDeadwoodScore();

		p2.addCardToHand(new Card(Suit.C, 9));
		p2.addCardToHand(new Card(Suit.D, 6));
		p2.checkMelds();
		p2.recalculateDeadwoodScore();

		GameOps.calculateScores(p1, p2);
		assertEquals(p1.getTotalScore(), 5);
		assertEquals(p2.getTotalScore(), 0);
	}

	/**
	 * Tests FR-GO-2: Score is calculated correctly when p2 is winning
	 */
	@Test
	public void testCalculateScoreTwo() {
		// customized setup
		p1.addCardToHand(new Card(Suit.C, 10));
		p1.addCardToHand(new Card(Suit.H, 13));
		p1.checkMelds();
		p1.recalculateDeadwoodScore();

		p2.addCardToHand(new Card(Suit.D, 9));
		p2.checkMelds();
		p2.recalculateDeadwoodScore();

		GameOps.calculateScores(p1, p2);
		assertEquals(p1.getTotalScore(), 0);
		assertEquals(p2.getTotalScore(), 11);
	}

	/**
	 * Tests FR-GO-3: Going Gin adds 20 points to winner's score
	 */
	@Test
	public void testGinScore() {
		// customized setup
		p2.addCardToHand(new Card(Suit.H, 13));
		p2.addCardToHand(new Card(Suit.S, 5));
		p2.checkMelds();
		p2.recalculateDeadwoodScore();

		GameOps.calculateScores(p1, p2);
		assertEquals(p1.getTotalScore(), 35);
		assertEquals(p2.getTotalScore(), 0);
	}

	/**
	 * Tests FR-GO-4: Create a new stock pile properly
	 */
	@Test
	public void testInitStockPile() {
		assertEquals(sp.size(), 52);

		// check the cards in the deck
		ArrayList<String> deck = new ArrayList<String>();
		for (Suit s : Suit.values()) {
			for (int i = 1; i <= 13; i++) {
				deck.add(new Card(s, i).toString());
			}
		}
		while (sp.size() > 0) {
			Card c = sp.pop();
			String cString = c.toString();
			assertTrue(deck.remove(cString));
		}
	}

	/**
	 * Tests FR-GO-5: Initialize discard pile correctly
	 */
	@Test(expected = EmptyStackException.class)
	public void testInitDiscardPile() {
		assertTrue(dp.isEmpty());
		// this should raise the exception
		dp.peek();
	}

	/**
	 * Tests FR-GO-6: Opening distribution for a new round
	 */
	@Test
	public void testOpeningDist() {
		GameOps.distributeCards(p1, p2, sp, dp);
		assertEquals(p1.getHand().size(), 10);
		assertEquals(p2.getHand().size(), 10);
		assertEquals(dp.size(), 1);
		assertEquals(sp.size(), 31);
	}

	/**
	 * Tests FR-GO-7: Opening distribution does not happen with an invalid initial
	 * stock pile.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidOpeningDist() {
		sp2 = new StockPile();
		for (Suit s : Suit.values()) {
			for (int i = 1; i < 8; i++) {
				sp.add(new Card(s, i));
			}
		}
		GameOps.distributeCards(p1, p2, sp2, dp);
	}

	/**
	 * Tests FR-GO-8: Interfacing play again method
	 */
	@Test
	public void testPlayAgain() {
		char c = GameOps.playAgain();
		assertTrue(c == 'y' || c == 'n');
	}

	/**
	 * Tests FR-GO-9: Processing a user's decision. This test requires viewing of
	 * the console.
	 */
	@Test
	public void testDecision() {
		GameOps.distributeCards(p1, p2, sp, dp);
		GameOps.processDecision(p1, sp, dp);
	}

	/**
	 * Tests FR-GO-10: Reset for a new deal
	 */
	@Test
	public void testResetEverything() {
		p1.addCardToHand(new Card(Suit.H, 1));
		p1.addCardToHand(new Card(Suit.C, 1));
		p1.addCardToHand(new Card(Suit.D, 1));
		p1.addCardToHand(new Card(Suit.S, 6));
		p1.checkMelds();
		p1.recalculateDeadwoodScore();

		GameOps.resetEverything(p1);
		assertTrue(p1.getHand().isEmpty());
		assertTrue(p1.getMelds().isEmpty());
		assertEquals(p1.getDeadwoodScore(), 0);
	}

	/**
	 * Tests FR-GO-11: Interfacing username method
	 */
	@Test
	public void testUsername() {
		String s = GameOps.username();
		assertNotNull(s);
	}
}
