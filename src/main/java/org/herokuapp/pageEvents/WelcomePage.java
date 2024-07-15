package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.utilities.WaitElementLoad;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WelcomePage {
    public static Logger logger = LogManager.getLogger(WelcomePage.class);
    WebDriver webdriver;


    //region Headings
    @FindBy(xpath = "//h1")
    WebElement heading1;
    @FindBy(xpath = "//h2")
    WebElement heading2;
    //endregion Headings

    @FindBy(linkText = "Add/Remove Elements")
    WebElement addRemoveLink;
    @FindBy(linkText = "Broken Images")
    WebElement brokenImagesLink;
    @FindBy(tagName = "h3")
    WebElement brokenImagesHeading;
    @FindBy(linkText = "Challenging DOM")
    WebElement challengingDOMLink;
    @FindBy(xpath = "(//a[contains(@class,'button')])[1]")
    WebElement challengingDOMButton;
    @FindBy(linkText = "Checkboxes")
    WebElement checkboxesLink;
    @FindBy(xpath = "//form[@id='checkboxes']")
    WebElement checkboxesForm;
    @FindBy(linkText = "Context Menu")
    WebElement contextMenuLink;
    @FindBy(xpath = "//div[@id='hot-spot']")
    WebElement contextMenuBox;
    public WelcomePage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    /**
     * String concatenation does a lot of work without knowing if it is needed or not (the traditional "is debugging
     * enabled" test known from log4j), and should be avoided if possible, as the {} allows delaying the toString() call
     * and string construction to after it has been decided if the event needs capturing or not. By having the logger
     * format a single string the code becomes cleaner in my opinion.
     */
    public void verifyPageLoad(){
        logger.debug("Inside verifyPageLoad");
        String h1 = heading1.getText();
        String h2 = heading2.getText();
        logger.info("Heading 1 - {}; Heading 2 - {}", h1, h2);
        Assert.assertEquals(h1, "Welcome to the-internet");
        Assert.assertEquals(h2, "Available Examples");
        logger.info("Heading Assertions complete");
    }

    public void clickAddRemoveElements(){
        addRemoveLink.click();
    }
    public void clickBrokenImages(){
        brokenImagesLink.click();
        Assert.assertTrue(WaitElementLoad.waitForElementToLoad(brokenImagesHeading));
    }
    public void clickChallengingDOM(){
        challengingDOMLink.click();
        Assert.assertTrue(WaitElementLoad.waitForElementToLoad(challengingDOMButton));
    }
    public void clickCheckboxes(){
        checkboxesLink.click();
        Assert.assertTrue(WaitElementLoad.waitForElementToLoad(checkboxesForm));
    }
    public void clickContextMenu(){
        contextMenuLink.click();
        Assert.assertTrue(WaitElementLoad.waitForElementToLoad(contextMenuBox));
    }

}
