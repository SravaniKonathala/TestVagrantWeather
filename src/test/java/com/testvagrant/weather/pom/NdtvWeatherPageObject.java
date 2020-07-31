package com.testvagrant.weather.pom;

import com.testvagrant.weather.utils.CommonFunctionality;
import com.testvagrant.weather.utils.Constants;
import com.testvagrant.weather.utils.JsonUtils;
import com.testvagrant.weather.utils.WebDriverInitialization;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;

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

    /*
    * @method: launchBrowser
    * @Description: launch the specified browser
    * @param: browserType
    * @Description: Type of the browser to be launched
    * */
    public void launchBrowser(String browserType) {
        WebDriverInitialization.initializeBrowser(browserType);
        PageFactory.initElements(driver,this);
    }
    /*
    * @method: launchApplication
    * @Description: launch the web application
    * */
    public void launchApplication() {
        driver.get(commonFunctionality.readDataFile().getProperty("url"));
    }
    /*
    * @method: clickOnWeather
    * @Description: Extend the header and click on "Weather" option
    * */
    public void clickOnWeather() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", subMenu);
        commonFunctionality.clickElement(weatherButton, driver, Constants.EXPLICIT_WAIT_VALUE);
    }

    /*
     *@method: enterCityName
     * @Description: Enter the city name in "Pin your City" field in the left of the screen and click on city
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
     *@method: getCityWeather
     * @Description: get the weather of the city specified and store the temperature in json data file
     * @param: cityName
     * @Description: temperature of city(Bangalore, Hyderabad..)
     */
    public void getCityWeather(String cityName){
        String cityWeatherDynXpath="//div[contains(text(),'"+cityName+"')]";
        JsonUtils jsonUtils = new JsonUtils();
        WebElement cityWeatherDynXpathElement= driver.findElement(By.xpath(cityWeatherDynXpath));
        commonFunctionality.clickElement(cityWeatherDynXpathElement,driver,10);

        //get the temperature from NDTV web application
        String cityTempXPath = "//b[contains(text(),'Temp in Degrees')]";
        WebElement cityTempElement = driver.findElement(By.xpath(cityTempXPath));

        //set the required data into bean for writing the new Json input file
        String temperatureString = cityTempElement.getText();
        System.out.println("temperature: "+temperatureString);
        double temp = Double.parseDouble(temperatureString.substring(temperatureString.indexOf(":")+1).trim());

        //update the temperature into data json file
        jsonUtils.prepareDataJsonObject(Constants.NDTV_SOURCE,cityName,temp);
    }
    /*
    * @method: validateCityWeather
    * @Description: Validate weather of a city is displayed in the list
    * */
    public boolean validateCityWeather() {
        System.out.println("Weather Report: " + getCityWeatherElement.getText());
        return getCityWeatherElement.isDisplayed();
    }
    /*
    * @method: takeScreenShot
    * @Description: Take a screen shot of a success scenario
    * */
    public void takeScreenShot() throws IOException {
        commonFunctionality.screenShot(driver);
    }

    /*
    * @method: quitBrowser
    * @Description: Quit the launched browser
    * */
    public void quitBrowser() {
        driver.quit();
    }
}
