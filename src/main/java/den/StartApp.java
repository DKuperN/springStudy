package den;

import den.logger.Event;
import den.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class StartApp {
    private EventLogger eventLogger;
    private Event event;
    private Client client;
    private String logMessage;

    public StartApp(EventLogger eventLogger, Client client, String msg) {
        this.eventLogger = eventLogger;
        this.client = client;
        this.logMessage = msg;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StartApp app = (StartApp) context.getBean("mainApplication");
        app.event = (Event) context.getBean("event");

        app.logEvent(app.logMessage);

        context.close();
    }

    private void logEvent(String msg) {
        String str = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(str);
        eventLogger.logEvent(event);
    }

}
