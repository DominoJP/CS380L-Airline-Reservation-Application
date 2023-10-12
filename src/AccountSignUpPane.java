import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridBagLayout;

/**
   JPanel for account sign up.
   Allows account creation with provided name, email, and password.
   @author
   @verison
*/

public class AccountSignUpPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public AccountSignUpPane(JPanel contentPane) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 115, 72, 0};
		gridBagLayout.rowHeights = new int[]{0, 16, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblFirstName = new JLabel(" First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 1;
		add(lblFirstName, gbc_lblFirstName);
		
		JLabel lblLastName = new JLabel(" Last Name");
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 1;
		add(lblLastName, gbc_lblLastName);
		
		JTextField txtFirstName = new JTextField();
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 2;
		add(txtFirstName, gbc_txtFirstName);
		txtFirstName.setColumns(10);
		
		JTextField txtLastName = new JTextField();
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 2;
		gbc_txtLastName.gridy = 2;
		add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);
		
		JLabel lblEmail = new JLabel(" Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		add(lblEmail, gbc_lblEmail);
		
		JTextField txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 4;
		add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel(" Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 5;
		add(lblPassword, gbc_lblPassword);
		
		JLabel lblRetypePassword = new JLabel(" Re-type Password");
		GridBagConstraints gbc_lblRetypePassword = new GridBagConstraints();
		gbc_lblRetypePassword.anchor = GridBagConstraints.WEST;
		gbc_lblRetypePassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRetypePassword.gridx = 2;
		gbc_lblRetypePassword.gridy = 5;
		add(lblRetypePassword, gbc_lblRetypePassword);
		
		JPasswordField passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		add(passwordField, gbc_passwordField);
		
		JPasswordField passwordFieldRetype = new JPasswordField();
		GridBagConstraints gbc_passwordFieldRetype = new GridBagConstraints();
		gbc_passwordFieldRetype.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldRetype.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldRetype.gridx = 2;
		gbc_passwordFieldRetype.gridy = 6;
		add(passwordFieldRetype, gbc_passwordFieldRetype);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String InputEmail = txtEmail.getText();
			}
		});
		
		JButton btnPreviousPage = new JButton("Previous Page");
		btnPreviousPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "SIGNIN");
			}
		});
		GridBagConstraints gbc_btnPreviousPage = new GridBagConstraints();
		gbc_btnPreviousPage.insets = new Insets(0, 0, 5, 5);
		gbc_btnPreviousPage.gridx = 1;
		gbc_btnPreviousPage.gridy = 8;
		add(btnPreviousPage, gbc_btnPreviousPage);
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnSignUp.gridx = 2;
		gbc_btnSignUp.gridy = 8;
		add(btnSignUp, gbc_btnSignUp);
		
	}

}
