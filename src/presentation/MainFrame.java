package presentation;

import business.PlaylistManager;
import business.SongManager;
import business.entity.Playlist;
import business.StatisticManager;
import business.entity.Song;
import business.entity.Statistic;
import persistence.SQL.SQLConnector;
import presentation.controller.*;
import presentation.view.ViewElements;
import presentation.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * The MainFrame class is a JFrame representing the top-level container in the program.
 *
 * The MainFrame class will be updated in every action to display the panel required from that action.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-04-19
 */
public class MainFrame extends JFrame {

    private MainPanel mainPanel;
    private AccessPanel accessPanel;
    private RegisterPanel registerPanel;
    private RegisterSongPanel registerSongPanel;
    private LoginPanel loginPanel;
    private BackViewPanel backgroundPanel;
    private SettingsPanel settingsPanel;
    private ListSongsPanel listSongPanel;
    private SongPanel songPanel;
    private PlaylistPanel playlistPanel;
    private JPanel mainView;
    private StatisticsPanel statisticsPanel;
    private AddPlaylistPanel addPlaylistPanel;
    private ChosenPlayListListPanel chosenPlayListListPanel;
    private ModifyPlaylistPanel modifyPlaylistPanel;
    private CurrentPlaylistPanel currentPlaylistPanel;
    private PlaylistModificationPanel playlistModificationPanel;
    private AddSongsPanel addSongsPanel;
    private DeletePLSongsPanel deletePLSongsPanel;
    private PlayingPanel playingPanel;
    //private StatisticsView statistics;

    public MainFrame() {
        playingPanel = new PlayingPanel();
        mainPanel = new MainPanel();
        accessPanel = new AccessPanel();
        registerPanel = new RegisterPanel();
        loginPanel = new LoginPanel();
        songPanel = new SongPanel();
        //listSongPanel = new ListSongsPanel(infoForListSongs);
        statisticsPanel = new StatisticsPanel();
        playlistPanel = new PlaylistPanel();
        settingsPanel = new SettingsPanel();
        registerSongPanel = new RegisterSongPanel();
        backgroundPanel = new BackViewPanel(accessPanel);
        addPlaylistPanel = new AddPlaylistPanel();
        chosenPlayListListPanel = new ChosenPlayListListPanel();
        modifyPlaylistPanel = new ModifyPlaylistPanel();
        currentPlaylistPanel= new CurrentPlaylistPanel();
        playlistModificationPanel = new PlaylistModificationPanel();
        addSongsPanel = new AddSongsPanel();
        deletePLSongsPanel = new DeletePLSongsPanel();

        this.setLayout(new BorderLayout());
        mainView = new JPanel();
        mainView.setBackground(ViewElements.DARK_GREY);
        mainView.add(backgroundPanel, BorderLayout.CENTER);
        mainView.add(playingPanel, BorderLayout.SOUTH);
        configureFrame();

        // Free the memory and disconnect database when closing the frame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
                SQLConnector.getInstance().disconnect();
            }
        });
    }

    /**
     * Swaps a panel with a different panel and updates the view.
     *
     * @param oldPanel the old panel
     * @param newPanel the new panel
     */
    public void updateView(JPanel oldPanel, JPanel newPanel) {
        backgroundPanel.changeCurrentPanel(oldPanel, newPanel);
        this.revalidate();
        this.repaint();

    }
    public void updateViewStats(JPanel oldPanel, JPanel newPanel, JButton jButton) {
        this.remove(jButton);
        backgroundPanel.changeCurrentPanel(oldPanel, newPanel);
        this.revalidate();
        this.repaint();

    }
    /**
     * Configures the frame.
     */
    private void configureFrame() {
        this.pack();
        this.setTitle(ViewElements.TITLE);
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(ViewElements.DARK_GREY);
        this.setContentPane(mainView);
    }

    /**
     * Register access presentation.controller.
     *
     * @param listener the listener
     */
    public void registerAccessController(ActionListener listener) {
        accessPanel.registerController(listener);
        registerPanel.registerController(listener);
        loginPanel.loginController(listener);
    }

    public AccessPanel getAccessPanel() {
        return accessPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public RegisterPanel getRegisterPanel() {
        return registerPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public RegisterSongPanel getRegisterSongPanel() {
        return registerSongPanel;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public ListSongsPanel getListSongsPanel() {
        return listSongPanel;
    }
    public SongPanel getSongPanel() {
        return songPanel;
    }

    public void registerMainController(MainController mainPanelListener) {
        mainPanel.registerController(mainPanelListener);
        settingsPanel.registerController(mainPanelListener);
    }

    public void registerAddSongController(AddSongController addSongListener) {
        registerSongPanel.registerController(addSongListener);
    }

    public void registerSettingsController(SettingsController settingsController) {
        settingsPanel.registerController(settingsController);
    }

    public void registerListSongsController(ListSongsController listSongsController, SongController songController) {
        listSongPanel.registerController(listSongsController, songController);
    }

    public void setSongListInfo(ArrayList<Song> songList, SongManager model) {
        listSongPanel = new ListSongsPanel();
        listSongPanel.addListSongsInfo(songList);
    }

    public void registerSongController(ActionListener songListener) {
        songPanel.registerSongController(songListener);
        playingPanel.registerPlayingController(songListener);
    }

    public void setSongLyrics(String s) {
        songPanel.setSongLyrics(s);
    }

    public StatisticsPanel getStatisticsPanel() {
        return statisticsPanel;
    }

    public PlaylistPanel getPlaylistPanel() {
        return playlistPanel;
    }

    public void registerPlaylistController(ActionListener listSongsController) {
        playlistPanel.registerController(listSongsController);
        addPlaylistPanel.registerController(listSongsController);
        chosenPlayListListPanel.registerPlaylistController(listSongsController);
        currentPlaylistPanel.registerPlaylistController(listSongsController);
        modifyPlaylistPanel.registerPlaylistController(listSongsController);
        playlistModificationPanel.registerPlaylistController(listSongsController);
        addSongsPanel.registerPlaylistController(listSongsController);
        deletePLSongsPanel.registerPlaylistController(listSongsController);
    }

    public AddPlaylistPanel getAddPlaylistPanel() {
        return addPlaylistPanel;
    }

    public ChosenPlayListListPanel getChosenPlayListListPanel() {
        return chosenPlayListListPanel;
    }

    public ModifyPlaylistPanel getModifyPlaylistPanel() {
        return modifyPlaylistPanel;
    }

    public CurrentPlaylistPanel getCurrentPlaylistPanel() {
        return currentPlaylistPanel;
    }

    public PlaylistModificationPanel getPlaylistModificationPanel() {
        return playlistModificationPanel;
    }

    public AddSongsPanel getAddSongsPanel() {
        return addSongsPanel;
    }

    public DeletePLSongsPanel getDeleteSongsPanel() {
        return deletePLSongsPanel;
    }
}
