package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDonate extends JFrame implements CommonComponents, ActionListener {

    private JButton makeDonationsButton;
    private JButton trackDonationsRecordsButton;
    private JButton viewWLInfoButton;
    private JButton backToMainMenuButton;
    private JPanel titlePanel;



    public ToDonate() {
        this.setTitle("Menu for Donors");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(createTitlePanel());
        this.add(createMakeDonationButton());
        this.add(createTrackDonationsButton());
        this.add(createViewWLInfoButton());
        this.add(createBackToPreviousButton());
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        Border border = BorderFactory.createLineBorder(new Color(255, 215, 0));
        this.setVisible(true);

    }


    public JButton createMakeDonationButton() {
        makeDonationsButton = new JButton();
        ImageIcon donationsImage = new ImageIcon("./data/media/make donation.jpg");
        makeDonationsButton.setIcon(donationsImage);
        makeDonationsButton.setBounds(101, 251, 200, 125);
        makeDonationsButton.setText("Make a donation");
        makeDonationsButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        makeDonationsButton.setVerticalTextPosition(JButton.BOTTOM);
        makeDonationsButton.setHorizontalTextPosition(JButton.CENTER);
        makeDonationsButton.setIconTextGap(1);;
        makeDonationsButton.setForeground(Color.black);
        makeDonationsButton.setBackground(Color.PINK);
        makeDonationsButton.setVisible(true);
        makeDonationsButton.addActionListener(this);
        return makeDonationsButton;
    }


    public JButton createTrackDonationsButton() {
        trackDonationsRecordsButton = new JButton();
        ImageIcon addTrackDonorImage = new ImageIcon("./data/media/donor info2 .jpg");
        trackDonationsRecordsButton.setIcon(addTrackDonorImage);
        trackDonationsRecordsButton.setBounds(401, 251, 200, 125);
        trackDonationsRecordsButton.setText("Track donation records");
        trackDonationsRecordsButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackDonationsRecordsButton.setVerticalTextPosition(JButton.BOTTOM);
        trackDonationsRecordsButton.setHorizontalTextPosition(JButton.CENTER);
        trackDonationsRecordsButton.setIconTextGap(1);;
        trackDonationsRecordsButton.setForeground(Color.black);
        trackDonationsRecordsButton.setBackground(Color.PINK);
        trackDonationsRecordsButton.setVisible(true);
        trackDonationsRecordsButton.addActionListener(this);
        return trackDonationsRecordsButton;
    }


    public JButton createViewWLInfoButton() {
        viewWLInfoButton = new JButton();
        ImageIcon trackWLInfoImage = new ImageIcon("./data/media/wildlife info.jpg");
        viewWLInfoButton.setIcon(trackWLInfoImage);
        viewWLInfoButton.setBounds(101, 500, 200, 125);
        viewWLInfoButton.setText("Track wildlife info");
        viewWLInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        viewWLInfoButton.setVerticalTextPosition(JButton.BOTTOM);
        viewWLInfoButton.setHorizontalTextPosition(JButton.CENTER);
        viewWLInfoButton.setIconTextGap(1);
        viewWLInfoButton.setForeground(Color.black);
        viewWLInfoButton.setBackground(Color.PINK);
        viewWLInfoButton.setVisible(true);
        viewWLInfoButton.addActionListener(this);
        return viewWLInfoButton;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(makeDonationsButton)) {

        }
        if (actionSource.equals(trackDonationsRecordsButton)) {

        }
        if (actionSource.equals(viewWLInfoButton)) {

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
}
