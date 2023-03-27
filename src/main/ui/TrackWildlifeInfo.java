package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackWildlifeInfo extends JPanel implements ActionListener, CommonComponents {
    private LaunchFundTrackerAppGUI mainFrame;

    public TrackWildlifeInfo(LaunchFundTrackerAppGUI mainFrame) {
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
