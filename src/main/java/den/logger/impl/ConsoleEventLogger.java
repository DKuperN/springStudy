package den.logger.impl;

import den.logger.Event;
import den.logger.EventLogger;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event){
        System.out.println("*** " + event.toString() + " ***");
    }
}
