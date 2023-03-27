package ui;

import model.Donor;
import model.Wildlife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class MakeDonations extends JPanel implements ActionListener, CommonComponents {


    private JComboBox wildlifeOptions;
    private JComboBox donationAmount;
    private JLabel alertUserNoWildlife;
    private LaunchFundTrackerAppGUI mainFrame;
    private JButton submissionButton;
    private JButton backToPreviousButton;


    public MakeDonations(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createSubmissionButton());
        this.add(selectDonationAmount());
        this.add(createBackToPreviousButton());
        this.add(createWildlifeOptions());
        this.add(getAlertUserNoWildlife());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }


    private JLabel getAlertUserNoWildlife() {
        alertUserNoWildlife = new JLabel();
        alertUserNoWildlife.setText("Currently, no wildlife is accepting donations! ");
        alertUserNoWildlife.setFont(new Font("Comic Sans", Font.BOLD, 18));
        alertUserNoWildlife.setBounds(126, 120, 500, 25);
        return alertUserNoWildlife;
    }


    private JComboBox createWildlifeOptions() {
        JLabel label = new JLabel("Please select a wildlife to donate");
        label.setBounds(150, 200, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        wildlifeOptions = new JComboBox(new String[0]);
        wildlifeOptions.setBounds(150, 226, 400, 25);
        return wildlifeOptions;
    }


    //Reference: https://stackoverflow.com/questions/4620295/dynamically-change-jcombobox
    public void refreshWildlifeOptions() {
        int numbOfWl = mainFrame.getCs().getListOfWildlifeNotFullyFunded().size();
        String[] wildlifeList = new String[numbOfWl];
        for (int i = 0; i < numbOfWl; i++) {
            String option = mainFrame.getCs().getListOfWildlifeNotFullyFunded().get(i).getWildlifeID() + ":"
                    + mainFrame.getCs().getListOfWildlifeNotFullyFunded().get(i).getSpeciesName();
            wildlifeList[i] = option;
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(wildlifeList);
        wildlifeOptions.removeAllItems();
        wildlifeOptions.setModel(model);
        if (numbOfWl == 0) {
            alertUserNoWildlife.setVisible(true);
        } else {
            alertUserNoWildlife.setVisible(false);
        }
    }


    private JComboBox selectDonationAmount() {
        JLabel label = new JLabel("Please select the amount of your donation");
        label.setBounds(150, 271, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        String[] donationOptions = {"$50", "$100", "$200", "$350", "$500"};
        donationAmount = new JComboBox(donationOptions);
        donationAmount.setBounds(150, 296, 400, 25);
        return donationAmount;
    }


    private JButton createSubmissionButton() {
        submissionButton = new JButton("Submit");
        submissionButton.setBounds(490, 611, 100, 40);
        submissionButton.setFocusable(false);
        submissionButton.setFont(new Font("Comic Sans", 1, 12));
        submissionButton.setBackground(Color.pink);
        submissionButton.addActionListener(this);
        return submissionButton;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.equals(submissionButton)) {
            donate();
        }
        if (source.equals(backToPreviousButton)) {
            this.setVisible(false);
            mainFrame.getToDonate().setVisible(true);
        }
    }


    public void donate() {
        String msg = "Do you have a donor ID with us?";
        int reply = JOptionPane.showConfirmDialog(null, msg, "Donor profile",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            mainFrame.getCp().setVisible(true);
            this.setVisible(false);
        } else {
            double userIntendedAmt = optionToAmt(selectDonationAmount().getSelectedItem().toString());
            Wildlife wl = optionToWL(wildlifeOptions.getSelectedItem().toString());
            Donor donor = getDonor();
            if (donor.makeDonation(wl, userIntendedAmt) < userIntendedAmt) {
                mainFrame.getCs().moveWildlifeToFullyFundedList(wl);
            }
            mainFrame.getCs().addDonor(donor);
            mainFrame.getCs().updateRaisedFunding();
        }
    }


    private Wildlife optionToWL(String str) {
        String wildlifeID = str.substring(0, 6);
        for (Wildlife wl : mainFrame.getCs().getListOfWildlifeNotFullyFunded()) {
            if (wildlifeID.equals(wl.getWildlifeID())) {
                return wl;
            }
        }
        return null;
    }

    private double optionToAmt(String str) {
        String amt = str.substring(1, str.length());
        return Double.parseDouble(amt);
    }


    private Donor getDonor() {
        String userID = JOptionPane.showInputDialog("Please enter your donor ID: ");
        while (!mainFrame.getCs().getDonorIDList().contains(userID)) {
            userID = JOptionPane.showInputDialog("Please enter your donor ID: ");
        }
        for (Donor d : mainFrame.getCs().getListOfDonors()) {
            if (userID.equals(d.getDonorID())) {
                return d;
            }
        }
        return null;
    }


    @Override
    public JButton createBackToPreviousButton() {
        backToPreviousButton = new JButton("Go back");
        backToPreviousButton.setBounds(590, 611, 100, 40);
        backToPreviousButton.setFont(new Font("Comic Sans", 1, 12));
        backToPreviousButton.setForeground(Color.black);
        backToPreviousButton.setBackground(Color.PINK);
        backToPreviousButton.setVisible(true);
        backToPreviousButton.addActionListener(this);
        return backToPreviousButton;
    }
}
