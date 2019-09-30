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

import core.utils.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public final class LoginPage extends ApplicationBasePage {
    @FindBy(id = "email-input")
    private WebElement emailField;

    @FindBy(id = "password-input")
    private WebElement passwordField;

    @FindBy(css = ".log-in-button-text")
    private WebElement logInBtn;

    /**
     * Fills email in the 'email' field.
     *
     * @param email that is a valid email.
     */
    private void getEmailField(final String email) {
        Actions.sendKeys(emailField, email);
    }

    /**
     * Fills password in the 'password' field.
     *
     * @param password that is a valid password.
     */
    private void getPasswordField(final String password) {
        Actions.sendKeys(passwordField, password);
    }

    /**
     * Clicks in the login button.
     */
    private void getLoginField() {
        Actions.click(logInBtn);
    }

    /**
     * Fills in and send the user's credentials.
     *
     * @param email    a String containing the email associated to a given user.
     * @param password a String containing the password associated to a given user.
     */
    public void authenticate(final String email, final String password) {
        getEmailField(email);
        getPasswordField(password);
        getLoginField();
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
