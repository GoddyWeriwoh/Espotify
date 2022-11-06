package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaylistPanel extends JPanel {
    private static final String MY_PLAYLISTS_LABEL = "My Playlists";
    private static final String LIST_OTHERS_PLAYLIST_LABEL = "Other playlists";
    private static final String PLAYLISTS_LABEL = "PLAYLISTS";
    public static final String ADD_PLAYLIST_LABEL = "ADD PLAYLIST";
    public static final String MODIFY_PLAYLIST_LABEL = "MODIFY PLAYLIST";
    private static final String BACK_LABEL = "BACK";
    public static final String BTN_BACK = "BACK_COMMAND";

    public static final String BTN_ADD_PLAYLIST = "BTN_ADD_PLAYLIST";
    public static final String BTN_MODIFY_PLAYLIST = "BTN_MODIFY_PLAYLIST";
    public static final String BTN_LIST_MY_PLAYLIST = "BTN_LIST_PLAYLIST";
    public static final String BTN_LIST_OTHERS_PLAYLIST = "BTN_LIST_OTHERS_PLAYLIST";


    private JButton jbAddPlaylist;
    private JButton jbModifyPl;
    private JButton jbListMyPlaylist;
    private JButton jbListOthersPlaylist;
    private JButton jbBack;


    public PlaylistPanel(){
        Color c3 = new Color(76, 175, 80);
        setLayout(new BorderLayout(100,100));
        setBorder(new EmptyBorder(150, 200, 250, 200));
        setBackground(Color.BLACK);

        // Title
        JLabel title = new JLabel(PLAYLISTS_LABEL);
        title. setFont(new Font("Arial", Font. BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);


        // Center Jpanel
        JPanel gr = new JPanel();
        gr.setLayout(new GridLayout(5, 1, 15, 35));
        gr.setPreferredSize(new Dimension(300, 300));
        gr.setOpaque(false);

        // Playlist Button
        jbAddPlaylist = new JButton(ADD_PLAYLIST_LABEL);
        jbAddPlaylist.setActionCommand(BTN_ADD_PLAYLIST);
        jbAddPlaylist.setForeground(Color.black);
        jbAddPlaylist.setBackground(c3);
        jbAddPlaylist.setOpaque(true);
        jbAddPlaylist.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbAddPlaylist.setFocusPainted(false);
        jbAddPlaylist.setPreferredSize(new Dimension(200,20));
        gr.add(jbAddPlaylist);

        // Playlist Button
        jbModifyPl = new JButton(MODIFY_PLAYLIST_LABEL);
        jbModifyPl.setActionCommand(BTN_MODIFY_PLAYLIST);
        jbModifyPl.setForeground(Color.black);
        jbModifyPl.setBackground(c3);
        jbModifyPl.setOpaque(true);
        jbModifyPl.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbModifyPl.setFocusPainted(false);
        jbModifyPl.setPreferredSize(new Dimension(200,20));
        gr.add(jbModifyPl);


        // Library Button
        jbListMyPlaylist = new JButton(MY_PLAYLISTS_LABEL);
        jbListMyPlaylist.setActionCommand(BTN_LIST_MY_PLAYLIST);
        jbListMyPlaylist.setBackground(c3);
        jbListMyPlaylist.setOpaque(true);
        jbListMyPlaylist.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbListMyPlaylist.setFocusPainted(false);
        jbListMyPlaylist.setPreferredSize(new Dimension(200,20));
        gr.add(jbListMyPlaylist);

        // Library Button
        jbListOthersPlaylist = new JButton(LIST_OTHERS_PLAYLIST_LABEL);
        jbListOthersPlaylist.setActionCommand(BTN_LIST_OTHERS_PLAYLIST);
        jbListOthersPlaylist.setBackground(c3);
        jbListOthersPlaylist.setOpaque(true);
        jbListOthersPlaylist.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbListOthersPlaylist.setFocusPainted(false);
        jbListOthersPlaylist.setPreferredSize(new Dimension(200,20));
        gr.add(jbListOthersPlaylist);

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
        gr.add(jbBack);

        add(gr, BorderLayout.WEST);
        add(gr, BorderLayout.CENTER);
    }

    public void registerController(ActionListener listener){
        jbAddPlaylist.addActionListener(listener);
        jbListMyPlaylist.addActionListener(listener);
        jbListOthersPlaylist.addActionListener(listener);
        jbModifyPl.addActionListener(listener);
        jbBack.addActionListener(listener);
    }


}
