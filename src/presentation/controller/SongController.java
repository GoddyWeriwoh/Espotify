package presentation.controller;
import business.LyricsManager;
import business.PlaylistManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import presentation.view.PlayingPanel;
import presentation.view.SongPanel;
import business.SongManager;
import presentation.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The SongController class defines the behaviour to execute when a button from the SongPanel
 * is pressed.
 *
 * Concretely, it implements the {@link ActionListener} interface to control the buttons to
 * play a song, delete a song, and to go back to the MainPanel.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since 2022-05-12
 */

public class SongController  extends Thread implements ActionListener {
    private static final String ERROR = "Error";
    private static final String SONG_NOT_DELETED = "Could not delete song";
    private static final Object NOT_CURRENT_USER = "You do not have permission to delete this song";

    private final SongManager songManager;
    private final MainFrame view;
    private LyricsManager lyricsManager;
    private FileInputStream fileInputStream;

    //private MusicPlayer musicPlayer;
    //private AdvancedPlayer player;
    private int pausedOnFrame;
    private String path;
    private BufferedInputStream bufferedInputStream;
    private File file;
    private PlaylistManager playlistManager;
    private MusicPlayer player;
    private boolean c;
    SongPlayer songPlayer = new SongPlayer();


    public SongController(SongManager songManager, PlaylistManager playlistManager, MainFrame view){
        this.songManager = songManager;
        this.view = view;
        this.playlistManager = playlistManager;
        player = null;
        c = false;
    }
    public void setSelectedSongInfo(String songName){
        songManager.setCurrentSong(songName);
        view.getSongPanel().setFromPlaylist(false);
        view.getSongPanel().setSongPanelInfo(songManager.getSong(songName));
        view.updateView(view.getListSongsPanel(), view.getSongPanel());
        showLyrics();
        /*try{
            setPlayer(songManager.getSong(songName).getPath());
        }catch (Exception e){
            e.printStackTrace();
        }
        //PausablePlayer player = new PausablePlayer(fileInputStream);*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            path = songManager.getCurrentSong().getPath();
            fileInputStream = new FileInputStream(setPath(path));
            player = new MusicPlayer(fileInputStream);
        } catch (FileNotFoundException | JavaLayerException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        switch (e.getActionCommand()){
            case PlayingPanel.AC_PLAY:
                c = !c;
                do{
                    showLyrics();
                    lyricsManager = new LyricsManager();
                    lyricsManager.setInfo(songManager.getCurrentSong().getTitle(), songManager.getCurrentSong().getAuthor());
                    lyricsManager.start();
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String s = lyricsManager.getCurrentLyrics();
                            view.setSongLyrics(s);
                            view.updateView(view.getSongPanel(),view.getSongPanel());
                        }
                    });
                    t.start();
                    songPlayer.start();
                    if(songPlayer.isOver()){
                        goToNextSong();
                    }
               }while(c);//while (!playlistManager.playlistOver() && !stopped);

                break;
            case SongPanel.BACK_TO_PL:
                view.updateView(view.getSongPanel(),view.getChosenPlayListListPanel());
                break;
            case SongPanel.BTN_BACK:
                view.updateView(view.getSongPanel(),view.getListSongsPanel());
                break;
            case SongPanel.BTN_DELETE:
                if(validated()){
                    if(songManager.deleteSong(songManager.getCurrentSong())){
                        view.updateView(view.getSongPanel(),view.getListSongsPanel());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, SONG_NOT_DELETED, ERROR, JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case PlayingPanel.AC_NEXT:
                //player.stop();
                goToNextSong();
                showLyrics();
                lyricsManager = new LyricsManager();
                lyricsManager.setInfo(songManager.getCurrentSong().getTitle(), songManager.getCurrentSong().getAuthor());
                lyricsManager.start();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String s = lyricsManager.getCurrentLyrics();
                        view.setSongLyrics(s);
                        view.updateView(view.getSongPanel(),view.getSongPanel());
                    }
                });
                t.start();
                songPlayer.interrupt();
                songPlayer.start();
                if(songPlayer.isOver()){
                    goToNextSong();
                }
                break;
        }
    }

    private void goToNextSong() {
        playlistManager.incrementCurrentSongPosition();
        int posi = playlistManager.getCurrentSongPosition();
        songManager.setCurrentSong(playlistManager.getCurrentPlaylist().getSongs().get(posi).getSong().getTitle());
        view.getSongPanel().setSongPanelInfo(songManager.getCurrentSong());

        try {
            path = songManager.getCurrentSong().getPath();
            fileInputStream = new FileInputStream(setPath(path));
            player = new MusicPlayer(fileInputStream);
        } catch (FileNotFoundException | JavaLayerException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        view.updateView(view.getSongPanel(), view.getSongPanel());
    }

    private boolean validated(){
        int answer = JOptionPane.showConfirmDialog(null,
                "Are you sure to delete?", "Confirmation",
                JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION){
            if(!songManager.isUser(songManager.getUser())){
                JOptionPane.showMessageDialog(null, NOT_CURRENT_USER, ERROR, JOptionPane.ERROR_MESSAGE);
            }
           return true;
        }
        return false;
    }

    private String setPath(String path1) throws FileNotFoundException, JavaLayerException {
        path = path1;
        String newPath1 = path.substring(path.indexOf("songs") + 5 , path.length());
        StringBuilder _sb = new StringBuilder(newPath1);
        _sb.insert(0, "save/songs/");
        String finPath = _sb.toString();
        return finPath;
        /*
        * file = new File(finPath);
        fileInputStream = new FileInputStream(file);
        bufferedInputStream = new BufferedInputStream(fileInputStream);
        player = new AdvancedPlayer(bufferedInputStream);*/
    }

    public void showLyrics(){
        String author = songManager.getCurrentSong().getAuthor() + "/";
        author = author.replace(' ', '_');
        String title = songManager.getCurrentSong().getTitle();
        title = title.replace(' ', '_');
        String lyrics = "Could not find this song's lyrics, please double check if the data entered is correct";

        try {
            URL link = new URL("https://api.lyrics.ovh/v1/" + author + title );
            System.out.println(link);
            HttpURLConnection conn = (HttpURLConnection)link.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }

            String inline = "";

            Scanner scanner;
            for(scanner = new Scanner(link.openStream()); scanner.hasNext(); inline = inline + scanner.nextLine()) {
            }

            scanner.close();
            JsonParser parse = new JsonParser();
            JsonObject data_obj = (JsonObject) parse.parse(inline);
            lyrics = data_obj.get("lyrics").getAsString();
            //System.out.println(lyrics);
        } catch (Exception var11) {
            var11.printStackTrace();
        }
        view.getSongPanel().setLyrics(lyrics);
        view.updateView(view.getSongPanel(),view.getSongPanel());
    }

    private class SongPlayer extends Thread{
        private boolean isOver;
        public void run(){
            try {
                if(c){
                    player.play();
                }
                else{
                    player.pause();
                }

            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }

         /*player.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.stop();
                // next audio file
            }
        });*/
        public boolean isOver(){
            return isOver;
        }
    }
}

