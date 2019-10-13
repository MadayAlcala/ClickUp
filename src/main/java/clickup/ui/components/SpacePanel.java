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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

/**
 * Saves some element to the page clickUp.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class SpacePanel extends BasePage {
    private static final int BUTTONCLICK = 7;
    private static final String SPACE_ELEMENT = "//a[contains(.,'%s')]";

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

    @FindBy(css = ".sidebar-section__name > cu-project-menu > div > div")
    private WebElement spaceMenuBtn;

    @FindBy(xpath = "//div[contains(@class,'cu-create-project-modal__switch-privacy-title')][contains(.,'Admin Workplace')]")
    private WebElement spaceForAdmin;

    @FindBy(xpath = "//div[contains(@class,'cu-create-project-modal__switch-privacy-title')][contains(.,'Private')]")
    private WebElement spaceForPrivate;

    @FindBy(xpath = "//div[contains(@class,'status-template__name active setup-step-project__preset-status-list-item-text')][contains(.,'Custom')]")
    private WebElement customButton;

    @FindBy(xpath = "//div[contains(@class,'status-template__name setup-step-project__preset-status-list-item-text')][contains(.,'Kanban')]")
    private WebElement kanbanButton;

    @FindBy(xpath = "//div[contains(@class,'status-template__name setup-step-project__preset-status-list-item-text')][contains(.,'Content')]")
    private WebElement contentButton;

    @FindBy(xpath = "//div[contains(@class,'status-template__name setup-step-project__preset-status-list-item-text')][contains(.,'Marketing')]")
    private WebElement marketingButton;

    @FindBy(xpath = "//div[contains(@class,'status-template__name setup-step-project__preset-status-list-item-text')][contains(.,'Scrum')]")
    private WebElement scrumButton;

    @FindBy(xpath = "//div[contains(@class,'status-template__name setup-step-project__preset-status-list-item-text')][contains(.,'Normal')]")
    private WebElement normalButton;


    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Multiple')]")
    private WebElement multipleButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Time Tracking')]")
    private WebElement timeTrackingButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Priority')]")
    private WebElement priorityButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Tags')]")
    private WebElement tagButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Time Estimates')]")
    private WebElement timeEstimatesButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Custom fields')]")
    private WebElement custonFieldsButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Dependency Warning')]")
    private WebElement dependencyWarningButton;
    @FindBy(xpath = "//div[contains(@class,'cu-sts__addon-title')][contains(.,'Remap Subtask Due Dates')]")
    private WebElement dueDatesButton;

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

    public void createSpaceSetting(Map<String, String> inputContentSpaceSettings) {
        inputContentSpaceSettings.forEach((key, value) -> {
            System.out.println("el Key -> " + key);
            System.out.println("el value -> " + value);
        });
        System.out.println(inputContentSpaceSettings.get("Name"));
//        Actions.click(addNewButton);
//        Actions.sendKeys(inputNameSpaceTextBox, inputContentSpaceSettings.get("Name"));
//        getWait().until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
        //color
        nextButton.click();
        //how is this space
        if ("Admin Workplace".equals(inputContentSpaceSettings.get("Who"))) {
            spaceForAdmin.click();
        } else {
            spaceForPrivate.click();
        }
        nextButton.click();
        //templeates

        switch (inputContentSpaceSettings.get("Type")) {
            case "Scrum":
                scrumButton.click();
                break;
            case "Marketing":
                marketingButton.click();
                break;
            case "Normal":
                normalButton.click();
                break;
            case "Content":
                contentButton.click();
                break;
            case "Kanban":
                kanbanButton.click();
                break;
            case "Custom":
                customButton.click();
                break;
        }

    }
}
