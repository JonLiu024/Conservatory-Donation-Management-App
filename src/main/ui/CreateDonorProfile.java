package ui;

import exception.ExistingDonorIDException;
import model.Donor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateDonorProfile extends JPanel implements ActionListener, FormatChecker {
    private LaunchFundTrackerAppGUI mainFrame;
    private JTextField textFieldDonorID;
    private JTextField textFieldEmail;
    private JButton submissionButton;


    public CreateDonorProfile(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createDonorIDTextField());
        this.add(createEmailTextField());
        this.add(createSubmissionButton());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);
    }


    public JButton createSubmissionButton() {
        submissionButton = new JButton("Submit");
        submissionButton.setBounds(490, 610, 100, 40);
        submissionButton.setFocusable(false);
        submissionButton.setFont(new Font("Comic Sans", 1, 12));
        submissionButton.setBackground(Color.pink);
        submissionButton.addActionListener(this);
        return submissionButton;
    }


    public JTextField createDonorIDTextField() {
        JLabel label = new JLabel("Please create a donor ID: ");
        label.setBounds(125,125, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        textFieldDonorID = new JTextField();
        textFieldDonorID.setPreferredSize(new Dimension(300, 30));
        textFieldDonorID.setBounds(125, 150, 400, 40);
        return textFieldDonorID;
    }


    public JTextField createEmailTextField() {
        JLabel label = new JLabel("Please enter your email: ");
        label.setBounds(125,325, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 15));
        this.add(label);
        textFieldEmail = new JTextField();
        textFieldEmail.setPreferredSize(new Dimension(300, 30));
        textFieldEmail.setBounds(125, 350, 400, 40);
        return textFieldEmail;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(submissionButton)) {
            try {
                String donorID = textFieldDonorID.getText();
                String email = textFieldEmail.getText().toLowerCase();
                if (mainFrame.getCs().getDonorIDList().contains(donorID)) {
                    throw new ExistingDonorIDException("The donor ID existed, please create another one: ");
                }
                formatChecker(email);
                Donor donor = new Donor(donorID, email);
                mainFrame.getCs().addDonor(donor);
                clearTextField();
                this.setVisible(false);
                mainFrame.getMd().setVisible(true);
            } catch (ExistingDonorIDException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "", JOptionPane.WARNING_MESSAGE);
            } catch (InputMismatchException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void clearTextField() {
        this.textFieldEmail.setText("");
        this.textFieldDonorID.setText("");
    }

    @Override
    public void formatChecker(String str) throws InputMismatchException {
        Pattern pattern = Pattern.compile("[0-9a-z]+(\\.[0-9a-z]+)*@[a-z0-9]+\\.(com|ca)");;
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            throw new InputMismatchException("Please enter your email address in valid format: ");
        }

    }
}
