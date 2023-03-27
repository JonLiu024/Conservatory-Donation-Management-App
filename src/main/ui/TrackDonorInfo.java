package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackDonorInfo extends JPanel implements CommonComponents, ActionListener {
    private LaunchFundTrackerAppGUI mainFrame;
    private JButton backToPreviousButton;

    public TrackDonorInfo(LaunchFundTrackerAppGUI mainFrame) {

        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createBackToPreviousButton());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(backToPreviousButton)) {
            this.setVisible(false);
            mainFrame.getForAdmin().setVisible(true);
        }

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
