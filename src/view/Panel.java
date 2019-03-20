package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private IController controller;

    public Panel(IController controller, int width, int height) {
        this.controller = controller;

        super.setPreferredSize(new Dimension(width, height));
        super.setBackground(Color.RED);
    }

}
