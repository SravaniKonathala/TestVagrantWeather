Feature: Validate the City Temperature and find variance

  Scenario Outline: Compare the City Temperature and find variance from NDTV weather and Open Weather API
    Given I retrieve the temperatures from ndtvSource for "<city>" from json file
    Given I retrieve the temperatures from openWeatherSource for "<city>" from json file
    Then I validate the temperatures of "<city>"
    Examples:
    |    city             |
    |   Bengaluru         |
