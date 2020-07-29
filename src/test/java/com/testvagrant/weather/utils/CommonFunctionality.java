package com.testvagrant.weather.utils;

import com.testvagrant.weather.resource.Constants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonFunctionality {

   public static Properties properties;
    WebDriverWait webDriverWait=null;
    public Properties readDataFile() throws IOException {
        File file = new File(Constants.PROPERTIES_FILE_PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        properties = new Properties();
        properties.load(fileInputStream);
        return properties;
    }

    public void writeTempPropertyFile(){
        File file = new File(Constants.PROPERTIES_FILE_PATH);
    }
    public WebDriverWait getInstanceOfWebDriverWait(WebDriver driver, Long time){
        if(webDriverWait == null){
            webDriverWait = new WebDriverWait(driver,time);
        }
        return webDriverWait;
    }
    public void clickElement(WebElement element, WebDriver driver,long time){
        WebDriverWait wait = getInstanceOfWebDriverWait(driver , time);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public void explicitSendValue(WebElement element, WebDriver driver,long time,String value){
        WebDriverWait wait = getInstanceOfWebDriverWait(driver , time);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }
    public void cityNameDynXpath(String cityName, WebDriver driver){
        String path = "//input[@id='"+cityName+"']";
        WebElement cityDynPath = driver.findElement(By.xpath(path));
        if(cityDynPath.isSelected()){
            System.out.println("City is already selected");
        }else{
            cityDynPath.click();
        }
        String cityWeather = "//div[contains(text(),'"+cityName+"')]";
        WebElement cityWeatherDynXpath= driver.findElement(By.xpath(cityWeather));
        cityWeatherDynXpath.click();
    }
    public void screenShot(WebDriver driver) throws IOException {
        TakesScreenshot scrshot=((TakesScreenshot) driver);
        File SrcFile= scrshot.getScreenshotAs(OutputType.FILE);
        File DstFile= new File(Constants.HTML_REPORT_FILE_PATH);
        FileUtils.copyFile(SrcFile,DstFile);
    }



}
