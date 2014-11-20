package pl.elka.mkoi.utils;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Filter, which can be used in text fields to prevent input
 * other than numerical. It can also prevent negative numbers.
 *
 */
public class NumericDocumentFilter extends DocumentFilter {
	
	/**
	 * Interface used as converter to convert user inputs.
	 * @author Mateusz
	 *
	 * @param <F> Class from which conversion will be made
	 * @param <T> Class to which conversion will be made
	 */
	@FunctionalInterface
	private interface Converter<F, T> {
		T convert(F from);
	}
	
	/**
	 * {@link Converter} from String to String.
	 * Will be used to prevent user input other than numerical.
	 */
	private Converter<String, String> converter;
	
	/**
	 * Create instance of this class. Developer can specify whether
	 * to allow user negative inputs.
	 * @param allowNegative true to allow negative numbers, else false.
	 */
	public NumericDocumentFilter(boolean allowNegative) {
		converter = (from) -> {
			if (allowNegative) {
				return from.replaceAll("[^0-9\\-]", "");
			} else {
				return from.replaceAll("\\D++", "");
			}
		};
	}
	/**
	 * Create instance of this class. Allows negative numerical inputs by default.
	 */
	public NumericDocumentFilter() {
		this(true);
	}
	
	/**
	 * Uses {@link #converter} to convert while inserting String.
	 * {@inheritDoc}
	 */
	@Override
	public void insertString(FilterBypass fb, int off, String str, AttributeSet attr) throws BadLocationException {
		fb.insertString(off, converter.convert(str), attr);
	}
	
	/**
	 * Uses {@link #converter} to convert while replacing String.
	 * {@inheritDoc}
	 */
	@Override
	public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr) throws BadLocationException {
		fb.replace(off, len, converter.convert(str), attr);
	}	
}


