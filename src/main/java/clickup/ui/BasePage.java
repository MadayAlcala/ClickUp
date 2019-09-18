/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui;

import core.selenium.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage class, this class is the intermediary between the 'WebDriverManager' class and the pages.
 *
 * @author Maday Alcala
 * @version 0.0.1
 */
public abstract class BasePage {
    protected WebDriver driver;

    /**
     * This method initializes the base class.
     */
    public BasePage() {
        this.driver = WebDriverManager.getInstance().getWebDriver();
        PageFactory.initElements(driver, this);
    }

    /**
     * This method closes the driver.
     */
    public void quitWindow() {
        driver.quit();
    }
}
