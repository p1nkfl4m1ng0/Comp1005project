
import java.util.HashMap;

public class Card implements Comparable<Card>{


    public final static String[] RANKS = { "None", "None",
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"};
    public final static String[] SUITS = { "Diamonds",
            "Clubs", "Hearts", "Spades", "None"};


    protected String suit;
    protected String rank;
    protected HashMap<String, Integer> rankValue;

    /** creates a card with specified suit and rank
     *
     * @param suit is the suit of the card (must be a string from Card.SUITS)
     * @param rank is the rank of the card (must be a string from Card.RANKS)
     */
    public Card(String suit, String rank){
        // assume input is valid!
        this.suit = suit;
        this.rank = rank;
        this.rankValue = new HashMap<String,Integer>(15);
        for(int r = 2; r < RANKS.length; r+=1){
            this.rankValue.put(RANKS[r], r);
        }
    }

    public int getRank(){
        if(this.rank.equals(RANKS[0])){ return -1; }   // "no card"
        return rankValue.get(this.rank);
    }

    public String getRankString(){ return this.rank; }
    public String getSuit(){ return this.suit; }

    @Override
    public int compareTo(Card other){

        String[] ranks = {RANKS[8], RANKS[2], RANKS[4], RANKS[7], RANKS[11], RANKS[12], RANKS[13], RANKS[10],RANKS[9], RANKS[6], RANKS[5], RANKS[3], RANKS[14],RANKS[0], RANKS[1]};
        String [] counter = new String[4];

        int heartCount = DiscardHighPointsPlayer.heartCount;
        int clubCount = DiscardHighPointsPlayer.clubCount;
        int diamondCount = DiscardHighPointsPlayer.diamondCount;
        int spadeCount = DiscardHighPointsPlayer.spadeCount;

            if(diamondCount>=clubCount && diamondCount>=spadeCount && diamondCount>=heartCount){
                if (clubCount>=spadeCount && clubCount>=heartCount){
                    if(spadeCount>=heartCount){
                        counter = new String[]{"Diamonds", "Clubs", "Spades", "Hearts"};
                    }
                    else{
                        counter = new String[]{"Diamonds", "Clubs", "Hearts", "Spades"};
                    }
                }
                else if (spadeCount>=clubCount && spadeCount>=heartCount){
                    if(clubCount>=heartCount){
                        counter = new String[]{"Diamonds", "Spades", "Clubs", "Hearts"};
                    }
                    else{
                        counter = new String[]{"Diamonds", "Spades", "Hearts", "Clubs"};
                    }
                }
                else if (heartCount>=spadeCount && heartCount>=clubCount){
                    if(spadeCount>=clubCount){
                        counter = new String[]{"Diamonds", "Hearts", "Spades", "Clubs"};
                    }
                    else{
                        counter = new String[]{"Diamonds", "Hearts", "Clubs", "Spades"};
                    }
                }
            }
            else if (clubCount>=diamondCount && clubCount>=spadeCount && clubCount>=heartCount){
                if (diamondCount>=spadeCount && diamondCount>=heartCount){
                    if(spadeCount>=heartCount){
                        counter = new String[]{"Clubs", "Diamonds", "Spades", "Hearts"};
                    }
                    else{
                        counter = new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
                    }
                }
                else if (spadeCount>=diamondCount && spadeCount>=heartCount){
                    if(diamondCount>=heartCount){
                        counter = new String[]{"Clubs", "Spades", "Diamonds", "Hearts"};
                    }
                    else{
                        counter = new String[]{"Clubs", "Spades", "Hearts", "Diamonds"};
                    }
                }
                else if (heartCount>=spadeCount && heartCount>=diamondCount){
                    if(spadeCount>=diamondCount){
                        counter = new String[]{"Clubs", "Hearts", "Spades", "Diamonds"};
                    }
                    else{
                        counter = new String[]{"Clubs", "Hearts", "Diamonds", "Spades"};
                    }
                }
            }
            else if (spadeCount>=diamondCount && spadeCount>=clubCount && spadeCount>=heartCount){
                if (clubCount>=diamondCount && clubCount>=heartCount){
                    if(diamondCount>=heartCount){
                        counter = new String[]{"Spades", "Clubs", "Diamonds", "Hearts"};
                    }
                    else{
                        counter = new String[]{"Spades", "Clubs", "Hearts", "Diamonds"};
                    }
                }
                else if (diamondCount>=clubCount && diamondCount>=heartCount){
                    if(clubCount>=heartCount){
                        counter = new String[]{"Spades", "Diamonds", "Clubs", "Hearts"};
                    }
                    else{
                        counter = new String[]{"Spades", "Diamonds", "Hearts", "Clubs"};
                    }
                }
                else if (heartCount>=diamondCount && heartCount>=clubCount){
                    if(diamondCount>=clubCount){
                        counter = new String[]{"Spades", "Hearts", "Diamonds", "Clubs"};
                    }
                    else{
                        counter = new String[]{"Spades", "Hearts", "Clubs", "Diamonds"};
                    }
                }
            }
            else if (heartCount>=diamondCount && heartCount>=spadeCount && heartCount>=clubCount){
                if (spadeCount>=diamondCount && spadeCount>=clubCount){
                    if(diamondCount>=clubCount){
                        counter = new String[]{"Hearts", "Spades", "Diamonds", "Clubs"};
                    }
                    else{
                        counter = new String[]{"Hearts", "Spades" , "Clubs", "Diamonds"};
                    }
                }
                else if (clubCount>=diamondCount && clubCount>=spadeCount){
                    if(diamondCount>=spadeCount){
                        counter = new String[]{"Hearts", "Clubs", "Diamonds", "Spades"};
                    }
                    else{
                        counter = new String[]{"Hearts","Clubs", "Spades", "Diamonds"};
                    }
                }
                else if(diamondCount>= spadeCount && diamondCount>=clubCount){
                    if(spadeCount>=clubCount){
                        counter = new String[]{"Hearts", "Diamonds", "Spades", "Clubs"};
                    }
                    else{
                        counter = new String[]{ "Hearts", "Diamonds", "Clubs", "Spades"};
                    }
                }
            }
            else{counter = new String[]{"Hearts","Diamonds", "Clubs", "Spades"};}

        int indexOfThis= 0;
        int indexOfOther = 0;


        for(int i=0; i<counter.length; i+=1){
            if(other.getSuit()== counter[i]){
                indexOfOther = i;
            }
        }

        for(int i=0; i<counter.length; i+=1){
            if(this.getSuit() == counter[i]){
                indexOfThis = i;
            }
        }

        if(indexOfThis>indexOfOther){
            return 1;
        }

        else if(indexOfThis<indexOfOther){
            return -1;
        }
        else {
            int indexOfThis2 = 0;
            int indexOfS = 0;
            for (int i = 0; i < ranks.length; i += 1) {
                if (other.getRankString() == ranks[i]) {
                    indexOfS = i;
                }
            }

            for (int i = 0; i < ranks.length; i += 1) {
                if (this.getRankString() == ranks[i]) {
                    indexOfThis2 = i;
                }
            }

            if (indexOfThis2 > indexOfS) {
                return 1;
            } else if (indexOfThis2 < indexOfS) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public final String toString(){
        // outputs a string representation of a card object
        if(this.rank.equals(RANKS[0])){
            return "no card";
        }

        int r = getRank();
        if( r >= 2 && r <= 14 ){
            return r + getSuit().substring(0,1);
        }
        return "no card";
    }

}
