package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackDonorInfo extends JPanel implements CommonComponents, ActionListener {
    private LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;
    private JButton trackButton;
    private JComboBox donorList;

    public TrackDonorInfo(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createBackToPreviousButton());
        this.add(createTrackButton());
        this.add(createDonorOptions());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(trackButton)) {
            displayDonorInfo();
        }
        if (actionSource.equals(backToPreviousButton)) {
            this.setVisible(false);
            mainFrame.getForAdmin().setVisible(true);
        }
    }

    private void displayDonorInfo() {

    }


    public JComboBox createDonorOptions() {
        JLabel label = new JLabel("Please select the donor profiles for view: ");
        label.setBounds(95,100, 500, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 20));
        this.add(label);
        String[] list = new String[0];
        donorList = new JComboBox(list);
        donorList.setBounds(90, 130, 435, 40);
        return donorList;
    }


    public void refreshDonorOptions() {
        int numOfDonors = mainFrame.getCs().getDonorIDList().size();
        String[] list = new String[numOfDonors];
        for (int i = 0; i < numOfDonors; i++) {
            String option = mainFrame.getCs().getDonorIDList().get(i);
            list[i] = option;
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(list);
        donorList.removeAllItems();
        donorList.setModel(model);
    }

    public JButton createTrackButton() {
        trackButton = new JButton("Track wildlife");
        trackButton.setBounds(525, 130, 150, 40);
        return trackButton;
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
