package ui;

import model.ConservationSite;
import model.Wildlife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
        this.add(createWildlifeOptions());
        this.add(createSubmissionButton());
        this.add(selectDonationAmount());
        if (mainFrame.getCs().getListOfWildlifeNotFullyFunded().size() == 0) {
            this.add(getAlertUserNoWildlife());
        }
        this.add(createBackToPreviousButton());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }

    private JLabel getAlertUserNoWildlife() {
        alertUserNoWildlife = new JLabel();
        alertUserNoWildlife.setText("Currently, no wildlife is accepting donations! ");
        alertUserNoWildlife.setFont(new Font("Comic Sans", Font.BOLD, 18));
        alertUserNoWildlife.setBounds(50, 161, 700, 25);
        return alertUserNoWildlife;
    }


    private JComboBox createWildlifeOptions() {
        JLabel label = new JLabel("Please select a wildlife to donate");
        label.setBounds(100,200, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        int numbOfWl = this.mainFrame.getCs().getListOfWildlifeNotFullyFunded().size();
        String[] wildlifeList = new String[numbOfWl];
        for (int i = 0; i < numbOfWl; i++) {
            wildlifeList[i] = mainFrame.getCs().getListOfWildlifeNotFullyFunded().get(i).getWildlifeID();
        }
        wildlifeOptions = new JComboBox(wildlifeList);
        wildlifeOptions.setBounds(100, 226, 400, 25);
        return wildlifeOptions;
    }

    public void refreshWildlifeList() {
        int numbOfWl = this.mainFrame.getCs().getListOfWildlifeNotFullyFunded().size();
        String[] wildlifeList = new String[numbOfWl];
        for (int i = 0; i < numbOfWl; i++) {
            wildlifeList[i] = mainFrame.getCs().getListOfWildlifeNotFullyFunded().get(i).getWildlifeID();
        }
        wildlifeOptions = new JComboBox(wildlifeList);
        wildlifeOptions.setBounds(100, 226, 400, 25);
        this.add(wildlifeOptions);
    }


    private JComboBox selectDonationAmount() {
        JLabel label = new JLabel("Please select the amount of your donation");
        label.setBounds(100,271, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        String[] donationOptions = {"$50", "$100", "$200", "$350", "$500"};
        donationAmount = new JComboBox(donationOptions);
        donationAmount.setBounds(100, 296, 400, 25);
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
            String msg = "Do you have an account with us?";
            int reply = JOptionPane.showConfirmDialog(null, msg, "Donor profile",
                    JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {

                mainFrame.getCp().setVisible(true);

            } else {
                donate();

            }
        }

        if (source.equals(backToPreviousButton)) {
            this.setVisible(false);
            mainFrame.getToDonate().setVisible(true);
        }

    }

    private void donate() {
        double amount = Double.parseDouble(selectDonationAmount().getSelectedItem().toString());


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
