/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package clickup.ui.pages.ListPanelModal;

import clickup.ui.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Report.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class ReportingPage extends BasePage {
    @FindBy(xpath = "//*[contains(text(), 'Admin')]")
    private WebElement userAdminDone;
    @FindBy(xpath = "//*[contains(text(), 'UserTest')]")
    private WebElement userTestDone;
    @FindBy(css = ".cu-banner-popup__button.cu-banner-popup__button_purple")
    private WebElement bannerPurpleButton;

    /**
     * Lets verify if the task is done.
     */
    public void verifyTaskDone() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(2700);
        robot.keyPress(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_F5);
    }

    /**
     * Lets verify is the admin user is showed.
     *
     * @return boolean variable.
     */
    public boolean isDoneTaskAdmin() {
        return userAdminDone.isEnabled();
    }

    /**
     * Lets verifi is the user is showed.
     *
     * @return boolean variable.
     */
    public boolean isDoneTaskUserTest() {
        return userTestDone.isEnabled();
    }
}
