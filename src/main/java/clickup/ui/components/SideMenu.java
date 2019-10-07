/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui.components;

import clickup.ui.BasePage;
import clickup.ui.pages.LoginPage;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * SideMenu class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class SideMenu extends BasePage {
    @FindBy(css = "[class='cu-user-settings-menu__link cu-user-settings-menu__link_logout']")
    private WebElement logOutButton;

    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton;

    @FindBy(css = ".cu-user-settings-menu__column > .cu-user-settings-menu__title .cu-user-settings-menu__title-name")
    private WebElement titleNameTxt;

    /**
     * Clicks on the account avatar.
     */
    private void displayUserMenu() {
        WebElementActions.click(spaceBarButton);
    }

    /**
     * Clicks on the account avatar.
     */
    private void logOutBtn() {
        WebElementActions.click(logOutButton);
    }

    /**
     * Gets the account title name.
     *
     * @return a String with the title name.
     */
    public String getTitleName() {
        displayUserMenu();
        String titleName = WebElementActions.getText(titleNameTxt);
        displayUserMenu();
        return titleName;
    }

    /**
     * Lets log out from the main page.
     */
    public void logOut() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton));
        WebElementActions.click(spaceBarButton);
        WebElementActions.click(logOutButton);
        LoginPage loginPage = new LoginPage();
        getWait().until(ExpectedConditions.visibilityOf(loginPage.getEmailWebElement()));
    }
}
