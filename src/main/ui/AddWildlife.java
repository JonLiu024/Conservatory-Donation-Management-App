package ui;

import model.ConservationStatus;
import model.Wildlife;
import model.formatters.DateFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddWildlife extends JPanel implements ActionListener, CommonComponents, FormatChecker {

    private JComboBox conservationStatusOption;
    private JTextField textFieldSpeciesName;
    private JTextField textFieldTargetFunding;
    private JTextField textFieldAdmissionDate;
    private JTextArea textDescription;
    private JButton submissionButton;
    private JButton backToPreviousButton;
    private Icon image;
    private LaunchFundTrackerAppGUI mainFrame;
    private String dialogMsg;



    public AddWildlife(LaunchFundTrackerAppGUI mainFrame) {
        this.mainFrame = mainFrame;
        dialogMsg = "Wildlife added successfully";
        this.setBounds(0, 141, 700, 700);
        this.setLayout(null);
        this.add(createSpeciesNameTextField());
        this.add(generateConservationStatusOptions());
        this.add(createTFTextField());
        this.add(createAdmissionDate());
        this.add(createDescription());
        this.add(createSubmissionButton());
        this.add(createBackToPreviousButton());
        this.setBackground(new Color(204, 255, 255));
        this.setVisible(false);

    }



    public JTextField createSpeciesNameTextField() {
        JLabel label = new JLabel("Species Name");
        label.setBounds(125,30, 400, 20);
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        textFieldSpeciesName = new JTextField();
        textFieldSpeciesName.setPreferredSize(new Dimension(300, 30));
        textFieldSpeciesName.setBounds(125, 50, 400, 40);
        return textFieldSpeciesName;

    }


    public JTextField createTFTextField() {
        JLabel label = new JLabel("Target Funding");
        label.setBounds(125,125, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        textFieldTargetFunding = new JTextField();
        textFieldTargetFunding.setPreferredSize(new Dimension(300, 30));
        textFieldTargetFunding.setBounds(125, 150, 400, 40);
        return textFieldTargetFunding;
    }

    public JComboBox generateConservationStatusOptions() {
        JLabel label = new JLabel("Please select the conservation status");
        label.setBounds(125,225, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        String[] conservationStata = {"Least Concerned", "Conservation Dependent", "Near Threatened",
                "Vulnerable", "Endangered", "Critically Endangered", "Extinct in the Wild"};
        conservationStatusOption = new JComboBox(conservationStata);
        conservationStatusOption.setBounds(125, 250, 400, 40);
        return conservationStatusOption;
    }


    public JTextField createAdmissionDate() {
        JLabel label = new JLabel("Admission Date (YYYY-MM-dd)");
        label.setBounds(125,325, 400, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        textFieldAdmissionDate = new JTextField();
        textFieldAdmissionDate.setPreferredSize(new Dimension(300, 30));
        textFieldAdmissionDate.setBounds(125, 350, 400, 40);
        return textFieldAdmissionDate;
    }

    public JTextArea createDescription() {
        JLabel label = new JLabel("Description (Enter \"none\" for no description)");
        label.setBounds(125,425, 600, 25);
        label.setFont(new Font("Comic Sans", Font.BOLD, 18));
        this.add(label);
        textDescription = new JTextArea();
        textDescription.setPreferredSize(new Dimension(300, 150));
        textDescription.setBounds(125, 450, 400, 150);
        return textDescription;
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

//    private void customOptionPane() {
//        image = new ImageIcon("./data/media/AddWildlifeSuccessful.jpg");
//
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource.equals(submissionButton)) {
            try {
                addWildlifeToCs();
                JOptionPane.showMessageDialog(null, dialogMsg, "", JOptionPane.INFORMATION_MESSAGE, image);
                this.setVisible(false);
                clearTextField();
                mainFrame.getForAdmin().setVisible(true);
            } catch (NullPointerException exception) {
                String msg = "Please fill in all the required information sections ";
                JOptionPane.showMessageDialog(null, msg, "", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException exception) {
                String msg = "Please enter a valid number for the target funding!";
                JOptionPane.showMessageDialog(null, msg, "", JOptionPane.WARNING_MESSAGE);
            } catch (InputMismatchException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (actionSource.equals(backToPreviousButton)) {
            this.setVisible(false);
            mainFrame.getForAdmin().setVisible(true);
        }
    }


    private void addWildlifeToCs() throws NullPointerException, NumberFormatException,InputMismatchException {
        String speciesName = textFieldSpeciesName.getText();
        if (speciesName.equals("")) {
            throw new NullPointerException();
        }
        ConservationStatus conservationStatus = stringToCS(conservationStatusOption.getSelectedItem().toString());
        double targetFunding = Double.parseDouble(textFieldTargetFunding.getText());
        formatChecker(textFieldAdmissionDate.getText());
        LocalDate admissionDate = DateFormatter.strToLocalDate(textFieldAdmissionDate.getText());
        Wildlife newWildlife = new Wildlife(speciesName, targetFunding, conservationStatus, admissionDate);
        newWildlife.setDescription(textDescription.getText());
        mainFrame.getCs().addWildlife(newWildlife);
    }

    @Override
    public void formatChecker(String str) throws InputMismatchException {
        Pattern pattern = Pattern.compile("20[0-9]{2}-((0[^0^2]|1[0-2])-([0-2][0-9]|3[0-1]))|(02-([0-2][0-9]))");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            throw new InputMismatchException("Please enter the admission date in required format");
        }
    }

    private void clearTextField() {
        textDescription.setText("");
        textFieldSpeciesName.setText("");
        textFieldAdmissionDate.setText("");
        textFieldTargetFunding.setText("");

    }

    public ConservationStatus stringToCS(String cs) {
        switch (cs) {
            case "Least Concerned":
                return ConservationStatus.LC;
            case "Conservation Dependent":
                return ConservationStatus.CD;
            case "Near Threatened":
                return ConservationStatus.NT;
            case "Vulnerable":
                return ConservationStatus.VU;
            case "Endangered":
                return ConservationStatus.EN;
            case "Critically Endangered":
                return ConservationStatus.CR;
            default:
                return ConservationStatus.EW;
        }
    }

    @Override
    public JButton createBackToPreviousButton() {
        backToPreviousButton = new JButton("Go back");
        backToPreviousButton.setBounds(590, 610, 100, 40);
        backToPreviousButton.setFont(new Font("Comic Sans", 1, 12));
        backToPreviousButton.setForeground(Color.black);
        backToPreviousButton.setBackground(Color.PINK);
        backToPreviousButton.setVisible(true);
        backToPreviousButton.addActionListener(this);
        return backToPreviousButton;
    }
}

