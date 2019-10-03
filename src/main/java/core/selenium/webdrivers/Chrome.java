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

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Chrome Driver configurator class.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class Chrome implements IBrowser {
    /**
     * Initializes options of the Chrome WebDriver.
     *
     * @return a WebDriver object configured for Chrome browser.
     */
    public WebDriver init() {
        ChromeDriverManager.chromedriver().version("76.0.3809.126").setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usagge");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(chromeOptions);
    }
}
