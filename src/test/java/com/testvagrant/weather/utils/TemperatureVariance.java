package com.testvagrant.weather.utils;

public class TemperatureVariance {

    public static double getTemperatureVariance(double ndtvTemp, double openWeatherapiTemp) {
        double[] data = {ndtvTemp, openWeatherapiTemp};
        // The mean average
        double mean = 0.0;
        for (double datum : data) {
            mean += datum;
        }
        mean /= data.length;

        // The variance
        double variance = 0;
        for (double datum : data) {
            variance += (datum - mean) * (datum - mean);
        }
        variance /= data.length;
        return variance;
    }
}
