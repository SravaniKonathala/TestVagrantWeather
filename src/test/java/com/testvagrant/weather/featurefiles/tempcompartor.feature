Feature: Validate the City Temperature and find variance

  Scenario Outline: Compare the City Temperature and find variance from NDTV weather and Open Weather API
    Given I retrieve the temperatures from ndtvSource "<ndtvSource>" and openWeatherSource "<openWeatherSource>"json file
    Then i validate the temperatures
    Examples:
    |    ndtvSource             |      openWeatherSource          |
    |   ndtv_weather_bangalore  |    open_weather_bangalore       |
