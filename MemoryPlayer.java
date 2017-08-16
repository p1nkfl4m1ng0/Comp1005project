import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class MemoryPlayer extends Player {
  
  public MemoryPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}
  
  public String bestSuit(DiscardPile discardPile) {
    String bestSuit;
    System.out.println("An 8 has been played. The player has to decide what suit to switch to.");
    getcountSuits(discardPile);
    
    bestSuit = Card.SUITS[2];
    System.out.println("The player switches the suit to: " + bestSuit);
    return bestSuit;
  }
  
  public int[] getcountRanks(DiscardPile discardPile) {
      // Create memory as all cards in the discard pile and player's current hand
      ArrayList<Card> memory = new ArrayList<Card>(); 
      while (!discardPile.empty()){
        memory.add(discardPile.remove());
      }
      for (int i=memory.size()-1; i>=0; i--) {
        discardPile.add(memory.get(i));
      }
      for (int i=0; i<hand.size(); i++) {
        memory.add(hand.get(i));
      }
      System.out.println("Looking at cards in memory ... " + memory);
      
      //** gen counts of ranks played in memory
      int[] countRanks = new int[15]; //will contain counts for each rank played.     
      
      //look through memory and populate countRanks
      for (int i=0; i<Card.RANKS.length ; i++) {
        for (int j=0; j< memory.size(); j++){
          if (memory.get(j).getRankString()==Card.RANKS[i]) {
            countRanks[i]++;
            //System.out.println("countRanks " + i + ": " + countRanks[i]);
          }
        }
      }
     System.out.println("frequency of ranks seen: " + Arrays.toString(countRanks));
     return countRanks;
  }
  
  public int[] getcountSuits(DiscardPile discardPile) {
      // Create memory as all cards in the discard pile and player's current hand
      ArrayList<Card> memory = new ArrayList<Card>(); 
      while (!discardPile.empty()){
        memory.add(discardPile.remove());
      }
      for (int i=memory.size()-1; i>=0; i--) {
        discardPile.add(memory.get(i));
      }
      
      for (int i=0; i<hand.size(); i++) {
        memory.add(hand.get(i));
      }
      System.out.println("Looking at cards in memory ... " + memory);
      
      //** gen counts of suits played in memory
      int[] countSuits = new int[5];  //will contain counts for each suit played.
      
      for (int i=0; i<Card.SUITS.length ; i++) {
        for (int j=0; j< memory.size(); j++){
          if (memory.get(j).getSuit()==Card.SUITS[i]) {
            countSuits[i]++;
          }
        }
      }
      
      System.out.println("frequency of suits seen: " + Arrays.toString(countSuits));
      return countSuits;
  }
  
  public boolean play(DiscardPile       discardPile,
                      Stack<Card>       drawPile,
                      ArrayList<Player> players) {
    
    boolean retrn=false;
    
    // Figure out what cards I have that are playable
    // Note that I don't consider an 8 as playable off the bat. I'll only play it I have nothing else I can play.
    ArrayList<Card> playable = new ArrayList<Card>();
    for (int i = 0; i < hand.size(); i++) {
      if ((hand.get(i).getSuit() == discardPile.top().getSuit()) || (hand.get(i).getRank() == discardPile.top().getRank())){
        playable.add(hand.get(i));
      }
    }
    System.out.println("playable cards are: " + playable);
  
    // If at most one card is playable, do what the RandomPlayer would do (ie. play that card or keep drawing until you have something to play)
    if (playable.size()<2) {
      retrn = playValid(discardPile,drawPile,players);
      eight(bestSuit(discardPile),discardPile);
      return retrn;
    }
    
    // If more than one card is playable, decide which one is best to play
    if (playable.size()>=2) {
      
      // First look across cards with the same rank. If there is at least one card that matches on rank, I will play it.
      ArrayList<Card> playableByRank = new ArrayList<Card>(); //create arraylist of cards with same rank
      for (int i=0; i<playable.size(); i++) {
        if (discardPile.top().getRank()==playable.get(i).getRank()){
          playableByRank.add(playable.get(i));
        }
      }
      System.out.println("playableByRank " + playableByRank);
      //If there is only one card with the same rank, play it. 
      if (playableByRank.size()==1) {
        Card cardToPlayByRank = new Card(playableByRank.get(0).getSuit(), playableByRank.get(0).getRankString()); 
        cardToPlayByRank = playableByRank.get(0);
        hand.remove(cardToPlayByRank);
        hand.add(0, cardToPlayByRank);
        retrn = playValid(discardPile,drawPile,players);
        eight(bestSuit(discardPile),discardPile);
        return retrn;        
      }
      // If there are multiple cards of the same rank, play the one whose suit has been seen the most
      else if (playableByRank.size()>1) {
        System.out.println("Multiple cards with the same rank can be played. They are: " + playableByRank);
        int[] countSuits = getcountSuits(discardPile);
        Card cardToPlayByRank = new Card(playableByRank.get(0).getSuit(), playableByRank.get(0).getRankString()); 
        cardToPlayByRank = playableByRank.get(0); //pick the first card to play in case nothing is better than it.
        int highest = 0;
        for (int i=0; i<playableByRank.size(); i++) {
          for (int j=0; j<Card.SUITS.length; j++) {
            if (playableByRank.get(i).getSuit()==Card.SUITS[j]){
              System.out.println("The suit of this card " + playableByRank.get(i) + " has been seen this many times: " + countSuits[j]);
              
              if (countSuits[j]>=highest) {
                highest = countSuits[j];
                System.out.println("Best card thusfar: " + playableByRank.get(i));
                cardToPlayByRank = playableByRank.get(i);
              }
            }
          }
        }
        
        System.out.println("Best card to play of all those with same rank: " + cardToPlayByRank);
        hand.remove(cardToPlayByRank);
        hand.add(0, cardToPlayByRank);

        retrn = playValid(discardPile,drawPile,players);
        eight(bestSuit(discardPile),discardPile);
        return retrn;     
      }
      
      
      // Given there were no cards of the same rank, look across cards with the same suit. 
      ArrayList<Card> playableBySuit = new ArrayList<Card>(); //create arraylist of cards with same suit
      for (int i=0; i<playable.size(); i++) {
        if (discardPile.top().getSuit()==playable.get(i).getSuit()){
          playableBySuit.add(playable.get(i));
        }
      }
      System.out.println("playableBySuit " + playableBySuit);
      // If there are multiple cards of the same rank, play the one whose suit has been seen the most
      if (playableBySuit.size()>1) {
        System.out.println("Multiple cards with the same suit can be played. They are: " + playableBySuit);
        int[] countRanks = getcountRanks(discardPile);
        Card cardToPlayBySuit = new Card(playableBySuit.get(0).getSuit(), playableBySuit.get(0).getRankString()); 
        cardToPlayBySuit = playableBySuit.get(0); //pick the first card to play in case nothing is better than it.
        int highest = 0;
        for (int i=0; i<playableBySuit.size(); i++) {
          for (int j=0; j<Card.RANKS.length; j++) {
            if (playableBySuit.get(i).getRankString()==Card.RANKS[j]){
              System.out.println("The rank of this card " + playableBySuit.get(i) + " has been seen this many times: " + countRanks[j]);
              
              if (countRanks[j]>=highest) {
                highest = countRanks[j];
                //System.out.println("Best card thusfar: " + playableBySuit.get(i));
                cardToPlayBySuit = playableBySuit.get(i);
              }
            }
          }
        }
        
        System.out.println("Best card to play of all those with same suit: " + cardToPlayBySuit);
        hand.remove(cardToPlayBySuit);
        hand.add(0, cardToPlayBySuit);

        retrn = playValid(discardPile,drawPile,players);
        eight(bestSuit(discardPile),discardPile);
        return retrn;     
        
      }
      
    }
      return retrn;
  }
   
}
