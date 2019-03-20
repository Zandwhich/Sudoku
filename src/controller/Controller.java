package controller;

import model.Board;

import javax.swing.*;

public class Controller implements IController {

    private int size;

    public Controller() {
        this.size = Board.DEFAULT_SIZE;
    }

    @Override
    public void newGame() {
        JOptionPane.showMessageDialog(null, "New Game");
    }

    @Override
    public void hint() {
        JOptionPane.showMessageDialog(null, "Hint");
    }

    public int getSize() {
        return size;
    }
}
