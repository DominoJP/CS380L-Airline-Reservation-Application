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
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollBar;

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
	private JTextField txtCreationPaneLastName;
	private JTextField txtCreationPaneFirstName;
	private JLabel lblCreationPaneFirstName;
	private JLabel lblCreationPaneLastName;
	private JScrollPane scrollPane;
	private JPanel FilterPane;
	private JPanel FilterListPane;
	private JComboBox selectionComboBox;
	private JButton btnSelectionPaneContinue;
	private JComboBox flightTypeComboBox;
	private JTextField txtFilterPaneDepart;
	private JLabel lblFilterPaneDepart;
	private JLabel lblFilterPaneReturn;
	private JRadioButton rdbtnFilterPaneRoundTrip;
	private JRadioButton rdbtnFilterPaneOneWay;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtFilterPaneReturn;
	private JLabel lblFilterPaneInvalidDepartDateFormat;
	private JLabel lblFilterPaneInvalidReturnDateFormat;
	private JTextField txtFilterPaneFrom;
	private JTextField txtFilterPaneTo;
	private JLabel lblFilterPaneFrom;
	private JLabel lblFilterPaneTo;
	private JSpinner spinnerFilterPanePassengers;
	private JLabel lblFilterPanePassengers;
	private JComboBox comboBoxFilterPanePassengers;
	private JButton btnFilterPaneSearch;
	private JLabel lblCreationPaneEmail;
	private JTextField txtCreationPaneEmail;
	private JLabel lblCreationPanePassword;
	private JPasswordField passwordFieldCreationPane;
	private JLabel lblCreationPaneReTypePassword_1;
	private JPasswordField passwordFieldCreationPaneReType;
	private JButton btnCreationPaneSignUp;
	private JScrollPane scrollPaneTest;
	private JList listScrollPaneTest;
	private JScrollBar scrollBar;
	private JButton btnFilterListPaneReserve;
	private JPanel AccountReview;
	private JLabel lblNewLabel;
	private JPanel CancellationPanel;
	private JLabel lblNewLabel_1;


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
		contentPane.add(LogInPane, "LOGIN");
		GridBagLayout gbl_LogInPane = new GridBagLayout();
		gbl_LogInPane.columnWidths = new int[]{0, 0, 0, 0, 0, 46, 0, 0};
		gbl_LogInPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_LogInPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_LogInPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		LogInPane.setLayout(gbl_LogInPane);
		
		JPanel CreationPane = new JPanel();
		contentPane.add(CreationPane, "CREATION");
		GridBagLayout gbl_CreationPane = new GridBagLayout();
		// gbl_CreationPane.columnWidths = new int[]{0, 0, 0, 0 ,0 , 0};
		// gbl_CreationPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		// gbl_CreationPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		// gbl_CreationPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_CreationPane.columnWidths = new int[]{0, 0, 126, 60, 0, 46, 0, 0};
		gbl_CreationPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_CreationPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_CreationPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		CreationPane.setLayout(gbl_CreationPane);
		
		JPanel SelectionPane = new JPanel();
		SelectionPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(SelectionPane, "SELECTION");
		GridBagLayout gbl_SelectionPane = new GridBagLayout();
		// gbl_SelectionPane.columnWidths = new int[]{39, 0};
		// gbl_SelectionPane.rowHeights = new int[]{0, 0, 0};
		// gbl_SelectionPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		// gbl_SelectionPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_SelectionPane.columnWidths = new int[]{0, 0, 0, 0, 0, 46, 0, 0};
		gbl_SelectionPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_SelectionPane.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_SelectionPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		SelectionPane.setLayout(gbl_SelectionPane);
		
		FilterPane = new JPanel();
		contentPane.add(FilterPane, "FILTER");
		GridBagLayout gbl_FilterPane = new GridBagLayout();
		gbl_FilterPane.columnWidths = new int[]{91, 0, 0, 0, 0, 0, 4, 0, 0};
		gbl_FilterPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_FilterPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_FilterPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		FilterPane.setLayout(gbl_FilterPane);
		
		FilterListPane = new JPanel();
		contentPane.add(FilterListPane, "FILTERLIST");
		GridBagLayout gbl_FilterListPane = new GridBagLayout();
		gbl_FilterListPane.columnWidths = new int[]{91, 0, 0, 0, 0, 0, 4, 0, 0};
		gbl_FilterListPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_FilterListPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_FilterListPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		FilterListPane.setLayout(gbl_FilterListPane);
		
		scrollPaneTest = new JScrollPane();
		contentPane.add(scrollPaneTest, "SCROLLTEST");
		
		AccountReview = new JPanel();
		contentPane.add(AccountReview, "AccountReview");
		GridBagLayout gbl_AccountReview = new GridBagLayout();
		gbl_AccountReview.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_AccountReview.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_AccountReview.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_AccountReview.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		AccountReview.setLayout(gbl_AccountReview);
		
		CancellationPanel = new JPanel();
		contentPane.add(CancellationPanel, "CancellationPanel");
		GridBagLayout gbl_CancellationPanel = new GridBagLayout();
		gbl_CancellationPanel.columnWidths = new int[]{158, 0};
		gbl_CancellationPanel.rowHeights = new int[]{14, 0};
		gbl_CancellationPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_CancellationPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		CancellationPanel.setLayout(gbl_CancellationPanel);
		
		
		
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
				String inputEmail = txtEmail.getText();
				char[] inputPassword = passwordField.getPassword();
				// Arrays inherit equals from Object; .equals() will not work
				if (inputEmail.equals(test.getEmail()) && Arrays.equals(inputPassword, test.getPassword())) {
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
		
		String [] selectionOptions = {
				"Reserve",
				"Review",
				"Cancel",
		};
		selectionComboBox = new JComboBox(selectionOptions);
		GridBagConstraints gbc_selectionComboBox = new GridBagConstraints();
		gbc_selectionComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_selectionComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_selectionComboBox.gridx = 0;
		gbc_selectionComboBox.gridy = 2;
		SelectionPane.add(selectionComboBox, gbc_selectionComboBox);
		
		btnSelectionPaneContinue = new JButton("Continue");
		btnSelectionPaneContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectionComboBox.getSelectedItem().equals("Reserve")) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER");
				}
			}
		});
		GridBagConstraints gbc_btnSelectionPaneContinue = new GridBagConstraints();
		gbc_btnSelectionPaneContinue.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectionPaneContinue.gridx = 0;
		gbc_btnSelectionPaneContinue.gridy = 3;
		SelectionPane.add(btnSelectionPaneContinue, gbc_btnSelectionPaneContinue);
		
		// CreationPane components
		
		lblCreationPaneFirstName = new JLabel(" First Name");
		lblCreationPaneFirstName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCreationPaneFirstName = new GridBagConstraints();
		gbc_lblCreationPaneFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblCreationPaneFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreationPaneFirstName.gridx = 2;
		gbc_lblCreationPaneFirstName.gridy = 0;
		CreationPane.add(lblCreationPaneFirstName, gbc_lblCreationPaneFirstName);
		
		lblCreationPaneLastName = new JLabel(" Last Name");
		lblCreationPaneLastName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblCreationPaneLastName = new GridBagConstraints();
		gbc_lblCreationPaneLastName.anchor = GridBagConstraints.WEST;
		gbc_lblCreationPaneLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreationPaneLastName.gridx = 4;
		gbc_lblCreationPaneLastName.gridy = 0;
		CreationPane.add(lblCreationPaneLastName, gbc_lblCreationPaneLastName);
		
		txtCreationPaneFirstName = new JTextField();
		GridBagConstraints gbc_txtCreationPaneFirstName = new GridBagConstraints();
		gbc_txtCreationPaneFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreationPaneFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreationPaneFirstName.gridx = 2;
		gbc_txtCreationPaneFirstName.gridy = 1;
		CreationPane.add(txtCreationPaneFirstName, gbc_txtCreationPaneFirstName);
		txtCreationPaneFirstName.setColumns(10);
		
		txtCreationPaneLastName = new JTextField();
		GridBagConstraints gbc_txtCreationPaneLastName = new GridBagConstraints();
		gbc_txtCreationPaneLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreationPaneLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreationPaneLastName.gridx = 4;
		gbc_txtCreationPaneLastName.gridy = 1;
		CreationPane.add(txtCreationPaneLastName, gbc_txtCreationPaneLastName);
		txtCreationPaneLastName.setColumns(10);
		
		lblCreationPaneEmail = new JLabel(" Email");
		GridBagConstraints gbc_lblCreationPaneEmail = new GridBagConstraints();
		gbc_lblCreationPaneEmail.anchor = GridBagConstraints.WEST;
		gbc_lblCreationPaneEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreationPaneEmail.gridx = 2;
		gbc_lblCreationPaneEmail.gridy = 2;
		CreationPane.add(lblCreationPaneEmail, gbc_lblCreationPaneEmail);
		
		txtCreationPaneEmail = new JTextField();
		GridBagConstraints gbc_txtCreationPaneEmail = new GridBagConstraints();
		gbc_txtCreationPaneEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtCreationPaneEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCreationPaneEmail.gridx = 2;
		gbc_txtCreationPaneEmail.gridy = 3;
		CreationPane.add(txtCreationPaneEmail, gbc_txtCreationPaneEmail);
		txtCreationPaneEmail.setColumns(10);
		
		lblCreationPanePassword = new JLabel(" Password");
		GridBagConstraints gbc_lblCreationPanePassword = new GridBagConstraints();
		gbc_lblCreationPanePassword.anchor = GridBagConstraints.WEST;
		gbc_lblCreationPanePassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreationPanePassword.gridx = 2;
		gbc_lblCreationPanePassword.gridy = 4;
		CreationPane.add(lblCreationPanePassword, gbc_lblCreationPanePassword);
		
		lblCreationPaneReTypePassword_1 = new JLabel(" Re-type Password");
		GridBagConstraints gbc_lblCreationPaneReTypePassword_1 = new GridBagConstraints();
		gbc_lblCreationPaneReTypePassword_1.anchor = GridBagConstraints.WEST;
		gbc_lblCreationPaneReTypePassword_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreationPaneReTypePassword_1.gridx = 4;
		gbc_lblCreationPaneReTypePassword_1.gridy = 4;
		CreationPane.add(lblCreationPaneReTypePassword_1, gbc_lblCreationPaneReTypePassword_1);
		
		passwordFieldCreationPane = new JPasswordField();
		GridBagConstraints gbc_passwordFieldCreationPane = new GridBagConstraints();
		gbc_passwordFieldCreationPane.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldCreationPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldCreationPane.gridx = 2;
		gbc_passwordFieldCreationPane.gridy = 5;
		CreationPane.add(passwordFieldCreationPane, gbc_passwordFieldCreationPane);
		
		passwordFieldCreationPaneReType = new JPasswordField();
		GridBagConstraints gbc_passwordFieldCreationPaneReType = new GridBagConstraints();
		gbc_passwordFieldCreationPaneReType.insets = new Insets(0, 0, 5, 5);
		gbc_passwordFieldCreationPaneReType.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordFieldCreationPaneReType.gridx = 4;
		gbc_passwordFieldCreationPaneReType.gridy = 5;
		CreationPane.add(passwordFieldCreationPaneReType, gbc_passwordFieldCreationPaneReType);
		
		btnCreationPaneSignUp = new JButton("Sign Up");
		btnCreationPaneSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String CreationPaneInputEmail = txtCreationPaneEmail.getText();
			}
		});
		GridBagConstraints gbc_btnCreationPaneSignUp = new GridBagConstraints();
		gbc_btnCreationPaneSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreationPaneSignUp.gridx = 4;
		gbc_btnCreationPaneSignUp.gridy = 8;
		CreationPane.add(btnCreationPaneSignUp, gbc_btnCreationPaneSignUp);
		
		// FilterPane components
		
		rdbtnFilterPaneRoundTrip = new JRadioButton("Round Trip");
		rdbtnFilterPaneRoundTrip.setSelected(true);
		rdbtnFilterPaneRoundTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFilterPaneReturn.setVisible(true);
				txtFilterPaneReturn.setVisible(true);
			}
		});
		buttonGroup.add(rdbtnFilterPaneRoundTrip);
		GridBagConstraints gbc_rdbtnFilterPaneRoundTrip = new GridBagConstraints();
		gbc_rdbtnFilterPaneRoundTrip.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFilterPaneRoundTrip.gridx = 1;
		gbc_rdbtnFilterPaneRoundTrip.gridy = 0;
		FilterPane.add(rdbtnFilterPaneRoundTrip, gbc_rdbtnFilterPaneRoundTrip);
		
		rdbtnFilterPaneOneWay = new JRadioButton("One way");
		rdbtnFilterPaneOneWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFilterPaneReturn.setVisible(false);
				txtFilterPaneReturn.setVisible(false);
			}
		});
		buttonGroup.add(rdbtnFilterPaneOneWay);
		GridBagConstraints gbc_rdbtnFilterPaneOneWay = new GridBagConstraints();
		gbc_rdbtnFilterPaneOneWay.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFilterPaneOneWay.gridx = 3;
		gbc_rdbtnFilterPaneOneWay.gridy = 0;
		FilterPane.add(rdbtnFilterPaneOneWay, gbc_rdbtnFilterPaneOneWay);
		
		lblFilterPaneFrom = new JLabel(" From");
		GridBagConstraints gbc_lblFilterPaneFrom = new GridBagConstraints();
		gbc_lblFilterPaneFrom.anchor = GridBagConstraints.WEST;
		gbc_lblFilterPaneFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPaneFrom.gridx = 1;
		gbc_lblFilterPaneFrom.gridy = 2;
		FilterPane.add(lblFilterPaneFrom, gbc_lblFilterPaneFrom);
		
		lblFilterPaneTo = new JLabel(" To");
		GridBagConstraints gbc_lblFilterPaneTo = new GridBagConstraints();
		gbc_lblFilterPaneTo.anchor = GridBagConstraints.WEST;
		gbc_lblFilterPaneTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPaneTo.gridx = 3;
		gbc_lblFilterPaneTo.gridy = 2;
		FilterPane.add(lblFilterPaneTo, gbc_lblFilterPaneTo);
		
		txtFilterPaneFrom = new JTextField();
		GridBagConstraints gbc_txtFilterPaneFrom = new GridBagConstraints();
		gbc_txtFilterPaneFrom.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilterPaneFrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFilterPaneFrom.gridx = 1;
		gbc_txtFilterPaneFrom.gridy = 3;
		FilterPane.add(txtFilterPaneFrom, gbc_txtFilterPaneFrom);
		txtFilterPaneFrom.setColumns(10);
		
		txtFilterPaneTo = new JTextField();
		GridBagConstraints gbc_txtFilterPaneTo = new GridBagConstraints();
		gbc_txtFilterPaneTo.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilterPaneTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFilterPaneTo.gridx = 3;
		gbc_txtFilterPaneTo.gridy = 3;
		FilterPane.add(txtFilterPaneTo, gbc_txtFilterPaneTo);
		txtFilterPaneTo.setColumns(10);
		
		lblFilterPaneDepart = new JLabel(" Depart (dd/mm/yyyy)");
		GridBagConstraints gbc_lblFilterPaneDepart = new GridBagConstraints();
		gbc_lblFilterPaneDepart.anchor = GridBagConstraints.WEST;
		gbc_lblFilterPaneDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPaneDepart.gridx = 1;
		gbc_lblFilterPaneDepart.gridy = 4;
		FilterPane.add(lblFilterPaneDepart, gbc_lblFilterPaneDepart);
		
		lblFilterPaneReturn = new JLabel(" Return (dd/mm/yyyy)");
		GridBagConstraints gbc_lblFilterPaneReturn = new GridBagConstraints();
		gbc_lblFilterPaneReturn.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPaneReturn.gridx = 3;
		gbc_lblFilterPaneReturn.gridy = 4;
		FilterPane.add(lblFilterPaneReturn, gbc_lblFilterPaneReturn);
		
		txtFilterPaneDepart = new JTextField();
		GridBagConstraints gbc_txtFilterPaneDepart = new GridBagConstraints();
		gbc_txtFilterPaneDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFilterPaneDepart.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilterPaneDepart.gridx = 1;
		gbc_txtFilterPaneDepart.gridy = 5;
		FilterPane.add(txtFilterPaneDepart, gbc_txtFilterPaneDepart);
		txtFilterPaneDepart.setColumns(10);
		
		txtFilterPaneReturn = new JTextField();
		GridBagConstraints gbc_txtFilterPaneReturn = new GridBagConstraints();
		gbc_txtFilterPaneReturn.insets = new Insets(0, 0, 5, 5);
		gbc_txtFilterPaneReturn.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFilterPaneReturn.gridx = 3;
		gbc_txtFilterPaneReturn.gridy = 5;
		FilterPane.add(txtFilterPaneReturn, gbc_txtFilterPaneReturn);
		txtFilterPaneReturn.setColumns(10);
		
		lblFilterPaneInvalidDepartDateFormat = new JLabel("Invalid date format");
		lblFilterPaneInvalidDepartDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblFilterPaneInvalidDepartDateFormat.setForeground(Color.RED);
		GridBagConstraints gbc_lblFilterPaneInvalidDepartDateFormat = new GridBagConstraints();
		gbc_lblFilterPaneInvalidDepartDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPaneInvalidDepartDateFormat.gridx = 1;
		gbc_lblFilterPaneInvalidDepartDateFormat.gridy = 7;
		FilterPane.add(lblFilterPaneInvalidDepartDateFormat, gbc_lblFilterPaneInvalidDepartDateFormat);
		lblFilterPaneInvalidDepartDateFormat.setVisible(false);
		
		lblFilterPaneInvalidReturnDateFormat = new JLabel("Invalid date format");
		lblFilterPaneInvalidReturnDateFormat.setForeground(Color.RED);
		lblFilterPaneInvalidReturnDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblFilterPaneInvalidReturnDateFormat = new GridBagConstraints();
		gbc_lblFilterPaneInvalidReturnDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPaneInvalidReturnDateFormat.gridx = 3;
		gbc_lblFilterPaneInvalidReturnDateFormat.gridy = 7;
		FilterPane.add(lblFilterPaneInvalidReturnDateFormat, gbc_lblFilterPaneInvalidReturnDateFormat);
		lblFilterPaneInvalidReturnDateFormat.setVisible(false);
		
		lblFilterPanePassengers = new JLabel("Passengers");
		GridBagConstraints gbc_lblFilterPanePassengers = new GridBagConstraints();
		gbc_lblFilterPanePassengers.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilterPanePassengers.gridx = 1;
		gbc_lblFilterPanePassengers.gridy = 8;
		FilterPane.add(lblFilterPanePassengers, gbc_lblFilterPanePassengers);
		
		Integer [] passengerOptions = {
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9
		};
		comboBoxFilterPanePassengers = new JComboBox(passengerOptions);
		GridBagConstraints gbc_comboBoxFilterPanePassengers = new GridBagConstraints();
		gbc_comboBoxFilterPanePassengers.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFilterPanePassengers.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFilterPanePassengers.gridx = 1;
		gbc_comboBoxFilterPanePassengers.gridy = 9;
		FilterPane.add(comboBoxFilterPanePassengers, gbc_comboBoxFilterPanePassengers);
		
		btnFilterPaneSearch = new JButton("Search");
		btnFilterPaneSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int FilterPanePassengerNumber = comboBoxFilterPanePassengers.getSelectedIndex();
				// STANDBY: METHOD FOR FILTERING FLIGHTS
				((CardLayout) contentPane.getLayout()).show(contentPane, "SCROLLTEST");
			}
		});
		GridBagConstraints gbc_btnFilterPaneSearch = new GridBagConstraints();
		gbc_btnFilterPaneSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnFilterPaneSearch.gridx = 3;
		gbc_btnFilterPaneSearch.gridy = 9;
		FilterPane.add(btnFilterPaneSearch, gbc_btnFilterPaneSearch);
		
		// scrollPaneTest components
		
		// ADD CASE FOR NO FLIGHTS FOUND. RETOOL "RESERVE SELECTED FLIGHT" BUTTON INTO "GO BACK BUTTON"
		String testArray[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"};
		// Java Docs: JList doesn't implement scrolling directly. To create a list that scrolls, make it the viewport view of a JScrollPane
		listScrollPaneTest = new JList(testArray);
		scrollPaneTest.setViewportView(listScrollPaneTest);
		listScrollPaneTest.setSelectedIndex(0);
		// returns Obj
		listScrollPaneTest.getSelectedValue();
		
		btnFilterListPaneReserve = new JButton("Reserve Selected Flight");
		btnFilterListPaneReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		scrollPaneTest.setColumnHeaderView(btnFilterListPaneReserve);
		
		
		//AccountReview panel components
		lblNewLabel = new JLabel("You are here");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 6;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 4;
		AccountReview.add(lblNewLabel, gbc_lblNewLabel);
		
		
		//CancellationPanel components
		lblNewLabel_1 = new JLabel("Are you sure you wish to cancel?");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		CancellationPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
	}

}
