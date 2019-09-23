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
import core.selenium.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * StarPage Class.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class StarPage extends BasePage {
    @FindBy(css = ".cu2-project-list-bar__add-icon > .ng-star-inserted")
    private WebElement addNewButton;

    @FindBy(css = ".cu-form__input")
    private WebElement inputNameSpaceTextBox;

    @FindBy(css = ".cu-btn")
    private WebElement nextButton;

    /**
     * This Method create a new space.
     *
     * @param nameSpace String parameter.
     */
    public void addNewSpace(String nameSpace) {
        addNewButton.click();
        inputNameSpaceTextBox.sendKeys(nameSpace);
        for (int buttonPresses = 0; buttonPresses < 7; buttonPresses++) {
            nextButton.click();
        }
    }

    /**
     * This method find name space.
     *
     * @param nameSpace parameter string.
     * @return boolean result.
     */
    public boolean isFoundNameSpace(String nameSpace) {
        return getDriver().getPageSource().contains(nameSpace);
    }
}
