package den.spring.core.logger.impl;

import den.spring.core.logger.Event;
import den.spring.core.logger.EventLogger;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event){
        System.out.println("*** " + event.toString() + " ***");
    }
}
