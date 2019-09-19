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

import core.utils.PropertyReader;

/**
 * WebDriverConfig.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class WebDriverConfig {
    private static WebDriverConfig instance;
    private final String defaultBrowserConfigFile = "browser.properties";
    private String browser;
    private int implicitWaitTime;
    private int explicitWaitTime;

    /**
     * Singleton private constructor.
     */
    private WebDriverConfig() {
        initialize();
    }

    /**
     * Singleton instance getter.
     *
     * @return the same instance for this class.
     */
    static WebDriverConfig getInstance() {
        if (instance == null) {
            instance = new WebDriverConfig();
        }
        return instance;
    }

    /**
     * Initial actions to be performed when instantiating WebDriverConfig for the first class.
     */
    public void initialize() {
        PropertyReader.loadFile(defaultBrowserConfigFile);
        if (System.getProperty("BROWSER") != null) {
            browser = System.getProperty("BROWSER");
        } else {
            browser = PropertyReader.retrieveField("BROWSER");
        }
        implicitWaitTime = Integer.parseInt(PropertyReader.retrieveField("implicitWaitTime"));
        explicitWaitTime = Integer.parseInt(PropertyReader.retrieveField("explicitWaitTime"));
    }

    /**
     * Returns the browser member of this class.
     *
     * @return a String holding the name of the browser the driver will be configured to work with.
     */

    public String getBrowser() {
        return browser;
    }

    /**
     * The period of time configured for Selenium implicit waits.
     *
     * @return a integer value representing the Selenium implicit wait value.
     */
    public int getImplicitWaitTime() {
        return implicitWaitTime;
    }

    /**
     * The period of time configured for Selenium explicit wait.
     *
     * @return a integer value representing the Selenium explicit wait value.
     */
    public int getExplicitWaitTime() {
        return explicitWaitTime;
    }
}
