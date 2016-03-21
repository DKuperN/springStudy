package den.annotationconfiguration;

import den.xmlconfiguration.Client;
import den.xmlconfiguration.logger.Event;
import den.xmlconfiguration.logger.EventLogger;
import den.xmlconfiguration.logger.EventType;
import den.xmlconfiguration.logger.impl.ConsoleEventLogger;
import den.xmlconfiguration.logger.impl.FileEventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dzianis_Kupryianchyk on 21-Mar-16.
 */
@Configuration
@Component
@ComponentScan(basePackages = {"den"})
@PropertySource({"classpath:client.properties", "classpath:test.properties"})
class AppConfig {

    @Autowired (required = true)
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

    @Value("log1.txt")
    private String filename;
    @Bean
    public FileEventLogger fileEventLogger(){
        return new FileEventLogger(filename);
    }

    @Bean
    public AnnotationApp annotationApp(){
        Map<EventType, EventLogger> loggerMap = new HashMap<EventType, EventLogger>();
        return new AnnotationApp(eventLogger, client(), loggerMap);
    }

    @Bean
    public ConsoleEventLogger consoleEventLogger(){
        return new ConsoleEventLogger();
    }

}