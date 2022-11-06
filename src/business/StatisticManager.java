package business;
import business.entity.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The StatisticManager class manages the logic related to the playlists from the app.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-10
 */
public class StatisticManager {
    private static final String ERROR = "Error";
    private static final String NO_SONGS = "There are no songs in the system";
    /**
     * Generates the statistics traversing all the list of songs and selecting the genre of every song.
     *
     * @return the arraylist containing the statistics.
     */
    public ArrayList<Statistic> getStatistics(SongManager songManager) {
        boolean newStat;
        ArrayList<Song> songs = songManager.getSongList();
        ArrayList<Statistic> statistics = new ArrayList<>();
        //If we do not have song to retrieve data from, we display an error pop-up
        if (songs == null || songs.isEmpty()){
            JOptionPane.showMessageDialog(null, NO_SONGS, ERROR, JOptionPane.ERROR_MESSAGE);
            return null;
        }
        //We traverse the list of songs until we have collected all the data
        String genre = songs.get(0).getGenre();
        statistics.add(new Statistic(genre));
        for (int i = 1; i < songs.size(); i++) {
            newStat =true;
            genre = songs.get(i).getGenre();
            for (Statistic statistic: statistics) {
                //We update the data we have from this genre
                if (genre.equals(statistic.getGenre())) {
                    statistic.updateNumSongs();
                    newStat = false;
                }
            }
            //We do not have data from this type of genre
            if(newStat){
                statistics.add(new Statistic(genre));
            }
        }
        //Order statistics by descending order
        statistics.sort(Collections.reverseOrder());
        ArrayList<Statistic> toSend;
        //Send only top 10 stats
        if (statistics.size() >= 10){
            toSend= new ArrayList<>(10);
            for (int i=0; i<10; i++){
                toSend.add(statistics.get(i));
            }
        }
        else{
            toSend= new ArrayList<>(statistics);
        }

        return toSend;
    }
}

