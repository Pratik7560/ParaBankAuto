package com.parabank.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        //  Unique Timestamp
        String dateName = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // for screenshot save
        String destination = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + "_" + dateName + ".png";
        File finalDestination = new File(destination);

        try {
            FileUtils.copyFile(source, finalDestination);
            Log.info("Screenshot captured successfully at: " + destination);
        } catch (IOException e) {
            Log.error("Failed to capture screenshot: " + e.getMessage());
        }
        return destination;
    }
}