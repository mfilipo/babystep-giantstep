package pl.elka.mkoi.logger;

import org.apache.log4j.Logger;

/**
 * Console logger. Logs messages on console using Log4J logger - {@link Logger}
 * Implements {@link ConsoleLogger} interface.
 *
 */
public class CustomConsoleLogger implements ConsoleLogger {

	/**
	 * Log4J logger instance - {@link Logger}
	 */
	private final Logger logger;
	
	/**
	 * Creates console logger for specified class.
	 * @param clss {@link Class} for which logger will be applied.
	 */
	@SuppressWarnings("rawtypes")
	public CustomConsoleLogger(Class clss) {
		logger = Logger.getLogger(clss);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void append(String text) {
		logger.info(text);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void appendError(String text) {
		logger.warn(text);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void appendImportant(String text) {
		logger.debug(text);
	}

}
