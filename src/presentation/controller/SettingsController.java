package presentation.controller;

import business.SongManager;
import business.UserManager;
import presentation.MainFrame;
import presentation.view.LoginPanel;
import presentation.view.SettingsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController implements ActionListener {
    private final MainFrame mainFrame;
    private final UserManager userManager;
    private SongManager songManager;

    public SettingsController(MainFrame mainFrame, UserManager userManager, SongManager songManager){
        this.mainFrame = mainFrame;
        this.userManager = userManager;
        this.songManager = songManager;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SettingsPanel.BTN_BACK -> mainFrame.updateView(mainFrame.getSettingsPanel(), mainFrame.getMainPanel());
            case SettingsPanel.BTN_DELETE_ACCOUNT -> {
                deleteAllInfo();
                mainFrame.updateView(mainFrame.getSettingsPanel(), mainFrame.getAccessPanel());
            }
            case SettingsPanel.BTN_LOGOUT -> mainFrame.updateView(mainFrame.getSettingsPanel(), mainFrame.getAccessPanel());
        }
    }

    private void deleteAllInfo() {
        userManager.deleteAccount();
    }
}
