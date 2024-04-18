package imani.earthquake;

import imani.earthquake.json.FeatureCollection;
import imani.earthquake.json.Properties;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EarthquakeServiceTest {

    @Test
    public void oneHour() {
        // given
        EarthquakeService service = new EarthquakeServiceFactory().getService();

        // when
        FeatureCollection collection = service.oneHour().blockingGet();
        // this waits until the data is downloaded
        // everything in my program stops until the data comes back
        // it should only be used in tests otherwise my program will block up
        // use other code from schwimmer page for an application

        // then
        Properties properties = collection.features[0].properties;
        assertNotNull(properties.place);
        assertNotEquals(0, properties.mag);
        assertNotEquals(0, properties.time);

        // asserting that the properties are not null and mag and time are not equal to 0
    }

    @Test
    public void thirtyDays() {
        // given
        EarthquakeService service = new EarthquakeServiceFactory().getService();

        // when
        FeatureCollection collection = service.thirtyDays().blockingGet();
        // this waits until the data is downloaded
        // everything in my program stops until the data comes back
        // it should only be used in tests otherwise my program will block up
        // use other code from schwimmer page for an application

        // then
        Properties properties = collection.features[0].properties;
        assertNotNull(properties.place);
        assertNotEquals(0, properties.mag);
        assertNotEquals(0, properties.time);

        // asserting that the properties are not null and mag and time are not equal to 0
    }

}