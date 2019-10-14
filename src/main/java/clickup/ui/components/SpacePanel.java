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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Saves some element to the page clickUp.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpacePanel extends BasePage {
    private static final int BUTTONCLICK = 7;
    private static final String SPACE_ELEMENT = "//cu2-project-list-bar-item[contains(.,'%s')]";

    @FindBy(css = ".cu2-project-list-bar__add .cu2-project-list-bar__add-icon")
    private WebElement addNewButton;

    @FindBy(xpath = "By.xpath(\"//body\")")
    private WebElement bodyPage;

    @FindBy(css = ".cu-btn:nth-child(2) > .cu-btn__text")
    private WebElement confirmDeleteBtn;

    @FindBy(xpath = "//a[contains(.,'Delete')]")
    private WebElement deleteBtn;

    @FindBy(css = ".cu-dc__input")
    private WebElement deleteTxtField;

    @FindBy(css = "img[src *= 'no-lists']")
    private WebElement emptyTaskListImg;

    @FindBy(css = ".cu-form__input")
    private WebElement inputNameSpaceTextBox;

    @FindBy(css = "[class='cu-user-settings-menu__link cu-user-settings-menu__link_logout']")
    private WebElement logOutButton;

    @FindBy(css = ".cu-btn")
    private WebElement nextButton;

    @FindBy(css = ".cu-avatar-container")
    private WebElement spaceBarButton;

    @FindBy(css = ".cu-dropdown__toggle .nav-menu__toggle")
    private WebElement spaceMenuBtn;

    /**
     * Creates a new space.
     *
     * @param nameSpace String parameter.
     */
    public void addNewSpace(final String nameSpace) {
        WebElementActions.click(addNewButton);
        WebElementActions.sendKeys(inputNameSpaceTextBox, nameSpace);
        getWait().until(ExpectedConditions.textToBePresentInElement(inputNameSpaceTextBox,
                inputNameSpaceTextBox.getText()));
        for (int buttonPresses = 0; buttonPresses < BUTTONCLICK; buttonPresses++) {
            getWait().until(ExpectedConditions.elementToBeClickable(nextButton));
            nextButton.click();
        }
        SpacePanel spaceMenu = new SpacePanel();
        getWait().until(ExpectedConditions.visibilityOf(emptyTaskListImg));
    }

    /**
     * Finds name space in the page.
     *
     * @param nameSpace parameter string.
     * @return boolean result.
     */
    public boolean isFoundNameSpace(final String nameSpace) {
        return getDriver().getPageSource().contains(nameSpace);
    }

    /**
     * Returns a space webElement.
     *
     * @param spaceName that is the name of the space to find.
     * @return WebElement 'space'.
     */
    private WebElement getSpaceElementByName(final String spaceName) {
        return getDriver().findElement(By.xpath(String.format(SPACE_ELEMENT, spaceName)));
    }

    /**
     * Deletes a Space.
     *
     * @param spaceName that is the name of the space to delete.
     */
    public void deleteSpace(final String spaceName) {
        WebElementActions.click(getSpaceElementByName(spaceName));
        WebElementActions.click(spaceMenuBtn);
        getWait().until(ExpectedConditions.elementToBeClickable(deleteBtn));
        WebElementActions.click(deleteBtn);
        getWait().until(ExpectedConditions.visibilityOf(deleteTxtField));
        WebElementActions.sendKeys(deleteTxtField, "delete");
        getWait().until(ExpectedConditions.textToBePresentInElement(deleteTxtField, deleteTxtField.getText()));
        WebElementActions.click(confirmDeleteBtn);
    }
}
