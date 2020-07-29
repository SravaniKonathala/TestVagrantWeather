package com.testvagrant.weather.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredCommonActions {
    RequestSpecification requestSpecification;
    Response response = null;

    public RequestSpecification getRequestSpecification(String url) {
        RestAssured.baseURI = url;
        requestSpecification = RestAssured.given();
        return requestSpecification;
    }

    public void setParameters(RequestSpecification requestSpecificationForPram, String location, String apiKey, String contentType) {
        requestSpecificationForPram.param("q", location);
        requestSpecificationForPram.header("x-api-key", apiKey);
        requestSpecificationForPram.header("Content-Type", contentType);
    }

    public Response invokeGetMethod(RequestSpecification requestSpecification, String uri) {
        response = requestSpecification.request(Method.GET, uri);
        return response;
    }
    public float temperatureConverter(float kelvinTemp){
        float celsiusTemp = (float) (kelvinTemp- 273.15);
        return celsiusTemp;
    }

}
