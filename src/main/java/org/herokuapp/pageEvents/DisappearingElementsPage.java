package org.herokuapp.pageEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DisappearingElementsPage {
    public static Logger logger = LogManager.getLogger(DisappearingElementsPage.class);
    WebDriver webdriver;
    @FindBy(xpath = "//a[contains(text(),'Gallery')]")
    List<WebElement> galleryLink;

    public DisappearingElementsPage(WebDriver driver){
        this.webdriver = driver;
        PageFactory.initElements(webdriver, this);
    }

    /**
     * The problem is the pattern itself. It uses @FindBy annotation (used by PageFactory to init the fields that
     * must be wrapped by Proxy) that replaces the standard elements with their proxy instances which contain
     * InvocationHandler.
     *
     * Each time you try to access a field, annotated with @FindBy, the invocation handler tries to find the element
     * using the default ElementLocator.The problem is that the ElementLocator.findElement() method throws an
     * TimeoutException / NoSuchElementException if there are no elements presented in the DOM.
     *
     * Approach 1 - Therefore, each time you need to check whether an element is displayed or not you have to search
     * for a List of elements and check its size.
     * Approach 2 - Add a try catch while accessing the element and access any property and return true or
     * false accordingly
     */
    public boolean isGalleryLinkPresent(){
        return (galleryLink != null && !galleryLink.isEmpty());
    }
}
