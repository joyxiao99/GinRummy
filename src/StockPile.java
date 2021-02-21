import java.util.Stack;

public class StockPile extends Stack<Card> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	/**
//	 * Overrides peek method since it is not allowed
//	 */
//	@Override 
//	public Card peek() {
//		return null;
//	}
	
	/**
	 * Overrides search method since information should not be given
	 * 
	 * @returns -1
	 */
	@Override 
	public int search(Object e) {
		return -1;
	}
}
