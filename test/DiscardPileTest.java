import org.junit.*;

public class DiscardPileTest {


	private static DiscardPile discardPile;

	@Before
	public void setUp() {
		discardPile = new DiscardPile();
		//Set up the hand		
		Card card = new Card(Suit.H, 5);
		discardPile.add(card);
		card = new Card(Suit.D, 9);
		discardPile.add(card);
	}
	
	/**
	 * Tests FR-DP-1 display method - Manual Test
	 */
	@Test
	public void testHandDisplay() {
		discardPile.displayTopCard();
		//9d is displayed
	}
	
	/**
	 * Tests FR-DP-2 drawing card
	 */
	@Test
	public void testHandPop() {
		Card card = new Card(Suit.H, 5);

		discardPile.pop();
		Assert.assertEquals(card.toString(), discardPile.peek().toString());
		Assert.assertEquals(1, discardPile.size());
	}
}
