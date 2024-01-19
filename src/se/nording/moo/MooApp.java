package se.nording.moo;

public class MooApp {

    public static void main(String[] args) {
        try {
            GameController gameController = new GameController();
            gameController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
