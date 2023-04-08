package ui;

import model.Donation;
import model.Donor;
import formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Representing a TrackDonationRecords JPanel and the function to track the donation records fo the conservtory
public class TrackDonationsRecords extends JPanel implements ActionListener, Exitable, Resetable {

    private final LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JTextField userEntry;
    private JLabel donationRecordsLabel;
    private JButton trackDonationButton;

    //REQUIRES: mainFrame is not null
    //EFFECTS: creates an TrackDonationsRecords objects, sets the mainFrame, and adds the associated components
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

    //MODIFIES: this
    //EFFECTS: sets and returns the button for the user to track donations records of the chosen donor
    private JButton createTrackDonationsButton() {
        trackDonationButton = new JButton("Track donation records");
        trackDonationButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        trackDonationButton.setBounds(500, 76, 190, 40);
        trackDonationButton.addActionListener(this);
        return trackDonationButton;
    }

    //MODIFIES: this
    //EFFECTS: sets and returns the textfield for the user to enter the donor ID
    private JTextField createUserEntryTextField() {
        JLabel label = new JLabel();
        label.setBounds(100, 50, 380, 25);
        label.setText("Please enter your donor ID: ");
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        userEntry = new JTextField();
        userEntry.setSize(new Dimension(400, 20));
        userEntry.setBounds(100, 76, 400, 40);
        return userEntry;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(backToPreviousButton)) {
            this.setVisible(false);
            clearContents();
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

    //MODIFIES: this
    //EFFECTS: sets and returns the label to show the donation records of the chosen donor
    private JLabel createDonationRecordsLabel() {
        donationRecordsLabel = new JLabel();
        donationRecordsLabel.setBounds(100, 70, 500, 550);
        donationRecordsLabel.setFont(new Font("Comic Sans", Font.BOLD, 15));
        return donationRecordsLabel;
    }

    //REQUIRES: d is not null
    //MODIFIES: this
    //EFFECTS: update the text of donationRecordsLabel with the donor information of d
    private void refreshDonationRecordsLabel(Donor d) {
        String contents = "<html>The donations records for " + d.getDonorID() + " are listed below:";
        if (d.getRecordsOfDonations().size() == 0) {
            contents = d.getDonorID() + " has not make any donation yet!";
        } else {
            for (Donation donation : d.getRecordsOfDonations()) {
                String wlID = donation.getWildlifeID();
                double amt = donation.getAmount();
                String date = DateFormatter.toStringLocalDate(donation.getDateDonationMade());
                String msg = "<br>$" + amt + " was donated to " + wlID + " on " + date;
                System.out.println(msg);
                contents = contents + msg;
            }
            contents += "</html>";
        }
        donationRecordsLabel.setText(contents);
    }

    //EFFECTS: finds the donor based on the user entry in the donor ID textfield and returns it
    private Donor findDonor() {
        String donorID = userEntry.getText();
        Map<String, Donor> donorMap = new HashMap<>();
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

    @Override
    public void clearContents() {
        userEntry.setText("");
        donationRecordsLabel.setText("");
    }
}
