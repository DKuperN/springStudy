package den.spring.core.annotationconfiguration;

import den.spring.core.logger.Client;
import den.spring.core.logger.Event;
import den.spring.core.logger.EventLogger;
import den.spring.core.logger.EventType;
import den.spring.core.logger.impl.CombinedEventLogger;
import den.spring.core.logger.impl.ConsoleEventLogger;
import den.spring.core.logger.impl.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Dzianis_Kupryianchyk on 21-Mar-16.
 */
@Configuration
@Component
@ComponentScan(basePackages = {"den"})
@PropertySource({"classpath:client.properties", "classpath:test.properties"})
class AppConfig {

    @Autowired(required = true)
    @Qualifier("consoleEventLogger")
    private EventLogger eventLogger;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${id}")
    private String id;
    @Value("${app.userName}")
    private String un;
    @Bean
    public Client client(){
        return new Client(Integer.parseInt(id), un);
    }

    @Bean
    @Scope("prototype")
    public Event event(){
        return new Event(new Date(), java.text.DateFormat.getDateTimeInstance());
    }

    @Value("${logFileName}")
    private String filename;
    @Bean
    public FileEventLogger fileEventLogger(){
        return new FileEventLogger(filename);
    }

    @Bean
    public StartAnnotationConfigApp annotationApp(){
        Map<EventType, EventLogger> loggerMap = new HashMap<EventType, EventLogger>();
        loggerMap.put(EventType.INFO, consoleEventLogger());
        loggerMap.put(EventType.ERROR, combinedEventLogger());
        return new StartAnnotationConfigApp(eventLogger, client(), loggerMap);
    }

    @Bean
    public CombinedEventLogger combinedEventLogger(){
        Collection<EventLogger> loggers = new ArrayList<EventLogger>();
        loggers.add(consoleEventLogger());
        loggers.add(fileEventLogger());

        return new CombinedEventLogger(loggers);
    }
    @Bean
    public ConsoleEventLogger consoleEventLogger(){
        return new ConsoleEventLogger();
    }

}
