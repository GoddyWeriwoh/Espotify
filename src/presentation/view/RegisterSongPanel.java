package presentation.view;

import presentation.view.Utilities.JFilePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * The RegisterSongPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * It is responsible for adding a song into the system
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-05-10
 */

public class RegisterSongPanel extends JPanel {
    public static final String TITLE_LABEL = "Title";
    private static final String ALBUM_LABEL = "Album";
    private static final String GENRE_LABEL = "Genre";
    private static final String FILE_LABEL = "File";
    public static final int TEXT_FIELD_LABEL = 25;
    public static final String AUTHOR_LABEL = "Author";
    public static final String CANCEL_LABEL = "CANCEL";
    public static final String BTN_CANCEL = "BTN_CANCEL2";
    private static final String USERNAME_LABEL = "userName";
    private static final String CREATE_SONG_BTN_LABEL = "Add Song";
    public static final String BTN_CREATE_SONG = "CREATE_SONG_BTN";
    private static final String PATH_LABEL = "Path to song";


    private final JButton register;
    private final JButton cancelRegister;
    private final JTextField titleTextField;
    private final JTextField authorTextField;
    private final JTextField genreTextField;
    private final JTextField albumTextField;
    private final JTextField durationTextField;
    private final JFilePicker filePicker;


    public RegisterSongPanel() {
        Color c3 = new Color(76, 175, 80);

        setLayout(new BorderLayout(20,30));
        setBorder(new EmptyBorder(150, 250, 200, 250) );
        setBackground(ViewElements.DARK_GREY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(ViewElements.BLACK);

        JLabel titleLabel = new JLabel();
        titleLabel. setFont(new Font("Arial", Font. BOLD, 15));
        titleLabel.setForeground(ViewElements.WHITE);
        titleLabel.setText(TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(titleLabel);

        this.titleTextField = new JTextField(TEXT_FIELD_LABEL);
        this.titleTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.titleTextField);

        JLabel genreLabel = new JLabel();
        genreLabel.setFont(new Font("Arial", Font. BOLD, 15));
        genreLabel.setForeground(ViewElements.WHITE);
        genreLabel.setText(GENRE_LABEL);
        genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(genreLabel);

        this.genreTextField = new JTextField(TEXT_FIELD_LABEL);
        this.genreTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.genreTextField);


        JLabel albumLabel = new JLabel();
        albumLabel. setFont(new Font("Arial", Font. BOLD, 15));
        albumLabel.setForeground(ViewElements.WHITE);
        albumLabel.setText(ALBUM_LABEL);
        albumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(albumLabel);

        this.albumTextField = new JTextField(TEXT_FIELD_LABEL);
        this.albumTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.albumTextField);

        JLabel authorLabel = new JLabel();
        authorLabel. setFont(new Font("Arial", Font. BOLD, 15));
        authorLabel.setForeground(ViewElements.WHITE);
        authorLabel.setText(AUTHOR_LABEL);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(authorLabel);

        authorTextField = new JTextField(TEXT_FIELD_LABEL);
        authorTextField.setBackground(ViewElements.WHITE);
        textPanel.add(authorTextField);


        //Duration label
        JLabel durationLabel = new JLabel("Duration");
        durationLabel.setFont(new Font("Arial", Font. BOLD, 15));
        durationLabel.setForeground(ViewElements.WHITE);
        durationLabel.setText("duration");
        durationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //textPanel.add(durationLabel);

        //Duration text field
        this.durationTextField = new JTextField(TEXT_FIELD_LABEL);
        this.durationTextField.setBackground(ViewElements.WHITE);
        //textPanel.add(this.durationTextField);

        JLabel fileLabel = new JLabel();
        fileLabel.setFont(new Font("Arial", Font. BOLD, 15));
        fileLabel.setForeground(ViewElements.WHITE);
        fileLabel.setText(FILE_LABEL);
        fileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(fileLabel);

        filePicker = new JFilePicker("  Pick a file ", " Browse... ");
        filePicker.setMode(JFilePicker.MODE_OPEN);
        filePicker.addFileTypeFilter(".mp3", "MP3");
        filePicker.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(filePicker, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(ViewElements.BLACK);

        // Add song button
        register = new JButton(CREATE_SONG_BTN_LABEL);
        register.setText(CREATE_SONG_BTN_LABEL);
        register.setActionCommand(BTN_CREATE_SONG);
        register.setForeground(Color.black);
        register.setBackground(c3);
        register.setOpaque(true);
        register.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        register.setFocusPainted(false);
        buttonPanel.add(this.register, BorderLayout.SOUTH);

        //Cancel song button
        cancelRegister = new JButton(CANCEL_LABEL);
        cancelRegister.setText(CANCEL_LABEL);
        cancelRegister.setActionCommand(BTN_CANCEL);
        cancelRegister.setForeground(c3);
        cancelRegister.setBackground(Color.black);
        cancelRegister.setOpaque(true);
        cancelRegister.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        cancelRegister.setFocusPainted(false);
        buttonPanel.add(this.cancelRegister, BorderLayout.SOUTH);

        JPanel black = new JPanel();
        black.setLayout(new BorderLayout(10,30));
        black.setBorder(new EmptyBorder(20, 100, 20, 100) );
        black.setBackground(ViewElements.BLACK);

        black.add(textPanel, BorderLayout.CENTER);
        black.add(buttonPanel,BorderLayout.SOUTH);

        add(black,BorderLayout.CENTER);
    }

    public void registerController(ActionListener mainPanelListener) {
        register.addActionListener(mainPanelListener);
        cancelRegister.addActionListener(mainPanelListener);
    }

    public String getSongName(){
        return titleTextField.getText();
    }

    public String getAuthor() {
        return authorTextField.getText();
    }

    public String getGenre() {
        return genreTextField.getText();
    }

    public String getAlbum() {
        return albumTextField.getText();
    }

    public String getDuration() {
        return  durationTextField.getText();
    }

    public String getPath() {
        return filePicker.getSelectedFilePath();
    }

}
