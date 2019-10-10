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

import clickup.entities.List;
import clickup.ui.BasePage;
import clickup.ui.pages.ListMenuModal;
import clickup.ui.pages.NewProjectModal;
import core.utils.WebElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ListPanel class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListPanel extends BasePage {
    private static final String LIST_BTN = "//cu-nav-section//a[contains(.,'%s')]";
    private static final String PROJECT_BTN = "//cu-nav-category//a[contains(.,'%s')]";
    private static final String LIST_MENU_BTN = "//following-sibling::div[@class='nav-section__menu']";
    private static final String PROJECT_MENU_BTN = "//following-sibling::div[@class='nav-category__menu']";
    @FindBy(css = ".sidebar-section__plus-icon")
    private WebElement addBtn;

    @FindBy(css = ".lv-empty_img > img")
    private WebElement emptyTaskListImg;

    @FindBy(xpath = " //a[@cutooltip='Rename']")
    private WebElement renameBtn;

    @FindBy(css = " .cu-dropdown-list-item__icon_new-folder")
    private WebElement folderBox;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement listNameMakerTxtField;

    @FindBy(css = ".nav-editor__input")
    private WebElement listNameEditorTxtField;

    @FindBy(css = "div.cu-list-group__name > cu-editable.cu-editable")
    private WebElement listNameHeader;

    @FindBy(css = "div.toast__undo.ng-tns-c0-0.ng-star-inserted")
    private WebElement informationPopUp;

    @FindBy(css = ".toast__undo-content")
    private WebElement copyConfirmationMessage;

    @FindBy(className = "cu-modal__control-item cu-modal__close icon")
    private WebElement folderCloseButton;

    @FindBy(css = "div.toast__close-button-block")
    private WebElement closeButton;

    @FindBy(xpath = "//a[contains(.,'Move')]")
    private WebElement moveLink;

    /**
     * Selects the '+' symbol to displayed their options.
     */
    private void addBtn() {
        WebElementActions.click(addBtn);
    }

    /**
     * Selects the option 'New List' to create a new List.
     */
    private void getListBox() {
        WebElementActions.click(listBox);
    }

    /**
     * Creates a new list.
     *
     * @param listName that is the name of the new List.
     */
    public void addNewList(final String listName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(listNameHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        addBtn();
        getListBox();
        WebElementActions.sendKeys(listNameMakerTxtField, listName);
        WebElementActions.enter(listNameMakerTxtField);
        getWait().until(ExpectedConditions.visibilityOf(listNameHeader));
        waitForHeaderElementTextEqualsCreatedListName(listName);
    }

    /**
     * Returns a list webElement.
     *
     * @param listName that is the name of the list to find.
     * @return WebElement 'list'.
     */
    public WebElement getListElementByName(final String listName) {
        return getDriver().findElement(By.xpath(String.format(LIST_BTN, listName)));
    }

    /**
     * Returns the Name of the new List.
     *
     * @param listName to find the specific list element.
     * @return a String with the name of list created.
     */
    public String getNameList(final String listName) {
        return WebElementActions.getText(getListElementByName(listName));
    }

    /**
     * Returns a displayListMenu webElement.
     *
     * @param listName that is the name of the list to find.
     * @return WebElement 'displayListMenu'.
     */
    private WebElement getListMenuElementByName(final String listName) {
        return getDriver().findElement(By.xpath(String.format(LIST_BTN, listName).concat(LIST_MENU_BTN)));
    }

    /**
     * Selects the list' menu.
     *
     * @param listName that is the name of the list.
     */
    public ListMenuModal displayListMenu(final String listName) {
        WebElementActions.click(getListMenuElementByName(listName));
        return new ListMenuModal();
    }

//    /**
//     * Selects delete button.
//     *
//     * @param listName that is the name of the list.
//     */
//    public void deleteList(final String listName) {
//        displayListMenu(listName);
//        getWait().until(ExpectedConditions.or(
//                ExpectedConditions.visibilityOf(listNameHeader),
//                ExpectedConditions.visibilityOf(emptyTaskListImg)
//        ));
//    }

    /**
     * Updates the name of a List.
     *
     * @param newListName that is the new name for the list.
     */
    public void updateList(final String newListName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(listNameHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        displayListMenu(newListName);
        WebElementActions.click(renameBtn);
        listNameEditorTxtField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        listNameEditorTxtField.sendKeys(newListName);
        listNameEditorTxtField.sendKeys(Keys.ENTER);
        getWait().until(ExpectedConditions.visibilityOf(listNameHeader));
    }

    /**
     * Creates a new folder.
     *
     * @param folderName that is going to be the name of the new folder.
     */
    public NewProjectModal addNewFolder(final String folderName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(listNameHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        addBtn();
        WebElementActions.click(folderBox);
        return new NewProjectModal();
    }

    /**
     * Closes the modal that appears after the copy of a project.
     */
    public void closeModal() {
        WebElementActions.click(closeButton);
    }

    /**
     * Returns the message on the pop up modal that appears after a List or folder is copied.
     *
     * @return a String containing the message on the pop up modal that appears after a some actions is realized.
     */
    public String getCopyConfirmationMessage() {
        getWait().until(ExpectedConditions.visibilityOf(informationPopUp));
        String result = copyConfirmationMessage.getText();        getWait().until(ExpectedConditions.elementToBeClickable(closeButton));
        closeModal();
        return result;
    }

    /**
     * Returns a project webElement.
     *
     * @param projectName that is the name of the project to find.
     * @return WebElement 'list'.
     */
    private WebElement getProjectElementByName(final String projectName) {
        return getDriver().findElement(By.xpath(String.format(PROJECT_BTN, projectName)));
    }

    /**
     * Returns the Name of the new Project.
     *
     * @param projectName to find the specific project element.
     * @return a String with the name of project created.
     */
    public String getNameProject(final String projectName) {
        WebElementActions.click(getProjectElementByName(projectName));
        getWait().until(ExpectedConditions.visibilityOf(getProjectElementByName(projectName)));
        return WebElementActions.getText(getProjectElementByName(projectName));
    }

    /**
     * Returns a displayProjectMenu webElement.
     *
     * @param projectName that is the name of the project to find.
     * @return WebElement 'projectName'.
     */
    private WebElement getProjectMenuElementByName(final String projectName) {
        return getDriver().findElement(By.xpath(String.format(PROJECT_BTN, projectName).concat(PROJECT_MENU_BTN)));
    }

    /**
     * Selects the list' menu.
     *
     * @param projectName that is the name of the project.
     */
    public ListMenuModal displayProjectMenu(final String projectName) {
        WebElementActions.click(getProjectMenuElementByName(projectName));
        return new ListMenuModal();
    }

//    /**
//     * Selects delete button.
//     *
//     * @param projectName that is the name of the project.
//     */
//    public void deleteProject(final String projectName) {
//        displayProjectMenu(projectName);
//        getWait().until(ExpectedConditions.or(
//                ExpectedConditions.visibilityOf(listNameHeader),
//                ExpectedConditions.visibilityOf(emptyTaskListImg)
//        ));
//    }

    /**
     * Waits for the List Name To appear on the content panel.
     *
     * @param newListName a String containing the title of a list, most likely a newly created one.
     */
    public void waitForHeaderElementTextEqualsCreatedListName(final String newListName) {
        ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> listNameHeader.getText().equals(newListName);
        getWait().until(elementTextEqualsString);
    }

//    /**
//     * Copies a list.
//     *
//     * @param listName     that represent the list to copy.
//     * @param copyListName that represent the new name for the list to copy.
//     */
//    public void copylist(final String listName, final String copyListName) {
//        displayListMenu(listName);
//        WebElementActions.click(copyLink);
//        listBox.click();
//        folderNameTxtBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        folderNameTxtBox.sendKeys(copyListName);
//        folderNameTxtBox.click();
//        WebElementActions.click(copyFolderBtn);
//    }

//    /**
//     * Copies a project.
//     *
//     * @param projectName     that represent the project to copy.
//     * @param copyProjectName that represent the new name for the project to copy.
//     */
//    public void copyProject(final String projectName, final String copyProjectName) {
//        displayProjectMenu(projectName);
//        WebElementActions.click(copyLink);
//        folderNameTxtBox.click();
//        folderNameTxtBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        folderNameTxtBox.sendKeys(copyProjectName);
//        WebElementActions.click(copyFolderBtn);
//    }
}
