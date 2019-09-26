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

/**
 * ListPage class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class ListPage extends BasePage {
    @FindBy(css = ".sidebar-section__plus-icon")
    private WebElement iconBtn;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement nameTxtField;

    @FindBy(xpath = "//div/div/a[@cutooltip='Delete']")
    private WebElement deleteBtn;

    @FindBy(xpath = "//div[@class='cu-btn__text'][contains(.,'Delete')]")
    private WebElement conrfirmDelete;

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
        getIconBtn();
        getListBox();
        nameTxtField.sendKeys(listName);
        nameTxtField.sendKeys(Keys.ENTER);
    }

    /**
     * Returns the Name of the new List.
     *
     * @param listName to find the specific element.
     */
    public void nameList(final String listName) {
        getDriver().findElement(By.xpath("//*[@id='-3']/cu-nav-section[contains(.,'"
                + listName + "')]"));
    }

    /**
     * Returns the Name of the new List.
     *
     * @param listName to find the specific element.
     * @return a String with the name of list created.
     */
    public String nameTxtBox(final String listName) {
        return getDriver().findElement(By.linkText(listName)).getText();
    }

    /**
     * Selects the list' menu.
     *
     * @param listName that is the name of the list.
     */
    public void listMenu(final String listName) {
        getDriver().findElement(By.xpath("//cu-nav-section[contains(.,'"
                + listName
                + "')]//following-sibling::div[@class='nav-section__menu']")).click();
    }

    /**
     * Selects delete button.
     */
    public void deleteList() {
        deleteBtn.click();
        conrfirmDelete.click();
    }
}