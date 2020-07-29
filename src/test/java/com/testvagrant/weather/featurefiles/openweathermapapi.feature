Feature: Validating the weather report in OpenWeatherMap(API)
  Scenario Outline:Validate the weather report
    Given I Initialize the URI
    When I pass the parameters "<q>" ,"<x-api-key>","<Content-Type>","<uri>"
    Then I validate the response

    Examples:
    |q        |         x-api-key              | Content-Type   | uri        |
    |Bengaluru|7fe67bf08c80ded756e598d6f8fedaea|application/json| /weather   |