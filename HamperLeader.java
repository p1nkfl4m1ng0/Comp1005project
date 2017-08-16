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
		boolean leaderInFront = (((players.indexOf(leader)-1 + players.size())%players.size() == players.indexOf(this)) && !Crazy8Game.reverseOrder) 
								|| ((players.indexOf(leader)+1 == players.indexOf(this)) && Crazy8Game.reverseOrder);
		
		boolean leaderBehind = (((players.indexOf(leader)+ 1)%players.size() == players.indexOf(this)) && !Crazy8Game.reverseOrder) 
								|| ((players.indexOf(leader)-1 == players.indexOf(this)) && Crazy8Game.reverseOrder);
		
		
		int[] suitsPowers = {0,0,0,0};
		int powerSuit = 0;
		/* if the next player is the leader 
		and the order of the game isn't reversed, then play a power card
		*/
	
		if (leaderInFront) {
			System.out.println("Leader's ahead: Player" + players.indexOf(leader));
			discard(2, discardPile, drawPile);
			discard(4, discardPile, drawPile);

			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getRank() == 2 || hand.get(i).getRank() == 4) {
					switch(hand.get(i).getSuit()) {
					case "Diamonds" :
						suitsPowers[0]++;
						break;
					case "Clubs" :
						suitsPowers[1]++;
						break;
					case "Hearts" :
						suitsPowers[2]++;
						break;
					case "Spades" :
						suitsPowers[3]++;
						break;
					}
				}
			}
			
			for (int j = 0; j < suitsPowers.length; j++){
				if (suitsPowers[j] > suitsPowers[powerSuit]) {
					powerSuit = j;
				}
			}
			
			if (suitsPowers[powerSuit] > 0) {
				discard(8, discardPile, drawPile);
			}
		}
		else if (leaderBehind) {
			System.out.println("Leader's behind: Player "+ players.indexOf(leader));

			discard(7, discardPile, drawPile);
			
				for (int i = 0; i < hand.size(); i++) {
					if (hand.get(i).getRank() == 7) {
						switch(hand.get(i).getSuit()) {
						case "Diamonds" :
							suitsPowers[0]++;
							break;
						case "Clubs" :
							suitsPowers[1]++;
							break;
						case "Hearts" :
							suitsPowers[2]++;
							break;
						case "Spades" :
							suitsPowers[3]++;
							break;
						}
					}
				}
				for (int j = 0; j < suitsPowers.length; j++){
					if (suitsPowers[j] > suitsPowers[powerSuit]) {
						powerSuit = j;
					}
				}
				if (suitsPowers[powerSuit] > 0) {
					discard(8, discardPile, drawPile);
				}
			} else if (players.indexOf(leader) == players.indexOf(this)) {
				System.out.println("This player has the least cards - will play normally.");
			} else {
				System.out.println("Leader's out of reach: It is Player " + players.indexOf(leader));

			}
		
		System.out.println(hand);
		while (!playMade && !drawPile.empty()) {
			discard(discardPile, drawPile);	
			if (leaderInFront) {
				discard(2, discardPile, drawPile);
				discard(4, discardPile, drawPile);
			} else if (leaderBehind) {
				discard(7, discardPile, drawPile);
			}
		}
		
		for (int i = 0; i < suitsPowers.length; i++) {
			suitsPowers[i] = 0;
		}
		powerSuit = 0;
		
		if (leaderInFront) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getRank() == 2 || hand.get(i).getRank() == 4) {
					switch(hand.get(i).getSuit()) {
					case "Diamonds" :
						suitsPowers[0]++;
						break;
					case "Clubs" :
						suitsPowers[1]++;
						break;
					case "Hearts" :
						suitsPowers[2]++;
						break;
					case "Spades" :
						suitsPowers[3]++;
						break;
					}
				}
			}
		} else if (leaderBehind) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i).getRank() == 7) {
					switch(hand.get(i).getSuit()) {
					case "Diamonds" :
						suitsPowers[0]++;
						break;
					case "Clubs" :
						suitsPowers[1]++;
						break;
					case "Hearts" :
						suitsPowers[2]++;
						break;
					case "Spades" :
						suitsPowers[3]++;
						break;
					}
				}
			}
		} 
		if ((!leaderInFront && !leaderBehind) || suitsPowers[powerSuit] == 0) {
			for (int i = 0; i < hand.size(); i++) {
				switch (hand.get(i).getSuit()) {
				case "Diamonds" :
					suitsPowers[0]++;
					break;
				case "Clubs" :
					suitsPowers[1]++;
					break;
				case "Hearts" :
					suitsPowers[2]++;
					break;
				case "Spades" :
					suitsPowers[3]++;
					break;
				}
			}
		}
		for (int j = 0; j < suitsPowers.length; j++){
			if (suitsPowers[j] > suitsPowers[powerSuit]) {
				powerSuit = j;
			}
		}
		
		
		this.eight(Card.SUITS[powerSuit], discardPile);
		
		if( this.hand.size() == 0 ){return true;}
		return false;
	}
	
	Card discard(DiscardPile discardPile, Stack<Card> drawPile) {
		for (int i = 0; i < hand.size(); i++) {
			
			if ((hand.get(i).getSuit() == discardPile.top().getSuit() 
				|| hand.get(i).getRank() == discardPile.top().getRank()) 
				&& hand.get(i).getRank() != 8 
				&& hand.get(i).getRank() != 2 
				&& hand.get(i).getRank() != 4 
				&& hand.get(i).getRank() != 7) {
				System.out.println("HamperLeader's play is: " + hand.get(i));
				discardPile.add(this.hand.get(i));
				playMade = true;
				return this.hand.remove(i);
			}
		}
		
		for (int i = 0; i < hand.size(); i++) {
			
			if (hand.get(i).getRank() == 8 ) {
				System.out.println("HamperLeader's play is: " + hand.get(i));
				discardPile.add(this.hand.get(i));
				playMade = true;
				return this.hand.remove(i);
			}
		}
		if (!drawPile.empty()) {
			hand.add(drawPile.pop());
			System.out.println("HamperLeader Draws: " + hand.get(hand.size()-1));
			return null;
		}

		return null;
	}
	
	Card discard(int inputRank, DiscardPile discardPile, Stack<Card> drawPile) {
		if (!playMade) {
			for (int i = 0; i < hand.size(); i++) {
				if ((hand.get(i).getSuit() == discardPile.top().getSuit() || hand.get(i).getRank() == discardPile.top().getRank()) 
					&& hand.get(i).getRank() == inputRank) {
					
					discardPile.add(this.hand.get(i));
					System.out.println("HamperLeader's play is: " + hand.get(i));
					playMade = true;
					return this.hand.remove(i);
				}
			}
		}
		return null;
	}
}
