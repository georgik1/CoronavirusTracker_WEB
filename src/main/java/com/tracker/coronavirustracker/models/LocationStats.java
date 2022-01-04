package com.tracker.coronavirustracker.models;

public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return String.format("Location statistics:%n" +
                "{ %n" +
                "state = %s %n" +
                "country = %s %n" +
                "latest total cases = %d %n" +
                "}%n",state, country, latestTotalCases);
    }
}
