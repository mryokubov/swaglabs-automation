package com.swaglabs;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import com.swaglas.config.ConfigReader;
import com.swaglas.driver.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseTest {

    static WebDriver driver;
    Faker faker;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    /**
     * All the necessary configuration for your html report
     */
    @BeforeSuite
    public void setupExtent() {
        extent = new ExtentReports();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ExtentSparkReporter spark = new ExtentSparkReporter("test_report/ExtentReports_" + timestamp + ".html");
        extent.attachReporter(spark);

        // Adding system information to the report
        extent.setSystemInfo("Username", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Environment", "QA");
    }

    @BeforeMethod
    public void beforeMethod() {
        faker = new Faker();
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().log(Status.FAIL, result.getThrowable());
            String screenshotPath = takeScreenshot(result.getName());
            test.get().addScreenCaptureFromPath("../" + screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().log(Status.PASS, "Test Passed");
        } else {
            test.get().log(Status.SKIP, "Test Skipped");
        }
        Driver.quitDriver();
        driver = null;
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
    }

    public String takeScreenshot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destination = "test_report/screenshots/" + screenshotName + "_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("test_report/screenshots/"));
            Files.copy(source.toPath(), Paths.get(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }
}
