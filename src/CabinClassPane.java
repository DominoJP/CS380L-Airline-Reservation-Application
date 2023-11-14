import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.ButtonGroup;

public class CabinClassPane extends JPanel implements PropertyChangeListener {
	private int selectedPassengerAmount;
	private Flight selectedFlight;
	private PropertyChangeSupport support;
	
	private JLabel lblEconomyPricing;
	private JLabel lblBusinessPricing;
	private JLabel lblFirstClassPricing;
	private JLabel lblEconomySeating;
	private JLabel lblBusinessSeating;
	private JLabel lblFirstClassSeating;

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public CabinClassPane(JPanel contentPane, Flight selectedFlight) {
		support = new PropertyChangeSupport(this);
		selectedPassengerAmount = 1;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 0, 0, 0, 20, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPricing = new JLabel("FARES");
		GridBagConstraints gbc_lblPricing = new GridBagConstraints();
		gbc_lblPricing.insets = new Insets(0, 0, 5, 5);
		gbc_lblPricing.gridx = 2;
		gbc_lblPricing.gridy = 1;
		add(lblPricing, gbc_lblPricing);
		
		JLabel lblSeats = new JLabel("SEATS");
		GridBagConstraints gbc_lblSeats = new GridBagConstraints();
		gbc_lblSeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeats.gridx = 3;
		gbc_lblSeats.gridy = 1;
		add(lblSeats, gbc_lblSeats);
		
		JLabel lblCabinClass = new JLabel("CABIN CLASS");
		GridBagConstraints gbc_lblCabinClass = new GridBagConstraints();
		gbc_lblCabinClass.insets = new Insets(0, 0, 5, 5);
		gbc_lblCabinClass.gridx = 5;
		gbc_lblCabinClass.gridy = 1;
		add(lblCabinClass, gbc_lblCabinClass);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 1;
		gbc_separator_2.gridy = 2;
		add(separator_2, gbc_separator_2);
		
		lblEconomyPricing = new JLabel("$0.00");
		GridBagConstraints gbc_lblEconomyPricing = new GridBagConstraints();
		gbc_lblEconomyPricing.insets = new Insets(0, 0, 5, 5);
		gbc_lblEconomyPricing.gridx = 2;
		gbc_lblEconomyPricing.gridy = 3;
		add(lblEconomyPricing, gbc_lblEconomyPricing);
		
		lblEconomySeating = new JLabel("0 left");
		GridBagConstraints gbc_lblEconomySeating = new GridBagConstraints();
		gbc_lblEconomySeating.insets = new Insets(0, 0, 5, 5);
		gbc_lblEconomySeating.gridx = 3;
		gbc_lblEconomySeating.gridy = 3;
		add(lblEconomySeating, gbc_lblEconomySeating);
		
		JRadioButton rdbtnEconomy = new JRadioButton("Economy");
		buttonGroup.add(rdbtnEconomy);
		GridBagConstraints gbc_rdbtnEconomy = new GridBagConstraints();
		gbc_rdbtnEconomy.anchor = GridBagConstraints.WEST;
		gbc_rdbtnEconomy.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnEconomy.gridx = 5;
		gbc_rdbtnEconomy.gridy = 3;
		add(rdbtnEconomy, gbc_rdbtnEconomy);
		
		
		lblBusinessPricing = new JLabel("$0.00");
		GridBagConstraints gbc_lblBusinessPricing = new GridBagConstraints();
		gbc_lblBusinessPricing.insets = new Insets(0, 0, 5, 5);
		gbc_lblBusinessPricing.gridx = 2;
		gbc_lblBusinessPricing.gridy = 4;
		add(lblBusinessPricing, gbc_lblBusinessPricing);
		
		lblBusinessSeating = new JLabel("0 left");
		GridBagConstraints gbc_lblBusinessSeating = new GridBagConstraints();
		gbc_lblBusinessSeating.insets = new Insets(0, 0, 5, 5);
		gbc_lblBusinessSeating.gridx = 3;
		gbc_lblBusinessSeating.gridy = 4;
		add(lblBusinessSeating, gbc_lblBusinessSeating);
		
		JRadioButton rdbtnBusiness = new JRadioButton("Business");
		buttonGroup.add(rdbtnBusiness);
		GridBagConstraints gbc_rdbtnBusiness = new GridBagConstraints();
		gbc_rdbtnBusiness.anchor = GridBagConstraints.WEST;
		gbc_rdbtnBusiness.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBusiness.gridx = 5;
		gbc_rdbtnBusiness.gridy = 4;
		add(rdbtnBusiness, gbc_rdbtnBusiness);
		
		lblFirstClassPricing = new JLabel("$0.00");
		GridBagConstraints gbc_lblFirstClassPricing = new GridBagConstraints();
		gbc_lblFirstClassPricing.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstClassPricing.gridx = 2;
		gbc_lblFirstClassPricing.gridy = 5;
		add(lblFirstClassPricing, gbc_lblFirstClassPricing);
		
		lblFirstClassSeating = new JLabel("0 left");
		GridBagConstraints gbc_lblFirstClassSeating = new GridBagConstraints();
		gbc_lblFirstClassSeating.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstClassSeating.gridx = 3;
		gbc_lblFirstClassSeating.gridy = 5;
		add(lblFirstClassSeating, gbc_lblFirstClassSeating);
		
		JRadioButton rdbtnFirstClass = new JRadioButton("First Class");
		buttonGroup.add(rdbtnFirstClass);
		GridBagConstraints gbc_rdbtnFirstClass = new GridBagConstraints();
		gbc_rdbtnFirstClass.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFirstClass.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFirstClass.gridx = 5;
		gbc_rdbtnFirstClass.gridy = 5;
		add(rdbtnFirstClass, gbc_rdbtnFirstClass);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.insets = new Insets(0, 0, 5, 5);
		gbc_separator_3.gridx = 1;
		gbc_separator_3.gridy = 6;
		add(separator_3, gbc_separator_3);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFirstClass.isSelected()) {
					support.firePropertyChange("selectedCabin", null, "First Class");
				} else if (rdbtnBusiness.isSelected()) {
					support.firePropertyChange("selectedCabin", null, "Business");
				} else {
					support.firePropertyChange("selectedCabin", null, "Economy");
				}
				((CardLayout) contentPane.getLayout()).show(contentPane, "PASSENGER1_DETAILS");
			}
		});
		
		JButton btnBack = new JButton("Return");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER_LIST");
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 7;
		add(btnBack, gbc_btnBack);
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.anchor = GridBagConstraints.EAST;
		gbc_btnContinue.gridwidth = 2;
		gbc_btnContinue.insets = new Insets(0, 0, 5, 5);
		gbc_btnContinue.gridx = 4;
		gbc_btnContinue.gridy = 7;
		add(btnContinue, gbc_btnContinue);
		
		
		rdbtnEconomy.setSelected(true);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 4;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 8;
		add(separator, gbc_separator);
		
		JLabel lblSeatingWarning = new JLabel("Insufficient seating for passenger selection.");
		lblSeatingWarning.setForeground(Color.RED);
		lblSeatingWarning.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblSeatingWarning = new GridBagConstraints();
		gbc_lblSeatingWarning.insets = new Insets(0, 0, 0, 5);
		gbc_lblSeatingWarning.gridwidth = 4;
		gbc_lblSeatingWarning.gridx = 2;
		gbc_lblSeatingWarning.gridy = 9;
		add(lblSeatingWarning, gbc_lblSeatingWarning);
		lblSeatingWarning.setVisible(false);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ((evt.getPropertyName()).equals("passengerAmount")) {
			this.selectedPassengerAmount = ((int) evt.getNewValue());
			// FIXME: add check
		}
		
		if ((evt.getPropertyName()).equals("selectedFlight")) {
			this.selectedFlight = ((Flight) evt.getNewValue());
			lblEconomyPricing.setText("$" + selectedFlight.getEconomyPricing().toString());
			lblBusinessPricing.setText("$" + selectedFlight.getBusinessPricing().toString());
			lblFirstClassPricing.setText("$" + selectedFlight.getFirstClassPricing().toString());
			lblEconomySeating.setText(selectedFlight.getEconomyCapacity() - selectedFlight.getEconomyPassengerCount() + " left");
			lblBusinessSeating.setText(selectedFlight.getBusinessCapacity() - selectedFlight.getBusinessPassengerCount() + " left");
			lblFirstClassSeating.setText(selectedFlight.getFirstClassCapacity() - selectedFlight.getFirstClassPassengerCount() + " left");
		}
		
	}

}
