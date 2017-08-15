import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class DiscardHighPointsPlayer extends Player{
    protected static int diamondCount;
    protected static int clubCount;
    protected static int spadeCount;
    protected static int heartCount;

    public DiscardHighPointsPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}

    public static boolean eightIsPlayed(DiscardPile discardPile, Player player){
        for (int i = 0; i < player.hand.size(); i++) {
            if (player.hand.get(i).getRank() == 8) {
                System.out.println(player.hand);
                System.out.println("This Players Play is: " + player.hand.get(i) + "\n");
                discardPile.add(player.hand.remove(i));
                return true;
            }

        }
        return false;
    }


    /* play a card */
    public boolean play(DiscardPile       discardPile,
                        Stack<Card>       drawPile,
                        ArrayList<Player> players)
    {
        while(true) {
            Card cardToMatch;
            diamondCount = 0;
            clubCount = 0;
            spadeCount = 0;
            heartCount = 0;

            //Check which suit has most points in current hand
            for (int i = 0; i < getSizeOfHand(); i++) {
                if (hand.get(i).getRankString() == "8") {
                    if (hand.get(i).getSuit() == "Diamonds") {
                        diamondCount += 50;
                    } else if (hand.get(i).getSuit() == "Clubs") {
                        clubCount += 50;
                    } else if (hand.get(i).getSuit() == "Spades") {
                        spadeCount += 50;
                    } else if (hand.get(i).getSuit() == "Hearts") {
                        heartCount += 50;
                    }
                } else if (hand.get(i).getRankString() == "4" || hand.get(i).getRankString() == "2") {
                    if (hand.get(i).getSuit() == "Diamonds") {
                        diamondCount += 25;
                    } else if (hand.get(i).getSuit() == "Clubs") {
                        clubCount += 25;
                    } else if (hand.get(i).getSuit() == "Spades") {
                        spadeCount += 25;
                    } else if (hand.get(i).getSuit() == "Hearts") {
                        heartCount += 25;
                    }
                } else if (hand.get(i).getRankString() == "7") {
                    if (hand.get(i).getSuit() == "Diamonds") {
                        diamondCount += 20;
                    } else if (hand.get(i).getSuit() == "Clubs") {
                        clubCount += 20;
                    } else if (hand.get(i).getSuit() == "Spades") {
                        spadeCount += 20;
                    } else if (hand.get(i).getSuit() == "Hearts") {
                        heartCount += 20;
                    }
                } else if (hand.get(i).getRankString() == "Jack" || hand.get(i).getRankString() == "Queen" || hand.get(i).getRankString() == "King" || hand.get(i).getRankString() == "10") {
                    if (hand.get(i).getSuit() == "Diamonds") {
                        diamondCount += 10;
                    } else if (hand.get(i).getSuit() == "Clubs") {
                        clubCount += 10;
                    } else if (hand.get(i).getSuit() == "Spades") {
                        spadeCount += 10;
                    } else if (hand.get(i).getSuit() == "Hearts") {
                        heartCount += 10;
                    }
                } else if (hand.get(i).getRankString() == "Ace") {
                    if (hand.get(i).getSuit() == "Diamonds") {
                        diamondCount += 1;
                    } else if (hand.get(i).getSuit() == "Clubs") {
                        clubCount += 1;
                    } else if (hand.get(i).getSuit() == "Spades") {
                        spadeCount += 1;
                    } else if (hand.get(i).getSuit() == "Hearts") {
                        heartCount += 1;
                    }
                } else {
                    if (hand.get(i).getSuit() == "Diamonds") {
                        diamondCount += hand.get(i).getRank();
                    } else if (hand.get(i).getSuit() == "Clubs") {
                        clubCount += hand.get(i).getRank();
                    } else if (hand.get(i).getSuit() == "Spades") {
                        spadeCount += hand.get(i).getRank();
                    } else if (hand.get(i).getSuit() == "Hearts") {
                        heartCount += hand.get(i).getRank();
                    }
                }
            }

            hand.sort(Card::compareTo);


            boolean retrn = false;
            if (eightIsPlayed(discardPile, this)){

                String suitToPlay = this.hand.get(0).getSuit(); //Chooses the suit with the highest points: It is at the begginning of the array
                //Check to see if an 8 was played and allows player to change suits:
                eight(suitToPlay,discardPile);


                if( this.hand.size() == 0 ){return true;}
                return false;
            }
            else {
                retrn = playValid(discardPile,drawPile,players);
                if (this.hand.size() !=0){
                    String suitToPlay = hand.get(0).getSuit();
                    eight(suitToPlay,discardPile);
                }
                
                return retrn;
            }
        }
    }
}
