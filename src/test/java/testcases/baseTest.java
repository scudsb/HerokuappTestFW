package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.*;
import org.herokuapp.utilities.LoadConfigFile;
import org.herokuapp.utilities.SetUpDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;


public class baseTest {
    public static Properties config;
    public static ExtentReports extentReport = new ExtentReports();
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest testLogger;
    public static WebDriver webDriver;
    public static String screenshotFile;
    public static Logger logger = LogManager.getLogger(baseTest.class);

    /**
     * Setting up the reporter and reporting configuration using spark reporter and reading values from config file
     */
    @BeforeTest(alwaysRun = true)
    public static void beforeTest(){
        try {
            logger.info("Inside method - beforeTest");
            config = LoadConfigFile.loadConfigProperties();
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM_dd_yy_HH_mm_ss");
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + config.getProperty("report_folder") + config.getProperty("report_name")
            + dateTime.format(dateFormatter));
            extentReport.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle(config.getProperty("document_title"));
            Thread currentThread = Thread.currentThread();
            currentThread.setName(config.getProperty("report_name") + dateTime.format(dateFormatter));
        }catch(Exception ex){
            logger.error("Exception caught - " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * Method to create an extent test instance and set up the driver
     */
    @BeforeMethod(alwaysRun = true)
    public static void beforeMethod(Method testMethod) {
        try {
            logger.info("Inside method - beforeTest");
            testLogger = extentReport.createTest(testMethod.getName());
            //sparkReporter.config().setReportName(testMethod.getName());
            webDriver = SetUpDriver.setUpDriver();
            screenshotFile = System.getProperty("user.dir") + config.getProperty("screenshot_folder") +
                    testMethod.getName() + ".png";
        } catch (Exception ex) {
            logger.error("Exception caught - " + Arrays.toString(ex.getStackTrace()));
        }
    }


    /**
     * Method to log the test status after the test case execution using extent test
     */
    @AfterMethod(alwaysRun = true)
    public static void afterMethod(ITestResult testResult){
        try {
            logger.info("Inside method - afterMethod");
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS:
                    logger.debug("Test status is success");
                    testLogger.log(Status.PASS, MarkupHelper.createLabel(testResult.getName() + " - Test Case Passed", ExtentColor.GREEN));
                    break;
                case ITestResult.FAILURE:
                    logger.debug("Test status is failure");
                    testLogger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName() + " - Test Case Failed", ExtentColor.RED));
                    testLogger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getThrowable().getMessage(), ExtentColor.RED),
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotFile).build());
                    break;
                case ITestResult.SKIP:
                    logger.debug("Test status is skip");
                    testLogger.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName() + " - Test Case Failed", ExtentColor.AMBER));
                    break;
                default:
                    logger.debug("Test status is incorrect");
                    break;
            }
            webDriver.close();
            //webDriver.quit();
        } catch (Exception ex) {
            logger.error("Exception caught - " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * Method to close & quite driver instances after all test cases are complete and flush out the extent test
     */
    @AfterTest(alwaysRun = true)
    public static void afterTest() {
        logger.info("Inside method - afterTest");
        extentReport.flush();
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
