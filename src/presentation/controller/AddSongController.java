package presentation.controller;

import business.SongManager;
import business.UserManager;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import presentation.MainFrame;
import presentation.view.RegisterSongPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AddSongController implements ActionListener {
    private static final String INVALID_PATH = "Invalid file, must be mp3 format";
    private static final String ERROR = "Error";
    private static final String INVALID_INPUTS = "Provide at least a name, genre and a file path";

    private final MainFrame mainFrame;
    private final SongManager songManager;
    private final UserManager userManager;

    public AddSongController(MainFrame mainFrame, SongManager songManager, UserManager userManager){
        this.mainFrame = mainFrame;
        this.songManager = songManager;
        this.userManager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case RegisterSongPanel.BTN_CREATE_SONG:
                if(registerSongOkay()){
                    mainFrame.updateView(mainFrame.getRegisterSongPanel(), mainFrame.getMainPanel());
                }
                break;
            case RegisterSongPanel.BTN_CANCEL:
                mainFrame.updateView(mainFrame.getRegisterSongPanel(), mainFrame.getMainPanel());
                break;
        }
    }

    private boolean registerSongOkay() {
        String name = mainFrame.getRegisterSongPanel().getSongName();
        String author = mainFrame.getRegisterSongPanel().getAuthor();
        String genre = mainFrame.getRegisterSongPanel().getGenre();
        String album = mainFrame.getRegisterSongPanel().getAlbum();
        String path = mainFrame.getRegisterSongPanel().getPath();

        if(!songManager.hasInputs(name, path, genre)){
            JOptionPane.showMessageDialog(null, INVALID_INPUTS, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!songManager.checkPathOk(path)){
            JOptionPane.showMessageDialog(null, INVALID_PATH, ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }


        String duration = "";
        try{
             duration = getSongDuration(path);
        }catch (Exception e){
            e.printStackTrace();
        }


        songManager.registerSong(name, author, genre, album, duration, path, userManager.getUser().getUsername());

        return true;
    }

    private String getSongDuration(String path) throws InvalidDataException, UnsupportedTagException, IOException {
        String newPath1 = path.substring(path.indexOf("songs") + 5 , path.length());
        //String newPath = newPath1.substring(newPath1.indexOf(" ") + 1 , newPath1.length());
        StringBuilder _sb = new StringBuilder(newPath1);

        _sb.insert(0, "save/songs/");
        String finPath = _sb.toString();

        long timeInSecs = new Mp3File(finPath).getLengthInMilliseconds();
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(timeInSecs) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeInSecs)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(timeInSecs) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInSecs)));
    }
}
