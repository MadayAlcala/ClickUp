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

import java.util.HashMap;
import java.util.Map;

/**
 * PageTransporter class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public final class PageTransporter {
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("login", "login");
    }

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
        WebDriver webDriver = WebDriverManager.getInstance().getWebDriver();
        webDriver.navigate().to("https://app.clickup.com/".concat(map.get(url)));
    }
}
