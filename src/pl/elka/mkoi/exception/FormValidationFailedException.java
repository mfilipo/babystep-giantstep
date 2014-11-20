package pl.elka.mkoi.exception;

/**
 * User input forms validation exception. It inherits from {@link MkoiException}.
 *
 */
public class FormValidationFailedException extends MkoiException {

	private static final long serialVersionUID = 1L;

	public FormValidationFailedException() {
		super();
	}
	
	public FormValidationFailedException(String message) {
		super(message);
	}
	
	@Override
	protected Object[] getMessageObjects() {
		return null;
	}
}
