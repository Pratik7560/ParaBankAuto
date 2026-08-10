package com.parabank.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.parabank.utils.ConfigReader;
import com.parabank.utils.Log;

public class BaseTest {

    
   public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        Log.info("Launching Chrome Browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        String url = ConfigReader.getProperty("url");
        Log.info("Navigating to URL: " + url);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            Log.info("Closing Browser.");
            driver.quit();
        }
    }
}