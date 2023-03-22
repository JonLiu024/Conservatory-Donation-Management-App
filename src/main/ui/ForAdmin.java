package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForAdmin extends JFrame implements ActionListener, TitlePanel{

    private JButton addWildlifeButton;
    private JButton trackDonorInfoButton;
    private JButton trackWildlifeInfoButton;
    private JButton backToMainMenuButton;
    private JPanel jPanelTitle;



    public ForAdmin() {
        this.setTitle("Administrating your conservatory");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(createTitlePanel());
        this.add(createAddWildlifeButton());
        this.add(createTrackDonorInfoButton());
        this.add(createTrackWLInfoButton());
        this.add(createBackToMenuButton());
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        Border border = BorderFactory.createLineBorder(new Color(255, 215, 0));
        this.setVisible(true);

    }


    public JButton createAddWildlifeButton() {
        addWildlifeButton = new JButton();
        addWildlifeButton.setBounds(151, 291, 150, 125);
        addWildlifeButton.setText("Add a wildlife to Conservatory");
        addWildlifeButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        addWildlifeButton.setVerticalTextPosition(JButton.CENTER);
        addWildlifeButton.setHorizontalTextPosition(JButton.CENTER);
        addWildlifeButton.setForeground(Color.black);
        addWildlifeButton.setBackground(Color.pink);
        addWildlifeButton.setVisible(true);
        addWildlifeButton.addActionListener(this);
        return addWildlifeButton;
    }

    public JButton createTrackDonorInfoButton() {
        trackDonorInfoButton = new JButton();
        trackDonorInfoButton.setBounds(401, 291, 150, 125);
        trackDonorInfoButton.setText("Track donor information");
        trackDonorInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackDonorInfoButton.setVerticalTextPosition(JButton.CENTER);
        trackDonorInfoButton.setHorizontalTextPosition(JButton.CENTER);
        trackDonorInfoButton.setForeground(Color.black);
        trackDonorInfoButton.setBackground(Color.pink);
        trackDonorInfoButton.setVisible(true);
        trackDonorInfoButton.addActionListener(this);
        return trackDonorInfoButton;
    }


    public JButton createTrackWLInfoButton() {
        trackWildlifeInfoButton = new JButton();
        trackWildlifeInfoButton.setBounds(151, 416, 150, 125);
        trackWildlifeInfoButton.setText("Track wildlife information");
        trackWildlifeInfoButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        trackWildlifeInfoButton.setVerticalTextPosition(JButton.CENTER);
        trackWildlifeInfoButton.setHorizontalTextPosition(JButton.CENTER);
        trackWildlifeInfoButton.setForeground(Color.black);
        trackWildlifeInfoButton.setBackground(Color.pink);
        trackWildlifeInfoButton.setVisible(true);
        trackWildlifeInfoButton.addActionListener(this);
        return trackWildlifeInfoButton;
    }

    public JButton createBackToMenuButton() {
        backToMainMenuButton = new JButton();
        backToMainMenuButton.setBounds(401, 416, 150, 125);
        backToMainMenuButton.setText("Back to the main menu");
        backToMainMenuButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        backToMainMenuButton.setVerticalTextPosition(JButton.CENTER);
        backToMainMenuButton.setHorizontalTextPosition(JButton.CENTER);
        backToMainMenuButton.setForeground(Color.black);
        backToMainMenuButton.setBackground(Color.pink);
        backToMainMenuButton.setVisible(true);
        backToMainMenuButton.addActionListener(this);
        return backToMainMenuButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(addWildlifeButton)) {

        }
        if (actionSource.equals(trackDonorInfoButton)) {

        }
        if (actionSource.equals(trackWildlifeInfoButton)) {

        }
        if (actionSource.equals(backToMainMenuButton)) {

        }

    }

    @Override
    public JPanel createTitlePanel() {
        jPanelTitle = new JPanel();
        jPanelTitle.setBounds(0, 0, 700, 140);
        jPanelTitle.setBackground(new Color(204, 255, 255));
        JLabel label1 = new JLabel();
        ImageIcon titleImage = new ImageIcon("logoWildlifeConservatory250.jpg");
        String labelContent1 = "<html>Welcome to our wildlife conservatory! <BR><BR></html>";
        label1.setText(labelContent1);
        label1.setIcon(titleImage);
        label1.setHorizontalTextPosition(4);
        label1.setFont(new Font("Serif", 1, 33));
        label1.setVerticalAlignment(1);
        jPanelTitle.add(label1);
        return jPanelTitle;
    }
}
