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

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Actions class, does actions on web elements.
 *
 * @author Maday Alcala Cuba.
 * @version 1.0
 */
public final class Actions {

    /**
     * Private constructor to comply checkStyle tool suggestions.
     */
    private Actions() {
    }

    /**
     * Clicks on an web element.
     *
     * @param webElement to be clicked.
     */
    public static void click(final WebElement webElement) {
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
     * @param key        that is a Key type that want to send as a key.
     */
    public static void sendKeys(final WebElement webElement, final Keys key) {
        webElement.sendKeys(key);
    }

    /**
     * Sends keys of an web element.
     *
     * @param webElement to send the keys.
     * @param key        that is a String type that want to send as a key.
     */
    public static void sendKeys(final WebElement webElement, final String key) {
//        webElement.click();
        webElement.clear();
        webElement.sendKeys(key);
    }
}
