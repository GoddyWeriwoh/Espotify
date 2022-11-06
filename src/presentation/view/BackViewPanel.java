package presentation.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackViewPanel extends JPanel{
    private static final String READING_ERROR = "Error reading background image";
    private static final String PATH_TO_IMAGE = "save/images/espotify.jpg";

    public BackViewPanel(JPanel jpanel) {
        try {
            BufferedImage biImage = ImageIO.read(new File(PATH_TO_IMAGE));
            setLayout(new GridBagLayout());
            jpanel.setPreferredSize(new Dimension(1000, 800));
            add(jpanel);
        } catch (IOException e) {
            System.out.println(READING_ERROR);
        }
    }

    public void changeCurrentPanel(JPanel oldPanel, JPanel newPanel) {
        remove(oldPanel);
        add(newPanel);
        newPanel.revalidate();
        newPanel.repaint();
    }
}
