import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

//something here.

public class BadPlayer extends Player{
    public static int diamondCount;
    public static int clubCount;
    public static int spadeCount;
    public static int heartCount;



    public BadPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}

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


            //if in the correct suit with most points
            cardToMatch = discardPile.top();
            int numberOfMatched = 0;
            int cardIndex = 0;

            for (int j = 0; j < getSizeOfHand(); j++) {
                if (this.hand.get(j).getRank() == cardToMatch.getRank() || this.hand.get(j).getSuit() == cardToMatch.getSuit()) {
                    numberOfMatched = 0;
                    cardIndex = j;
                    break;
                }
                else {
                    numberOfMatched++;
                }
            }

            if (numberOfMatched == 0) {
                System.out.println("This is current hand: " + hand);
                System.out.println("it's diamond points: " + diamondCount);
                System.out.println("it's club points: " + clubCount);
                System.out.println("it's spades points: " + spadeCount);
                System.out.println("it's hyearts points: " + heartCount);
                discardPile.add(this.hand.remove(cardIndex));
                if (this.hand.size() == 0) {
                    return true;
                }
                return false;
            }
            else {
                if (!drawPile.empty()){
                    hand.add(drawPile.pop());
                }
                else{
                    return true;
                }

            }

            /*System.out.println("This is current hand: " + hand);
            System.out.println("it's diamond points: " + diamondCount);
            System.out.println("it's club points: " + clubCount);
            System.out.println("it's spades points: " + spadeCount);
            System.out.println("it's hyearts points: " + heartCount);
            discardPile.add(this.hand.remove(0));
            if( this.hand.size() == 0 ){return true;}
            return false;*/
        }
    }


}
