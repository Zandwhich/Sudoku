import controller.Controller;
import model.Board;
import view.Window;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(true);
        Controller controller = new Controller();

        try {
            board.setCellNum(5,0,5);
            board.setCellNum(6,0,4);
            board.setCellNum(8,0,9);
            board.setCellNum(0,1,4);
            board.setCellNum(1,1,5);
            board.setCellNum(2,1,1);
            board.setCellNum(5,1,2);
            board.setCellNum(6,1,3);
            board.setCellNum(0,2,9);
            board.setCellNum(1,2,8);
            board.setCellNum(2,2,2);
            board.setCellNum(6,2,5);
            board.setCellNum(7,2,6);
            board.setCellNum(8,2,1);
            board.setCellNum(0,3,6);
            board.setCellNum(2,3,7);
            board.setCellNum(6,3,9);
            board.setCellNum(7,3,8);
            board.setCellNum(2,4,3);
            board.setCellNum(3,4,4);
            board.setCellNum(4,4,6);
            board.setCellNum(0,5,5);
            board.setCellNum(3,5,2);
            board.setCellNum(4,5,8);
            board.setCellNum(5,5,7);
            board.setCellNum(7,5,1);
            board.setCellNum(1,6,4);
            board.setCellNum(4,6,7);
            board.setCellNum(7,6,9);
            board.setCellNum(8,6,6);
            board.setCellNum(0,7,3);
            board.setCellNum(6,7,7);
            board.setCellNum(2,8,5);
            board.setCellNum(3,8,9);
            board.setCellNum(4,8,4);
            board.setCellNum(5,8,6);
            board.setCellNum(6,8,8);
            board.setCellNum(8,8,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        board.print();
        System.out.println();
        board.printGrids();

        Machine machine = new Machine(controller, board);
        machine.runAI();
        board.print();

        //new Window(new Controller());
    }

}//end Main
