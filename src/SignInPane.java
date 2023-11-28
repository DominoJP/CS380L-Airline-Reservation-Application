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
import java.awt.font.TextAttribute;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Design Documentation: "SignInUI."
 * Description: JPanel subclass for account sign in.
 * <p>
 * Function: Login credentials (email and password) accepted as text inputs.
 * Calls method signIn() (another class) with parameters email input and password input, returning true if successful.
 * Method signIn() fires PropertyChangeEvents for account attributes email and id, observed by Panes of the booking process.
 * <p>
 * Data Structures: N/A.
 * Algorithms: N/A.
 * @version 1.3, Last Modified: November 25, 2023
 * @author Jevy Miranda
 */

public class SignInPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnSignUp;
	private JLabel lblWrong;

	/**
	 * Constructor.
	 * @param contentPane
	 * @param account
	 */
	public SignInPane(JPanel contentPane, Account account) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{140, 42, 71, 79, 140, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("I'm a...");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JRadioButton rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// shows "Sign Up" if "Customer" selected
				btnSignUp.setVisible(true);
				textField.setText("");
				passwordField.setText("");
			}
		});
		buttonGroup.add(rdbtnCustomer);
		rdbtnCustomer.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_rdbtnCustomer = new GridBagConstraints();
		gbc_rdbtnCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCustomer.gridx = 2;
		gbc_rdbtnCustomer.gridy = 1;
		add(rdbtnCustomer, gbc_rdbtnCustomer);
		
		JRadioButton rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// hides "Sign Up" if "Employee" selected
				btnSignUp.setVisible(false);
				textField.setText("");
				passwordField.setText("");
			}
		});
		buttonGroup.add(rdbtnManager);
		rdbtnManager.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GridBagConstraints gbc_rdbtnManager = new GridBagConstraints();
		gbc_rdbtnManager.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnManager.gridx = 3;
		gbc_rdbtnManager.gridy = 1;
		add(rdbtnManager, gbc_rdbtnManager);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.insets = new Insets(10, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 2;
		add(separator_1, gbc_separator_1);
		
		JLabel lblEmail = new JLabel(" Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.gridwidth = 3;
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 3;
		add(lblEmail, gbc_lblEmail);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 4;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel(" Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.gridwidth = 3;
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 5;
		add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		add(passwordField, gbc_passwordField);
		
		JButton btnLogInPane = new JButton("Sign In");
		btnLogInPane.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnLogInPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnCustomer.isSelected()) {
					// if sign in successful
					if (account.signIn(textField.getText(), passwordField.getPassword())) {
						// instantiate reservations from .txt
						ReservationIO.instantiateReservations(account);
						((CardLayout) contentPane.getLayout()).show(contentPane, "MENU");
					} else {
						lblWrong.setVisible(true);
						passwordField.setText("");
					}
				} else if (rdbtnManager.isSelected()) {
					if (Manager.signIn(textField.getText(), passwordField.getPassword())) {
						lblWrong.setVisible(false);
						// FIXME: REPORTS PANE
						System.out.println("FIXME: REPORTS PANE");
					} else {
						lblWrong.setVisible(true);
						passwordField.setText("");
					}
				}
			}
		});
		
		btnSignUp = new JButton("<html><font size='2'color=black> No account?</font> <font size='2'color=blue> <u>Sign up</u></font></html>");
		btnSignUp.setForeground(SystemColor.textHighlight);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "SIGNUP");
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.gridwidth = 3;
		gbc_separator_2.insets = new Insets(10, 0, 5, 5);
		gbc_separator_2.gridx = 2;
		gbc_separator_2.gridy = 7;
		add(separator_2, gbc_separator_2);
		btnSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSignUp.gridwidth = 2;
		gbc_btnSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnSignUp.gridx = 1;
		gbc_btnSignUp.gridy = 8;
		add(btnSignUp, gbc_btnSignUp);
		btnSignUp.setBorderPainted( false );
		
		GridBagConstraints gbc_btnLogInPane = new GridBagConstraints();
		gbc_btnLogInPane.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogInPane.gridx = 3;
		gbc_btnLogInPane.gridy = 8;
		add(btnLogInPane, gbc_btnLogInPane);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 9;
		add(separator, gbc_separator);
		
		lblWrong = new JLabel("Wrong Email or Password.");
		lblWrong.setForeground(Color.RED);
		lblWrong.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblWrong = new GridBagConstraints();
		gbc_lblWrong.gridwidth = 3;
		gbc_lblWrong.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrong.gridx = 1;
		gbc_lblWrong.gridy = 10;
		add(lblWrong, gbc_lblWrong);
		lblWrong.setVisible(false);
		
		this.underline(btnSignUp);
		this.setDefault(rdbtnCustomer);
	}
	
	private void underline(JButton btnSignUp) {
		Font font = btnSignUp.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		btnSignUp.setFont(font.deriveFont(attributes));
	}
	
	private void setDefault(JRadioButton rdbtnCustomer) {
		rdbtnCustomer.setSelected(true);
	}

}
