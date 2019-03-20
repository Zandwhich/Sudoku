package view;

import controller.IController;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private IController controller;

    private JMenu gameMenu = new JMenu("Game");
    private JMenuItem newGame = new JMenuItem("New");
    private JMenuItem hint = new JMenuItem("Hint");

    public MenuBar(IController controller) {
        this.controller = controller;

        this.newGame.addActionListener(e -> this.controller.newGame());
        this.hint.addActionListener(e -> this.controller.hint());

        this.gameMenu.add(this.newGame);
        this.gameMenu.add(this.hint);
        super.add(this.gameMenu);
    }
}
