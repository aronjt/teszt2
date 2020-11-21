package org.progmatic;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryCode;
    private String countryName;
    private String continent;
    private String region;
    private double area;
    private int independence;
    private int population;
    private String leaderName;
    private List<City> cities = new ArrayList<>();

    public Country(String countryCode, String countryName, String continent, String region, double area, int independence, int population) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.continent = continent;
        this.region = region;
        this.area = area;
        this.independence = independence;
        this.population = population;
    }

    public Country(String countryCode, String countryName, String continent, String region, double area, int independence, int population, String leaderName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.continent = continent;
        this.region = region;
        this.area = area;
        this.independence = independence;
        this.population = population;
        this.leaderName = leaderName;
    }

    public void setCities(City city) {
        cities.add(city);
    }

    public double getPopulationDensity() {
        if (population == 0 || area == 0) {
            return -1;
        }
        return population / area;
    }

    public double getRuralPopulation() {
        double citiesPopulation = 0.0;
        for (City city : cities) {
            citiesPopulation += city.getPopulation();
        }
        return population - citiesPopulation;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public double getArea() {
        return area;
    }

    public int getIndependence() {
        return independence;
    }

    public int getPopulation() {
        return population;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public List<City> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", area=" + area +
                ", independence=" + independence +
                ", population=" + population +
                ", leaderName='" + leaderName + '\'' +
                ", cities=" + cities +
                '}';
    }
}
