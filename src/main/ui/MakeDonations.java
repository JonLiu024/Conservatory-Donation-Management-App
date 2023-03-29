package ui;

import model.Donor;
import model.Wildlife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeDonations extends JPanel implements ActionListener, Exitable, Updatable {


    private JComboBox wildlifeOptions;
    private JComboBox donationAmount;
    private JLabel alertUserNoWildlife;
    private LaunchFundTrackerAppGUI mainFrame;
    private JButton submissionButton;
    private JButton goBackButton;


    public MakeDonations(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createSubmissionButton());
        this.add(selectDonationAmount());
        this.add(createGoBackButton());
        this.add(createWildlifeOptions());
        this.add(createAlertUserNoWildlife());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }


    private JLabel createAlertUserNoWildlife() {
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
    @Override
    public void refreshComboBoxOptions() {
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
        submissionButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        submissionButton.setBackground(Color.pink);
        submissionButton.addActionListener(this);
        return submissionButton;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source.equals(submissionButton)) {
            donate();
            this.setVisible(false);
        }
        if (source.equals(goBackButton)) {
            this.setVisible(false);
            mainFrame.getToDonate().setVisible(true);
        }
    }


    public void donate() {
        String msg = "Do you have a donor ID with us?";
        int reply = JOptionPane.showConfirmDialog(null, msg, "Donor profile",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            this.setVisible(false);
            mainFrame.getCp().setVisible(true);
        } else {
            double userIntendedAmt = optionToAmt(donationAmount.getSelectedItem().toString());
            Wildlife wl = optionToWL(wildlifeOptions.getSelectedItem().toString());
            Donor donor = getDonor();
            double amount = donor.makeDonation(wl, userIntendedAmt);
            if (wl.getAmountFunded() == wl.getTargetFunding()) {
                mainFrame.getCs().moveWildlifeToFullyFundedList(wl);
            }
            mainFrame.getCs().addDonor(donor);
            mainFrame.getCs().updateRaisedFunding();
            mainFrame.getToDonate().setVisible(true);
            String message = donor.getDonorID() + " has successfully donated " + "$" + amount
                    + " to " + wl.getWildlifeID();
            JOptionPane.showMessageDialog(null, message, "Donation Confirmation", JOptionPane.INFORMATION_MESSAGE);
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
        String amt = str.substring(1);
        return Double.parseDouble(amt);
    }


    private Donor getDonor() {
        String userID = JOptionPane.showInputDialog("Please enter your donor ID: ");
        while (!mainFrame.getCs().getDonorIDList().contains(userID)) {
            String msg = "It seems that the ID you entered does not exist, "
                    + "Please enter an existing donor ID: ";
            userID = JOptionPane.showInputDialog(msg, JOptionPane.WARNING_MESSAGE);
        }
        for (Donor d : mainFrame.getCs().getListOfDonors()) {
            if (userID.equals(d.getDonorID())) {
                return d;
            }
        }
        return null;
    }


    @Override
    public JButton createGoBackButton() {
        goBackButton = new JButton("Go back");
        goBackButton.setBounds(590, 611, 100, 40);
        goBackButton.setFont(new Font("Comic Sans", Font.BOLD, 12));
        goBackButton.setForeground(Color.black);
        goBackButton.setBackground(Color.PINK);
        goBackButton.setVisible(true);
        goBackButton.addActionListener(this);
        return goBackButton;
    }
}
