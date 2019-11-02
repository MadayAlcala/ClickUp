/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package core.selenium.webdrivers;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Firefox Driver configurator class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class Firefox implements IBrowser {
    /**
     * Initializes options of the Firefox WebDriver.
     *
     * @return a WebDriver object configured for Firefox browser.
     */
    public WebDriver init() {
        FirefoxDriverManager.firefoxdriver().version("0.24.0").setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new FirefoxDriver(firefoxOptions);
    }
}
