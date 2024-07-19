package testcases;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.herokuapp.pageEvents.BrokenImagesPage;
import org.herokuapp.pageEvents.WelcomePage;
import org.herokuapp.utilities.TakeScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class BrokenImagesTest extends baseTest{
    public static Logger logger = LogManager.getLogger(BrokenImagesPage.class);
    BrokenImagesPage brokenImagesPage;
    WelcomePage welcomePage;

    //Check for broken images approach 1
    /**
    1. Import required packages Apache HttpClient - HttpClient library is used for checking the status codes of the images
    present on the page
    2. Find all the images on the page
    3. Create a new instance of HttpClient
    4. Create a new instance of HttpGet
    5. Retrieve the response object
    6. Read the Status Code
     */
    @Test(priority = 1, groups = {"done"})
    public void checkForBrokenImages(){
        try {
            AtomicInteger brokenImageCounter = new AtomicInteger();
            brokenImageCounter.set(0);

            logger.debug("Inside checkForBrokenImages method");
            welcomePage = new WelcomePage(webDriver);
            brokenImagesPage = new BrokenImagesPage(webDriver);

            welcomePage.clickBrokenImages();
            String actualHeader = brokenImagesPage.validateHeader();
            Assert.assertEquals(actualHeader, "Broken Images");

            List<WebElement> images = brokenImagesPage.getImages();
            images.stream().map(image -> new HttpGet(image.getAttribute("src"))).forEach(httpGet -> {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                        if (httpResponse.getCode() != 200) {
                            brokenImageCounter.getAndIncrement();
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            logger.info("Total number of broken images is {}", brokenImageCounter.get());
            Assert.assertEquals(brokenImageCounter.get(), 0);
        }catch (Exception ex){
            TakeScreenshot.takeScreenshot(webDriver, "Exception", testLogger);
            logger.error("Exception caught - " + ex.getMessage());
        }
    }
    //Check for broken images approach 2
    /**
     *1. Find all the images on the page
     * 2. Read the naturalWidth attribute
     */
    @Test(priority = 2, groups = {"done"})
    public void checkForBrokenImagesApproach2(){
        try {
            AtomicInteger brokenImageCounter = new AtomicInteger();
            brokenImageCounter.set(0);

            logger.debug("Inside checkForBrokenImages method");
            welcomePage = new WelcomePage(webDriver);
            brokenImagesPage = new BrokenImagesPage(webDriver);

            welcomePage.clickBrokenImages();
            String actualHeader = brokenImagesPage.validateHeader();
            Assert.assertEquals(actualHeader, "Broken Images");

            List<WebElement> images = brokenImagesPage.getImages();
            images.forEach(image -> {
                if (image.getAttribute("naturalWidth").equals("0")) {
                    logger.error("Image " + brokenImageCounter.get() + " is broken. src = '"
                            + image.getAttribute("src") + "'");
                    brokenImageCounter.getAndIncrement();
                }
            });
            logger.info("Total number of broken images is {}", brokenImageCounter.get());
            Assert.assertEquals(brokenImageCounter.get(), 0);
        }catch (Exception ex){
            TakeScreenshot.takeScreenshot(webDriver, "Exception", testLogger);
            logger.error("Exception caught - " + ex.getMessage());
        }
    }
}
