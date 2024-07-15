package org.herokuapp.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WaitElementLoad extends SetUpDriver{
    public static Logger logger = LogManager.getLogger(WaitElementLoad.class);
    public static boolean waitForElementToLoad(WebElement webElement){
        logger.debug("Inside waitForElementToLoad");
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        }catch(WebDriverException ex){
            logger.error("Exception caught - " + ex.getMessage());
            return false;
        }
    }
}
