package org.progmatic;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        WorldStatistics orszagok = new WorldStatistics();
        orszagok.readFiles();
      //  orszagok.findCountyByIsoCode("AND");
        System.out.println(orszagok.getCountriesOfContinent("Europe"));
        System.out.println(orszagok.getCitiesOfCOuntry("HUN"));
        System.out.println(orszagok.getAhmedCount());
        System.out.println(orszagok.lastIndependentCountry());
        orszagok.bonus();
        System.out.println("-----");
        System.out.println(orszagok.getPopularFirstLetter());
    }
}