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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
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

    public void getEmailField(String email) {
        emailField.sendKeys(email);
    }

    public void getPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    public void getLoginField() {
        logInBtn.click();
    }

    public void getAvatar() {
        avatar.click();
    }

    public String getTitleName() {
        return avatar.getText();
    }
}
