package den.spring.core.annotationconfiguration;

import den.spring.core.logger.Client;
import den.spring.core.logger.Event;
import den.spring.core.logger.EventLogger;
import den.spring.core.logger.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Created by Dzianis_Kupryianchyk on 21-Mar-16.
 */
public class StartAnnotationConfigApp {

    private Map<EventType, EventLogger> loggers;
    @Autowired
    private Event event;
    private EventType eventType;
    @Autowired
    private Client client;
    private String[] logMessage = {"ERROR message user 1", "SIMPLE message user 1", "INFO message user 1", "TEST message user 1"};

//    @Autowired
//    @Qualifier("consoleEventLogger")
    private EventLogger defaultLogger;

    @Autowired
    public StartAnnotationConfigApp(EventLogger eventLogger, Client client, Map<EventType, EventLogger> loggers) {
        this.defaultLogger = eventLogger;
        this.client = client;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        StartAnnotationConfigApp app = (StartAnnotationConfigApp) ctx.getBean("annotationApp");
        for (String aLogMessage : app.logMessage) {
            if (aLogMessage.contains(EventType.ERROR.toString())) {
                app.eventType = EventType.ERROR;
            } else if (aLogMessage.contains(EventType.INFO.toString())) {
                app.eventType = EventType.INFO;
            } else {
                app.eventType = null;
            }
            app.logEvent(aLogMessage, app.eventType);
        }

    }

    private void logEvent(String msg, EventType eventType){
        String str = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(str);
        EventLogger logger = loggers.get(eventType);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
