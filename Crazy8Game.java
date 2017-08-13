import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;

public class Crazy8Game{
    public static boolean reverseOrder = false;

    public static void main(String[] args){

  /* create the deck */
        Card[] deck = new Card[52];
        int index = 0;
        for(int r=2; r<=14; r+=1){
            for(int s=0; s<4; s+=1){
                deck[index++] = new Card(Card.SUITS[s], Card.RANKS[r]);
            }
        }

  /* shuffle the deck */
        Random rnd = new Random();
        Card swap;
        for(int i = deck.length-1; i>=0; i=i-1){
            int pos = rnd.nextInt(i+1);
            swap = deck[pos];
            deck[pos] = deck[i];
            deck[i] = swap;
        }

  /* players in the game */
        Player[] players = new Player[4];
        players[0] = new DiscardHighPointsPlayer( Arrays.copyOfRange(deck, 0, 5) );
        System.out.println("0 : " + Arrays.toString( Arrays.copyOfRange(deck, 0, 5)));
        players[1] = new DiscardHighPointsPlayer( Arrays.copyOfRange(deck, 5, 10) );
        System.out.println("1 : " + Arrays.toString( Arrays.copyOfRange(deck, 5, 10)));
        players[2] = new DiscardHighPointsPlayer( Arrays.copyOfRange(deck, 10, 15) );
        System.out.println("2 : " + Arrays.toString( Arrays.copyOfRange(deck, 10, 15)));
        players[3] = new MemoryPlayer( Arrays.copyOfRange(deck, 15, 20) );
        System.out.println("3 : " + Arrays.toString( Arrays.copyOfRange(deck, 15, 20)));

  /* discard and draw piles */
        DiscardPile discardPile = new DiscardPile();
        Stack<Card> drawPile = new Stack<Card>();
        for(int i=20; i<deck.length; i++){
            drawPile.push(deck[i]);
        }

        System.out.println("draw pile is : " + Arrays.toString( Arrays.copyOfRange(deck, 20, deck.length) ));

        deck = null;


        boolean win = false;

        int skip = 1; //determines if should skip or not. 1 = normal passing, 2 = skip a turn.
        int player = -1;    // start game play with player 0

        ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players));
        discardPile.add( drawPile.pop() );

        while( !win ){

            if(reverseOrder){
                 player = (player - skip + players.length) % players.length;
                    System.out.println(player);//changed player-1 to player-skip
            }
            else {player = (player + skip) % players.length;
            System.out.println(player);} //changed player +1 to player + skip
            System.out.println();
            System.out.println("player " + player);
            System.out.println("draw pile    : " + drawPile.peek() );
            System.out.println("discard pile : " + discardPile.top() );

            win = people.get(player).play(discardPile, drawPile, people);
            System.out.println("discard pile : " + discardPile.top() );

            //PowerCard 7's:
            if (discardPile.top().getRank() == 7) {
                reverseOrder = !reverseOrder;
            }

            //PowerCard consequences for 4's and 2's:
            if (discardPile.top().getRank() == 4 || discardPile.top().getRank() == 2 ){
                skip = 2;
                if (discardPile.top().getRank() == 2) {
                    if(reverseOrder){

                            if(!drawPile.empty()) { //Needed in case there aren't enough cards left in the deck to hand out, giving a EmptyStackException (Exception Handling?)
                                people.get((player - 1 + players.length)% players.length).hand.add(drawPile.pop());
                            }
                            if(!drawPile.empty()) {
                                people.get((player - 1 + players.length)% players.length).hand.add(drawPile.pop());
                            } 
                    }
                    else {
                        if(!drawPile.empty()) {
                            people.get((player + 1) % players.length).hand.add(drawPile.pop());
                        }
                        if(!drawPile.empty()) {
                            people.get((player + 1) % players.length).hand.add(drawPile.pop());
                        }
                    }
                }
            }
            else {skip = 1;}

            //A check to see if 2 cards are handed out, and then the drawpile is empty. This is needed for when the drawpile needs to be accessed to see the next
            //card on the drawpile to be able to know which card to play. In trying to display the card from the drawpile, the program crashes since the drawpile is empty.
            if(drawPile.empty()){break;}

        }

        if (drawPile.empty()) { // if drawPile is empty, add up points and declare winner as topScorer
            int topScorer = 0;
            int topScore = 0;
            int score;

            for (int i=0; i<people.size(); i++){
                System.out.println("player " + i + "'s hand is: " + people.get(i).getHand());
                score = 0;
                for (int j=0; j<people.get(i).getSizeOfHand(); j++) {
                    score = score + people.get(i).getHand().get(j).getRank();
                    if (score>=topScore) {
                        topScore = score;
                        topScorer = i;
                    }
                }
            }
            System.out.println("winner is player " + topScorer + " with this many points: " + topScore);
        }
        else {
            System.out.println("winner is player " + player);
        }
    }
}
