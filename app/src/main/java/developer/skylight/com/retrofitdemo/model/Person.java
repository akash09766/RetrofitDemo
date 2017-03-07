package developer.skylight.com.retrofitdemo.model;

import java.util.ArrayList;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public class Person {

    private String status;
    private ArrayList<PersonDetails> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PersonDetails> getResult() {
        return result;
    }

    public void setResult(ArrayList<PersonDetails> result) {
        this.result = result;
    }
}
