package presentation.view;

import business.entity.Statistic;
import presentation.MainFrame;
import presentation.controller.StatisticsController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The StatisticsPanel extends the {@link JPanel} to define a concrete implementation
 * of it.
 *
 * It is responsible for drawing the graphs and displaying the top 10 genres most common among the
 * songs loaded.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-05-10
 */

public class StatisticsPanel extends JPanel {
    private final ArrayList<Statistic> values;

    /**
     * Default constructor to initialise the class.
     */
    public StatisticsPanel(){
        this.values = new ArrayList<>();
    }

    /**
     * Constructor to initialize the drawing of the graphs. It is passed as parameter the list of statistics generated
     * in the manager to draw the data in a bar graph.
     *
     * @param v The list of statistics
     */
    public StatisticsPanel(ArrayList<Statistic> v) {
        values = new ArrayList<>(v);
        setLayout(new BorderLayout(20,40));
        setBorder(new EmptyBorder(300,400,400,400) );
        setBackground(ViewElements.DARK_GREY);
        JPanel statusPanel = new JPanel(new GridLayout(1, 3));

        statusPanel.setBackground(ViewElements.GREEN);
        statusPanel.setBackground(Color.BLUE);
        this.add(statusPanel, BorderLayout.CENTER);
    }

    /**
     * Calls paint. Doesn't clear the background but see ComponentUI.update, which is called by paintComponent.
     * @param graphics The Graphics context in which to paint
     */

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        this.setBackground(ViewElements.LIGHT_GREY);

        //if the list of values is empty, we are not painting the graphs and just inform about to the user using a pop-up
        if (values.isEmpty())
            return;
        //retrieve the maximum and minimum value to calculate the scalar factor X
        double minValue = 0;
        double maxValue = 0;
        for (Statistic value : values) {
            if (minValue > value.getNumSongs())
                minValue = value.getNumSongs();
            if (maxValue < value.getNumSongs())
                maxValue = value.getNumSongs();
        }
        //retrieve the size of the panel to calculate the scalar factor X and Y
        Dimension dimension = getSize();
        int panelWidth = dimension.width;
        int panelHeight = dimension.height;
        int barWidth = panelWidth / values.size() - 5;

        //Writes the title
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        FontMetrics titleFontMetrics = graphics.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = graphics.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth("Statistics");
        int y = titleFontMetrics.getAscent();
        int x = (panelWidth - titleWidth) / 2;
        graphics.setFont(titleFont);
        graphics.setColor(Color.black);
        graphics.drawString("Statistics", x, y);

        //Calculating the scalar factor using the
        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight() + 10;
        if (maxValue == minValue)
            return;
        double scale = (panelHeight - 25.5 - top - bottom) / (maxValue - minValue);

        //Draws the axis of the bar
        graphics.setFont(labelFont);
        graphics.setColor(ViewElements.BLACK);
        graphics.drawRect(panelWidth - 50 - labelFontMetrics.stringWidth("100"), 30, 1, panelHeight - 80);
        graphics.drawRect(0, panelHeight - 50, panelWidth - 50 - labelFontMetrics.stringWidth("100"), 1);

        //Traverses the list of statistics drawing the statistics according to its value and the scalar factor
        // previously calculated. Adds the string title of the genre below every bar plotted (X-axis).
        for (int i = 0; i < values.size(); i++) {
            int valueX = i * barWidth;
            int valueY = top;
            int height = (int) (values.get(i).getNumSongs() * scale);
            if (values.get(i).getNumSongs() >= 0)
                valueY += (int) ((maxValue - values.get(i).getNumSongs()) * scale);
            else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            graphics.setColor(ViewElements.getColors(i));
            graphics.fillRect(valueX, valueY, barWidth - (barWidth / 2), height);
            graphics.setColor(Color.black);
            graphics.drawRect(valueX, valueY, barWidth - (barWidth / 2), height);
            graphics.drawString(values.get(i).getGenre(), valueX + (barWidth / 10), panelHeight - bottom);
        }
        //Draws the reference points to know how many songs are from every genre (Y-axis).
        for (int i = 0; i <= maxValue; i++) {
            graphics.setColor(Color.black);
            graphics.drawString(i + "", panelWidth - labelFontMetrics.stringWidth("100"), (int) ((-(panelHeight - 60 - top) * i) / maxValue + panelHeight - 50));
        }

        revalidate();
    }

    /**
     * Returns the statistics panel to plot in on screen.
     * @return statistics panel
     */

    public StatisticsPanel getStatisticsPanel() {
        return this;
    }
}