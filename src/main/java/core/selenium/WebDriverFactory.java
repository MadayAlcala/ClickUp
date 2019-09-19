/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package core.selenium;

import core.selenium.webdrivers.Chrome;
import core.selenium.webdrivers.Firefox;
import core.selenium.webdrivers.IBrowser;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * WebDriverFactory class.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public final class WebDriverFactory {
    private static final Map<String, IBrowser> DRIVER_MAP = new HashMap<>();
    static {
        DRIVER_MAP.put("chrome", new Chrome());
        DRIVER_MAP.put("firefox", new Firefox());
    }

    /**
     * Private constructor.
     */
    private WebDriverFactory() { }

    /**
     * Returns a WebDriver object associated to a given browser.
     *
     * @param browser String holding the name of the browser to be used. Currently it supports the following
     *                values:
     *                Chrome
     *                Firefox
     * @return a WebDriver object that makes it possible to connect to a webserver and perform requests via its UI
     * elements.
     */
    public static WebDriver getDriver(final String browser) {
        return DRIVER_MAP.get(browser.toLowerCase()).init();
    }
}

