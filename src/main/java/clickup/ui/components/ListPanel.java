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
import core.utils.WebElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(css = ".cu-btn_danger > .cu-btn__text")
    private WebElement confirmDeleteBtn;

    @FindBy(xpath = "//a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(xpath = " //a[@cutooltip='Rename']")
    private WebElement renameBtn;

    @FindBy(css = "img[src *= 'no-lists']")
    private WebElement emptyTaskListImg;

    @FindBy(css = " .cu-dropdown-list-item__icon_new-folder")
    private WebElement folderBox;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement listNameMakerTxtField;

    @FindBy(css = ".nav-editor__input")
    private WebElement listNameEditorTxtField;

    @FindBy(css = "*[class *= 'item-label-body cu-task-list-header']")
    private WebElement taskListHeader;

    @FindBy(css = "div.toast__undo.ng-tns-c0-0.ng-star-inserted")
    private WebElement informationPopUp;

    @FindBy(css = ".toast__undo-content")
    private WebElement copyConfirmationMessage;

    @FindBy(css = ".cu-form__input")
    private WebElement folderNameTxtBox;

    @FindBy(className = "cu-modal__control-item cu-modal__close icon")
    private WebElement folderCloseButton;

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
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        addBtn();
        getListBox();
        WebElementActions.sendKeys(listNameMakerTxtField, listName);
        WebElementActions.enter(listNameMakerTxtField);
        getWait().until(ExpectedConditions.visibilityOf(taskListHeader));
    }

    /**
     * Returns a list webElement.
     *
     * @param listName that is the name of the list to find.
     * @return WebElement 'list'.
     */
    private WebElement getListElementByName(final String listName) {
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
    private void displayListMenu(final String listName) {
        WebElementActions.click(getListMenuElementByName(listName));
    }

    /**
     * Selects delete button.
     *
     * @param listName that is the name of the list.
     */
    public void deleteList(final String listName) {
        displayListMenu(listName);
        WebElementActions.click(deleteBtn);
        getWait().until(ExpectedConditions.visibilityOf(confirmDeleteBtn));
        getWait().until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn));
        WebElementActions.click(confirmDeleteBtn);
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
    }

    /**
     * Updates the name of a List.
     *
     * @param newListName that is the new name for the list.
     */
    public void updateList(final String newListName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        displayListMenu(newListName);
        WebElementActions.click(renameBtn);
        listNameEditorTxtField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        listNameEditorTxtField.sendKeys(newListName);
        listNameEditorTxtField.sendKeys(Keys.ENTER);
        getWait().until(ExpectedConditions.visibilityOf(taskListHeader));
    }

    /**
     * Creates a new folder.
     *
     * @param folderName that is going to be the name of the new folder.
     */
    public void addNewFolder(final String folderName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        addBtn();
        WebElementActions.click(folderBox);
        WebElementActions.sendKeys(folderNameTxtBox, folderName);
        WebElementActions.enter(folderNameTxtBox);
        WebElementActions.click(getProjectElementByName(folderName));
        getWait().until(ExpectedConditions.visibilityOf(taskListHeader));
    }

    /**
     * Returns the message on the pop up modal that appears after a List or folder is copied.
     *
     * @return a String containing the message on the pop up modal that appears after a some actions is realized.
     */
    public String getCopyConfirmationMessage() {
        getWait().until(ExpectedConditions.visibilityOf(informationPopUp));
        String result = copyConfirmationMessage.getText();
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
        return WebElementActions.getText(getProjectElementByName(projectName));
    }

    /**
     * Returns a projectMenu webElement.
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
    private void projectMenu(final String projectName) {
        WebElementActions.click(getProjectMenuElementByName(projectName));
    }

    /**
     * Selects delete button.
     *
     * @param projectName that is the name of the project.
     */
    public void deleteProject(final String projectName) {
        projectMenu(projectName);
        WebElementActions.click(deleteBtn);
        getWait().until(ExpectedConditions.visibilityOf(confirmDeleteBtn));
        getWait().until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn));
        WebElementActions.click(confirmDeleteBtn);
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
    }
}
