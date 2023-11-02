import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservationConfirmationPane extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ReservationConfirmationPane(JPanel contentPane) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblConfirmation = new JLabel("Reservation booked successfully. Confirmation will be sent to your account email.");
		lblConfirmation.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblConfirmation = new GridBagConstraints();
		gbc_lblConfirmation.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmation.gridx = 0;
		gbc_lblConfirmation.gridy = 0;
		add(lblConfirmation, gbc_lblConfirmation);
		
		JButton btnReturn = new JButton("Return to Option Selection");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "SELECT");
			}
		});
		GridBagConstraints gbc_btnReturn = new GridBagConstraints();
		gbc_btnReturn.insets = new Insets(0, 0, 5, 5);
		gbc_btnReturn.gridx = 0;
		gbc_btnReturn.gridy = 1;
		add(btnReturn, gbc_btnReturn);

	}

}
