import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

/**
 * a) Design Documentation: 'NavigationUI'
 * b) Date of Creation: October 10, 2023
 * c) @author Jevy Miranda
 * d) Description: JPanel subclass serving as a navigation hub between flight booking, review, and cancellation.
 * e) Functions: Displays JLabel feedback text upon successful sign up and flight booking.
 * f) Data Structures: N/A
 * g) Algorithms: N/A
 */
public class MenuPane extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JLabel lblConfirmation;

	public MenuPane(JPanel contentPane, Account account) {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{95, 100, 100, 100, 95};
		gridBagLayout.rowHeights = new int[]{75, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// comboBox parameter
		String [] optionsArray = {
				"Reserve",
				"Review",
				"Cancel",
		};
		
		lblConfirmation = new JLabel("");
		lblConfirmation.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblConfirmation = new GridBagConstraints();
		gbc_lblConfirmation.gridwidth = 3;
		gbc_lblConfirmation.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmation.gridx = 1;
		gbc_lblConfirmation.gridy = 1;
		add(lblConfirmation, gbc_lblConfirmation);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		JComboBox comboBox = new JComboBox(optionsArray);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblConfirmation.setVisible(false);
				String selection = comboBox.getSelectedItem().toString();
				switch (selection) {
					case "Reserve":
						((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER");
						break;
					case "Review":
						// FIXME: REMOVE
						// ReservationListPane ReviewPane = new ReservationListPane(contentPane, account);
						// contentPane.add(ReviewPane, "REVIEW");
						((CardLayout) contentPane.getLayout()).show(contentPane, "REVIEW_LIST");
						break;
					case "Cancel":
						((CardLayout) contentPane.getLayout()).show(contentPane, "Cancel");
						break;
				}
		}
		});
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.insets = new Insets(0, 0, 5, 5);
		gbc_btnContinue.gridx = 2;
		gbc_btnContinue.gridy = 4;
		add(btnContinue, gbc_btnContinue);
		
		lblConfirmation.setVisible(false);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		// fires from PaymentPane
		if (evt.getPropertyName().equals("reservationBooked")) {
			lblConfirmation.setVisible((boolean) evt.getNewValue());
			lblConfirmation.setText("Reservation booked. Confirmation will be sent to your email.");
		}
		// fires from 
		if (evt.getPropertyName().equals("successfulSignUp")) {
			lblConfirmation.setVisible((boolean) evt.getNewValue());
			lblConfirmation.setText("Sign up successful.");
		}
	}
	

}
