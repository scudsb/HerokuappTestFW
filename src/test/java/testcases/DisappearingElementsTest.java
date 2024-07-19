package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.pageEvents.DisappearingElementsPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DisappearingElementsTest extends baseTest{
    public static Logger logger = LogManager.getLogger(DisappearingElementsTest.class);
    WelcomePage welcomePage;
    DisappearingElementsPage disappearingElementsPage;

    @Test(priority = 1, groups = {"done"})
    public void validateDisappearanceofLink(){
        welcomePage = new WelcomePage(webDriver);
        disappearingElementsPage = new DisappearingElementsPage(webDriver);

        welcomePage.clickDisappearingElementsLink();
        //refresh the page until the Gallery Link appears
        while(!disappearingElementsPage.isGalleryLinkPresent()){
            webDriver.navigate().refresh();
        }
        Assert.assertTrue(disappearingElementsPage.isGalleryLinkPresent());
        logger.info("Gallery link is present");
        TakeScreenshot.takeScreenshot(webDriver, "Gallery Link", testLogger);

        //refresh the page until the Gallery Link disappears
        while(disappearingElementsPage.isGalleryLinkPresent()){
            webDriver.navigate().refresh();
        }
        Assert.assertFalse(disappearingElementsPage.isGalleryLinkPresent());
        logger.info("Gallery link is not present");
        TakeScreenshot.takeScreenshot(webDriver, "No Gallery Link", testLogger);
    }
}
