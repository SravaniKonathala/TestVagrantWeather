package com.testvagrant.weather.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.util.Properties;

public class CommonFunctionality {

    WebDriverWait webDriverWait = null;

    /**
     * method name:readDataFile
     * @Description: Read the properties from property file
     * @return properties
     * @Description: returns property object
     */
    public Properties readDataFile() {
         Properties properties = null;
        try{
            File file = new File(Constants.PROPERTIES_FILE_PATH);
            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
            
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * method name:getInstanceOfWebDriverWait
     * @Description: if object is available then returns existing object otherwise it will create new objects (service locator pattern)
     * @param: driver
     * @Description: WebDriver Object
     * @param: time
     * @Description: Explicit time to wait
     * @return webDriverWait
     * @Description: returns WebDriverWait object
     */
    public WebDriverWait getInstanceOfWebDriverWait(WebDriver driver,Long time){
        if(webDriverWait==null){
        webDriverWait=new WebDriverWait(driver,time);
        }
        return webDriverWait;
    }

    /**
     * method name:clickElement
     * @Description: click action on WebElement
     * @param: element
     * @Description: WebElement where the value to be sent
     * @param: driver
     * @Description: Webdriver object
     * @param: time
     * @Description: Explicit wait time
     *
     */

    public void clickElement(WebElement element,WebDriver driver,long time){
        WebDriverWait wait=getInstanceOfWebDriverWait(driver,time);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * method name:explicitSendValue
     * @Description: send the value to the WebElement
     * @param: element
     * @Description: WebElement where the value to be sent
     * @param: driver
     * @Description: WebDriver object
     * @param: time
     * @Description: Explicit wait time
     * @param: value
     * @Description: input data Value to the WebElement
     */

    public void explicitSendValue(WebElement element,WebDriver driver,long time,String value){
        WebDriverWait wait=getInstanceOfWebDriverWait(driver,time);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    /**
     * method name: screenShot
     * @Description: screenShot for success scenario
     * @param: driver
     * @Description: WebDriver object for handling the screen shots
     */

    public void screenShot(WebDriver driver) {
        try{
            TakesScreenshot scrshot=((TakesScreenshot)driver);
            File SrcFile=scrshot.getScreenshotAs(OutputType.FILE);
            File DstFile=new File(Constants.HTML_REPORT_FILE_PATH);
            FileUtils.copyFile(SrcFile,DstFile);
        }catch(IOException e){
            e.printStackTrace();
        }

    }


}
