//Zandile Monalisa Dladla


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sunshine Sitters Registration");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 2));

            JLabel firstNameLabel = new JLabel("First Name:");
            JTextField firstNameField = new JTextField(20);

            JLabel lastNameLabel = new JLabel("Last Name:");
            JTextField lastNameField = new JTextField(20);

            JLabel emailLabel = new JLabel("Email Address:");
            JTextField emailField = new JTextField(20);

            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField(20);

            JButton submitButton = new JButton("Submit");

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = emailField.getText();
                    String password = new String(passwordField.getPassword());

                    // Process the user information here

                    JOptionPane.showMessageDialog(frame, "Registration successful!\nFirst Name: " + firstName
                            + "\nLast Name: " + lastName + "\nEmail Address: " + email);

                    // Clear the input fields after registration
                    firstNameField.setText("");
                    lastNameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");
                }
            });

            panel.add(firstNameLabel);
            panel.add(firstNameField);
            panel.add(lastNameLabel);
            panel.add(lastNameField);
            panel.add(emailLabel);
            panel.add(emailField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(new JLabel());
            panel.add(submitButton);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}

