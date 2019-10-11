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
import clickup.ui.pages.AddNewModal;
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

    @FindBy(css = ".nav-section-maker__input")
    private WebElement listNameMakerTxtField;

    @FindBy(css = ".nav-editor__input")
    private WebElement listNameEditorTxtField;

    @FindBy(css = "div.cu-list-group__name > cu-editable.cu-editable")
    private WebElement listNameHeader;
//
//    /**
//     * Selects the '+' symbol to displayed their options.
//     */
//    private void addBtn() {
//        WebElementActions.click(addBtn);
//    }

    /**
     * Creates a new list.
     *
     * @param listName that is the name of the new List.
     */
    public void newNameList(final String listName) {
//        getWait().until(ExpectedConditions.or(
//                ExpectedConditions.visibilityOf(listNameHeader),
//                ExpectedConditions.visibilityOf(emptyTaskListImg)
//        ));
        WebElementActions.sendKeys(listNameMakerTxtField, listName);
        WebElementActions.enter(listNameMakerTxtField);
        getWait().until(ExpectedConditions.visibilityOf(listNameHeader));
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

    /**
     * Updates the name of a List.
     *
     * @param newListName that is the new name for the list.
     */
    public void updateNameList(final String newListName) {
        WebElementActions.sendKeysWithDeleteText(listNameEditorTxtField, newListName);
        WebElementActions.enter(listNameEditorTxtField);
        getWait().until(ExpectedConditions.visibilityOf(listNameHeader));
    }

    /**
     * Creates a new folder.
     *
     */
    public AddNewModal addNewBtn() {
//        getWait().until(ExpectedConditions.or(
//                ExpectedConditions.visibilityOf(listNameHeader),
//                ExpectedConditions.visibilityOf(emptyTaskListImg)
//        ));
        WebElementActions.click(addBtn);
//        WebElementActions.click(folderBox);
        return new AddNewModal();
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

    /**
     * Waits for the List Name To appear on the content panel.
     *
     * @param newListName a String containing the title of a list, most likely a newly created one.
     */
    public void waitForHeaderElementTextEqualsCreatedListName(final String newListName) {
        ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> listNameHeader.getText().equals(newListName);
        getWait().until(elementTextEqualsString);
    }
}
