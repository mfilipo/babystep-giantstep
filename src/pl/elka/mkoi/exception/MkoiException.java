package pl.elka.mkoi.exception;

import static pl.elka.mkoi.utils.MessageUtil.getLocaleMessage;

import org.apache.log4j.Logger;
/** 
 * MKOI project exception. It inherits from {@link RuntimeException}
 *
 */
public abstract class MkoiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Logger
	 */
	protected static Logger LOG = Logger.getLogger(MathException.class);
	
	/**
	 * Empty constructor. Creates instance of class. Generates default message.
	 */
	public MkoiException() {
		super();
		this.printStackTrace();
	}

	/**
	 * Constructor. Creates instance of class. Generates custom message.
	 * @param message message to show on exception
	 */
	public MkoiException (String message) {
		super(message);
		this.printStackTrace();	
	}
	
	/**
	 * Message parameters passed as list of objects.
	 * @return message parameters
	 */
	protected abstract Object[] getMessageObjects();
	
	/**
	 * Overriden method {@link RuntimeException#getMessage()}.
	 * {@inheritDoc}
	 * Creates exception message. If any parameters are passed then creates message with putted in parameters.
	 */
	@Override
	public final String getMessage() {
		final String clss = this.getClass().getName();
		final Object[] messageObjects = getMessageObjects();
		if (messageObjects != null && messageObjects.length > 0) {
			return getLocaleMessage(clss, messageObjects);
		} else {
			return getLocaleMessage(clss);
		}
	}
}
