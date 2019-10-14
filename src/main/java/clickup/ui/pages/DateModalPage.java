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
import core.utils.WebElementActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Date Modal Page Object Modal.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public class DateModalPage extends BasePage {
    @FindBy(xpath = "//*[@class='cu-datetime-input__value-label' and contains(text(), 'Start date')]")
    private WebElement startDateSelector;

    @FindBy(xpath = "//*[@class='cu-datetime-input__value-label' and contains(text(), 'Due date')]")
    private WebElement dueDateSelector;

    @FindBy(css = "*.cu-quick-date-options__item-title")
    private List<WebElement> quickDateOptions;

    @FindBy(css = "div.cu-calendar-picker__done")
    private WebElement closeButton;

    /**
     * Picks a date option from the list presented in Date Modal.
     *
     * @param option a String matching one of the options listed.
     */
    private void pickQuickDateOption(final String option) {
        for (WebElement element : quickDateOptions) {
            if (element.getText().equals(option)) {
                element.click();
                break;
            }
        }
    }


    /**
     * Selects Start Date for Task.
     *
     * @param option a String containing the option to be selected.
     */
    public void pickStartDate(final String option) {
        WebElementActions.click(startDateSelector);
        pickQuickDateOption(option);
    }

    /**
     * Selects Due Date for Task.
     *
     * @param option a String containing the option to be selected.
     */
    public void pickDueDate(final String option) {
        WebElementActions.click(dueDateSelector);
        pickQuickDateOption(option);
    }

    /**
     * Closes Date Selector Modal.
     *
     * @return a new intance of TaskModal POM.
     */
    public TaskModalPage close() {
        WebElementActions.click(closeButton);
        return new TaskModalPage();
    }
}
