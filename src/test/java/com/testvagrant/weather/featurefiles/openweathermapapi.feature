Feature: Validating the weather report in OpenWeatherMap(API)
  Scenario Outline:Validate the weather report
    Given I Initialize the URI
    When I pass the parameters for city "<city>"
    Then I validate the response

    Examples:
    |city|
    |Bengaluru|