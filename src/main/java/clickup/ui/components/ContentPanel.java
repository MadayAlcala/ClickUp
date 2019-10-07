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

import clickup.entities.Task;
import clickup.ui.BasePage;
import clickup.ui.PageTransporter;
import core.utils.WebElementActions;
import core.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ContentPanel Object Model.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class ContentPanel extends BasePage {
    private static final String APP_CONFIG_FILE = "app.properties";
    private static final String URL_BASE = "url";
    private static final String TASK_PREFIX = "t/";
    private static final String TASK_LIST_ANCHORS = "a.cu-task-row-main__link[href='%s']";
    private static final String TASK_ANCHOR = "a[href='%s']";
    private static final String CARD_ELEMENT = "//cu-dashboard-board-card[contains(.,'%s')]";
    private static final String CONTENT_LIST_HEADER = "//cu-list-group[contains(.,'%s')]";

    @FindBy(css = "*[class *= 'list-group__add']")
    private WebElement newTaskLink;

    @FindBy(css = "cu-list-group input.cu-task-row-new__input")
    private WebElement newTaskNameTxtField;

    @FindBy(css = "div.toast__undo.ng-tns-c0-0.ng-star-inserted")
    private WebElement creationPopUp;

    @FindBy(css = "span.toast__name-link-text")
    private WebElement creationConfirmationMessage;

    @FindBy(css = "div.toast__copy-button-link")
    private WebElement copyUrlLink;

    @FindBy(css = "div.toast__close-button-block")
    private WebElement closeButton;

    @FindBy(css = ".cu-task-list-header-field__item:nth-child(1) .cu-task-list-header-field__title-text")
    private WebElement taskQtyLink;

    @FindBy(css = ".cu-search-filter .cu-search-filter__input")
    private WebElement searchTxtField;

    @FindBy(css = "cu-data-view-item:nth-child(2) > a.cu-data-view-item__link.cu-data-view-item__link_icon")
    private WebElement boardViewBtn;

    @FindBy(css = "div.cu-list-group__name")
    private WebElement listNameContentPanelLabelTxt;

    @FindBy(className = "cu-views-bar-title__list-btn-label")
    private WebElement listNameBarTitleLabelTxt;

    @FindBy(className = "cu-views-bar-title__label")
    private WebElement projectNameBarTitleLabelTxt;

    @FindBy(css = ".cu-panel-board:nth-child(1) > .cu-dashboard-board-card .cu-panel-board__header")
    private WebElement toDoColumn;

    @FindBy(css = ".cu-dashboard-board__column:nth-child(2) .cu-panel-board__column-drag")
    private WebElement completeColumn;

    /**
     * Visits the '+ New Task' hyperlink from the menu at the top of the List group in the body section.
     */
    private void followNewTaskLink() {
        newTaskLink.click();
    }

    /**
     * Creates a new Task.
     *
     * @param taskName A String containing the name of the Task to be created.
     */
    public void createTask(final String taskName) {
        followNewTaskLink();
        newTaskNameTxtField.sendKeys(taskName);
        newTaskNameTxtField.sendKeys(Keys.ENTER);
    }

    /**
     * Presses the 'Copy URL' hyperlink in the creation confirmation modal.
     */
    private void followCopyUrlLink() {
        WebElementActions.click(copyUrlLink);
    }

    /**
     * Retrieves the Task url from the clipboard.
     *
     * @return a String containing the Task url.
     * @throws UnsupportedFlavorException .
     * @throws IOException                .
     */
    private String getTaskUrl() throws UnsupportedFlavorException, IOException {
        followCopyUrlLink();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }

    /**
     * Closes the modal that appears after the creation of a task.
     */
    public void closeModal() {
        WebElementActions.click(closeButton);
    }

    /**
     * Returns a String containing the id assigned to a newly created Task.
     *
     * @return a String containing the id assigned to a newly created Task.
     * @throws UnsupportedFlavorException .
     * @throws IOException                .
     */
    public String extractTaskId() throws UnsupportedFlavorException, IOException {
        PropertyReader.loadFile(APP_CONFIG_FILE);
        PropertyReader.retrieveField(URL_BASE);
        return getTaskUrl().replace(PropertyReader.retrieveField(URL_BASE).concat(TASK_PREFIX), "");
    }

    /**
     * Returns the message on the pop up modal that appears after a Task is created.
     *
     * @return a String containing the message on the pop up modal that appears after a Task is created.
     */
    public String getConfirmationMessage() {
        getWait().until(ExpectedConditions.visibilityOf(creationConfirmationMessage));
        return creationConfirmationMessage.getText();
    }

    /**
     * Returns a string containing the name ot task specified by id.
     *
     * @return a string containing the name ot task specified by id
     */
    public String getTaskTitleById() {
        //TODO implementation pending.
        return null;
    }

    /**
     * Returns a webElement associated to a hyperlink.
     *
     * @param task a instance of a Task entity.
     * @return WebElement pointing to a hyperlink listed in the page.
     */
    public WebElement getAnchorElementByTask(final Task task) {
        String hyperLink = PageTransporter.getBaseUrl().concat(PageTransporter.getMap().get("task")
                .concat(task.getId()));
        return getDriver().findElement(By.cssSelector(String.format(TASK_LIST_ANCHORS, hyperLink)));
    }

    /**
     * Returns a webElement associated to a hyperlink.
     *
     * @param hyperLink a String containing a url address.
     * @return WebElement pointing to a hyperlink listed in the page.
     */
    private WebElement getAnchorElementByUrl(final String hyperLink) {
        return getDriver().findElement(By.cssSelector(String.format(TASK_ANCHOR, hyperLink)));
    }

    /**
     * Returns true if the task is listed in the page.
     *
     * @param taskId The id of a partiuclar Task.
     * @return true if the url is listed in the page.
     */
    public ArrayList<WebElement> collectWebElementsByTaskId(final String taskId) {
        List<WebElement> resultList = new ArrayList<WebElement>();
        String hyperLink = PageTransporter.getBaseUrl().concat(PageTransporter.getMap().get("task").concat(taskId));
        resultList = getDriver().findElements(By.cssSelector(String.format(TASK_ANCHOR, hyperLink)));
        return (ArrayList<WebElement>) resultList;
    }

    /**
     * Searches for a task by its id and retrieves its Name.
     *
     * @param taskId The id of a partiuclar Task.
     * @return a String containing the name assigned to the Task.
     */
    public String searchTaskByIdAndGetName(final String taskId) {
        String hyperLink = PageTransporter.getBaseUrl().concat(PageTransporter.getMap().get("task").concat(taskId));
        return WebElementActions.getText(getAnchorElementByUrl(hyperLink));
    }

    /**
     * Waits until confirmation message appears.
     */
    public void waitUntilMessagePops() {
        getWait().until(ExpectedConditions.visibilityOf(creationConfirmationMessage));
    }

    /**
     * Returns the name of listMenu webElement.
     *
     * @param listName that is the name of the list to find.
     * @return WebElement 'listMenu'.
     */
    private WebElement contentListHeader(final String listName) {
        return getDriver().findElement(By.xpath(String.format(CONTENT_LIST_HEADER, listName)));
    }

    /**
     * Creates multiple tasks, according to the list that it receives.
     *
     * @param taskList that is the list of tasks to create.
     * @param listName that is the name of the list to wait for create the tasks.
     */
    public void createListTasks(final List taskList, final String listName) {
        getWait().until(ExpectedConditions.visibilityOf(contentListHeader(listName)));
        followNewTaskLink();
        taskList.forEach(task -> {
            createTask((String) task);
            closeModal();
        });
        getWait().until(ExpectedConditions.visibilityOf(newTaskLink));
    }

    /**
     * Searches a task in the search filter.
     *
     * @param taskName that is a String of the task' name that wants to search.
     */
    public void searchTask(final String taskName) {
        getWait().until(ExpectedConditions.elementToBeClickable(searchTxtField));
        WebElementActions.click(searchTxtField);
        WebElementActions.sendKeys(searchTxtField, taskName);
        getWait().until(ExpectedConditions.textToBePresentInElement(searchTxtField, searchTxtField.getText()));
        searchTxtField.sendKeys(Keys.ENTER);
    }

    /**
     * Recovers the number of tasks found it in the content panel.
     *
     * @return a String with the number of tasks founded.
     */
    public String getTasksQuantity() {
        getWait().until(ExpectedConditions.textToBePresentInElement(searchTxtField, searchTxtField.getText()));
        String tasksQty = WebElementActions.getText(taskQtyLink);
        return tasksQty;
    }

    /**
     * Changes to board view.
     */
    public void setBoardView() {
        closeModal();
        boardViewBtn.click();
    }

    /**
     * Recovers the name of the List on content Panel.
     *
     * @param listName that is the name of the list to wait.
     * @return the name of the list on content Panel.
     */
    public String getContentListHeader(final String listName) {
        getWait().until(ExpectedConditions.visibilityOf(contentListHeader(listName)));
        return listNameContentPanelLabelTxt.getText();
    }

    /**
     * Recovers the name of the List on the bar title of content panel.
     *
     * @param listName listName that is the name of the list to wait.
     * @return the name of the list on the bar title of content Panel.
     */
    public String getBarTitleListName(final String listName) {
        getWait().until(ExpectedConditions.visibilityOf(listNameBarTitleLabelTxt));
        getWait().until(ExpectedConditions.visibilityOf(contentListHeader(listName)));
        return listNameBarTitleLabelTxt.getText();
    }

    /**
     * Recovers the name of the Project on the bar title of content panel.
     *
     * @return the name of the project on the bar title of content Panel.
     */
    public String getBarTitleProjectName() {
        getWait().until(ExpectedConditions.visibilityOf(projectNameBarTitleLabelTxt));
        String barTitleProjectName = projectNameBarTitleLabelTxt.getText();
        if (WebElementActions.isElementPresent(closeButton)) {
            closeModal();
            return barTitleProjectName;
        } else {
            return barTitleProjectName;
        }
    }

    /**
     * Returns a card webElement.
     *
     * @param cardName that is the name of the project to find.
     * @return WebElement 'card'.
     */
    private WebElement getCardElement(final String cardName) {
        return getDriver().findElement(By.xpath(String.format(CARD_ELEMENT, cardName)));
    }

    /**
     * Moves a task to different status in the list.
     *
     * @param cardName that is the name of the card to move.
     */
    public void moveTask(final String cardName) {
        Actions builder = new Actions(getDriver());
        builder.dragAndDrop(getCardElement(cardName), toDoColumn).perform();
        builder.dragAndDrop(getCardElement(cardName), completeColumn).perform();
        builder.build();
    }

    /**
     * Returns true if the task is in the complete status.
     *
     * @param taskName that is the name of the task to find.
     * @return a conditional agreement whether the task is found or not.
     */
    public boolean containsTask(final String taskName) {
        return completeColumn.getText().contains(taskName);
    }
}
