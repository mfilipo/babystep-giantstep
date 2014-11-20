package pl.elka.mkoi.ui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * Custom {@link JTextPane} used to log calculations and other important informations on.
 */
public class ConsoleTextPane extends JTextPane {

	private static final long serialVersionUID = 1L;

	/**
	 * Font styles
	 *
	 */
	private enum StyleType {
		/**
		 * Basic style.
		 * Font family - {@link ConsoleTextPane#FONT_FAMILY}
		 * Font size - {@link ConsoleTextPane#FONT_SIZE}
		 * Color - black
		 */
		BASE,
		/**
		 * Error style.
		 * Font family - {@link ConsoleTextPane#FONT_FAMILY}
		 * Font size - {@link ConsoleTextPane#FONT_SIZE}
		 * Color - red
		 * Bold
		 */
		ERROR,
		/**
		 * Defalt style.
		 * Font family - {@link ConsoleTextPane#FONT_FAMILY}
		 * Font size - {@link ConsoleTextPane#FONT_SIZE}
		 * Color - blue
		 * Bold
		 */
		WELCOME,
		/**
		 * Defalt style.
		 * Font family - {@link ConsoleTextPane#FONT_FAMILY}
		 * Font size - {@link ConsoleTextPane#FONT_SIZE}
		 * Color - black
		 * Bold
		 * 
		 */
		DISTINGUISHED
	}
	
	/**
	 * Default font size - {@value}px
	 */
	private static final int FONT_SIZE = 12;
	/**
	 * Default font family - {@value}
	 */
	private static final String FONT_FAMILY = "Verdana";
	
	/**
	 * Map containing font styles
	 */
	private static final Map<StyleType, Style> styles = new HashMap<>();
	
	static {
		StyleContext context = new StyleContext();
		Style baseStyle = context.addStyle(StyleType.BASE.name(), null);
		StyleConstants.setFontFamily(baseStyle, FONT_FAMILY);
		StyleConstants.setFontSize(baseStyle, FONT_SIZE);
		StyleConstants.setForeground(baseStyle, Color.BLACK);
		styles.put(StyleType.BASE, baseStyle);
		
		Style style = context.addStyle(StyleType.ERROR.name(), baseStyle);
		StyleConstants.setForeground(style, Color.RED);
		StyleConstants.setBold(style, true);
		styles.put(StyleType.ERROR, style);
		
		style = context.addStyle(StyleType.WELCOME.name(), baseStyle);
		StyleConstants.setForeground(style, new Color(20, 50, 150));
		StyleConstants.setBold(style, true);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_RIGHT);
		styles.put(StyleType.WELCOME, style);
		
		style = context.addStyle(StyleType.DISTINGUISHED.name(), baseStyle);
		StyleConstants.setBold(style, true);
		styles.put(StyleType.DISTINGUISHED, style);
	}
	
	/**
	 * {@link DefaultStyledDocument}
	 */
	private DefaultStyledDocument document;
	
	/**
	 * Constructor, which creates this text pane.
	 * @param document {@link DefaultStyledDocument}
	 */
	public ConsoleTextPane(DefaultStyledDocument document) {
		super(document);
		super.setEditable(false);
		setDoubleBuffered(true);
		this.document = document;
		clear();
	}
	
	/**
	 * Append text to this text pane.
	 * @param text text to append
	 * @param style {@link StyleType} to use
	 */
	private void append(String text, StyleType style) {
		try {
			document.insertString(document.getLength(), text+"\n", styles.get(style));
			this.setCaretPosition(document.getLength());
		} catch(BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Clear text pane. Removes all characters.
	 */
	public void clear() {
		this.setText("");
	}
	
	/**
	 * Appends text using {@link StyleType#WELCOME} style.
	 * @param text text to append
	 */
	public void appendWelcome(String text) {
		append(text, StyleType.WELCOME);
	}

	/**
	 * Appends text using {@link StyleType#BASE} style.
	 * @param text text to append
	 */
	public void append(String text) {
		append(text, StyleType.BASE);
	}

	/**
	 * Appends text using {@link StyleType#ERROR} style.
	 * @param text text to append
	 */
	public void appendError(String text) {
		append(text, StyleType.ERROR);
	}

	/**
	 * Appends text using {@link StyleType#DISTINGUISHED} style.
	 * @param text text to append
	 */
	public void appendImportant(String text) {
		append(text, StyleType.DISTINGUISHED);
	}
	
	/**
	 * Empty setter, because this panel is read-only - user can't edit it.
	 */
	public void setEditable(boolean value) { }//LOG -> not possible
}
