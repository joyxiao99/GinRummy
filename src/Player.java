import java.util.ArrayList;

/**
 * Data type to represent a generic player in a Gin-Rummy game.
 */
public class Player {
    private final String name;
    private Hand hand;
    private int totalScore;
    private int deadwoodScore;
    private ArrayList<ArrayList<Card>> melds;

    /**
     * Player constructor
     * 
     * @param name - name of player
     */
    public Player(String name){
        this.name = name;
        this.hand = new Hand();
        this.totalScore = 0;
        this.deadwoodScore = 0;
        this.melds = null;
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
     * Get the melds in the player's hand
     * 
     * @return melds in player's hand
     */
    public ArrayList<ArrayList<Card>> getMelds(){
        return this.melds;
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
    public Card discardFromHand(Suit suit, int rank){
        Card c = new Card(suit, rank);
        if(this.hand.contains(c)){
            this.hand.remove(c);
            return c;
        }
        else{
            // TENTATIVE - pending main code
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

    private ArrayList<Card> extractDeadwood(){
        // check deadwood
        ArrayList<Card> deadwood = new ArrayList<Card>();
        // for each meld, remove cards from hand
        for(ArrayList<Card> meld: this.melds){
            for(Card c: meld){
                if(!this.hand.contains(c.toString())){
                    deadwood.add(c);
                }
            }
        }
        return deadwood;
    }

    /**
     * Recalculate the deadwood score of the hand
     */
    public void recalculateDeadwoodScore(){
        ArrayList<Card> deadwood = extractDeadwood();
        // reset and recalculate score
        this.resetDeadwoodScore();
        for(Card c: deadwood){
            this.deadwoodScore += c.getRank();
        }
    }

    /**
     * Check for melds at the beginning of each round (?)
     */
    public void recheckMelds(){
        // TODO requires Meld class
    }

    /**
     * Reset the player's hand at the end of a deal
     */
    public void resetHand(){
        this.hand = new Hand();
    }

    /**
     * Reset the deadwood score at the end of a new deal
     */
    public void resetDeadwoodScore(){
        this.deadwoodScore = 0;
    }

    /**
     * Reset melds
     */
    public void resetMelds(){
        this.melds = new ArrayList<ArrayList<Card>>();
    }
}
