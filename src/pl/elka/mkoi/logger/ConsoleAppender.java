package pl.elka.mkoi.logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import pl.elka.mkoi.ui.ConsolePanel;
import pl.elka.mkoi.ui.ConsoleTextPane;

/**
 * Log4J custom appender used to log on {@link ConsoleTextPane}.
 *
 */
public class ConsoleAppender extends AppenderSkeleton {

	/**
	 * Console to log on. Console is instance of {@link ConsoleTextPane}.
	 */
	private final ConsoleTextPane console = ConsolePanel.getInstance().getConsole();
	
	/**
	 * Empty constructor. Creates instance of ConsoleAppender.
	 */
	public  ConsoleAppender() { 
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		if (!performChecks() || event.getMessage() == null) {
            return;
		}
		String message = null;
        if(event.locationInformationExists()){
            StringBuilder formatedMessage = new StringBuilder();
            formatedMessage.append(event.getLocationInformation().getClassName());
            formatedMessage.append(".");
            formatedMessage.append(event.getLocationInformation().getMethodName());
            formatedMessage.append(":");
            formatedMessage.append(event.getLocationInformation().getLineNumber());
            formatedMessage.append(" - ");
            formatedMessage.append(event.getMessage().toString());
            message = formatedMessage.toString();
        } else{
            message = event.getMessage().toString();
        }

        switch(event.getLevel().toInt()){
        case Level.DEBUG_INT: 
        	console.appendImportant(message);
        	break;
        case Level.INFO_INT:
        	console.append(message);
        	break;
        case Level.WARN_INT:
            console.appendError(message);
            break;
        case Level.ERROR_INT:
        case Level.FATAL_INT:
        case Level.TRACE_INT:
        default:
            break;
        }
	}
	
	/**
	 * Checks if appender is closed or layout is null.
	 * @return true if appender is not close and layout is not null, else false.
	 */
    private boolean performChecks() {
        return !closed && layout != null; 
    }
}
