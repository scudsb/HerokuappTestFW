package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.pageEvents.AddRemoveElementsPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

public class AddDeleteElementsTest extends baseTest{
    public static Logger logger = LogManager.getLogger(AddDeleteElementsTest.class);
    WelcomePage welcomePage;
    AddRemoveElementsPage addRemoveElementsPage;

    @Test(priority = 1, groups = {"done","addremoveelements"})
    public void addingSingleElement(){
        welcomePage = new WelcomePage(webDriver);
        addRemoveElementsPage = new AddRemoveElementsPage(webDriver);
        logger.debug("Inside addingSingleElement");
        welcomePage.clickAddRemoveElements();
        addRemoveElementsPage.clickAddElementButton();
        Assert.assertEquals(addRemoveElementsPage.findTotalDeleteButtons(), 1);
        TakeScreenshot.takeScreenshot(webDriver,"AddSingleElement", testLogger);
    }
    @Test(priority = 2, groups = {"done", "addremoveelements"})
    public void addingMultipleElements(){
        welcomePage = new WelcomePage(webDriver);
        addRemoveElementsPage = new AddRemoveElementsPage((webDriver));
        logger.debug("Inside addingMultipleElement");
        welcomePage.clickAddRemoveElements();
        IntStream.range(0, 10).forEach(count -> addRemoveElementsPage.clickAddElementButton());
        Assert.assertEquals(addRemoveElementsPage.findTotalDeleteButtons(), 10);
        TakeScreenshot.takeScreenshot(webDriver,"AddMultipleElements", testLogger);
    }
    @Test(priority = 3, groups = {"done","addremoveelements"})
    public void deletingSingleElement(){
        welcomePage = new WelcomePage(webDriver);
        addRemoveElementsPage = new AddRemoveElementsPage(webDriver);
        logger.debug("Inside deletingSingleElement");
        //First add the delete button
        welcomePage.clickAddRemoveElements();
        addRemoveElementsPage.clickAddElementButton();
        Assert.assertEquals(addRemoveElementsPage.findTotalDeleteButtons(), 1);
        testLogger.pass("1 Button added successfully");
        TakeScreenshot.takeScreenshot(webDriver,"AddSingleElement", testLogger);

        //delete the added button and validate
        addRemoveElementsPage.clickFirstDeleteButton();
        Assert.assertEquals(addRemoveElementsPage.findTotalDeleteButtons(), 0);
        TakeScreenshot.takeScreenshot(webDriver,"DeletedSingleElement", testLogger);
    }

    @Test(priority = 4, groups = {"done","addremoveelements"})
    public void deletingMultipleElements(){
        welcomePage = new WelcomePage(webDriver);
        addRemoveElementsPage = new AddRemoveElementsPage(webDriver);
        logger.debug("Inside deletingMultipleElements");
        //First add the 10 delete buttons
        welcomePage.clickAddRemoveElements();
        IntStream.range(0, 10).forEach(count -> addRemoveElementsPage.clickAddElementButton());
        Assert.assertEquals(addRemoveElementsPage.findTotalDeleteButtons(), 10);
        TakeScreenshot.takeScreenshot(webDriver,"AddMultipleElements", testLogger);

        //delete the added buttons and validate
        IntStream.range(0, 10).forEach(count -> addRemoveElementsPage.clickFirstDeleteButton());
        Assert.assertEquals(addRemoveElementsPage.findTotalDeleteButtons(), 0);
        TakeScreenshot.takeScreenshot(webDriver,"DeletedMultipleElements", testLogger);
    }
}
