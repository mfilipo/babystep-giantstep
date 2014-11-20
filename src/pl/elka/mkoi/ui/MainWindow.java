package pl.elka.mkoi.ui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import pl.elka.mkoi.logger.ConsoleAppender;

/**
 * Main application window. It contains {@link #main} method which is application starting method.
 *
 */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Log4J logger.
	 */
	private static Logger LOG = Logger.getLogger(MainWindow.class);
	
	/**
	 * Initial ({@value}px) main window width.
	 */
	static final int WIDTH = 650;

	/**
	 * Initial ({@value}px) main window height.
	 */
	static final int HEIGHT = 600;

	/**
	 * Minimum width ({@value}px) of main window.
	 */
	static final int MIN_WIDTH = 350;

	/**
	 * Minimum height ({@value}px) of main window.
	 */
	static final int MIN_HEIGHT = 350;
	
	/**
	 * Relative path to Log4J configuration file.
	 */
	private static final String LOG_CONFIG = "config/log4j.properties";
	
	/**
	 * Launch the application.
	 * @param args application starting args. Not supported.
	 */
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure(LOG_CONFIG);
		} catch (Exception e) {
			BasicConfigurator.configure();
			BasicConfigurator.configure(new ConsoleAppender());
			LOG.warn("Logger configuration file not found under \""+LOG_CONFIG+"\". Using default configuration");
		}
		
		EventQueue.invokeLater(() -> {
			try {
				new MainWindow();
				LOG.trace("Application started");
			} catch (Exception e) {
				LOG.fatal(e.getStackTrace().toString());;
			}
		});
	}

	/**
	 * Create the application window.
	 */
	public MainWindow() {
		initialize();
		add(new MainPanel());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 200, WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setTitle("Baby Step - Giant Step");
		this.setResizable(false);
	}

}
