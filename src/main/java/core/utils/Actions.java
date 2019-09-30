package core.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Actions {
    public static void click(WebElement webElement) {
        webElement.click();
    }

    public static String getText(WebElement webElement) {
        return webElement.getText();
    }

    public static void sendKeys(WebElement webElement, Keys key) {
        webElement.sendKeys(key);
    }

    public static void sendKeys(WebElement webElement, String key) {
//        webElement.click();
//        webElement.clear();
        webElement.sendKeys(key);
    }
}
