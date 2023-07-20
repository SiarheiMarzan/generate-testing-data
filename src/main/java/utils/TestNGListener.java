package utils;

import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestNGListener extends TestListenerAdapter {
    private void takeScreenshot(ITestResult result) {
        TakesScreenshot screenshotDriver = getScreenshotDriver();
        if (screenshotDriver != null) {
            byte[] screenshotBytes = screenshotDriver.getScreenshotAs(OutputType.BYTES);
            try {
                String screenshotName = result.getName() + "_" + System.currentTimeMillis() + ".png";
                Files.createDirectories(Paths.get("allure-results"));
                Files.write(Paths.get("allure-results", screenshotName), screenshotBytes);
                Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshotBytes));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private TakesScreenshot getScreenshotDriver() {
        if (DriverFactory.isScreenshotSupported()) {
            return (TakesScreenshot) DriverFactory.getDriver();
        } else {
            System.out.println("Screenshot is not supported by the WebDriver.");
            return null;
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            attachScreenshot();
        }
    }

    private void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result);
    }
}