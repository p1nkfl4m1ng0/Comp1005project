
public class TestGame {

    public static int[] scores = new int[numbPlayers()];
    public static int numbGames = 3;
    public static final int WINNING_SCORE = 1000;

    public static int numbPlayers(){
        if(Crazy8Game.twoPlayer){
            return 2;
        }
        else if (Crazy8Game.threePlayer){
            return 3;
        }
        else
            return 4;
    }

    public static void main(String[] args){
        Crazy8Game[] game = new Crazy8Game[numbGames];

        for (int i =0; i<scores.length; i++){
            scores[i] = 0;
        }
        
        int gameCount = 0;
        boolean flag = true;
        while(flag){
            System.out.println("This is game: " + (gameCount +1));
            Crazy8Game.main(null);
            for (int i =0; i<scores.length; i++){
                if (scores[i] >= WINNING_SCORE){
                    flag = false;
                }
            }
            gameCount++;
        }

        System.out.println("\n-*-*-*-OVERALL RESULTS-*-*-*-\n");
        for (int i =0; i<scores.length; i++){
            System.out.println("Player " + i + " Total Points = " + scores[i]);
        }
    }
}
