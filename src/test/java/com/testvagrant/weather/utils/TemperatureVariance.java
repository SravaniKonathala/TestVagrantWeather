package com.testvagrant.weather.utils;
public class TemperatureVariance {

    public double getTempertureVariance(){
        double[] data = {23,24};
        // The mean average
        double mean = 0.0;
        for (int i = 0; i < data.length; i++) {
            //mean = mean+data[i];
            mean += data[i];
        }
        mean /= data.length;

// The variance
        double variance = 0;
        for (int i = 0; i < data.length; i++) {
            variance += (data[i] - mean) * (data[i] - mean);
        }
        variance /= data.length;
        System.out.println("variance : "+variance);
// Standard Deviation
        double std = Math.sqrt(variance);
        System.out.println("Standard Deviation : "+std);
            return 0.00;
    }
}
