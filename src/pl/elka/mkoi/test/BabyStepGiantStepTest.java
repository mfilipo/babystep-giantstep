package pl.elka.mkoi.test;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.elka.mkoi.exception.SolutionNotExistException;
import pl.elka.mkoi.math.BabyStepGiantStep;

/**
 * Test class for {@link BabyStepGiantStep}
 *
 */
public class BabyStepGiantStepTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private BabyStepGiantStep bsgs = new BabyStepGiantStep();

	@Test
	public void testCalculateSuccess() {
		exception = ExpectedException.none();
		BigInteger solution = bsgs.calculate(new BigInteger("6"), new BigInteger("41"), new BigInteger("5"));
		assertEquals(new BigInteger("22"), solution);
		solution = bsgs.calculate(new BigInteger("2"), new BigInteger("29"), new BigInteger("3"));
		assertEquals(new BigInteger("5"), solution);
		solution = bsgs.calculate(new BigInteger("3"), new BigInteger("101"), new BigInteger("37"));
		assertEquals(new BigInteger("24"), solution);
	}
	
	@Test
	public void testCalculateFail() {
		try {
			bsgs.calculate(new BigInteger("19"), new BigInteger("21"), new BigInteger("12"));
			fail("Exception expected. Solution should not exist.");
		} catch (SolutionNotExistException e) {
		} catch (Exception ex) {
			fail("Not expected exception");
		}
		try {
			bsgs.calculate(new BigInteger("12"), new BigInteger("21"), new BigInteger("21"));
			fail("Exception expected. Solution should not exist.");
		} catch (SolutionNotExistException e) {
		} catch (Exception ex) {
			fail("Not expected exception");
		}
	}

	@Test
	public void testNumberFormatException() {
		exception.expect(NumberFormatException.class);
		bsgs.calculate(new BigInteger("124"), new BigInteger("-12-4"), new BigInteger("124"));
	}

	@Test
	public void testSqrt() {
		BigInteger nine = new BigInteger("9");
		assertEquals(new BigInteger("3"), bsgs.sqrt(nine));
		BigInteger seven = new BigInteger("7");
		assertEquals(new BigInteger("3"), bsgs.sqrt(seven));
	}

}
