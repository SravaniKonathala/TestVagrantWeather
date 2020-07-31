package com.testvagrant.weather.stepdef;

import com.testvagrant.weather.pom.NdtvWeatherPageObject;
import com.testvagrant.weather.utils.Constants;
import com.testvagrant.weather.utils.CommonFunctionality;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;

public class NdtvWeatherStepDef {

    NdtvWeatherPageObject ndtvWeatherPageObject = new NdtvWeatherPageObject();
    CommonFunctionality commonFunctionality = null;

    @Given("^I launch the web browser$")
    public void i_launch_the_web_browser() {
        commonFunctionality = new CommonFunctionality();
        ndtvWeatherPageObject.launchBrowser(commonFunctionality.readDataFile().getProperty(Constants.BROWSER_Type));
    }

    @Given("^I launch the application$")
    public void i_launch_the_application() {
        ndtvWeatherPageObject.launchApplication();
    }

    @When("^I extend the header and click on weather$")
    public void i_click_on_weather() {
        ndtvWeatherPageObject.clickOnWeather();
    }

    @When("^I enter the city name \"([^\"]*)\",select city and click city on map$")
    public void i_enter_the_city_name_select_city_and_click_city_on_map(String cityName) throws InterruptedException, IOException, ParseException {
        ndtvWeatherPageObject.enterCityName(cityName);
        ndtvWeatherPageObject.getCityWeather(cityName);
    }

    @Then("^I validate weather condition of city on the map$")
    public void i_validate_weather_condition_of_city_on_the_map() throws Throwable {
        Assert.assertTrue(ndtvWeatherPageObject.validateCityWeather());
        ndtvWeatherPageObject.takeScreenShot();
    }

    @Then("^I quit the browser$")
    public void i_quit_the_browser() {
        ndtvWeatherPageObject.quitBrowser();
    }
}
