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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage class.
 */
public final class LoginPage extends BasePage {
    @FindBy(id = "email-input")
    private WebElement emailField;

    @FindBy(id = "password-input")
    private WebElement passwordField;

    @FindBy(css = ".log-in-button-text")
    private WebElement logInBtn;

    @FindBy(xpath = "//cu-team-avatar/div")
    private WebElement avatar;

    @FindBy(css = ".cu-user-settings-menu__column > .cu-user-settings-menu__title > .cu-user-settings-menu__title-name")
    private WebElement titleName;

    /**
     * Fills email in the 'email' field.
     *
     * @param email that is a valid email.
     */
    private void getEmailField(final String email) {
        emailField.sendKeys(email);
    }

    /**
     * Fills password in the 'password' field.
     *
     * @param password that is a valid password.
     */
    private void getPasswordField(final String password) {
        passwordField.sendKeys(password);
    }

    /**
     * Clicks in the login button.
     */
    private void getLoginField() {
        logInBtn.click();
    }

    /**
     * Clicks on the account avatar.
     */
    public void getAvatar() {
        avatar.click();
    }

    /**
     * Gets the account title name.
     *
     * @return a String with the title name.
     */
    public String getTitleName() {
        return titleName.getText();
    }

    /**
     * Fills in and send the user's credentials.
     *
     * @param email a String containing the email associated to a given user.
     * @param password a String containing the password associated to a given user.
     */
    public void authenticate(final String email, final String password) {
        getEmailField(email);
        getPasswordField(password);
        getLoginField();
    }
}
