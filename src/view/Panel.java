package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private IController controller;

    public Panel(IController controller, Color frameColor) {
        this.controller = controller;

        super.setBorder(BorderFactory.createLineBorder(frameColor, 50));
        super.setBorder(BorderFactory.createLoweredBevelBorder());
        super.setBackground(Color.WHITE);
    }

}
