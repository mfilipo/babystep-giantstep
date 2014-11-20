package pl.elka.mkoi.math.base;

import pl.elka.mkoi.exception.MathException;

/**
 * Interface used to calculate discrete logarithm.
 * Implementations can be various:
 * <ul>
 * 	<li>Baby step - Giant Step,</li>
 * 	<li>Trial Multiplication,</li>
 * 	<li>Index calculus</li>
 * 	<li>and other</li>
 * </ul>
 * Also used data types T can be different;
 *
 * @param <T> type used in discrete logarithm calculations
 */
public interface DiscreteLogarithm<T> {

	/**
	 * Perform discrete logarithm calculation.
	 * @param logBase logarithm base (g)
	 * @param modulus modulus (p)
	 * @param result result(x)
	 * 
	 * @return result of discrete logarithm problem calculation (a), which is log[g](x) = a (mod p).
	 * @throws MathException exception thrown on any calculation problem, i.e. solution not found, illegal argument etc.
	 */
	T calculate (T logBase, T modulus, T result) throws MathException ;
	
}
