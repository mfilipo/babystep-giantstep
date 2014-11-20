package pl.elka.mkoi.logger;

/**
 * Console logging interface.
 *
 */
public interface ConsoleLogger {
	
	/**
	 * Log text using simple style.
	 * 
	 * @param text message to log
	 */
	void append(String text);
	
	/**
	 * Log text using error style.
	 * 
	 * @param text message to log
	 */
	void appendError(String text);
	
	/**
	 * Log text using important (wyrozniony) style
	 * 
	 * @param text message to log
	 */
	void appendImportant(String text);
}
