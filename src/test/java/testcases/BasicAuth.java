package testcases;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.pageEvents.AddRemoveElementsPage;
import org.herokuapp.pageEvents.BasicAuthPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.testng.annotations.Test;

public class BasicAuth extends baseTest{
    public static Logger logger = LogManager.getLogger(BasicAuth.class);
    BasicAuthPage basicAuthPage;
    @Test(groups = {"new"})
    public void enterValidCredentials(){
        basicAuthPage = new BasicAuthPage(webDriver);
        logger.debug("Inside enterValidCredentials");
        String url = config.getProperty("test_url").replace("//", "//admin:admin@") + "basic_auth";
        webDriver.get(url);
        basicAuthPage.validateSuccessMessage();
        TakeScreenshot.takeScreenshot(webDriver, "Success Message", testLogger);
        testLogger.log(Status.PASS, "Valid Credentials");
    }
}
