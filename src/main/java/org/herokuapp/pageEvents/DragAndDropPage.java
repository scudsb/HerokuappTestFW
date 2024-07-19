package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDropPage {
    public static Logger logger = LogManager.getLogger(DragAndDropPage.class);
    WebDriver webdriver;
    public DragAndDropPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(id = "column-a")
    WebElement boxA;
    public WebElement getBoxA() {
        return boxA;
    }
    @FindBy(id = "column-b")
    WebElement boxB;
    public WebElement getBoxB() {
        return boxB;
    }
    @FindBy(xpath = "//div[@id='column-a']//header")
    WebElement boxAHeader;
    public String getBoxAHeader(){
        return boxAHeader.getText();
    }
    @FindBy(xpath = "//div[@id='column-b']//header")
    WebElement boxBHeader;
    public String getBoxBHeader(){
        return boxBHeader.getText();
    }
}
