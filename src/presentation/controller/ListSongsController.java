package presentation.controller;

import business.SongManager;
import business.UserManager;
import presentation.MainFrame;
import presentation.view.ListSongsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSongsController implements ActionListener {
    private MainFrame mainFrame;
    private UserManager userManager;
    private SongManager songManager;

    public ListSongsController(MainFrame mainFrame, UserManager userManager, SongManager songManager){
        this.mainFrame = mainFrame;
        this.userManager = userManager;
        this.songManager = songManager;

        mainFrame.setSongListInfo(songManager.getSongList(), songManager);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ListSongsPanel.BUTN_BACK:
                //view.getRegisterPanel().resetUI();
                mainFrame.updateView(mainFrame.getListSongsPanel(), mainFrame.getMainPanel());
                break;
        }
    }
}
