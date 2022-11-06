package presentation.controller;

import business.PlaylistManager;
import business.SongManager;
import business.UserManager;
import presentation.MainFrame;
import presentation.view.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private MainFrame mainFrame;
    private SongManager songManager;
    private PlaylistManager playlistManager;
    private UserManager userManager;
    private StatisticsController statisticsController;
    private JButton backButton;
    public static final String BUTTON_BACK = "BUTON_BACK";

    public MainController(MainFrame mainFrame, SongManager model, PlaylistManager playlistModel1, UserManager userModel1){
        this.mainFrame = mainFrame;
        this.songManager = model;
        playlistManager = playlistModel1;
        userManager = userModel1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case MainPanel.BTN_ADD_SONG -> mainFrame.updateView(mainFrame.getMainPanel(), mainFrame.getRegisterSongPanel());
            case MainPanel.BTN_LIBRARY -> {
                updateListSongsPanelInfo();
                mainFrame.updateView(mainFrame.getMainPanel(), mainFrame.getListSongsPanel());
            }
            case MainPanel.BTN_SETTINGS -> mainFrame.updateView(mainFrame.getMainPanel(), mainFrame.getSettingsPanel());
            //Controls the statistics when those are needed and the back button to return to the main panel
            case MainPanel.BTN_STATISTICS -> {
                backButton = new JButton("Back");
                statisticsController = new StatisticsController(mainFrame, songManager, this);
                statisticsController.generateStatistics();
                JPanel test = new JPanel();
                test.setBorder(new EmptyBorder(20,200,20,200));
                test.setBackground(ViewElements.DARK_GREY);
                test.add(backButton, BorderLayout.CENTER);
                backButton.setForeground(ViewElements.BLACK);
                backButton.setBackground(ViewElements.GREEN);
                backButton.setActionCommand(BUTTON_BACK);
                mainFrame.add(test, BorderLayout.EAST);
                backButton.addActionListener(statisticsController);
            }
            case MainPanel.BTN_PLAYLIST -> mainFrame.updateView(mainFrame.getMainPanel(), mainFrame.getPlaylistPanel());
        }
    }

    /**
     * Returns the button we need to delete from the view
     *
     * @return the button back to delete from the main view
     */
    public JButton getBackButton(){
        return backButton;
    }


    private void updateListSongsPanelInfo() {
        mainFrame.getListSongsPanel().addListSongsInfo(songManager.getSongList());
    }

    private boolean registerSongOkay() {
        String name = mainFrame.getRegisterSongPanel().getSongName();
        String author = mainFrame.getRegisterSongPanel().getAuthor();
        String genre = mainFrame.getRegisterSongPanel().getGenre();
        String album = mainFrame.getRegisterSongPanel().getAlbum();
        String duration = mainFrame.getRegisterSongPanel().getDuration();
        String path = mainFrame.getRegisterSongPanel().getPath();

        songManager.registerSong(name, author, genre, album, duration, "", path);
        return true;
    }
}
