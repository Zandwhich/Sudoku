package controller;

import javax.swing.*;

public class Controller implements IController {

    @Override
    public void newGame() {
        JOptionPane.showMessageDialog(null, "New Game");
    }

    public void hint() {
        JOptionPane.showMessageDialog(null, "Hint");
    }
}
