package com.testvagrant.weather.utils;

import com.testvagrant.weather.bean.WeatherCityBean;
import org.apache.commons.io.FileUtils;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Properties;

public class CommonFunctionality {

    public static Properties properties;
    WebDriverWait webDriverWait = null;

    public Properties readDataFile() throws IOException {
        File file = new File(Constants.PROPERTIES_FILE_PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        properties = new Properties();
        properties.load(fileInputStream);
        return properties;
    }

    public void writeJsonFileForCityDtaa(WeatherCityBean weatherCityBean) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONArray dataArray = new JSONArray();
        JSONObject sampleObject = new JSONObject();
        JSONObject mainObject = new JSONObject();

        //read the json file
        FileReader reader = new FileReader(Constants.RESOURCE_ROOT_PATH + Constants.JSON_FILE_NAME);
        //Read JSON file
        Object obj = jsonParser.parse(reader);
        sampleObject.put(Constants.CITY, weatherCityBean.getCityName());
        sampleObject.put(Constants.TEMP, weatherCityBean.getTemp());
        mainObject.put(weatherCityBean.getSource(), sampleObject);
        dataArray.add(mainObject);
        FileWriter fw = new FileWriter(new File(Constants.RESOURCE_ROOT_PATH + Constants.JSON_FILE_NAME));
        fw.write(dataArray.toString());
        fw.close();
        //Files.write(Paths.get(Constants.RESOURCE_ROOT_PATH+Constants.JSON_FILE_NAME), dataArray.toJSONObject(dataArray).getBytes());
    }

    public WebDriverWait getInstanceOfWebDriverWait(WebDriver driver, Long time) {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, time);
        }
        return webDriverWait;
    }

    public void clickElement(WebElement element, WebDriver driver, long time) {
        WebDriverWait wait = getInstanceOfWebDriverWait(driver, time);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void explicitSendValue(WebElement element, WebDriver driver, long time, String value) {
        WebDriverWait wait = getInstanceOfWebDriverWait(driver, time);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    public void screenShot(WebDriver driver) throws IOException {
        TakesScreenshot scrshot = ((TakesScreenshot) driver);
        File SrcFile = scrshot.getScreenshotAs(OutputType.FILE);
        File DstFile = new File(Constants.HTML_REPORT_FILE_PATH);
        FileUtils.copyFile(SrcFile, DstFile);
    }


}
