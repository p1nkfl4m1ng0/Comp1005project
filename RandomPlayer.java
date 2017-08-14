import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class RandomPlayer extends Player {

    public RandomPlayer(Card[] cards){this.hand = new ArrayList<Card>(Arrays.asList(cards));}

    public boolean play(DiscardPile       discardPile,
                        Stack<Card>       drawPile,
                        ArrayList<Player> players)
    {
        boolean retrn;
        retrn = playValid(discardPile,drawPile,players);
        eight(Card.SUITS[(int)(Math.random()*5)],discardPile);
        return retrn;
    }
}
