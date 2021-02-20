import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meld {

	public static List<ArrayList<Card>> checkMelds(Hand H){
		List<Card> handList = new ArrayList<Card>();
		List<ArrayList<Card>> sequenceMelds = new ArrayList<ArrayList<Card>>();
		for(Card c: H) {
			handList.add(c);
		}
		handList = sortRank(handList);
		System.out.println(handList);
		handList = sortSuitRank(handList);
		System.out.println(handList);
		sequenceMelds = findSeq(handList);
		System.out.println(sequenceMelds);
		List<ArrayList<Card>> meldset = new ArrayList<ArrayList<Card>>();
		return meldset;
	}
	
	
	private static List<Card> sortRank(List<Card> h) {
		 Collections.sort(h, new sortByRank());
		return h;
	}
	
	private static List<Card> sortSuitRank(List<Card> h) {
		Collections.sort(h, new sortBySR());
		return h;
	}
	
	private static List<ArrayList<Card>> findSeq(List<Card> sh) {
		
		List<ArrayList<Card>> seqMeld = new ArrayList<ArrayList<Card>>();
		
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

				if(ranki + 1==(sh.get(i+1).getRank())
						&& ranki + 2 == (sh.get(i+2).getRank()) 
						&& ranki + 3 == (sh.get(i+3).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2),sh.get(i+3));
					i = i+4;
				}
				i++;
			}else if(i+2 < sh.size() && suiti ==  sh.get(i+1).getSuit() 
					&& suiti == sh.get(i+2).getSuit()) {
				System.out.println("Passed");
				if(ranki + 1 == sh.get(i+1).getRank() 
						&& ranki + 2 == sh.get(i+2).getRank()) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2));
					i = i+3;
				}
				i++;
			}else
				i++;
		System.out.println(miniMeld);
		if(!miniMeld.isEmpty()) {
			seqMeld.add(miniMeld);	
		}	
		}
		return seqMeld;
	}
	
	private Hand findgroups(List<Card> sortedHand){
		return null;  
	}
	
	public static void main (String args[]) {
		Hand h = new Hand();
		Card c1 = new Card(Suit.S,11);
		Card c2 = new Card(Suit.D, 12);
		Card c3 = new Card(Suit.S, 10);
		Card c4 = new Card(Suit.S, 9);
		Card c5 = new Card(Suit.S,8);
		Card c6 = new Card(Suit.D, 1);
		Card c7 = new Card(Suit.H, 1);
		Card c8 = new Card(Suit.C, 1);
		Card c9 = new Card(Suit.H, 13);
		Card c10 = new Card(Suit.C, 2);
		h.add(c1); h.add(c2); h.add(c3);h.add(c4); h.add(c5); 
		h.add(c6); h.add(c7); h.add(c8);h.add(c9); h.add(c10); 
		checkMelds(h);
		
	}
	
}