package ui;

import model.Donation;
import model.Donor;
import model.formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackDonationsRecords extends JPanel implements ActionListener, Exitable {

    private LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JTextField userEntry;
    private JLabel donationRecordsLabel;
    private JButton trackDonationButton;


    public TrackDonationsRecords(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.add(createTrackDonationsButton());
        this.add(createGoBackButton());
        this.add(createUserEntryTextField());
        this.add(createDonationRecordsLabel());
        this.setLayout(null);
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }

    private JButton createTrackDonationsButton() {
        trackDonationButton = new JButton("Track donation records");
        trackDonationButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        trackDonationButton.setBounds(500, 76, 190, 40);
        trackDonationButton.addActionListener(this);
        return trackDonationButton;
    }


    private JTextField createUserEntryTextField() {
        JLabel label = new JLabel();
        label.setBounds(100, 50, 380, 25);
        label.setText("Please enter your donor ID: ");
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        userEntry = new JTextField();
        userEntry.setSize(new Dimension(400, 20));
        userEntry.setBounds(95, 76, 400, 40);
        return userEntry;
    }

    private void clearTextField() {
        userEntry.setText("");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(backToPreviousButton)) {
            this.setVisible(false);
            clearTextField();
            mainFrame.getToDonate().setVisible(true);
        }
        if (actionSource.equals(trackDonationButton)) {
            try {
                refreshDonationRecordsLabel(findDonor());
            } catch (NullPointerException exception) {
                String msg = "Oops, the donor ID you entered does not exists!";
                JOptionPane.showMessageDialog(null, msg, "", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    private JLabel createDonationRecordsLabel() {
        donationRecordsLabel = new JLabel();
        donationRecordsLabel.setBounds(100, 70, 450, 550);
        donationRecordsLabel.setFont(new Font("Comic Sans", Font.BOLD, 18));
        return donationRecordsLabel;
    }


    private void refreshDonationRecordsLabel(Donor d) {
        String contents = "<html>";
        for (Donation donation : d.getRecordsOfDonations()) {
            String wlID = donation.getWildlifeID();
            double amt = donation.getAmount();
            String date = DateFormatter.toStringLocalDate(donation.getDateDonationMade());
            String msg = "<br>$" + amt + " was donated to " + wlID + " on " + date;
            contents = contents + msg;
        }
        contents += "</html>";
        System.out.println(contents);
        donationRecordsLabel.setText(contents);
    }


    private Donor findDonor() {
        String donorID = userEntry.getText();
        Map<String, Donor> donorMap = new HashMap<>();
        List<String> donorIDList = mainFrame.getCs().getDonorIDList();
        List<Donor> donorList = mainFrame.getCs().getListOfDonors();
        for (Donor d : donorList) {
            donorMap.put(d.getDonorID(), d);
        }
        return donorMap.get(donorID);
    }

    @Override
    public JButton createGoBackButton() {
        backToPreviousButton = new JButton("Go back");
        backToPreviousButton.setBounds(590, 611, 100, 40);
        backToPreviousButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        backToPreviousButton.setForeground(Color.black);
        backToPreviousButton.setBackground(Color.PINK);
        backToPreviousButton.setVisible(true);
        backToPreviousButton.addActionListener(this);
        return backToPreviousButton;
    }
}
