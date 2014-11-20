/**
 * 
 */
package pl.elka.mkoi.math.base;

import java.math.BigInteger;

import pl.elka.mkoi.logger.ConsoleLogger;
import pl.elka.mkoi.logger.EmptyConsoleLogger;
import pl.elka.mkoi.ui.ConsolePanel;

/**
 * Abstract class implementing {@link DiscreteLogarithm}. It provides required methods
 * to solve discrete algorithm problem using big numbers.
 * Class {@link BigInteger} will be used in any calculations.
 */
public abstract class BigIntDiscreteLogarithm implements DiscreteLogarithm<BigInteger> {

	/**
	 * BigInteger value equal -1
	 */
	protected static final BigInteger MINUS_ONE = new BigInteger("-1");
	/**
	 * BigInteger value equal 0
	 */
	protected static final BigInteger ZERO = new BigInteger("0");
	/**
	 * BigInteger value equal 1
	 */
	protected static final BigInteger ONE = new BigInteger("1");
	/**
	 * BigInteger value equal 2
	 */
	protected static final BigInteger TWO = new BigInteger("2");
	
	private ConsoleLogger logger;
	private ConsoleLogger emptyLogger;
	private ConsoleLogger customLogger;
	
	/**
	 * {@link EmptyConsoleLogger} is used - 
	 * no calculations will be logged on {@link ConsolePanel}
	 */
	public BigIntDiscreteLogarithm() {
		emptyLogger = new EmptyConsoleLogger();
		logger = emptyLogger;
	}

	/**
	 * Custom {@link ConsoleLogger} is used to log calculations on {@link ConsolePanel}.
	 * If <i>logger</i> is null, then {@link EmptyConsoleLogger} will be used.
	 * @param logger {@link ConsoleLogger}
	 */
	public BigIntDiscreteLogarithm(ConsoleLogger logger) {
		this();
		if (logger != null) {
			this.customLogger = logger;
		}
	}
	
	/**
	 * Subtract one from BigInteger.
	 * @param m value to subtract
	 * @return subtraction result
	 */
	protected BigInteger subtractOne(BigInteger m) {
		return m.add(MINUS_ONE);
	}
	
	/**
	 * Calculate square root with ceil.
	 * @param x	number to calculate sqrt for
	 * @return square root with ceil
	 * @throws IllegalArgumentException thrown when input number is negative
	 */
	public BigInteger sqrt(BigInteger x)
	        throws IllegalArgumentException {
		if (x.compareTo(BigInteger.ZERO) < 0) {
	        throw new IllegalArgumentException("Negative argument.");
	    }
	    // square roots of 0 and 1 are trivial and
	    // y == 0 will cause a divide-by-zero exception
	    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
	        return x;
	    } // end if
	    BigInteger two = BigInteger.valueOf(2L);
	    BigInteger y;
	    // starting with y = x / 2 avoids magnitude issues with x squared
	    for (y = x.divide(two);
	            y.compareTo(x.divide(y)) > 0;
	            y = ((x.divide(y)).add(y)).divide(two));
	    if (x.compareTo(y.multiply(y)) == 0) {
	        return y;
	    } else {
	        return y.add(BigInteger.ONE);
	    }
	}

	/**
	 * Logging is forced using custom logger, despite {@link #logger} is not set as {@link #customLogger}
	 * @param text text to log
	 */
	protected void forceLog(String text) {
		if (customLogger != null) {
			customLogger.append(text);
		}
	}
	
	/**
	 * Log calculation using {@link #logger}. If logger is set as {@link #emptyLogger} then nothing is logged.
	 * If logger is set as {@link #customLogger} and {@link #customLogger} is not null, then text is being logged on
	 * {@link ConsolePanel}.
	 * @param text text to log on {@link ConsolePanel}. 
	 */
	protected void logCalculation(String text) {
		synchronized(logger) {
			logger.append(text);
		}
	}
	
	/**
	 * Enables or disables logging on {@link ConsolePanel}. If logging is enabled and {@link #customLogger} is not null,
	 * then text will logged, else not.
	 * @param showCalculation true to log on {@link ConsolePanel}, else false
	 */
	public synchronized void setShowCalculation(boolean showCalculation) {
		synchronized(logger) {
			if (showCalculation && customLogger != null) {
				logger = customLogger;
			} else {
				logger = emptyLogger;
			}
		}
	}
}
