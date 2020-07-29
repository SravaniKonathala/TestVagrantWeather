package com.testvagrant.weather.utils;

public class TemperatureVariance {

    public static double getTemperatureVariance(double ndtvTemp, double openWeatherapiTemp) {
        double[] data = {ndtvTemp, openWeatherapiTemp};
        // The mean average
        double mean = 0.0;
        for (double datum : data) {
            //mean = mean+data[i];
            mean += datum;
        }
        mean /= data.length;

// The variance
        double variance = 0;
        for (double datum : data) {
            variance += (datum - mean) * (datum - mean);
        }
        variance /= data.length;
        System.out.println("variance : " + variance);
// Standard Deviation
        double std = Math.sqrt(variance);
        System.out.println("Standard Deviation : " + std);
        return variance;
    }
}
