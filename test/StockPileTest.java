import org.junit.*;

public class StockPileTest {


	private static StockPile stockPile;

	@Before
	public void setUp() {
		stockPile = new StockPile();
		//Set up the hand		
		Card card = new Card(Suit.S, 1);
		stockPile.add(card);
		card = new Card(Suit.D, 5);
		stockPile.add(card);
	}

	/**
	 * Tests FR-DP-1 drawing card
	 */
	@Test
	public void testHandPop1() {
		Card card = new Card(Suit.S, 1);

		stockPile.pop();
		Assert.assertEquals(card.toString(), stockPile.peek().toString());
		Assert.assertEquals(1, stockPile.size());
	}
	
//	/*
//	 * Tests FR-DP-2 drawing card
//	 */
//	@Test
//	public void testHandPop2() {
//		StockPile stockPile = new StockPile();
//		stockPile.pop();
//	}
}
