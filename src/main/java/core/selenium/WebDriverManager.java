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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * WebDriverConfig.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class WebDriverManager {
    private static WebDriverManager instance;
    private WebDriverConfig webDriverConfig;
    private WebDriverWait webDriverWait;
    private static WebDriver webDriver;

    /**
     * Singleton private constructor.
     */
    private WebDriverManager() {
        initialize();
    }

    /**
     * Singleton instance getter.
     *
     * @return the same instancstatic e for WebDriverManager class.
     */
    public static WebDriverManager getInstance() {
        if (webDriver == null || instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    /**
     * Initial actions to be performed when instantiating WebDriverManager for the first time.
     */
    public void initialize() {
        webDriverConfig = WebDriverConfig.getInstance();
        webDriver = WebDriverFactory.getDriver(webDriverConfig.getBrowser());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(webDriverConfig.getImplicitWaitTime(), TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, webDriverConfig.getExplicitWaitTime());
    }

    /**
     * Returns the WebDriverWait member of this class.
     *
     * @return a WebDriverWait object.
     */
    public WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    /**
     * Returns the WebDriver member of this class.
     *
     * @return a WebDriver object.
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }
}
