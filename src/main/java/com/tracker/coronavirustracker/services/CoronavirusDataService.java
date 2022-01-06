package com.tracker.coronavirustracker.services;

import com.tracker.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CoronavirusDataService {

    //http call to data url
    private static String VIRUS_DATA_URL =
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    //create instances of the stats and populate the list
    private List<LocationStats> allStats = new ArrayList<>();


    //fetch the data and get a response.If "client.send" fails, throw exceptions.
    //this annotation assures method will always execute upon project startup
    @PostConstruct
    //help update the data in real-time(format = s, m, h, dd, MM, yyyy..scheduled to run once every day)
    @Scheduled(cron = " * * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> httpResponse =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
            System.out.println(locationStat);
            newStats.add(locationStat);
        }
        this.allStats = newStats;
    }
}
