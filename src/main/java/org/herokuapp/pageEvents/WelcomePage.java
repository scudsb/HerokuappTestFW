package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
    public static Logger logger = LogManager.getLogger(WelcomePage.class);
    WebDriver webdriver;
    public WelcomePage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    @FindBy(xpath = "//h1")
    WebElement heading1;
    @FindBy(xpath = "//h2")
    WebElement heading2;
    /**
     * String concatenation does a lot of work without knowing if it is needed or not (the traditional "is debugging
     * enabled" test known from log4j), and should be avoided if possible, as the {} allows delaying the toString() call
     * and string construction to after it has been decided if the event needs capturing or not. By having the logger
     * format a single string the code becomes cleaner in my opinion.
     */
    public String[] verifyPageLoad(){
        logger.debug("Inside verifyPageLoad");
        String h1 = heading1.getText();
        String h2 = heading2.getText();
        logger.info("Heading 1 - {}; Heading 2 - {}", h1, h2);
        return new String[] {h1,h2};
    }

    @FindBy(linkText = "Add/Remove Elements")
    WebElement addRemoveLink;
    public void clickAddRemoveElements(){
        addRemoveLink.click();
    }

    @FindBy(linkText = "Broken Images")
    WebElement brokenImagesLink;
    public void clickBrokenImages(){
        brokenImagesLink.click();
    }
    @FindBy(linkText = "Context Menu")
    WebElement contextMenuLink;
    public void clickContextMenuLink() {
        contextMenuLink.click();
    }

    @FindBy(linkText = "Disappearing Elements")
    WebElement disappearingElement;
    public void clickDisappearingElementsLink(){
        disappearingElement.click();
    }
    @FindBy(linkText = "Drag and Drop")
    WebElement dragAndDropLink;
    public void clickDragAndDropLink(){
        dragAndDropLink.click();
    }
}
