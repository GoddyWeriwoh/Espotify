package presentation.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * The AccessPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * It is responsible for managing if the user wants to create an account or log in into one
 *
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-05-10
 */
public class AccessPanel extends JPanel {
    public static final String BTN_TO_LOGIN = "BTN_LOGIN0";
    public static final String BTN_TO_REGISTER = "BTN_REGISTER0";
    public static final String LOGIN_LABEL = "LOG IN";
    public static final String REGISTER_LABEL = "SIGN UP";
    private static final String PATH_TO_IMAGE = "save/images/espotify.jpg";

    private final JButton login;
    private final JButton register;

    public AccessPanel() {
        /*
        Background panel
        MUST BE DARK_GREY
         */
        // Setting the Layout
        setLayout(new BorderLayout(20,20));
        //Add margin to panel
        setBorder(new EmptyBorder(200, 250, 300, 250) );
        // Change background color
        setBackground(ViewElements.DARK_GREY);

        /*
        Configure the panel that contains the logo and title
         */
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridLayout(1, 2, -10, 10));


        JLabel title = new JLabel(ViewElements.TITLE);
        title. setFont(new Font("Arial", Font. BOLD, 35));
        title.setForeground(ViewElements.WHITE);

        // Put label on the middle
        title.setHorizontalAlignment(SwingConstants.RIGHT);

        //Put Image
        JImagePanel image = new JImagePanel("save/images/espotify.jpg");
        image.setSize(180,70);
        image.setHorizontalAlignment(SwingConstants.LEFT);

        logoPanel.setBackground(ViewElements.BLACK);
        logoPanel.add(image, BorderLayout.CENTER);
        logoPanel.add(title, BorderLayout.CENTER);
        /*
        Panel that contains the buttons
        MUST BE BLACK
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(ViewElements.BLACK);

        // Login button
        this.login = new JButton();
        this.login.setForeground(ViewElements.WHITE);
        this.login.setBackground(ViewElements.GREEN);
        this.login.setText(LOGIN_LABEL);
        this.login.setActionCommand(BTN_TO_LOGIN);
        buttonPanel.add(this.login, BorderLayout.SOUTH);

        //Register Button
        this.register = new JButton();
        this.register.setForeground(ViewElements.GREEN);
        this.register.setBackground(ViewElements.WHITE);
        this.register.setText(REGISTER_LABEL);
        this.register.setActionCommand(BTN_TO_REGISTER);
        buttonPanel.add(this.register, BorderLayout.SOUTH);

        //add(buttonPanel,BorderLayout.SOUTH);

        /*
        center black panel
         */
        JPanel black = new JPanel();
        black.setLayout(new BorderLayout(10,30));
        //Add margin to panel
        black.setBorder(new EmptyBorder(50, 100, 20, 100) );
        // Change background color
        black.setBackground(ViewElements.BLACK);

        black.add(logoPanel,BorderLayout.NORTH);
        black.add(buttonPanel,BorderLayout.SOUTH);

        add(black,BorderLayout.CENTER);
    }

    public void registerController(ActionListener listener) {
        login.addActionListener(listener);
        register.addActionListener(listener);
    }
}
/*
class RoundedBorder implements Border
{
    private int r;
    RoundedBorder(int r) {
        this.r = r;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }
}
 */