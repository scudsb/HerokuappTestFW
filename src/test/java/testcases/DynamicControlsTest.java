package testcases;

import org.herokuapp.pageEvents.ContextMenuPage;
import org.herokuapp.pageEvents.DynamicControlsPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class DynamicControlsTest extends baseTest{
    public static Logger logger = Logger.getLogger(DynamicControlsTest.class);
    DynamicControlsPage dynamicControlsPage;
    WelcomePage welcomePage;
    @Test(priority = 1, groups = {"new"})
    public void removeCheckBox(){
        welcomePage = new WelcomePage(webDriver);
        dynamicControlsPage = new DynamicControlsPage(webDriver);


    }
}
