import javax.swing.JPanel;
import java.awt.GridBagLayout;

public class TravellerInformationPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public TravellerInformationPane(JPanel contentPane) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
	}

}
