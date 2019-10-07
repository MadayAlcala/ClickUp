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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ListPanel class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListPanel extends BasePage {
    private static final String LIST_BTN = "//cu-nav-section[contains(.,'%s')]";
    private static final String LIST_MENU_BTN = "//following-sibling::div[@class='nav-section__menu']";

    @FindBy(xpath = "//div[@class='cu-btn__text'][contains(.,'Delete')]")
    private WebElement confirmDelete;

    @FindBy(xpath = "//div/div/a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(css = ".lv-empty_img > img")
    private WebElement emptyTaskListImg;

    @FindBy(css = ".sidebar-section__plus-icon")
    private WebElement iconBtn;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement nameTxtField;

    @FindBy(css = "div.cu-list-group__name > cu-editable.cu-editable")
    private WebElement listNameHeader;

    /**
     * Selects the '+' symbol to displayed their options.
     */
    private void getIconBtn() {
        WebElementActions.click(iconBtn);
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
    public void createList(final String listName) {
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(listNameHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
        getIconBtn();
        getListBox();
        WebElementActions.sendKeys(nameTxtField, listName);
        WebElementActions.sendKeys(nameTxtField, Keys.ENTER);
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
    public String nameList(final String listName) {
        return WebElementActions.getText(getListElementByName(listName));
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
        WebElementActions.click(getListMenuElementByName(listName));
    }

    /**
     * Selects delete button.
     *
     * @param listName that is the name of the list.
     */
    public void deleteList(final String listName) {
        listMenu(listName);
        WebElementActions.click(deleteBtn);
        getWait().until(ExpectedConditions.elementToBeClickable(confirmDelete));
        WebElementActions.click(confirmDelete);
        getWait().until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(listNameHeader),
                ExpectedConditions.visibilityOf(emptyTaskListImg)
        ));
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
