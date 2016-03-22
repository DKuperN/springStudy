package den.xmlconfiguration.logger.impl;

import den.xmlconfiguration.logger.Event;
import den.xmlconfiguration.logger.EventLogger;

import java.util.Collection;

/**
 * Created by Dzianis_Kupryianchyk on 17-Mar-16.
 */
public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for(EventLogger logger : loggers){
            logger.logEvent(event);
        }

    }
}
