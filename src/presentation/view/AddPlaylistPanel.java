package presentation.view;

import presentation.view.Utilities.CheckboxListCellRenderer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class AddPlaylistPanel extends JPanel {
    public static final String TITLE_LABEL = "Playlist name";
    public static final int TEXT_FIELD_LABEL = 25;
    public static final String CANCEL_LABEL = "CANCEL";
    public static final String BTN_CANCEL = "BTN_CANCEL2";
    private static final String CREATE_PLAYLIST_BTN_LABEL = "Add PLaylist";
    public static final String BTN_CREATE_PLAYLIST = "CREATE_PLAYLIST_BTN";


    private  JButton register;
    private  JButton cancelRegister;
    private  JTextField titleTextField;



    public AddPlaylistPanel(){
        Color c3 = new Color(76, 175, 80);

        setLayout(new BorderLayout(20,30));
        setBorder(new EmptyBorder(150, 250, 200, 250) );
        setBackground(ViewElements.DARK_GREY);



        JList list = new JList();
        list.setCellRenderer(new CheckboxListCellRenderer());


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(ViewElements.BLACK);

        JLabel titleLabel = new JLabel();
        titleLabel. setFont(new Font("Arial", Font. BOLD, 15));
        titleLabel.setForeground(ViewElements.WHITE);
        titleLabel.setText(TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(titleLabel);

        this.titleTextField = new JTextField(TEXT_FIELD_LABEL);
        this.titleTextField.setBackground(ViewElements.WHITE);
        textPanel.add(this.titleTextField);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(ViewElements.BLACK);

        // Add song button
        register = new JButton(CREATE_PLAYLIST_BTN_LABEL);
        register.setText(CREATE_PLAYLIST_BTN_LABEL);
        register.setActionCommand(BTN_CREATE_PLAYLIST);
        register.setForeground(Color.black);
        register.setBackground(c3);
        register.setOpaque(true);
        register.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        register.setFocusPainted(false);
        buttonPanel.add(this.register, BorderLayout.SOUTH);

        //Cancel song button
        cancelRegister = new JButton(CANCEL_LABEL);
        cancelRegister.setText(CANCEL_LABEL);
        cancelRegister.setActionCommand(BTN_CANCEL);
        cancelRegister.setForeground(c3);
        cancelRegister.setBackground(Color.black);
        cancelRegister.setOpaque(true);
        cancelRegister.setBorder(BorderFactory.createEmptyBorder(2,32,2,32));
        cancelRegister.setFocusPainted(false);
        buttonPanel.add(this.cancelRegister, BorderLayout.SOUTH);

        JPanel black = new JPanel();
        black.setLayout(new BorderLayout(10,30));
        black.setBorder(new EmptyBorder(20, 100, 20, 100) );
        black.setBackground(ViewElements.BLACK);

        black.add(textPanel, BorderLayout.CENTER);
        black.add(buttonPanel,BorderLayout.SOUTH);

        add(black,BorderLayout.CENTER);
    }

    public void registerController(ActionListener actionListener){
        cancelRegister.addActionListener(actionListener);
        register.addActionListener(actionListener);
    }

    public String getPlaylistName() {
        return titleTextField.getText();
    }
}
