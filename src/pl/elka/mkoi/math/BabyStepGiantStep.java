/**
 * 
 */
package pl.elka.mkoi.math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import pl.elka.mkoi.exception.DiscreteLogarithmException;
import pl.elka.mkoi.exception.MathException;
import pl.elka.mkoi.exception.SolutionNotExistException;
import pl.elka.mkoi.logger.ConsoleLogger;
import pl.elka.mkoi.logger.EmptyConsoleLogger;
import pl.elka.mkoi.math.base.BigIntDiscreteLogarithm;
import pl.elka.mkoi.math.base.DiscreteLogarithm;
import pl.elka.mkoi.ui.ConsolePanel;

/**
 * Class used to solve discrete algorithm problem using
 * Baby Step - Giant Step algorithm.
 *
 */
public class BabyStepGiantStep extends BigIntDiscreteLogarithm {
	
	/**
	 * {@link EmptyConsoleLogger} is used - 
	 * no calculations will be logged on {@link ConsolePanel}
	 */
	public BabyStepGiantStep() {
		super();
	}

	/**
	 * Custom {@link ConsoleLogger} is used to log calculations on {@link ConsolePanel}.
	 * If <i>logger</i> is null, then {@link EmptyConsoleLogger} will be used.
	 * @param logger {@link ConsoleLogger}
	 */
	public BabyStepGiantStep(ConsoleLogger logger) {
		super(logger);
	}
	
	/**
	 * Calculates baby steps, defined as:
	 * a(i) = x*g^i (mod p), where i=0,1...,maxIteratorValue and maxIteratorValue = ceil(sqrt(modulus))-1
	 * p is modulus
	 * x is result
	 * g is logarithm base
	 * @param babySteps array into which calculated baby steps will be added
	 * @param maxIteratorValue value used in for loop. It is defined as ceil(sqrt(modulus))-1
	 * @param modulus modulus (p)
	 * @param result result (x)
	 * @param logBase logarithm base (g)
	 */
	private void generateBabySteps(List<BigInteger> babySteps, BigInteger maxIteratorValue,
			BigInteger modulus, BigInteger result, BigInteger logBase) {
		forceLog("Computing baby steps which are:\na(i) = x*g^(i) (mod p), for i = 0,1,…"+maxIteratorValue+":");
		for (BigInteger i = ZERO; i.compareTo(maxIteratorValue) < 1; i = i.add(ONE)){
			BigInteger pow = logBase.modPow(i, modulus);
			BigInteger res = result.multiply(pow).mod(modulus);
			logCalculation("a("+i+") = " + res);
			babySteps.add(res);
		}
		forceLog("Baby steps generated.");
	}
	
	/**
	 * Method used to compare generated  baby steps (using {@link #generateBabySteps} with giant step.
	 * @param giantStep giant step defined as b(j) = g^(k*j) (mod p), created in {@link #calculate} for specific j.
	 * @param maxIteratorValue value used in for loop. It is defined as ceil(sqrt(modulus))-1
	 * @param babySteps list of baby steps  defined as a(i) = x*g^i (mod p), where i=0,1...,maxIteratorValue
	 * @return -1 if match was not found, else i (position of matched element in baby steps array)
	 */
	private BigInteger matchBabyStep(List<BigInteger> babySteps, BigInteger maxIteratorValue, BigInteger giantStep) {
			for (BigInteger i = ZERO; i.compareTo(maxIteratorValue) < 1; i = i.add(ONE)) {
				BigInteger babyStep = babySteps.get(i.intValue());
				logCalculation(babyStep + " = " + giantStep);
				if (babyStep.compareTo(giantStep) == 0) {
					return i;
				}
			}
			return MINUS_ONE;
	}
	/**
	 * Implemented method from interface {@link DiscreteLogarithm}. Perform discrete logarithm calculation.
	 * @param logBase logarithm base (g)
	 * @param modulus modulus (p)
	 * @param result result(x)
	 * 
	 * @return result of discrete logarithm problem calculation (a), which is log[g](x) = a (mod p).
	 * @throws MathException exception thrown on any calculation problem, i.e. solution not found, illegal argument etc.
	 */
	@Override
	public BigInteger calculate(BigInteger logBase, BigInteger modulus,
			BigInteger result) throws MathException {
		
		long time = System.currentTimeMillis();
		
		try {
			if (result.compareTo(modulus) == 0) {
				throw new ArithmeticException("If x = p then solution does not exist");
			}
//			if (result.compareTo(logBase) == 0) {
//				return ONE;
//			}
			
			forceLog("Calculating k = ceil(sqrt(p))");
			logCalculation("k = ceil(sqrt("+modulus+"))");
			final BigInteger k = sqrt(modulus);
			logCalculation("k = " + k);
			final BigInteger maxIteratorValue = subtractOne(k);
			final List<BigInteger> babySteps = new ArrayList<>();
			
			generateBabySteps(babySteps, maxIteratorValue, modulus, result, logBase);

			forceLog("Comparing baby steps with giant steps, which are\n"
					+ "b(j) = g^(k*j) (mod p), for j = 1,2,…"+maxIteratorValue);
			forceLog("if a(i) = b(j) then solution is: a = k*j - i");
			for (BigInteger j = ONE ; j.compareTo(maxIteratorValue) < 1 ; j = j.add(ONE)) {
				BigInteger multiplied = j.multiply(k);
				BigInteger giantStep = logBase.modPow(multiplied, modulus);
				BigInteger i = matchBabyStep(babySteps, maxIteratorValue, giantStep);
				if (i.compareTo(MINUS_ONE) > 0) {
					forceLog("matched: i = " + i);
					forceLog("matched: j = " + j);
					BigInteger sub = new BigInteger("-"+i.toString());
					return j.multiply(k).add(sub);
				}
			}
			throw new ArithmeticException();
		} catch (ArithmeticException | IllegalArgumentException e) {
			forceLog(e.getMessage());
			throw new SolutionNotExistException(logBase, modulus, result);
		} catch (Exception e) {
			throw new DiscreteLogarithmException();
		} finally {
			long tookTime = System.currentTimeMillis() - time;
			float t = ((float)tookTime)/1000;
			if (t < 1) {
				forceLog("Calculation done in " + tookTime + "ms");
			} else {
				forceLog("Calculation done in " + String.format("%.2f", t) + "s");
			}
		}
	}
}
