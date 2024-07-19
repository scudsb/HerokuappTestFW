package org.herokuapp.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class SetUpDriver {
    public static Logger logger = LogManager.getLogger(SetUpDriver.class);
    public static WebDriver webDriver;
    public static Properties config;

    /**
     * Method to set up the driver based on the browser mentioned in the config file
     */
    public static WebDriver setUpDriver() {
        logger.debug("Inside setUpDriver");
        if (config == null) {
            config = LoadConfigFile.loadConfigProperties();
        }
        switch (config.getProperty("browser").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                logger.debug("Incorrect browser specified. Initiating chrome.");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }
        webDriver.get(config.getProperty("test_url"));
        webDriver.manage().window().maximize();
        //TakeScreenshot.takeScreenshot(webDriver,"WelcomePage",);
        return webDriver;
    }
}
