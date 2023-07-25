package drivers;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static WebDriver webDriver = null;

    private DriverFactory() {
    }

    public static synchronized WebDriver getDriver() {
        if (null != webDriver)
            return webDriver;
        var browserName = System.getProperty("browser", "Chrome");
        switch (browserName) {
            case "Firefox":
                webDriver = FirefoxST.getFFDriver();
                break;
            case "Chrome":
                webDriver = ChromeST.getChromeDriver();
                break;
            default:
                webDriver = null;
                break;
        }
        return webDriver;
    }

    public static synchronized void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public static synchronized boolean isScreenshotSupported() {
        return webDriver instanceof TakesScreenshot;
    }
}
