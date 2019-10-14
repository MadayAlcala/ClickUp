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
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(css = ".task-history.task-history__scroll-view")
    private WebElement historyScrollListContainer;

    @FindBy(css = ".cu-task-history-item:last-of-type .task-history-item__content")
    private WebElement lastHistoryEntryMessage;

    @FindBy(css = "input.task-uploader__input")
    private WebElement taskUploader;

    @FindBy(css = ".attachment-tile__title-text")
    private WebElement attachmentFileNameText;

    /**
     * Selects a user from a dropdown list and assigns him the Task.
     *
     * @param userFullName a String containing the Full Name associated to a given user's account.
     */
    public void assignTaskToUser(final String userFullName) {
        WebElementActions.click(addUserDropdownButton);
        for (WebElement element : userSelectorLink) {
            if (element.getText().equals(userFullName)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Reads the last message appended to the history section of a Task.
     * @return a String containing the last appended message to the Task's history.
     */
    public String readLastTaskHistory() {
        getWait().until(ExpectedConditions.visibilityOf(historyScrollListContainer));
        return lastHistoryEntryMessage.getText();
    }

    /**
     * Uploads a file to the Modal of a Task.
     *
     * @param filePath a String containing the absolute path of the file to be uploaded.
     */
    public void attachFile(final String filePath) {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].setAttribute('style', 'visibility:visible;');", taskUploader);
        String absoluteFilePath = System.getProperty("user.dir").concat(System.getProperty("file.separator"))
                .concat(filePath);
        taskUploader.sendKeys(absoluteFilePath);
    }

    /**
     * Returns the name (from the Task modal page) of a file attached to a Task.
     *
     * @return a String containing the name of the attached file.
     */
    public String getAttachmentFileName() {
        return attachmentFileNameText.getText().replaceAll("\n", "");
    }

    /**
     * Closes a given Task modal window.
     *
     * @return a new instance of ApplicationPage class.
     */
    public ApplicationPage close() {
        WebElementActions.click(taskCloseButton);
        return new ApplicationPage();
    }
}
