package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddRemoveElementsPage {
    public static Logger logger = LogManager.getLogger(AddRemoveElementsPage.class);
    WebDriver webdriver;
    @FindBy(xpath = "//button[text()=\"Add Element\"]")
    WebElement addElementButton;
    @FindBys({@FindBy(xpath = "//button[text()='Delete']")})
    List<WebElement> deleteButtons;

    public AddRemoveElementsPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }
    public void clickAddElementButton(){
        addElementButton.click();
    }
    public int findTotalDeleteButtons(){
        return deleteButtons.size();
    }
    public void clickFirstDeleteButton(){
        deleteButtons.get(0).click();
    }
}
