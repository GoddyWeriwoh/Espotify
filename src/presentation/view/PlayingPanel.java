package presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

public class PlayingPanel extends JPanel {
    public static final int FRAME_WIDTH = 1080;
    private static final int BOTTOM_BAR_HEIGHT = 64;
    private JButton jbPlay, jbNext, jbPrevious, jbRepeat;
    private static final String IC_NEXT = "save/images/next.png";

    private static final String IC_PLAY = "save/images/play.png";
    private static final String IC_PREVIOUS = "save/images/previous.png";
    private static final String IC_REPEAT = "save/images/repeat.png";


    public static final String AC_NEXT = "AC_NEXT";
    public static final String AC_PLAY = "AC_PLAY";
    public static final String AC_PREVIOUS = "AC_PREVIOUS";
    public static final String AC_REPEAT = "AC_REPEAT";
    private static final int LATERAL_MENU_WIDTH = FRAME_WIDTH / 14;

    public PlayingPanel() {

            JPanel homeSouthView = new JPanel();
            homeSouthView.setLayout(new BoxLayout(homeSouthView, BoxLayout.X_AXIS));
            homeSouthView.setBackground(Color.GRAY);


            jbPlay = initIcon(IC_PLAY, AC_PLAY);
            jbPlay.setActionCommand(AC_PLAY);
            jbNext = initIcon(IC_NEXT, AC_NEXT);
            jbNext.setActionCommand(AC_NEXT);
            jbPrevious = initIcon(IC_PREVIOUS, AC_PREVIOUS);
            jbPrevious.setActionCommand(AC_PREVIOUS);
            jbRepeat = initIcon(IC_REPEAT, AC_REPEAT);
            jbRepeat.setActionCommand(AC_REPEAT);


            homeSouthView.add(Box.createRigidArea(new Dimension(400, 0)));
            // Icons

            homeSouthView.add(jbPrevious);
            homeSouthView.add(jbPlay);
            homeSouthView.add(jbNext);
            homeSouthView.add(jbRepeat);

            this.add(homeSouthView, BorderLayout.SOUTH);
    }

    public void registerPlayingController(ActionListener songListener){
        jbPlay.addActionListener(songListener);
        jbNext.addActionListener(songListener);
        jbPrevious.addActionListener(songListener);
        jbRepeat.addActionListener(songListener);
    }


    private JButton initIcon(String iconPath, String actionCommand) {
        JButton jButton = new JButton(new ImageIcon(iconPath));
        // Size
        jButton.setPreferredSize(new Dimension(LATERAL_MENU_WIDTH, BOTTOM_BAR_HEIGHT));
        jButton.setMaximumSize(jButton.getPreferredSize());
        // Format
        jButton.setContentAreaFilled(false);
        jButton.setBorder(BorderFactory.createEmptyBorder());

        jButton.setFocusPainted(false);

        return jButton;
    }
}
