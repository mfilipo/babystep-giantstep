package pl.elka.mkoi.ui;

import java.awt.Dimension;

/**
 * Enum containing default GUI objects sizes. Used to standarize sizes.
 */
public enum DefaultSizes {
	BUTTON(100,35),
	TEXTFIELD(185,25),
	LABEL(120,20)
	;
	
	/**
	 * Default {@link Dimension} of GUI object.
	 */
	private Dimension dimension;
	
	/**
	 * Enum constructor
	 * @param width object width
	 * @param height object height
	 */
	private DefaultSizes(int width, int height) {
		this.dimension = new Dimension(width, height);
	}
	
	/**
	 * @return efault {@link Dimension} of GUI object.
	 */
	public Dimension getDimension() {
		return dimension;
	}
}
