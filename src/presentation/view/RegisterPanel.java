package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * The RegisterPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * Concretely, its displays the view providing the user the tools necessaries in order to create an
 * account.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-23-04
 */
public class RegisterPanel extends JPanel {
    private static final String CONFIRM_PASSWORD_LABEL = "Password Confirmation";
    private static final String PASSWORD_LABEL = "Password";
    public static final String USERNAME_LABEL = "Username";
    public static final int TEXT_FIELD_LABEL = 25;
    public static final String EMAIL_LABEL = "Email";
    public static final String REGISTER_BTN_LABEL = "REGISTER";
    public static final String BTN_REGISTER = "BTN_REGISTER2";
    public static final String CANCEL_LABEL = "CANCEL";
    public static final String BTN_CANCEL = "BTN_CANCEL2";
    private static final String PATH_TO_IMAGE = "save/images/espotify.jpg";



    private final JButton register;
    private final JButton cancelRegister;
    private final JTextField usernameTextField;
    private final JTextField emailTextField;
    private final JPasswordField passwordTextField;
    private final JPasswordField passwordConTextField;
    //private Hints jpfPassword;


    public RegisterPanel() {
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


        // Email label
        JLabel emailLabel = new JLabel();
        emailLabel. setFont(new Font("Arial", Font. BOLD, 15));
        emailLabel.setForeground(ViewElements.WHITE);
        emailLabel.setText(EMAIL_LABEL);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(emailLabel);

        // Email text field
        emailTextField = new JTextField(TEXT_FIELD_LABEL);
        emailTextField.setBackground(ViewElements.WHITE);
        textPanel.add(emailTextField);

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

        //ConfirmPassword label
        JLabel confirmPasswordLabel = new JLabel("Password Confirmation");
        confirmPasswordLabel.setFont(new Font("Arial", Font. BOLD, 15));
        confirmPasswordLabel.setForeground(ViewElements.WHITE);
        confirmPasswordLabel.setText(CONFIRM_PASSWORD_LABEL);
        confirmPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(confirmPasswordLabel);

        //ConfirmPassword text field
        this.passwordConTextField = new JPasswordField(TEXT_FIELD_LABEL);
        this.passwordConTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.passwordConTextField);

        /*
        Panel that contains the buttons
        MUST BE BLACK
         */
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(ViewElements.BLACK);

        // SignUp button
        this.register = new JButton();
        this.register.setForeground(ViewElements.WHITE);
        this.register.setBackground(ViewElements.GREEN);
        this.register.setText(REGISTER_BTN_LABEL);
        this.register.setActionCommand(BTN_REGISTER);
        buttonPanel.add(this.register, BorderLayout.SOUTH);

        // Cancel Button
        this.cancelRegister = new JButton();
        this.cancelRegister.setForeground(ViewElements.GREEN);
        this.cancelRegister.setBackground(ViewElements.WHITE);
        this.cancelRegister.setText(CANCEL_LABEL);
        this.cancelRegister.setActionCommand(BTN_CANCEL);
        buttonPanel.add(this.cancelRegister, BorderLayout.SOUTH);

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

        //black.add(title, BorderLayout.NORTH);
        //black.add(image, BorderLayout.NORTH);
        black.add(logoPanel,BorderLayout.NORTH);
        black.add(textPanel, BorderLayout.CENTER);
        black.add(buttonPanel,BorderLayout.SOUTH);

        add(black,BorderLayout.CENTER);

    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getPassword1() {
        return Arrays.toString(passwordTextField.getPassword());
    }
    public String getPassword2() {
        return Arrays.toString(passwordConTextField.getPassword());
    }

    public void registerController(ActionListener listener) {
        register.addActionListener(listener);
        cancelRegister.addActionListener(listener);
    }

    /*
    public void resetUI() {
        jtfEmail.setText("");
        jtfUsername.setText("");
        jpfPassword.setText(ENTER_PASSWORD_LABEL);
        jpfPassword.setShowingHint(true);
        jpfRepeatPassword.setText(ENTER_CONF_PASSWORD_LABEL);
        jpfRepeatPassword.setShowingHint(true);
    }*/
}