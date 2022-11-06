package presentation.view;

import business.PlaylistManager;
import business.entity.Playlist;
import presentation.MainFrame;
import presentation.controller.PlaylistController;
import presentation.controller.SongController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChosenPlayListListPanel extends JPanel{
    private static final String MY_PLAYLISTS_LABEL = "My Playlists";
    public static final String BACK_BTN = "BACK_BTN";
    private static final String BACK_LABEL = "BACK";
    private static final String OTHERS_PLAYLISTS_LABEL = "Others Playlists";
    public static final String START_PL_BTN = "START_PL_BTN";
    private static final String START_PL_LABEL = "START PLAYLIST";


    private ArrayList<Playlist> userPlaylists = new ArrayList<>();
    private JButton backButton;
    private JList<String> jbAddPlaylistJ;
    private String data[];
    private PlaylistController playlistController;
    private boolean isMyPlaylist;
    private JLabel title;
    private JButton startPlaylistButton;

    /**
     * Playlist that pops after the clicking on a playlist in the playlist modification panel
     */
    public ChosenPlayListListPanel(){
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

        // Playlist Button
        jbAddPlaylistJ = new JList();
        jbAddPlaylistJ.setLayoutOrientation(JList.VERTICAL);
        jbAddPlaylistJ.setVisibleRowCount(-1);

        jbAddPlaylistJ.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    playlistController.setIsMyPlaylist(isMyPlaylist);
                    playlistController.setSelectedPlaylistListInfo(jbAddPlaylistJ.getSelectedValue().toString());
                }
            }
        });

        JScrollPane listScroller = new JScrollPane(jbAddPlaylistJ);
        listScroller.setPreferredSize(new Dimension(250, 380));
        gr.add(listScroller);

        startPlaylistButton = new JButton(START_PL_LABEL);
        startPlaylistButton.setActionCommand(START_PL_BTN);
        startPlaylistButton.setForeground(Color.black);
        startPlaylistButton.setBackground(c3);
        startPlaylistButton.setOpaque(true);
        startPlaylistButton.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        startPlaylistButton.setFocusPainted(false);
        startPlaylistButton.setPreferredSize(new Dimension(200,20));
        gr.add(startPlaylistButton);

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

    public void registerPlaylistController(ActionListener listener){
        playlistController = (PlaylistController)listener;
        backButton.addActionListener(listener);
        startPlaylistButton.addActionListener(listener);
    }

    /**
     *  Sets the information of the playlist panel if it was
     * @param isMyPlaylist ArrayList<Playlist>
     * @param userPlaylists ArrayList<Playlist>
     * @param othersPlaylist ArrayList<Playlist>
     */
    public void addPlaylistList(Boolean isMyPlaylist, ArrayList<Playlist> userPlaylists, ArrayList<Playlist> othersPlaylist) {

        data = new String[100];

        this.isMyPlaylist = isMyPlaylist;
        if(isMyPlaylist){
            title.setText(MY_PLAYLISTS_LABEL);
            if(userPlaylists.size() > 0){
                for (int i = 0; i < userPlaylists.size(); i++) {

                    data[i] = (userPlaylists.get(i).getTitle());
                }
            }

        }
        else{
            title.setText(OTHERS_PLAYLISTS_LABEL);
            if(othersPlaylist.size() > 0){
                for (int i = 0; i < othersPlaylist.size(); i++) {

                    data[i] = (othersPlaylist.get(i).getTitle());
                }
            }

        }
        jbAddPlaylistJ.setListData(data);
    }

    public boolean getPlaylistStatus() {
        return isMyPlaylist;
    }
}
