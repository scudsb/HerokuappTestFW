package org.herokuapp.utilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.herokuapp.utilities.SetUpDriver.config;

public class TakeScreenshot{
    public static Logger logger = LogManager.getLogger(TakeScreenshot.class);
    public static void takeScreenshot(WebDriver webDriver, String name, ExtentTest testLogger){
        try{
            logger.debug("Taking Screenshot...");
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM_dd_yy_HH_mm_ss");

            String fileName = System.getProperty("user.dir") + config.getProperty("screenshot_folder") +
                    name + dateTime.format(dateFormatter) + ".png";
            logger.debug("File Name for screenshot - "+ fileName);
            File screenshotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            String base64 = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
            FileUtils.copyFile(screenshotFile, new File(fileName));
            //testLogger.log(Status.INFO, name, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
            testLogger.log(Status.INFO, name, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        }catch (IOException ex){
            System.out.println("Exception Caught - " + ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
        }
    }
}
