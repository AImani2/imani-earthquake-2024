package imani.earthquake.json;

import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import imani.earthquake.EarthquakeService;
import imani.earthquake.EarthquakeServiceFactory;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.function.Function;

public class EarthquakeFrame extends JFrame {
    JList<String> earthquakes = new JList<>();

    EarthquakeService service = new EarthquakeServiceFactory().getService();

    JRadioButton oneHourButton = new JRadioButton("Past Hour");
    JRadioButton thirtyDaysButton = new JRadioButton("Past Thirty Days");
    ButtonGroup buttonGroup = new ButtonGroup();

    public EarthquakeFrame() {

        setSize(300, 600);
        setTitle("Earthquake Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //JPanel main = new JPanel();
        setLayout(new BorderLayout());

        JPanel radioPanel = new JPanel(new FlowLayout());
        radioPanel.add(oneHourButton);
        radioPanel.add(thirtyDaysButton);
        add(radioPanel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JScrollPane(earthquakes), BorderLayout.CENTER);
        add(listPanel, BorderLayout.CENTER);

        buttonGroup.add(oneHourButton);
        buttonGroup.add(thirtyDaysButton);

        oneHourButton.setSelected(true); // Set default selection

        oneHourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch and display data for past hour
                fetchAndDisplayOneHourData();
            }
        });

        thirtyDaysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch and display data for past thirty days
                fetchAndDisplayThirtyDaysData();
            }
        });

        // Code for existing functionality (fetching earthquake data and updating JList)
        // Add it here...



        //add(earthquakes, BorderLayout.CENTER);
        //tells the JFrame to use this JPanel
        //setContentPane(main);



        // jList that outputs the data from Earthquake service
        // This will make a request for the ProductResponse on a separate Thread.

// Do I need this here to repeat?


    }

    private void fetchAndDisplayOneHourData() {
        Disposable disposable = service.oneHour() // updates automatically according to the website
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(SwingSchedulers.edt())
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(response -> handleResponse(response),
                        Throwable::printStackTrace);
    }

    private void fetchAndDisplayThirtyDaysData() {
        Disposable disposable2 = service.thirtyDays() // updates automatically according to the website
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(SwingSchedulers.edt())
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(response -> handleResponse(response),
                        Throwable::printStackTrace);
    }

    private void handleResponse(FeatureCollection response) {
        // got up to here in my code
        String[] listData = new String[response.features.length];
        for (int i = 0; i < response.features.length; i++) {
            Feature feature = response.features[i];
            listData[i] = feature.properties.mag + " " + feature.properties.place;
        }
        earthquakes.setListData(listData);
//        String[] listData = Arrays.stream(response.features).map(feature -> )
//        for (int currentEarthquake = 0; currentEarthquake < response.features.length; currentEarthquake++) {
//
//        }
        // is this taking my featurecollection and returning it to a
        // GUI?
    }

    public static void main(String[] args) {
        new EarthquakeFrame().setVisible(true);
    }
}



