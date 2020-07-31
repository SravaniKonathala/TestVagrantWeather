# TestVagrantWeather
Test Vagrant Assignment

 Step 1: Read the temperature of a city from NDTV web application
    featureFile Name: ndtvweather.feature
    frameWork: Cucumber, Selenium WebDriver, java using Page Object Model which supports Cucumber RestAssured to test API 
    Execution: run the TestRunner class with "ndtvweather.feature" path in features in CucumberOptions in TestRunner Class
    enable "@CucumberOptions(features = "src/test/java/com/testvagrant/weather/featurefiles/ndtvweather.feature", dryRun = false, strict = true)"
    rest should be commented
    
    Description: 
    * It will launch the NDTV application and retrieve the temperature for a city.
    * Then save the temperature in the json data file 
    * json data file path: src/test/java/com/testvagrant/weather/resource/temperatureDataFile.json
    
 Step 2: Read the temperature of a city from Open Weather API
     featureFile: openweathermapapi.feature
     frameWork: Cucumber, Selenium WebDriver, java using Page Object Model which supports Cucumber RestAssured to test API 
     Execution: Run the TestRunner class with "openweathermapapi.feature" path in features in CucumberOptions in TestRunner Class
     
     Description: 
     * It will hit the API and get the response
     * Identify the temperature of the city from response body.
     * Then save the temperature in the json data file 
         * json data file path: src/test/java/com/testvagrant/weather/resource/temperatureDataFile.json  
  
 Step 3: Compare and validate the temperatures of a city from json file
       featureFile: tempcompartor.feature
       frameWork: Cucumber, Selenium WebDriver, java using Page Object Model which supports Cucumber RestAssured to test API 
       Execution: Run the TestRunner class with "tempcompartor.feature" path in features in CucumberOptions in TestRunner Class
       
       Mandatory:Execute ndtvweather.feature and openweathermapapi.feature then execute tempcompartor.feature
       Reason: As the data should be retrieve and store in json data file which is used as input for this scenario, from the applications 
       Description: 
       * It will get the temperature values from NDTV and Open Weather API stored in json data file
       * Then it finds the variance and validate the variance  
       * variance condition: variance<2
       
 Execution with Ide: Run TestRunner class, will run all the three feature files in sequence 
      
       
 Maven Build:
 In Terminal: mvn test (maven should be in the local machine)
 
        
       
       
    
    
    
    
