import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meld {

	public static ArrayList<ArrayList<Card>> checkMelds(Hand H){
		List<Card> handList = new ArrayList<Card>();
		ArrayList<ArrayList<Card>> sequenceMelds = new ArrayList<ArrayList<Card>>();
		ArrayList<ArrayList<Card>> groupMelds = new ArrayList<ArrayList<Card>>();
		for(Card c: H) {
			handList.add(c);
		}
		handList = sortRank(handList);
		handList = sortSuitRank(handList);
		sequenceMelds = findSeq(handList);
		for(ArrayList<Card> list: sequenceMelds) {
			for(Card c1: list) {
				handList.remove(c1);
			}
		}
		handList = sortRank(handList);
		groupMelds = findgroups(handList);
		
		ArrayList<ArrayList<Card>> meldset = new ArrayList<ArrayList<Card>>();
		for(ArrayList<Card> list: sequenceMelds ) {
			meldset.add(list);
		}
		for(ArrayList<Card> list: groupMelds ) {
			meldset.add(list);
		}
		System.out.println(meldset);
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

				if(ranki + 1==(sh.get(i+1).getRank())
						&& ranki + 2 == (sh.get(i+2).getRank()) 
						&& ranki + 3 == (sh.get(i+3).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2),sh.get(i+3));
					i = i+3;
				}
				i++;
			}else if(i+2 < sh.size() && suiti ==  sh.get(i+1).getSuit() 
					&& suiti == sh.get(i+2).getSuit()) {
				if(ranki + 1 == sh.get(i+1).getRank() 
						&& ranki + 2 == sh.get(i+2).getRank()) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2));
					i = i+2;
				}
				i++;
			}else
				i++;
		if(!miniMeld.isEmpty()) {
			seqMeld.add(miniMeld);	
		}	
		}
		return seqMeld;
	}
	
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
			}else if((i+2 < sh.size() && ranki == sh.get(i+1).getRank() 
						&& ranki == sh.get(i+2).getRank())) {
					Collections.addAll(miniMeld,sh.get(i),sh.get(i+1),sh.get(i+2));
					i = i+2;
			}else
				i++;
		if(!miniMeld.isEmpty()) {
			groupMelds.add(miniMeld);	
		}	
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
	
}
