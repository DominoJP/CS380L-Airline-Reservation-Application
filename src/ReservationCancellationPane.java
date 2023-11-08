import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ReservationCancellationPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public ReservationCancellationPane() {

	}

}
	private JTextArea dataTextArea;
	private String reservationFilePath;
	private CancelReservation cancelReservation;
	private JPanel ReservationCancellationPane;

	public ReservationCancellationPane(JPanel contentPane, CancelReservation cancelReservation) {
		this.cancelReservation = cancelReservation;
		
		setLayout(new BorderLayout(0, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{78, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
	ReservationCancellationPane = new JPanel();

	}
	
	
	 /**
     * Load data from the file and display it in the text area
     */
    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.reservationFilePath))) {
            String line;
            StringBuilder data = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
            dataTextArea.setText(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        
	

}