import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;

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
					ManagerPane frame = new ManagerPane();
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
	public ManagerPane() {
		setTitle("Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBoxOptions = new JComboBox();
		comboBoxOptions.setToolTipText("");
		comboBoxOptions.setModel(new DefaultComboBoxModel(new String[] {"Get Total Revenue", "Get Partial Revenue", "Approve Refund", "Cancel Reservation"}));
		comboBoxOptions.setSelectedIndex(0);
		comboBoxOptions.setBounds(144, 65, 146, 22);
		contentPane.add(comboBoxOptions);
		
		JLabel lblOptions = new JLabel("Manager Options");
		lblOptions.setBounds(22, 65, 97, 22);
		contentPane.add(lblOptions);
	}
}
