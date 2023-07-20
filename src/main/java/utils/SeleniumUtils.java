package utils;

import drivers.DriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumUtils {

    private static Actions getActions() {
        return new Actions(DriverFactory.getDriver());
    }

    public static Boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static String getElementsText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getElementsAttribute(WebElement element, String name) {
        try {
            return element.getAttribute(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
