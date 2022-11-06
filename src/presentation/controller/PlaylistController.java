package presentation.controller;

import business.PlaylistManager;
import business.SongManager;
import business.UserManager;
import business.entity.Playlist;
import business.entity.Song;
import presentation.MainFrame;
import presentation.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistController implements ActionListener {
    private static final String ERROR = "Error";
    private static final String INVALID_INPUT = "Sorry, you already have this playlist created";
    private static final Object INVALID_EMPTY = "A playlist name must be passed";
    private static final String RETRY = "Retry";
    private static final String RETRY_CLICK = "Please click again";
    private static final String ALREADY_EXIST_SONG = "This song already exist in this playlist";
    private static final String HIGH_LIMIT = "You cannot move the song higher";
    private static final String LOW_LIMIT = "You cannot move the song lower";

    private MainFrame view;
    private SongManager songModel;
    private PlaylistManager playlistModel;
    private UserManager userModel;

    public PlaylistController(MainFrame view1, SongManager model1, PlaylistManager playlistModel1, UserManager userModel1){
        view = view1;
        songModel = model1;
        playlistModel = playlistModel1;
        userModel = userModel1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case PlaylistPanel.BTN_ADD_PLAYLIST:
                view.updateView(view.getPlaylistPanel(), view.getAddPlaylistPanel());
                break;
            case PlaylistPanel.BTN_LIST_MY_PLAYLIST://ArrayList<Playlist> userPlaylists, PlaylistManager playlistModel, MainFrame mainFrame
                view.getChosenPlayListListPanel().addPlaylistList(true, playlistModel.getUserPlaylists(userModel.getUser()), playlistModel.getOthersPlaylists(userModel.getUser().getUsername()));
                view.updateView(view.getPlaylistPanel(), view.getChosenPlayListListPanel());
                break;
            case PlaylistPanel.BTN_MODIFY_PLAYLIST:
                view.getModifyPlaylistPanel().addPlaylistList(playlistModel.getUserPlaylists(userModel.getUser()));
                view.updateView(view.getPlaylistPanel(), view.getModifyPlaylistPanel());
                break;
            case PlaylistPanel.BTN_LIST_OTHERS_PLAYLIST:
                view.getChosenPlayListListPanel().addPlaylistList(false, playlistModel.getUserPlaylists(userModel.getUser()), playlistModel.getOthersPlaylists(userModel.getUser().getUsername()));
                view.updateView(view.getPlaylistPanel(), view.getChosenPlayListListPanel());
                break;
            case AddPlaylistPanel.BTN_CREATE_PLAYLIST:
                if(saveOkay()){
                    //updateInfoOfPlaylistCreated
                    view.updateView(view.getAddPlaylistPanel(), view.getPlaylistPanel());
                }else{
                    view.updateView(view.getAddPlaylistPanel(), view.getPlaylistPanel());
                }
                break;
            case AddPlaylistPanel.BTN_CANCEL:
                view.updateView(view.getAddPlaylistPanel(), view.getPlaylistPanel());
                break;

            case ChosenPlayListListPanel.BACK_BTN:
                view.updateView(view.getChosenPlayListListPanel(), view.getPlaylistPanel());
                break;
            case PlaylistPanel.BTN_BACK:
                view.updateView(view.getPlaylistPanel(), view.getMainPanel());
                break;
            case CurrentPlaylistPanel.BACK_BTN:
                boolean isMyPlaylist = view.getChosenPlayListListPanel().getPlaylistStatus();
                try{
                    view.getChosenPlayListListPanel().addPlaylistList(isMyPlaylist, playlistModel.getUserPlaylists(userModel.getUser()), playlistModel.getOthersPlaylists(userModel.getUser().getUsername()));
                    view.updateView(view.getCurrentPlaylistPanel(), view.getChosenPlayListListPanel());
                }catch (NullPointerException e1){
                    JOptionPane.showMessageDialog(null, RETRY_CLICK, RETRY, JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case ModifyPlaylistPanel.BACK_BTN:
                view.updateView(view.getModifyPlaylistPanel(), view.getPlaylistPanel());
                break;
            case PlaylistModificationPanel.BACK_BTN:
                //view.getModifyPlaylistPanel().addPlaylistList(playlistModel.getUserPlaylists(userModel.getUser()),this);
                view.updateView(view.getPlaylistModificationPanel(), view.getModifyPlaylistPanel());
                break;

            case AddSongsPanel.BACK_BTN:
                //view.getPlaylistModificationPanel().setPlaylistPanelInfo(view.getPlaylistModificationPanel().getCurrentPlaylist());
                view.updateView(view.getAddSongsPanel(), view.getPlaylistModificationPanel());
                break;

            case PlaylistModificationPanel.ADD_SONG_BTN:
                view.getAddSongsPanel().addSongsList(songModel.getSongList());
                view.updateView(view.getPlaylistModificationPanel(), view.getAddSongsPanel());
                break;

            case PlaylistModificationPanel.DELETE_SONG_BTN:
                view.getDeleteSongsPanel().addSongsList(playlistModel.getCurrentPlaylist());
                view.updateView(view.getPlaylistModificationPanel(), view.getDeleteSongsPanel());
                break;

            case DeletePLSongsPanel.BACK_BTN:
                view.updateView(view.getDeleteSongsPanel(), view.getPlaylistModificationPanel());
                break;
            case PlaylistModificationPanel.BTN_ARR_UP:
                try{
                    playlistModel.decrementChosenSongOrder();
                }
                catch (IndexOutOfBoundsException eIo){
                    JOptionPane.showMessageDialog(null, HIGH_LIMIT, ERROR, JOptionPane.ERROR_MESSAGE);
                }
                view.getPlaylistModificationPanel().setPlaylistPanelInfo(playlistModel.getCurrentPlaylist());
                view.updateView(view.getPlaylistModificationPanel(), view.getPlaylistModificationPanel());
                break;
            case PlaylistModificationPanel.BTN_ARR_DOWN:
                try{
                    playlistModel.incrementChosenSongOrder();
                }
                catch (IndexOutOfBoundsException eIo){
                    JOptionPane.showMessageDialog(null, LOW_LIMIT, ERROR, JOptionPane.ERROR_MESSAGE);
                }
                view.getPlaylistModificationPanel().setPlaylistPanelInfo(playlistModel.getCurrentPlaylist());
                view.updateView(view.getPlaylistModificationPanel(), view.getPlaylistModificationPanel());
                break;
            case ChosenPlayListListPanel.START_PL_BTN:
                playlistModel.setCurrentSongPosition(0);
                view.getSongPanel().setFromPlaylist(true);
                songModel.setCurrentSong(playlistModel.getCurrentPlaylist().getSongs().get(0).getSong().getTitle());
                view.getSongPanel().setSongPanelInfo(songModel.getCurrentSong());
                view.updateView(view.getChosenPlayListListPanel(), view.getSongPanel());
                break;
        }
    }

    private boolean saveOkay() {
        String name = view.getAddPlaylistPanel().getPlaylistName();
        String userName = userModel.getUserName();
        if(playlistModel.playlistExists(name, userName)){
            JOptionPane.showMessageDialog(null, INVALID_INPUT, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(view.getAddPlaylistPanel().getPlaylistName() == ""){
            JOptionPane.showMessageDialog(null, INVALID_EMPTY, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        playlistModel.createPlaylist(new Playlist(0, name, userModel.getUser()));
        return true;
    }

    /**
     * Sets the information of the selected playlist accordingly
     * @param playlistName String
    *   @param isMyPlayList boolean
     */
    public void setSelectedPlaylistInfo(String playlistName, boolean isMyPlayList) {
       boolean b = false;
        int position = 0;

        if(isMyPlayList){
            for (int i = 0; i < playlistModel.getUserPlaylists(userModel.getUser()).size() && !b; i++) {
                Playlist p = playlistModel.getUserPlaylists(userModel.getUser()).get(i);
                if(p.getTitle().equals(playlistName)){
                    position = i;
                    b = true;
                }
            }
        }
        else{
            for (int i = 0; i < playlistModel.getOthersPlaylists(userModel.getUserName()).size() && !b; i++) {
                Playlist p = playlistModel.getOthersPlaylists(userModel.getUserName()).get(i);
                if(p.getTitle().equals(playlistName)){
                    position = i;
                    b = true;
                }
            }
        }

        if(b){
            if(isMyPlayList){
                view.getCurrentPlaylistPanel().setPlaylistPanelInfo(playlistModel.getUserPlaylists(userModel.getUser()).get(position));
                view.updateView(view.getChosenPlayListListPanel(), view.getCurrentPlaylistPanel());
            }
            else{
                view.getCurrentPlaylistPanel().setPlaylistPanelInfo(playlistModel.getOthersPlaylists(userModel.getUserName()).get(position));
                view.updateView(view.getChosenPlayListListPanel(), view.getCurrentPlaylistPanel());
            }

        }

    }

    public void setPlaylistModificationInfo(String playlistName) {
        playlistModel.setCurrentPlaylist(playlistName, userModel.getUser());
        view.getPlaylistModificationPanel().setPlaylistPanelInfo(playlistModel.getCurrentPlaylist());
        view.updateView(view.getModifyPlaylistPanel(), view.getPlaylistModificationPanel());
    }

    public void storeSong(String songName) {
        if(!(playlistModel.storeSongToCurrPlaylist(songModel.getSong(songName)))) {
            JOptionPane.showMessageDialog(null, ALREADY_EXIST_SONG, ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSongFromPlayList(String songName) {
        playlistModel.deleteSongFromPlaylist(songModel.getSong(songName));
    }

    public void setCurrentSong(String songName) {
        playlistModel.setChosenSong(songName);
    }

    public void setSelectedPlaylistListInfo(String currPlaylist) {
        playlistModel.setCurrentPlaylist(playlistModel.getPlaylist(currPlaylist, userModel.getUser()).getTitle(), userModel.getUser());
    }

    public void setIsMyPlaylist(boolean isMyPlaylist) {
        playlistModel.setIsMyPlaylist(isMyPlaylist);
    }


    /*public void setSelectedPlaylistListInfo(String toString, ) {
        view.updateView(view.getModifyPlaylistPanel(), view.getModifyingPlaylistPanel());
    }*/
}