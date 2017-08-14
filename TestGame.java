
public class TestGame {
    public static void main(String[] args){
        Crazy8Game[] game = new Crazy8Game[20];
        for (int i =0; i<game.length; i++){
            game[i] = new Crazy8Game();
        }

        for (int i =0; i<game.length; i++){

            System.out.println("\nThis is game " + (i+1) + "\n");

            game[i].main(null);
        }
    }
}
