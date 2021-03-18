import java.util.Stack;

/**
 * @author Joy Xiao
 * @brief The stock pile class
 */
public class StockPile extends Stack<Card> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Overrides search method since information should not be given
	 * 
	 * @returns -1 always
	 */
	@Override 
	public int search(Object e) {
		return -1;
	}
}
