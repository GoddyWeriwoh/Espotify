package presentation.view;

import business.entity.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The SongPanel extends the {@link JPanel} to define the view where every song is going to be managed.
 *
 * It mainly consists of three buttons that allows the plays and deletion of the song or the option to
 * go back to the list of songs.

 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since 2022-05-12
 */
public class SongPanel extends JPanel {
    private static final String PATH_TO_IMAGE = "save/images/espotify.jpg";
    private static final String PLAY_BTN_LABEL = "Play";
    public static final String BTN_PLAY = "PLAY_COMMAND";
    private static final String BACK_BTN_LABEL = "BACK";
    public static final String BTN_BACK = "BACK_COMMAND";
    public static final String BTN_DELETE = "DELETE_COMMAND";
    public static final String DELETE_BTN_LABEL = "DELETE";
    public static final String PAUSE_BTN = "PAUSE";
    private static final String ADD_BTN ="ADD_BTN" ;
    public static final String BACK_TO_PL = "BACK_TO_PL";


    private JButton playButton;
    private JButton addToPly;
    private JButton backButton;
    private JButton deleteButton;
    private JButton backToPLButton;
    private String lyrics;
    private JLabel usernameLabel;
    private JLabel songName;
    private JLabel authorName;
    private PlayingPanel playingPanel ;
    private JButton pauseButton;
    private JLabel titleLavel;
    private JTextArea textLyrics;
    private JTextArea textDetails;
    private String names;
    private String songN;

    public SongPanel(){

        this.setLayout(new BorderLayout(0, 0));

        //String Names = "\n    NAME:\n    " + songName + "\n\n    AUTHOR:\n    " + usernameLabel + "\n\n    DURATION:\n    03:11";
        titleLavel = new JLabel();

        backButton = new JButton("Back");
        backButton.setActionCommand(BTN_BACK);
        deleteButton = new JButton("Delete");
        deleteButton.setActionCommand(BTN_DELETE);

        playButton = new JButton(PLAY_BTN_LABEL);
        playButton.setActionCommand(BTN_PLAY);

        addToPly = new JButton("Add");
        addToPly.setActionCommand(ADD_BTN);

        backToPLButton = new JButton("Back to List");
        backToPLButton.setActionCommand(BACK_TO_PL);
        Color c = Color.decode("#009637");

        JPanel up = new JPanel();
        titleLavel.setForeground(c);
        up.add(titleLavel);

        textLyrics = new JTextArea(30, 40);
        textLyrics.setText(lyrics);

        // uneditable JTextArea.
        textLyrics.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textLyrics);
        this.add(scrollPane, BorderLayout.CENTER);

        //table.setPreferredScrollableViewportSize(new Dimension(600, 100));

        JPanel down = new JPanel();
        down.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        backButton.setBackground(c);
        deleteButton.setBackground(c);
        addToPly.setBackground(c);
        playButton.setBackground(c);
        backToPLButton.setBackground(c);
        down.add(backToPLButton);
        down.add(backButton);
        down.add(deleteButton);
        down.add(addToPly);
        //down.add(playButton);


        JPanel west = new JPanel();
        textDetails = new JTextArea(19, 15);
        textDetails.setText(names);
        Font font = textDetails.getFont();
        textDetails.setFont(font.deriveFont(Font.BOLD));
        textDetails.setBackground(c);
        west.add(textDetails);


        this.add(up, BorderLayout.NORTH);
        this.add(down, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
    }

    public void setSongPanelInfo(Song song){
        this.songN = song.getPath();
        titleLavel.setText("  " + song.getTitle() + " Song Details");
        //genreName.setText(song.getGenre());
        textDetails.setText("\n    NAME:\n    " + song.getTitle() + "\n\n    AUTHOR:\n    " + song.getAuthor() + "\n\n    DURATION:\n    "+ song.getDuration()
        + "\n\n    GENRE:\n    " + song.getGenre() + "\n\n    ALBUM:\n    " + song.getAlbum() + "\n\n    OWNER:\n    " + song.getUserName());

    }

    public void registerSongController(ActionListener songListener) {
        playButton.addActionListener(songListener);
        backButton.addActionListener(songListener);
        deleteButton.addActionListener(songListener);
        //pauseButton.addActionListener(songListener);
        addToPly.addActionListener(songListener);
        backToPLButton.addActionListener(songListener);
    }

    public String getMusicPath() {
        return songN;
    }

    public void setSongLyrics(String s) {
        lyrics = s;
    }

    public void setLyrics(String lyrics) {
        textLyrics.setText(lyrics);
        int c = 0;
    }

    public void setFromPlaylist(boolean b) {
        backToPLButton.setEnabled(b);
    }
}
