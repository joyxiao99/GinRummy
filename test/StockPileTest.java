import org.junit.*;

public class StockPileTest {

	private static StockPile stockPile;
	
	private static StockPile stockPileExpected;
	
	private static Card card_5d = new Card(Suit.D, 5); 

	private static Card card_As = new Card(Suit.S, 1); 

	@Before
	public void setUp() {
		stockPile = new StockPile();
		//Set up the hand		
		stockPile.add(card_As);
		stockPile.add(card_5d);
		
		stockPileExpected = new StockPile();
	}

	/**
	 * Tests FR-DP-1 drawing card
	 */
	@Test
	public void testStockPilePop() {
		stockPile.pop();
		
		//Expected stock pile
		stockPileExpected.add(card_As);
		
		Assert.assertEquals(stockPileExpected, stockPile);
	}

}
