package den.logger.impl;

import den.logger.Event;
import den.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public void logEvent(Event even) {
        try {
            FileUtils.writeStringToFile(file, even.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        file.canWrite();
    }

}
