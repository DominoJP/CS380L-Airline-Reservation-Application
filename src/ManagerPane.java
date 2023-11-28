import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;


public class ManagerPane extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerIO managers = new ManagerIO();
					
					managers.getManagers();
					
					ManagerPane frame = new ManagerPane(managers.getList().get(0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManagerPane(Manager owner) {
		owner.totalReservations();
		
		setTitle("Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBoxOptions = new JComboBox();
		comboBoxOptions.setToolTipText("");
		comboBoxOptions.setModel(new DefaultComboBoxModel(new String[] {"Get Total Revenue", "Get Partial Revenue", "Get Customer Revenue", "Approve Refund", "Cancel Reservation"}));
		comboBoxOptions.setSelectedIndex(0);
		comboBoxOptions.setBounds(144, 65, 146, 22);
		contentPane.add(comboBoxOptions);
		
		JLabel lblOptions = new JLabel("Manager Options");
		lblOptions.setBounds(22, 65, 97, 22);
		contentPane.add(lblOptions);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(166, 227, 89, 23);
		contentPane.add(btnSelect);
		
		JLabel lblRevenue = new JLabel("Revenue");
		lblRevenue.setBounds(22, 176, 97, 22);
		contentPane.add(lblRevenue);
		
		JLabel lblCurrentRevenue = new JLabel("");
		lblCurrentRevenue.setBounds(144, 176, 146, 22);
		contentPane.add(lblCurrentRevenue);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setBounds(22, 128, 97, 22);
		contentPane.add(lblCustomer);
		lblCustomer.setVisible(false);

		
		JTextPane textCustomerName = new JTextPane();
		textCustomerName.setBounds(144, 130, 146, 20);
		contentPane.add(textCustomerName);
		textCustomerName.setVisible(false);
		
		comboBoxOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOptions.getSelectedItem().toString().equals("Get Customer Revenue")) {
					lblCustomer.setVisible(true);
					textCustomerName.setVisible(true);
				}else {
					lblCustomer.setVisible(false);
					textCustomerName.setVisible(false);				}
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxOptions.getSelectedItem().toString()) {
				case "Get Total Revenue":
					lblCurrentRevenue.setText(owner.getTotalRevenue().toString());
					break;
				case "Get Partial Revenue":
					break;
				case "Get Customer Revenue":
					owner.getAllAccounts();
					lblCurrentRevenue.setText(owner.getAccountRevenue(textCustomerName.getText()).toString());
					break;
				case "Approve Refund":
					break;
				case "Cancel Reservation":
					break;
				}
			}
		});
	}
}
