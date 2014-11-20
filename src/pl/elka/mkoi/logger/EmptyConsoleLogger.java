package pl.elka.mkoi.logger;

/**
 * Empty logger, which does not log anything.
 * Implements {@link ConsoleLogger} interface.
 *
 */
public class EmptyConsoleLogger implements ConsoleLogger {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void append(String text) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void appendError(String text) {

	}
	
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public void appendImportant(String text) {

	}

}
