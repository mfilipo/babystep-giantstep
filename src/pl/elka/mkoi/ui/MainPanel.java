package pl.elka.mkoi.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Main panel class. Used only to place main GUI objects - {@link ConsolePanel}, {@link StatusPanel} and {@link ConsolePanel}
 *
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * {@link ConsolePanel}
	 */
	private final ConsolePanel console;
	/**
	 * Create the panel.
	 */
	public MainPanel() {
		setLayout(new BorderLayout(0, 0));
		
		console = ConsolePanel.getInstance();
		add(console, BorderLayout.CENTER);
		
		add(StatusPanel.getInstance(), BorderLayout.SOUTH);
		
		add(new ControlPanel(), BorderLayout.EAST);
		setVisible(true);
	}
}
