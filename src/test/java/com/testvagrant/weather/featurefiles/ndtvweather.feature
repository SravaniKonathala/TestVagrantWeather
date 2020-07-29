Feature: Validating the weather report in NDTV weather(UI)

  Scenario Outline: Validate the weather report for a specific location
    Given I launch the web browser
    And I launch the application
    When I extend the header and click on weather
    And I enter the city name "<city name>",select city and click city on map
    Then I validate weather condition of city on the map
    And I quit the browser

    Examples:
    | city name |
    | Bengaluru |



