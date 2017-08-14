import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;

public class Crazy8Game {
    public static boolean reverseOrder = false;

    public static boolean twoPlayer = true;
    public static boolean threePlayer = false;
    public static boolean fourPlayer = false;


    //Method to calculate value of a hand - used for determining winner and keeping scores
    public static int handValue(ArrayList<Card> hand) {
        int value = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getRankString().equals("Jack") || hand.get(i).getRankString().equals("Queen") || hand.get(i).getRankString().equals("King")) {
                value = value + 10;
            } else if (hand.get(i).getRankString().equals("Ace")) {
                value = value + 1;
            } else if (hand.get(i).getRankString().equals("8")) {
                value = value + 50;
            } else if (hand.get(i).getRankString().equals("2") || hand.get(i).getRankString().equals("4")) {
                value = value + 25;
            } else if (hand.get(i).getRankString().equals("7")) {
                value = value + 20;
            } else if (hand.get(i).getRankString().equals("3")) {
                value = value + 3;
            } else if (hand.get(i).getRankString().equals("5")) {
                value = value + 5;
            } else if (hand.get(i).getRankString().equals("6")) {
                value = value + 6;
            } else if (hand.get(i).getRankString().equals("9")) {
                value = value + 9;
            } else if (hand.get(i).getRankString().equals("10")) {
                value = value + 10;
            }
        }
        return value;
    }

    public static void main(String[] args) {


            /* create the deck */
            Card[] deck = new Card[52];
            int index = 0;
            for (int r = 2; r <= 14; r += 1) {
                for (int s = 0; s < 4; s += 1) {
                    deck[index++] = new Card(Card.SUITS[s], Card.RANKS[r]);
                }
            }

            /* shuffle the deck */
            Random rnd = new Random();
            Card swap;
            for (int i = deck.length - 1; i >= 0; i = i - 1) {
                int pos = rnd.nextInt(i + 1);
                swap = deck[pos];
                deck[pos] = deck[i];
                deck[i] = swap;
            }

            /* Some Declarations */
            Player[] players;
            DiscardPile discardPile;
            Stack<Card> drawPile;

            /* Running the 2P Game: 2 Players play with 7-cards each */
            if (twoPlayer) {
                //Welcome to the game
                System.out.println("\nWelcome to Crazy 8's!\n");

                //Players in the game:
                players = new Player[2];
                players[0] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 0, 7));
                System.out.println("Player 0's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 0, 7)));
                players[1] = new RandomPlayer(Arrays.copyOfRange(deck, 7, 14));
                System.out.println("Player 1's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 7, 14)));

                discardPile = new DiscardPile();
                drawPile = new Stack<Card>();

                /* Pushes the rest of the Deck into the DrawPile */
                for (int i = 14; i < deck.length; i++) {
                    drawPile.push(deck[i]);
                }

                System.out.println("... And the draw pile is : " + Arrays.toString(Arrays.copyOfRange(deck, 14, deck.length)));

            }
            /* Running the 3P Game */
            else if (threePlayer) {
                System.out.println("\nWelcome to Crazy 8's!\n");

                players = new Player[3];
                players[0] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 0, 5));
                System.out.println("Player 0's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 0, 5)));
                players[1] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 5, 10));
                System.out.println("Player 1's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 5, 10)));
                players[2] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 10, 15));
                System.out.println("Player 2's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 10, 15)));

                discardPile = new DiscardPile();
                drawPile = new Stack<Card>();

                for (int i = 15; i < deck.length; i++) {
                    drawPile.push(deck[i]);
                }

                System.out.println("... And the draw pile is : " + Arrays.toString(Arrays.copyOfRange(deck, 15, deck.length)));
            }
            /* Running the 4P Game */
            else {
                System.out.println("\nWelcome to Crazy 8's!\n");

              /* players in the game */
                players = new Player[4];
                players[0] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 0, 5));
                System.out.println("Player 0's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 0, 5)));
                players[1] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 5, 10));
                System.out.println("Player 1's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 5, 10)));
                players[2] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 10, 15));
                System.out.println("Player 2's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 10, 15)));
                players[3] = new DiscardHighPointsPlayer(Arrays.copyOfRange(deck, 15, 20));
                System.out.println("Player 3's Hand ---> : " + Arrays.toString(Arrays.copyOfRange(deck, 15, 20)));

              /* discard and draw piles */
                discardPile = new DiscardPile();
                drawPile = new Stack<Card>();
                for (int i = 20; i < deck.length; i++) {
                    drawPile.push(deck[i]);
                }

                System.out.println("... And the draw pile is : " + Arrays.toString(Arrays.copyOfRange(deck, 20, deck.length)));
            }

            //Empties the deck:
            deck = null;


            /* PLAYS THE ACTUAL GAME */
            /* ********************* */
            /*    **************     */
            /*        ******         */
            /*          **           */
            /*          ∨            */


            boolean win = false;
            int skip = 1; //determines if should skip or not. 1 = normal, 2 = skip a turn.
            int player = -1;    // start game play with player 0

            ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players)); //Populates an ArrayList with Players
            discardPile.add(drawPile.pop()); //Takes the first of the DrawPile and adds it to the DiscardPile

            while (!win) {

                /* Determining Which Player Goes */
                if (reverseOrder) {
                    player = (player - skip + players.length) % players.length;
                } else {
                    player = (player + skip) % players.length;
                }

                System.out.println("\n\nPLAYER:  " + player + "\n");
                System.out.println("Draw Pile    : " + drawPile.peek());
                System.out.println("Discard Pile : " + discardPile.top());

                win = people.get(player).play(discardPile, drawPile, people);

                //PowerCard 7's:
                if (discardPile.top().getRank() == 7) {
                    reverseOrder = !reverseOrder;
                }

                //PowerCard consequences for 4's and 2's:
                if (discardPile.top().getRank() == 4 || discardPile.top().getRank() == 2) {
                    skip = 2;
                    if (discardPile.top().getRank() == 2) {
                        if (reverseOrder) {

                            if (!drawPile.empty()) { //Needed in case there aren't enough cards left in the deck to hand out, giving a EmptyStackException (Exception Handling?)
                                people.get((player - 1 + players.length) % players.length).hand.add(drawPile.pop());
                            }
                            if (!drawPile.empty()) {
                                people.get((player - 1 + players.length) % players.length).hand.add(drawPile.pop());
                            }
                        } else {
                            if (!drawPile.empty()) {
                                people.get((player + 1) % players.length).hand.add(drawPile.pop());
                            }
                            if (!drawPile.empty()) {
                                people.get((player + 1) % players.length).hand.add(drawPile.pop());
                            }
                        }
                    }
                } else {
                    skip = 1;
                }

                //A check to see if 2 cards are handed out, and then the drawpile is empty. This is needed for when the drawpile needs to be accessed to see the next
                //card on the drawpile to be able to know which card to play. In trying to display the card from the drawpile, the program crashes since the drawpile is empty.
                if (drawPile.empty()) {
                    System.out.println("\nTHE DRAWPILE IS EMPTY!!!");
                    break;
                }

            }

            // Calculate value of each hand. The player with the smaller value (including empty hand) wins.
            int smallestHandValue = handValue(people.get(0).getHand()); //initialize with value from first player;
            int smallestHandPlayer = 0; //initialize with value from first player;

            System.out.println();
            System.out.println("*******RESULTS*******:");
            System.out.println();

            for (int i = 0; i < people.size(); i++) {
                System.out.println("player " + i + "'s hand is: " + people.get(i).getHand() + " and it is worth " + handValue(people.get(i).getHand()));

                if (handValue(people.get(i).getHand()) < smallestHandValue) {
                    smallestHandValue = handValue(people.get(i).getHand());
                    smallestHandPlayer = i;
                }
            }
            System.out.println("\nPLAYER " + smallestHandPlayer + " IS THE WINNER!\n");

            // To keep track of total scores ******************* needs modifications in order to work for multiple games
            int[] scores = new int[people.size()];
            for (int i = 0; i < people.size(); i++) {
                if (i != smallestHandPlayer) { //if the winner of the game still has cards, don't add their value to their total score.
                    scores[smallestHandPlayer] = scores[smallestHandPlayer] + handValue(people.get(i).getHand());
                }
            }
            for (int i = 0; i < people.size(); i++) {
                System.out.println("player " + i + "'s total score thusfar is: " + scores[i]);
            }

    }
}

