import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class MemoryPlayer extends Player {
  
  public MemoryPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
  
  public boolean play(DiscardPile       discardPile, 
                      Stack<Card>       drawPile, 
                      ArrayList<Player> players)
  {
    boolean playMade = false;
    System.out.println("MemoryPlayer's hand is: " + hand);
    
    
    // Figure out what cards I have that are playable
    ArrayList<Card> playable = new ArrayList<Card>();
    for (int i = 0; i < hand.size(); i++) {
      if ((hand.get(i).getSuit() == discardPile.top().getSuit()) || (hand.get(i).getRank() == discardPile.top().getRank())){
        playable.add(hand.get(i));
      }
    }
    System.out.println("playable cards are: " + playable);
    
    // If nothing is playable, then draw a card
    if (playable.size()==0 && drawPile.empty()) { //if nothing is playable, but you have nothing to draw, return true
      return true;
    }
    else if (playable.size()==0 && !drawPile.empty()) {
      hand.add(drawPile.pop());
      System.out.println("MemoryPlayer Draws: " + hand.get(hand.size()-1));
      
      // If the card you just drew is playable, play it.
      if ((hand.get(hand.size()-1).getSuit() == discardPile.top().getSuit()) || 
          (hand.get(hand.size()-1).getRank() == discardPile.top().getRank())) {
        System.out.println("The card just drawn can be played. MemoryPlayer's play is: " + hand.get(hand.size()-1));
        discardPile.add(this.hand.remove(hand.size()-1));
        return false;
      }
    }
    
    // If only one card is playable, play that card
    else if (playable.size()==1) {
      System.out.println("Only one card can be played. MemoryPlayer's play is: " + playable.get(0));
      for (int i=0; i<hand.size(); i++){
        if (hand.get(i)==playable.get(0)){
          discardPile.add(this.hand.remove(i));
        }
      } 
    }
    //**** If more than one card is playable, decide which one is best to play
    else if (playable.size()>=2) {
      
      //** gen counts of ranks and suits played in discard pile
      int[] countRanks = new int[15]; //will contain counts for each rank played.
      int[] countSuits = new int[5];  //will contain counts for each suit played.
      
      //make an arraylist copy of discardPile
      ArrayList<Card> discardPileMemory = new ArrayList<Card>(); 
      while (!discardPile.empty()){
        discardPileMemory.add(discardPile.remove());
      }
      
      for (int i=0; i<discardPileMemory.size(); i++) {
        discardPile.add(discardPileMemory.get(i));
      }
      System.out.println("Multiple cards can be played. discardPileMemory is: " + discardPileMemory);
      
      //look through discardPileMemory and populate countRanks and countSuits
      for (int i=0; i<Card.RANKS.length ; i++) {
        for (int j=0; j< discardPileMemory.size(); j++){
          if (discardPileMemory.get(j).getRankString()==Card.RANKS[i]) {
            countRanks[i]++;
            //System.out.println("countRanks " + i + ": " + countRanks[i]);
          }
        }
      }
      for (int i=0; i<Card.SUITS.length ; i++) {
        for (int j=0; j< discardPileMemory.size(); j++){
          if (discardPileMemory.get(j).getSuit()==Card.SUITS[i]) {
            countSuits[i]++;
            // System.out.println("countSuits: " + i + ": " + countSuits[i]);
          }
        }
      }
      
      //**code to decide which card to play
      
      //*Look across cards with the same rank
      ArrayList<Card> playableByRank = new ArrayList<Card>(); //create arraylist of cards with same rank
      for (int i=0; i<playable.size(); i++) {
        if (discardPile.top().getRank()==playable.get(i).getRank()){
          playableByRank.add(playable.get(i));
        }
      }
      
      System.out.println("Multiple cards with the same rank can be played. They are: " + playableByRank);
      
      if (playableByRank.size()!=0) {
        ArrayList<Card> cardToPlayByRank = new ArrayList<Card>(); 
        cardToPlayByRank.add(playableByRank.get(0)); //pick the first card to play in case nothing is better than it.
        int highest = 0;
        for (int i=0; i<playableByRank.size(); i++) {
          for (int j=0; j<Card.SUITS.length; j++) {
            if (playableByRank.get(i).getSuit()==Card.SUITS[j]){
              System.out.println("The suit of this card " + playableByRank.get(i) + " has been seen this many times: " + countSuits[j]);
              
              if (countSuits[j]>=highest) {
                System.out.println("Best card is that whose suite has been seen the most thusfar: " + countSuits);
                cardToPlayByRank.add(0, playableByRank.get(i));
              }
            }
          }
        }
        System.out.println("Best card to play of all those with same rank: " + cardToPlayByRank);
        for (int i=0; i<hand.size(); i++){
          if (hand.get(i)==cardToPlayByRank.get(0)){
            discardPile.add(this.hand.remove(i));
          }
        }
      }
      
      //*Look across cards with same suit
      ArrayList<Card> playableBySuit = new ArrayList<Card>(); //create arraylist of cards with same suit
      for (int i=0; i<playable.size(); i++) {
        if (discardPile.top().getSuit()==playable.get(i).getSuit()){
          playableBySuit.add(playable.get(i));
        }
      }
      
      System.out.println("Multiple cards with the same suit can be played. They are: " + playableBySuit);
      
      if (playableBySuit.size()!=0) {
        ArrayList<Card> cardToPlayBySuit = new ArrayList<Card>();
        cardToPlayBySuit.add(playableBySuit.get(0)); //pick the first card to play in case nothing is better than it.
        int highest = 0;
        for (int i=0; i<playableBySuit.size(); i++) {
          for (int j=0; j<Card.SUITS.length; j++) {
            if (playableBySuit.get(i).getRankString()==Card.RANKS[j]){
              System.out.println("The rank of this card " + playableBySuit.get(i) + " has been seen this many times: " + countRanks[j]);
              if (countRanks[j]>=highest) {
                System.out.println("Best card is that whose rank has been seen the most thusfar: " + countRanks);
                cardToPlayBySuit.add(0, playableBySuit.get(i));
              }
            }
          }
        }
        System.out.println("Best card to play of all those with same suit: " + cardToPlayBySuit);
        for (int i=0; i<hand.size(); i++){
          if (hand.get(i)==cardToPlayBySuit.get(0)){
            discardPile.add(this.hand.remove(i));
          }
        }
      }
    }
    
    // end game if no more cards in hand or no more cards to pick up from drawPile
    if( this.hand.size() == 0 || drawPile.empty()){
      return true;
    }
    return false;
  }
}
