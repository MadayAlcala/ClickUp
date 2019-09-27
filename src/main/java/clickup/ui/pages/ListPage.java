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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ListPage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListPage extends BasePage {
    private static final String LIST_BTN = "//cu-nav-section[contains(.,'%s')]";
    private static final String LIST_MENU_BTN = "//following-sibling::div[@class='nav-section__menu']";

    @FindBy(xpath = "//div[@class='cu-btn__text'][contains(.,'Delete')]")
    private WebElement conrfirmDelete;

    @FindBy(xpath = "//div/div/a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(css = "img[src *= 'no-lists']")
    private WebElement emptyTaskListImg;

    @FindBy(css = ".sidebar-section__plus-icon")
    private WebElement iconBtn;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement nameTxtField;

    @FindBy(css = "*[class *= 'item-label-body cu-task-list-header']")
    private WebElement taskListHeader;

    /**
     * Selects the '+' symbol to displayed their options.
     */
    private void getIconBtn() {
        iconBtn.click();
    }

    /**
     * Selects the option 'New List' to create a new List.
     */
    private void getListBox() {
        listBox.click();
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
        nameTxtField.sendKeys(listName);
        nameTxtField.sendKeys(Keys.ENTER);
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
        return getListElementByName(listName).getText();
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
    public void listMenu(final String listName) {
        getListMenuElementByName(listName).click();
    }

    /**
     * Selects delete button.
     */
    public void deleteList() {
        deleteBtn.click();
        getWait().until(ExpectedConditions.elementToBeClickable(conrfirmDelete));
        conrfirmDelete.click();
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(taskListHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
    }
}
