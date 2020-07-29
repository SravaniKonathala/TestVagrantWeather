package com.testvagrant.weather.pom;

import com.testvagrant.weather.bean.WeatherCityBean;
import com.testvagrant.weather.utils.CommonFunctionality;
import com.testvagrant.weather.utils.Constants;
import com.testvagrant.weather.utils.WebDriverInitialization;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.util.Objects;

public class NdtvWeatherPageObject extends WebDriverInitialization {

    CommonFunctionality commonFunctionality = new CommonFunctionality();

    @FindBy(partialLinkText = Constants.WEATHER_BUTTON_PARTIAL_LINK_TEXT)
    WebElement weatherButton;

    @FindBy(id = Constants.SUB_MENU_ID)
    WebElement subMenu;

    @FindBy(xpath = Constants.PINCODE_XPATH)
    WebElement giveCityName;

    @FindBy(xpath = Constants.CITY_WEATHER)
    WebElement getCityWeatherElement;

    // Select the browser and launch the application in required browser

    public void launchBrowser(String browserType) {
        WebDriverInitialization.initializeBrowser(browserType);
        PageFactory.initElements(driver,this);
    }

    public void launchApplication() throws IOException {
        driver.get(commonFunctionality.readDataFile().getProperty("url"));
    }

    public void clickOnWeather() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", subMenu);
        commonFunctionality.clickElement(weatherButton, driver, Constants.EXPLICIT_WAIT_VALUE);
    }

    /*
     Enter the city name in "Pin your City" field in the left of the screen
     */
    public void enterCityName(String cityName) {
        commonFunctionality.explicitSendValue(giveCityName, driver, Constants.EXPLICIT_WAIT_VALUE, cityName);
        giveCityName.sendKeys(Keys.ENTER);
        String path = "//input[@id='"+cityName+"']";
        //System.out.println("cityNameDynPath: "+path);
        WebElement cityDynPath = driver.findElement(By.xpath(path));
        if(cityDynPath.isSelected()){
            System.out.println("City is already selected");
        }else{
            cityDynPath.click();
        }


    }
    /*

     */
    public void getCityWeather(String cityName) throws IOException, ParseException {
        WeatherCityBean weatherCityBean;
        String cityWeatherDynXpath="//div[contains(text(),'"+cityName+"')]";
        WebElement cityWeatherDynXpathElement= driver.findElement(By.xpath(cityWeatherDynXpath));
        cityWeatherDynXpathElement.click();

        //get the temputure from NDTV web application
        String cityTempXPath = "//b[contains(text(),'Temp in Degrees')]";
        WebElement cityTempElement = driver.findElement(By.xpath(cityTempXPath));
        //set the required data into bean for writing the new Json input file
        weatherCityBean = new WeatherCityBean();
        weatherCityBean.setCityName(cityName);
        weatherCityBean.setSource(Constants.NDTV);
        String temperatureString = cityTempElement.getText();
        System.out.println("temperatureString : "+temperatureString);
        //if(temperatureString != null || !temperatureString.isEmpty()){
        if(temperatureString != null || !Objects.requireNonNull(temperatureString).isEmpty()){
            weatherCityBean.setTemp(Double.parseDouble(temperatureString.substring(temperatureString.lastIndexOf(":")+1).trim()));
        }
        //writing the json file
        commonFunctionality.writeJsonFileForCityDtaa(weatherCityBean);
    }

    public boolean validateCityWeather() {
        System.out.println("Weather Report: " + getCityWeatherElement.getText());
        return getCityWeatherElement.isDisplayed();
    }

    public void takeScreenShot() throws IOException {
        commonFunctionality.screenShot(driver);
    }

    public void quitBrowser() {
        driver.quit();
    }
}
