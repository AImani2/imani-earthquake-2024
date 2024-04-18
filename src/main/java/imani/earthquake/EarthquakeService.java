package imani.earthquake;

import imani.earthquake.json.FeatureCollection;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface EarthquakeService {

    // each service has a factory

    @GET("/earthquakes/feed/v1.0/summary/1.0_hour.geojson")
    Single<FeatureCollection> oneHour();

}

