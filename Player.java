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
                    if (hand.get(i).getSuit().equals(discardPile.top().getSuit()) || hand.get(i).getRank() ==(discardPile.top().getRank())) {
                        System.out.println("This Player's play is: " + hand.get(i));
                        discardPile.add(this.hand.remove(i));
                        playMade = true;
                        break;
                    }
                }
                else{return true;}
            }
        }
        //Check to see if an 8 was played and allows player to change suits:
        Scanner keyboard = new Scanner(System.in);
        if(discardPile.top().getRank() == 8){
            System.out.println("Please enter the desired suit");
            String input = keyboard.nextLine();
            discardPile.add(new Card(input, "8"));
        }
        if( this.hand.size() == 0 ){return true;}
        return false;
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