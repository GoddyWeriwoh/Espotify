package presentation.controller;

import business.SongManager;
import business.StatisticManager;
import business.entity.Statistic;
import presentation.MainFrame;
import presentation.view.StatisticsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The StatisticController class manages the behaviour to execute when  a button from the StatisticsPanel is pressed.
 * Is also in charge instantiating the view when the main controller needs the view.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-26
 */
public class StatisticsController implements ActionListener {
    private final StatisticManager statisticManager;
    private StatisticsPanel statisticsPanel;
    private final MainFrame mainFrame;
    private final SongManager songManager;
    private final MainController mainController;
    public StatisticsController(MainFrame mainFrame, SongManager songManager, MainController mainController){
        this.songManager = songManager;
        this.mainFrame = mainFrame;
        statisticManager = new StatisticManager();
        statisticsPanel = new StatisticsPanel();
        this.mainController = mainController;
    }
    public void generateStatistics(){
        ArrayList<Statistic> statistics = statisticManager.getStatistics(songManager);
        statisticsPanel = new StatisticsPanel(statistics);
        mainFrame.updateView(mainFrame.getMainPanel(), statisticsPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainController.BUTTON_BACK.equals(e.getActionCommand())) {
            mainFrame.updateViewStats(statisticsPanel.getStatisticsPanel(), mainFrame.getMainPanel(), mainController.getBackButton());

        }
    }

}
