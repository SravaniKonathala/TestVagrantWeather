package com.testvagrant.weather.pom;

import com.testvagrant.weather.utils.CommonFunctionality;
import com.testvagrant.weather.resource.Constants;
import com.testvagrant.weather.utils.WebDriverIntilization;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class NdtvWeatherPageObject extends WebDriverIntilization{
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
        WebDriverIntilization.initializeBrowser(browserType);
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

    // Enter the city in "Pin your City" field in the left of the screen
    public void enterCityName(String cityName) throws InterruptedException {
        commonFunctionality.explicitSendValue(giveCityName, driver, Constants.EXPLICIT_WAIT_VALUE, cityName);
        giveCityName.sendKeys(Keys.ENTER);
        commonFunctionality.cityNameDynXpath(cityName, driver);
    }

    public boolean getCityWeather() {
        System.out.println("Weather Report: " + getCityWeatherElement.getText());
        boolean value = getCityWeatherElement.isDisplayed();
        return value;
    }

    public void takeScreenShot() throws IOException {
        commonFunctionality.screenShot(driver);
    }

    public void quitBrowser() {
        driver.quit();
    }
}
