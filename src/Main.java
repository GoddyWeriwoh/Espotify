import business.PlaylistManager;
import business.SongManager;
import business.UserManager;
import presentation.controller.*;
import presentation.MainFrame;
import presentation.view.ListSongsPanel;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create the model.
                UserManager userModel = new UserManager();
                SongManager songModel = new SongManager();
                PlaylistManager playlistModel = new PlaylistManager();

                // Create the main view.
                MainFrame view = new MainFrame();

                // Create the presentation.controller, which will receive both the model and
                // the vicd existing-project
                //git remote set-url origin https://atlassian.salle.url.edu:7943/scm/dpoo2122/dpoo-2122-s2-ice12.git
                //git push -u origin --all
                //git push origin --tagsew to set up its unidirectional associations
                AccessController accessController = new AccessController(view, userModel, songModel);
                MainController mainController = new MainController(view,songModel, playlistModel,userModel);
                AddSongController addSongController= new AddSongController(view, songModel, userModel);
                SettingsController settingsController = new SettingsController(view, userModel, songModel);
                ListSongsController listSongsController = new ListSongsController(view, userModel, songModel);
                SongController songController = new SongController(songModel, playlistModel, view);
                PlaylistController playlistController = new PlaylistController(view, songModel, playlistModel, userModel);
                //StatisticsController statisticsController = new StatisticsController(view,songModel);
                // Register the Controller to the View, which has an indirect
                // dependency on it by using the implemented Listener interface.
                // (View ---notifies---> ActionListener)
                view.registerAccessController(accessController);
                view.registerMainController(mainController);
                view.registerAddSongController(addSongController);
                view.registerSettingsController(settingsController);
                view.registerListSongsController(listSongsController, songController);
                view.registerSongController(songController);
                view.registerPlaylistController(playlistController);

                // Make the frame visible after we have configured the view.
                view.setVisible(true);
            }
        });
    }
}
