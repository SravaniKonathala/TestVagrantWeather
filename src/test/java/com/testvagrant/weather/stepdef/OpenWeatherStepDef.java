package com.testvagrant.weather.stepdef;

import com.testvagrant.weather.bean.WeatherCityBean;
import com.testvagrant.weather.utils.CommonFunctionality;
import com.testvagrant.weather.utils.Constants;
import com.testvagrant.weather.utils.RestAssuredCommonActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;

import static java.lang.Float.parseFloat;

public class OpenWeatherStepDef {
    public static float celsiusTemp;
    RestAssuredCommonActions restAssuredCommonActions = new RestAssuredCommonActions();
    CommonFunctionality commonFunctionality = null;
    RequestSpecification requestSpecification;
    Response response;

    @Given("^I Initialize the URI$")
    public void i_Initialize_the_URI() throws IOException {
        commonFunctionality = new CommonFunctionality();
        requestSpecification = restAssuredCommonActions.getRequestSpecification(commonFunctionality.readDataFile().getProperty("api_base_url"));
    }

    @When("^I pass the parameters \"([^\"]*)\" ,\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void i_pass_the_parameters(String query, String xApiKey, String contentType, String uri) {
        restAssuredCommonActions.setParameters(requestSpecification, query, xApiKey, contentType);
        response = restAssuredCommonActions.invokeGetMethod(requestSpecification, uri);
    }

    @Then("^I validate the response$")
    public void i_validate_the_response() throws IOException, ParseException {
        WeatherCityBean weatherCityBean = new WeatherCityBean();
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is: " + responseBody);
        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        JsonPath jsonPath = response.jsonPath();
        float temObj = parseFloat(jsonPath.getMap("main").get("temp").toString());
        celsiusTemp = (float) restAssuredCommonActions.temperatureConverter(temObj);
        weatherCityBean.setTemp(celsiusTemp);
        weatherCityBean.setSource(Constants.OPEN_WEATHER);
        weatherCityBean.setCityName(jsonPath.get("name").toString());
        commonFunctionality.writeJsonFileForCityDtaa(weatherCityBean);
        System.out.println("Temp in Celsius:" + celsiusTemp);
    }

}
