package presentation.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The ViewElements stores all the common elements that the different views shares in order to achieve
 * a concordance between the different panels.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-04-10
 */

public class ViewElements {
    public static final String TITLE = "ESPOTIFAI";
    public static final Color DARK_GREY = new Color(45, 45, 45);
    public static final Color LIGHT_GREY = new Color(100, 100,100);
    public static final Color GREEN = new Color(76, 175, 80);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);

    public static Font getEspotifyFont(float size) {
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/VCR_OSD_MONO_1.001.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
            return customFont;
        } catch (IOException | FontFormatException e) {
            System.out.println("Custom font replaces with default font.");
        }

        return new Font("OCR A Extended", Font.BOLD, (int)size);
    }
    public static Color getColors(int x){
        switch (x%10){
            case 9 -> {
                return new Color(81, 18, 115);
            }
            case 8 -> {
                return new Color(36, 10, 68);
            }
            case 7 -> {
                return new Color(0, 65, 206);
            }
            case 6 -> {
                return new Color(76, 175, 167);
            }
            case 5 -> {
                return new Color(76, 175, 80);
            }
            case 4 -> {
                return new Color(255, 249, 75);
            }
            case 3 -> {
                return new Color(255, 177, 75);
            }
            case 2 -> {
                return new Color(255, 102, 75);
            }
            case 1 -> {
                return new Color(119, 35, 35);
            }
            case 0 -> {
                return new Color(255, 75, 177);
            }

        }
        return new Color(255, 75, 177);
    }
}
