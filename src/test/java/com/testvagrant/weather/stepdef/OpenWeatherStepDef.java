package com.testvagrant.weather.stepdef;

import com.testvagrant.weather.utils.CommonFunctionality;
import com.testvagrant.weather.utils.Constants;
import com.testvagrant.weather.utils.JsonUtils;
import com.testvagrant.weather.utils.RestAssuredCommonActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.text.DecimalFormat;

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

    @When("^I pass the parameters for city \"([^\"]*)\"$")
    public void i_pass_the_parameters_for_city(String cityName) throws IOException {
        String xApiKey = commonFunctionality.readDataFile().getProperty(Constants.X_API_KEY);
        String uri = commonFunctionality.readDataFile().getProperty(Constants.WEATHER_URI);
        String contentType = commonFunctionality.readDataFile().getProperty(Constants.CONTENT_TYPE);
        restAssuredCommonActions.setParameters(requestSpecification, cityName, xApiKey, contentType);
        response = restAssuredCommonActions.invokeGetMethod(requestSpecification, uri);
    }

    @Then("^I validate the response$")
    public void i_validate_the_response() {
        JsonUtils jsonUtils =new JsonUtils();
        String responseBody = response.getBody().asString();
        int expectedStatusCode = 200;
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
        JsonPath jsonPath = response.jsonPath();
        String cityName = jsonPath.get("name").toString();
        double tempObj = Double.parseDouble(jsonPath.getMap("main").get("temp").toString());
        celsiusTemp = (float) restAssuredCommonActions.temperatureConverter(tempObj);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double tempTrim = Double.parseDouble(decimalFormat.format(celsiusTemp));
        jsonUtils.prepareDataJsonObject(Constants.OPEN_WEATHER_SOURCE,cityName,tempTrim);
    }

}
