package testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.pageEvents.DragAndDropPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends baseTest{
    public static Logger logger = LogManager.getLogger(DragAndDropTest.class);
    WelcomePage welcomePage;
    DragAndDropPage dragAndDropPage;

    @Test(priority = 1, groups = {"done"})
    public void dragAtoB(){
        logger.info("Inside dragAtoB method");
        welcomePage = new WelcomePage(webDriver);
        dragAndDropPage = new DragAndDropPage(webDriver);

        welcomePage.clickDragAndDropLink();
        logger.info("Before drag and drop");
        TakeScreenshot.takeScreenshot(webDriver,"Original places", testLogger);
        //drag box a and drop it on B
        Actions dragAndDropAction = new Actions(webDriver);
        dragAndDropAction.dragAndDrop(dragAndDropPage.getBoxA(), dragAndDropPage.getBoxB()).build().perform();

        Assert.assertEquals(dragAndDropPage.getBoxAHeader(), "B");
        Assert.assertEquals(dragAndDropPage.getBoxBHeader(), "A");
        TakeScreenshot.takeScreenshot(webDriver,"Swapped places", testLogger);
        logger.info("After drag and drop");
    }

    @Test(priority = 2, groups = {"done"})
    public void dragBtoA() {
        logger.info("Inside dragBtoA method");
        welcomePage = new WelcomePage(webDriver);
        dragAndDropPage = new DragAndDropPage(webDriver);

        welcomePage.clickDragAndDropLink();
        logger.info("Before initial drag and drop");
        //drag box a and drop it on B
        Actions dragAndDropAction = new Actions(webDriver);
        dragAndDropAction.dragAndDrop(dragAndDropPage.getBoxA(), dragAndDropPage.getBoxB()).build().perform();
        logger.info("After initial drag and drop");
        logger.info("Before final drag and drop");
        TakeScreenshot.takeScreenshot(webDriver, "Original places", testLogger);
        dragAndDropAction = new Actions(webDriver);
        dragAndDropAction.dragAndDrop(dragAndDropPage.getBoxA(), dragAndDropPage.getBoxB()).build().perform();
        Assert.assertEquals(dragAndDropPage.getBoxAHeader(), "A");
        Assert.assertEquals(dragAndDropPage.getBoxBHeader(), "B");
        TakeScreenshot.takeScreenshot(webDriver, "Swapped places", testLogger);
        logger.info("After final drag and drop");
    }
}
