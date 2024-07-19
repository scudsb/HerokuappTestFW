package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.testng.Assert;
import java.util.*;


public class BrokenImagesPage {
    public static Logger logger = LogManager.getLogger(BrokenImagesPage.class);
    WebDriver webdriver;
    @FindBy(xpath = "//h3")
    WebElement heading;
    @FindBys(@FindBy(tagName = "img"))
    List<WebElement> images;
    public List<WebElement> getImages() {
        return images;
    }
    public BrokenImagesPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    public String validateHeader(){
        return heading.getText().trim();
    }
}
