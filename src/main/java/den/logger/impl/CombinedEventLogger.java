package den.logger.impl;

import den.logger.Event;
import den.logger.EventLogger;

import java.util.Collection;

/**
 * Created by Dzianis_Kupryianchyk on 17-Mar-16.
 */
public class CombinedEventLogger implements EventLogger {
    Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for(EventLogger logger : loggers){
            logger.logEvent(event);
        }

    }
}
