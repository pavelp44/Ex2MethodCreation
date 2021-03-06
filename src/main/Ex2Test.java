package main;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ex2Test {

    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception

    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("AutomationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/pavel/Documents/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);
    }

    @After

    public void tearDown() {
        driver.quit();
    }


    @Test

    public void Ex2MethodCreation() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search field",
                10);

        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search field",
                5);

        String search_field_text = titleElement.getAttribute("text");

        Assert.assertEquals(
                "String in search fiend is not equal 'Search...'",
                "Search…",
                search_field_text);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {

        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
    }
}
