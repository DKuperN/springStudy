package den.spring.core.logger.impl;

import den.spring.core.logger.Event;
import den.spring.core.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class FileEventLogger implements EventLogger {
    private File file;

    public FileEventLogger(String fileName) {
        this.file = new File(fileName);
    }

    public void logEvent(Event even) {
        try {
            FileUtils.writeStringToFile(file, even.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException {
        file.canWrite();
    }

}
