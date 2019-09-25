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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * BasePage class, this class is the intermediary between the 'WebDriverManager' class and the pages.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    /**
     * Initializes the base class.
     */
    public BasePage() {
        this.driver = WebDriverManager.getInstance().getWebDriver();
        this.wait = WebDriverManager.getInstance().getWebDriverWait();
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * @return a single WebDriver instance.
     */
    protected WebDriver getDriver() {
        return driver;
    }

    /**
     * @return a single WebDriverWait instance.
     */
    public WebDriverWait getWait() {
        return wait;
    }
}
