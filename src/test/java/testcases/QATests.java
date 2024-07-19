package testcases;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.pageEvents.AddRemoveElementsPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.IntStream;


public class QATests extends baseTest{
    public static Logger logger = LogManager.getLogger(QATests.class);
    WelcomePage welcomePage;
    AddRemoveElementsPage addRemoveElementsPage;
    @Test(groups = {"done"})
    public void navigateToWebsite(){
        welcomePage = new WelcomePage(webDriver);
        logger.debug("Inside navigateToWebsite");
        //welcomePage = new WelcomePage(webDriver);
        String[] headings = welcomePage.verifyPageLoad();
        Assert.assertEquals(headings[0], "Welcome to the-internet");
        Assert.assertEquals(headings[1], "Available Examples");
        logger.info("Heading Assertions complete");
        testLogger.log(Status.PASS, "Welcome page is loaded");
    }
}
