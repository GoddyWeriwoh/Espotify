package business;

import business.entity.Playlist;
import business.entity.Song;
import business.entity.SongOrdered;
import business.entity.User;
import persistence.PlaylistDAO;
import persistence.SQL.SQLPlaylistDAO;

import java.util.ArrayList;
/**
 * The PlaylistManager class focuses on logic related to the playlists from the users in the app.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-25
 */
public class PlaylistManager {

    private final PlaylistDAO playlistDAO;
    private Playlist currentPlaylist;
    private String chosenSong;
    private boolean isMyPlaylist;

    public PlaylistManager(){
        playlistDAO = new SQLPlaylistDAO();
        isMyPlaylist = true;
        //currentPlaylist = new Playlist();
    }

    public void createPlaylist(Playlist playlist) {
        playlistDAO.addPlaylist(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        if(!playlistDAO.deletePlaylist(playlist)){
            System.out.println("Something unexpected occurred while deleting the playlist."+
                    " Please, check the connection of the system and try again");
        }
    }

    /*public void addSongToPlaylist(Playlist playlist, Song song){
        playlistDAO.addSongToPlaylist(playlist,song);
        playlist.addSong(song);
    }*/

    /**
     * Gets the list of songs from a particular playlist
     * @param playlistName String
     * @return ArrayList<Song>
     */
    public ArrayList<Song> getSongListFromPlaylist(String playlistName) {
        return playlistDAO.getSongListFromPlaylist(playlistName);
    }

    /**
     *Deletes song from playlist
     * @param song
     */
    public void deleteSongFromPlaylist(Song song){
        if(!playlistDAO.deleteSongFromPlaylist(currentPlaylist,song)){
            System.out.println("Something unexpected occurred while deleting the song from the playlist."+
                    " Please, check the connection of the system and try again");
        }
        else{
            for (int i = 0; i < currentPlaylist.getSongsOrdered().size(); i++) {
                if(currentPlaylist.getSongTitle(i).equals(song.getTitle())){
                    currentPlaylist.removeSong(i);
                }
            }
            playlistDAO.refreshPlaylist(currentPlaylist);
        }

    }

    public boolean playlistExists(String name, String userName) {

        return playlistDAO.playlistNameExists(name, userName);
    }

    /**
     * Gets the list of playlists names created by the current user
     * @param currentUser User
     * @return ArrayList<Playlist>
     */
    public ArrayList<Playlist> getUserPlaylists(User currentUser) {
        return new ArrayList<Playlist>(playlistDAO.getUserPlaylists(currentUser));
    }

    /**
     * Gets the list of playlists that the current user did not create.
     * @param userName String
     * @return ArrayList<Playlist>
     */
    public ArrayList<Playlist> getOthersPlaylists(String userName) {
        return new ArrayList<Playlist>(playlistDAO.getOthersPlaylists(userName));
    }

    /**
     * Sets the current playlist
     * @param playlistName String
     * @param currentUser User
     */
    public void setCurrentPlaylist(String playlistName, User currentUser) {
        int position = 0;
        Playlist currPlayList;
        boolean b = false;
        for (int i = 0; i < getUserPlaylists(currentUser).size() && !b; i++) {
            currPlayList = getUserPlaylists(currentUser).get(i);
            if(currPlayList.getTitle().equals(playlistName)){
                position = i;
                b = true;
            }
        }
        currentPlaylist = getUserPlaylists(currentUser).get(position);
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    /**
     *
     * @param song Song
     * @return Stores  a song to the current playlist
     */
    public boolean storeSongToCurrPlaylist(Song song) {
        for (int i = 0; i <currentPlaylist.getSongs().size(); i++) {
            if(currentPlaylist.getSongs().get(i).getSong().getTitle().equals(song.getTitle())){
                return false;
            }
        }
        SongOrdered songOrdered = new SongOrdered(currentPlaylist.getSongs().size(), song);
        currentPlaylist.addSong(songOrdered);
        playlistDAO.addSongToPlaylist(currentPlaylist, songOrdered);
        return true;
    }

    /**
     * sets the chosen song name from the playlist modification panel.
     * @param songName String
     */
    public void setChosenSong(String songName) {
        chosenSong = songName;
    }
    /**
     * Decrements the order position of the chosen song.
     */
    public void decrementChosenSongOrder() throws IndexOutOfBoundsException{
        currentPlaylist.decrementCurrentSongOrder(chosenSong);
        playlistDAO.refreshPlaylist(currentPlaylist);
    }
    /**
     * Increments the order position of the chosen song.
     */
    public void incrementChosenSongOrder() throws IndexOutOfBoundsException{
        currentPlaylist.incrementCurrentSongOrder(chosenSong);
        playlistDAO.refreshPlaylist(currentPlaylist);
    }

    /**
     * Gets a playlist from the database
     * @param currPlaylist Playlist
     * @param user User
     * @return Playlist
     */
    public Playlist getPlaylist(String currPlaylist, User user) {
        ArrayList<Playlist> playlists = null;
        if(isMyPlaylist){
            playlists = playlistDAO.getUserPlaylists(user);
        }
        else{
            playlists = playlistDAO.getOthersPlaylists(user.getUsername());
        }
        for (int i = 0; i < playlists.size(); i++) {
            if(playlists.get(i).getTitle().equals(currPlaylist)){
                return playlists.get(i);
            }
        }
        return null;
    }

    /**
     * Sets the boolean to insure if its the current user's playlist or not.
     * @param isMyPlaylist boolean
     */
    public void setIsMyPlaylist(boolean isMyPlaylist) {
        this.isMyPlaylist = isMyPlaylist;
    }

    /**
     * Sets the current playing song position
     * @param i int
     */
    public void setCurrentSongPosition(int i) {
        currentPlaylist.setCurrentSongPosition(i);
    }

    /**
     *  Gets the current playing song position
     * @return int
     */
    public int getCurrentSongPosition() {
        return currentPlaylist.getCurrentSongPosition();
    }

    /**
     * Increments the current song position in playlist
     */
    public void incrementCurrentSongPosition() {
        currentPlaylist.incrementCurrentSongPosition();
    }

    public boolean playlistOver() {
        return currentPlaylist.playlistOver();
    }
}
