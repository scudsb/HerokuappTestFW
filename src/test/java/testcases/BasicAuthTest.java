package testcases;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.*;
import org.herokuapp.pageEvents.BasicAuthPage;
import org.herokuapp.utilities.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.*;

public class BasicAuthTest extends baseTest{
    public static Logger logger = LogManager.getLogger(BasicAuthTest.class);
    BasicAuthPage basicAuthPage;
    @Test(priority = 1, groups = {"done"})
    public void enterValidCredentials(){
        basicAuthPage = new BasicAuthPage(webDriver);
        logger.debug("Inside enterValidCredentials");
        String url = config.getProperty("test_url").replace("//", "//admin:admin@") + "basic_auth";
        webDriver.get(url);
        String successMessage = basicAuthPage.validateSuccessMessage();
        Assert.assertEquals(successMessage, "Congratulations! You must have the proper credentials.");
        TakeScreenshot.takeScreenshot(webDriver, "Success Message", testLogger);
        testLogger.log(Status.PASS, "Valid Credentials");
    }

    /**
     * Doesn't work - doesn't take screenshot
     */
    @Test(priority = 2, groups = {"done"})
    public void invalidUserName() throws InterruptedException {
        basicAuthPage = new BasicAuthPage(webDriver);
        logger.debug("Inside invalidUserName");
        String url = config.getProperty("test_url").replace("//", "//admin1:admin@") + "basic_auth";
        webDriver.get(url);
//        Actions escape = new Actions(webDriver);
//        webDriver.manage().window().maximize();
//        escape.sendKeys(Keys.ESCAPE).build().perform();
//        Thread.sleep(2000);
//        basicAuthPage.validateFailureMessage();
        TakeScreenshot.takeScreenshot(webDriver, "Not authorized", testLogger);
        testLogger.log(Status.PASS, "Invalid Credentials");
    }
}
