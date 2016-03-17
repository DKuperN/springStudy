package den;

/**
 * Created by Dzianis_Kupryianchyk on 14-Mar-16.
 */
public class Client {
    private int id;
    private String fullName;
    private String greeting; //for testing of setting field by <property> tag in bean

    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //for testing of setting field by <property> tag in bean
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
