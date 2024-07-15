package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.utilities.TakeScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class BasicAuthPage {
    public static Logger logger = LogManager.getLogger(BasicAuthPage.class);
    WebDriver webdriver;
    @FindBy(tagName = "p")
    WebElement successMessage;

    public BasicAuthPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    public void validateSuccessMessage() {
        String msg = successMessage.getText().trim();
        Assert.assertEquals(msg, "Congratulations! You must have the proper credentials.");
    }
}
