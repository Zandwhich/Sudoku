import controller.Controller;
import model.Board;
import view.Window;

public class Main {

    public static void main(String[] args) {
        new Board(true);
        new Window(new Controller());
    }

}//end Main
