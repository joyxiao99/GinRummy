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
		
		ArrayList<ArrayList<Card>> sequenceMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> groupMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> meldset = new ArrayList<ArrayList<Card>>();
		List<Card> handList = new ArrayList<Card>();
		
		for(Card c: H) {
			handList.add(c);
		}
		
		handList = sortRank(handList);
		handList = sortSuitRank(handList);
		sequenceMelds = findSeq(handList);
		
		handList = removeCards(handList, sequenceMelds);
		handList = sortRank(handList);
		groupMelds = findgroups(handList);
		
		
		meldset.addAll(sequenceMelds);
		meldset.addAll(groupMelds);
		System.out.println(meldset);
		return meldset;
	}
	/**
	 * Private method that removeCards of a hand that are parts of the sequence melds
	 * 
	 * @param H hand of cards of the player
	 * @param sequencemelds a list of sequence melds
	 * @return 2d arraylist of the the list of melds
	 */
	private static List<Card> removeCards (List<Card> handList, ArrayList<ArrayList<Card>> sequenceMelds){
		
		for(ArrayList<Card> list: sequenceMelds) {
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
		System.out.println(h);
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
					&& suiti == sh.get(i+3).getSuit()){

				if(ranki + 1 ==(sh.get(i+1).getRank())
						&& ranki + 2 == (sh.get(i+2).getRank()) 
						&& ranki + 3 == (sh.get(i+3).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2),sh.get(i+3));
					i = i+3;
					seqMeld.add(miniMeld);
					continue;
				}
			}
			if(i+2 < sh.size() && suiti ==  sh.get(i+1).getSuit() 
					&& suiti == sh.get(i+2).getSuit()) {
				if(ranki + 1 == sh.get(i+1).getRank() 
						&& ranki + 2 == sh.get(i+2).getRank()) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2));
					i = i+2;
					seqMeld.add(miniMeld);
					continue;
				}
				
			}
				i++;

		}
		return seqMeld;
	}
//	private static boolean checkSuits(int i,List<Card> sh, int iterations) {
//		Suit suiti = sh.get(i).getSuit();
//		for(int j = 1; j < = iterations; j++) {
//			if ()
//		}
//	}
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
	
//	public static void main (String args[]) {
//		Hand h = new Hand();
//		Card c1 = new Card(Suit.S,11);
//		Card c2 = new Card(Suit.D, 12);
//		Card c3 = new Card(Suit.S, 10);
//		Card c4 = new Card(Suit.S, 9);
//		Card c5 = new Card(Suit.S,8);
//		Card c6 = new Card(Suit.H, 12);
//		Card c7 = new Card(Suit.H, 11);
//		Card c8 = new Card(Suit.C, 1);
//		Card c9 = new Card(Suit.H, 13);
//		Card c10 = new Card(Suit.C, 2);
//		h.add(c1); h.add(c2); h.add(c3);h.add(c4); h.add(c5); 
//		h.add(c6); h.add(c7); h.add(c8);h.add(c9); h.add(c10); 
//		checkMelds(h);
//		
//	}
//	
//	public static void main (String args[]) {
//		Hand h = new Hand();
//		Card c1 = new Card(Suit.D,8);
//		Card c2 = new Card(Suit.C, 11);
//		Card c3 = new Card(Suit.C, 5);
//		Card c4 = new Card(Suit.C, 4);
//		Card c5 = new Card(Suit.S,2);
//		Card c6 = new Card(Suit.C, 13);
//		Card c7 = new Card(Suit.C, 10);
//		Card c8 = new Card(Suit.D, 3);
//		Card c9 = new Card(Suit.C, 9);
//		Card c10 = new Card(Suit.H, 5);
//		h.add(c1); h.add(c2); h.add(c3);h.add(c4); h.add(c5); 
//		h.add(c6); h.add(c7); h.add(c8);h.add(c9); h.add(c10); 
//		checkMelds(h);
//		
//	}
//	
}
