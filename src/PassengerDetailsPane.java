import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
   JPanel for Passenger Details, based on aa.com.
   @author Jevy Miranda
   @version 1.0
*/

public class PassengerDetailsPane extends JPanel implements PropertyChangeListener {
	
	private int selectedPassengerAmount;
	private Flight selectedFlight;
	private String selectedCabin;
	
	private PropertyChangeSupport support;

	private static final long serialVersionUID = 1L;
	private JTextField textName;
	private JTextField textDoB;
	private JComboBox comboBoxState;
	String[] DepartmentofStateGenderMarkerArray = {
			"Male (M)",
			"Female (F)",
			"Unspecified (X)",
			"Undisclosed (U)"
	};
	String[] cabinArray = {
			"Economy",
			"First Class"
	};
 	
	//FIXME: populate from .txt
	String[] countryArray = {
		"United States",
		"Afghanistan"
	};
	//FIXME: populate from .txt
	String[] stateArray = {
			"California"
		};

	public PassengerDetailsPane(JPanel contentPane, int passengerIndex, String previousPane, String nextPassengerDetailsPane, Flight flight) {
		support = new PropertyChangeSupport(this);
		selectedPassengerAmount = 1;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{168, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPassengerNumber = new JLabel(" Passenger " + passengerIndex + " Details: ");
		lblPassengerNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassengerNumber = new GridBagConstraints();
		gbc_lblPassengerNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPassengerNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerNumber.gridx = 0;
		gbc_lblPassengerNumber.gridy = 0;
		add(lblPassengerNumber, gbc_lblPassengerNumber);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		JLabel lblFullName = new JLabel(" Full Name");
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.anchor = GridBagConstraints.WEST;
		gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 2;
		add(lblFullName, gbc_lblFullName);
		
		JLabel lblGender = new JLabel(" Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 2;
		gbc_lblGender.gridy = 2;
		add(lblGender, gbc_lblGender);
		
		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 0;
		gbc_textName.gridy = 3;
		add(textName, gbc_textName);
		textName.setColumns(10);
		
		JComboBox comboBox = new JComboBox(DepartmentofStateGenderMarkerArray);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		
		JLabel lblFullNameAndGender = new JLabel("  Enter name and gender as printed on government-issued photo ID");
		lblFullNameAndGender.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblFullNameAndGender = new GridBagConstraints();
		gbc_lblFullNameAndGender.anchor = GridBagConstraints.WEST;
		gbc_lblFullNameAndGender.gridwidth = 3;
		gbc_lblFullNameAndGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullNameAndGender.gridx = 0;
		gbc_lblFullNameAndGender.gridy = 4;
		add(lblFullNameAndGender, gbc_lblFullNameAndGender);
		
		JLabel lblDoB = new JLabel(" Date of Birth (mm/dd/yyyy)");
		GridBagConstraints gbc_lblDoB = new GridBagConstraints();
		gbc_lblDoB.anchor = GridBagConstraints.WEST;
		gbc_lblDoB.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoB.gridx = 0;
		gbc_lblDoB.gridy = 5;
		add(lblDoB, gbc_lblDoB);
		
		textDoB = new JTextField();
		GridBagConstraints gbc_textDoB = new GridBagConstraints();
		gbc_textDoB.insets = new Insets(0, 0, 5, 5);
		gbc_textDoB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDoB.gridx = 0;
		gbc_textDoB.gridy = 6;
		add(textDoB, gbc_textDoB);
		textDoB.setColumns(10);
		
		JLabel lblDoBInvalidDateFormat = new JLabel("Invalid date format.");
		lblDoBInvalidDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDoBInvalidDateFormat.setForeground(Color.RED);
		GridBagConstraints gbc_lblDoBInvalidDateFormat = new GridBagConstraints();
		gbc_lblDoBInvalidDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoBInvalidDateFormat.gridx = 2;
		gbc_lblDoBInvalidDateFormat.gridy = 6;
		add(lblDoBInvalidDateFormat, gbc_lblDoBInvalidDateFormat);
		lblDoBInvalidDateFormat.setVisible(false);
		
		JLabel lblCountry = new JLabel(" Country or Region of Residence");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 7;
		add(lblCountry, gbc_lblCountry);
		
		JLabel lblState = new JLabel(" State of Residence");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.WEST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 2;
		gbc_lblState.gridy = 7;
		add(lblState, gbc_lblState);
		
		JComboBox comboBoxCountry = new JComboBox(countryArray);
		comboBoxCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCountry.getSelectedItem().toString().equals("United States")) {
					lblState.setVisible(true);
					comboBoxState.setVisible(true);
				} else {
					lblState.setVisible(false);
					comboBoxState.setVisible(false);
				}
			}
		});
		GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
		gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCountry.gridx = 0;
		gbc_comboBoxCountry.gridy = 8;
		add(comboBoxCountry, gbc_comboBoxCountry);
		
		comboBoxState = new JComboBox(stateArray);
		GridBagConstraints gbc_comboBoxState = new GridBagConstraints();
		gbc_comboBoxState.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxState.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxState.gridx = 2;
		gbc_comboBoxState.gridy = 8;
		add(comboBoxState, gbc_comboBoxState);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 9;
		add(separator_1, gbc_separator_1);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				support.firePropertyChange("passengerName" + passengerIndex, null, textName.getText());
				if (passengerIndex == selectedPassengerAmount) {
					support.firePropertyChange("sumRunningTotal", null, null);
					((CardLayout) contentPane.getLayout()).show(contentPane, "PAY");
				}
				else {
					((CardLayout) contentPane.getLayout()).show(contentPane, nextPassengerDetailsPane);
				}
			}
		});
		
		JButton btnBack = new JButton("Go back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, previousPane);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.WEST;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 10;
		add(btnBack, gbc_btnBack);
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.anchor = GridBagConstraints.EAST;
		gbc_btnContinue.insets = new Insets(0, 0, 0, 5);
		gbc_btnContinue.gridx = 2;
		gbc_btnContinue.gridy = 10;
		add(btnContinue, gbc_btnContinue);
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ((evt.getPropertyName()).equals("passengerAmount")) {
			this.selectedPassengerAmount = ((int) evt.getNewValue());
			System.out.print("amt");
		}
		
		if ((evt.getPropertyName()).equals("selectedFlight")) {
			this.selectedFlight = ((Flight) evt.getNewValue());
		}
		
	}

}