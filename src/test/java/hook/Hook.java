/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package hook;

import core.selenium.WebDriverManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Allows to execute some steps.
 *
 * @author Jesus Menacho
 * @version 1.0
 */
public class Hook {
    private WebDriver webDriver;

    /**
     * Allows init the webDriver.
     */
    public Hook() {
        this.webDriver = WebDriverManager.getInstance().getWebDriver();
    }

    /**
     * Allows take a screenshot after the scenarios failures.
     *
     * @param scenario that represent the scenarios of cucumber.
     */
    @After(order = 2)
    public void tearDown(final Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
    }

    /**
     * Allows take a screenshot after some failure.
     *
     * @param scenario that represent the scenarios of cucumber.
     */
    public void takeScreenshot(final Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
