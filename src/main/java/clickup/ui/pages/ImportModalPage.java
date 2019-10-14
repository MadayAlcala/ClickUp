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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Import modal.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ImportModalPage extends BasePage {
    @FindBy(css = ".icon-upload")
    private WebElement uploadButton;

    @FindBy(id = "headerConfirm")
    private WebElement yesButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "final-close-include")
    private WebElement yesDialogButton;

    @FindBy(xpath = "//button[contains(@class,'button primary')][contains(text(), 'Complete')]")
    private WebElement buttonComplete;

    /**
     * Lets upload a file.
     *
     * @param fileImport variable.
     * @throws AWTException control the exception.
     */
    public void uploadFile(String fileImport) throws AWTException {
        uploadButton.click();
        StringSelection stringSelection = new StringSelection(fileImport);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        yesButton.click();
        continueButton.click();
        buttonComplete.click();
        yesDialogButton.click();
    }
}
