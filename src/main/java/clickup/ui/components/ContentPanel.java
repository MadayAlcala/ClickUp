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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
     * @throws IOException .
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
     * @throws IOException .
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
}
