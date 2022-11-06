package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * The MainPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * It's main goal is to display the different activities the user can choose from
 *
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-05-10
 */
public class MainPanel extends JPanel {
    public static final String PLAYLIST_LABEL = "PLAYLIST";
    public static final String MENU_LABEL = "MENU";

    public static final String LIBRARY_LABEL = "LIBRARY";
    public static final String Add_LABEL = "ADD SONG";
    public static final String STA_LABEL = "STATISTICS";
    public static final String SET_LABEL = "SETTINGS";
    public static final String PATH_SETTS_IMAGES = "save/images/setts.png";


    public static final String BTN_PLAYLIST = "BTN_PLAYLIST";
    public static final String BTN_LIBRARY = "BTN_LIBRARY";
    public static final String BTN_STATISTICS = "BTN_STATISTICS";
    public static final String BTN_ADD_SONG = "BTN_ADD_SONG";
    public static final String BTN_SETTINGS = "BTN_SETTINGS";

    private JButton jbPlaylist;
    private JButton jbSettings;
    private JButton jbStatistics;
    private JButton jbAddSong;
    private JButton jbLibrary;

    public MainPanel() {
        Color c3 = new Color(76, 175, 80);
        setLayout(new BorderLayout(100,100));
        setBorder(new EmptyBorder(150, 200, 250, 200));
        setBackground(Color.BLACK);

        // Title
        JLabel title = new JLabel(MENU_LABEL);
        title. setFont(new Font("Arial", Font. BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        jbSettings = new JButton(new ImageIcon(PATH_SETTS_IMAGES));
        jbSettings.setBorder(BorderFactory.createEmptyBorder());
        jbSettings.setContentAreaFilled(false);
        jbSettings.setActionCommand(BTN_SETTINGS);
        add(jbSettings, BorderLayout.EAST);


        // Center Jpanel
        JPanel gr = new JPanel();
        gr.setLayout(new GridLayout(5, 1, 15, 35));
        gr.setPreferredSize(new Dimension(300, 300));
        gr.setOpaque(false);


        // Playlist Button
        jbPlaylist = new JButton(PLAYLIST_LABEL);
        jbPlaylist.setText(PLAYLIST_LABEL);
        jbPlaylist.setActionCommand(BTN_PLAYLIST);
        jbPlaylist.setForeground(Color.black);
        jbPlaylist.setBackground(c3);
        jbPlaylist.setOpaque(true);
        jbPlaylist.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbPlaylist.setFocusPainted(false);
        jbPlaylist.setPreferredSize(new Dimension(200,20));
        gr.add(jbPlaylist);


        // Library Button
        jbLibrary = new JButton(LIBRARY_LABEL);
        jbLibrary.setText(LIBRARY_LABEL);
        jbLibrary.setActionCommand(BTN_LIBRARY);
        jbLibrary.setBackground(c3);
        jbLibrary.setOpaque(true);
        jbLibrary.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbLibrary.setFocusPainted(false);
        jbLibrary.setPreferredSize(new Dimension(200,20));
        gr.add(jbLibrary);

        // Add Song Button
        jbAddSong = new JButton(Add_LABEL);
        jbAddSong.setText(Add_LABEL);
        jbAddSong.setActionCommand(BTN_ADD_SONG);
        jbAddSong.setBackground(c3);
        jbAddSong.setOpaque(true);
        jbAddSong.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbAddSong.setFocusPainted(false);
        jbAddSong.setPreferredSize(new Dimension(200,20));
        gr.add(jbAddSong);

        // Statistics  Button
        jbStatistics = new JButton(STA_LABEL);
        jbStatistics.setText(STA_LABEL);
        jbStatistics.setActionCommand(BTN_STATISTICS);
        jbStatistics.setBackground(c3);
        jbStatistics.setOpaque(true);
        jbStatistics.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        jbStatistics.setFocusPainted(false);
        jbStatistics.setPreferredSize(new Dimension(200,20));
        gr.add(jbStatistics);

        add(gr, BorderLayout.WEST);
        add(gr, BorderLayout.CENTER);
    }

    public void registerController(ActionListener listener){
        jbPlaylist.addActionListener(listener);
        jbLibrary.addActionListener(listener);
        jbAddSong.addActionListener(listener);
        jbStatistics.addActionListener(listener);
        jbSettings.addActionListener(listener);
    }

}


/*}*/
