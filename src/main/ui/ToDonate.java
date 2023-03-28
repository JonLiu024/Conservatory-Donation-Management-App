package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDonate extends JPanel implements ActionListener, Exitable {

    private JButton makeDonationsButton;
    private JButton trackDonationsRecordsButton;
    private JButton viewWLInfoButton;
    private JButton GoBackButton;
    private LaunchFundTrackerAppGUI mainFrame;




    public ToDonate(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setSize(700, 840);
        this.add(createMakeDonationButton());
        this.add(createTrackDonationsButton());
        this.add(createViewWLInfoButton());
        this.add(createGoBackButton());
        this.setLayout(null);
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
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
        makeDonationsButton.setIconTextGap(1);
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
        trackDonationsRecordsButton.setIconTextGap(1);
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

    private void setMainMenuInvisible() {
        mainFrame.getLoadButton().setVisible(true);
        mainFrame.getDonationButton().setVisible(true);
        mainFrame.getSaveButton().setVisible(true);
        mainFrame.getAdminButton().setVisible(true);
        mainFrame.getBackgroundLabel().setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(makeDonationsButton)) {
            this.setVisible(false);
            mainFrame.getMd().refreshComboBoxOptions();
            mainFrame.getMd().setVisible(true);
        }
        if (actionSource.equals(trackDonationsRecordsButton)) {
            this.setVisible(false);
            mainFrame.getTrackDonationsRecords().setVisible(true);
        }
        if (actionSource.equals(viewWLInfoButton)) {
            this.setVisible(false);
            mainFrame.getTwlInfo().refreshComboBoxOptions();
            mainFrame.getTwlInfo().setVisible(true);
        }
        if (actionSource.equals(GoBackButton)) {
            this.setVisible(false);
            setMainMenuInvisible();
        }

    }




    @Override
    public JButton createGoBackButton() {
        GoBackButton = new JButton();
        ImageIcon backImage = new ImageIcon("./data/media/back to previous menu .jpg");
        GoBackButton.setIcon(backImage);
        GoBackButton.setBounds(401, 500, 200, 125);
        GoBackButton.setText("Back to the main menu");
        GoBackButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        GoBackButton.setVerticalTextPosition(JButton.BOTTOM);
        GoBackButton.setHorizontalTextPosition(JButton.CENTER);
        GoBackButton.setIconTextGap(1);
        GoBackButton.setForeground(Color.black);
        GoBackButton.setBackground(Color.PINK);
        GoBackButton.setVisible(true);
        GoBackButton.addActionListener(this);
        return GoBackButton;
    }
}
