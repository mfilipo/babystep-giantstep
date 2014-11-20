package pl.elka.mkoi.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class used to get localized messages.
 * Messages are placed in <i>resources/</i> folder.
 *
 */
public class MessageUtil {
	
	/**
	 * System bundle name.
	 */
	private static final String BUNDLE_NAME = "Messages";
	/**
	 * Messages bundle.
	 */
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());

	//cannot create instance of utility class
	private MessageUtil() {
    }
	
	/**
	 * Obtains message from specified key. If not found returns key value.
	 * @param key message key
	 * @return message
	 */
	public static String getLocaleMessage(String key) {
		try {
			return BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}
	
	/**
	 * Obtains message with parameters from specified key. If not found returns key value.
	 * @param key message key
	 * @param params message parameters
	 * @return message
	 */
	public static String getLocaleMessage(String key, Object... params) {
		try {
			return MessageFormat.format(BUNDLE.getString(key), params);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}
}
