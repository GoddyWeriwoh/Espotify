package presentation.view;

import business.entity.Playlist;
import business.entity.Song;
import presentation.controller.PlaylistController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePLSongsPanel extends JPanel {
    private static final String MY_PLAYLISTS_LABEL = "My Playlists";
    public static final String BACK_BTN = "BACK_BTN8";
    private static final String BACK_LABEL = "BACK";
    private static final String ADD_SONG_LABEL = "Add songs";
    public static final String ADD_SONG_BTN = "ADD_SONG_BTN1";
    private static final String DEL_SONG_LABEL = "Delete songs";
    public static final String DELETE_SONG_BTN = "DELETE_SONG_BTN";

    private JButton backButton;
    private JList<String> jbAddPlaylistJ;
    private String data[];
    private PlaylistController playlistController;
    private JLabel title;
    private JButton addButton;
    private JButton deleteButton;

    public DeletePLSongsPanel(){
        revalidate();
        repaint();
        data = new String[100];

        Color c3 = new Color(76, 175, 80);
        setLayout(new BorderLayout(100,100));
        setBorder(new EmptyBorder(150, 200, 250, 200));
        setBackground(Color.BLACK);

        // Title
        title = new JLabel();
        title. setFont(new Font("Arial", Font. BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);


        // Center Jpanel
        JPanel gr = new JPanel();
        gr.setLayout(new GridLayout(5, 1, 15, 35));
        gr.setPreferredSize(new Dimension(500, 500));
        gr.setOpaque(false);


        jbAddPlaylistJ = new JList();
        jbAddPlaylistJ.setLayoutOrientation(JList.VERTICAL);
        jbAddPlaylistJ.setVisibleRowCount(-1);

        jbAddPlaylistJ.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    playlistController.deleteSongFromPlayList(jbAddPlaylistJ.getSelectedValue().toString());
                }
            }
        });


        JScrollPane listScroller = new JScrollPane(jbAddPlaylistJ);
        listScroller.setPreferredSize(new Dimension(250, 300));
        gr.add(listScroller);


        backButton = new JButton(BACK_LABEL);
        backButton.setActionCommand(BACK_BTN);
        backButton.setForeground(Color.black);
        backButton.setBackground(c3);
        backButton.setOpaque(true);
        backButton.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(200,20));
        gr.add(backButton);

        add(gr, BorderLayout.WEST);
        add(gr, BorderLayout.CENTER);
    }

    public void registerPlaylistController(ActionListener listener) {
        playlistController = (PlaylistController) listener;
        backButton.addActionListener(listener);
    }

    public void addSongsList(Playlist currentPlaylist) {
        if(currentPlaylist.getSongs().size() > 0){
            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                data[i] = (currentPlaylist.getSongs().get(i).getSong().getTitle());
            }
        }
        jbAddPlaylistJ.setListData(data);
    }
}
