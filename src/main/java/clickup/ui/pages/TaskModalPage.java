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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @FindBy(css = "div.cu-user-group.cu-user-group_due-date.due-date_view-task.ng-star-inserted svg")
    private WebElement dateSelectorButton;

    @FindBy(css = ".cu-editor-content .ql-editor.ql-blank")
    private WebElement descriptionTextField;


    @FindBy(css = ".cu-editor-content .ql-editor > div")
    private WebElement filledDescriptionTextField;

    @FindBy(css = ".cu-priorities-view__icon-select.cu-priorities-view__icon-select-large svg")
    private WebElement prioritySelector;

    @FindBy(css = ".priorities-list__item-name")
    private List<WebElement> priorityOptions;

    @FindBy(css = ".due-date__row.due-date_view-task.ng-star-inserted .due-date__info-value-display > span")
    private List<WebElement> datesText;

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

    /**
     * Transitions from a Date Modal to a Task Modal.
     *
     * @return a new instance of Task Modal POM.
     */
    public DateModalPage goToDateModalPage() {
        WebElementActions.click(dateSelectorButton);
        return new DateModalPage();
    }

    /**
     * Retrieves the description field of a Task Modal Page.
     *
     * @return the description of the current Task Modal page.
     */
    public String getDescriptionField() {
        return WebElementActions.getText(filledDescriptionTextField);
    }

    /**
     * Fills the description field of a Task Modal Page.
     *
     * @param description a String containing the description for a Task.
     */
    public void setDescriptionField(final String description) {
        WebElementActions.sendKeys(descriptionTextField, description);
    }

    /**
     * Selects the priority for a Task.
     *
     * @param priority a String containing the priority to be set for a given Task.
     */
    public void setTaskPriority(final String priority) {
        WebElementActions.click(prioritySelector);
        for (WebElement element : priorityOptions) {
            if (element.getText().equals(priority)) {
                element.click();
                break;
            }
        }
    }

    /**
     * Retrieves the current programmed start date of the task from the task page.
     *
     * @return the current programmed start date of the task from the task page.
     */
    private String getScheduledStartDate() {
        return WebElementActions.getText(datesText.get(0));
    }

    /**
     * Retrieves the current programmed due date of the task from the task page.
     *
     * @return the current programmed due date of the task from the task page.
     */
    private String getScheduledDueDate() {
        return WebElementActions.getText(datesText.get(1));
    }

    /**
     * Returns a Map containing the set of keys declared as part of the configuration to be checked against.
     *
     * @return a Map containing the set of keys declared as part of the configuration to be checked against.
     */
    public Map getCurrentTaskConfiguration() {
        Map map = new HashMap<String, String>();
        map.put("Description", getDescriptionField());
        map.put("Start Date", getScheduledStartDate());
        map.put("Due Date", getScheduledDueDate());
        return map;
    }
}
