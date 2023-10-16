import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
   JPanel that allows navigation between Reserving, Reviewing, or Canceling flights.
   @author
   @verison
*/

public class OptionSelectionPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public OptionSelectionPane(JPanel contentPane) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{137, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		String [] optionsArray = {
				"Reserve",
				"Review",
		};
		JComboBox comboBox = new JComboBox(optionsArray);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Reserve")) {
					((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER");
				}
			}
		});
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.insets = new Insets(0, 0, 5, 5);
		gbc_btnContinue.gridx = 0;
		gbc_btnContinue.gridy = 1;
		add(btnContinue, gbc_btnContinue);
		

	}

}
