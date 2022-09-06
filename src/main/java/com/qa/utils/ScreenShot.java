package com.qa.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    public static void takeScreenshot(WebDriver driver, String fileName) {
        String dateName = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("./screenshots/" + fileName + "_" + dateName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
