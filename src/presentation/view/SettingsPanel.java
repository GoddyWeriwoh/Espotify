package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The SettingsPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * Concretely, is in charge of managing the settings of the account of the current user.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-04-23
 */
public class SettingsPanel extends JPanel {
    public static final String SETTINGS_LABEL = "SETTINGS";
    public static final String LOGOUT_LABEL = "LOG OUT";
    public static final String DELETE_LABEL = "DELETE ACCOUNT";
    public static final String BACK_LABEL = "BACK";

    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE_ACCOUNT = "BTN_DELETE";
    public static final String BTN_BACK = "BTN_BACK";

    private final JButton jbLogOut;
    private final JButton jbDeleteAccount;
    private final JButton jbBack;

    public SettingsPanel() {
        Color c3 = new Color(76, 175, 80);

        setLayout(new BorderLayout(100,110));
        setBorder(new EmptyBorder(110, 130, 110, 130));
        setBackground(Color.BLACK);

        // Panel
        /*JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(Color.BLACK);
*/
        // Title
        JLabel title = new JLabel(SETTINGS_LABEL);
        title.setFont(new Font("Arial", Font. BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Back
        jbBack = new JButton(BACK_LABEL);
        jbBack.setText(BACK_LABEL);
        jbBack.setActionCommand(BTN_BACK);
        jbBack.setForeground(c3);
        jbBack.setBackground(Color.black);
        jbBack.setOpaque(true);
        jbBack.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbBack.setFocusPainted(false);
        jbBack.setHorizontalAlignment(jbBack.CENTER);
        add(jbBack, BorderLayout.SOUTH);

        // Center Jpanel
        JPanel gr = new JPanel();
        gr.setLayout(new GridLayout(2, 1, 15, 30));
        gr.setPreferredSize(new Dimension(150, 150));
        gr.setOpaque(false);

        // Logout Button
        jbLogOut = new JButton(LOGOUT_LABEL);
        jbLogOut.setText(LOGOUT_LABEL);
        jbLogOut.setActionCommand(BTN_LOGOUT);
        jbLogOut.setForeground(Color.black);
        jbLogOut.setBackground(c3);
        jbLogOut.setOpaque(true);
        jbLogOut.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbLogOut.setFocusPainted(false);
        jbLogOut.setPreferredSize(new Dimension(200,20));
        add(jbLogOut, BorderLayout.EAST);

        // Delete Button
        jbDeleteAccount = new JButton(DELETE_LABEL);
        jbDeleteAccount.setText(DELETE_LABEL);
        jbDeleteAccount.setActionCommand(BTN_DELETE_ACCOUNT);
        jbDeleteAccount.setForeground(c3);
        jbDeleteAccount.setBackground(Color.darkGray);
        jbDeleteAccount.setOpaque(true);
        jbDeleteAccount.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbDeleteAccount.setFocusPainted(false);
        jbDeleteAccount.setPreferredSize(new Dimension(200,20));
        add(jbDeleteAccount, BorderLayout.WEST);


        gr.add(jbLogOut);
        gr.add(jbDeleteAccount);
        add(gr, BorderLayout.CENTER);
    }


    public void registerController(ActionListener listener){
        jbLogOut.addActionListener(listener);
        jbDeleteAccount.addActionListener(listener);
        jbBack.addActionListener(listener);
    }

}
