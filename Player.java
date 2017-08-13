import java.util.Scanner; //if needed here
import java.util.ArrayList;
import java.util.Stack;

public abstract class Player{
    protected ArrayList<Card> hand;

    public int getSizeOfHand(){
        return hand.size();
    }
    public ArrayList<Card> getHand(){return this.hand;}

    public boolean playValid(DiscardPile discardPile, Stack<Card> drawPile, ArrayList<Player> players){

        boolean playMade = false;
        System.out.println(hand);

        while (!playMade) {
            for (int i = 0; i <= hand.size(); i++) {
                if(!drawPile.empty()){

                    //If there is not a valid card to play, this picks up another card:
                    if (i == hand.size()) {
                        hand.add(drawPile.pop());
                        System.out.println("This Player Draws: " + hand.get(hand.size()-1));
                        break;
                    }

                    //Finds A Card In Your Hand And Plays It:
                    if (  (hand.get(i).getSuit().equals(discardPile.top().getSuit())) || (hand.get(i).getRank() == discardPile.top().getRank()) || (hand.get(i).getRank() == 8)) {
                        System.out.println("This Player's play is: " + hand.get(i));
                        discardPile.add(this.hand.remove(i));
                        playMade = true;
                        break;
                    }
                }
                else{return true;}
            }
        }
     
        if( this.hand.size() == 0 ){return true;}
        return false;
    }
    
    //new method to for playing 8's: Essentially, me and Will had to figure out which suit we needed to play, using some code in your Player classes
    //and used that suit for the parameter below. This method makes the new 8 placed in the discard pile of the suit parameter.
    public Card eight(String suit, DiscardPile discardPile){
      if(discardPile.top().getRank() == 8){
           discardPile.add(new Card(suit, "8"));
           return null;
        }
      return null;
    }
    
    

    /* play a card  */
    public abstract boolean play(DiscardPile       discardPile,
                                 Stack<Card>       drawPile,
                                 ArrayList<Player> players);
    // return true if player wins game by playing last card
    // returns false otherwise
    // side effects: plays a card to top of discard Pile, possibly taking zero
    //               or more cards from the top of the drawPile
    //               card played must be valid card

}
