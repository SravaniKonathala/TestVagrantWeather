Feature: Validate the City Temperature and find variance

  Scenario: Compare the City Temperature and find variance from NDTV weather and Open Weather API
    Given I retrieve the temperatures of city from NDTV weather and Open Weather API from external file
    Then i validate the temperatures
