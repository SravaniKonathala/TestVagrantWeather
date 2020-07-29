package com.testvagrant.weather;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/testvagrant/weather/featurefiles/ndtvweather.feature", dryRun = false, strict = true)
//@CucumberOptions(features = "src/test/java/com/testvagrant/weather/featurefiles/openweathermapapi.feature", dryRun = false, strict = true)
//@CucumberOptions(features = "src/test/java/com/testvagrant/weather/featurefiles/tempcompartor.feature", dryRun = false, strict = true)
public class TestRunner {
}
