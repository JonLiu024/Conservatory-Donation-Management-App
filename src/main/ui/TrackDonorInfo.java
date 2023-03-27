package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackDonorInfo extends JPanel implements CommonComponents, ActionListener {
    private LaunchFundTrackerAppGUI mainFrame;

    public TrackDonorInfo(LaunchFundTrackerAppGUI mainFrame) {
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
