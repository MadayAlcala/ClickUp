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
import core.utils.PropertyReader;
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * AlertModal Page Object Model.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class AlertModal extends BasePage {
    private static final String APP_CONFIG_FILE = "app.properties";
    private static final String URL_BASE = "url";
    private static final String TASK_PREFIX = "t/";

    @FindBy(css = "div.toast__undo.ng-tns-c0-0.ng-star-inserted")
    private WebElement creationPopUp;

    @FindBy(css = "span.toast__name-link-text")
    private WebElement creationConfirmationMessage;

    @FindBy(css = "div.toast__copy-button-link")
    private WebElement copyUrlLink;

    @FindBy(css = "div.toast__close-button-block")
    private WebElement closeButton;

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
     *
     * @return a new instance of Application Page Object Modal class.
     */
    public ApplicationPage closeModal() {
        WebElementActions.click(closeButton);
        return new ApplicationPage();
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
}
