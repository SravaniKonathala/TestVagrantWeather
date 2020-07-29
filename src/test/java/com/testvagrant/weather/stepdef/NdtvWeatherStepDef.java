package com.testvagrant.weather.stepdef;

import com.testvagrant.weather.pom.NdtvWeatherPageObject;
import com.testvagrant.weather.resource.Constants;
import com.testvagrant.weather.utils.CommonFunctionality;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.IOException;

public class NdtvWeatherStepDef {

    NdtvWeatherPageObject ndtvWeatherPageObject = new NdtvWeatherPageObject();
    CommonFunctionality commonFunctionality = null;

    @Given("^I launch the web browser$")
    public void i_launch_the_web_browser()  {
        try {
            commonFunctionality = new CommonFunctionality();
            ndtvWeatherPageObject.launchBrowser(commonFunctionality.readDataFile().getProperty(Constants.BROWSER_Type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("^I launch the application$")
    public void i_launch_the_application()  {
        try {
            ndtvWeatherPageObject.launchApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("^I extend the header and click on weather$")
    public void i_click_on_weather() throws InterruptedException {
        ndtvWeatherPageObject.clickOnWeather();
    }

    @When("^I enter the city name \"([^\"]*)\",select city and click city on map$")
    public void i_enter_the_city_name_select_city_and_click_city_on_map(String cityName) throws InterruptedException {
        ndtvWeatherPageObject.enterCityName(cityName);
    }

    @Then("^I validate weather condition of city on the map$")
    public void i_validate_weather_condition_of_city_on_the_map() throws Throwable {
        boolean value = ndtvWeatherPageObject.getCityWeather();
        Assert.assertTrue(value);
        ndtvWeatherPageObject.takeScreenShot();
    }

    @Then("^I quit the browser$")
    public void i_quit_the_browser() {
        ndtvWeatherPageObject.quitBrowser();
    }
}
