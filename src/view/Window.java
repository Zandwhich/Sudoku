package view;

import controller.IController;

import javax.swing.*;

public class Window extends JFrame {

    public final static int START_X = 310;
    public final static int START_Y = 120;
    public final static int WIDTH = 640;
    public final static int HEIGHT = 480;
    public final static String TITLE = "Sudoku";

    private IController controller;

    private MenuBar menuBar;
    private Panel panel;

    public Window(IController controller) {
        this.controller = controller;

        super.setLocation(Window.START_X, Window.START_Y);
        super.setSize(Window.WIDTH, Window.HEIGHT);
        super.setTitle(Window.TITLE);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        super.getContentPane().setLayout(null);

        this.menuBar = new MenuBar(this.controller);
        this.panel = new Panel(this.controller, super.getBackground());

        super.setJMenuBar(this.menuBar);

        super.add(this.panel);
        this.panel.setLocation(50, 50);

        super.setVisible(true);
    }
}
