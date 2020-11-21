package org.progmatic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WorldStatistics {

    HashMap<String, Country> countries = new HashMap<>(readFiles());

    public WorldStatistics() throws FileNotFoundException {
    }

    public HashMap<String, Country> readFiles() throws FileNotFoundException {
        HashMap<String, Country> countries = new HashMap<>();
        Scanner sc = new Scanner(new File("files/orszagok.txt"));
        Scanner sci = new Scanner(new File("files/varosok.txt"));
        List<Country> countryList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            String countryCode = line[0];
            String countryName = line[1];
            String continent = line[2];
            String region = line[3];
            double area = Double.parseDouble(line[4]);
            int independence;
            if (line[5].equals("NULL")) {
                independence = 0;
            }else {
               independence = Integer.parseInt(line[5]);
            }
            int population = Integer.parseInt(line[6]);
            if (line.length > 7) {
                String leaderName = line[7];
                countryList.add(new Country(countryCode, countryName, continent, region, area, independence, population, leaderName));
            } else {
                countryList.add(new Country(countryCode, countryName, continent, region, area, independence, population, "-"));
            }
        }
        List<City> cityList = new ArrayList<>();
        while (sci.hasNextLine()) {
            String[] line = sci.nextLine().split(",");
            String cityName = line[0];
            String countryCode = line[1];
            int population = Integer.parseInt(line[2]);
            cityList.add(new City(cityName, countryCode, population));
        }
        for (Country country : countryList) {
            for (City city : cityList) {
                if (country.getCountryCode().equals(city.getCountryCode())) {
                    country.setCities(city);
                    city.setCountry(country);
                }
            }
        }
        for (Country country : countryList) {
            countries.putIfAbsent(country.getCountryCode(), country);
        }
        return countries;
    }

    //Írj egy olyan metódust, ami országkód alapján visszaadja egy ország összes adatát!

    public Country findCountyByIsoCode(String isoCode) {
        Country country = countries.get(isoCode);
        System.out.println("Ország: " + country.getCountryName());
        System.out.println("Kontines: " + country.getContinent());
        System.out.println("Régió: " + country.getRegion());
        System.out.println("Terület: " + country.getArea());
        if (country.getIndependence() == 0) {
            System.out.println("Mindig független volt.");
        } else {
            System.out.println("Független: " + country.getIndependence());
        }
        System.out.println("Népesség: " + country.getPopulation());
        System.out.println("Vezető: " + country.getLeaderName());
        System.out.print("Városok: ");
        for (City city : country.getCities()) {
            System.out.print(city.getCityName() + ", ");
        }
        return country;
    }

    //Írj egy olyan metódust, amely visszaadja egy paraméterül kapott kontinens országainak az országkódjait! (Fura, de megy)


    public ArrayList<String> getCountriesOfContinent (String continentName) {
        ArrayList<String> countriesInContinent = new ArrayList<>();
        for (Map.Entry<String, Country> entry : countries.entrySet()) {
            if (entry.getValue().getContinent().equals(continentName)) {
                countriesInContinent.add(entry.getValue().getCountryCode());
            }
        }
        System.out.println();
        return countriesInContinent;
    }

    //Írj egy olyan metódust, amely visszaadja egy paraméterül kapott ország városainak a neveit
    //(az országot országkóddal adjuk meg)!

    public HashSet<String> getCitiesOfCOuntry(String countryCode) {
        HashSet<String> citiesOfCuntry = new HashSet<>();
        for (String key : countries.keySet()) {
            if (key.equals(countryCode)) {
                for (int i = 0; i < countries.get(key).getCities().size(); i++) {
                    citiesOfCuntry.add(countries.get(key).getCities().get(i).getCityName());
                }
            }
        }
        return citiesOfCuntry;
    }

    //Hány országnak az államfőjének nevében szerepel “Hamad” vagy “Ahmad” vagy “Ahmed”?

    public int getAhmedCount() {
        int sum = 0;
        for (Country value : countries.values()) {
            if (value.getLeaderName().indexOf("Hamad") > 0 || value.getLeaderName().indexOf("Ahmad") > 0 || value.getLeaderName().indexOf("Ahmed") > 0) {
                sum++;
            }
        }
        return sum;
    }

    //Melyik betűvel kezdődik a legtöbb országkód?

    public String getPopularFirstLetter() {
        int sum;
        int max = 0;
        String retu = null;
        for (int i = 65; i < 91; i++) {
            sum = 0;
            char firstLetter = (char) i;
            String elso = Character.toString(firstLetter);
            for (String key : countries.keySet()) {
                if (elso.equals(key.substring(0,1))) {
                    sum++;
                }
            }
            if (sum > max) {
                max = sum;
                retu = Character.toString((char) i);
            }
        }
        return retu;
    }

    //Melyik ország nyerte el legkésőbb (a nyilvántartás szerint) a függetlenségét?

    public String lastIndependentCountry() {
        int year = 0;
        String countryCode = null;
        for (Country country : countries.values()) {
            if (country.getIndependence() > year) {
                countryCode = country.getCountryCode();
                year = country.getIndependence();
            }
        }
        return countryCode;
    }

    public void bonus() {
        Country proba = countries.get("HUN");
        System.out.println(proba.getCities().get(0).getPopulationPercentage());
    }
}
