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

import clickup.entities.Context;
import clickup.ui.BasePage;
import core.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Import page.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ImportPage extends BasePage {
    private Context context;
    private Boolean isSuccessfully;
    private static final String SPACE_MENU = "//div[@class='user-list-item ng-star-inserted']//div[./text()='%s']";
    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton2;

    @FindBy(xpath = "//*[contains(text(), 'Import/Export')]")
    private WebElement importExportMenuButton;

    @FindBy(css = ".cu-integration-btn__title")
    private WebElement csvFileButton;

    @FindBy(css = ".cu-btn_fw")
    private WebElement importFromCsvButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = ".flatfile-component iframe")
    private WebElement block;

    @FindBy(css = ".ReactModalPortal")
    private WebElement modalyesOrFalse;

    @FindBy(css = ".cu-btn.ng-star-inserted")
    private WebElement selectSpaceButton;

    @FindBy(css = ".cu-nav-sts__back")
    private WebElement backButton;

    @FindBy(css = ".cu-banner-popup__button.cu-banner-popup__button_purple")
    private WebElement bannerPurpleButton;

    @FindBy(css = ".cu-import-progress__data-status.importPending")
    private WebElement imputProgressButton;

    @FindBy(xpath = "//div[contains(@class,'col-required')][contains(.,'Task name')]")
    private WebElement taskNameTitleTable;

    @FindBy(css = ".cdk-overlay-container")
    private WebElement containerMenuModal;

    @FindBy(xpath = "//*[contains(text(), 'Delete imported')]")
    private WebElement deleteImportedButton;

    @FindBy(xpath = "//*[contains(text(), 'PAST')]")
    private WebElement pastImportButton;

    @FindBy(xpath = "//*[contains(text(), 'Delete tasks')]")
    private WebElement deleteTaskButton;

    @FindBy(xpath = "//*[contains(text(), 'ads')]")
    private WebElement spaceAds;

    @FindBy(xpath = "//*[contains(text(), 'pepito')]")
    private WebElement task1;

    @FindBy(xpath = "//*[contains(text(), 'Next')]")
    private WebElement continueButtonImport;

    @FindBy(xpath = "//div[contains(@class,'cu-settings-section cu-settings-section_open ng-star-inserted')]//*[contains(text(), 'Next')]")
    private WebElement continueButtonImport2;

    @FindBy(xpath = "//button[contains(@class,'cu-btn')][contains(text(), 'Start Import')]")
    private WebElement startImportButton;

    @FindBy(xpath = "//td[contains(@class,'htNoWrap')]")
    private WebElement dataTableCellFisrt;

    @FindBy(id = "final-close-include")
    private WebElement yesDialogButton;

    @FindBy(id = "intercom-frame")
    private WebElement intercomFrame;

    @FindBy(xpath = "//button[contains(@class,'button primary')][contains(text(), 'Complete')]")
    private WebElement buttonComplete;

    @FindBy(xpath = "//*[contains(text(), 'There is no data')]")
    private WebElement messageBox;

    /**
     * Constructor.
     *
     * @param context parameter.
     */
    public ImportPage(Context context) {
        this.context = context;
    }

    public void importTable(String fileImport) {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();
        getDriver().switchTo().frame(block);
        ImportModalPage importModalPage = new ImportModalPage();

        try {
            importModalPage.uploadFile(System.getProperty("user.dir") + "/src/test/resources/" + fileImport);
        } catch (AWTException e) {
            e.printStackTrace();
            Log.getInstance().getLog().error(e);
        }
        getDriver().switchTo().defaultContent();
        selectSpaceButton.click();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(500);
        getDriver().findElement(By.xpath(String.format(SPACE_MENU, context.getSpace().getTitle()))).click();
        continueButtonImport.click();
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_END);
        robot.keyRelease(KeyEvent.VK_END);
        robot.delay(50);
        continueButtonImport2.click();
        startImportButton.click();
        pastImportButton.click();
        imputProgressButton.click();
        isSuccessfully = imputProgressButton.isEnabled();
        backButton.click();
    }

    /**
     * Returns a space webElement.
     *
     * @param spaceName that is the name of the space to find.
     * @return WebElement 'space'.
     */
    private WebElement getSpaceElementByName(final String spaceName) {
        return getDriver().findElement(By.xpath(String.format(SPACE_MENU, spaceName)));
    }

    /**
     * Lets verify is the import is ok.
     * @return boolean variable.
     */
    public boolean isSuccessfullyImport() {
        return isSuccessfully;
    }

    /**
     * Lets delete a task.
     */
    public void deleImportedTask() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        pastImportButton.click();
        deleteImportedButton.click();
        getWait().until(ExpectedConditions.elementToBeClickable(deleteTaskButton));
        deleteTaskButton.click();
        backButton.click();
    }

    /**
     * Lets verify is the task was create.
     *
     * @return boolean variable.
     */
    public boolean isCreateTask() {
        spaceAds.click();
        boolean isCreate;
        getWait().until(ExpectedConditions.elementToBeClickable(task1));
        isCreate = task1.isDisplayed();
        return isCreate;
    }

    /**
     * Lest import a list.
     *
     * @param informationList variable.
     */
    public void manualImport(List<String> informationList) {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();
        getDriver().switchTo().frame(3);
        System.out.println(informationList.get(0));
        StringSelection stringSelection = new StringSelection(informationList.get(0) + "\n" + informationList.get(1) + "\n" + informationList.get(2));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot2 = null;
        try {
            robot2 = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot2.delay(1000);
        dataTableCellFisrt.click();
        robot2.keyPress(KeyEvent.VK_CONTROL);
        robot2.keyPress(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_V);
        robot2.keyRelease(KeyEvent.VK_CONTROL);
        continueButton.click();
        robot2.delay(1000);
        buttonComplete.click();
        yesDialogButton.click();
        getDriver().switchTo().defaultContent();
        selectSpaceButton.click();
        robot2.delay(500);
        getDriver().findElement(By.xpath(String.format(SPACE_MENU, context.getSpace().getTitle()))).click();
        robot2.delay(50);
        robot2.keyPress(KeyEvent.VK_END);
        robot2.keyRelease(KeyEvent.VK_END);
        robot2.delay(50);
        continueButtonImport.click();
        continueButtonImport2.click();
        robot2.delay(50);
        robot2.keyPress(KeyEvent.VK_END);
        robot2.keyRelease(KeyEvent.VK_END);
        robot2.delay(50);
        startImportButton.click();
        pastImportButton.click();
        imputProgressButton.click();
        isSuccessfully = imputProgressButton.isEnabled();
        backButton.click();
    }

    /**
     * Lets do steps for the test negative.
     */
    public void negativeTest() {
        getWait().until(ExpectedConditions.elementToBeClickable(spaceBarButton2));
        spaceBarButton2.click();
        importExportMenuButton.click();
        csvFileButton.click();
        importFromCsvButton.click();
        getDriver().switchTo().frame(3);
        continueButton.click();

    }

    /**
     * Verify is the message is show.
     * @return
     */
    public boolean isMessageShow() {
        return messageBox.isDisplayed();
    }
}
