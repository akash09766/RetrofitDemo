package developer.skylight.com.retrofitdemo.network;

import developer.skylight.com.retrofitdemo.model.Person;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Akash Wangalwar on 17-01-2017.
 */
public interface NetworkManager {
    @GET("android/getAllpeople.php?cmd=get")
    Call<Person> getPersonsList();
}
