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
import clickup.ui.pages.HomeModal;
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
    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton;

    /**
     * Clicks on the account avatar.
     *
     * @return a HomeModal instance.
     */
    public HomeModal displayUserMenu() {
        getWait().until(ExpectedConditions.visibilityOf(spaceBarButton));
        WebElementActions.click(spaceBarButton);
        return new HomeModal();
    }

    /**
     * Waits for the visibility of the toolbar menu button.
     */
    public void waitForPageLoading() {
        getWait().until(ExpectedConditions.visibilityOf(spaceBarButton));
    }
}
