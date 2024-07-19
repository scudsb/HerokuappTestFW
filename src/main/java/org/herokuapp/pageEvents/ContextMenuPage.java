package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContextMenuPage {
    public static Logger logger = LogManager.getLogger(ContextMenuPage.class);
    WebDriver webDriver;
    @FindBy(xpath = "//*[@id='hot-spot']")
    WebElement contextMenuBox;

    public ContextMenuPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
    public void rightClickContextMenuBox(){
        Actions rightClick = new Actions(webDriver);
        rightClick.contextClick(contextMenuBox).build().perform();
    }

}
