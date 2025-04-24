package Components;

import java.util.Date;

public class Page {
    private int value;

    private Date lastAccess;

    private int uses;

    public Page(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
