package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class DynamicControlsPage {
    public static Logger logger = LogManager.getLogger(DynamicControlsPage.class);
    WebDriver webdriver;
    public DynamicControlsPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }
    @FindBy(xpath = "//div[@id='checkbox']//input[@type='checkbox']")
    List<WebElement> checkbox;
    public void clickCheckBox(){
        checkbox.get(0).click();
    }
    public boolean isCheckboxPresent(){
        return checkbox != null && !checkbox.isEmpty() && checkbox.get(0).isDisplayed();
    }
    @FindBy(xpath = "//form[@id='checkbox-example']//button[normalize-space()='Remove']")
    WebElement removeButton;
    public void clickRemoveButton(){
        removeButton.click();
    }
    @FindBy(xpath = "//form[@id='checkbox-example']//p[@id='message']")
    WebElement checkBoxMessage;
    public String checkBoxMessage(){
        return checkBoxMessage.getText();
    }

    @FindBy(xpath = "//form[@id='checkbox-example']//button[normalize-space()='Add']")
    WebElement addButton;
    public void clickAddButton(){
        addButton.click();
    }
    @FindBy(xpath = "//form[@id='input-example']//button[normalize-space()='Enable']")
    WebElement enableButton;
    public void clickEnable(){
        enableButton.click();
    }
    @FindBy(xpath = "//form[@id='input-example']//input[@type='text']")
    WebElement textBox;
    public boolean isTextBoxEnabled(){
        return textBox.isEnabled();
    }
    @FindBy(xpath = "//form[@id='input-example']//p[@id='message']")
    WebElement textBoxMessage;
    public String textBoxMessage(){
        return textBoxMessage.getText();
    }
}
