package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * The LoginPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * Concretely, its displays the view providing the user the tools necessaries in order to allow
 * the log-in of a user into the app.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-04-23
 */
public class LoginPanel extends JPanel {
    private static final String PASSWORD_LABEL = "Password";
    public static final String USERNAME_LABEL = "Username";
    public static final int TEXT_FIELD_LABEL = 25;
    public static final String LOGIN_BTN_LABEL = "LOGIN";
    public static final String BTN_LOGIN = "BTN_LOGIN2";
    public static final String CANCEL_LABEL = "CANCEL";
    public static final String BTN_CANCEL = "BTN_CANCEL3";
    private static final String PATH_TO_IMAGE = "save/images/espotify.jpg";



    private final JButton login;
    private final JButton cancelLogin;
    private final JTextField usernameTextField;
    private final JPasswordField passwordTextField;


    public LoginPanel() {
        /*
        Background panel
        MUST BE DARK_GREY
         */
        // Setting the Layout
        setLayout(new BorderLayout(20,30));
        //Add margin to panel
        setBorder(new EmptyBorder(200, 250, 300, 250) );
        // Change background color
        setBackground(ViewElements.DARK_GREY);

        /*
        Configure the panel that contains the logo and title
         */

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new GridLayout(1, 2, 15, 10));


        JLabel title = new JLabel(ViewElements.TITLE);
        title. setFont(new Font("Arial", Font. BOLD, 35));
        title.setForeground(ViewElements.WHITE);

        // Put label on the middle
        title.setHorizontalAlignment(SwingConstants.RIGHT);


        //Put Image
        JImagePanel image = new JImagePanel(PATH_TO_IMAGE);
        image.setSize(180,70);
        image.setHorizontalAlignment(SwingConstants.LEFT);

        logoPanel.setBackground(ViewElements.BLACK);
        logoPanel.add(image, BorderLayout.CENTER);
        logoPanel.add(title, BorderLayout.CENTER);

        /*
        Panel that contains the text labels and text field
        MUST BE BLACK
         */

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(ViewElements.BLACK);
        textPanel.add(logoPanel);

        //Username label
        JLabel usernameLabel = new JLabel();
        usernameLabel. setFont(new Font("Arial", Font. BOLD, 15));
        usernameLabel.setForeground(ViewElements.WHITE);
        usernameLabel.setText(USERNAME_LABEL);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(usernameLabel);

        //Username text field
        this.usernameTextField = new JTextField(TEXT_FIELD_LABEL);
        this.usernameTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.usernameTextField);

        //Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel. setFont(new Font("Arial", Font. BOLD, 15));
        passwordLabel.setForeground(ViewElements.WHITE);
        passwordLabel.setText(PASSWORD_LABEL);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(passwordLabel);

        //Password text field
        this.passwordTextField = new JPasswordField(TEXT_FIELD_LABEL);
        this.passwordTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.passwordTextField);

        /*
        Panel that contains the buttons
        MUST BE BLACK
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(ViewElements.BLACK);

        // SignUp button
        this.login = new JButton();
        this.login.setForeground(ViewElements.WHITE);
        this.login.setBackground(ViewElements.GREEN);
        this.login.setText(LOGIN_BTN_LABEL);
        this.login.setActionCommand(BTN_LOGIN);
        buttonPanel.add(this.login, BorderLayout.SOUTH);

        // Cancel Button
        this.cancelLogin = new JButton();
        this.cancelLogin.setForeground(ViewElements.GREEN);
        this.cancelLogin.setBackground(ViewElements.WHITE);
        this.cancelLogin.setText(CANCEL_LABEL);
        this.cancelLogin.setActionCommand(BTN_CANCEL);
        buttonPanel.add(this.cancelLogin, BorderLayout.SOUTH);

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
        black.add(textPanel, BorderLayout.CENTER);
        black.add(buttonPanel,BorderLayout.SOUTH);

        add(black,BorderLayout.CENTER);
    }

    public void loginController(ActionListener listener){
        login.addActionListener(listener);
        cancelLogin.addActionListener(listener);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }


    public String getPassword1() {
        return Arrays.toString(passwordTextField.getPassword());
    }

    public static void resetLogin() {
        /*usernameTextField.setText("");
        passwordTextField.setText("");*/
    }

}


