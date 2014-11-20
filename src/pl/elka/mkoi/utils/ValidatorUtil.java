package pl.elka.mkoi.utils;

import pl.elka.mkoi.ui.BigIntegerTextField;

/**
 * Utility class used to validate user inputs.
 * 
 */
public class ValidatorUtil {
	
	//cannot create instance of utility class
	private ValidatorUtil() {
    }
	
	/**
	 * Checks if all of the objects are not null, else throws {@link IllegalArgumentException}
	 * @param objects objects to be validated [vararg]
	 * @throws IllegalArgumentException is thrown when any of the objects is null
	 */
	public static void validateNotNull(Object... objects) throws IllegalArgumentException {
		for (Object o : objects) {
			if (o == null) {
				throw new IllegalArgumentException("null value input");
			}
		}
	}
	
	/**
	 * Checks if all of the forms contains null or empty values.
	 * @param forms {@link BigIntegerTextField} objects to validate
	 * @return true if all forms are not null and not empty, else false
	 */
	public static boolean validateForms(BigIntegerTextField... forms) {
		for (BigIntegerTextField form : forms) {
			String text = form.getText();
			try {
				Double.parseDouble(text);
			} catch (NumberFormatException e) {
				return false;
			}
			if ( text == null || text.trim().length() == 0) {
				return false;
			}
		}
		return true;
	}
}
