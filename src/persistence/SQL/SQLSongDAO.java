package persistence.SQL;

import business.entity.Song;
import persistence.SongDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * The SQLSongDAO implements the {@link SongDAO} interface to persist the data in
 * a database.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-11
 */
public class SQLSongDAO implements SongDAO{

    /**
     * Saves a song in the database.
     *
     * @param song The song to be persisted
     */
    @Override
    public void addSong(Song song, int id) {
        String name = song.getTitle();
        String author = song.getAuthor();
        String genre = song.getGenre();
        String album = song.getAlbum();
        String duration = song.getDuration();
        String path = song.getPath();
        String username = song.getUserName();

        DateFormat formatter = new SimpleDateFormat("mm:ss");
        Time timeValue = null;
                try{
                    timeValue = new java.sql.Time(formatter.parse(duration).getTime());
                }catch (Exception e){
                    e.printStackTrace();
                }

        String query = "INSERT INTO song(id,name, author, genre, album, duration, path, username) VALUES ('" +
                0+"', '" +
                name + "', '" +
                author + "', '" +
                genre + "', '" +
                album + "', '" +
                timeValue + "', '" +
                path + "', '" +
                username + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Deletes a son from the database.
     *
     * @param song The song to be deleted
     * @return true if the deletion was successful
     */
    @Override
    public boolean deleteSong(Song song) {
        String query = "DELETE FROM playlist_song WHERE song = " + song.getId() +";";
        SQLConnector.getInstance().deleteQuery(query);
        query = "DELETE FROM song WHERE id = " + song.getId() +";";
        return SQLConnector.getInstance().deleteQuery(query);
    }

    /**
     * Retreves all the stores stored in the database.
     *
     * @return the list containing all the songs
     */
    @Override
    public ArrayList<Song> getSongList() {
        String query = "SELECT * FROM song;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        ArrayList<Song> songs = new ArrayList<>();
        try {
            while(result.next()){
                int id = result.getInt("id");
                String title = result.getString("name");
                String author = result.getString("author");
                String genre = result.getString("genre");
                String album = result.getString("album");

                SimpleDateFormat dateFormat= new SimpleDateFormat("mm:ss");
                String duration = dateFormat.format(result.getTime("duration").getTime());

                String username = result.getString("username");
                String path = result.getString("path");

                Song s = new Song(id, title, author, genre, album, duration, username, path);
                songs.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

}
