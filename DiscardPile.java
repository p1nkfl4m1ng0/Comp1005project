import java.util.Stack;

public class DiscardPile{
    /* the actual discard pile */
    protected Stack<Card> cards = new Stack<Card>();

    /* the top card on the discard pile */
    public Card top(){
        if(cards.isEmpty()){
            return new Card("None","None");
        }
        return cards.peek();
    }

    /* add a card to the discard pile */
    public void add(Card card){
        cards.push(card);
    }

    /* Oana: remove a card from the discard pile */
    public Card remove() {
        return cards.pop();
    }
    
    /* Oana: check is discard pile is empty */
    public boolean empty() {
        if (cards.isEmpty()){
            return true;
        }
        return false;
    }

}
