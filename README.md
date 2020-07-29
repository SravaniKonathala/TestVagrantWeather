# TestVagrantWeather
Test Vagrant Assignment

 Step 1: Read the temperature of a city from NDTV web application
    featureFile: ndtvweather.feature
    frameWork: Cucumber, Selenium WebDriver, java using Page Object Model which supports Cucumber RestAssured to test API 
    Execution: run the TestRunner class with "ndtvweather.feature" path in features in CucumberOptions
    
    Description: 
    * It will luanch the NDTV application and retrieve the temperature for a city.
    * Then save the temperature in the json file as json object
    
 Step 2: Read the temperature of a city from Open Weather API
     featureFile: openweathermapapi.feature
     frameWork: Cucumber, Selenium WebDriver, java using Page Object Model which supports Cucumber RestAssured to test API 
     Execution: Run the TestRunner class with "openweathermapapi.feature" path in features in CucumberOptions
     
     Description: 
     * It will hit the API and get the response
     * Identify the temperature of the city from response body.
     * Then save the temperature in the json file as json object   
  
 Step 3: Compare and validate the temperatures of a city from json file
       featureFile: tempcompartor.feature
       frameWork: Cucumber, Selenium WebDriver, java using Page Object Model which supports Cucumber RestAssured to test API 
       Execution: Run the TestRunner class with "tempcompartor.feature" path in features in CucumberOptions
       
       Description: 
       * It get the temperature values from NDTV and Open Weather API stored in json file
       * Then find the variance and validate the variance  
    
    
    
    
