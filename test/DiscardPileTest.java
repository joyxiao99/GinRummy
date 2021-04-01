import org.junit.*;

public class DiscardPileTest {

	private static DiscardPile discardPile;

	private static DiscardPile discardPileExpected = new DiscardPile();

	private static Card card_9d = new Card(Suit.D, 9); 

	private static Card card_5h = new Card(Suit.H, 5); 

	@Before
	public void setUp() {
		discardPile = new DiscardPile();
		//Set up the hand		
		discardPile.add(card_5h);
		discardPile.add(card_9d);
	}
	
	/**
	 * Tests FR-DP-1 display method - Manually checked in console
	 */
	@Test
	public void testDiscardPileDisplay() {
		discardPile.displayTopCard();
		//9 of diamonds card is displayed
	}
	
	/**
	 * Tests FR-DP-2 drawing card
	 */
	@Test
	public void testDiscardPilePop() {
		discardPile.pop();
		
		//Expected discard pile
		discardPileExpected.add(card_5h);
		
		Assert.assertEquals(discardPileExpected, discardPile);
	}
}
