package imani.earthquake;

// this will give us an earthquake service
// this is where the magic happens...

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthquakeServiceFactory {

    public EarthquakeService getService() {

        // use retrofit to give us the service
        // creating a builder and a retrofit object - will always be the same
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                // Configure Retrofit to use Gson to turn the Json into Objects
                .addConverterFactory(GsonConverterFactory.create())
                // Configure Retrofit to use Rx
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(EarthquakeService.class);
    }
}




