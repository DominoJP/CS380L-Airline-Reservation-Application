import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

/**
   //FIXME: ENTER TEXT HERE
   @author
   @verison
*/

public class TravellerInformationPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textName;
	private JTextField textDoB;
	String[] DepartmentofStateGenderMarkerArray = {
			"Male (M)",
			"Female (F)",
			"Unspecified (X)",
			"Undisclosed (U)"
	};

	public TravellerInformationPane(JPanel contentPane) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{244, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPassengerNumber = new JLabel(" Passenger 1");
		GridBagConstraints gbc_lblPassengerNumber = new GridBagConstraints();
		gbc_lblPassengerNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPassengerNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerNumber.gridx = 0;
		gbc_lblPassengerNumber.gridy = 0;
		add(lblPassengerNumber, gbc_lblPassengerNumber);
		//FIXME: GET TRAVELLER COUNT FROM FlightFilterPane
		
		JLabel lblName = new JLabel(" Name (as appears on photo ID)");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);
		
		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 0;
		gbc_textName.gridy = 3;
		add(textName, gbc_textName);
		textName.setColumns(10);
		
		JLabel lblDoB = new JLabel(" Date of Birth (mm/dd/yyyy)");
		GridBagConstraints gbc_lblDoB = new GridBagConstraints();
		gbc_lblDoB.anchor = GridBagConstraints.WEST;
		gbc_lblDoB.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoB.gridx = 0;
		gbc_lblDoB.gridy = 4;
		add(lblDoB, gbc_lblDoB);
		
		JLabel lblGender = new JLabel(" Gender (as listed on photo ID)");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 2;
		gbc_lblGender.gridy = 4;
		add(lblGender, gbc_lblGender);
		
		textDoB = new JTextField();
		GridBagConstraints gbc_textDoB = new GridBagConstraints();
		gbc_textDoB.insets = new Insets(0, 0, 5, 5);
		gbc_textDoB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDoB.gridx = 0;
		gbc_textDoB.gridy = 5;
		add(textDoB, gbc_textDoB);
		textDoB.setColumns(10);
		
		JComboBox comboBox = new JComboBox(DepartmentofStateGenderMarkerArray);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		add(comboBox, gbc_comboBox);
		
		JLabel lblDoBInvalidDateFormat = new JLabel("Invalid date format.");
		lblDoBInvalidDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDoBInvalidDateFormat.setForeground(Color.RED);
		GridBagConstraints gbc_lblDoBInvalidDateFormat = new GridBagConstraints();
		gbc_lblDoBInvalidDateFormat.insets = new Insets(0, 0, 0, 5);
		gbc_lblDoBInvalidDateFormat.gridx = 0;
		gbc_lblDoBInvalidDateFormat.gridy = 6;
		add(lblDoBInvalidDateFormat, gbc_lblDoBInvalidDateFormat);
		lblDoBInvalidDateFormat.setVisible(false);
		
		
	}

}
