import org.junit.*;

public class ComputerTest {
	
	private static Player player;
	
	private static DiscardPile discardPile;
	
	private static StockPile stockPile;
	
	private static Card card_4h = new Card(Suit.H, 4); 

	private static Card card_9c = new Card(Suit.C, 9); 

	private static Card card_5d = new Card(Suit.D, 5); 

	private static Card card_4d = new Card(Suit.D, 4); 

	private static Card card_6d = new Card(Suit.D, 6); 

	private static Card card_Ks = new Card(Suit.S, 13); 

	private static Card card_Js = new Card(Suit.S, 11); 

	private static Card card_Ad = new Card(Suit.D, 1); 
	
	private static Card card_2d = new Card(Suit.D, 2); 

	private static Card card_Qs = new Card(Suit.S, 12); 

	private static Card card_3d = new Card(Suit.D, 3); 
	
	private static Card card_Jc = new Card(Suit.C, 11); 

	private static Card card_Jd = new Card(Suit.D, 11); 

	private static Card card_10d = new Card(Suit.D, 10); 
	
	private static Card card_As = new Card(Suit.S, 1); 

	private static Card card_Kc = new Card(Suit.C, 13); 

	private static Card card_Kd = new Card(Suit.D, 13); 
	
	private static Card card_Qc = new Card(Suit.C, 12); 

	private static DiscardPile discardPileExpected = new DiscardPile();
	
	private static Hand handExpected = new Hand();

	private static StockPile stockPileExpected = new StockPile();
	
	@Before
	public void setUp() {
		discardPile = new DiscardPile();
		stockPile = new StockPile();
		stockPile.add(card_Qs);
		stockPile.add(card_3d);
		discardPileExpected = new DiscardPile();
		handExpected = new Hand();
		stockPileExpected = new StockPile();
		player = new Player("Computer");
	}

	/**
	 * Tests FR-CP-1 make move method
	 */
	@Test
	public void testComputerMakeMove1() {
		discardPile.add(card_4h);
		discardPile.add(card_9c);
		discardPile.add(card_5d);
		
		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Ks);
		player.addCardToHand(card_Js);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		
		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_4h);
		discardPileExpected.push(card_9c);
		discardPileExpected.push(card_Ks);
		
		handExpected.add(card_4d);
		handExpected.add(card_6d);
		handExpected.add(card_Js);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_5d);
		
		stockPileExpected.add(card_Qs);
		stockPileExpected.add(card_3d);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(stockPileExpected.toString(), stockPile.toString());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());

	}
	
	/**
	 * Tests FR-CP-2 make move method
	 */
	@Test
	public void testComputerMakeMove2() {
		discardPile.add(card_4h);
		discardPile.add(card_Jc);
		discardPile.add(card_10d);
		discardPile.add(card_Ks);

		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Js);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_5d);
		
		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_4h);
		discardPileExpected.push(card_Jc);
		discardPileExpected.push(card_10d);
		discardPileExpected.push(card_Ks);
		discardPileExpected.push(card_Js);

		handExpected.add(card_4d);
		handExpected.add(card_6d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_5d);
		handExpected.add(card_3d);

		stockPileExpected.add(card_Qs);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(stockPileExpected.toString(), stockPile.toString());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());
	}
	
	/**
	 * Tests FR-CP-3 make move method
	 */
	@Test
	public void testComputerMakeMove3() {
		discardPile.add(card_4h);
		discardPile.add(card_Jc);
		discardPile.add(card_10d);
		discardPile.add(card_As);

		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Js);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_5d);
		
		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_4h);
		discardPileExpected.push(card_Jc);
		discardPileExpected.push(card_10d);
		discardPileExpected.push(card_Js);
		
		handExpected.add(card_4d);
		handExpected.add(card_6d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_5d);
		handExpected.add(card_As);
		
		stockPileExpected.add(card_Qs);
		stockPileExpected.add(card_3d);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(stockPileExpected.toString(), stockPile.toString());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());
	}
	
	/**
	 * Tests FR-CP-4 never discards meld
	 */
	@Test
	public void testComputerFunctionalityMeld1() {
		discardPile.add(card_4h);
		discardPile.add(card_Jc);
		discardPile.add(card_10d);
		discardPile.add(card_As);

		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_Kc);
		player.addCardToHand(card_Kd);
		player.addCardToHand(card_Ks);

		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_4h);
		discardPileExpected.push(card_Jc);
		discardPileExpected.push(card_10d);
		discardPileExpected.push(card_6d);
		
		handExpected.add(card_4d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_Kc);
		handExpected.add(card_Kd);
		handExpected.add(card_Ks);
		handExpected.add(card_As);

		stockPileExpected.add(card_Qs);
		stockPileExpected.add(card_3d);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(stockPileExpected.toString(), stockPile.toString());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());
	}
	
	/**
	 * Tests FR-CP-5 never discards meld
	 */
	@Test
	public void testComputerFunctionalityMeld2() {
		discardPile.add(card_4h);
		discardPile.add(card_Jd);
		discardPile.add(card_10d);
		discardPile.add(card_As);

		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_Jc);
		player.addCardToHand(card_Qc);
		player.addCardToHand(card_Kc);

		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_4h);
		discardPileExpected.push(card_Jd);
		discardPileExpected.push(card_10d);
		discardPileExpected.push(card_6d);

		handExpected.add(card_4d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_Jc);
		handExpected.add(card_Qc);
		handExpected.add(card_Kc);
		handExpected.add(card_As);

		stockPileExpected.add(card_Qs);
		stockPileExpected.add(card_3d);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(stockPileExpected.toString(), stockPile.toString());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());
	}
	
	/**
	 * Tests FR-CP-6 knock when deadwood score is 10 or less
	 */
	@Test
	public void testComputerKnock() {
		
		stockPile = new StockPile();

		player.addCardToHand(card_4d);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_Jc);
		player.addCardToHand(card_Qc);
		player.addCardToHand(card_Kc);
		player.addCardToHand(card_As);

		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		handExpected.add(card_4d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_Jc);
		handExpected.add(card_Qc);
		handExpected.add(card_Kc);
		handExpected.add(card_As);

		Assert.assertEquals(true, actual);
	}

	/**
	 * Tests FR-CP-7 make move method when discardPile has 1 card
	 */
	@Test
	public void testComputerMakeMove4() {
		discardPile.add(card_4h);

		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Js);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_5d);
		
		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_Js);
		
		handExpected.add(card_4d);
		handExpected.add(card_6d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_5d);
		handExpected.add(card_4h);

		stockPileExpected.add(card_Qs);
		stockPileExpected.add(card_3d);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(stockPileExpected.toString(), stockPile.toString());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());
	}
	
	/**
	 * Tests FR-CP-8 computer doesn't draw from stock when stock pile is empty
	 */
	@Test
	public void testComputerDrawStock0() {
		discardPile.add(card_4h);

		stockPile = new StockPile();
		
		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Js);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_5d);
		
		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		//Expected values for test
		discardPileExpected.push(card_Js);
		
		handExpected.add(card_4d);
		handExpected.add(card_6d);
		handExpected.add(card_Ad);
		handExpected.add(card_2d);
		handExpected.add(card_5d);
		handExpected.add(card_4h);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(discardPileExpected.toString(), discardPile.toString());
		Assert.assertEquals(0, stockPile.size());
		Assert.assertEquals(handExpected.toString(), player.getHand().toString());
	}
}
