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

import core.utils.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ListMenu class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListMenu extends ApplicationBasePage {
    private static final String LIST_BTN = "//cu-nav-section[contains(.,'%s')]";
    private static final String LIST_MENU_BTN = "//following-sibling::div[@class='nav-section__menu']";
    private static final String DASHBOARD_TASKS = "//cu-dashboard-board-card[contains(.,'%s')]";

    @FindBy(xpath = "//div/div/a[@cutooltip='Create task']")
    private WebElement addNewTaskBtn;

    @FindBy(css = "cu-data-view-item:nth-child(2) > a.cu-data-view-item__link.cu-data-view-item__link_icon")
    private WebElement boardView;

    @FindBy(xpath = "//div[@class='cu-btn__text'][contains(.,'Delete')]")
    private WebElement confirmDeleteBtn;

    @FindBy(css = "button > .cu-draft-view__submit-btn")
    private WebElement createTaskBtn;

    @FindBy(xpath = "//div/div/a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(css = "img[src *= 'no-lists']")
    private WebElement emptyTaskListImg;

    @FindBy(css = ".sidebar-section__plus-icon")
    private WebElement iconBtn;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement listNameTxtField;

    @FindBy(css = ".cu-search-filter .cu-search-filter__input")
    private WebElement searchTxtField;

    @FindBy(css = "*[class *= 'item-label-body cu-task-list-header']")
    private WebElement taskListHeader;

    @FindBy(css = ".cu-form__input")
    private WebElement taskNameTxtField;

    /**
     * Selects the '+' symbol to displayed their options.
     */
    private void getIconBtn() {
        Actions.click(iconBtn);
    }

    /**
     * Selects the option 'New List' to create a new List.
     */
    private void getListBox() {
        Actions.click(listBox);
    }

    /**
     * Creates a new list.
     *
     * @param listName that is the name of the new List.
     */
    public void createList(final String listName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        getIconBtn();
        getListBox();
        Actions.sendKeys(listNameTxtField, listName);
        Actions.sendKeys(listNameTxtField, Keys.ENTER);
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
    public String nameList(final String listName) {
        return Actions.getText(getListElementByName(listName));
    }

    /**
     * Returns a listMenu webElement.
     *
     * @param listName that is the name of the list to find.
     * @return WebElement 'listMenu'.
     */
    private WebElement getListMenuElementByName(final String listName) {
        return getDriver().findElement(By.xpath(String.format(LIST_BTN, listName).concat(LIST_MENU_BTN)));
    }

    /**
     * Selects the list' menu.
     *
     * @param listName that is the name of the list.
     */
    private void listMenu(final String listName) {
        Actions.click(getListMenuElementByName(listName));
    }

    /**
     * Selects delete button.
     *
     * @param listName that is the name of the list.
     */
    public void deleteList(final String listName) {
        listMenu(listName);
        Actions.click(deleteBtn);
        getWait().until(ExpectedConditions.elementToBeClickable(confirmDeleteBtn));
        Actions.click(confirmDeleteBtn);
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
    }

    public void addNewTask(String listName, String taskName) {
        listMenu(listName);
        Actions.click(addNewTaskBtn);
        Actions.sendKeys(taskNameTxtField, taskName);
        Actions.click(createTaskBtn);
    }


    /**
     * Returns a task webElement.
     *
     * @param taskName that is the name of the task to find.
     * @return WebElement 'taskName'.
     */
    private WebElement findTaskInDashboard(final String taskName) {
        return getDriver().findElement(By.xpath(String.format(DASHBOARD_TASKS, taskName)));
    }

    /**
     * Searches a task in the search filter.
     *
     * @param taskName that is a String of the task' name that wants to search.
     */
    public void searchTask(final String taskName) {
        Actions.click(boardView);
        Actions.click(searchTxtField);
        Actions.sendKeys(searchTxtField, taskName);
        Actions.sendKeys(searchTxtField, Keys.ENTER);
    }

    /**
     * Finds a task in the dashboard that was already searched.
     *
     * @param taskName that is a String of the task' name that wants to find.
     * @return a String with the text of the task found.
     */
    public String findTask(final String taskName) {
        return Actions.getText(findTaskInDashboard(taskName));
    }
}
