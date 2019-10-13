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

import clickup.ui.BasePage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * LoginPage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public final class LoginPage extends BasePage {
    @FindBy(id = "email-input")
    @CacheLookup
    private WebElement emailField;

    @FindBy(css = ".log-in-button-text")
    @CacheLookup
    private WebElement logInBtn;

    @FindBy(id = "password-input")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(css = ".cu-user-settings-menu__column > .cu-user-settings-menu__title > .cu-user-settings-menu__title-name")
    @CacheLookup
    private WebElement titleNameTxt;

    /**
     * Fills email in the 'email' field.
     *
     * @param email that is a valid email.
     */
    private void getEmailField(final String email) {
        WebElementActions.sendKeys(emailField, email);
    }

    /**
     * Fills password in the 'password' field.
     *
     * @param password that is a valid password.
     */
    private void getPasswordField(final String password) {
        WebElementActions.sendKeys(passwordField, password);
    }

    /**
     * Clicks in the login button.
     */
    private void getLoginField() {
        WebElementActions.click(logInBtn);
    }

    /**
     * Fills in and send the user's credentials.
     *
     * @param email    a String containing the email associated to a given user.
     * @param password a String containing the password associated to a given user.
     * @return an ApplicationPage instance.
     */
    public ApplicationPage authenticate(final String email, final String password) {
        getWait().until(ExpectedConditions.visibilityOf(emailField));
        getEmailField(email);
        getPasswordField(password);
        getLoginField();
        return new ApplicationPage();
    }

    /**
     * Returns the Selenium WebElement associated to email input field.
     *
     * @return a WebElement instance pointing to the email input field.
     */
    public WebElement getEmailWebElement() {
        return emailField;
    }
}
