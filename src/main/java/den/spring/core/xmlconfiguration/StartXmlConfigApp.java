package den.spring.core.xmlconfiguration;

import den.spring.core.logger.Event;
import den.spring.core.logger.EventLogger;
import den.spring.core.logger.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class StartXmlConfigApp {
    private EventLogger defaultLogger;
    private Event event;
    private EventType et;
    private Client client;
    private String[] logMessage = {"ERROR message user 1", "SIMPLE message user 1", "INFO message user 1", "TEST message user 1"};
    private Map<EventType, EventLogger> loggers;

    public StartXmlConfigApp(EventLogger eventLogger, Client client, Map<EventType, EventLogger> loggers) {
        this.defaultLogger = eventLogger;
        this.client = client;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StartXmlConfigApp app = (StartXmlConfigApp) context.getBean("mainApplication");
        app.event = (Event) context.getBean("event");

        for (String aLogMessage : app.logMessage) {
            if (aLogMessage.contains(EventType.ERROR.toString())) {
                app.et = EventType.ERROR;
            } else if (aLogMessage.contains(EventType.INFO.toString())) {
                app.et = EventType.INFO;
            } else {
                app.et = null;
            }
            app.logEvent(aLogMessage, app.et);
        }

        context.close();
    }

    private void logEvent(String msg, EventType type) {
        String str = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(str);
        EventLogger logger = loggers.get(type);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

}
