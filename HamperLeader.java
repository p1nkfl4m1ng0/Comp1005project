import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class HamperLeader extends Player {

	public HamperLeader(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
	
	static boolean playMade;
	public boolean play(DiscardPile       discardPile, 
	                    Stack<Card>       drawPile, 
											ArrayList<Player> players)
	{
		System.out.println("HamperLeader's Turn");
		playMade = false;
		// find the leader
		Player leader = players.get(0);
		for (int i = 1; i< players.size(); i++) {
			if (players.get(i).hand.size() < leader.hand.size()) {
				leader = players.get(i);
			}
		}
		
		//where's the leader?
		boolean leaderInFront = ((players.indexOf(leader)-1 == players.indexOf(this)) && !Crazy8Game.reverseOrder) 
								|| ((players.indexOf(leader)+1 == players.indexOf(this)) && Crazy8Game.reverseOrder);
		
		boolean leaderBehind = ((players.indexOf(leader)+1 == players.indexOf(this)) && !Crazy8Game.reverseOrder) 
								|| ((players.indexOf(leader)-1 == players.indexOf(this)) && Crazy8Game.reverseOrder);
		
		/* if the next player is the leader 
		and the order of the game isn't reversed, then play a power card
		*/
	
		if (leaderInFront) {
			discard(2, discardPile, drawPile);
			discard(4, discardPile, drawPile);
		}
		else if (leaderBehind) {
			discard(7, discardPile, drawPile);
		}		
		
		System.out.println(hand);
		while (!playMade && !drawPile.empty()) {
						discard(discardPile, drawPile);	
			}
		
		if( this.hand.size() == 0 ){return true;}
		return false;
	}
	
	Card discard(DiscardPile discardPile, Stack<Card> drawPile) {
		for (int i = 0; i <= hand.size(); i++) {
			if (i == hand.size()) {
				if (!drawPile.empty())
				hand.add(drawPile.pop());
				System.out.println("HamperLeader Draws: " + hand.get(hand.size()-1));
				return null;
			}
		
			if (hand.get(i).getSuit() == discardPile.top().getSuit() || hand.get(i).getRank() == discardPile.top().getRank()) {
				System.out.println("HamperLeader's play is: " + hand.get(i));
				discardPile.add(this.hand.get(i));
				playMade = true;
				return this.hand.remove(i);
			}
		}
		return null;
	}
	
	Card discard(int inputRank, DiscardPile discardPile, Stack<Card> drawPile) {
		while (!playMade) {
			for (int i = 0; i < hand.size(); i++) {
				if ((hand.get(i).getSuit() == discardPile.top().getSuit() || hand.get(i).getRank() == discardPile.top().getRank()) 
					&& hand.get(i).getRank() == inputRank) {
					
					discardPile.add(this.hand.get(i));
					playMade = true;
					return this.hand.remove(i);
				}
			}
		}
		return null;
	}
}