package com.testvagrant.weather.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverInitialization {
    public static WebDriver driver;

    /*
    * @method: initializeBrowser
    * @Description: Initialize the Driver
    * @param: browserType
    * @Description: type of browser(eg: chrome, firefox...)
    * */
    public static void initializeBrowser(String browserType) {
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
    }
}
