/**
 * 
 */
package pl.elka.mkoi.ui;

import java.math.BigInteger;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import org.apache.log4j.Logger;

import pl.elka.mkoi.utils.NumericDocumentFilter;

/**
 * Class used as user input form of {@link BigInteger}. 
 *
 */
public class BigIntegerTextField extends JTextField {

	/**
	 * Log4J logger
	 */
	private static final Logger LOG = Logger.getLogger(BigIntegerTextField.class);
	
	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor. Creates single instance of {@link BigIntegerTextField}
	 * @param allowNegative true if negative values are allowed, else false.
	 */
	 public BigIntegerTextField(boolean allowNegative) {
		this.setRequestFocusEnabled(true);
		 ((AbstractDocument) this.getDocument()).setDocumentFilter(new NumericDocumentFilter(allowNegative));		 
	 }
	 
	 /**
	  * Returns value stored in {@link BigIntegerTextField}
	  * @return value as {@link BigInteger}
	  */
	 public BigInteger getValue() {
		 String txt = this.getText();
		 if (txt == null || txt.trim().length() == 0) {
			 LOG.info("Value is null");
			 return null;
		 }
		 return new BigInteger(this.getText());//this.getText() != null && this.getText().trim() != "" ? new BigInteger(this.getText()) : null;
	 }
}