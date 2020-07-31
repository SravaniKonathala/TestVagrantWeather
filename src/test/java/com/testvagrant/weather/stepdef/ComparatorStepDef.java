package com.testvagrant.weather.stepdef;

import com.testvagrant.weather.utils.Constants;
import com.testvagrant.weather.utils.JsonUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class ComparatorStepDef {
    JsonUtils jsonUtils = new JsonUtils();
    @Given("^I retrieve the temperatures from ndtvSource for \"([^\"]*)\" from json file$")
    public void i_retrieve_the_temperatures_from_ndtvSource_for_from_json_file(String cityName) {

        Assert.assertTrue(jsonUtils.isDataTemperaturePresentInDataFile(Constants.NDTV_SOURCE+Constants.UNDER_LINE+cityName));
    }

    @Given("^I retrieve the temperatures from openWeatherSource for \"([^\"]*)\" from json file$")
    public void i_retrieve_the_temperatures_from_openWeatherSource_for_from_json_file(String cityName) {
        Assert.assertTrue(jsonUtils.isDataTemperaturePresentInDataFile(Constants.OPEN_WEATHER_SOURCE+Constants.UNDER_LINE+cityName));
    }

    @Then("^I validate the temperatures of \"([^\"]*)\"$")
    public void i_validate_the_temperatures_of(String cityName) {
        //Find the variance and validate
        double variance;
        List<Double> temperatureList;
        temperatureList =  jsonUtils.retriveTemperature(cityName);
        variance = jsonUtils.getTemperatureVariance1(temperatureList);
        Assert.assertTrue(variance <= Constants.MAX_VARIANCE_VALUE);

    }
}
