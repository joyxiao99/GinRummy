AUX = Card.class 'Card$$1.class' Computer.class DiscardPile.class GameOps.class GinRummy.class Hand.class Meld.class Player.class sortByRank.class sortBySR.class StockPile.class Suit.class UserInputOps.class

GinRummy:
	java -jar GinRummy.jar

code:
	cd src; javac GinRummy.java; \
	java GinRummy

clean:
	cd src;	rm $(AUX) # for cleaning reasons
