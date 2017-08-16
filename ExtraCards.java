import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class ExtraCards extends Player{
    
    public ExtraCards (Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
    
    }

    public boolean play(DiscardPile       discardPile,
                        Stack<Card>       drawPile,
                        ArrayList<Player> players)
    {

      boolean playMade = false;
		Player nextPlayer;
		
		if (!reverseOrder) {
			nextPlayer = players.get((players.indexOf(this) + 1) % players.length());
		} else {
			nextPlayer = players.get((players.indexOf(this) - 1 + players.length() ) % players.length());
		}

		
		if (nextPlayer.getSizeOfHand() == 1) {
			while (!playMade && !drawPile.empty()) {
				this.discard(2, discardPile, drawPile);
				this.discard(4, discardPile, drawPile);
				if (!playMade && !drawPile.empty()) {
					hand.add(drawPile.pop());
				}
			}
        } else {
			while(!playMade) {
				this.discard(discardPile, drawPile);
			}
		}


	  }
    //risk taking cards from the draw pile to get power cards
    //
    //if the next player only has one 1 card left, keep picking a card until a power card, if they do not have one
    //already
    //
    //no more than one extra card in the early rounds of the game
    //will not take extra cards if power cards are in hand
    
    
	Card discard(int inputRank, DiscardPile discardPile, Stack<Card> drawPile) {
		if (!playMade) {
			for (int i = 0; i < hand.size(); i++) {
				if ((hand.get(i).getSuit() == discardPile.top().getSuit() || hand.get(i).getRank() == discardPile.top().getRank()) 
					&& hand.get(i).getRank() == inputRank) {
					
					discardPile.add(this.hand.get(i));
					System.out.println("ExtraCards's play is: " + hand.get(i));
					playMade = true;
					return this.hand.remove(i);
				}
			}
		}
		return null;
	}
	
	Card discard(DiscardPile discardPile, Stack<Card> drawPile) {
		for (int i = 0; i < hand.size(); i++) {
			
			if ((hand.get(i).getSuit() == discardPile.top().getSuit() 
				|| hand.get(i).getRank() == discardPile.top().getRank()) 
				&& hand.get(i).getRank() != 2 
				&& hand.get(i).getRank() != 4
				&& hand.get(i).getRank() != 8) {
				System.out.println("ExtraCards's play is: " + hand.get(i));
				discardPile.add(this.hand.get(i));
				playMade = true;
				return this.hand.remove(i);
			}
		}
		
		for (int i = 0; i < hand.size(); i++) {
			
			if (hand.get(i).getRank() == 8 ) {
				System.out.println("ExtraCards's play is: " + hand.get(i));
				discardPile.add(this.hand.get(i));
				playMade = true;
				return this.hand.remove(i);
			}
		}
		
		if (!drawPile.empty()) {
			hand.add(drawPile.pop());
			System.out.println("ExtraCards Draws: " + hand.get(hand.size()-1));
			return null;
		}

		return null;
	}
}

