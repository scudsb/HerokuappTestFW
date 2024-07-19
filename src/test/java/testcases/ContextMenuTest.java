package testcases;

import org.herokuapp.pageEvents.ContextMenuPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.*;

public class ContextMenuTest extends baseTest{
    public static Logger logger = Logger.getLogger(ContextMenuTest.class);
    ContextMenuPage contextMenuPage;
    WelcomePage welcomePage;

    @Test(priority = 1, groups = {"done"})
    public void checkAlertOnRightClick(){
        contextMenuPage = new ContextMenuPage(webDriver);
        welcomePage = new WelcomePage(webDriver);

        welcomePage.clickContextMenuLink();
        contextMenuPage.rightClickContextMenuBox();//this generates an alert
        String alertText = webDriver.switchTo().alert().getText();
        Assert.assertEquals(alertText, "You selected a context menu");
    }
}
