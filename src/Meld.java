/**
 * @brief  A class to determine the melds of a hand
 * @author Smita Singh
 * 
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meld {

	/**
	 * Finds the sequence and group melds of a given hand of cards
	 * 
	 * @param Hand H hand of cards of the player
	 * @return 2d arraylist of the the list of melds
	 */
	public static ArrayList<ArrayList<Card>> checkMelds(Hand H){
		
		ArrayList<ArrayList<Card>> meldset1 = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> meldset2 = new ArrayList<ArrayList<Card>>();
		List<Card> handList = new ArrayList<Card>();
		List<Card> handList2 = new ArrayList<Card>(); 

		for(Card c: H) {
			handList.add(c);
			handList2.add(c);
		}
		

		meldset1 = checkSeqGroup(handList);
		meldset2 = checkGroupSeq(handList2);
		if(deadwood(handList, meldset1) < deadwood(handList2, meldset2)) {
			return meldset1;
		}
		else{
			return meldset2;

		}
	}

	/**
	 * Determines the deadwood score of a hand with melds
	 * 
	 * @param List<Card> handList hand of cards of the player
	 * @param ArrayList<ArrayList<Card>> melds of the hand
	 * @return integer representing deadwood score
	 */
	private static int deadwood(List<Card> handList, ArrayList<ArrayList<Card>> melds) {
		int deadwoodscore = 0;
		List<Card> newHandList = removeCards(handList, melds);
		for(Card c: newHandList) {
			deadwoodscore += c.points();
		}
		return deadwoodscore;
		
	}

	/**
	 * Determines the sequence melds and then the group melds
	 * 
	 * @param List<Card> handList hand of cards of the player
	 * @return 2-D ArrayList of cards representing sequence and group melds
	 */
	private static ArrayList<ArrayList<Card>> checkSeqGroup(List<Card> handList){
		ArrayList<ArrayList<Card>> sequenceMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> groupMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> meldset = new ArrayList<ArrayList<Card>>();
		handList = sortSuitRank(handList);
		sequenceMelds = findSeq(handList);
		
		handList = removeCards(handList, sequenceMelds);
		handList = sortRank(handList);
		groupMelds = findgroups(handList);
		
		
		meldset.addAll(sequenceMelds);
		meldset.addAll(groupMelds);
		return meldset;
	}
	
	/**
	 * Determines the group melds and then the sequence melds
	 * 
	 * @param List<Card> handList hand of cards of the player
	 * @return 2-D ArrayList of cards representing sequence and group melds
	 */
	private static ArrayList<ArrayList<Card>> checkGroupSeq(List<Card> handList){
		ArrayList<ArrayList<Card>> sequenceMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> groupMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> meldset = new ArrayList<ArrayList<Card>>();
		handList = sortRank(handList);
		groupMelds = findgroups(handList);
	
		handList = removeCards(handList, groupMelds);
		handList = sortSuitRank(handList);
		sequenceMelds = findSeq(handList);
		
		meldset.addAll(sequenceMelds);
		meldset.addAll(groupMelds);
		return meldset;
	}
	/**
	 * Private method that removeCards of a hand that are parts of the sequncemelds
	 * 
	 * @param H hand of cards of the player
	 * @param sequencemelds a list of sequencemelds
	 * @return 2d arraylist of the the list of melds
	 */
	private static List<Card> removeCards (List<Card> handList, ArrayList<ArrayList<Card>> melds){
		if(melds.isEmpty()) {

			return handList;
		}
		for(ArrayList<Card> list: melds) {
			for(Card c1: list) {
				handList.remove(c1);
			}
		}
		return handList;
	}
	/**
	 * Private method that sorts the cards in a hand by rank
	 * 
	 * @param h hand of cards
	 * @return sorted list of cards
	 */
	private static List<Card> sortRank(List<Card> h) {
		 Collections.sort(h, new sortByRank());
		return h;
	}
	
	/**
	 * Private method that sorts the cards in a hand by suit first, and then rank
	 * 
	 * @param h list of cards
	 * @return sorted list of cards
	 */
	private static List<Card> sortSuitRank(List<Card> h) {
		Collections.sort(h, new sortBySR());
		return h;
	}
	/**
	 * Private method that finds sequence melds from a sorted hand of cards
	 * 
	 * @param sh list of sorted cards by suit and rank
	 * @return 2d arraylist of all the sequence melds.
	 */
	private static ArrayList<ArrayList<Card>> findSeq(List<Card> sh) {
		
		ArrayList<ArrayList<Card>> seqMeld = new ArrayList<ArrayList<Card>>();	
		int i = 0;
		Suit suiti;
		int ranki;
		while(i < sh.size()) {
			ArrayList<Card> miniMeld = new ArrayList<Card>();
			suiti = sh.get(i).getSuit();
			ranki = sh.get(i).getRank();
	
			if(i+3 < sh.size() && suiti ==  sh.get(i+1).getSuit() 
					&& suiti == sh.get(i+2).getSuit() 
					&& suiti == sh.get(i+3).getSuit()
					&&ranki + 1 ==(sh.get(i+1).getRank())
					&& ranki + 2 == (sh.get(i+2).getRank()) 
					&& ranki + 3 == (sh.get(i+3).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2),sh.get(i+3));
					i = i+3;
					seqMeld.add(miniMeld);
					continue;
				}			
			if(i+2 < sh.size() && suiti ==  sh.get(i+1).getSuit() 
					&& suiti == sh.get(i+2).getSuit()
					&&ranki + 1 == sh.get(i+1).getRank() 
					&& ranki + 2 == sh.get(i+2).getRank()) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2));
					i = i+2;
					seqMeld.add(miniMeld);
					continue;
				}
				
			
				i++;

		}
		return seqMeld;
	}

	/**
	 * Private method that finds group melds from a sorted hand of cards
	 * 
	 * @param sh list of sorted cards by rank
	 * @return 2d arraylist of all the group melds.
	 */
	private static ArrayList<ArrayList<Card>> findgroups(List<Card> sh){
		ArrayList<ArrayList<Card>> groupMelds = new ArrayList<ArrayList<Card>>();
		int i = 0;
		int ranki;
		while(i < sh.size()) {
			ArrayList<Card> miniMeld = new ArrayList<Card>();
			ranki = sh.get(i).getRank();
	
			if(i+3 < sh.size() && ranki ==(sh.get(i+1).getRank())
						&& ranki == (sh.get(i+2).getRank()) 
						&& ranki == (sh.get(i+3).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2),sh.get(i+3));
					i = i+3;
					groupMelds.add(miniMeld);
					continue;
			}
			if((i+2 < sh.size() && ranki == sh.get(i+1).getRank() 
						&& ranki == sh.get(i+2).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2));
					i = i+2;
					groupMelds.add(miniMeld);
					continue;
			}
				i++;
		}
		return groupMelds;
	}
	

}
