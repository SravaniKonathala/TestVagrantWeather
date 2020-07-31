package com.testvagrant.weather.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class JsonUtils {

    /* method name:  retriveTemperature
     * @param cityName
     * Description: retrive the temperatures from datajson file and returns the list city temperature from different source (using simple JSON API)
     */

    public List<Double> retriveTemperature(String cityName) {
        System.out.println(("JsonUtils :: retriveTemperature :: Start"));
        List<Double> temperatureList = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject temperatureDataObj;
        JSONObject ndtvDataJasonObj;
        JSONObject openWeatherJsonObj;
        JSONArray temperatureDataList;
        FileReader fileReader;
        double ndtv_city_temp;
        double open_weather_temp;

        try {
            fileReader = new FileReader(Constants.JSON_DATA_FILE_PATH);
            temperatureDataList = (JSONArray) jsonParser.parse(fileReader);
            temperatureList = new ArrayList<>();
            for (Object o : temperatureDataList) {
                temperatureDataObj = (JSONObject) o;
                if (temperatureDataObj.containsKey(Constants.NDTV_SOURCE + Constants.UNDER_LINE + cityName)) {
                    ndtvDataJasonObj = (JSONObject) temperatureDataObj.get(Constants.NDTV_SOURCE + Constants.UNDER_LINE + cityName);
                    ndtv_city_temp = Double.parseDouble(ndtvDataJasonObj.get("temp").toString());
                    System.out.println("JsonUtils :: retriveTemperature :: get temperature of "+cityName+" city from ndtv webpage : "+ndtv_city_temp);
                    temperatureList.add(ndtv_city_temp);
                } else if (temperatureDataObj.containsKey(Constants.OPEN_WEATHER_SOURCE + Constants.UNDER_LINE + cityName)) {
                    openWeatherJsonObj = (JSONObject) temperatureDataObj.get(Constants.OPEN_WEATHER_SOURCE + Constants.UNDER_LINE + cityName);
                    open_weather_temp = Double.parseDouble(openWeatherJsonObj.get("temp").toString());
                    System.out.println("JsonUtils :: retriveTemperature :: get temperature of "+cityName+" city from ope weather api : "+open_weather_temp);
                    temperatureList.add(open_weather_temp);
                }

            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("JsonUtils :: retriveTemperature :: temperatureList : "+temperatureList.toString());
        System.out.println("JsonUtils :: retriveTemperature :: end");
        return temperatureList;
    }

    /**
     * @method name:  prepareDataJsonObject
     * @Description: Prepare the JsonObject for JsonData File
     * @param source
     * @Description: get the temperature from ndtv and open wether api(eg: source = ndtv or open_weather)
     * @param city
     * @Description: to find the temperature of a city(city = Bengaluru or Hyderabad....)
     * @param temperature
     * @Description: prepare the data json object and store into JSONArray (simple JSON API)
     *
     */

    public void prepareDataJsonObject(String source, String city, double temperature) {
        System.out.println("JsonUtil :: prepareDataJsonObject :: Start");
        JSONObject newDataJsonObj;
        JSONParser jsonParser;
        JSONObject mainObject;
        JSONArray cityJsonList;
        FileReader reader;
        JSONObject allCitiesJsonObj;
        try {
            jsonParser = new JSONParser();
            newDataJsonObj = new JSONObject();
            mainObject = new JSONObject();
            JSONArray dataArray = new JSONArray();
            reader = new FileReader(Constants.JSON_DATA_FILE_PATH);
            cityJsonList = (JSONArray) jsonParser.parse(reader);
            if (cityJsonList.size() > 0) {
                for (Object o : cityJsonList) {
                    allCitiesJsonObj = (JSONObject) o;
                    allCitiesJsonObj.remove(source + Constants.UNDER_LINE + city);
                    if (!allCitiesJsonObj.isEmpty()) {
                        dataArray.add(allCitiesJsonObj);
                    }
                    System.out.println("dataArray" + dataArray.toJSONString());
                }
            }
            newDataJsonObj.put(Constants.TEMP, temperature);
            mainObject.put(source + Constants.UNDER_LINE + city, newDataJsonObj);
            dataArray.add(mainObject);
            System.out.println("JsonUtil :: prepareDataJsonObject :: jsondatafile data : "+dataArray.toJSONString());
            writeJsonFile(Constants.JSON_DATA_FILE_PATH, dataArray);
            System.out.println("JsonUtil :: prepareDataJsonObject :: end");
        } catch (Exception pe) {
            pe.printStackTrace();
        }
    }

    /* @method name:  writeJsonFile
     * @Description: write the json file using org.json.simple.JSON
     * @param filePath
     * @Description: File location of JsonData file
     * @param dataJsonArray
     * @Description: storing the list of JsonObject from JsonData file
     */
    public void writeJsonFile(String filePath, JSONArray dataJsonArray) {
        System.out.println("JsonUtil :: writeJsonFile :: Start");
        try {
            FileWriter fw = new FileWriter(new File(filePath));
            fw.write(dataJsonArray.toString());
            System.out.println("JsonUtil :: writeJsonFile :: file write successfully: "+filePath);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("JsonUtil :: writeJsonFile :: End");
    }

    /**
     * method name:  isDataTemperaturePresentInDataFile
     * @Description: validate the different source temperature are presents in the data json file.
     * @param source return boolean
     * @Description: get the temperature from ndtv and open wether api(eg: source = ndtv or open_weather).
     */
    public Boolean isDataTemperaturePresentInDataFile(String source) {
        System.out.println("JsonUtil :: isDataTemperaturePresentInDataFile :: Start");
        boolean isDataTemperatureInDataFile = false;
        JSONParser jsonParser = new JSONParser();
        JSONObject temperatureDataObj;
        JSONArray temperatureDataList;
        FileReader fileReader;
        try {
            fileReader = new FileReader(Constants.JSON_DATA_FILE_PATH);
            temperatureDataList = (JSONArray) jsonParser.parse(fileReader);
            for (Object o : temperatureDataList) {
                temperatureDataObj = (JSONObject) o;
                if (temperatureDataObj.containsKey(source)) {
                    isDataTemperatureInDataFile = true;
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("isDataTemperatureInDataFile : "+isDataTemperatureInDataFile);
        System.out.println("JsonUtil :: isDataTemperaturePresentInDataFile :: End");
        return isDataTemperatureInDataFile;

    }

    /**
     * method name:  getTemperatureVariance1
     * @Description: find the variance form list of temperature (double values)  and return variance (ex : 2.34).
     * @param temperatureList (Array List  )
     * @Description: Store list of temperatures of a city from different source(ndtv and open weather)
     *
     */
    public double getTemperatureVariance1(List<Double> temperatureList) {
        System.out.println("JsonUtil :: isDataTemperaturePresentInDataFile :: Start");
        double meanOfTemperature = 0.0;
        double variance = 0;
        try {
            //find the mean of temperature
            for (double temperature : temperatureList) {
                meanOfTemperature += temperature;
            }
            meanOfTemperature /= temperatureList.size();
            // find The variance of temperature
            for (double temperature : temperatureList) {
                variance += (temperature - meanOfTemperature) * (temperature - meanOfTemperature);
            }
            variance /= temperatureList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("variance : " + variance);
        System.out.println("JsonUtil :: isDataTemperaturePresentInDataFile :: End");
        return variance;
    }
}
