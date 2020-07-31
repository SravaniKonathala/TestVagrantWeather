package com.testvagrant.weather.utils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredCommonActions {

    Response response = null;


    /*
    * @method: getRequestSpecification
    * @Description: get the rest specification object
    * */
    public RequestSpecification getRequestSpecification(String url) {
        RequestSpecification requestSpecification = null;
        try{
            if(url !=null || !url.equals("")) {
                RestAssured.baseURI = url;
                requestSpecification = RestAssured.given();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return requestSpecification;
    }

    /*
     * @method: setParameters
     * @Description: set the required parameters into  request specification object
     * @parameters: location, apiKey, contentType
     * @Description: api header parameters
     * */
    public void setParameters(RequestSpecification requestSpecificationForPram, String location, String apiKey, String contentType) {
        requestSpecificationForPram.param(Constants.Q, location);
        requestSpecificationForPram.header(Constants.X_API_KEY, apiKey);
        requestSpecificationForPram.header(Constants.CONTENT_TYPE, contentType);
    }

    /*
     * @method: invokeGetMethod
     * @Description: invok the get metod through reset client using rest assured api.
     * @parameters: requestSpecification, uri.
     * @Description: uri = /weather, requestSpecification have all required header and URl information.
     * @return: weather response from open weather api.
     */
    public Response invokeGetMethod(RequestSpecification requestSpecification, String uri) {
        response = requestSpecification.request(Method.GET, uri);
        return response;
    }

    /*
    * @method: temperatureConverter
    * @Description: convert temperature from kelvin to celsius
    * @param: kelvinTemp
    * @Description: temperature in kelvin
    * @return: celsiusTemp
    * @Description: returns temperature in celsius
     */

    public double temperatureConverter(double kelvinTemp) {
        double celsiusTemp = 0.0;
        celsiusTemp = kelvinTemp - 273.15;
        return celsiusTemp;
    }
}
