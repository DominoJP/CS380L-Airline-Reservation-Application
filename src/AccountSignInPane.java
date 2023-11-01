import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/**
   JPanel for account sign in.
   Login credentials (email and password) accepted as text inputs and authenticated on button press.
   @author Jevy Miranda
   @version 1.0
 */

public class AccountSignInPane extends JPanel {
	AccountSignIn signIn = new AccountSignIn();

	private static final long serialVersionUID = 1L;

	public AccountSignInPane(JPanel contentPane, Account account) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 115, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblEmail = new JLabel(" Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 0;
		add(lblEmail, gbc_lblEmail);
		
		JTextField textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel(" Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 3;
		add(passwordField, gbc_passwordField);
		
		JLabel lblWrong = new JLabel("Wrong Email or Password.");
		lblWrong.setForeground(Color.RED);
		lblWrong.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblWrong = new GridBagConstraints();
		gbc_lblWrong.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrong.gridx = 1;
		gbc_lblWrong.gridy = 4;
		add(lblWrong, gbc_lblWrong);
		lblWrong.setVisible(false);
		
		JButton btnLogInPane = new JButton("Sign In");
		btnLogInPane.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnLogInPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FIXME
				// TEMP
				if (signIn.validateCredentials(textField.getText(), passwordField.getPassword())) {
					account.setaccountNumber(signIn.getID());
					// account.setEmail(signIn.getEmail());
					// account.setPassword(signIn.getPassword().toString());
					System.out.println(account.getAccountNumber());
					((CardLayout) contentPane.getLayout()).show(contentPane, "SELECT");
				} else {
					lblWrong.setVisible(true);
					passwordField.setText("");
				}
				
			}
		});
		GridBagConstraints gbc_btnLogInPane = new GridBagConstraints();
		gbc_btnLogInPane.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogInPane.gridx = 1;
		gbc_btnLogInPane.gridy = 5;
		add(btnLogInPane, gbc_btnLogInPane);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 6;
		add(separator, gbc_separator);
		
		JLabel lblNoAccount = new JLabel("No Account?");
		lblNoAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblNoAccount = new GridBagConstraints();
		gbc_lblNoAccount.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoAccount.gridx = 1;
		gbc_lblNoAccount.gridy = 7;
		add(lblNoAccount, gbc_lblNoAccount);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "SIGNUP");
			}
		});
		btnSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnSignUp.gridx = 1;
		gbc_btnSignUp.gridy = 8;
		add(btnSignUp, gbc_btnSignUp);
		
	}
	
	public int getID() {
		return signIn.getID();
	}

}
