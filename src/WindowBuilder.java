import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class WindowBuilder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JButton btnLogInPane;
	private JButton btnSignUp;
	private JLabel lblWrong;
	private JPasswordField passwordField;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblNoAccount;
	private JLabel lblPlaceHolder;
	private JButton btnNewButton;
	private JSeparator separator;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblFirstName;
	private JLabel lblLastName;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilder frame = new WindowBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WindowBuilder() {
		TestAccount test = new TestAccount();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// CardLayout cl = new CardLayout(0, 0);
		
		setContentPane(contentPane);
		//contentPane.setLayout(cl);
		contentPane.setLayout(new CardLayout(0, 0));
		
		// JPanels
		
		JPanel LogInPane = new JPanel();
		LogInPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(LogInPane, "name_199742075500365");
		GridBagLayout gbl_LogInPane = new GridBagLayout();
		gbl_LogInPane.columnWidths = new int[]{0, 0, 0, 0, 0, 46, 0, 0};
		gbl_LogInPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_LogInPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_LogInPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		LogInPane.setLayout(gbl_LogInPane);
		
		JPanel CreationPane = new JPanel();
		contentPane.add(CreationPane, "name_213982163308624");
		GridBagLayout gbl_CreationPane = new GridBagLayout();
		// gbl_CreationPane.columnWidths = new int[]{0, 0, 0, 0 ,0 , 0};
		// gbl_CreationPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		// gbl_CreationPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		// gbl_CreationPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_CreationPane.columnWidths = new int[]{0, 0, 126, 60, 0, 46, 0, 0};
		gbl_CreationPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_CreationPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_CreationPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		CreationPane.setLayout(gbl_CreationPane);
		
		JPanel SelectionPane = new JPanel();
		SelectionPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(SelectionPane, "name_199935099642492");
		GridBagLayout gbl_SelectionPane = new GridBagLayout();
		gbl_SelectionPane.columnWidths = new int[]{0, 0};
		gbl_SelectionPane.rowHeights = new int[]{0, 0};
		gbl_SelectionPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_SelectionPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		SelectionPane.setLayout(gbl_SelectionPane);
		
		contentPane.add("LOGIN", LogInPane);
		contentPane.add("SELECTION", SelectionPane);
		contentPane.add("CREATION", CreationPane);
		
		// LogInPane components
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		
		lblEmail = new JLabel(" Email");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 0;
		LogInPane.add(lblEmail, gbc_lblEmail);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 1;
		LogInPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel(" Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 2;
		LogInPane.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		passwordField.setToolTipText("");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 3;
		LogInPane.add(passwordField, gbc_passwordField);
		
		lblWrong = new JLabel("Wrong Email or Password.");
		lblWrong.setForeground(Color.RED);
		lblWrong.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblWrong = new GridBagConstraints();
		gbc_lblWrong.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrong.gridx = 2;
		gbc_lblWrong.gridy = 4;
		LogInPane.add(lblWrong, gbc_lblWrong);
		lblWrong.setVisible(false);
		
		btnLogInPane = new JButton("Sign In");
		btnLogInPane.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnLogInPane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] inputEmail = txtEmail.getText().toCharArray();
				char[] inputPassword = passwordField.getPassword();
				// Arrays inherit equals from Object; .equals() will not work
				if (Arrays.equals(inputEmail, test.getEmail()) && Arrays.equals(inputPassword, test.getPassword())) {
					// cl.getLayout()).show(contentPane, "SELECTION");
					((CardLayout) contentPane.getLayout()).show(contentPane, "SELECTION");
					// cl.next(contentPane);
				}
				else {
					lblWrong.setVisible(true);
					passwordField.setText("");
				}
			}
		});
		GridBagConstraints gbc_btnLogInPane = new GridBagConstraints();
		gbc_btnLogInPane.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogInPane.gridx = 2;
		gbc_btnLogInPane.gridy = 5;
		LogInPane.add(btnLogInPane, gbc_btnLogInPane);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "CREATION");
			}
		});
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 6;
		LogInPane.add(separator, gbc_separator);
		
		lblNoAccount = new JLabel("No Account?");
		lblNoAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblNoAccount = new GridBagConstraints();
		gbc_lblNoAccount.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoAccount.gridx = 2;
		gbc_lblNoAccount.gridy = 7;
		LogInPane.add(lblNoAccount, gbc_lblNoAccount);
		btnSignUp.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSignUp = new GridBagConstraints();
		gbc_btnSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnSignUp.gridx = 2;
		gbc_btnSignUp.gridy = 8;
		LogInPane.add(btnSignUp, gbc_btnSignUp);
		
		// SelectionPane components
		
		btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		SelectionPane.add(btnNewButton, gbc_btnNewButton);
		
		// CreationPane components
		
		lblFirstName = new JLabel(" First Name");
		lblFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 2;
		gbc_lblFirstName.gridy = 0;
		CreationPane.add(lblFirstName, gbc_lblFirstName);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		CreationPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblLastName = new JLabel(" Last Name");
		lblLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 2;
		CreationPane.add(lblLastName, gbc_lblLastName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 3;
		CreationPane.add(textField, gbc_textField);
		textField.setColumns(10);
	}

}
