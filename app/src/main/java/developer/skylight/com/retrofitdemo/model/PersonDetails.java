package developer.skylight.com.retrofitdemo.model;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public class PersonDetails {

    private String person_name;
    private String person_image;
    private String person_lat;
    private String person_long;

    private String people_id;

    public String getPeople_id() {
        return people_id;
    }

    public void setPeople_id(String people_id) {
        this.people_id = people_id;
    }

    public String getPeople_name() {
        return person_name;
    }

    public void setPeople_name(String people_name) {
        this.person_name = people_name;
    }

    public String getPeople_image() {
        return person_image;
    }

    public void setPeople_image(String people_image) {
        this.person_image = people_image;
    }

    public String getPeople_lat() {
        return person_lat;
    }

    public void setPeople_lat(String people_lat) {
        this.person_lat = people_lat;
    }

    public String getPeople_long() {
        return person_long;
    }

    public void setPeople_long(String people_long) {
        this.person_long = people_long;
    }
}
