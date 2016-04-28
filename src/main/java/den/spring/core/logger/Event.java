package den.spring.core.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class Event {
    private int id;

    private String msg;

    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getId() {
        Random r = new Random();
        return r.nextInt(100);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + getId() +
                ", msg='" + getMsg() + '\'' +
                ", date=" + df.format(date) +
                '}';
    }

    public boolean isDay(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh");
        return Integer.parseInt(dateFormat.format(dateFormat)) > 11;
    }

}
