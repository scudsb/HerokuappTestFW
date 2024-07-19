package org.herokuapp.pageEvents;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.testng.Assert;

import java.util.List;

public class BasicAuthPage {
    public static Logger logger = LogManager.getLogger(BasicAuthPage.class);
    WebDriver webdriver;
    @FindBy(tagName = "p")
    WebElement successMessage;
    @FindBy(tagName = "body")
    WebElement webPageBody;

    public BasicAuthPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    public String validateSuccessMessage() {
        return successMessage.getText().trim();
    }
}
