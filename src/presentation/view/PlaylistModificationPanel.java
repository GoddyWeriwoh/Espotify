package presentation.view;

import business.entity.Playlist;
import presentation.controller.PlaylistController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * This extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * It is panel that appears when a playlist has been clicked on in the modifyPlaylist panel,
 * displaying the clicked playlist information.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-05-10
 */
public class PlaylistModificationPanel extends JPanel {
    private static final String MY_PLAYLISTS_LABEL = "My Playlists";
    public static final String BACK_BTN = "BACK_BTN5";
    private static final String BACK_LABEL = "BACK";
    private static final String ADD_SONG_LABEL = "Add songs";
    public static final String ADD_SONG_BTN = "ADD_SONG_BTN1";
    private static final String DEL_SONG_LABEL = "Delete songs";
    public static final String DELETE_SONG_BTN = "DELETE_SONG_BTN";
    private static final String PATH_UP_ARROW = "save/images/arrow_up.png";
    private static final String PATH_DOWN_ARROW = "save/images/arrow_down.png";
    public static final String BTN_ARR_UP = "BTN_ARR_UP";
    public static final String BTN_ARR_DOWN = "BTN_ARR_DOWN";


    private JButton backButton;
    private JList<String> jbAddPlaylistJ;
    private final String[] data;
    private JLabel title;
    private JButton addButton;
    private JButton deleteButton;
    private JButton jbArrUp;
    private JButton jbArrDown;
    private PlaylistController playlistListener;


    /**
     * Panel which appears after clicking on a playlist in the modification panel
     */
    public PlaylistModificationPanel(){
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
                    playlistListener.setCurrentSong(jbAddPlaylistJ.getSelectedValue().toString());
                }
            }
        });


        JScrollPane listScroller = new JScrollPane(jbAddPlaylistJ);
        listScroller.setPreferredSize(new Dimension(250, 300));
        gr.add(listScroller);

        addButton = new JButton(ADD_SONG_LABEL);
        addButton.setActionCommand(ADD_SONG_BTN);
        addButton.setForeground(Color.black);
        addButton.setBackground(c3);
        addButton.setOpaque(true);
        addButton.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(200,20));
        gr.add(addButton);

        deleteButton = new JButton(DEL_SONG_LABEL);
        deleteButton.setActionCommand(DELETE_SONG_BTN);
        deleteButton.setForeground(Color.black);
        deleteButton.setBackground(c3);
        deleteButton.setOpaque(true);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        deleteButton.setFocusPainted(false);
        deleteButton.setPreferredSize(new Dimension(200,20));
        gr.add(deleteButton);


        backButton = new JButton(BACK_LABEL);
        backButton.setActionCommand(BACK_BTN);
        backButton.setForeground(Color.black);
        backButton.setBackground(c3);
        backButton.setOpaque(true);
        backButton.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(200,20));
        gr.add(backButton);

        add(gr, BorderLayout.CENTER);

        jbArrUp = new JButton(new ImageIcon(PATH_UP_ARROW));
        jbArrUp.setBorder(BorderFactory.createEmptyBorder());
        jbArrUp.setSize(20, 20);
        jbArrUp.setContentAreaFilled(false);
        jbArrUp.setActionCommand(BTN_ARR_UP);

        jbArrDown = new JButton(new ImageIcon(PATH_DOWN_ARROW));
        jbArrDown.setBorder(BorderFactory.createEmptyBorder());
        jbArrDown.setSize(20, 20);
        jbArrDown.setContentAreaFilled(false);
        jbArrDown.setActionCommand(BTN_ARR_DOWN);

        JPanel arrow_panel = new JPanel();
        arrow_panel.setLayout(new GridLayout(2, 1, 15, 35));
        arrow_panel.add(jbArrUp);
        arrow_panel.add(jbArrDown);

        add(arrow_panel, BorderLayout.EAST);
    }

    public void registerPlaylistController(ActionListener listSongsController) {
        playlistListener = (PlaylistController) listSongsController;
        backButton.addActionListener(listSongsController);
        deleteButton.addActionListener(listSongsController);
        addButton.addActionListener(listSongsController);
        jbArrUp.addActionListener(listSongsController);
        jbArrDown.addActionListener(listSongsController);
    }

    public void setPlaylistPanelInfo(Playlist playlist) {
        title.setText(playlist.getTitle());
        if(playlist.getSongs().size() > 0){
            for (int i = 0; i < playlist.getSongs().size(); i++) {
                data[i] = (playlist.getSongs().get(i).getSong().getTitle());
            }
        }
        jbAddPlaylistJ.setListData(data);
    }

}
