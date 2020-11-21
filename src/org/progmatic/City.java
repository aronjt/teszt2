package org.progmatic;

public class City {
    private String cityName;
    private String countryCode;
    private int population;
    private Country country;

    public City(String cityName, String countryCode, int population) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.population = population;
    }

    //Adott város populációja hány százaléka az anyaország populációjának!

    public double getPopulationPercentage() {
        return (double) population / country.getPopulation();
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", population=" + population +
                ", country=" + country +
                '}';
    }
}
