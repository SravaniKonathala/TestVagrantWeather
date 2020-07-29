package com.testvagrant.weather.utils;

public interface Constants {

    final static String WEATHER_BUTTON_PARTIAL_LINK_TEXT = "WEATHER";
    final static String SUB_MENU_ID = "h_sub_menu";
    final static String PINCODE_XPATH = "/html/body/div[7]/div/div[2]/div/input";
    final static int EXPLICIT_WAIT_VALUE = 20;
    final static String CITY_WEATHER = "//div[@class = 'leaflet-popup-content']";
    final static String CHROME = "chrome";
    final static String FIREFOX = "firefox";
    final static String IE = "ie";
    final static String BROWSER_Type = "browserType";
    final static String PROPERTIES_FILE_PATH = "src/test/java/com/testvagrant/weather/resource/config.properties";
    final static String HTML_REPORT_FILE_PATH = "target/cucumber-reports/screenshap.jpg";
    final static String CITY_TEMP_XPATH = "//b[contains(text(),'Temp in Degrees')]";
    final static String CITY_TEMP_DYN_XPATH = "//b[contains(text(),'Temp in Degrees')]";
    final static String RESOURCE_ROOT_PATH = "src/test/java/com/testvagrant/weather/resource/";
    final static String JSON_FILE_NAME = "cityTem.json";
    final static String NDTV = "ndtv";
    final static String OPEN_WEATHER = "openweather";
    final static String CITY = "city";
    final static String TEMP = "temp";
    final static String READ_JSON_FILE_PATH = "src/test/java/com/testvagrant/weather/resource/sample.json";
    final static int MAX_VARIANCE_VALUE = 2;
}
