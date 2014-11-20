package pl.elka.mkoi.exception;

import java.math.BigInteger;

/**
 * Exception thrown when solution of discrete logarithm not exist (was not found in finite number of steps).
 *
 */
public class SolutionNotExistException extends DiscreteLogarithmException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Logarithm base.
	 */
	private BigInteger logBase;
	/**
	 * Logarithm modulus.
	 */
	private BigInteger modulus;
	/**
	 * Result of logarithm.
	 */
	private BigInteger result;
	
	/**
	 * Public constructor. Creates exception.
	 * @param logBase logarithm base
	 * @param modulus logarithm modulus
	 * @param result result of logarithm
	 */
	public SolutionNotExistException(BigInteger logBase, BigInteger modulus, BigInteger result) {
		this.logBase = logBase;
		this.modulus = modulus;
		this.result = result;
	}
	
	/**
	 * @return logarithm base
	 */
	public BigInteger getLogBase() {
		return logBase;
	}

	/**
	 * @return logarithm modulus
	 */
	public BigInteger getModulus() {
		return modulus;
	}
	
	/**
	 * @return result of logarithm
	 */
	public BigInteger getResult() {
		return result;
	}
	/**
	 * {@inheritDoc}
	 */
	
	@Override
	protected Object[] getMessageObjects() {
		return new Object[]{logBase, modulus, result};
	}
}
