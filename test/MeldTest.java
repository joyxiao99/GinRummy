import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MeldTest {
	private static Card card_7d = new Card(Suit.D, 7);
	private static Card card_Qh = new Card(Suit.H, 12);
	private static Card card_Kc = new Card(Suit.C, 13);
	private static Card card_Kd = new Card(Suit.D, 13);
	private static Card card_Ac= new Card(Suit.C, 1);
	private static Card card_Ad= new Card(Suit.D, 1);
	private static Card card_5h = new Card(Suit.H, 5);
	private static Card card_3s = new Card(Suit.S, 3);
	private static Card card_4s = new Card(Suit.S,4);
	private static Card card_5s = new Card(Suit.S, 5);
	private static Card card_6s= new Card(Suit.S, 6);
	private static Card card_Ah = new Card(Suit.H, 1);
	private static Card card_5d= new Card(Suit.D, 5);
	private static Card card_4d= new Card(Suit.D, 4);
	private static Card card_5c = new Card(Suit.C, 5);
	private static Card card_Jc= new Card(Suit.C, 11);
	private static Card card_Qd= new Card(Suit.D, 12);
	private static Card card_Js = new Card(Suit.S,11);
	private static Card card_Qc = new Card(Suit.C, 12);
	private static Card card_As = new Card(Suit.S, 1); 
	private static Card card_10c = new Card(Suit.C, 10);
	private static Card card_Ks = new Card(Suit.S, 13);
	private static Card card_10d = new Card(Suit.D, 10);
	private static Card card_Qs = new Card(Suit.S, 12);
	private static Card card_10h = new Card(Suit.H, 10);
	private static Card card_6h = new Card(Suit.H, 6);
	private static Card card_9h = new Card(Suit.H, 9);
	private static Card card_4c = new Card(Suit.C, 4);
	private static Card card_2s = new Card(Suit.S,2);
	private static Card card_Jh = new Card(Suit.H, 11);
	private static Card card_Jd = new Card(Suit.D, 11);
	private static Card card_7s = new Card(Suit.S, 7);
	
	private static Hand hand1 = new Hand();
	private static Hand hand2 = new Hand();
	private static Hand hand3 = new Hand();
	private static Hand hand4 = new Hand();
	private static Hand hand5 = new Hand();
	private static Hand hand6 = new Hand();
	
	private static ArrayList<ArrayList<Card>> a1 = new ArrayList<ArrayList<Card>>();
	private static ArrayList<ArrayList<Card>> a2 = new ArrayList<ArrayList<Card>>();
	private static ArrayList<ArrayList<Card>> a3 = new ArrayList<ArrayList<Card>>();
	private static ArrayList<ArrayList<Card>> a4 = new ArrayList<ArrayList<Card>>();
	private static ArrayList<ArrayList<Card>> a5 = new ArrayList<ArrayList<Card>>();
	private static ArrayList<ArrayList<Card>> a6 = new ArrayList<ArrayList<Card>>();
	private static ArrayList<Card> m1 = new ArrayList<Card>();
	private static ArrayList<Card> m2 = new ArrayList<Card>();
	private static ArrayList<Card> m3 = new ArrayList<Card>();
	private static ArrayList<Card> m41 = new ArrayList<Card>();
	private static ArrayList<Card> m42 = new ArrayList<Card>();
	private static ArrayList<Card> m43 = new ArrayList<Card>();
	private static ArrayList<Card> m51 = new ArrayList<Card>();
	private static ArrayList<Card> m52 = new ArrayList<Card>();
	private static ArrayList<Card> m61 = new ArrayList<Card>();
	private static ArrayList<Card> m62 = new ArrayList<Card>();

	
	
	@Before
	public void setup(){
		//hand1 for test 1
		hand1.add(card_3s);
		hand1.add(card_7d);
		hand1.add(card_Qh);
		hand1.add(card_4s);
		hand1.add(card_Kc);
		hand1.add(card_Kd);
		hand1.add(card_Ac);
		hand1.add(card_Ad);
		hand1.add(card_5s);
		hand1.add(card_6s);
		
		//hand2 for test 2
		hand2.add(card_3s);
		hand2.add(card_7d);
		hand2.add(card_Qh);
		hand2.add(card_4s);
		hand2.add(card_Kc);
		hand2.add(card_Kd);
		hand2.add(card_Ac);
		hand2.add(card_Ad);
		hand2.add(card_Ah);
		hand2.add(card_6s);
		
		
		//hand3 for test 3
		hand3.add(card_Kd);
		hand3.add(card_5d);
		hand3.add(card_4d);
		hand3.add(card_5c);
		hand3.add(card_Jc);
		hand3.add(card_Qd);
		hand3.add(card_5h);
		hand3.add(card_5s);
		hand3.add(card_3s);
		hand3.add(card_Js);
							
		//hand4 for test 4
		hand4.add(card_3s);
		hand4.add(card_4s);
		hand4.add(card_5s);
		hand4.add(card_Kc);
		hand4.add(card_Jc);
		hand4.add(card_Qc);
		hand4.add(card_Ac);
		hand4.add(card_Ad);
		hand4.add(card_Ah);
		hand4.add(card_As);
		
		//hand5 for test 5
		hand5.add(card_10c);
		hand5.add(card_Js);
		hand5.add(card_Ks);
		hand5.add(card_10d);
		hand5.add(card_Qs);
		hand5.add(card_10h);
		hand5.add(card_Ac);
		hand5.add(card_4c);
		hand5.add(card_6h);
		hand5.add(card_9h);

		//hand6 for test 6
		hand6.add(card_Ac);
		hand6.add(card_Ad);
		hand6.add(card_Ah);
		hand6.add(card_As);
		hand6.add(card_2s);
		hand6.add(card_3s);
		hand6.add(card_Ks);
		hand6.add(card_Jh);
		hand6.add(card_Jd);
		hand6.add(card_7s);
		
		m1.add(card_3s);
		m1.add(card_4s);
		m1.add(card_5s);
		m1.add(card_6s);
		a1.add(m1);
		
		m2.add(card_Ac);
		m2.add(card_Ad);
		m2.add(card_Ah);
		a2.add(m2);
		
		m3.add(card_5d);
		m3.add(card_5c);
		m3.add(card_5h);
		m3.add(card_5s);
		a3.add(m3);
		
		
		
		m41.add(card_3s);
		m41.add(card_4s);
		m41.add(card_5s);
		a4.add(m41);
		m42.add(card_Jc);
		m42.add(card_Qc);
		m42.add(card_Kc);
		a4.add(m42);
		m43.add(card_Ac);
		m43.add(card_Ad);
		m43.add(card_Ah);
		m43.add(card_As);
		a4.add(m43);
		
		m51.add(card_Js);
		m51.add(card_Qs);
		m51.add(card_Ks);
		a5.add(m51);
		m52.add(card_10c);
		m52.add(card_10d);
		m52.add(card_10h);
		a5.add(m52);
	
		m61.add(card_As);
		m61.add(card_2s);
		m61.add(card_3s);
		a6.add(m61);
		m62.add(card_Ah);
		m62.add(card_Ac);
		m62.add(card_Ad);
		a6.add(m62);
		
	}
	@Test
	public void testCheckMelds() {
		Assert.assertEquals(a1, Meld.checkMelds(hand1));
		Assert.assertEquals(a2, Meld.checkMelds(hand2));
		Assert.assertEquals(a3, Meld.checkMelds(hand3));
		Assert.assertEquals(a4, Meld.checkMelds(hand4));
		Assert.assertEquals(a5, Meld.checkMelds(hand5));
		Assert.assertEquals(a6, Meld.checkMelds(hand6));


	}


}
