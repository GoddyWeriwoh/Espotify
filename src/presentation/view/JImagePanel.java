package presentation.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The JImagePanel extends the {@link JPanel} to define a concrete implementation of it.
 *
 * Concretely, it helps the other possibles JPanels that need to include an image in their view to
 * make the insertion and the managing of it easier.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-04-20
 */
public class JImagePanel extends JPanel {

    // The image to render
    private BufferedImage image;

    // Constructor with parameters
    public JImagePanel(String path) {
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            // Not properly managed, sorry!
            e.printStackTrace();
        }
    }

    // IMPORTANT: WE override this to scale the image in layouts that stretch it horizontally while respecting its preferred vertical size
    // THIS WILL NOT WORK IF YOU HAVE OTHER GOALS, DON'T REUSE WITHOUT THINKING
    @Override
    public Dimension getPreferredSize() {
        Dimension preferred = super.getPreferredSize();

        float width = image.getWidth();
        float height = image.getHeight();

        // Calculate the height needed to mantain aspect ratio
        preferred.height = Math.round(getWidth()*height/width);

        return preferred;
    }

    // Paint the image in the background, with the size the layout assigns to the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    public void setHorizontalAlignment(int left) {
    }
}