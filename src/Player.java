import java.util.ArrayList;

/**
 * Data type to represent a generic player in a Gin-Rummy game.
 */
public class Player {
    private final String name;
    private Hand hand;
    private int totalScore;
    private int deadwoodScore;

    /**
     * Player constructor
     * 
     * @param name - name of player
     */
    public Player(String name){
        this.name = name;
        this.hand = new Hand(new ArrayList<Card>());
    }

    /**
     * Accessor for player's name.
     * 
     * @return name of player
     */
    public String getName(){
        return this.name;
    }

    /**
     * Accessor for player's hand
     * 
     * @return player's hand
     */
    public Hand getHand(){
        return this.hand;
    }

    /**
     * Accessor for the player's total game score
     * 
     * @return player's total game score
     */
    public int getTotalScore(){
        return this.totalScore;
    }

    /**
     * Accessor for the player's current deadwood score
     * 
     * @return player's current deadwood score
     */
    public int getDeadwoodScore(){
        return this.deadwoodScore;
    }

    /**
     * Add a card to the player's hand
     * 
     * @param c - card to be added
     */
    public void addCardToHand(Card c){
        this.hand.add(c);
    }

    /**
     * Discard a card from the player's hand, if it is there
     * and return its Card representation.
     * 
     * @param suit - suit of the card
     * @param rank - rank of the card
     */
    public Card discardFromHand(Card.Suit suit, int rank){
        Card c = new Card(suit, rank);
        if(this.hand.contains(c)){
            this.hand.remove(c);
            return c;
        }
        else{
            // TO FIX LATER
            return null;
        }
    }

    /**
     * Add points earned in the round to the total score
     * 
     * @param points - points earned that round
     */
    public void addToTotalScore(int points){
        this.totalScore += points;
    }

    /**
     * Recalculate the deadwood score of the hand
     */
    public void recalculateDeadwoodScore(){
        // TODO
    }

    /**
     * Reset the player's hand at the beginning of a new deal (?)
     */
    public void resetHand(){
        this.hand = new Hand(new ArrayList<Card>());
    }

    /**
     * Reset the deadwood score at the beginning of a new deal (?)
     */
    public void resetDeadwoodScore(){
        this.deadwoodScore = 0;
    }

    public ? checkMelds(){
        // TODO
    }
}
