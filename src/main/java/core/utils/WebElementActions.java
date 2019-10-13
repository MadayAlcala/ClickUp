/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package core.utils;

import core.selenium.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * WebElementActions class, does actions on web elements.
 *
 * @author Maday Alcala Cuba.
 * @version 1.0
 */
public final class WebElementActions {
    private static WebDriverWait webDriverWait = WebDriverManager.getInstance().getWebDriverWait();

    /**
     * Private constructor to comply checkStyle tool suggestions.
     */
    private WebElementActions() {
    }

    /**
     * Clicks on an web element.
     *
     * @param webElement to be clicked.
     */
    public static void click(final WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    /**
     * Returns the text of an Element.
     *
     * @param webElement from which wants to get its text.
     * @return the String of the web element
     */
    public static String getText(final WebElement webElement) {
        return webElement.getText();
    }

    /**
     * Sends keys of an web element.
     *
     * @param webElement to send the keys.
     */
    public static void enter(final WebElement webElement) {
        webElement.sendKeys(Keys.ENTER);
    }

    /**
     * Sends keys of an web element.
     *
     * @param webElement to send the keys.
     * @param key        that is a String type that want to send as a key.
     */
    public static void sendKeys(final WebElement webElement, final String key) {
        webElement.click();
        webElement.clear();
        webElement.sendKeys(key);
    }

    /**
     * Verifies if an element is present.
     *
     * @param webElement that is the element presence to verify.
     * @return true if the element is found and false if the element is not found.
     */
    public static boolean isElementPresent(final WebElement webElement) {
        boolean present;
        try {
            webElement.isDisplayed();
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }

    /**
     * Deletes text of a text field and writes a new text.
     *
     * @param webElement to send the keys.
     * @param key that is a String type that want to send as a key.
     */
    public static void sendKeysWithDeleteText(final WebElement webElement, final String key) {
        webElement.click();
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        webElement.sendKeys(key);
    }
}
