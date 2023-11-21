import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.JSeparator;
import java.awt.Color;

/**
 * a) Design Documentation: "SignUpUI"
 * b) Last Modified: November 18, 2023
 * c) @author Jevy Miranda, Sayra Reyes
 * d) Description: JPanel subclass for new account sign up. JTextFields for all inputs.
 * e) Functions: Displays JLabel error message with corresponding issue for the following issues:
 * 	  name field empty, email invalid, password too short, and re-typed password does not match.
 * 	  Invokes method to write to Customers.txt given valid inputs.
 * f) Data Structures: N/A
 * g) Algorithms: N/A
 */

public class SignUpPane extends JPanel {
	private JLabel lblError;
	
	private PropertyChangeSupport support;

	private static final long serialVersionUID = 1L;

	public SignUpPane(JPanel contentPane) {
		support = new PropertyChangeSupport(this);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{98, 129, 133, 90, 0};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);

		JLabel lblFirstName = new JLabel(" First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 0;
		add(lblFirstName, gbc_lblFirstName);

		JLabel lblLastName = new JLabel(" Last Name");
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 0;
		add(lblLastName, gbc_lblLastName);

		JTextField txtFirstName = new JTextField();
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 1;
		add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);

		JTextField txtLastName = new JTextField();
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 2;
		gbc_txtLastName.gridy = 1;
		add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);

		JLabel lblEmail = new JLabel(" Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 2;
		add(lblEmail, gbc_lblEmail);

		JTextField txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 3;
		add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPassword = new JLabel(" Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		add(lblPassword, gbc_lblPassword);

		JLabel lblRetypePassword = new JLabel(" Re-type Password");
		GridBagConstraints gbc_lblRetypePassword = new GridBagConstraints();
		gbc_lblRetypePassword.anchor = GridBagConstraints.WEST;
		gbc_lblRetypePassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRetypePassword.gridx = 2;
		gbc_lblRetypePassword.gridy = 4;
		add(lblRetypePassword, gbc_lblRetypePassword);

		JPasswordField passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 5;
		add(passwordField, gbc_passwordField);

		JPasswordField passwordFieldRetype = new JPasswordField();
		GridBagConstraints gbc_passwordFieldRetype = new GridBagConstraints();
		gbc_passwordFieldRetype.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRetype.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRetype.gridx = 2;
		gbc_passwordFieldRetype.gridy = 5;
		add(passwordFieldRetype, gbc_passwordFieldRetype);
		
		JButton btnPreviousPage = new JButton("Return");
		btnPreviousPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "SIGNIN");
			}
		});
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 6;
		add(separator, gbc_separator);
		GridBagConstraints gbc_btnPreviousPage = new GridBagConstraints();
		gbc_btnPreviousPage.insets = new Insets(0, 0, 5, 5);
		gbc_btnPreviousPage.gridx = 1;
		gbc_btnPreviousPage.gridy = 7;
		add(btnPreviousPage, gbc_btnPreviousPage);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtFirstName.getText().equals("") || txtLastName.getText().equals("")) {
					setError("First & Last Name required.");
				 } else if (txtEmail.getText().isEmpty()) {
					 setError("Email required.");
				 } else if (!txtEmail.getText().contains("@")){
					 setError("Invalid email.");
				 } else if (passwordField.getPassword().length < 8) {
					 setError("Password is required!");
				 } else if (!Arrays.equals(passwordField.getPassword(), passwordFieldRetype.getPassword())) {	
					 setError("Retype password!");
				} else {
					 if (!emailIsUnique(txtEmail.getText())) {
						 setError("Email is already used! try again!");
					 }else {
						AccountSignUp.writeToFile(txtEmail.getText(), String.valueOf(passwordField.getPassword()), txtFirstName.getText(), txtLastName.getText());
						lblError.setVisible(false);
						support.firePropertyChange("Successful SignUp!", null, true);
						((CardLayout) contentPane.getLayout()).show(contentPane, "MENU");
					 }
				}
			}

			
			/**
			 * Method reads through the file and checks if the provided email matches any existing emails. 
			 * @param email
			 * @return
			 */
			private boolean emailIsUnique(String email) {
			    try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/Customers.txt"))) {
			        String line;
			        while ((line = reader.readLine()) != null) {
			            String[] parts = line.split(", ");
			            if (parts.length >= 4 && parts[2].trim().equals(email.trim())) {
			                return false; 
			            }
			        }
			    } catch (IOException e) {
			        e.printStackTrace();
			        return false; 
			    }
			    return true; 
			}
			
			
		});
				
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnSignUp.gridx = 2;
		gbc_btnSignUp.gridy = 7;
		add(btnSignUp, gbc_btnSignUp);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 8;
		add(separator_1, gbc_separator_1);
		
		lblError = new JLabel("Error message.");
		lblError.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 2;
		gbc_lblError.insets = new Insets(0, 0, 5, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 9;
		add(lblError, gbc_lblError);
		
		lblError.setVisible(false);
	}
	
	/**
	 * Method to set error message and visibility 
	 * @param message
	 */
	private void setError(String message) {
		lblError.setVisible(true);
		lblError.setText(message);
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

}