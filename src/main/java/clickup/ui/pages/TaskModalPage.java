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
import core.utils.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Task Modal Page Object Model.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */

public class TaskModalPage extends BasePage {
    @FindBy(css = ".cu-user-group__icon > svg")
    private WebElement addUserDropdownButton;

    @FindBy(css = ".task-close > svg")
    private WebElement taskCloseButton;

    @FindBy(css = ".user-list-item > .user-list-item__name")
    private List<WebElement> userSelectorLink;

    /**
     * Selects a user from a dropdown list and assigns him the Task.
     *
     * @param userFullName a String containing the Full Name associated to a given user's account.
     */
    public void assignTaskToUser(final String userFullName) {
        Actions.click(addUserDropdownButton);
        for (WebElement element : userSelectorLink) {
            if (element.getText().equals(userFullName)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Closes a given Task modal window.
     */
    public void close() {
        Actions.click(taskCloseButton);
    }
}
