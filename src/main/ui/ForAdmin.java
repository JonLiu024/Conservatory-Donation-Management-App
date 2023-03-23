package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForAdmin extends JFrame implements ActionListener, CommonComponents {

    private JButton addWildlifeButton;
    private JButton trackDonorInfoButton;
    private JButton trackWildlifeInfoButton;
    private JButton backToMainMenuButton;
    private JPanel titlePanel;



    public ForAdmin() {
        this.setTitle("Menu for Staff");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(createTitlePanel());
        this.add(createAddWildlifeButton());
        this.add(createTrackDonorInfoButton());
        this.add(createTrackWLInfoButton());
        this.add(createBackToPreviousButton());
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        Border border = BorderFactory.createLineBorder(new Color(255, 215, 0));
        this.setVisible(true);

    }


    public JButton createAddWildlifeButton() {
        addWildlifeButton = new JButton();
        ImageIcon addWLImage = new ImageIcon("./data/media/add wildlife .jpg");
        addWildlifeButton.setIcon(addWLImage);
        addWildlifeButton.setBounds(101, 251, 200, 125);
        addWildlifeButton.setText("Add a wildlife");
        addWildlifeButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        addWildlifeButton.setVerticalTextPosition(JButton.BOTTOM);
        addWildlifeButton.setHorizontalTextPosition(JButton.CENTER);
        addWildlifeButton.setIconTextGap(1);;
        addWildlifeButton.setForeground(Color.black);
        addWildlifeButton.setBackground(Color.PINK);
        addWildlifeButton.setVisible(true);
        addWildlifeButton.addActionListener(this);
        return addWildlifeButton;
    }


    public JButton createTrackDonorInfoButton() {
        trackDonorInfoButton = new JButton();
        ImageIcon addTrackDonorImage = new ImageIcon("./data/media/donor info2 .jpg");
        trackDonorInfoButton.setIcon(addTrackDonorImage);
        trackDonorInfoButton.setBounds(401, 251, 200, 125);
        trackDonorInfoButton.setText("Track donor info");
        trackDonorInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackDonorInfoButton.setVerticalTextPosition(JButton.BOTTOM);
        trackDonorInfoButton.setHorizontalTextPosition(JButton.CENTER);
        trackDonorInfoButton.setIconTextGap(1);;
        trackDonorInfoButton.setForeground(Color.black);
        trackDonorInfoButton.setBackground(Color.PINK);
        trackDonorInfoButton.setVisible(true);
        trackDonorInfoButton.addActionListener(this);
        return trackDonorInfoButton;
    }


    public JButton createTrackWLInfoButton() {
        trackWildlifeInfoButton = new JButton();
        ImageIcon trackWLInfoImage = new ImageIcon("./data/media/wildlife info.jpg");
        trackWildlifeInfoButton.setIcon(trackWLInfoImage);
        trackWildlifeInfoButton.setBounds(101, 500, 200, 125);
        trackWildlifeInfoButton.setText("Track wildlife info");
        trackWildlifeInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackWildlifeInfoButton.setVerticalTextPosition(JButton.BOTTOM);
        trackWildlifeInfoButton.setHorizontalTextPosition(JButton.CENTER);
        trackWildlifeInfoButton.setIconTextGap(1);
        trackWildlifeInfoButton.setForeground(Color.black);
        trackWildlifeInfoButton.setBackground(Color.PINK);
        trackWildlifeInfoButton.setVisible(true);
        trackWildlifeInfoButton.addActionListener(this);
        return trackWildlifeInfoButton;
    }

    @Override
    public JButton createBackToPreviousButton() {
        backToMainMenuButton = new JButton();
        ImageIcon backImage = new ImageIcon("./data/media/back to previous menu .jpg");
        backToMainMenuButton.setIcon(backImage);
        backToMainMenuButton.setBounds(401, 500, 200, 125);
        backToMainMenuButton.setText("Back to the main menu");
        backToMainMenuButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        backToMainMenuButton.setVerticalTextPosition(JButton.BOTTOM);
        backToMainMenuButton.setHorizontalTextPosition(JButton.CENTER);
        backToMainMenuButton.setIconTextGap(1);
        backToMainMenuButton.setForeground(Color.black);
        backToMainMenuButton.setBackground(Color.PINK);
        backToMainMenuButton.setVisible(true);
        backToMainMenuButton.addActionListener(this);
        return backToMainMenuButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(addWildlifeButton)) {

        }
        if (actionSource.equals(trackDonorInfoButton)) {

        }
        if (actionSource.equals(trackWildlifeInfoButton)) {

        }
        if (actionSource.equals(backToMainMenuButton)) {
            new LaunchFundTrackerAppGUI();

        }

    }

    @Override
    public JPanel createTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, 700, 140);
        titlePanel.setBackground(new Color(204, 255, 255));
        JLabel label1 = new JLabel();
        ImageIcon titleImage = new ImageIcon("./data/media/logoWildlifeConservatory250.jpg");
        String labelContent1 = "<html>Welcome to our wildlife conservatory! <BR><BR></html>";
        label1.setText(labelContent1);
        label1.setIcon(titleImage);
        label1.setHorizontalTextPosition(4);
        label1.setFont(new Font("Serif", 1, 33));
        label1.setVerticalAlignment(1);
        titlePanel.add(label1);
        return titlePanel;
    }
}
