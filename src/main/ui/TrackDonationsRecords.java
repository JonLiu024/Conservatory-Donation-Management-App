package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackDonationsRecords extends JPanel implements ActionListener, CommonComponents {

    private LaunchFundTrackerAppGUI mainFrame;


    public TrackDonationsRecords(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;

    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public JButton createBackToPreviousButton() {
        return null;

    }
}
