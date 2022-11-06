package presentation.controller;

import business.SongManager;
import business.UserManager;
import presentation.view.AccessPanel;
import presentation.view.LoginPanel;
import presentation.MainFrame;
import presentation.view.RegisterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessController implements ActionListener {

    private static final String INVALID_USERNAME = "Invalid user name";
    private static final String ERROR = "Error";
    private static final String INVALID_EMAIL = "Invalid email";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";
    private static final String INVALID_PASSWORD = "Invalid password";
    private static final Object USERNAME_EXIST = "This username already exist";
    private static final Object EMAIL_EXISTS = "This email already exist";
    private static final Object INVALID_USERNAME_EXIST = "This user does not exist";


    private final MainFrame mainFrame;
    private final UserManager userManager;
    private final SongManager songManager;

    public AccessController(MainFrame mainFrame, UserManager userManager, SongManager songManager){
        this.mainFrame = mainFrame;
        this.userManager = userManager;
        this.songManager = songManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case AccessPanel.BTN_TO_REGISTER:
                //view.getRegisterPanel().resetUI();
                mainFrame.updateView(mainFrame.getAccessPanel(), mainFrame.getRegisterPanel());
                break;
            case AccessPanel.BTN_TO_LOGIN:
                //view.updateView(view.getAccessPanel(), view.getMainPanel());
                mainFrame.updateView(mainFrame.getAccessPanel(), mainFrame.getLoginPanel());
                break;
            case LoginPanel.BTN_LOGIN:
                if (login()) {
                    LoginPanel.resetLogin();
                    mainFrame.updateView(mainFrame.getLoginPanel(), mainFrame.getMainPanel());
                }
                break;
            case LoginPanel.BTN_CANCEL:
                mainFrame.updateView(mainFrame.getLoginPanel(), mainFrame.getAccessPanel());
                break;
            case RegisterPanel.BTN_REGISTER:
                if (register()) {
                    mainFrame.updateView(mainFrame.getRegisterPanel(), mainFrame.getLoginPanel());
                }
                break;
            case RegisterPanel.BTN_CANCEL:
                mainFrame.updateView(mainFrame.getRegisterPanel(), mainFrame.getAccessPanel());
                break;
        }
    }

    private boolean register() {
        String username = mainFrame.getRegisterPanel().getUsername();
        String email = mainFrame.getRegisterPanel().getEmail();

        String password1 = mainFrame.getRegisterPanel().getPassword1();
        String password2 = mainFrame.getRegisterPanel().getPassword2();
        password1 = password1.replaceAll("[,[ ]]", "");
        password2 = password2.replaceAll("[,[ ]]", "");

        password1 = password1.replace("]", "");
        password2 = password2.replace("]", "");
        password1 = password1.replace("[", "");
        password2 = password2.replace("[", "");

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, INVALID_USERNAME, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (userManager.nameExist(username)) {
            JOptionPane.showMessageDialog(null, USERNAME_EXIST, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidEmailAddress(email)) {
            JOptionPane.showMessageDialog(null, INVALID_EMAIL, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(userManager.emailExist(email)){
            JOptionPane.showMessageDialog(null, EMAIL_EXISTS, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidPassword(password1) || !password1.equals(password2)) {
            JOptionPane.showMessageDialog(null, INVALID_PASSWORD, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        userManager.storeUser(email, username, password1);
        //songManager.setUser(email, username, password1);
        return true;
    }

    private boolean login() {
        String username = mainFrame.getLoginPanel().getUsername();
        String password1 = mainFrame.getLoginPanel().getPassword1();

        password1 = password1.replaceAll("[,[ ]]", "");
        password1 = password1.replace("]", "");
        password1 = password1.replace("[", "");


        boolean isUsername = true;


        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(null, INVALID_USERNAME, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!userManager.nameExist(username)) {
            JOptionPane.showMessageDialog(null, INVALID_USERNAME_EXIST, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            if(!userManager.emailExist(username)){
                JOptionPane.showMessageDialog(null, INVALID_USERNAME_EXIST, ERROR, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        boolean b3 = userManager.isPasswordCorrect(username, password1, isUsername);
        if (!userManager.isPasswordCorrect(username, password1, isUsername)) {
            JOptionPane.showMessageDialog(null, INVALID_PASSWORD, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //model.setUsername(username);

        /*if (!isUsername) {
            userModel.setUsername(userModel.getUserName(username));
            songModel.setUsername(userModel.getUserName(username));
        }*/
        userManager.setUser(username, password1);
        songManager.setUser(userManager.getUser());
        return true;
    }

    private boolean isValidEmailAddress(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
