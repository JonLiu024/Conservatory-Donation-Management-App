package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener, TitlePanel {

    private JPanel jPanelTitle;
    private JPanel jPanelAdmin;
    private JPanel jPanelDonation;
    private JButton jButtonAdmin;
    private JButton jButtonDonation;
    private JButton saveButton;
    private JButton loadButton;


    public MenuFrame() {
        this.setTitle("Wildlife Conservatory ");
        this.setSize(700, 840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(createTitlePanel());
        this.add(createAdminButton());
        this.add(createDonateButton());
        this.add(createSaveButton());
        this.add(createLoadButton());
        this.add(createAdminPanel());
        this.add(createDonatePanel());
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 255));
        Border border = BorderFactory.createLineBorder(new Color(255, 215, 0));

        this.setVisible(true);
    }


    public JPanel createAdminPanel() {
        JLabel label2 = new JLabel();
        jPanelAdmin = new JPanel();
        jPanelAdmin.setBounds(0, 141, 700, 350);
        jPanelAdmin.setBackground(new Color(204, 255, 255));
        ImageIcon adminImage = new ImageIcon("Admin.jpg");
        label2.setIcon(adminImage);
        jPanelAdmin.add(label2);
        return jPanelAdmin;
    }


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

    public JPanel createDonatePanel() {
        jPanelDonation = new JPanel();
        jPanelDonation.setBounds(0, 491, 700, 350);
        jPanelDonation.setBackground(new Color(204, 255, 255));
        JLabel label3 = new JLabel();
        ImageIcon donateImage = new ImageIcon("Donate.jpg");
        label3.setIcon(donateImage);
        jPanelDonation.add(label3);
        return jPanelDonation;
    }


    public JButton createAdminButton() {
        jButtonAdmin = new JButton();
        ImageIcon icon = new ImageIcon("for admin.jpg");
        jButtonAdmin.setBounds(276, 276, 150, 80);
        jButtonAdmin.setText("For Admin");
        jButtonAdmin.setFocusable(false);
        jButtonAdmin.setIcon(icon);
        jButtonAdmin.setHorizontalTextPosition(JButton.CENTER);
        jButtonAdmin.setVerticalTextPosition(JButton.BOTTOM);
        jButtonAdmin.setFont(new Font("Comic Sans", Font.BOLD, 20));
        jButtonAdmin.setIconTextGap(-1);
        jButtonAdmin.setForeground(Color.black);
        jButtonAdmin.setBackground(Color.lightGray);
        jButtonAdmin.addActionListener(this);
        return jButtonAdmin;
    }

    public JButton createDonateButton() {
        jButtonDonation = new JButton();
        ImageIcon icon = new ImageIcon("donate logo.png");
        jButtonDonation.setBounds(276, 626, 150, 80);
        jButtonDonation.setText("To Donate");
        jButtonDonation.setFocusable(false);
        jButtonDonation.setIcon(icon);
        jButtonDonation.setHorizontalTextPosition(JButton.CENTER);
        jButtonDonation.setVerticalTextPosition(JButton.BOTTOM);
        jButtonDonation.setFont(new Font("Comic Sans", Font.BOLD, 20));
        jButtonDonation.setIconTextGap(-1);
        jButtonDonation.setForeground(Color.black);
        jButtonDonation.setBackground(Color.lightGray);
        jButtonDonation.setVisible(true);
        jButtonDonation.addActionListener(this);
        return jButtonDonation;
    }

    public JButton createSaveButton() {
        saveButton = new JButton();
        saveButton.setBounds(500, 770, 100, 80);
        saveButton.setText("Save to file");
        saveButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        saveButton.setVerticalTextPosition(JButton.CENTER);
        saveButton.setHorizontalTextPosition(JButton.CENTER);
        saveButton.setForeground(Color.black);
        saveButton.setBackground(Color.lightGray);
        saveButton.setVisible(true);
        saveButton.addActionListener(this);
        return saveButton;
    }

    public JButton createLoadButton() {
        loadButton = new JButton();
        loadButton.setBounds(600, 770, 100, 80);
        loadButton.setText("Load from file");
        loadButton.setFocusable(false);
        loadButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        loadButton.setHorizontalTextPosition(JButton.CENTER);
        loadButton.setVerticalTextPosition(JButton.CENTER);
        loadButton.setForeground(Color.black);
        loadButton.setBackground(Color.lightGray);
        loadButton.addActionListener(this);
        loadButton.setVisible(true);
        return loadButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(jButtonAdmin)) {
            new ForAdmin();
        }
        if (actionSource.equals(jButtonDonation)) {
            System.out.println("d");
        }
        if (actionSource.equals(saveButton)) {
            System.out.println('s');
        }
        if (actionSource.equals(loadButton)) {
            System.out.println("l");
        }

    }
}
