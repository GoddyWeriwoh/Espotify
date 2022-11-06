package presentation.view;

import business.SongManager;
import business.entity.Song;
import presentation.MainFrame;
import presentation.controller.ListSongsController;
import presentation.controller.SongController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListSongsPanel extends JPanel{

    public static final String BUTN_BACK = "BUTN_BACK";
    public static final int NUM_ROWS = 5;


    private JTextField searchJtf;
    private JLabel search;
    private TableModel model;
    private TableRowSorter sorter;
    private JButton songsBackButton;
    private String data[][];
    private SongController songController;

    public ListSongsPanel() {
        data = new String[100][NUM_ROWS];
        //addListSongsInfo(songs, songModel);
        revalidate();
        repaint();
        this.setLayout(new BorderLayout(0, 0));


        String[] columnNames = {"NAME", "AUTHOR", "ALBUM", "GENRE", "OWNER"};

        JTable table = new JTable(data, columnNames);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                songController.setSelectedSongInfo(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });


        search = new JLabel("Search");
        model = new DefaultTableModel(data, columnNames);
        sorter = new TableRowSorter<>(model);
        searchJtf = new JTextField(15);
        songsBackButton = new JButton("Back");
        songsBackButton.setActionCommand(BUTN_BACK);


        JPanel up = new JPanel();
        up.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        up.add(search);
        up.add(searchJtf);

        table.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
        table.setPreferredScrollableViewportSize(new Dimension(600, 100));

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#009637"));

        JPanel down = new JPanel();
        songsBackButton.setBackground(Color.decode("#009637"));
        down.add(songsBackButton);

        this.add(up, BorderLayout.NORTH);
        this.add(down, BorderLayout.SOUTH);
        songsBackButton.setActionCommand(BUTN_BACK);


        searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchJtf.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchJtf.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchJtf.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }

    public void registerController(ListSongsController listSongsController, SongController songController) {
        songController = (SongController) songController;
        songsBackButton.addActionListener(listSongsController);
    }

    public void addListSongsInfo(ArrayList<Song> songList) {
        for (int i = 0; i < songList.size(); i++) {
            data[i][0] = songList.get(i).getTitle();
            data[i][1] = songList.get(i).getAuthor();
            data[i][2] = songList.get(i).getAlbum();
            data[i][3] = songList.get(i).getGenre();
            data[i][4] = songList.get(i).getUserName();
        }
    }
}
