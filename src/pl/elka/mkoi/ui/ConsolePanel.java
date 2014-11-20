package pl.elka.mkoi.ui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.DefaultStyledDocument;

/**
 * Console panel, on which calculations will be logged. Uses {@link ConsoleTextPane} as text pane to log on.
 *
 */
public class ConsolePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Welcome message shown at application start.
	 */
	private static final String WELCOME = "Welcome in Baby Step - Giant Step algorithm preview.\n"
			+ "Input proper values and run calculation.\n";
	
	/**
	 * {@link ConsoleTextPane} used as text pane to log on 
	 */
	private ConsoleTextPane console;
	/**
	 * Scroll pane, which makes scrolling on {@link #console} possible.
	 */
	private JScrollPane scrollPane;
	
	/**
	 * Instance of this class, as it is singleton.
	 */
	private static ConsolePanel consolePanel;
	/**
	 * Mutex used as synchronization object in {@link #getInstance()}
	 */
	private static Object panelMutex = new Object();
	
	/**
	 * Create the panel.
	 */
	private ConsolePanel() {
		this.setVisible(true);
		console = new ConsoleTextPane(new DefaultStyledDocument());
		printWelcome();
		scrollPane = new JScrollPane(console);
		this.setLayout(new GridLayout(1,1));
		add(scrollPane);
	}
	
	/**
	 * Log {@link #WELCOME} message
	 */
	private void printWelcome() {
		console.appendWelcome(WELCOME);
	}
	
	/**
	 * Clear console text pane. Removes all characters.
	 */
	public void clear() {
		console.clear();
	}
	
	/**
	 * @return console text pane {@link #console}
	 */
	public ConsoleTextPane getConsole() {
		return console;
	}
	
	/**
	 * @return instance of this class as it is singleton.
	 */
	public static ConsolePanel getInstance() {
		synchronized (panelMutex) {
			if (consolePanel == null) {
				consolePanel = new ConsolePanel();
			}
			return consolePanel;
		}
	}
}
