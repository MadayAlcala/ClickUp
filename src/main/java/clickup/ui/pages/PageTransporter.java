/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui.pages;

import core.selenium.WebDriverManager;
import org.openqa.selenium.WebDriver;

/**
 * PageTransporter class.
 *
 * @author Maday Alcala
 * @version 0.0.1
 */
public final class PageTransporter {
    private static WebDriver webDriver;

    /**
     * This is the empty constructor according to checkstyle.
     */
    private PageTransporter() {
    }

    /**
     * This method is used for go to a page.
     *
     * @param url The parameter url defines a input url.
     */
    public static void goToUrl(final String url) {
        webDriver = WebDriverManager.getInstance().getWebDriver();
        webDriver.navigate().to(url);
    }
}
