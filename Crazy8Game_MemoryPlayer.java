import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.Random;

public class Crazy8Game_MemoryPlayer{
    
  public static void main(String[] args){
    
    /* create the deck */
    Card[] deck = new Card[52];
    int index = 0;
    for(int r=2; r<=14; r+=1){
      for(int s=0; s<4; s+=1){
        deck[index++] = new Card(Card.SUITS[s], Card.RANKS[r]);
      }
    }
    
    Player[] players = new Player[1]; //for testing, ignore other players
    
    /****************** Test Case 1: When I have no cards to play, I keep drawing until one comes up.*/
    System.out.println("Test Case 1: When I have no cards to play, I keep drawing until one comes up.");
    System.out.println();
    
    // HAND 
    Card[] testHand1 = {new Card(Card.SUITS[0], Card.RANKS[2]), 
                       new Card(Card.SUITS[0], Card.RANKS[3]),
                       new Card(Card.SUITS[0], Card.RANKS[4])};
                                   
    players[0] = new MemoryPlayer(testHand1);
    System.out.println("MemoryPlayer's starting hand is : " + Arrays.toString(testHand1)); 
    
    //DISCARDPILE
    DiscardPile discardPile1 = new DiscardPile();
    discardPile1.add(new Card(Card.SUITS[1], Card.RANKS[5]));
    System.out.println("Last card on discard pile is: " + discardPile1.top());
    
    //DRAWPILE
    Stack<Card> drawPile1 = new Stack<Card>();
    drawPile1.push(new Card(Card.SUITS[1], Card.RANKS[4]));
    drawPile1.push(new Card(Card.SUITS[3], Card.RANKS[2]));
    drawPile1.push(new Card(Card.SUITS[4], Card.RANKS[3]));
    
    //PLAY
    ArrayList<Player> people = new ArrayList<Player>(Arrays.asList(players));
    people.get(0).play(discardPile1, drawPile1, people);
    System.out.println("Last card on discard pile is: " + discardPile1.top());
    System.out.println();
    System.out.println();
    
    /****************** Test Case 2: If I only have one card I can play, I'll play it.*/
    System.out.println("Test Case 2: If I only have one card I can play, I'll play it");
    System.out.println();
    
    // HAND 
    Card[] testHand2 = {new Card(Card.SUITS[0], Card.RANKS[2]), 
                       new Card(Card.SUITS[0], Card.RANKS[3]),
                       new Card(Card.SUITS[0], Card.RANKS[4])};
                                   
    players[0] = new MemoryPlayer(testHand2);
    System.out.println("MemoryPlayer's starting hand is : " + Arrays.toString(testHand2)); 
    
    //DISCARDPILE
    DiscardPile discardPile2 = new DiscardPile();
    discardPile2.add(new Card(Card.SUITS[1], Card.RANKS[2]));
    System.out.println("Last card on discard pile is: " + discardPile2.top());
    
    //DRAWPILE
    Stack<Card> drawPile2 = new Stack<Card>();
    drawPile2.push(new Card(Card.SUITS[4], Card.RANKS[3]));
    
    //PLAY
   ArrayList<Player> people2 = new ArrayList<Player>(Arrays.asList(players));
    people2.get(0).play(discardPile2, drawPile2, people2);
    System.out.println("Last card on discard pile is: " + discardPile2.top());
    System.out.println();
    System.out.println();
    
    /*** Test Case 3: If I have multiple cards of the same rank, I will play the one whose suit has been seen the most.*/
    System.out.println("Test Case 3: If I have multiple cards of the same rank, I will play the one whose suit has been seen the most.");
    System.out.println();
    
    // HAND 
    Card[] testHand3 = {new Card(Card.SUITS[0], Card.RANKS[2]),
                       new Card(Card.SUITS[2], Card.RANKS[2]),
                       new Card(Card.SUITS[1], Card.RANKS[2])};
                                   
    players[0] = new MemoryPlayer(testHand3);
    System.out.println("MemoryPlayer's starting hand is : " + Arrays.toString(testHand3)); 
    
    //DISCARDPILE
    DiscardPile discardPile3 = new DiscardPile();
    discardPile3.add(new Card(Card.SUITS[1], Card.RANKS[5]));
    discardPile3.add(new Card(Card.SUITS[1], Card.RANKS[6]));
    discardPile3.add(new Card(Card.SUITS[2], Card.RANKS[5]));
    discardPile3.add(new Card(Card.SUITS[2], Card.RANKS[6]));
    discardPile3.add(new Card(Card.SUITS[2], Card.RANKS[7]));
    discardPile3.add(new Card(Card.SUITS[3], Card.RANKS[2]));
    System.out.println("Last card on discard pile is: " + discardPile3.top());
    
    //DRAWPILE
    Stack<Card> drawPile3 = new Stack<Card>();
    drawPile3.push(new Card(Card.SUITS[4], Card.RANKS[3]));
    
    //PLAY
   ArrayList<Player> people3 = new ArrayList<Player>(Arrays.asList(players));
    people3.get(0).play(discardPile3, drawPile3, people3);
    System.out.println("Last card on discard pile is: " + discardPile3.top());
    System.out.println();
    System.out.println();
    
    /*** Test Case 4: If I have multiple cards of the same suit, I will play the one whose rank has been seen the most.*/
    System.out.println("Test Case 4: If I have multiple cards of the same suit, I will play the one whose rank has been seen the most.");
    System.out.println();
    
    // HAND 
    Card[] testHand4 = {new Card(Card.SUITS[0], Card.RANKS[2]),
                       new Card(Card.SUITS[0], Card.RANKS[3]),
                       new Card(Card.SUITS[0], Card.RANKS[4])};
                                   
    players[0] = new MemoryPlayer(testHand4);
    System.out.println("MemoryPlayer's starting hand is : " + Arrays.toString(testHand4)); 
    
    //DISCARDPILE
    DiscardPile discardPile4 = new DiscardPile();
    discardPile4.add(new Card(Card.SUITS[1], Card.RANKS[2]));
    discardPile4.add(new Card(Card.SUITS[2], Card.RANKS[2]));
    discardPile4.add(new Card(Card.SUITS[1], Card.RANKS[3]));
    discardPile4.add(new Card(Card.SUITS[2], Card.RANKS[3]));
    discardPile4.add(new Card(Card.SUITS[3], Card.RANKS[3]));
    discardPile4.add(new Card(Card.SUITS[0], Card.RANKS[5]));
    System.out.println("Last card on discard pile is: " + discardPile4.top());
    
    //DRAWPILE
    Stack<Card> drawPile4 = new Stack<Card>();
    drawPile4.push(new Card(Card.SUITS[4], Card.RANKS[3]));
    
    //PLAY
   ArrayList<Player> people4 = new ArrayList<Player>(Arrays.asList(players));
    people4.get(0).play(discardPile4, drawPile4, people4);
    System.out.println("Last card on discard pile is: " + discardPile4.top());
    System.out.println();
    System.out.println();
    
    // Test Case 5: If I have multiple cards that match on rank and suit, play one of the ones that match by rank, and pick
    //         the one whose suit has been seen the most.
    System.out.println("Test Case 5: If I have multiple cards that match on rank and suit, play one of the ones that match by rank, and pick the one whose suit has been seen the most.");
    System.out.println();
    
    // HAND 
    Card[] testHand5 = {new Card(Card.SUITS[0], Card.RANKS[2]),
                       new Card(Card.SUITS[0], Card.RANKS[3]),
                       new Card(Card.SUITS[2], Card.RANKS[5]),
                       new Card(Card.SUITS[3], Card.RANKS[5])};
                                   
    players[0] = new MemoryPlayer(testHand5);
    System.out.println("MemoryPlayer's starting hand is : " + Arrays.toString(testHand5)); 
    
    //DISCARDPILE
    DiscardPile discardPile5 = new DiscardPile();
    discardPile5.add(new Card(Card.SUITS[1], Card.RANKS[2]));
    discardPile5.add(new Card(Card.SUITS[2], Card.RANKS[2]));
    discardPile5.add(new Card(Card.SUITS[1], Card.RANKS[3]));
    discardPile5.add(new Card(Card.SUITS[2], Card.RANKS[3]));
    discardPile5.add(new Card(Card.SUITS[3], Card.RANKS[3]));
    discardPile5.add(new Card(Card.SUITS[0], Card.RANKS[5]));
    System.out.println("Last card on discard pile is: " + discardPile5.top());
    
    //DRAWPILE
    Stack<Card> drawPile5 = new Stack<Card>();
    drawPile5.push(new Card(Card.SUITS[4], Card.RANKS[3]));
    
    //PLAY
   ArrayList<Player> people5 = new ArrayList<Player>(Arrays.asList(players));
    people5.get(0).play(discardPile5, drawPile5, people5);
    System.out.println("Last card on discard pile is: " + discardPile5.top());
    System.out.println();
    System.out.println();
    

    // Test Case 6: If I play an 8, change suit to what has been seen most.
    System.out.println("Test Case 6: If I play an 8, change suit to what has been seen most.");
    System.out.println();
    
    // HAND 
    Card[] testHand6 = {new Card(Card.SUITS[0], Card.RANKS[2]), 
                       new Card(Card.SUITS[0], Card.RANKS[3]),
                       new Card(Card.SUITS[0], Card.RANKS[4])};
                                   
    players[0] = new MemoryPlayer(testHand6);
    System.out.println("MemoryPlayer's starting hand is : " + Arrays.toString(testHand6)); 
    
    //DISCARDPILE
    DiscardPile discardPile6 = new DiscardPile();
    discardPile6.add(new Card(Card.SUITS[1], Card.RANKS[5]));
    System.out.println("Last card on discard pile is: " + discardPile6.top());
    
    //DRAWPILE
    Stack<Card> drawPile6 = new Stack<Card>();
    drawPile6.push(new Card(Card.SUITS[1], Card.RANKS[4]));
    drawPile6.push(new Card(Card.SUITS[3], Card.RANKS[2]));
    drawPile6.push(new Card(Card.SUITS[4], Card.RANKS[8]));
    
    //PLAY
    ArrayList<Player> people6 = new ArrayList<Player>(Arrays.asList(players));
    people6.get(0).play(discardPile6, drawPile6, people6);
    System.out.println("Last card on discard pile is: " + discardPile6.top());
    System.out.println();
    System.out.println();
    
  }
  
}
