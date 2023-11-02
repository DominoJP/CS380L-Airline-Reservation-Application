import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ReservationPaymentPane extends JPanel {
	private Reservation reservation;

	private static final long serialVersionUID = 1L;

	public ReservationPaymentPane(JPanel contentPane, Account account, Flight flight) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FIXME: RESERVE FLIGHT
				ArrayList<String> test = new ArrayList<>();
				test.add("name");
				reservation = new Reservation(account, flight, test);
				account.addReservationHistory(reservation);
				System.out.println(account.getReservationHistory());
				((CardLayout) contentPane.getLayout()).show(contentPane, "CONFIRM");
			}
		});
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.gridx = 0;
		gbc_btnPay.gridy = 0;
		add(btnPay, gbc_btnPay);

	}

}
