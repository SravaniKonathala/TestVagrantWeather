package com.testvagrant.weather.utils;

import com.testvagrant.weather.resource.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;

public class WebDriverIntilization {
    public  static WebDriver driver;
    public static WebDriver initializeBrowser(String browserType) {
        if (browserType.equalsIgnoreCase(Constants.CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browserType.equalsIgnoreCase(Constants.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browserType.equalsIgnoreCase(Constants.IE)) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
