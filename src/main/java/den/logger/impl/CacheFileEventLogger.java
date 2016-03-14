package den.logger.impl;

import den.logger.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;
    private Event event;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        cache = new ArrayList<Event>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        this.event = event;
        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    public void writeEventsFromCache(){
        super.logEvent(event);
    }
}
