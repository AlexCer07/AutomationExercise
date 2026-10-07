package Listeners;

import Resources.ExtendReporterNG;
import TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener extends BaseTest implements ITestListener {

    private static final Logger log =
            LogManager.getLogger(TestListener.class);


    ExtentTest test;
    ExtentReports extent = ExtendReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {

        log.info("==================================================");
        log.info("INICIANDO TEST: {}", result.getMethod().getMethodName());
        log.info("==================================================");

        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info("TEST PASSED: {}", result.getMethod().getMethodName());
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        try {

            Object testClass = result.getInstance();

            BaseTest baseTest = (BaseTest) testClass;

            WebDriver driver = baseTest.getDriver();

            if (driver != null) {

                log.error("URL al momento del error: {}",
                        driver.getCurrentUrl());

                log.error("Título de página: {}",
                        driver.getTitle());
            }

        } catch (Exception e) {

            log.warn("No fue posible obtener información del navegador");
        }

        if (result.getThrowable() != null) {

            log.error(
                    "Excepción producida durante la prueba:",
                    result.getThrowable()
            );
        }

        extentTest.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }


        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());


    }

    @Override
    public void onTestSkipped(ITestResult result) {

        log.warn("TEST SKIPPED: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }
}