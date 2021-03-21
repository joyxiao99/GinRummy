import org.junit.*;

public class ComputerTest {

	private static Computer computer = new Computer();
	
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

	private static Card card_10d = new Card(Suit.D, 10); 
	
	private static Card card_As = new Card(Suit.S, 1); 

	private static Card card_Kc = new Card(Suit.C, 13); 

	private static Card card_Kd = new Card(Suit.D, 13); 
	
	private static Card card_Qc = new Card(Suit.C, 12); 

	@Before
	public void setUp() {
		discardPile = new DiscardPile();
		stockPile = new StockPile();
		stockPile.add(card_Qs);
		stockPile.add(card_3d);
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
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(3, discardPile.size());
		Assert.assertEquals(2, stockPile.size());
		Assert.assertEquals(6, player.getHand().size());
	}
	
	/**
	 * Tests FR-CP-2 make move method
	 */
	@Test
	public void testComputerMakeMove2() {
		discardPile.add(card_4h);
		discardPile.add(card_Jc);
		discardPile.add(card_10d);
		
		player.addCardToHand(card_4d);
		player.addCardToHand(card_6d);
		player.addCardToHand(card_Js);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_5d);
		
		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(4, discardPile.size());
		Assert.assertEquals(1, stockPile.size());
		Assert.assertEquals(6, player.getHand().size());
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
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(4, discardPile.size());
		Assert.assertEquals(2, stockPile.size());
		Assert.assertEquals(6, player.getHand().size());
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
		player.addCardToHand(card_2d);
		player.addCardToHand(card_Kc);
		player.addCardToHand(card_Kd);
		player.addCardToHand(card_Ks);
		player.addCardToHand(card_As);

		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(4, discardPile.size());
		Assert.assertEquals(2, stockPile.size());
		Assert.assertEquals(7, player.getHand().size());
		Assert.assertEquals(true, player.getHand().contains("kd"));
		Assert.assertEquals(true, player.getHand().contains("ks"));
		Assert.assertEquals(true, player.getHand().contains("kc"));
	}
	
	/**
	 * Tests FR-CP-5 never discards meld
	 */
	@Test
	public void testComputerFunctionalityMeld2() {
		discardPile.add(card_4h);
		discardPile.add(card_Jc);
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
		
		Assert.assertEquals(false, actual);
		Assert.assertEquals(4, discardPile.size());
		Assert.assertEquals(2, stockPile.size());
		Assert.assertEquals(7, player.getHand().size());
		Assert.assertEquals(true, player.getHand().contains("jc"));
		Assert.assertEquals(true, player.getHand().contains("qc"));
		Assert.assertEquals(true, player.getHand().contains("kc"));
	}
	
	/**
	 * Tests FR-CP-6 knock when deadwood score is 10 or less
	 */
	@Test
	public void testComputerKnock() {
		player.addCardToHand(card_4d);
		player.addCardToHand(card_Ad);
		player.addCardToHand(card_2d);
		player.addCardToHand(card_Jc);
		player.addCardToHand(card_Qc);
		player.addCardToHand(card_Kc);
		player.addCardToHand(card_As);

		Boolean actual = Computer.makeMove(player, stockPile, discardPile);
		
		Assert.assertEquals(true, actual);
	}

}
