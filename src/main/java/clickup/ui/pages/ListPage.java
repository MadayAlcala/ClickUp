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
 * @version 0.0.1
 */
public class ListPage extends BasePage {
    @FindBy(css = ".sidebar-section__plus-icon")
    private WebElement iconBtn;

    @FindBy(css = ".cu-dropdown-list-item__icon_new-list")
    private WebElement listBox;

    @FindBy(css = ".nav-section-maker__input")
    private WebElement nameTxtField;

    private void getIconBtn() {

        iconBtn.click();
    }

    private void getListBox() {
        listBox.click();
    }

    public void createList(String listName) {
        getIconBtn();
        getListBox();
        nameTxtField.sendKeys(listName);
        nameTxtField.sendKeys(Keys.ENTER);
    }

       public String nameTxtBox(String listName) {
//           return getDriver().findElement(By.xpath("//div[@class='nav-section'] //a[contains(.,'"+listName+"')]")).getText();

           return getDriver().findElement(By.xpath("//*[@id='-3']/cu-nav-section/div/a[contains(.,'"+listName+"')]")).getText();

       }
}
