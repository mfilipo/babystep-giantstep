/**
 * 
 */
package pl.elka.mkoi.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * Status panel used to show current application status. There are
 * three statuses defined:
 * <ul>
 * 	<li>Ready</li>
 * 	<li>Busy</li>
 * 	<li>Error</li>
 * </ul>
 * Status panel is singleton. To obtain instance of this class
 * use {@link #getInstance()}.
 */
public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Status label
	 */
	private final JLabel status;
	
	/**
	 * Instance of {@link StatusPanel}, as it is singleton 
	 */
	private static StatusPanel statusPanel;
	
	/**
	 * Mutex object used for synchronization in {@link #getInstance()}
	 */
	private static Object panelMutex = new Object();
	
	/**
	 * Enum defining used statuses.
	 *
	 */
	private enum Status {
		READY("Ready"),
		BUSY("Busy"),
		ERROR("Error")
		;
		
		/**
		 * Status name
		 */
		private String status;
		private Status(String status) {
			this.status = status;
		}
		/**
		 * @return {@link #status}
		 */
		public String getStatus() {
			return status;
		}
	}
	
	/**
	 * Private constructor, as it is singleton
	 */
	private StatusPanel() {
		this.setLayout(new GridLayout(1, 1));
		this.setPreferredSize(new Dimension(0,18));
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		status = new JLabel(Status.READY.getStatus());
		status.setVisible(true);
		status.setForeground(Color.DARK_GRAY);
		status.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(status);
	}
	/**
	 * Set {@link StatusPanel.Status#READY} status.
	 */
	public void setReady() {
		status.setText(Status.READY.getStatus());
	}
	/**
	 * Set {@link StatusPanel.Status#READY} status along with custom message.
	 * @param message custom message
	 */
	public void setReady(String message) {
		status.setText(Status.READY.getStatus() + " - " + message);
	}
	
	/**
	 * Set {@link StatusPanel.Status#BUSY} status.
	 */
	public void setBusy() {
		status.setText(Status.BUSY.getStatus());
	}
	
	/**
	 * Set {@link StatusPanel.Status#BUSY} status along with custom message.
	 * @param message custom message
	 */
	public void setBusy(String message) {
		status.setText(Status.BUSY.getStatus() + " - " + message);
	}
	
	/**
	 * Set {@link StatusPanel.Status#ERROR} status along with custom message.
	 */
	public void setError() {
		status.setText(Status.ERROR.getStatus());
	}
	
	/**
	 * Set {@link StatusPanel.Status#ERROR} status.
	 * @param message custom message
	 */
	public void setError(String message) {
		status.setText(Status.ERROR.getStatus() + " - " + message);
	}
	
	/**
	 * It returns instance of this class, as it is singleton.
	 * @return instance of {@link StatusPanel}
	 */
	public static StatusPanel getInstance() {
		synchronized(panelMutex) {
			if (statusPanel == null) {
				statusPanel = new StatusPanel();
			}
			return statusPanel;
		}
	}
}
